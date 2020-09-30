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
    <div class="col-lg-12"><h2>Recommendations based on your preferences</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
   
    
    
    <!-- Start Shop Detail  -->
    <div class="shop-detail-box-main">
        <div class="container">
    	<div class="row my-5">
              <div class="col-lg-12">
                  <div class="title-all">
                  <spring:forEach items="${prefreport1}" var="p" end="0">
	        	<div class="col-lg-10"><h3 style="font-weight: bold;color:#1e90ff">Preference 1 : ${p.pref}</h3>
	        	<p>You preferred this category : ${p.hits} times</p></div>
	        </spring:forEach>                        
                  </div>
                  <div class="featured-products-box owl-carousel owl-theme">
                      <spring:forEach items="${prefreport1}" var="p">
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
        
    <!-- End Cart -->
    <!-- --------------------------------------------------------- -->
<!-- Start Shop Detail  -->
    
    	<div class="row my-5">
              <div class="col-lg-12">
                  <div class="title-all">
                  <spring:forEach items="${prefreport2}" var="p" end="0">
	        	<div class="col-lg-10"><h3 style="font-weight: bold;color:#1e90ff">Preference 1 : ${p.pref}</h3>
	        	<p>You preferred this category : ${p.hits} times</p></div>
	        </spring:forEach>                        
                  </div>
                  <div class="featured-products-box owl-carousel owl-theme">
                      <spring:forEach items="${prefreport2}" var="p">
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
       
    <!-- End Cart -->
			



	<!-- --------------------------------------------------------- -->
    <!-- Start Shop Detail  -->
   
    	<div class="row my-5">
              <div class="col-lg-12">
                  <div class="title-all">
                  <spring:forEach items="${prefreport3}" var="p" end="0">
	        	<div class="col-lg-10"><h3 style="font-weight: bold;color:#1e90ff">Preference 1 : ${p.pref}</h3>
	        	<p>You preferred this category : ${p.hits} times</p></div>
	        </spring:forEach>                        
                  </div>
                  <div class="featured-products-box owl-carousel owl-theme">
                      <spring:forEach items="${prefreport3}" var="p">
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