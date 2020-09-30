<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
<%           
        response.setHeader("Cache-Control","no-cache"); 
        response.setHeader("Cache-Control","no-store"); 
        response.setDateHeader("Expires", 0);         
        try
        {        
            if((session.getAttribute("user")==null))             
            { response.sendRedirect("invalid");}
            else if(session.getAttribute("utype")==null)
            { response.sendRedirect("invalid");}               
        }
        catch(Exception ex){response.sendRedirect("invalid");}
        %>
<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Fashion Trends Administration</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-sm-12">
                    <div class="contact-form-right">
                        <h2>New Product Category</h2>                        
                        <form id="contactForm" method="post" action="regcategory" autocomplete="off">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="catname" name="catname" placeholder="Product Category Name" required data-error="Please enter product category">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                
                                <div class="col-md-10">                                    
                                    <div class="submit-button text-center">
                                        <button class="btn hvr-hover" id="submit" type="submit">Submit</button>
                                        <div id="msgSubmit" class="h3 text-center hidden"></div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
				<div class="col-lg-6 col-sm-12">
                    <div class="contact-form-right">
                        <h2>Categories List</h2>                        
                        <table class="table table-bordered" style="background-color: white;">
							<tr>
								<th>Category Name</th>
								<th>Date Created</th>
								<th>Action</th>			
							</tr>
						
							<spring:forEach items="${categories}" var="c">
							<tr>
								<td>${c.catname}</td>
								<td>${c.catdate}</td>								
								<td>
									<a href="deleteCat?cid=${c.catid}" onclick="return confirm('Are you sure you want to delete category.?')">Delete</a>	
								</td>		
							</tr>
							</spring:forEach>	
						</table>
                        
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Cart -->
    
    <jsp:include page="Footer.jsp"></jsp:include>
    
    
    <!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>