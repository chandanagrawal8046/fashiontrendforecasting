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
<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Shopping Cart</h2></div>
    </div></div></div>
    <!-- End All Title Box -->

<!-- Start Cart  -->
    <div class="cart-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Images</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<spring:forEach items="${cart}" var="p">
                                <tr>
                                    <td class="thumbnail-img"><a href="#"><img class="img-fluid" src="${p.imgurl}" /></a></td>
                                    <td class="name-pr"><a href="#">${p.title}</a></td>
                                    <td class="price-pr"><p>Rs. ${p.price}</p></td>
                                    <td class="quantity-box">${p.quantity}</td>
                                    <td class="total-pr"><p>Rs.${p.total}</p></td>
                                    <td class="remove-pr"><a href="delfromcart?cartid=${p.id }" 
                                    onclick="javascript:return confirm('Are you sure you want to delete product from cart.??')">
                                    <i class="fas fa-times"></i></a></td>
                                </tr>
                                </spring:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row my-5">               
                <div class="col-lg-6 col-sm-6">
                    <div class="update-box">
                    	<form name=frm method="post" action="continue">
                        <input value="Continue Shopping" type="submit">
                        </form>
                    </div>
                </div>
            </div>

            <div class="row my-5">
                <div class="col-lg-8 col-sm-12"></div>
                <div class="col-lg-4 col-sm-12">
                    <div class="order-box">
                        <h3>Order summary</h3>
                        <div class="d-flex">
                            <h4>Sub Total</h4>
                            <div class="ml-auto font-weight-bold"> Rs. ${cartsum} </div>
                        </div>
                        <div class="d-flex">
                            <h4>Discount</h4>
                            <div class="ml-auto font-weight-bold"> Rs. ${discount } </div>
                        </div>
                        <hr class="my-1">                        
                        <div class="d-flex">
                            <h4>Tax</h4>
                            <div class="ml-auto font-weight-bold"> Rs. ${tax } </div>
                        </div>
                        <div class="d-flex">
                            <h4>Shipping Cost</h4>
                            <div class="ml-auto font-weight-bold"> Free </div>
                        </div>
                        <hr>
                        <div class="d-flex gr-total">
                            <h5>Grand Total</h5>
                            <div class="ml-auto h5"> Rs. ${grandtotal } </div>
                        </div>
                        <hr> </div>
                </div>
                <div class="col-12 d-flex shopping-box"><a href="orderinfo" class="ml-auto btn hvr-hover">Checkout</a> </div>
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
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery.nicescroll.min.js"></script>
    <script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>


</body>
</html>