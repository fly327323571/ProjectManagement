<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com----Product Details</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
      <link href="static/css/customer/headCommon.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/productShow.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
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
  
  
  <div class = "main" >
  <input type="hidden" id="userId" value="${store.shopOwner.userName }"/>
    <div class = "view">
	    <img src="${product.commodityImg}" class="img-thumbnail">
	    <span  class = "glyphicon glyphicon-heart collection" id="favorite"></span>
    </div>
    
    <div class = "product-view">
      <input type="hidden" id="productId" value="${product.commodityNo}"/>
      <h3 class = "pro-title" >${product.commodityName} </h3>
      <div style = "width:100px;float:left">
        <p>Price:</p>
        <p>Inventory:</p>
        <p>Delivery:</p>
        <p>Postage:</p>
        <p>Number:</p>
        <p>Contact:</p>
        <p></p>
      </div >
      <div class="right">
        <p class="emphasis">$<span id="price">${product.commodityPrice}</span></p>
        <p id="amounts">${product.commodityCount}</p>
        <!-- 地址 -->
        <p><span class="address">${store.shopAddr }</span>To
        <span class="">
        <input type="text" name="toAddr" id="toAddr"/>
		</span>
		</p>
		
		<p id="postage" class="emphasis">20 </p>
     
	    <p>
	      <button id="minus_number_button" style = "width:20px">-</button> <input id="product_number_input" type = "text" style = "width:40px;text-align:center" value="1" >
	      <button id="add_number_button" style = "width:20px">+</button>
	    </p>
		  <p>
		    <button id="contact"type = "button" class = "btn btn-default" style = "border:0px" username="${store.shopOwner.userName }">
		    	<span class="glyphicon glyphicon-user session emphasis">${store.shopOwner.userName }</span>
		    </button>
		  </p>
		  <p id="info-show">please complete the info!</p>
		  <p>
		    <button type = "button"  class = "btn btn-default  my-btn" id="buyNow">Buy Now</button>
	        <button type = "button"  class = "btn btn-default  my-btn" id="cart">Add to cart</button>
	      </p>
      </div>
  </div>

    <div class = "store-view">
    	<div class="panel panel-warning">
    		<div class="panel-heading">
        		<h3 class="panel-title">Store Information</h3>
      		</div>
      		<div class="panel-body">
	      		<div class="form-horizontal">
	      		<div class="form-group" style="margin-bottom: 5px;">
	      			<label class="col-sm-6 control-label" for="shopName">shop name :</label>
	      			<div class="col-sm-6">
						<div class="store-infor-col" id="shopName">${store.shopName }</div>
					</div>
	      		</div>
		    	<div class="form-group" style="margin-bottom: 5px;">
		    		<label class="col-sm-6 control-label" for="credit">credit :</label>
		    		<div class="col-sm-6">
		    			<div class="store-infor-col" id="credit">${store.shopRank }</div>
		    		</div>
		    	</div>
		    	<div class="form-group" style="margin-bottom: 5px;">
		    		<label class="col-sm-6 control-label" for="shopOwner">shop owner : </label>
	      			<div class="col-sm-6">
						<div class="store-infor-col" id="shopOwner">${store.shopOwner.userName }</div>
					</div>
	      		</div>
	      		<div class="form-group" style="margin-bottom: 5px;">
		    		<label class="col-sm-6 control-label" for="shopOwner">Address : </label>
	      			<div class="col-sm-6">
						<div class="store-infor-col">${store.shopAddr } </div>
					</div>
	      		</div>
	      		</div><!-- end of form -->
	      		<div>
	      			<button storeId="${store.shopNo }" type="button" class="btn btn-default  my-btn" id="enterShop" style="margin-left: 8px;">visit shop</button>
	      			<button storeId="${store.shopNo }" type="button" class="btn btn-default  my-btn" id="addFavoriteShopBtn" style="margin-left: 5px;">Add Favorite</button>
	      		</div>
      		</div><!-- end of panel body -->
    	</div>
    </div>
  </div>

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

<div class="comment">
	<div>
		<button type="button" class="result">comments</button>
		<button type="button" class="result">product detail</button>
   	</div>
	<ul class="pdt-comment">
		
	</ul>
	<div class="pdt-detail">
		<p>${commodityDetail }</p>
	</div>
</div>
<div id="pagination"></div>
<%@include file="../common/tail.html" %>
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
  </body>
  <script src="static/js/common/baseAjax.js"></script>
  <script src="static/js/common/bootstrap-modal.js"></script>
  <script src="static/js/shopping/productShow.js"></script>
  <script src="static/js/shopping/productShowConfig.js"></script>
 <script src="static/js/communicate/communicate.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script type="text/javascript">
   var _storeId = ${store.shopNo};
   var status = ${store.status};
   var userId  = ${store.shopOwner.userName};
   $(document).ready(function(){
		/* $("#addFavoriteShopBtn").click(function(){	
			location.href = "/ParknShop/customer/addFavoriteShop?shopNo="+${store.shopNo};		
		}); */
		addFavorite_json();
		/* $("#favorite").click(function(){	
			location.href = "/ParknShop/customer/addFavoriteCommidity?commodityNo="+${product.commodityNo};		
		}) */
	});
   function addFavorite_json(){
		var shopNo = ${store.shopNo};
		var commodityNo = ${product.commodityNo};
		$("#addFavoriteShopBtn").click(function(){	
			url = "/ParknShop/customer/addFavoriteShop";	
			args = {"shopNo": shopNo};
			$.get(url,args);
			alertSuccess("Succeed in adding favorite shop");
					
		});	
		$("#favorite").click(function(){	
			url = "/ParknShop/customer/addFavoriteCommidity";	
			args = {"commodityNo": commodityNo};
			$.get(url,args);
			alertSuccess("Succeed in adding favorite commodity");
		})
	}
  </script>
  
</html>