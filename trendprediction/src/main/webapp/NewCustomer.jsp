<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
                        <h2>New Customer Registration</h2>                        
                        <form id="contactForm" method="post" action="regcustomer" autocomplete="off">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Your Name" required data-error="Please enter your name">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Your Mobile" required data-error="Please enter your mobile no">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="email" placeholder="Your Email" id="email" class="form-control" name="email" required data-error="Please enter your email">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="date" class="form-control" id="dob" name="dob" required data-error="Please Enter Date of Birth">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">                                       
                                        <select name="gender" id="gender" class="form-control" required data-error="Please Select Gender">
										<option>Select Gender</option>
	                                    <option>Male</option>
	                                    <option>Female</option>  
										</select>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">                                       
                                        <select name="secquestion" id="secquestion" class="form-control" required data-error="Please Select Security Question">
										<option>Select Security Question</option>
	                                    <option>What is your neighbor's name?</option>
	                                    <option>Which is your favorite sport?</option>
	                                    <option>Which is your favorite dress type?</option>
	                                    <option>Who is your idol?</option>
	                                    <option>Which is your favorite fruit?</option>
	                                    <option>Who is your best friend?</option>
	                                    <option>What is your college name?</option>
	                                    <option>What is your brother name?</option>
										</select>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="answer" name="answer" placeholder="Answer" required data-error="Please enter your answer">
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