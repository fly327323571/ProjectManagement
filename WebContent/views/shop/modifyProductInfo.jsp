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
	
	<!--append â#!watchâ to the browser URL, then refresh the page. -->
	
	
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	
	<link href="static/css/shop/modifyProductInfo.css" rel="stylesheet">
</head>

<body>
<div class="container">
	<div  class="pic">
		<img  src="${product.commodityImg}" width="200" height="200" >
		<div style="position: relative; width: 105px;margin-top: 16px;  margin-left: 30px;">
			<div class="btn btn-default" style="position: relative; width: 125px;overflow: hidden;">
	 			<div>Update Logo</div>
				<input type="file" id="logo" name="myfiles">
			</div>
			<span id="uploading" style="display:none;"></span>
			<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -48px;display:none;"></span>
		</div>
	</div>

	<form class="form-horizontal">
		
			<div class="form-group">
				 <label class="col-sm-2 control-label" for="inputEmail3" >Product Name</label>
				<div class="col-sm-7" >
					<input class="form-control" type="text" value="${product.commodityName }" id="commodityName" name="commodityName" required="required" validateType="productName"/>
				</div>
			</div>
			<div class="form-group">
				  <label class="col-sm-2 control-label" for="inputEmail3">Price</label>					
				<div class="col-sm-7">
					<input class="form-control" type="text" value='${product.commodityPrice }' id="commodityPrice" name="commodityPrice" required="required" validateType="positiveFloat"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputEmail3">Category</label>
				<div class="col-sm-7">
					<select id="category" name="category" class="form-control" required="required" validateType="nonNegativeInteger">
					<option value="-1" class="form-control" style="display:none;">select your product's category</option>
						<%int i=0; %>
					<c:forEach var="category" items="${categories}">
						<%request.setAttribute("i", i);%>
						<option value="<%=i++%>"
						 <c:if test='${product.category ==i}'>selected="selected"</c:if>
						>${category}</option>
					</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				  <label class="col-sm-2 control-label" for="inputEmail3">Quantity</label>					
				<div class="col-sm-7">
					<input class="form-control"  type="text" value="${product.commodityCount }" id="commodityCount" required="required" validateType="positiveInteger"/>
				</div>
			</div>
			
			
			<div class="form-group">
				  <label class="col-sm-2 control-label" for="inputEmail3">product introduciton</label>					
				<div class="col-sm-7">
					<textarea class="form-control" rows="3" id="commodityDetail"  name="commodityDetail">${product.commodityDetail }</textarea>
				</div>
			</div>
			
			
			
			<div class="form-group">
					 <button class="btn btn-default"  type="button" id="save">Save</button>
					 <button class="btn btn-default"  type="button" id="close">Close</button>
			</div>
			
		</form>

</div>

<div class="modal fade" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
      <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Warm Prompt</h4>
      </div>
      <div class="modal-body" style="text-align:center;line-height:4;">
        <p>tip</p>	
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
   	  </div>
   	  </div>
   	  </div>
</div>
	
  <script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>   
 <script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/product/modifyProductInfo.js"></script>
<script src="static/js/product/modifyProductInfoConfig.js"></script>
<script src="static/js/common/validator.js"></script> 
<script type="text/javascript">
	 var storeId = "${storeId}";
	 var productId = ${product.commodityNo};
</script>


</body>
</html>
