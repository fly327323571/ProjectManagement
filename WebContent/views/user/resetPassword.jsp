<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--reset password</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/customer/resetPassword.css" rel="stylesheet" type="text/css">
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
   <%@include file="../common/toolbar.jsp" %>

<header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<span class="identity">reset password</span>
</header>

<div class="container">
	
	
	<form class="form-horizontal" method="post">
		
		<div class="form-group">
			  <label class="col-sm-4 control-label">New Password</label>					
			<div class="col-sm-7">
				<input class="form-control" name="password" id="password" type="password" required="required" autofocus="autofocus" validateType="password" placeholder="Can't be pure Numbers, letters, special symbols,length 6-16"/>
				<div id="passwordCheck" class="check"></div>
			</div>
		</div>
		
		<div class="form-group">
			  <label class="col-sm-4 control-label">Confirm</label>					
			<div class="col-sm-7">
				<input class="form-control" id="confirm" type="password" required="required" validateType="userValidate"/>
				<div id="confirmCheck" class="check"></div>
			</div>
		</div>
		
		
		<div class="form-group">
			
				 <input class="btn btn-default"  type="button" id="reset" value="submit"/>
		</div>
		
	</form>

</div>
   <%@include file="../common/tail.html" %>
  </body>
    <script src="static/js/common/baseAjax.js"></script>
   <script src="static/js/common/validator.js"></script>
   <script src="static/js/user/resetPassword.js"></script>
</html>
