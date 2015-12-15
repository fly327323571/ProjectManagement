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
  
  <title>ParknShop--Login</title>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	<!-- 注意：这里如果用相对的路径名,请确保最后的路径访问的是 /ParknShop/static/... 因为静态资源的映射url为/ParknShop/static/... -->
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
   
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
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
  <link href="static/css/user/login/login.css" rel="stylesheet">
  
  
</head>

<body>

  <header>
		<a href="" class="logo">PARKnSHOP</a>
		<span class="identity">login</span>
 </header>
   <div  style="float:left;width:50%">
	 <!--  <img  src="static/images/ylogo.png" style="margin-left:5%"> -->
	  <img  src="static/images/ypic.jpg" style="margin-top:10%;margin-left:20%">
  </div>
  <form class="form-horizontal">
  		<div id="show-info">
  		
  		</div>
		<div class="form-group">
			 <label class="col-sm-2 control-label">Username</label>
			<div class="col-sm-10">
				<input class="form-control" type="text" placeholder="username" autofocus="autofocus" required="required" id="userName"/>
			</div>
		</div>
		<div class="form-group">
			  <label class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input class="form-control" type="password" required="required" id="password"/>
			</div>
		</div>
		<a href="user/findPassword/index.do">forgot password?</a>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" >
				 <div class="btn btn-default my-btn" id="login">Login</div>
			</div>
		</div>
		<a href="<c:url value="/user/register/customerReg"/>">registration</a>
		<a href="admin/index.do" style="margin-left: 11px;">I'm admin</a>
	</form>
	<span class="login-info" style=""></span>
	<%@include file="WEB-INF/views/common/tail.html" %>
 </body>
 <script>
 	var redirectUrl = "${historyUrl }";
 </script>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/user/login/login.js"></script>
 <script src="static/js/user/login/loginConfig.js"></script>
</html>
