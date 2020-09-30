<%@page import="com.fashion.datagenerators.TopCategories"%>
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

<script>
            


            AmCharts.ready(function () 
            		
            {
            	var chart;

                var chartData = [
                    {
                        "year": document.frm.title1.value,
                        "income": document.frm.hits1.value,
                        "expenses": document.frm.hits1.value
                    },
                    {
                        "year": document.frm.title2.value,
                        "income": document.frm.hits2.value,
                        "expenses": document.frm.hits2.value
                    },
                    {
                        "year": document.frm.title3.value,
                        "income": document.frm.hits3.value,
                        "expenses": document.frm.hits3.value
                    },
                    {
                        "year": document.frm.title4.value,
                        "income": document.frm.hits4.value,
                        "expenses": document.frm.hits4.value
                    },
                    {
                        "year": document.frm.title5.value,
                        "income": document.frm.hits5.value,
                        "expenses": document.frm.hits5.value
                    },
                    {
                        "year": document.frm.title6.value,
                        "income": document.frm.hits6.value,
                        "expenses": document.frm.hits6.value
                    },
                    {
                        "year": document.frm.title7.value,
                        "income": document.frm.hits7.value,
                        "expenses": document.frm.hits7.value
                    },
                    {
                        "year": document.frm.title8.value,
                        "income": document.frm.hits8.value,
                        "expenses": document.frm.hits8.value
                    },
                    {
                        "year": document.frm.title9.value,
                        "income": document.frm.hits9.value,
                        "expenses": document.frm.hits9.value
                    },
                    {
                        "year": document.frm.title10.value,
                        "income": document.frm.hits10.value,
                        "expenses": document.frm.hits10.value
                    }
                ];
            	
            	
            	
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "year";
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
                graph1.valueField = "income";
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
        %>
<jsp:include page="Header.jsp"/>
	<jsp:include page="Navigate.jsp"/>

	<!-- Start All Title Box -->
    <div class="all-title-box"><div class="container"><div class="row">
    <div class="col-lg-12"><h2>Top Product Categories Report</h2></div>
    </div></div></div>
    <!-- End All Title Box -->
    
    
    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
        
        <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <div class="contact-form-right">                        
                        <div id="chartdiv" style="width: 90%; height: 600px;"></div>                        
                    </div>
                </div>
        </div>
        
            <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <div class="contact-form-right">
                        
                        <form name="frm">
                            <table class="table table-bordered" style="background-color: white;">
								<tr>
									<th>Category</th>
									<th>Title</th>
									<th>Brand</th>
									<th>Units Sold</th>
								</tr>
								<spring:forEach items="${catreport}" var="p">
								<tr>
									<td>${p.categories}</td>
									<td>${p.title}</td>
									<td>${p.brand}</td>
									
									<td>${p.hits}</td>
								</tr>
								</spring:forEach>
							</table>
							
							<%
								
								int hits[]=new int[10];
								String name[]=new String[10];
								try
								{
									TopCategories obj=new TopCategories();	
									obj.getReport();
									
									hits=obj.getHit();
									name=obj.getName();
									
								}
								catch(Exception ex){}
							%>
                            
                            <input type="hidden" id="title1" name="title1" value="<%=name[0] %>"/>
				            <input type="hidden" id="title2" name="title2" value="<%=name[1] %>"/> 
				            <input type="hidden" id="title3" name="title3" value="<%=name[2] %>"/>
				            <input type="hidden" id="title4" name="title4" value="<%=name[3] %>"/> 
				            <input type="hidden" id="title5" name="title5" value="<%=name[4] %>"/>
				            <input type="hidden" id="title6" name="title6" value="<%=name[5] %>"/>
				            <input type="hidden" id="title7" name="title7" value="<%=name[6] %>"/> 
				            <input type="hidden" id="title8" name="title8" value="<%=name[7] %>"/>
				            <input type="hidden" id="title9" name="title9" value="<%=name[8] %>"/> 
				            <input type="hidden" id="title10" name="title10" value="<%=name[9] %>"/>  
            
                            <input type="hidden" id="hits1" name="hits1" value="<%=hits[0] %>"/>
				            <input type="hidden" id="hits2" name="hits2" value="<%=hits[1] %>"/> 
				            <input type="hidden" id="hits3" name="hits3" value="<%=hits[2] %>"/>
				            <input type="hidden" id="hits4" name="hits4" value="<%=hits[3] %>"/> 
				            <input type="hidden" id="hits5" name="hits5" value="<%=hits[4] %>"/>
				            <input type="hidden" id="hits6" name="hits6" value="<%=hits[5] %>"/>
				            <input type="hidden" id="hits7" name="hits7" value="<%=hits[6] %>"/> 
				            <input type="hidden" id="hits8" name="hits8" value="<%=hits[7] %>"/>
				            <input type="hidden" id="hits9" name="hits9" value="<%=hits[8] %>"/> 
				            <input type="hidden" id="hits10" name="hits10" value="<%=hits[9] %>"/>
                            
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