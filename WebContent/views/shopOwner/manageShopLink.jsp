<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<base href="<%=basePath%>">
   <title>ParknShop--manage shop link</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ¡®#!watch¡¯ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/manage.css" rel="stylesheet">
	<link href="static/css/shopOwner/manageShopLink.css" rel="stylesheet" type="text/css">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
	
</head>
  <body>
    <%@include file="../common/toolbar.jsp" %>
    <header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">select the shop link</span>
	</header>
	<section class="content">
		<div class="shop-profile">
			<ul>
				<li><img alt="shop logo" src="${store.logo }"/></li>
				<li>Store Name:<span>${store.storeName }</span></li>
				<li>Category:<span>${store.category }</span></li>
				<li>Credit: ${store.creditValue }
					<!-- <span class="glyphicon glyphicon-star star"></span>
					<span class="glyphicon glyphicon-star star"></span>
					<span class="glyphicon glyphicon-star star"></span>
					<span class="glyphicon glyphicon-star star"></span>
					<span class="glyphicon glyphicon-star star"></span> -->
				</li>
				<li>Register Time:<span>${store.registerTimeString }</span></li>
			</ul>
		</div>
		<div  class="other-shop">
		   
			 <table class="table" id="shopLink"></table>
		<nav>
			<div id="pagination"></div>
		</nav>
			<button type="button" class="btn link-btn">submit</button>
			
		</div>
	</section>
		 <%@include file="../common/tail.html" %>
	
    <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/shop/manageShopLinkConfig.js"></script>
 <script src="static/js/shop/manageShopLink.js"></script>
 <script>
 	var storeId= ${storeId};
 </script>
  </body>
</html>
