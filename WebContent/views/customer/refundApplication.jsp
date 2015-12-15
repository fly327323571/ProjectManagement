<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script src="static/js/common/baseAjax.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/manage.css" rel="stylesheet">
	<link href="static/css/customer/refundApplication.css" rel="stylesheet">
</head>

<body>
<%@include file="../common/toolbar.jsp" %>
<header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">refund page</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
		
</header>
<div class="container">
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="title_color">
				Appeal Refund
			</h3>
			<br/>
		
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">Order Id</label>
					<div class="col-sm-5">
						<div class="order-id">${orderId }</div>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">Refund Reason</label>
					 <div class="col-sm-5">
						<select id="refundReason" class="form-control" required="required" validateType="nonNegativeInteger">
						<option value="-1" style="display:none;">Please select the refund reason</option>
						<option value="0">I received a fake</option>
						<option value="1">The quality problem</option>
						<option value="2">This is not the one I want</option>
						<option value="3">Other reason</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label" for="inputAmount">Refund amount</label>
					<div class="col-sm-2">
						<input placeHolder="amount of refund" id="refundAmount" class="form-control" id="iinputAmount" type="text" required="required" validateType="positiveFloat"/>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label" for="inputEmail3">Description</label>
					<div class="col-sm-5">
						<textarea id="description" placeHolder="describe your reason more thoroughly" class="form-control" id="inputEmail3"></textarea>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label" for="File">Upload proof</label>
					 <div class="col-sm-6" style="position: relative; width: 105px;">
 						<div class="btn btn_color" style="position: relative; width: 120px;overflow: hidden;">
 							<div>Select File</div>
							<input type="file" id="file" name="file" accept="image/*">
							<input type="hidden" id="image" name="image"/>
						</div>
						<span id="uploading" style="display:none;"></span>
			  			<span id="upload_mark"class="glyphicon glyphicon-ok mark_OK" style="position: absolute;top: 8px;right: -64px;display:none;"></span>
			  		</div>
					<br />
					<br />
					 <p class="help-block" style="margin-left:210px">
						The size of picture can not be more than 10M
					 </p>	 
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a class="btn btn_color" style="padding: 6px 29px;" type="submit">Submit</a>
					</div>
				</div>
			</form>
			<div class="picture">
				<img src="" alt=""/>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" style="width: 800px;">
	      <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Warm Prompt</h4>
		      </div>
		      <div class="modal-body clearfix" style="text-align:center;line-height:4;">
		        <p>tip</p>	
		      </div>
		      <div class="modal-footer" style="text-align: center;">
		      	<button type="button" class="btn btn-primary" id="delete">Delete</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		   	  </div>
	   	  </div>
   	  </div>
</div>
</div>
	<%@include file="../common/tail.html" %>
<script src="static/js/common/validator.js"></script>
 <script src="static/js/common/ajaxfileupload.js"></script>
  <script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/customer/refundApplication.js"></script>
<script src="static/js/customer/refundApplicationConfig.js"></script>
<script>
	var info = "${info}";
</script>
</body>
</html>
