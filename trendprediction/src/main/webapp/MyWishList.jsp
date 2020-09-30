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
    <div class="col-lg-12"><h2>My Wishlist</h2></div>
    </div></div></div>
    <!-- End All Title Box -->


<!-- Start Wishlist  -->
    <div class="wishlist-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Images</th>
                                    <th>Product Name</th>
                                    <th>Unit Price </th>
                                    <th>Stock</th>
                                    <th>Add Item</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<spring:forEach items="${wishlist}" var="p">
                                <tr>
                                    <td class="thumbnail-img">
                                        <a href="#"><img class="img-fluid" src="${p.imgurl}" alt="" /></a>
                                    </td>
                                    <td class="name-pr"><a href="#">${p.title }</a></td>
                                    <td class="price-pr"><p>Rs. ${p.price }</p></td>
                                    <spring:if test="${p.stock>0}">
                                    	<td class="quantity-box">In Stock</td>
                                    </spring:if>
                                    <spring:if test="${p.stock==0}">
                                    	<td class="quantity-box" style="color:red">Out of stock</td>
                                    </spring:if>
                                    <td class="add-pr"><a class="btn hvr-hover" href="viewprod?prodid=${p.prodid}">Add to Cart</a></td>
                                    <td class="remove-pr"><a href="deletefromwish?id=${p.id}" onclick="javascript:return confirm('Are you sure you want to delete product from wishlist.??')"><i class="fas fa-times"></i></a></td>
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