<%@page import="com.fashion.datagenerators.GenderwiseReportGeneration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="js/amcharts.js" type="text/javascript"></script>
<script src="js/serial.js" type="text/javascript"></script>
<
<script>
            


            AmCharts.ready(function () 
            		
            {
            	var chart;

                var chartData = [
                    {
                        "gender": document.frm.title1.value,
                        "hits": document.frm.hits1.value,
                        "expenses": document.frm.hits1.value
                    },
                    {
                        "gender": document.frm.title2.value,
                        "hits": document.frm.hits2.value,
                        "expenses": document.frm.hits2.value
                    }
                ];
            	
            	
            	
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "gender";
                chart.startDuration = 1;
                chart.rotate = true;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridPosition = "start";
                categoryAxis.axisColor = "#DADADA";
                categoryAxis.dashLength = 3;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.dashLength = 3;
                valueAxis.axisAlpha = 0.2;
                valueAxis.position = "top";
                valueAxis.title = "Orders Placed";
                valueAxis.minorGridEnabled = true;
                valueAxis.minorGridAlpha = 0.08;
                valueAxis.gridAlpha = 0.15;
                chart.addValueAxis(valueAxis);

                // GRAPHS
                // column graph
                var graph1 = new AmCharts.AmGraph();
                graph1.type = "column";
                graph1.title = "No of orders";
                graph1.valueField = "hits";
                graph1.lineAlpha = 0;
                graph1.fillColors = "#ADD981";
                graph1.fillAlphas = 0.8;
                graph1.balloonText = "<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b></span>";
                chart.addGraph(graph1);

                // line graph
                var graph2 = new AmCharts.AmGraph();
                graph2.type = "line";
                graph2.lineColor = "#27c5ff";
                graph2.bulletColor = "#FFFFFF";
                graph2.bulletBorderColor = "#27c5ff";
                graph2.bulletBorderThickness = 2;
                graph2.bulletBorderAlpha = 1;
                graph2.title = "No of orders";
                graph2.valueField = "expenses";
                graph2.lineThickness = 2;
                graph2.bullet = "round";
                graph2.fillAlphas = 0;
                graph2.balloonText = "<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b></span>";
                chart.addGraph(graph2);

                // LEGEND
                var legend = new AmCharts.AmLegend();
                legend.useGraphSettings = true;
                chart.addLegend(legend);

                chart.creditsPosition = "top-right";

                // WRITE
                chart.write("chartdiv");
            });
        </script>
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
<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Gender wise Reports</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-sm-12">
                    <div class="contact-form-right">
                        
                        <form name="frm">
                            <table class="table table-bordered" style="background-color: white;">
								<tr>
									<th>Gender</th>
									<th>Orders</th>
								</tr>
								<spring:forEach items="${genderreport}" var="a">
								<tr>
									<td>${a.gender}</td>
									<td>${a.hits}</td>
								</tr>
								</spring:forEach>
							</table>
							
							<%
								int hits1=0,hits2=0;
								String gender1="",gender2="";
								
								try
								{
									GenderwiseReportGeneration obj=new GenderwiseReportGeneration();
									obj.getGenderwiseReport();
									
									hits1=obj.getHits1();
									hits2=obj.getHits2();
									
									gender1=obj.getGender1();
									gender2=obj.getGender2();
									
								}
								catch(Exception ex){}
							%>
                            
                            <input type="hidden" id="title1" name="title1" value="<%=gender1 %>"/>
				            <input type="hidden" id="title2" name="title2" value="<%=gender2 %>"/> 
				            
                            <input type="hidden" id="hits1" name="hits1" value="<%=hits1 %>"/>
				            <input type="hidden" id="hits2" name="hits2" value="<%=hits2 %>"/> 
				            
                        </form>             
                        
                    </div>
                </div>
				<div class="col-lg-6 col-sm-12">
                    <div class="contact-form-right">
                                          
                        
                        <div id="chartdiv" style="width: 400px; height: 300px;"></div>
                        
                        
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