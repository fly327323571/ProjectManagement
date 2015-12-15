<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Boolean flag=(session.getAttribute("user")==null);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<base href="<%=basePath%>">

<title>PARKnSHOP.com</title>

<meta http-equiv="keywords"
	content="PARKnSHOP.com,Online Mall,electronic commerce">
<meta http-equiv="description"
	content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">


<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
<link href="static/css/user/headTail.css" rel="stylesheet">
<link href="static/css/homepage.css" rel="stylesheet">
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<script type="javascript">

</script>
</head>

<body class="clearfix">
	<section class="easy-nav">
	<ul>
		<li><a href="#" class="sn-help"><span
				class="glyphicon glyphicon-question-sign sn"></span> help</a>
		</li>
		<li><a href=""
			class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span>
				home</a>
		</li>
		<li><a href="WEB-INF/views/myCart.html" class="sn-cart"><span
				class="glyphicon glyphicon-shopping-cart sn"></span> cart</a>
		</li>
		<li><a href="WEB-INF/views/myFavorite.html" class="sn-favorite"><span
				class="glyphicon glyphicon-heart sn"></span> favorite</a>
		</li>
		<li><a href="#" id="goTop"><span
				class="glyphicon glyphicon-chevron-up sn"></span>
		</a>
		</li>
	</ul>
	</section>
	
	
	<header> <span class="logo">PARKnSHOP</span>

	<div class="search-box">
		<input type="search" placeholder=" Big promotion!Come & Grab!" id="query" name="query"/>
		<button type="button" id="search">search</button>
	</div>
	<div class="tSearch">
		<ul>
			<li><a href="#">Haier</a>
			</li>
			<li><a href="#">HuaWei</a>
			</li>
			<li><a href="#">oppo</a>
			</li>
			<li><a href="#">BMW</a>
			</li>
			<li><a href="#">BaoJie</a>
			</li>
		</ul>
	</div>
	</header>
	<section class="content">
	<div class="top">
		<ul>
			<li></li>
			<li></li>
			<li class="type">Clothes</li>
			<li class="type">Furniture</li>
			<li class="type">Phone</li>
			<li class="type">Computer</li>
			<li class="type">Cosmetics</li>
			<li></li>
			<li></li>
		</ul>
	</div>

	<section class="adt">
	<div class="pdt-type">

		<ul>
			<li>Product Categories</li>
			<li><a href="#">TV & Home Theater</a>
			</li>
			<li><a href="#">Computers & Tablets</a>
			</li>
			<li><a href="#">Cell Phone</a>
			</li>
			<li><a href="#">Cameras & Camcorders</a>
			</li>
			<li><a href="#">Audio</a>
			</li>
			<li><a href="#">Car Electronics & GPS</a>
			</li>
			<li><a href="#">Video,Games,Movies & Music</a>
			</li>
			<li><a href="#">Health,Fitness & Sports</a>
			</li>
			<li><a href="#">Home & Office</a>
			</li>
<!-- 			<li><img src="static/images/ypic.jpg" -->
<!-- 				style="width:200px;height:75px;" /> -->
<!-- 			</li> -->
		</ul>
	</div>
	<div class="pdt-adt">
		<div id="myCarousel" class="carousel slide">
			<!--    轮播（Carousel）指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="static/images/pdt1.png" alt="First slide">
				</div>
				<div class="item">
					<img src="static/images/pdt2.png" alt="Second slide">
				</div>
				<div class="item">
					<img src="static/images/pdt3.png" alt="Third slide">
				</div>
			</div>
			<!--    轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left arrow"></span> </a> <a
				class="carousel-control right" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right arrow"></span> </a>
		</div>
	</div>

	</section> <section class="list">
	<div class="left">
		<div style="
    font-size: 16px;
    border-bottom: 2px solid #ff5400;
    margin-left: 20px;
    background-color: #FF5400;
    padding: 12px 4px;
    color: white;
    font-weight: bold;
    margin-bottom: 6px;margin-top: 0px;
">Top 10 products
			      
		</div>
		<div class="pdt-list" style="
    background-color: #f5f5f5;
    margin-left: 20px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
		">
			<div>
				<img alt="" src="static/images/pdtS1.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS2.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS3.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS4.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS5.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS6.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS7.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
<!-- 			<DIV> -->
<!-- 				<IMG ALT="" SRC="STATIC/IMAGES/PDTS8.PNG"> -->
<!-- 					<DIV> -->
<!-- 						<SPAN CLASS="PDT-NAME">CUP</SPAN> <BR /> -->
<!-- 						<SPAN CLASS="PDT-SHOP">HAPPYHOME</SPAN> <SPAN CLASS="PDT-PRICE">$20</SPAN> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
			<div>
				<img alt="" src="static/images/pdtS9.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS10.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS11.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>
			<div>
				<img alt="" src="static/images/pdtS12.png">
					<div>
						<span class="pdt-name">cup</span> <br />
						<span class="pdt-shop">HappyHome</span> <span class="pdt-price">$20</span>
					</div>
			</div>

		</div>
	</div>
	<div class="right">

		<!-- <img alt="" src="static/images/girl.png"> -->
		<div class="shop-logo">Shop</div>
		<div style="text-align: center;
width: 76%;
height: 43px;
line-height: 43px;
background-color: #ff5400;
color: white;
font-size: 16px;
font-weight: bold;">Top 10 shops</div>
	<div class="shop-list">
		<ul>
			<li><a href="#"><img src="static/images/logo-fg.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-twitter.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-bocoup.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-adobe.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-bitovi.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-jquery.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-saucelabs.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/logo-modernizr.jpg" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/shopLogo8.png" />
			</a>
			</li>
			<li><a href="#"><img src="static/images/shopLogo4.png" />
			</a>
			</li>
			
			
		</ul>
		<a href="#" class="seeMore">See more...</a>
</div>
	</div>


	</section> </section>
	<%@include file="WEB-INF/views/common/tail.html" %>
	<script src="static/js/homepage.js"></script>
	<script src="static/js/homepageConfig.js"></script>
<script type="text/template" id="productAdTmpl">
	<div link='{link}'>
		<img alt="" src="{logo}">
		<div>
			<span class="pdt-name">{productName}</span> <br>
			<span class="pdt-shop">{storeName}</span> <span class="pdt-price">$ {price}</span>
		</div>
	</div>
</script>
<script type="text/template" id="shopAdTmpl">
<li>
<a href="{link}"><img src="{logo}">
</a>
</li>
</script>
</body>
</html>
