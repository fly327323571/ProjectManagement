<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--Track logistics</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
		<link href="static/css/user/headTail.css" rel="stylesheet">
	
	<link href="static/css/customer/logisticsQuery.css" rel="stylesheet">
</head>

<body>
 <%@include file="../common/toolbar.jsp" %>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-left text-default title_color">
				Track the Logistics
			</h3>
		<br>
		<div>
			
		</div>
		<hr>
		<div class="background">
			<dl>
			    <c:forEach var="logi" items="${logistics}" varStatus="name">
					<dt class="sec_color">
							<span>${logi.formatDate}</span>
					
				</dt>
				<dd class="sec_color" style="margin-left:50px;">
					<p>
							<span>${logi.content}</span>&nbsp;&nbsp;
							<span>company:${logi.expressType}</span>
					</p>
				</dd>
                </c:forEach>
			</dl> 
			
			</div>
<hr/>
 <center><button class="btn btn-default" type="submit" onclick="window.close();">Close</button></center>
 </div>
 </div>
 </div>
	<%@include file="../common/tail.html" %>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/customer/logisticsQuery.js"></script>
 <script src="static/js/customer/logisticsQueryConfig.js"></script>
</body>
</html>

