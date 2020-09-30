<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<SCRIPT lang="javascript" src="js/validations.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Navigate.jsp"></jsp:include>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Fashion Trends Prediction</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-sm-12">
                    <div class="contact-form-right">
                        <h2>Change Password</h2>                        
                        <form id="contactForm" method="post" action="changepass" autocomplete="off" onsubmit="return validateForm(this,'chpass');">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="pass" name="pass" placeholder="Current Password" required data-error="Please Enter Current Password">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="newpass" name="newpass" placeholder="New Password" required data-error="Please Enter New Password">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="retypepass" name="retypepass" placeholder="Retype Password" required data-error="Please Retype Password">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">                                    
                                    <div class="submit-button text-center">
                                        <button class="btn hvr-hover" id="submit" type="submit">Submit</button>
                                        <div id="msgSubmit" class="h3 text-center hidden"></div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                        </form>
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