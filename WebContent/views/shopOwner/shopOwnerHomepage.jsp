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
    <script src="static/js/common/baseAjax.js"></script>
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/manage.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css">
  
</head>
  
  <body>
      <%@include file="../common/toolbar.jsp" %>
	<header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">shop owner page</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
		
	</header>
	
	<section class="content">
		
		<div class="nav">
			<ul>
				<li><a id="nav_shoplist" href="javascript:void(0)" class="option">shop list</a></li>
				<li><a id="nav_new_shop" href="javascript:void(0)" class="option">register new shop</a></li>
			</ul>
		</div>
		<div class="container">
			<iframe id="iframe" src="shop/index" class="container"></iframe>
		</div>
	</section>
	
	
	
	<%@include file="../common/tail.html" %>
 <script src="static/js/shop/shopOwnerHomepage.js"></script>
 <script src="static/js/shop/shopOwnerHomepageConfig.js"></script>
  </body>
</html>
