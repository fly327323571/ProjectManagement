<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--Customer Manage</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/admin/customerManage.css" rel="stylesheet"/>
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
 
	
	
</head>
	
 
  
  <body>
  
  <div class="container">
  	<nav class="navbar navbar-default" role="navigation">
		<!-- <div class="navbar-header">
			 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span>
			 <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand">Sort by:</a>
		</div> -->
		
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<%-- 	<ul class="nav navbar-nav">
				<li class="nav_option">
					<a href="javascript:void(0);" id="orderByRegTime">Register time<span id="reg_time_caret" class="caret" style="display:none;"></span></a>
				</li>
			</ul>
			<div class="status-style">
				<span id="status_span">Status:</span>
				<div class="btn-group">
				  <a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
				    <span id="status">All</span>
				    <span class="caret"></span>
				  </a>
				  <ul id="status_choice" class="dropdown-menu">
				    <!-- dropdown menu links -->
				      <li><a tabindex="-1" class="status_item">All</a></li>
				      <c:forEach var="status" items="${userStatus }">
				      	<li><a tabindex="-1" class="status_item">${status}</a></li>
					  </c:forEach>
				  </ul>
				  <!-- 待修改 -->
				</div>
			</div> --%>
			<form action="admin/customerSearch" class="navbar-form navbar-right" role="search">
				<div class="form-group">
					<input id="user_name" class="form-control" placeholder="nick name" name="nickname" type="text">
				</div>
				<input id="search" class="btn btn_color" type="submit" value="Search">
			</form>
		</div>
	</nav>
    <table  class="table">
          <thead>
             <tr>
                <th>number</th>
                <th>username</th>
                <th>nickname</th>
                <th>email</th>
                <th>phone</th>
                <th>address</th>
                <th>register time</th>
                <th>operation</th>               
             </tr>
          </thead>
           <tbody >
                  <c:forEach items="${Customerlist}" var="person" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${person.userName }</td>
                      <td>${person.nickName}</td>
                      <td>${person.email}</td>
                      <td>${person.tel}</td>
                      <td>${person.address}</td>
                      <td>${person.registerTime}</td> 
                      <td>                 	  
                  	    <a href="admin/handleCustomer?userName=${person.userName}">&nbsp;&nbsp;Handle</a>         	   
                      </td>                    	
                   </tr>
                 </c:forEach>
             </tbody>
    </table>
	<nav class="page">
		<div id="pagination"></div>
	</nav>
	</div>
  </body>
<!--  <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script> -->
 <!-- <script src="static/js/admin/customerManage.js"></script> -->
<!--  <script src="static/js/admin/customerManageConfig.js"></script> -->
</html>
