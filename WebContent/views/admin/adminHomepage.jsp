<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>"/>
    
    <title>ParknShop--Administration Homepage</title>
    
    <meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet"/>
	<link href="static/css/user/headTail.css" rel="stylesheet"/>
	<link href="static/css/user/manage.css" rel="stylesheet"/>
	<link href="static/css/user/style.css" rel="stylesheet"/>
	
	
</head>
  
  <body>
     <%@include file="../common/toolbar.jsp" %>
	<header>
		<img src="static/images/logo.jpg" style="padding-left:20px;padding-top:10px;height:60px;"></img>
		
		<span class="identity">administrator page</span>
		
	</header>
	<section class="content">
		<div class="nav">
			<ul>
			    <li><a href="admin/complaint" class="option">Complaint Manage</a></li>
			    <li><a href="admin/odium" class="option">Odium Manage</a></li>
				<li><a href="admin/shopManage" class="option">Shop Manage</a></li>
				<!-- <li><a href="admin/shopOwnerManage" class="option">Shop Owner Manage</a></li> -->
				<li><a href="admin/customerManage" class="option">Customer Manage</a></li>
				<li><a href="/ParknShop/admin/registerShopInfo" class="option">AuditShop Manage</a></li>
				<li><a href="/ParknShop/afterSaleService/view" class="option">Saled Service</a></li>
				<li><a href="admin/businessTransaction" class="option">Business Transaction</a></li>
				<li><a href="admin/getIncome" class="option">Get Income</a></li>
				<li><a href="admin/searchIncome" class="option">Search Income</a></li>
				<li><a href="admin/summary" class="option">Summary</a></li>
				<li><a href="admin/adManage" class="option">Ad Manage</a></li>
				<li><a href="admin/adSummary" class="option">Ad Summary</a></li>
				<!--  <li><a href="admin/top10" class="option">Top 10</a></li>-->
				<li><a href="admin/database" class="option">DataBase</a></li>
				
			</ul>
		</div>
		<div class="container">
		
			<iframe src="/ParknShop/views/admin/shopRegManage.jsp" class="container" ></iframe>
			
			
	</section>
	
<%@include file="../common/tail.html" %>	
	 <script src="static/js/common/baseAjax.js"></script>
     <script type="text/javascript">
			$(".content > .nav li  a").bind("click",function(e){
     			e.preventDefault();
     		});
     		$(".content > .nav  li .option").bind("click",function(){
     			var target=$(this).attr("href");
     			$(".container").children("iframe").attr("src",target);
     			$(".content > .nav  li .option").css("background-color","#F5F5F5");
     			$(".content > .nav  li .option").css("color","black");
     			$(this).css("background-color","#FF5400");
     			$(this).css("color","white");
     		});
     		
     </script>
  </body>
</html>