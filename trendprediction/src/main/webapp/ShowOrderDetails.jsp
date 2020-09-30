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
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Navigate.jsp"></jsp:include>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Order Details</h2></div>
    </div></div></div>
    <!-- End All Title Box -->


<!-- Start Wishlist  -->
    <div class="wishlist-box-main">
        <div class="container">
        <div class="row text-center" >
        <spring:forEach items="${orderdetails}" var="p" end="0">
        	<div class="col-lg-3"><h3 style="font-weight: bold;color:#1e90ff">Order No : ${p.orderno}</h3></div>
        	<div class="col-lg-3"><h3 style="font-weight: bold;color:#1e90ff">Customer Name : ${p.name}</h3></div>
        	<div class="col-lg-3"><h3 style="font-weight: bold;color:#1e90ff">Order Date : ${p.orderdt}</h3></div>
        	<div class="col-lg-3"><h3 style="font-weight: bold;color:#1e90ff">Bill Amount : Rs. ${p.bill}</h3></div>
        </spring:forEach>
        
        </div><br/>
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                     
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Images</th>
                                    <th>Product Name</th>
                                    <th>Unit Price </th>
                                    <th>Quantity</th>
                                    <th>Total</th>                                    
                                </tr>
                            </thead>
                            <tbody>
                            	<spring:forEach items="${orderdetails}" var="p" >
                                <tr>
                                    <td class="thumbnail-img">
                                        <a href="#"><img class="img-fluid" src="${p.imgurl}" alt="" /></a>
                                    </td>
                                    <td class="name-pr"><a href="#">${p.title }</a></td>
                                    <td class="price-pr"><p>Rs. ${p.price }</p></td>                                    
                                    <td class="quantity-box">${p.quantity }</td>
                                    <td class="price-pr"><p>Rs. ${p.total }</p></td> 
                                </tr>
                                </spring:forEach>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Wishlist -->







<jsp:include page="Footer.jsp"></jsp:include>   
<!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    
    <script src="js/jquery.superslides.min.js"></script>
    <script src="js/bootstrap-select.js"></script>
    <script src="js/inewsticker.js"></script>
    <script src="js/bootsnav.js."></script>
    <script src="js/images-loded.min.js"></script>
    <script src="js/isotope.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/baguetteBox.min.js"></script>
    <script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>


</body>
</html>