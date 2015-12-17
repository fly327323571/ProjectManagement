<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--shop Owner Homepage</title>
    
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
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css">
</head>
  <body>
     <%@include file="../common/toolbar.jsp" %>
	<header>
		<span class="logo">PARKnSHOP</span>
		<span class="identity">shop page</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
		
	</header>
	<section class="content">
		<div class="nav">
			<ul>
				<li><a href="#">Shop Profile</a></li>
				<li>
					<div class="profile">
						<div class="img">
							<div>
								<a id=view_my_profile href="javascript:void(0)" class="option">View Profile</a>
								<a id="modify_my_profile" href="javascript:void(0)" class="option">Modify Profile</a>
							</div>
						</div><!-- <img src="img/pic.jpg"/> -->
						<ul> 
							<li>shop name:<span>${shop.shopName }</span></li>
							<li>credit value:<span>${shop.shopRank } stars</span></li>
							<li>register time:<span>${shop.regTime}</span></li>
						</ul>
						
					</div>
				</li>
				<!-- 要修改此处的id请慎重,多处地方引用了此处的id 包括shopManageHomepage.js以及productListConfig.js-->
				<li><a id="dashboard" href="javascript:void(0)" class="option">Dashboard</a></li>
				<li><a id="order_history" href="javascript:void(0)" class="option">Order History</a></li>
				<li><a id="manage_products" href="javascript:void(0)" class="option">Manage Products</a></li><!-- WEB-INF/views/shop/productsList.html -->
				<li><a id="add_products" href="javascript:void(0)" class="option">Add Products</a></li>
				<li><a id="modify_shop_profile" href="javascript:void(0)" class="option">Modify Profile</a></li>
				<li><a id="manage_ads" href="javascript:void(0)" class="option">Manage Ads</a></li><!-- WEB-INF/views/shop/advertiseManage.html -->
			</ul>
		</div>
		<div class="container">
		
			<iframe id="iframe" src="store/${shop.shopNo}/dashboard/index.do" class="container" ></iframe>
			</div>
			
	</section>
		
<div id="chat" style="position:fixed;bottom: 0px;right: 10px;" class="panel panel-warning">
  <div class="panel-heading" style="cursor: pointer;">
       <h3 class="panel-title">Chatting</h3>
  </div>
  <div style="width: 260px;height: 300px;background-color:white;padding-left: 0px;padding-right: 0px;padding-top: 0px;" class="panel-body">
  <div style="height: 80%;">
  	<div style="background-color: #F8F8F8;height: 100%;  padding-top: 1px;">
		<div class="messages" style="background-color: white; width: 92%;height: 95%; margin: 8px; border: 1px solid #eee; overflow: scroll; font-size: small;padding: 7px;">
  		</div>
	</div>
  </div>
  <div style=" width: 100%;">	
  <div style="margin: 10px;">
	<div style=" float: left;width: 73%;">
	<textarea class="form-control" style=""></textarea>
	</div>
	<button class="btn btn-warning send" style=" margin-left: 7px; margin-top: 8px;">send</button>
   </div>
</div>
</div>
</div>		

		
	<%@include file="../common/tail.html" %>
     <script type="text/javascript">
     	var _storeId = '${shop.shopNo}';
     	var info = "${info}";//info 字符串不为空时将会跳转到店家主页 store/index.do
     	if(info){
     		alert(info);
     	}
     	var logo  = "${shop.shopIcon}";
     </script>
  </body>
 <script src="static/js/shop/shopManageHomepage.js"></script>
 <script src="static/js/shop/shopManageHomepageConfig.js"></script>
 <script src="static/js/communicate/communicate.js"></script>
</html>
