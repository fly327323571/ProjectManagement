<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    
    <title>PARKnSHOP.com----My Cart</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
      <link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/myCart.css" rel="stylesheet">
	
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
  </head>
   <body>
     <%@include file="../common/toolbar.jsp" %>
     <header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
	</header>
     <div class = "conform-main">
     
       <div >
        <table class="table table-hover" id = "orderList">
        </table>        
    </div>
    <div id="pagination"></div>
    <form id="checkOut" class="checkOut" action="store/orderConfirm" method="post">
    	<span>selected goods's total price: $</span><span id="totalPrice"> 0</span>
   		<button class="btn btn_color"  type="button" id="check">Check Out</button>
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
		      	<button type="button" class="btn btn-primary" id="delete">Delete</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		   	  </div>
	   	  </div>
   	  </div>
</div>

  <%@include file="../common/tail.html" %>
  </body>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/table.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/common/jquery.paginate.js"></script>
<script src="static/js/shopping/myCart.js"></script>
<script src="static/js/shopping/myCartConfig.js"></script>
<script src="static/js/common/validator.js"></script>
</html>
