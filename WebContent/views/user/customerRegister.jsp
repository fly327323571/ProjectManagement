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
    
    <title>ParknShop--customerRegister</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/user/register/register.css" rel="stylesheet" type="text/css">
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
  <link rel="stylesheet" href="static/css/user/register/register.css"/>
	
	
</head>
<body>
 <%@include file="../common/toolbar.jsp" %>

<header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">register</span>
</header>

<div class="container">
	<!-- <div id="show-info">
	</div>
	 -->
	
	<form class="form-horizontal" action="user/register" method="get" >
		
		<div class="form-group">
			 <label class="col-sm-2 control-label">Username</label>
			<div class="col-sm-7" >
				<input class="form-control" name="userName" id="userName" type="text" required="required" validateType="userName" autofocus="autofocus" placeholder="length 4-16"/>
<!-- 				<div id="nameCheck" class="check tip"></div> -->
			</div>
		</div>
		<div class="form-group">
			  <label class="col-sm-2 control-label">Password</label>					
			<div class="col-sm-7">
				<input class="form-control" name="password" id="password" type="password" required="required" validateType="password" placeholder="mixed numbers,letters...length 6-16"/>
				<div id="passwordCheck" class="check"></div>
			</div>
		</div>
		
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;Confirm</label>					
			<div class="col-sm-7">
				<input class="form-control" id="confirm" type="password" required="required" validateType="userValidate" placeHolder="confirm your password"/>
				<div id="confirmCheck" class="check"></div>
			</div>
		</div>
				
		<div class="form-group">
			  <label class="col-sm-2 control-label"> Phone Number</label>					
			<div class="col-sm-7">
				<input class="form-control" name="tel" id="tel"  type="text" required="required" validateType="cellphone"/>
				<div id="phoneNumberCheck" class="check"></div>
			</div>
		</div>
		
		
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email</label>					
			<div class="col-sm-7">
				<input class="form-control" name="email" id="email" type="text" required="required" validateType="email"/>
				<div id="emailCheck" class="check"></div>
			</div>
		</div>
		
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RealName</label>					
			<div class="col-sm-7">
				<input class="form-control" name="nickName"  id="nickName" type="text" required="required" validateType="userName"/>
				<div id="nickNameCheck" class="check"></div>
			</div>
		</div>
		
			<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address</label>					
			<div class="col-sm-7">
				<input class="form-control" name="address" id="address" type="text" required="required" validateType="address"/>
				<div id="addressCheck" class="check"></div>
			</div>
		</div>
		
		
		
		<div class="form-group">
				 <input class="btn btn-default"  type="submit" id="register" value="Register"/>			
		</div>
		
	</form>

</div>



<%@include file="../common/tail.html" %>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/user/register/register.js"></script>
<script src="static/js/user/register/registerConfig.js"></script>
<script src="static/js/common/validator.js"></script>
<script>
	var error = "${error}";
	if(error){
		alert(error);
	}
</script>
</body>
</html>
