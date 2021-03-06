<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--Customer Buy History</title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->

	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/customer/buyHistory.css" rel="stylesheet">
</head>

<body>
	<br />
	<div class="container">

		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand">Orders:</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav" id="viewByStatus">
						
						<li class="nav_option active">
							<!-- 默认 -->
							<a id="all">All</a>
						</li>
						<li class="nav_option">
							<a  id="unpaid">Unpaid</a>
						</li>
						<li class="nav_option">
							<a  id="unshipped">Unshipped</a>
						</li>
						<li class="nav_option">
							<a  id="shipped">Unreceived</a>
						</li>
						<li class="nav_option">
							<a  id="received">Received</a>
						</li>
						
					</ul>
			<form class="navbar-form navbar-right" role="search">
				<div class="form-group">
					<input class="form-control" placeholder="order key words" type="text" id="orderKeyWords"/>
				</div>
				<button class="btn btn_color" type="submit" id="orderSearch">Search</button>
			</form>
		</div>
		</nav>
		<table id="orderList" class="table">
			<tr><th>No</th><th>Order No</th><th>Order Time</th><th>Order Price</th><th>Status</th>
				<th>Pay Way</th><th>Shop Name</th><th>More Details</th><th>Pay</th><th>Sign for</th>
			</tr>
			<c:forEach items="${orderList }"  var="order" varStatus="status">
				<tr>
				<td>${status.index+1 }</td>
				<td>${empty order.orderNo?" ":order.orderNo}</td>
				<td>${order.addTime} </td>
		        <td>￥${empty order.orderPrice?" ":order.orderPrice}</td>
		        <c:choose>
	               	<c:when test="${empty order.state}">
	            			<td>&nbsp;</td>
	            	</c:when>
	            	<c:when test="${order.state == 0}">
	               		<td>Lose Efficiency</td>
	               	</c:when>
	               	<c:when test="${order.state == 1}">
	               		<td>Unpaid</td>
	               	</c:when>
	               	<c:when test="${order.state == 2}">
	               		<td>Not shipped</td>
	               	</c:when>
	               	<c:when test="${order.state == 3}">
	               		<td>User request a refund</td>
	               	</c:when>
	               	<c:when test="${order.state == 4}">
	               		<td>Seller refused to refund</td>
	               	</c:when>
	               	<c:when test="${order.state == 5}">
	               		<td>refund</td>
	               	</c:when>
	               	<c:when test="${order.state == 6}">
	               		<td>Shipped</td>
	               	</c:when>
	               	<c:when test="${order.state == 7}">
	               		<td>Signed</td>
	               	</c:when>
	               	<c:when test="${order.state == 8}">
	               		<td>Comment</td>
	               	</c:when>
	               	<c:when test="${order.state == 9}">
	               		<td>Termination</td>
	               	</c:when>
	         	</c:choose>	
		        <c:choose>
	               	<c:when test="${empty order.payWay}">
	            			<td>&nbsp;</td>
	            	</c:when>
	               	<c:when test="${order.payWay == 0}">
	               		<td>Online Payment</td>
	               	</c:when>
	               	<c:when test="${order.payWay == 1}">
	               		<td>Cash on delivery</td>
	               	</c:when>
	         	</c:choose>
	    		<td>${order.shop.shopName }</td>
	    		<td>
					<a href="/ParknShop/customer/moreOrderDetail?orderId=${order.id}"><input value="More" type="button" class="btn btn_color"></input></a>
				</td>
				<td>
					<c:choose>
						<c:when test="${order.state == 1}">
							<a href="/ParknShop/customer/confirmOrders?isInner=1"><input value="Pay" type="button" class="btn btn_color"></input></a>
						</c:when>
						<c:otherwise>
							Can't Pay
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${order.state == 6}">
							<a href="/ParknShop/customer/signFor?orderId=${order.id}"><input value="Sign" type="button" class="btn btn_color"></input></a>
						</c:when>
						<c:otherwise>
							Can't Sign
						</c:otherwise>
					</c:choose>
				</td>
	         	</tr>
			</c:forEach>
			
		</table>
		<nav class="page">
		<div id="pagination"></div>
		</nav>
	</div>
<script type="text/template" id="dialogTmpl">
	<div class="modal fade" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Warm Prompt</h4>
			</div>
			<div class="modal-body" style="text-align:center;line-height:4;">
				<p>tip</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_color" id="yes1">Yes</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			</div>
		</div>
		</div>
	</div>
</script>
</body>
<script type="text/template" id="confirmTmpl">
	<div>
		<p style="font-size: large;"><span class="glyphicon glyphicon-info-sign info"></span>Are you sure to confirm the receipt of order <span style="color:#FF5400;">{orderId}</span> ?</p>
		<span style="color: #aaa;">You may end up with the loss of money and product,if you confirmed without gaining the product.</span>
	</div>
</script>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<!-- <script src="static/js/customer/customerBuyHistory.js"></script> -->
<script src="static/js/customer/customerBuyHistoryConfig.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/table.js"></script>
<script src="static/js/common/jquery.paginate.js"></script>
</html>
