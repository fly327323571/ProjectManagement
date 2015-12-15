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
    
    <title>PARKnSHOP.com----Shop Homepage</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/shop/shopHomepage.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <link href="static/css/common/jpagination.css" rel="stylesheet">
  </head>
 
  
  <body class="clearfix">
  		<section class="easy-nav">
  		<ul>
  			<li><a href="#" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a></li>
  			<li><a href="#" class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span> home</a></li>
  			<li><a href="#" class="sn-cart"><span class="glyphicon glyphicon-shopping-cart sn"></span> cart</a></li>
  			<li><a href="#" class="sn-favorite"><span class="glyphicon glyphicon-heart sn"></span> favorite</a></li>
  			<li><a href="#" id="goTop"><span class="glyphicon glyphicon-chevron-up sn"></span></a></li>
  		</ul>
  	</section>
  	
  	 <%@include file="../common/toolbar.jsp" %>
	 <header>
    	<span class="logo">PARKnSHOP</span>
    	
    	<form class="search-box" action="">
    		<input type="search" placeholder=" Big promotion!Come & Grab!" name="type"/>
    		<button type="submit" id="search">search</button>
    	</form>
    	<!-- <div class="tSearch">
    		<ul>
    			<li><a href="#">Haier</a></li>
    			<li><a href="#">HuaWei</a></li>
    			<li><a href="#">oppo</a></li>
    			<li><a href="#">BMW</a></li>
    			<li><a href="#">BaoJie</a></li>
    		</ul>
    	</div> -->
    </header>
    
    <div id="myCarousel" class="carousel slide">
		<!--    Carousel  -->
	   <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      <li data-target="#myCarousel" data-slide-to="1"></li>
	      <li data-target="#myCarousel" data-slide-to="2"></li>
	   </ol>   
	   <!--Carousel -->
	   <div class="carousel-inner">
	      <div class="item active">
	         <img src="static/images/shopPdt1.png" alt="First slide">
	      </div>
	      <div class="item">
	         <img src="static/images/shopPdt2.png" alt="Second slide">
	      </div>
	      <div class="item">
	         <img src="static/images/shopPdt3.png" alt="Third slide">
	      </div>
	   </div>
		<!--    Carousel -->
	   <a class="carousel-control left" href="#myCarousel" 
	      data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left arrow"></span>
	      <!-- <img class="arrow" src="static/images/lArrow.png"/> -->
	   </a>
	   <a class="carousel-control right" href="#myCarousel" 
	      data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right arrow"></span>
	      <!-- <img class="arrow" src="static/images/rArrow.png"/> -->
	   </a>
	</div>  
	
	<section class="list">
    		<div class="left">
    			<div>
    				<label class="pdt-logo">B</label>
    				<label class="pdt-logo">I</label>
    				<label class="pdt-logo">G</label>
    				<label class="pdt-logo">S</label>
    				<label class="pdt-logo">A</label>
    				<label class="pdt-logo">L</label>
    				<label class="pdt-logo">E</label>
    			</div>
    			<div class="pdt-list">
    				
	    		</div>
	    		<div id="pagination"></div>
    		</div>
	</section>
	
	<section class="link-shop">
		<div>
			<span class="ls-logo">popular SHOPS</span>
			<!-- <img alt="" src="static/images/ls-logo.png"> -->
			<p style="display:inline-block;margin-left:50px;">click any shop link below,you can come into the store you're worth it!</p>
		</div>
		<ul>
	    			<li><a href="WEB-INF/views/shop/shopHomepage.html"><img src="static/images/shopLogo1.png"/></a></li>
	    			<li><a href="WEB-INF/views/shop/shopHomepage.html"><img src="static/images/shopLogo2.png"/></a></li>
	    			<li><a href="WEB-INF/views/shop/shopHomepage.html"><img src="static/images/shopLogo3.png"/></a></li>
	    			<li><a href="WEB-INF/views/shop/shopHomepage.html"><img src="static/images/shopLogo4.png"/></a></li>
	    </ul>
	</section>
	
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
<script type="text/template" id="productShelfTmpl">
<div>
    <a href="{link}">
	   <img alt="" src="{defaultImage}">
	   <div>
	    <span class="pdt-name">{productName}</span>
	    <span class="pdt-price">{presentPrice}</span>
	   </div>
    </a>
 </div>
</script>
<script type="text/template" id="shopLinkTmpl">
<li><a href="{link}"><img src="{logo}"></a></li>
</script>
 <script type="text/javascript">
   var storeId = ${storeId};
   var status = ${store.status};
  </script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/shop/shopHomepage.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/shop/shopHomepageConfig.js"></script>
<script src="static/js/common/jquery.paginate.js"></script>
  </body>
</html>
