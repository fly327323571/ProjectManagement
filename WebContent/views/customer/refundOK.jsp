<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com----Refund Result</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/customer/refundOK.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
  </head>

<body>
<section class="site-nav">
    	<div class="log-info">
    		<span>Welcome to PARKnSHOP.com!  <span class="glyphicon glyphicon-user sn"></span></span>
	    	<a href="login.html" class="sn-log">log in</a>
	    	<a href="customerRegister.html" class="sn-register">register</a>
    	</div>
    	<div class="quick-menu">
    		
    		<a href="customerHomepage.html" class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span>  my homepage</a>
    		<a href="#" class="sn-cart"><span class="glyphicon glyphicon-shopping-cart sn"></span>  my cart</a>
    		<a href="#" class="sn-favorite"><span class="glyphicon glyphicon-heart sn"></span>  my favorite</a>
    		<a href="#" class="sn-help"><span class="glyphicon glyphicon-star sn"></span>  to be ShopOwner</a>
    		<a href="#" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a>
    		<a href="siteMap.html" class="sn-help"><span class="glyphicon glyphicon-globe sn"></span>  site map</a>
    	</div>
    </section>
     <header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
	</header>
	<div class="container">
		<div class="row clearfix">
			<div class="background">
				<img src="static/images/tick.png" class="tick_img"/>&nbsp;<span class="title_color title_size">Request for Refund submitted Successfully</span>
				<br/><br/>
				Remaining time:&nbsp;<span class="main_color">48:00:00</span>
				<br/>
				<ul>
					
					<li>If the seller agreed the refund requesting or not reply in two days, this refund action success</li>
					<li>If two sides negotiated no results, you can apply site administrator for processing.</li>
				</ul>
				<button class="btn btn_color" type="button" onclick="window.close();">OK</button>
			</div>
		</div>
	</div>
		
	<footer>
	    	<div class="concept">
		    	<ul>
		    		<li><span>easy</span></li>
		    		<li><span>cheap</span></li>
		    		<li><span>safe</span></li>
		    	</ul>
	    	</div>
	    	<div class="tail">
	    		<div>
	    			<ul>
	    				<li><span>about us</span></li>
	    				<li><a href="#">our homepage</a></li>
	    			</ul>
	    		</div>
	    		<div>
	    			<ul>
	    				<li><span>contact us</span></li>
	    				<li><a href="Mailto://764586144@qq.com">Email</a></li>
	    				<li><a href="tencent://message/?uin=764586144&Site=http://qbar.qq.com/application/&Menu=yes">Tencent QQ</a></li>
	    				<li><a href="http://weibo.com/u/3750216917">Sina weibo</a></li>
	    			</ul>
	    		</div>
	    		<div>
	    			<ul>
	    				<li><span>our service</span></li>
	    				<li><a href="#">on-line sale</a></li>
	    				<li><a href="#">physical store</a></li>
	    				<li><a href="#">after-sales serves</a></li>
	    			</ul>
	    		</div>
	    		<div>
	    			<ul>
	    				<li><span>how to use it</span></li>
	    				<li><a href="#">help page</a></li>
	    			</ul>
	    		</div>
	    		<div>
	    			<ul>
	    				<li><span>link</span></li>
	    				<li><a href="http://glyphicons.com/">glyphicon</a></li>
	    			</ul>
	    		</div>
	    	</div>
	    </footer>
</body>
</html>