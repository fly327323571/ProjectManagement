<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com----Add Cart Success</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
    <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
    <link href="static/css/customer/addCartSuccess.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <script src="static/js/common/baseAjax.js"></script>
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
	<div class="container">
		<div class="row clearfix">
			<div class="background">
				<div class="background-header">
					<img src="static/images/tick.png" class="tick_img"/>&nbsp;
					<span class="title_color title_size">You have added the product to cart successfully</span>
				</div>
				<br/>
				<div class="background-left">
					<img src="${cart.commodity.commodityImg }" alt="" height=100 width=100 />
				</div>
				<div class="background-right">
					<ul>
				      <li><b>Name:</b><span class="glyphicon">${cart.commodity.commodityName }</span></li>
				      <li><b>Amount:</b><span class="glyphicon">${cart.commodityNum }</span></li>
				      <li><b>price:</b><span class="glyphicon glyphicon-usd">${cart.price }</span></li>
				    </ul>
					<a class="btn btn_color btn_style" type="button" href="cart/myCart.do">check out</a>
					<a class="btn btn_color btn_style" type="button" href=".">back to shopping</a>
				</div>
			</div>
		</div>
	</div>
  <%@include file="../common/tail.html" %>
  
  </body>
</html>
