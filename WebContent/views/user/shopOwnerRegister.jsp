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

<title>ParknShop--shopOwnerRegister</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
<link href="static/css/user/headTail.css" rel="stylesheet">
<link href="static/css/user/style.css" rel="stylesheet">
<link href="static/css/user/register/shopOwnerRegister.css"
	rel="stylesheet" type="text/css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="img/favicon.png">



</head>

<body>
	 <%@include file="../common/toolbar.jsp" %>
	<header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<span class="identity">to be shop owner</span>
	</header>
	<div class="container">
		<form id="form" class="form-horizontal" action="<c:url value="/user/register/saveShopOwner"/>" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-1 control-label" for="inputEmail3">Portrait</label>
				<div class="col-sm-7" style="position: relative; width: 105px;">
					<div class="btn btn-default" style="position: relative; width: 120px;overflow: hidden;">
 						<div>Select File</div>
						<input type="file" id="file" name="file"  accept="image/*"/>
					</div>
					<span id="uploading" style="display:none;"></span>
			  		<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -64px;display:none;"></span>
					<input id="portrait" name="portrait" type="text" style="display:none;"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" for="inputEmail3">Id Card</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" id="idCard" name="idCard" required="required" validateType="idCard" placeholder="digits, length 15-18"/>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" for="inputEmail3">Real Name</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" id="realName" name="realName" required="required" placeHolder="please provide your real name"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-7">
					<div class="btn btn-default" id="register">Register</div>
				</div>
			</div>
		</form>
		<div id="show-info" ><img  alt="Logo" width='100px' height='100px' />
		</div>
	</div>
<%@include file="../common/tail.html" %>
</body>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/user/register/shopOwnerRegister.js"></script>
 <script src="static/js/common/validator.js"></script>
</html>
