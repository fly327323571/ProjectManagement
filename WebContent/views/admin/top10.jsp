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
    <title>ParknShop--business Transaction</title>  
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet"/>
	<link href="static/css/common/jpagination.css" rel="stylesheet"/>
	<link href="static/css/user/headTail.css" rel="stylesheet"/>
	<link href="static/css/user/style.css" rel="stylesheet"/>
	<link href="static/css/admin/shopManage.css" rel="stylesheet"/>
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css"/>
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png"/>
  <link rel="shortcut icon" href="img/favicon.png"/>
 
	<style type="text/css">
		.result{
			font-weight:bold;
			padding-left:30px;
			font-size:17px;
		
		}
	
	</style>	
	
</head>
  <body>
  
  <div class="container">
     <table  class="table">
          <thead>
             <tr>
                <th>No.</th>
                <th>Commodity Name</th>
                <th>Commodity Id</th>
                <th>Shop Name</th>
                <th>Shop Id</th>
                <th>Sum Price</th>               
             </tr>
          </thead>
           <tbody >
                  <c:forEach items="${array}" var="commodity" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${commodity.commodityName}</td>
                      <td>${commodity.commodityId}</td>
                      <td>${commodity.shopName}</td>
                      <td>${commodity.shopId}</td>
                      <td>${commodity.sumPrice}</td>
                   </tr>
                 </c:forEach>
             </tbody>
    </table>
	</div>
</body>
</html>
