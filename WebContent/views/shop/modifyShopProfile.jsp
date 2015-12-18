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
    <title>ParknShop--shop detail</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/shop/modifyShopProfile.css" rel="stylesheet">

</head>
<body>
<div class="container">

	<div class="tabbable" id="tabs-175400">
		<ul class="nav nav-tabs"><!-- 选项卡 -->
			<li class="active">
				<a href="#panel-334142" data-toggle="tab"><p class="block-title">Basic shop profile</p></a>
			</li>
			<li>
				<a href="#panel-995732" data-toggle="tab"><p class="block-title">Postage policy</p></a>
			</li>
			
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="panel-334142">
			<div class="clearfix bs-callout bs-callout-warning" style="padding:auto;">
			<div class="pic">
				<img  src="${store.shopIcon}" width="200" height="200" >
				<div style="position: relative; width: 105px;margin-top: 16px;  margin-left: 30px;">
					<div class="btn btn-default" style="position: relative; width: 125px;overflow: hidden;">
			 			<div>Update Logo</div>
						<input type="file" id="logo" name="myfiles" accept="image/*">
					</div>
					<span id="uploading" style="display:none;"></span>
					<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -48px;display:none;"></span>
				</div>
			</div>
			<form id="basicProfile" class="form-horizontal">
				<div class="form-group" style="display: none;">
					<label class="col-sm-2 control-label" for="inputEmail3">Store
						id</label>
					<div class="col-sm-7">
						<input class="form-control" type="text" value="${store.shopNo}" id="shopNo" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="inputEmail3">Shop
						Name</label>
					<div class="col-sm-7">
						<input class="form-control" type="text" value="${store.shopName }"
							id="shopName" required="required" validateType="storeName"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="inputEmail3">Category</label>
					<div class="col-sm-7">
						<select id="category" name="category" class="form-control" validateType="nonNegativeInteger">
						<option value="-1" style="display:none;">select your shop's category</option>
							<%int i=0; %>
						<c:forEach var="category" items="${categories}">
							<%request.setAttribute("i", i);%>
							<option value="<%=i++%>"
							 <c:if test='${store.shopCategories ==i}'>selected="selected"</c:if>
							>${category}</option>
						</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="inputEmail3">Remarks</label>
					<div class="col-sm-7">
						<textarea rows="3" id="shopDesc" class="form-control">${store.shopDesc }</textarea>
					</div>
				</div>
				<div class="form-group">
									
						<div class="btn btn-default" id="save">Save</div>
						<div class="btn btn-default" id="close">Cancel</div>
					</div>
				</form>
			</div>
		</div><!-- end of block -->
		
		<div class="tab-pane" id="panel-995732">		
			<div class="clearfix bs-callout bs-callout-info" style="padding: 20px 50px;">
				<div id="policyProfile" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="inputEmail3">postage policy</label>
						<div class="col-sm-6">
							<select id="policy" name="category" class="form-control" validateType="nonNegativeInteger">
								<option value="-1" style="display:none;">select your postage policy</option>
								<option value="0">free for delivery</option>
								<option value="1">charged for delivery</option>
							</select>
						</div>
					</div>
					<div class="charge-policy">
					
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn btn-default" id="savePolicy">Save</div>
					</div>
				</div>
			</div>
		</div><!-- end of block -->
	</div>
 </div>
	
</div>
<script type="text/template" id="policyTmpl">
				<h4 class="page-header">Within province</h4>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Basic postage</label>
					<div class="col-sm-6">
						<input id="within_basicPostage" name="category" class="form-control" validateType="nonNegativeFloat"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Quantity limit</label>
					<div class="col-sm-6">
						<input id="within_quantityLimit" name="category" class="form-control" validateType="nonNegativeInteger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Extra fee/product</label>
					<div class="col-sm-6">
						<input id="within_extfee" name="category" class="form-control" validateType="nonNegativeFloat"/>
					</div>
				</div>
				
				<h4 class="page-header">Cross province</h4>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Basic postage</label>
					<div class="col-sm-6">
						<input id="cross_basicPostage" name="category" class="form-control" validateType="nonNegativeFloat"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Quantity limit</label>
					<div class="col-sm-6">
						<input id="cross_quantityLimit" name="category" class="form-control" validateType="nonNegativeInteger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail3">Extra fee/product</label>
					<div class="col-sm-6">
						<input id="cross_extfee" name="category" class="form-control" validateType="nonNegativeFloat"/>
					</div>
				</div>
</script>
</body>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/shop/modifyShopProfile.js"></script>
<script src="static/js/shop/modifyShopProfileConfig.js"></script>
<script>
	var expressRules = ${expressRules};
</script>
</html>
