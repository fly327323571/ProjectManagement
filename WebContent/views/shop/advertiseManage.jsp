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
    <title>ParknShop--shop advertisement</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append  to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/fileupload.css" rel="stylesheet">
	<link href="static/css/shop/advertiseManage.css" rel="stylesheet">
</head>
	
  <body>
  
  <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h5 class="intro">
				you can select 3 pictures for the advertisement in your shop homepage
			</h5>
			<br/>
		
			<form class="form-horizontal" role="form">
				<!-- <span>advertise 1</span> -->
				<section class="clearfix">
					<div class="form-horizontal" style="float:left;width: 60%;">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputProduct">Product</label>
						<div class="col-sm-6">
							<select id="product1" class="form-control" name="inputProduct" required="required" validateType="nonNegativeInteger">
								<option value="-1" style="display:none;">select your product</option>
								<c:forEach var="product" items="${products }">
								<option value="${product.commoditNo }">${product.commodityName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						 <label class="col-sm-3 control-label" for="inputEmail3">Description</label>
						<div class="col-sm-6">
							<textarea class="form-control" id="inputEmail3"></textarea>
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label" for="File">Upload image</label>
					 <div class="col-sm-6" style="position: relative; width: 120px;">
 						<div class="btn btn_color" style="position: relative; width: 120px;overflow: hidden;">
 							<div>Select File</div>
							<input type="file" id="file1" name="file1" accept="image/*"/>
						</div>
			  		</div>
					<br />
					<br />
					 <p class="help-block" style="margin-left:210px">
						The size of picture can not be more than 10M
					 </p> 
					</div>
					</div>
					<div style="float:left">
						<img src="" alt="" style="width:180px;height:180px"/>
					</div>
				</section>
				<section class="clearfix">
					<div class="form-horizontal" style="float:left;width: 60%;">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputProduct">Product</label>
						<div class="col-sm-6">
							<select id="product2" class="form-control" name="inputProduct" required="required" validateType="nonNegativeInteger">
							<option value="-1" style="display:none;">select your product</option>
								<c:forEach var="product" items="${products }">
								<option value="${product.commoditNo }">${product.commodityName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						 <label class="col-sm-3 control-label" for="inputEmail3">Description</label>
						<div class="col-sm-6">
							<textarea class="form-control" id="inputEmail3"></textarea>
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label" for="File">Upload image</label>
					 <div class="col-sm-6" style="position: relative; width: 120px;">
 						<div class="btn btn_color" style="position: relative; width: 120px;overflow: hidden;">
 							<div>Select File</div>
							<input type="file" id="file2" name="file2" accept="image/*"/>
						</div>
			  		</div>
					<br />
					<br />
					 <p class="help-block" style="margin-left:210px">
						The size of picture can not be more than 10M
					 </p> 
					</div>
					</div>
					<div style="float:left">
						<img src="" alt="" style="width:180px;height:180px"/>
					</div>
				</section>
				<section class="clearfix">
					<div class="form-horizontal" style="float:left;width: 60%;">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputProduct">Product</label>
						<div class="col-sm-6">
							<select id="product3" class="form-control" name="inputProduct" required="required" validateType="nonNegativeInteger">
							<option value="-1" style="display:none;">select your product</option>
								<c:forEach var="product" items="${products }">
								<option value="${product.commoditNo }">${product.commodityName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						 <label class="col-sm-3 control-label" for="inputEmail3">Description</label>
						<div class="col-sm-6">
							<textarea class="form-control" id="inputEmail3"></textarea>
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label" for="File">Upload image</label>
					 <div class="col-sm-6" style="position: relative; width: 120px;">
 						<div class="btn btn_color" style="position: relative; width: 120px;overflow: hidden;">
 							<div>Select File</div>
							<input type="file" id="file3" name="file3" accept="image/*"/>
						</div>
			  		</div>
					<br />
					<br />
					 <p class="help-block" style="margin-left:210px">
						The size of picture can not be more than 10M
					 </p> 
					</div>
					</div>
					<div style="float:left">
						<img src="" alt="" style="width:180px;height:180px"/>
					</div>
				</section>
				
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="submit" class="btn btn_color" type="button">Submit</button>
					</div>
				</div>
			</form>
			
		</div>
	</div>
</div>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/shop/adManage.js"></script>
<script src="static/js/shop/adManageConfig.js"></script>
<script type="text/javascript">
	var storeId = ${storeId};
</script>
  </body>
 
</html>
