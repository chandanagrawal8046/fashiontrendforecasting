<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fashion Trends Prediction</title>
</head>
<body>

<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Navigate.jsp"></jsp:include>

<!-- Start Slider -->
    <div id="slides-shop" class="cover-slides">
        <ul class="slides-container">
            <li class="text-center">
                <img src="images/banner-01.jpg" alt="">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="m-b-20"><strong>Trend Prediction using Fashion Dataset</strong></h1>                            
                        </div>
                    </div>
                </div>
            </li>
            <li class="text-center">
                <img src="images/banner-02.jpg" alt="">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="m-b-20"><strong>Trend Prediction using Fashion Dataset</strong></h1>                            
                        </div>
                    </div>
                </div>
            </li>
         
        </ul>
        <div class="slides-navigation">
            <a href="#" class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
            <a href="#" class="prev"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
        </div>
    </div>
    <!-- End Slider -->


	<!-- Start Categories  -->
    <div class="categories-shop">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="images/categories_img_01.jpg" alt="" />
                        <a class="btn hvr-hover" href="#"></a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="images/categories_img_02.jpg" alt="" />
                        <a class="btn hvr-hover" href="#"></a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="images/categories_img_03.jpg" alt="" />
                        <a class="btn hvr-hover" href="#"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Categories -->


	<!-- Start Shop Detail  -->
    <div class="shop-detail-box-main">
        <div class="container">
    	<div class="row my-5">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>Recently Viewed Products</h1>
                        <p>The products you recently showed interest in</p>
                    </div>
                    <div class="featured-products-box owl-carousel owl-theme">
                        
                        
                        <spring:forEach items="${history}" var="p">
                        <div class="item">
                            <div class="products-single fix">
                                <div class="box-img-hover">
                                    <img src="${p.imgurl}" class="img-fluid" alt="Image">
                                    <div class="mask-icon">
                                        <ul>
                                            <li><a href="viewprod?prodid=${p.prodid}" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>                                            
                                            <li><a href="addtowishlist?prodid=${p.prodid}" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
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
    <!-- ALL PLUGINS -->
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