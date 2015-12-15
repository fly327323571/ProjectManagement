<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
   <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com----Product Comment</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
      <link href="static/css/customer/headCommon.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/productShow.css" rel="stylesheet">
	<link href="static/css/customer/comment.css" rel="stylesheet">
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
  
  
  <div class = "main" >
    <div class = "view">
	    <img src="${product.defaultImage}" class="img-thumbnail">
	   
    </div>
    
    <div class = "product-view">
      
      <h3 class = "pro-title" >${product.productName}</h3>
      <div style = "width:100px;float:left">
        <p>Price:</p>
        
        <p>Postage:</p>
        
        <p></p>
      </div >
      <div class="right">
        <p class="emphasis">$<span id="price">${product.presentPrice}</span></p>
		<p id="postage" class="emphasis">${product.amounts}</p>
      </div>
  </div>

    
  </div>
	
<div class="comment">
	
	<div>
		<button type="button" class="result">comments</button>
   	</div>
	
<div class="commentdetail">
<div class="top">
<font size="4" >Another buyers needs your comment</font>
</div>


<form>
<div class="left">
<div class="productcomment">comment</div>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="consistent" name="consistent" value=""/>
<input type="hidden" id="service" name="service" value=""/>
<input type="hidden" id="delivery" name="delivery" value=""/>
<textarea id="comment" type="textarea" class="producttext" name="comment"></textarea>
</div>

<div class="right">

<li id="consistentPoint">
<div class="choosestar">

Consistent description:&nbsp;&nbsp;
</div > 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 

</li>
<li id="servicePoint">
<div class="choosestar">

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Service attitude:&nbsp;&nbsp;
</div >
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
</li>

<li id="deliveryPoint">
<div class="choosestar">

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Delivery speed:&nbsp;&nbsp;
</div class="choosestar">
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
 <span class="glyphicon glyphicon-star star"  ></span> 
</li>

</ur>
</div>
<div class="bottom">
<button type = "button" id="submit" class = "btn btn-default  my-btn"  style="margin-left:46%;">Comment</button>
</div>

</form>
</div>


</div>


  <%@include file="../common/tail.html" %>
  </body>
  <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/customer/comment.js"></script>
 <script src="static/js/customer/commentConfig.js"></script>
  
  
</html>