<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath %>">
    
    <title>PARKnSHOP.com----Pay</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
		<link href="static/css/customer/headCommon.css" rel="stylesheet">
      <link href="static/css/pay.css" rel="stylesheet">
	
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
	<section class="content">
		
		<div class="payment">
			<ul>
				<li>
					<!-- <input type="radio" name="payment" value="onlineBank"/> -->
					<span>Online Banking System</span>
					
					<ul class="bank">
						<li><input type="radio" name="payment" value="China Post"/><img src="static/images/bank1.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Bank Of Communication"/><img src="static/images/bank2.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="China Construction Bank"/><img src="static/images/bank3.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="China Guangfa Bank"/><img src="static/images/bank4.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="SPD Bank"/><img src="static/images/bank5.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Bank Of China"/><img src="static/images/bank6.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="China Everbright Bank"/><img src="static/images/bank7.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Banco Bilbao Vizcaya Argentaria"/><img src="static/images/bank8.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Rural Credit Cooperatives"/><img src="static/images/bank9.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Bank of Guangzhou"/><img src="static/images/bank10.png" alt="bank1"/></li>
					</ul>
				</li>
				<li><!-- <input type="radio"  name="payment" value="Alipay"/> -->
					<span>Alipay</span>
					<ul class="bank">
						<li><input type="radio" name="payment" value="ChinaReD Payment"/><img src="static/images/online1.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Alipay"/><img src="static/images/online2.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="Tenpay"/><img src="static/images/online3.png" alt="bank1"/></li>
						<li><input type="radio" name="payment" value="CMPAY"/><img src="static/images/online4.png" alt="bank1"/></li>
						
						
					</ul>
					
				</li>
				<li><input type="radio" name="payment" value="COD"/><span>COD</span></li>
			</ul>
			<div align="center"><!-- <button id="__pay" class="btn btn-default">pay</button> -->
				<a href="/ParknShop/customer/pay"><input value="Pay" type="button" class="btn btn-default"></input>
			</div>
		</div>
	</section>
	<div class="modal" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog">
	      <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Pay</h4>
		      </div>
		      <div class="modal-body" style="text-align:center;line-height:4;">
			     <div class="account">
					<form  class="form-horizontal" method="post">
						<div>
							<span>Account</span>
							<input type="text" name="account" required="required" class="form-control"/>
						</div>
						<div>
							<span>Password</span>
							<input type="password" name="account" required="required" class="form-control"/>
						</div>
						<div>
							<input type="button" name="" value="confirm" class="form-control" id="confirm"/>
						</div>
					</form>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		   	  </div>
	   	  </div>
   	  </div>
</div>
		
<%@include file="../common/tail.html" %>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/shopping/pay.js"></script>
<script src="static/js/shopping/payConfig.js"></script>
     <script>
     	
    
     </script>
  </body>
</html>
