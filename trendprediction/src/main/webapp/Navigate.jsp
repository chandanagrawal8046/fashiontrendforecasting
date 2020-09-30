<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Site Metas -->
    <title>Fashion Trends Prediction</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

 <!-- Start Main Top -->
    <header class="main-header">
        <!-- Start Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
            <div class="container">  
            
            <%
		    String userid="";
		    String utype="";
		    String username="";
		    try
		    {            
		    userid=String.valueOf(session.getAttribute("user"));
		    utype=String.valueOf(session.getAttribute("utype"));
		    username=String.valueOf(session.getAttribute("username"));
		    System.out.println("Navigate Name : "+username);
		    if(utype.equals("admin"))
		    {
		    %>
            <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="adminhome">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="customerslist">Customers List</a></li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Categories</a>
                            <ul class="dropdown-menu">
								<li><a href="newcategory">New Category</a></li>
								<li><a href="categories">Categories List</a></li>                                
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Products</a>
                            <ul class="dropdown-menu">
								<li><a href="newProduct">New Product</a></li>
								<li><a href="viewcatprods">Products List</a></li>                                
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Companies</a>
                            <ul class="dropdown-menu">
								<li><a href="newcomp">New Company</a></li>
								<li><a href="complist">Companies List</a></li>                                
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Orders</a>
                            <ul class="dropdown-menu">
								<li><a href="orderslist">Orders List</a></li>
								<li><a href="complist">Companies List</a></li>                                
                            </ul>
                        </li>
                        
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Reports</a>
                            <ul class="dropdown-menu">
								<li><a href="agewise">Agegroup wise Report</a></li>
								<li><a href="genderwise">Gender wise Report</a></li>                                
                            </ul>
                        </li>
                        
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Selling Analysis</a>
                            <ul class="dropdown-menu">
								<li><a href="topproducts">Top Sold Products</a></li>
								<li><a href="topcategories">Top Categories Report</a></li>
								<li><a href="topbrands">Top Brands Report</a></li>                                
                            </ul>
                        </li>
                        
                        
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
                  
            <%}        
		    else if(utype.equals("customer"))
		    {%>
		    <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="userhome">Home</a></li>                        
                       
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Orders</a>
                            <ul class="dropdown-menu">
								<li><a href="orderslist">Orders List</a></li>
								<li><a href="complist">Companies List</a></li>                                
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">My Account</a>
                            <ul class="dropdown-menu">
								<li><a href="custprofile">My Profile</a></li>
								<li><a href="chpass">Change Password</a></li>                                
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->  
		    
		    <%}
		    else if(utype.equals("company"))
		    {%>
		    <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="companyhome">Home</a></li>
                        
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">My Account</a>
                            <ul class="dropdown-menu">
								<li><a href="compprofile">Company Profile</a></li>
								<li><a href="chpass">Change Password</a></li>                               
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="myproducts">Products List</a></li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Selling Analysis</a>
                            <ul class="dropdown-menu">
								<li><a href="topproducts">Top Sold Products</a></li>
								<li><a href="topcategories">Top Categories Report</a></li>
								<li><a href="topbrands">Top Brands Report</a></li>                                
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->  
		    
		    <%}
		    else
		    {%>
		    	<!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="trend">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="start">Start Shopping</a></li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Selling Analysis</a>
                            <ul class="dropdown-menu">
								<li><a href="topproducts">Top Sold Products</a></li>
								<li><a href="topcategories">Top Categories Report</a></li>
								<li><a href="topbrands">Top Brands Report</a></li>                                
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="register">Customers</a></li>                        
                        <li class="nav-item"><a class="nav-link" href="forgot">Password Recovery</a></li>
                        
                        
                        
                    </ul>
                </div>
                <!-- /.navbar-collapse -->  
		    <%}
		    
		    }
		    catch(Exception ex)
		    {%>
		    	<!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="trend">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="start">Start Shopping</a></li>
                        <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Selling Analysis</a>
                            <ul class="dropdown-menu">
								<li><a href="topproducts">Top Sold Products</a></li>
								<li><a href="topcategories">Top Categories Report</a></li>
								<li><a href="topbrands">Top Brands Report</a></li>                                
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="register">Customers</a></li>                        
                        <li class="nav-item"><a class="nav-link" href="forgot">Password Recovery</a></li>     
                        
                                           
                    </ul>
                </div>
                <!-- /.navbar-collapse -->  
		    
		    <%}
    		%>
                          
                              
            </div>         
        </nav>
        <!-- End Navigation -->
    </header>
    <!-- End Main Top -->

	<!-- Start Top Search -->
    <div class="top-search">
        <div class="container">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
            </div>
        </div>
    </div>
    <!-- End Top Search -->

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