<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<table class="table table-bordered" style="background-color: white;">
				<spring:forEach items="${catproducts}" var="c">
				<tr>
					<td rowspan="2"><img src="${c.imgurl}" class="img-responsive" style="max-height: 100px"/></td>
					<td colspan="2"><b>${c.title}</b></td>
					<td>Brand : ${c.brand}</td>
				</tr>
				<tr>
					<td>Rs. : ${c.price} /-</td>
					<td>Stock : ${c.stock}</td>								
					<td>
						<a href="delProd?id=${c.id}" onclick="return confirm('Are you sure you want to delete product.?')">Delete</a>	
					</td>		
				</tr>
				</spring:forEach>
		</table>

</body>
</html>