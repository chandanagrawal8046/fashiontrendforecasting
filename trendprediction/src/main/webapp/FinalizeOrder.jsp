<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        %>
<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Navigate.jsp"></jsp:include>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Fashion Trends Administration</h2></div>
    </div></div></div>
    <!-- End All Title Box -->


		<%
          String orderno="";
          try
          {
            orderno=String.valueOf(session.getAttribute("orderno"));
          }
          catch(Exception ex){}
        %>

	<!-- Start About Page  -->
    <div class="about-box-main">
        <div class="container">
            <div class="row">
				
                <div class="col-lg-8">
                    <h2 class="noo-sh-title-top">Place your order</h2>
                    <p>
                    Please finalize your order 
                <span style="color: orangered">Your order no is <%=orderno%></span><br/>
                Please remember your order for future reference.
                <br/><hr/><br/>
                
                <a href="finalizeorder" class="btn hvr-hover" style="padding:7px">Finalize order</a>
                    
                    </p>
                    
                </div>
            </div>
 		</div>
    </div>
    <!-- End About Page -->

	<jsp:include page="Footer.jsp"></jsp:include>
    
    <!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>