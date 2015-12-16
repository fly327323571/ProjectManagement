<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>PARKnSHOP.com---Site Map</title>
	
    <meta http-equiv="keywords" content="PARKnSHOP.com,Online Mall,electronic commerce">
    <meta http-equiv="description" content="PARKnSHOP.com Online Mall|Hong Kong's Largest Online Grocery Store">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   
    
      <link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
      <link href="static/css/siteMap.css" rel="stylesheet">
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <script src="static/js/common/baseAjax.js"></script>
  </head>
  
  <body>
     <header>
		<span class="logo">PARKnSHOP</span>
		<div class="search-box">
    		<input type="search" placeholder=" Big promotion!Come & Grab!"/>
    		<button type="button" id="search">search</button>
    	</div>
	</header>
	
	<section class="container">
		<div class="customer out">
			<span>customer</span>
			<ul>
				<li class="in">
					<span class="glyphicon glyphicon-user"></span><span> as a customer</span>
					<ul>
						<li><a href="#">register</a></li>
						<li><a href="#">log in</a></li>
					</ul>
				</li>
				<li class="in">
					<span class="glyphicon glyphicon-shopping-cart"></span><span> shopping</span>
					<ul>
						<li><a href="#">search product</a></li>
						<li><a href="#">shopping cart</a></li>
						<li><a href="#">self favorite</a></li>
						<li><a href="#">Logistics query</a></li>
						<li><a href="#">comment product</a></li>
					</ul>
				</li>
				<li class="in">
					<span class="glyphicon glyphicon-wrench"></span><span> deal management</span>
					<ul>
						<li><a href="#">view order history</a></li>
						<li><a href="#">confirm the order</a></li>
						<li><a href="#">refund</a></li>
					</ul>
				</li>
				
			</ul>
		</div>
		<div class="seller out">
			<span>seller</span>
			<ul>
				<li class="in">
					<span class="glyphicon glyphicon-user"></span><span> as a seller</span>
					<ul>
						<li><a href="#">register</a></li>
						<li><a href="#">log in</a></li>
						<li><a href="#">view self shops</a></li>
					</ul>
				</li>
				<li class="in">
					<span class="glyphicon glyphicon-tasks"></span><span> shop management</span>
					<ul>
						<li><a href="#">register shop</a></li>
						<li><a href="#">manage shop link</a></li>
						<li><a href="#">modify shop detail</a></li>
						<li><a href="#">manage shop advertisement</a></li>
						<li><a href="#">view shop income</a></li>
					</ul>
				</li>
				<li class="in">
					<span class="glyphicon glyphicon-magnet"></span><span> product management</span>
					<ul>
						<li><a href="#">view products</a></li>
						<li><a href="#">add product</a></li>
						<li><a href="#">delete product</a></li>
						<li><a href="#">modify product</a></li>
					</ul>
				</li>
				<li class="in">
					<span class="glyphicon glyphicon-usd"></span><span> deal management</span>
					<ul>
						<li><a href="#">view deal</a></li>
						<li><a href="#">modify deal status</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</section>
		
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
	    				<li><a href="#">hlep page</a></li>
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
     <script type="text/javascript">
     	$(".in").bind({
     		mouseover:function(){
     		
     			$(this).css("background","rgba(133,133,133,0.2)");
     		},
	     	mouseout:
	     	function(){
	     		
	     		$(this).css("background","white");
	     	}
	     });
     </script>
  </body>
</html>
