<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath %>">
    
    <title>PARKnSHOP.com----My Favorite</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/myFavorite.css" rel="stylesheet">
      <link href="static/css/common/jpagination.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
  </head>
  <body>
  <%@include file="../common/toolbar.jsp" %>
     <header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
	</header>
  <div class="container-fluid">
  	<div class="row-fluid" style="padding-top: 35px;">
 	<div class="tabbable" id="tabs-175400">
		<ul class="nav nav-tabs">
			<li class="active product">
				<a href="#panel-334142" data-toggle="tab">Favorite Products</a>
			</li>
			<li class="store">
				<a href="#panel-995732" data-toggle="tab">Favorite Shops</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active f_product" id="panel-334142">
				<div class = "favorite clearfix">
			      <c:forEach items="${collectCommodityList}" var="collectCommodity">
				  <a class="sec" href="product/${collectCommodity.commodity.shop.shopNo}/productDetail/${collectCommodity.commodity.commodityNo}.do">				  
					  <img src="${collectCommodity.commodity.commodityImg}">
					  <h3 class = "tittle">Product Name: ${collectCommodity.commodity.commodityName }</h3>
					  <p> Shop Name: ${collectCommodity.commodity.shop.shopName}</p>
				  </a>
			      </c:forEach>
 				</div>
			</div>
			<div class="tab-pane f_store" id="panel-995732">
				<div class = "favorite clearfix">
			      <c:forEach items="${collectShopList}" var="collectShop">
				  <a class="sec" href="business/market/${collectShop.shop.shopNo}/shopHomePage.do">
				  
					  <img src="${collectShop.shop.shopIcon}">
					  <h3 class = "tittle">Shop Name: ${collectShop.shop.shopName }</h3>
					  <%-- <p> ${item.shopName }</p> --%>
				  </a>
			      </c:forEach>
 				</div>
			</div>
		</div>
	</div>
	<div class="page">
 		<div id="pagination"></div>
 	</div>
	</div>
</div>
<script type="text/template" id="favProductTmpl">
	<a class="sec" href="{detailUrl}">
		<img src="{productImage}">
		<h3 class = "tittle">{productName}</h3>
		<p>{shopName}</p>
	</a>
</script>
  <%@include file="../common/tail.html" %>
  </body>
  <script src="static/js/common/baseAjax.js"></script>
  <script src="static/js/common/jquery.paginate.js"></script>
  <script src="static/js/customer/myFavorite.js"></script>
  <script src="static/js/customer/myFavoriteConfig.js"></script>
</html>