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
    
    <title>PARKnSHOP.com----Confirm Order</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet"> 
      <link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/customer/headCommon.css" rel="stylesheet">
      <link href="static/css/confirmOrder.css" rel="stylesheet">
      <script src="static/js/common/jquery-1.11.1.js"></script>
	<script src="static/js/common/bootstrap.min.js"></script>
	
  </head>
  
  <body>
    <%@include file="../common/toolbar.jsp" %>
	<header>
		<span class="logo">PARKnSHOP</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
	</header>
	<ul class="navBar">
		<li><span class="glyphicon glyphicon-hand-right"></span> Confirm Order</li>
		<li><span class="glyphicon glyphicon-hand-right"></span> Pay</li>
		<li><span class="glyphicon glyphicon-hand-right"></span> sign on delivery</li>
		<li><span class="glyphicon glyphicon-hand-right"></span> make comments</li>
	</ul>
     <div class = "conform-main">
    
     <h4 class = "tittle">Confirm your address</h4>
		<div class="address-frame" data-toggle="buttons" >
			
		</div>
        <button class="btn btn_color" id="newAddress" type="button" >Use a new address</button>
        
        <h4 class = "tittle">Confirm your Order</h4>
        <div >
        <table id="orderToPay" class="table table-hover">
         	<tr><th>No</th><th>Buyer Name</th><th>Order No</th><th>Order Price</th><th>Seller Name</th></tr>
			<c:forEach items="${orderList }"  var="order" varStatus="status"> 
	         	<tr>
	         		<td>${status.index+1 }</td>
					<td>${empty order.buyer.userName?" ":order.buyer.userName}</td>
					<td>${empty order.orderNo?" ":order.orderNo}</td>
			        <td>${empty order.orderPrice?" ":order.orderPrice}￥</td>	 
		         	<td>${empty order.seller.userName?" ":order.seller.userName}</td> 
	         	</tr> 
			</c:forEach>
         </table>       
        </div>
     <div id="pagination"></div>   
	    <div class="checkOut">
	    	<span>orders' total price: ${sumPrice}￥</span><span id="totalPrice"></span>
	   		<button class="btn btn_color"  id="pay">pay</button>
	    </div>
  </div>  
  <script type="text/template" id="dialogTmpl">
	<div class="modal" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" style="width:800px;">
	      <div class="modal-content" >
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel" style="color:black;">Warm Prompt</h4>
		      </div>
		      <div class="modal-body" style="text-align:center;line-height:4;">
		        <p>tip</p>	
		      </div>
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary" id="delete">Delete</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		   	  </div>
	   	  </div>
   	  </div>
</div>
</script>
    

   <%@include file="../common/tail.html" %>
  <script type="text/template" id="newAddressTmpl">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label">Consignee</label>					
			<div class="col-sm-7">
				<input id="consignee" name="consignee" type="text" class="form-control" autofocus="autofocus" required="required" validateType="userName"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">Province</label>					
			<div class="col-sm-7">
				<select id="province" type="text" class="form-control" autofocus="autofocus" required="required" validateType="nonNegativeInteger">
					<option value="-1" style="display:none;">select your province</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">City</label>					
			<div class="col-sm-7">
				<select id="city" type="text" class="form-control" autofocus="autofocus" required="required" validateType="nonNegativeInteger">
					<option value="-1" style="display:none;">select your city</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">Address</label>					
			<div class="col-sm-7">
				<input id="address" name="address" type="text" class="form-control" autofocus="autofocus" required="required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">Phone number</label>					
			<div class="col-sm-7">
				<input id="phoneNumber" name="phoneNumber" type="text" class="form-control" autofocus="autofocus" required="required" validateType="phoneNumber"/>
			</div>
		</div>
	</form>
  </script>
  <script type="text/template" id="addressTmpl">
	<label class="btn btn-p address-more"> 
		<input type="radio" name="options"  autocomplete="off" id="{addrId}">
		<span style="font-weight:bold;">
		<i style="color:#FF5400;"class="glyphicon glyphicon-map-marker"></i>
		<i style="color:#ff5400;">deliver to </i> <i class="province">{province}</i>, <i class="city">{city}</i>, <i class="address">{address}</i> (<i class="consignee">{consignee}</i> receive) 
		<i style="color:#ccc;">contact : <i class="phoneNumber">{phoneTel}</i></i>
		</span>
		<span align="right">
			<a class="modifyAddress">Modify</a>
			<a class="deleteAddress">Delete</a>
		</span>
	</label>
</script>
</body>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/table.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/common/jquery.paginate.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<!-- <script src="static/js/shopping/confirmOrder.js"></script> -->
<script src="static/js/shopping/confirmOrderConfig.js"></script>
</html>
