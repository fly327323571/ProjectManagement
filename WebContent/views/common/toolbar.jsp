<%@page import="cn.xidian.parknshop.beans.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="static/js/common/toolbar.js"></script>
<%
Object userObj = session.getAttribute("user");
if(userObj == null){
%>
	<!-- Guest -->
    <section class="site-nav">
    	<div class="log-info">
    		<span>welcome to<a id="quickTohomepage">PARKnSHOP.com!</a>  <span class="glyphicon glyphicon-user sn"></span></span>
	    	<a id="login" class="sn-log">log in</a>
	    	<a id="__register" class="sn-register">register</a>
    	</div>
    	<div class="quick-menu">
    		
    		<a href="." class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span>  my homepage</a>
    		<a href="javascript:void(0)" class="sn-cart"><span class="glyphicon glyphicon-shopping-cart sn"></span>  my cart</a>
    		<a href="javascript:void(0)" class="sn-favorite"><span class="glyphicon glyphicon-heart sn"></span>  my favorite</a>
    		<a id="shopOwnerRegister" class="sn-help"><span class="glyphicon glyphicon-star sn"></span>  to be ShopOwner</a>
    		<a href="javascript:void(0)" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a>
    		<a href="siteMap.do" class="sn-help"><span class="glyphicon glyphicon-globe sn"></span>  site map</a>
    	</div>
    </section>
<% }else{
	User user = (User)userObj;
	String userType = user.getEmail();
	String userName = user.getUserName();
	if(userType.equals(user.getEmail())){
%>
	<!-- Customer -->
    <section class="site-nav">
    	<div class="log-info">
    		<span>Welcome to<a id="quickTohomepage">PARKnSHOP.com!</a></span>
    		<a href="user/myHomepage.do" class="sn-user"><span class="glyphicon glyphicon-user sn"></span><%=userName %></a>
	    	<a id="logout" class="sn-log">log out</a>
    	</div>
    	<div class="quick-menu">
    		
    		<a href="javascript:void(0)" class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span>  my homepage</a>
    		<a href="javascript:void(0)" class="sn-cart"><span class="glyphicon glyphicon-shopping-cart sn"></span>  my cart</a>
    		<a href="javascript:void(0)" class="sn-favorite"><span class="glyphicon glyphicon-heart sn"></span>  my favorite</a>
    		<a id="shopOwnerRegister" class="sn-help"><span class="glyphicon glyphicon-star sn"></span>  to be ShopOwner</a>
    		<a href="javascript:void(0)" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a>
    		<a href="siteMap.html" class="sn-help"><span class="glyphicon glyphicon-globe sn"></span>  site map</a>
    	</div>
    </section>
    
<%  }if(userType == user.getEmail()){//Shop Owner%>

	<!-- Shop Owner -->
    <section class="site-nav">
    	<div class="log-info">
    		<span>welcome to<a id="quickTohomepage">PARKnSHOP.com!</a></span>
			<a href="user/myHomepage.do" class="sn-user"><span class="glyphicon glyphicon-user sn"></span><%=userName %></a>
	    	<a id="myStores" class="sn-register">my stores</a>
	    	<a id="logout" class="sn-log">log out</a>
    	</div>
    	<div class="quick-menu">
    		<a href="javascript:void(0)" class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span> my homepage</a>
    		<a href="javascript:void(0)" class="sn-cart"><span class="glyphicon glyphicon-shopping-cart sn"></span>  my cart</a>
    		<a href="javascript:void(0)" class="sn-favorite"><span class="glyphicon glyphicon-heart sn"></span>  my favorite</a>
    		<a href="javascript:void(0)" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a>
    		<a href="siteMap.html" class="sn-help"><span class="glyphicon glyphicon-globe sn"></span>  site map</a>
    	</div>
    </section>
<% } if(userType ==user.getEmail()){//admin %>
	<!-- Admin -->
    <section class="site-nav">
    	<div class="log-info">
    		<span>welcome to<a id="quickTohomepage">PARKnSHOP.com!</a></span>
			<a href="admin/adminHomepage.do" class="sn-user"><span class="glyphicon glyphicon-user sn"></span><%=userName %></a>
	    	<a href="javascript:void(0)" class="sn-log">my profile</a>
	    	<a id="logout" class="sn-log">log out</a>
    	</div>
    	<div class="quick-menu">
    		<a href="javascript:void(0)" class="sn-homepage"><span class="glyphicon glyphicon-home sn"></span>  homepage</a>
    		<a href="javascript:void(0)" class="sn-help"><span class="glyphicon glyphicon-question-sign sn"></span>  help</a>
    		<a href="siteMap.html" class="sn-help"><span class="glyphicon glyphicon-globe sn"></span>  site map</a>
    	</div>
    </section>
<%	
	}
}
%>

