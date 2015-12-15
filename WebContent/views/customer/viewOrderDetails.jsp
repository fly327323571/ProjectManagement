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
 <%@include file="../common/toolbar.jsp" %>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="title_color">
				View Order Details
			</h3>
			
			<div style="text-align:right">
			<a class="btn"><span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;vendor: ${vendor.userName }</a>
			</div>
			<hr/>
			<div class="row clearfix" >
				<div class="col-md-4 column">
					<h4><span class="main_color">Order Status: </span>${order.statusString }</h4>
					<br/>
					<div>
					<dl>
						<dt><b class="main_color">Delivery Address:</b></dt>
						<dd><address> <strong>${address.province }, ${address.city }.</strong><br /> ${address.address }<br /> <abbr title="Phone">P:</abbr> ${address.phoneTel }</address></dd>
						<dt><b class="main_color">Receiver Name</b></dt>
						<dd><p>${address.consignee }</p></dd>
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
									OrderID
								</dt>
								<dd>
									<p>${order.mainOrderId }</p>
								</dd>
								<dt>
								Product Name
								</dt>
								<dd>
									<c:forEach var="subOrder" items="${order.productsOfOrder }">
									<p>${subOrder.productName }</p>
									</c:forEach>
								</dd>
								<dt>
									Total Price 
								</dt>
								<dd>
									<p>${order.totalPrice }</p>
								</dd>					
								<dt>
									Date of Order
								</dt>
								<dd>
									<p>${order.addTimeString }</p>
								</dd>
								<dt>
									Payment Type
								</dt>
								<dd>
									<p>${order.paymentType }</p>
								</dd>
								<dt>
									Post Company
								</dt>
								<dd>
									<p id="postCompany">${order.postType }</p>
								</dd>
							</dl>	
						</div>
						<div id="track" class="panel-footer" style="background-color:#ff9900;color:#ffffff;">
							TrackID: <em>${order.packageNumber }</em>
							<a class="btn panel_btn" target="_blank" href="business/logistics?packageNumber=${order.packageNumber }"><b>Track the logistics?</b></a>
						</div>
					</div>
				</div>
			</div>
			<hr/>
			
 <center><button class="btn btn-default" type="submit" onclick="window.close();">Close</button></center>
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
