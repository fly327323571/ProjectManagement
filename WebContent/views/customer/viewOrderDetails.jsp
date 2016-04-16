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
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
		<link href="static/css/user/headTail.css" rel="stylesheet">
	
	<link href="static/css/customer/viewOrderDetails.css" rel="stylesheet">
</head>

<body>
 <%-- <%@include file="../common/toolbar.jsp" %> --%>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="title_color">
				View Order Details
			</h3>
			
			<div style="text-align:right">
			<a class="btn"><span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;vendor: ${order.seller.userName }</a>
			</div>
			<hr/>
			<div class="row clearfix" >
				<div class="col-md-4 column">
					<h4><span class="main_color">Order Status: </span>
						<c:choose>
			               	<c:when test="${empty order.state}">
			            		&nbsp;
			            	</c:when>
			               	<c:when test="${order.state == 0}">
			               		Lose Efficiency
			               	</c:when>
			               	<c:when test="${order.state == 1}">
			               		Unpaid
			               	</c:when>
			            	 <c:when test="${order.state == 2}">
			            		non-payment
			            	</c:when>
			               	<c:when test="${order.state ==6}">
			               		Not shipped
			               	</c:when>
			               	<c:when test="${order.state >= 7}">
			               		User request a refund
			               	</c:when>
			               	<c:when test="${order.state == 3}">
			               		Seller refused to refund
			               	</c:when>
			               	<c:when test="${order.state == 4}">
			               		refund
			               	</c:when>
			               	<c:when test="${order.state == 5}">
			               		Shipped
			               	</c:when>
			               	<c:when test="${order.state == 6}">
			               		Signed
			               	</c:when>
			               	<c:when test="${order.state == 7}">
			               		Comment
			               	</c:when>
			               	<c:when test="${order.state == 8}">
			               		Termination
			               	</c:when>
			         	</c:choose>	
					</h4>
					<br/>
				<div>
					<dl>
						<dt><b class="main_color">Delivery Address: </b>${order.toAddr}</dt>
						<br/>
						<dt><b class="main_color">Receiver Name: </b>${order.buyer.userName}</dt>
						<!--<dt><b class="main_color">Customer Message</b></dt>
						<dd><p><i>I want the blue one. ^-^</i></p></dd>  -->
						
					
					</dl>
					</div>
				</div>
				<div class="col-md-8 column">
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color:#ff9900;color:#ffffff;">
							<h3 class="panel-title">
								<b>Order Information</b>
							</h3>
						</div>
						<div class="panel-heading">
							<dl>
								<dt>
									OrderNO
								</dt>
								<dd>
									<p>${order.orderNo }</p>
								</dd>
								<dt>
								Product Information
								</dt>
								<dd>
									<table id="commodityInfoTable">
										<tr>
										<th>Name</th><th>Price</th><th>Number</th><th>Comment</th>
										</tr>
										<c:forEach items="${orderDetails }" var="orderDetail" >
											<tr>
											<td>${orderDetail.commodity.commodityName}</td>
											<td>${orderDetail.commodity.commodityPrice }</td>
											<td>${orderDetail.count}</td>
											
											<td>
											<c:choose>
											<c:when test="${order.state != 8}">
												<a href="/ParknShop/customer/comment?orderDetailId=${orderDetail.id}">
												<input value="Comment" type="button" ></input>
												</a>			
			            					</c:when>
											 <c:when test="${order.state == 6}">
			               						<a href="/ParknShop/customer/signIn?orderNo=${order.orderNo} }">
												<input value="Sign In " type="button"></input>
												</a>
			               					</c:when>
											</c:choose>
											</td>
											
											</tr>
										</c:forEach>
									</table>
									<br/>
								</dd>
								<dt>
									Total Price 
								</dt>
								<dd>
									<p>${order.orderPrice }</p>
								</dd>					
								<dt>
									Date of Order
								</dt>
								<dd>
									<p>${order.addTime}</p>
								</dd>
								<dt>
									Payment Type
								</dt>
								<dd>
									<c:choose>
						               	<c:when test="${order.payWay == 0}">
						               		<td>Online Payment</td>
						               	</c:when>
						               	<c:when test="${order.payWay == 1}">
						               		<td>Cash on delivery</td>
						               	</c:when>
						         	</c:choose>
								</dd>
								<br />
								<dt>
									Express Company
								</dt>
								<dd>
									<p>${empty express? "Unshipped":express.expressCompanyName}</p>
								</dd>
							</dl>	
						</div>
						<div id="track" class="panel-footer" style="background-color:#ff9900;color:#ffffff;">
							TrackID: <em></em>
							<a class="btn panel_btn" target="_blank" href="business/logistics?packageNumber="><b>Track the logistics?</b></a>
						</div>
					</div>
				</div>
			</div>
			<hr/>
			
 <center><!-- <button class="btn btn-default" type="submit" onclick="window.close();">Close</button> --></center>
		</div>
	</div>
</div>
<%@include file="../common/tail.html" %>
<script src="static/js/common/baseAjax.js"></script>
<script>
	if(!$("#postCompany").text()){
		$("#postCompany").text("---");
		$("#track").hide();
	}
</script>
</body>
</html>
