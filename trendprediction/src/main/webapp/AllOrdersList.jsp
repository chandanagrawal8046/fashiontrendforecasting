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
    <div class="col-lg-12"><h2>Orders List</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <div class="contact-form-right">
                        
                        <table class="table table-bordered" style="background-color: white;">
							<tr>
								<th>OrderNo</th>
								<th>Customer Name</th>
								<th>Order Date</th>
								<th>Bill Amount</th>
								<th>Pay Mode</th>
								<th>Pay Status</th>
								<th>Order Status</th>
								<th>Action</th>
							</tr>						
							<spring:forEach items="${orders}" var="o">
							<tr>
								<td>${o.orderno}</td>
								<td>${o.name}</td>
								<td>${o.orderdt}</td>
								<td>${o.bill}</td>
								<td>${o.paymode}</td>
								<td>${o.paystatus}</td>
								<td>${o.orderstatus}</td>
								<td>
									<a href="orderdetails?orderno=${o.orderno}">View Details</a>
									
									<spring:if test="${o.orderstatus=='confirmed'}">&nbsp;|&nbsp;                                    	
                                    	<a href="processorder?orderno=${o.orderno}&act=dispatch" onclick="javascript:return confirm('Are you sure you want to dispatch order.??'">Dispatch Order</a>
                                    </spring:if>
									<spring:if test="${o.orderstatus=='dispatched'}"> &nbsp;|&nbsp;                                   	
                                    	<a href="processorder?orderno=${o.orderno}&act=complete" onclick="javascript:return confirm('Are you sure you want to complete order.??')"> Complete Order</a>
                                    </spring:if>
								</td>
							</tr>
							</spring:forEach>	
						</table>
                        
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