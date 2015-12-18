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
    <title>ParknShop--shop detail</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append â#!watchâ to the browser URL, then refresh the page. -->
	

	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	
	<link href="static/css/shop/addProduct.css" rel="stylesheet">
</head>
	
<body>
<div class="container">
	
		<div class="clearfix">
			<form id="productForm" role="form" class="form-horizontal">
				<div class="form-group">
					 <span class="col-sm-2 control-label" for="productName">Product name</span>
					 <div class="col-sm-7" >
					 	<input type="text" class="form-control" id="commodityName" name="commodityName" required="required" validateType="productName" placeholder="composed by letters, Numbers, underscores and space,length 4-16 "/><!--  -->
					 	
					 </div>
				</div>
				<input type="text"  id="storeId" name="storeId" style="display:none;"/>
				<div class="form-group">
					 <span class="col-sm-2 control-label" for="presentPrice">Price</span>
					 <div class="col-sm-7" >
					 	<input type="text" class="form-control" id="commodityPrice" name="commodityPrice" required="required" validateType="positiveFloat"/>
					 </div>
				</div>
				<div class="form-group">
					 <span class="col-sm-2 control-label" for="type">Category</span>
					 <div class="col-sm-7" >
						 <select id="type" name="type" class="category form-control" required="required" validateType="nonNegativeInteger">
						 <option value="-1" style="display:none;">select your category</option>
						 <%int i=0; %>
						 <c:forEach var="category" items="${categories }">
							<option value="<%=i++%>">${category }</option>
						 </c:forEach>
					     </select>
				     </div>
				</div>
				<div class="form-group">
					 <span class="col-sm-2 control-label" for="amounts">Quantity</span>
					 <div class="col-sm-7" >
					 	<input type="number" class="form-control" id="commodityCount" name="commodityCount" required="required" validateType="positiveInteger"/>
					 </div>
				</div>
				
				<div class="form-group">
					 <span class="col-sm-2 control-label" for="description">description</span>
					 <div class="col-sm-7" >
					 	<textarea id="commodityDetail" rows="3" class="form-control" name="commodityDetail" required="required"></textarea>
					 </div>
				</div>
				
				<div class="form-group upload" style="position: relative;">
					 <span class="col-sm-2 control-label" for="">Photo</span>
					 <div class="col-sm-7" >
						 <div class="btn btn-default" style="position: relative; width: 120px;overflow: hidden;">
	 						<div>Select File</div>
							<input type="file" id="file" name="file" accept="image/*">
						 </div>
						<span id="uploading" style="display:none;"></span>
				  		<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;display:none;color: green;"></span>
						<input id="defaultImage" name="defaultImage" type="text" style="display:none;"/>
					</div>
				</div>
				
				<div class="checkbox">
					
				</div>
				
				
			</form>
			<div id="image_snap_shot">
				<img id="_productImage" src="" alt="" style="display:none;"/>
			</div>
		</div>
			<div align="center">
					<div id="addProductBtn" class="btn btn-default">Submit</div>
				</div> 
</div>
	<div class="modal fade" id="dialog" style="/*display: none;text-align:center;width:30%;*/ " tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
      
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
   	  </div>
   	  </div>
   	  </div>
     </div>

</body>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/product/addProduct.js"></script>
<script src="static/js/product/addProductConfig.js"></script>
<script type="text/javascript">
	
	var storeId = '${storeId}';
	var result = '${result.result}';
	if(result) {
		//alert(result);
		$("#dialog #tip").html(result);
		$("#dialog").modal();
	}
	
</script>
</html>
