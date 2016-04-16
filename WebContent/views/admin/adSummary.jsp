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
			padding-left:2px;
			font-size:17px;
		
		}
	
	</style>	
	
</head>
  <body>
  
  <div class="container">
	<div class="result">
		total money from advertisement: &nbsp;&nbsp;$${money}<br/>
		active advertisement:
	</div>
     <table  class="table">
          <thead>
             <tr>
                <th>No</th>
                <th>Shop Name</th>
                <th>Commodity Name</th>
                <th>Type</th>
                <th>from time</th> 
                <th>days</th>           
             </tr>
          </thead>
           <tbody >
                  <c:forEach items="${array1}" var="commodity" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${commodity.shop.shopName}</td>
                      <td>${commodity.commodity.commodityName}</td>
                      <td>commodity</td>
                      <td>${commodity.ad_rate}</td>
                      <td>${commodity.days}</td>
                   </tr>
                 </c:forEach>
                 <c:forEach items="${array2}" var="shop" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 + array1.size()}</td>
                      <td>${shop.shop.shopName}</td>
                      <td>--</td>
                      <td>shop</td>
                      <td>${shop.ad_rate}</td>
                      <td>${shop.days}</td>
                   </tr>
                 </c:forEach>
             </tbody>
    </table>
    
   
	<nav class="page"><!-- 分页 -->
		<div id="pagination"></div>
	</nav>
	</div>
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
</body>

</html>
