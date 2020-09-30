
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fashion.models.Preferences"%>    
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
            
            Preferences pr=new Preferences();
            pr.trackPreferences(String.valueOf(session.getAttribute("user")));            
        }
        catch(Exception ex){response.sendRedirect("invalid");}
%>
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Navigate.jsp"></jsp:include>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Welcome : <%=String.valueOf(session.getAttribute("username")) %></h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
   
    <!-- Start My Account  -->
    <div class="my-account-box-main">
        <div class="container">
            <div class="my-account-page">
                <div class="row">
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="orderslist"> <i class="fa fa-gift"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Your Orders</h4>
                                    <p>View, return, or buy things again</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="custprofile"><i class="fa fa-lock"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Login &amp; details</h4>
                                    <p>Edit your name, mobile, like profile</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="chpass"> <i class="fa fa-location-arrow"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Account security</h4>
                                    <p>Edit & manage your password</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="mywishlist"> <i class="fa fa-trophy"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Wishlist</h4>
                                    <p>Manage your wishlist</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="recommend"> <i class="fab fa-apple"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Preferences</h4>
                                    <p>Products recommendations for you</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div class="account-box">
                            <div class="service-box">
                                <div class="service-icon">
                                    <a href="profilewise"> <i class="fab fa-archway"></i> </a>
                                </div>
                                <div class="service-desc">
                                    <h4>Recommendation</h4>
                                    <p>Profile matching recommendations</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
        </div>
    </div>
    <!-- End My Account -->
    
    
    
    <!-- Start Shop Detail  -->
    <div class="shop-detail-box-main">
        <div class="container">
    	<div class="row my-5">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>Recommended Products for you</h1>
                        <p>The products you recently added in your shopping cart & removed</p>
                    </div>
                    <div class="featured-products-box owl-carousel owl-theme">
                        
                        <spring:forEach items="${history}" var="p">
                        <div class="item">
                            <div class="products-single fix">
                                <div class="box-img-hover">
                                    <img src="Products/${p.imgurl}" class="img-fluid" alt="Image">
                                    <div class="mask-icon">
                                        <ul>
                                            <li><a href="viewprod?prodid=${p.prodid}" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>                                            
                                            <li><a href="#" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                                        </ul>
                                        <a class="cart" href="viewprod?prodid=${p.prodid}">Add to Cart</a>
                                    </div>
                                </div>
                                <div class="why-text">
                                    <h4>${p.title}</h4>
                                    <h5>Rs. ${p.price}</h5>
                                </div>
                            </div>
                        </div>
                        </spring:forEach>
                        
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