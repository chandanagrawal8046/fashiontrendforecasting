<%@page import="com.fashion.datagenerators.CompanyDetails"%>
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
    <div class="col-lg-12"><h2>Fashion Trends Prediction</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-sm-12">
                    <div class="contact-form-right">
                        <h2>Company Profile</h2>                        
                        <form id="contactForm" method="post" action="editcompany" autocomplete="off">
                        
                        <%
                        String userid=String.valueOf(session.getAttribute("user"));
                        CompanyDetails dtl=new CompanyDetails(userid);
                        %>
                        
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" value="<%=dtl.getName() %>" id="name" name="name" placeholder="Company Name" required data-error="Please enter your name">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" value="<%=dtl.getMobile()%>" id="mobile" name="mobile" placeholder="Your Mobile" required data-error="Please enter your mobile no">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="email" placeholder="Your Email" id="email" value="<%=dtl.getEmail()%>" class="form-control" name="email" required data-error="Please enter your email">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="url" class="form-control" value="<%=dtl.getCompurl()%>" placeholder="Company Url : http://www.mycompany.com" id="compurl" name="compurl" required data-error="Please Enter Company URL"/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">                                       
                                        <textarea class="form-control" id="address" name="address" placeholder="Address" required data-error="Please enter address"><%=dtl.getAddress() %></textarea>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="headoffice" value="<%=dtl.getHeadoffice() %>" name="headoffice" placeholder="Your headoffice city" required data-error="Please enter your headoffice city">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-10">
                                    <div class="form-group">                                       
                                        <select name="secquestion" id="secquestion" class="form-control" required data-error="Please Select Security Question">
										<option><%=dtl.getSecquestion() %></option>
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
                                        <input type="text" class="form-control" value="<%=dtl.getAnswer() %>" id="answer" name="answer" placeholder="Answer" required data-error="Please enter your answer">
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