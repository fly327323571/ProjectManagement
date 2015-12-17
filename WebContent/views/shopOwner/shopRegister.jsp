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
    
    <title>ParknShop--shop Register</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/shopOwner/shopRegister.css" rel="stylesheet">
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
</head>
<body>
	<div class="clearfix">
	<form class="form-horizontal" id="shopRegForm">
		
		<div class="form-group">
			  <label class="col-sm-2 control-label">Shop Name</label>					
			<div class="col-sm-6">
				<input id="shopName" name="shopName" type="text" class="form-control" autofocus="autofocus" required="required" validateType="shopName"/>
			</div>
		</div>
		<div class="form-group">
			  <label class="col-sm-2 control-label">Category</label>					
			<div class="col-sm-6">
				<select id="shopCategories" name="shopCategories" class="form-control" required="required" validateType="userValidate">
					<option value="-1" style="display:none;">select your shop's category</option>
					<%int i=0; %>
					<c:forEach var="category" items="${categories }">
						<option value="<%=i++%>">${category }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			  <label class="col-sm-2 control-label">sourceType</label>					
			<div class="col-sm-6">
				<select id="shopSourse" name="shopSourse" class="form-control" required="required" validateType="userValidate">
					<option value="-1" style="display:none;">select your shop's sourceType</option>
					<%int j=0; %>
					<c:forEach var="sourceType" items="${sourceTypes }">
						<option value="<%=j++%>">${sourceType }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!--
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;Province</label>	
			  <div class="col-sm-6">
				<select id="province" type="text" class="form-control" autofocus="autofocus" required="required" validateType="nonNegativeInteger">
					<option value="-1" style="display:none;">select your province</option>
				</select>
			  </div>
		</div>
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;City</label>	
			  <div class="col-sm-6">
				<select id="city" type="text" class="form-control" autofocus="autofocus" required="required" validateType="nonNegativeInteger">
					<option value="-1" style="display:none;">select your city</option>
				</select>
			  </div>
		</div>
		  -->
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;Address</label>	
			  <div class="col-sm-6">
				<input id="shopAddr" name="shopAddr" type="text" class="form-control" autofocus="autofocus" required="required"/>
			  </div>
		</div>
		
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;Description</label>	
			  <div class="col-sm-6">
				<textarea id="shopDesc"  name="shopDesc" class="form-control" rows="3"></textarea>
			  </div>
		</div>
		<div class="form-group">
			  <label class="col-sm-2 control-label"> &nbsp;&nbsp;&nbsp;Logo</label>	
			  <div class="col-sm-6" style="position: relative; width: 105px;">
 					<div class="btn btn-default" style="position: relative; width: 120px;overflow: hidden;">
 						<div>Select File</div>
						<input type="file" id="logo" name="myfiles" accept="image/* required="required"/>
					</div>
					<span id="uploading" style="display:none;"></span>
			  		<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -64px;display:none;"></span>
			  </div>
		</div>
		<div class="form-group" align="center">
			<div class="btn btn-default" id="register" >Register</div>
		</div>
		<div id="_shopImage">
			<img src="" alt="" style="display:none;" height="200px" width="200px"/>
		</div>
	</form>
	
	</div>
</body>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/ajaxfileupload.js"></script>
 <script src="static/js/shop/shopRegister.js"></script>
 <script src="static/js/shop/shopRegisterConfig.js"></script>
 <script src="static/js/common/validator.js"></script>
 <script>
 	var info = "${info}";
 	if(info){
 		alert(info);
 	}
 </script>
</html>
