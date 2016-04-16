<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com----Pay Result</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
     
      <link href="static/css/payResult.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <script src="static/js/common/baseAjax.js"></script>
  </head>

  <body>
  	<c:choose>
       	<c:when test="${isInner == 0}">
       		<%@include file="../common/toolbar.jsp" %>    	
		     <header>
				<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
				<div class="search-box">
		    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
		    		<button type="button" id="search">search</button>
		    	</div>
			</header>
		</c:when>
	</c:choose>
	<div class="container">
		<div class="row clearfix">
			<div class="background">
				<img src="static/images/tick.png" class="tick_img"/>&nbsp;<span class="title_color title_size">You have purchased the product successfully</span>
				<br/><br/><br/>
				<ul>
			      <li>The payment amount:<span>￥${sumPrice }</span></li>
			      <%-- <li>The postage:<span class="glyphicon glyphicon-usd">${totalPostage }</span></li> --%>
			      <%-- <li>The address of delivery:${address.province } ${address.city } ${address.address }</li> --%>
			      <%-- <li><a href="business/viewOrderDetails/${orderId }">view the order</a></li> --%>
			    </ul>
				<a class="btn btn_color" type="button" href=".">back to shopping</a>
			</div>
		</div>
	</div>
  <%@include file="../common/tail.html" %>
  
  </body>
</html>
