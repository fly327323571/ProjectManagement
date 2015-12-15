<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--Administration Homepage</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/manage.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	
</head>
  
  <body>
     <%@include file="common/toolbar.jsp" %>
	<header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">administrator page</span>
		
	</header>
	<section class="content">
		<div class="nav">
			<ul>
				<li><a href="admin/shopRegMessage" class="option">Shop Manage</a></li>
				<li><a href="admin/shopOwnerManage" class="option">Shop Owner Manage</a></li>
				<li><a href="admin/customerManage" class="option">Customer Manage</a></li>
				<li><a href="admin/orderHistory" class="option">Order History</a></li>
				<li><a href="admin/adver/product" class="option">Top Products</a></li>
				<li><a href="admin/adver/shop" class="option">Top Shop</a></li>
				<li><a href="admin/income" class="option">View Income</a></li>
				<li><a href="admin/backupDbPage" class="option">Backup Database</a></li>
			</ul>
		</div>
		<div class="container">
		
			<iframe src="admin/shopRegMessage" class="container" ></iframe>
			
			
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