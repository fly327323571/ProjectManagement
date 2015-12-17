<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
  <base href="<%=basePath%>">
    <title>ParknShop--customer detail</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <script src="static/js/common/jquery-ui.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jquery-ui.css" rel="stylesheet">
	<link href="static/css/customer/modifyCustomerProfile.css" rel="stylesheet">

</head>
<body>
<div class="container">
	<div  class="pic">
		<img  src="${user.portrait }" width="200" height="200" />
		<div style="position: relative; width: 105px;margin-top: 16px;  margin-left: 30px;">
			<div class="btn btn-default" style="position: relative; width: 125px;overflow: hidden;">
	 			<div>Update Image</div>
				<input type="file" id="logo" name="file" accept="image/*">
			</div>
			<span id="uploading" style="display:none;"></span>
			<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -48px;display:none;"></span>
		</div>
	</div>
		<form class="form-horizontal">
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Username
					</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" value="${user.userName }"
						id="userName" readOnly="true"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Gender
					</label>
				<div class="col-sm-7">
					<label for="maleRadio" style="color: black;font-weight: normal;min-width: 80px;">
						Male<input style="margin-left: 5px;" type="radio" id="maleRadio"  name="gender" value="0"/>
					</label>
					<label for="femaleRadio"  style="color: black;font-weight: normal;min-width: 80px;">
					Female<input style="margin-left: 5px;" type="radio" id="femaleRadio" name="gender" value="1"/>
					</label>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Birthday
					</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" value="${user.birthday }"
						id="birthday" />
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Phone Number
					</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" value="${user.phoneNumber }"
						id="phoneNumber" required="required" validateType="cellphone"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Email
					</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" value="${user.email}"
						id="email"  required="required" validateType="email"/>
				</div>
			</div>
			<div class="form-group">
								
					<div class="btn btn-default" id="save">Modify</div>
				</div>
				
				
			</div>

		</form>

	</div>
</body>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/customer/modifyCustomerProfile.js"></script>
<script src="static/js/customer/modifyCustomerProfileConfig.js"></script>
<script>

	var gender = '${user.gender}';	
	if(gender){
		$("input[value='"+gender+"']").attr("checked",true);
	}
</script>
</html>
