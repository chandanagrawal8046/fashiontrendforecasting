<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.fashion.datagenerators.*" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
int prodid=Integer.parseInt(String.valueOf(session.getAttribute("prodid")));
ProductDetailsGenerator dtl=new ProductDetailsGenerator(prodid);
session.setAttribute("stock", dtl.getStock());

%>

	<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Enjoy Shopping Here..</h2></div>
    </div></div></div>
    <!-- End All Title Box -->

	
	<!-- Start Shop Detail  -->
    <div class="shop-detail-box-main">
        <div class="container">
            <div class="row">
                <div class="col-xl-5 col-lg-5 col-md-6">
                    <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active"> <img class="d-block w-100" src="<%=dtl.getImgurl() %>" alt="First slide"> </div>
                        </div>
						</div>
                </div>
                <div class="col-xl-7 col-lg-7 col-md-6">
                    <div class="single-product-details">
                        <h2><%=dtl.getTitle() %></h2>
                        <h5> Rs. <%=dtl.getPrice()%></h5>
                        <p class="available-stock"><span><%=dtl.getStock()%> items available in stock</span><p>
                        <p class="available-stock" style="color:purple;font-weight: bold;"><span>Currently in <%=dtl.getWishcount()%> wishlists..</span><p>
						<h4>Short Description:</h4>
						<p><%=dtl.getDescription() %></p>
						<form name="frm" method="post" action="addtocart">
						<ul>
							<li>
								<div class="form-group quantity-box">
									<label class="control-label">Quantity</label>
									<input name="quantity" class="form-control" value="0" min="0" max="20" type="number">
								</div>
							</li>
						</ul>

						<div class="price-box-bar">
							<div class="cart-and-bay-btn">								
                                <input type="submit" class="btn hvr-hover" data-fancybox-close="" value="Add to cart"/>
                                <a class="btn hvr-hover" href="addtowishlist?prodid=<%=dtl.getId()%>"><i class="fas fa-heart"></i> Add to wishlist</a>
							</div>
						</div>
						</form>
						</div>
                </div>
            </div>
        
		<!-- --------------------------------------------------------- -->
            
		<!-- Start Shop Detail -->
    	<div class="row my-5">
              <div class="col-lg-12">
                  <div class="title-all">                  
	        		<div class="col-lg-10"><h3 style="font-weight: bold;color:#1e90ff">People Also Viewed</h3></div>
                  </div>
                  <div class="featured-products-box owl-carousel owl-theme">
                  
                      <spring:forEach items="${viewreport}" var="p">
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
<!-- --------------------------------------------------------- -->




        </div>
    </div>
    <!-- End Cart -->
    
    <!-- --------------------------------------------------- -->

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