<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--Income</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/admin/income.css" rel="stylesheet"/>
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
 
	
	
</head>
	
 
  
  <body>
  
  <div class="container">
  	<!-- 效果未测试 -->
  	<nav class="navbar navbar-default" role="navigation">
	  		<div class="navbar-header">
				 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span>
					 <span class="icon-bar"></span>
					 <span class="icon-bar"></span>
					 <span class="icon-bar"></span>
				 </button>
				 <a class="navbar-brand">Income:</a>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul id="viewByTime" class="nav navbar-nav">
					<li class="nav_option nav_active">
						<a href="javascript:void(0);" id="today">Today</a>
					</li>
					<li class="nav_option">
						<a href="javascript:void(0);" id="week">This week</a>
					</li>
					<li class="nav_option">
						<a href="javascript:void(0);" id="month">This Month</a>
					</li>
					<li class="nav_option">
						<a href="javascript:void(0);" id="year">This Year</a>
					</li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input id="order_id" class="form-control" placeholder="Order Id" type="text">
					</div>
					<div id="search" class="btn btn_color" type="button">Search</div>
				</form>
			</div>
			<div class="nav_2">
				<div class="commission_info">
					<form class="navbar-form navbar-right navbar-style" role="search">
					<div class="form-group">
						<input id="commission" class="form-control" placeholder="comission" type="text">
					</div>
						<div id="modify" class="btn btn_color modify" type="button">modify commission</div>
					</form>
				</div>
				<span class="income_now">sum income : <b>$${sumIncome}</b></span>
				<span class="commission_now"> commission is <b>${commission}%</b> now</span>
			</div>
		<!--  -->
	</nav>
	
    <table id="incomeList" class="table table-hover"></table>
	<nav class="page"><!-- 分页 -->
		<div id="pagination"></div>
	</nav>
	</div>
  </body>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/common/bootstrap-modal.js"></script>
 <script src="static/js/admin/income.js"></script>
 <script src="static/js/admin/incomeConfig.js"></script>
</html>
