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
                
</body>
</html>