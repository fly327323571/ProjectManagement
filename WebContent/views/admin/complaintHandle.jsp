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
  
   <div><span style="color:#FF4400;font-size:30px;font-weight:bolder"">COMPLIANT HANDLE</span></div>
  	
   <table  class="table">
        <thead>
          <tr>       
           <th>Number</th>
           <th>Username</th>
           <th>Nickname</th>
           <th>ShopNo</th>
           <th>ShopName</th>
           <th>Complaint Reason</th>
           <th>Time</th>
           <th>Operation</th>       
          </tr>     
       </thead>
       <tbody>
           <c:forEach items="${ComplaintList}" var="person" varStatus="status">
	     <tr>          	    
          <td>${status.index+1 }</td>
          <td>${person.complaintUser.userName }</td>
          <td>${person.complaintUser.nickName }</td>
          <td>${person.complaintedShop.shopNo }</td>
          <td>${person.complaintedShop.shopName }</td>
          <td>${person.reason}</td>
          <td>${person.complaintTime }</td> 
          <td>
     	  <%--  <a href="admin/modifyShop?shopId=${person.complaintedShop.shopId }">HandleShop</a> --%>
     	   <a href="admin/complaintDetial?complaintNo=${person.complaintNo}">HandleShop</a>
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
