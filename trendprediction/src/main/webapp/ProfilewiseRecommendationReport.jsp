<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<script language="javascript" type="text/javascript">
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

        function makeGetRequest(type) {
          //  alert(type);
            http.open('get', 'profilereport?type='+type);
            http.onreadystatechange = processResponse;
            http.send(null);
        }

        function processResponse() {
            if(http.readyState == 4){
                var response = http.responseText;
                document.getElementById('prodreport').innerHTML = response;
            }
        }
        -->
        </script>
</head>
<body>

<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Profilewise Recommendation</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    <!-- Start Shop Page  -->
    <div class="shop-box-inner">
        <div class="container">
            <div class="row">
                <div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
                    <div class="right-product-box">
                        <div class="product-item-filter row">                        
                            <div class="col-12 col-sm-4 text-center text-sm-right">
                                <ul class="nav nav-tabs ml-auto">
                                    <li>
                                        <a class="nav-link active" href="#grid-view" data-toggle="tab"> <i class="fa fa-th"></i> </a>
                                    </li>
                                    <li>
                                        <a class="nav-link" href="#list-view" data-toggle="tab"> <i class="fa fa-list-ul"></i> </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

						<div id="prodreport">
						<!-- ------------------------------------------------- -->
                        <div class="product-categorie-box">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade show active" id="grid-view">
                                    <div class="row">
                                    <spring:forEach items="${recreport}" var="p">
                                    <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                                            <div class="products-single fix">
                                                <div class="box-img-hover">
                                                    <div class="type-lb">
                                                        <p class="new">New</p>
                                                    </div>
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
                                <div role="tabpanel" class="tab-pane fade" id="list-view">
                                    <spring:forEach items="${recreport}" var="p">
                                    <div class="list-view-box">                                    
                                        <div class="row">
                                            <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                                                <div class="products-single fix">
                                                    <div class="box-img-hover">
                                                        <div class="type-lb">
                                                            <p class="new">New</p>
                                                        </div>
                                                        <img src="${p.imgurl}" class="img-fluid" alt="Image">
                                                        <div class="mask-icon">
                                                            <ul>
                                                                <li><a href="viewprod?prodid=${p.prodid}" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>                                                                
                                                                <li><a href="createwishlist?prodid=${p.prodid}" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                                                            </ul>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-8 col-xl-8">
                                                <div class="why-text full-width">
                                                    <h4>${p.title}</h4>
                                                    <h5>Rs. ${p.price}</h5>
                                                    <h5>Brand : ${p.brand}</h5>
                                                    <p>${p.description}</p>
                                                    <a class="btn hvr-hover" href="viewprod?prodid=${p.prodid}">Add to Cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </spring:forEach>
                                    </div>
                            </div>
                        </div>
                        <!-- ------------------------------------------------- -->
                        </div>
                        
                    </div>
                </div>
				<div class="col-xl-3 col-lg-3 col-sm-12 col-xs-12 sidebar-shop-left">
                    <div class="product-categori">
                        <div class="search-product">
                            <form action="#">
                                <input class="form-control" placeholder="Search here..." type="text">
                                <button type="submit"> <i class="fa fa-search"></i> </button>
                            </form>
                        </div>
                        <div class="filter-sidebar-left">
                            <div class="title-left">
                                <h3>Recommendations</h3>
                            </div>
                            <div class="list-group list-group-collapse list-group-sm list-group-tree" id="list-group-men" data-children=".sub-men">
                            	<a href="javascript:makeGetRequest('age')" class="list-group-item list-group-item-action"> Age Wise</a>
                            	<a href="javascript:makeGetRequest('gender')" class="list-group-item list-group-item-action"> Gender Wise</a>
                            
                            </div>
                        </div>                       
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Shop Page -->
    
    
    
    <jsp:include page="Footer.jsp"></jsp:include>




<!-- ------------------------------------------------------------------------------------------------------- -->

    
    
    
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