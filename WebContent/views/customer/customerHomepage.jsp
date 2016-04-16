<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--shop Owner Homepage</title>
    
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
    <%@include file="../common/toolbar.jsp" %>
	<header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<span class="identity">customer page</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
		
	</header>
	<section class="content">
		<div class="nav">
			<ul>
				<li><a href="#">Personal Profile</a></li>
				<li>
					<div class="profile">
						<div class="img">
							<div>
								<a href="javascript:void(0)" id="view_profile">View Profile</a>
								<a href="javascript:void(0)" id="modify_profile">Modify Profile</a>
							</div>
						</div>
						<ul> 
							<li>User name:<span>${user.userName }</span></li>
						</ul>
						
					</div>
				</li>
				<li><a id="order_history" href="/ParknShop/customer/customerHomePage" class="option">Order details</a></li>
				<li><a id="myCart"  href="javascript:void(0)" target="_top" class="option">My Cart</a></li>
				<li><a id="myFavorite"  href="/ParknShop/customer/myFavorite" target="_top" class="option">My Favorite</a></li>
			</ul>
		</div>
		<div class="container">
		
			<iframe src="/ParknShop/customer/orderDetails" class="container" ></iframe>
		</div>	
	</section>
	<%@include file="../common/tail.html" %>
  </body>
<script src="static/js/common/baseAjax.js"></script>  
<!-- <script src="static/js/customer/customerHomepage.js"></script>
<script src="static/js/customer/customerHomepageConfig.js"></script> -->
<script>
</script>
</html>
