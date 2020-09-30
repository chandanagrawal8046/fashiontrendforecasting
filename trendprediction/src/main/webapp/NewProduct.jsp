<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script language="Javascript" type="text/javascript">
        <!--

        function createRequestObject() {
            var tmpXmlHttpObject;
            if (window.XMLHttpRequest) {
                    tmpXmlHttpObject = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
            }
            return tmpXmlHttpObject;
        }

        var http = createRequestObject();

        function makeGetRequest(cat) {
           // alert(cat);
            http.open('get', 'catProducts?cat='+cat);
            http.onreadystatechange = processResponse;
            http.send(null);
        }

        function processResponse() {
            if(http.readyState == 4){
                var response = http.responseText;
                document.getElementById('report').innerHTML = response;
            }
        }
        -->
        </script>


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
                        <h2>New Product</h2>     <hr/> 
                                          
                        <form id="contactForm" method="post" action="regproduct" autocomplete="off" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="title" name="title" placeholder="Product Name" required data-error="Please enter product name"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group">                                       
                                        <select name="categories" id="categories" class="form-control" required data-error="Please Select Category" onchange="makeGetRequest(this.value)">
										<option>Select Category</option>
	                                    <spring:forEach items="${categories}" var="c">
	                                    <option value="${c.catname}">${c.catname}</option>
	                                    </spring:forEach>
										</select>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="price" name="price" placeholder="Product Price" required data-error="Please enter product price"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="file" class="form-control" id="file" name="file" required data-error="Please enter product image"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">                                       
                                        <select name="brand" id="brand" class="form-control" required data-error="Please Select Brand">
										<option>Select Brand</option>
	                                    <spring:forEach items="${companies}" var="c">
	                                    <option value="${c.name}">${c.name}</option>
	                                    </spring:forEach>
										</select>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="asinno" name="asinno" placeholder="Product ASINo" required data-error="Please enter product ASIN number"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="stock" name="stock" placeholder="Product Stock" required data-error="Please enter stock"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <textarea class="form-control" id="description" name="description" placeholder="Product Description" required data-error="Please enter description"></textarea>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">                                    
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
                    <h2>Products List</h2>     <hr/>                       
                        
                        <div id="report" class="col-lg-12 col-sm-12"></div>
                        
                        
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