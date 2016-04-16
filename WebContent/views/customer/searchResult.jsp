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
    
    <title>PARKnSHOP.com----Search Result</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script src="static/js/common/jquery-1.11.1.js"></script>
     <script src="static/js/common/bootstrap.min.js"></script>
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
      <link href="static/css/common/jpagination.css" rel="stylesheet">
	  <link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/searchResult.css" rel="stylesheet">
  </head>
   
  <body>
     
     <script>
		toolbarFloatDisable = true;
	</script>
	 <header>
    	<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
    	
    	<form class="search-box" action="">
    		<input type="search"  name="type" id="productType" placeHolder="Shopping, as easy as a click!" value="${query }"/>
    		<button type="button" id="search">search</button>
    	</form>
    	<div class="tSearch">
    		<ul>
    			<li><a href="#">Haier</a></li>
    			<li><a href="#">HuaWei</a></li>
    			<li><a href="#">oppo</a></li>
    			<li><a href="#">BMW</a></li>
    			<li><a href="#">BaoJie</a></li>
    		</ul>
    	</div>
    </header>
	<section class="content">
		<div class='order'>
   			<button type="button" class="result" id="orderByDefault" value='addTime'>Search Result</button>
   			<button type="button" class="result" id="orderBySales" value='saleVolume'>Sort By Sales</button>
   			<button type="button" class="result" id="orderByPrice" value='commodityPrice'>Sort by Price</button>
   		</div>
   		
  
   		<ul class="show">
   		<!--  
   		<li>
   		<a>
    		<img alt="">
    			<div class="describe">
			    	<span class="name"></span>
			    	<span class="price"></span>
			   </div>
   		</a></li>
   		-->
   		</ul>
   		
		<nav class="page">
			<div id="pagination"></div>
		</nav>
	</section>
	<script type="text/template" id="searchResultTmpl">
		<li>
        <a href="product/productDetail/{commodityNo}">
    		<img alt="" src="{commodityImg}" width="160px" height="160px">
    			<div class="describe">
			    	<span class="name">{commodityName}</span>
			    	<span class="price">Price:{commodityPrice}</span>
			   </div>
   		</a>
   		</li>
	</script>
		
	<%@include file="../common/tail.html" %>
	    
	
    <script src="static/js/common/baseAjax.js"></script>
    <script src="static/js/shopping/searchResult.js"></script>
     <script src="static/js/shopping/searchResultConfig.js"></script>
     <script src="static/js/common/jquery.paginate.js"></script>
     <script src="static/js/common/ajaxfileupload.js"></script>
     <script src="static/js/common/bootstrap-modal.js"></script>
     <script>
     	var	page = ${page};
     
     	var pageCount=${pageCount};
     </script>
  </body>
</html>
