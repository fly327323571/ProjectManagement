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
  	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<form action="admin/businessTransaction" class="navbar-form navbar-right" method="post">
				<div class="form-group">
					<input id="store_name" class="form-control" name="shopName" placeholder="store name" type="text"/>
				</div>
				<input id="search" class="btn btn_color" type="submit" value="Search"/>
			</form>
		</div>
	</nav>
	<div class="result">
		shopName: &nbsp;&nbsp;${shopName} |&nbsp;&nbsp;shop Transaction: &nbsp;&nbsp; ${shopTransaction}
	</div>
     <table  class="table">
          <thead>
             <tr>
                <th>No</th>
                <th>Order Id</th>
                <th>Shop Name</th>
                <th>Buyer Name</th>
                <th>Price</th>               
             </tr>
          </thead>
           <tbody >
                  <c:forEach items="${transactionDetail}" var="order" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${order.orderNo}</td>
                      <td>${order.shop.shopName}</td>
                      <td>${order.buyer.userName}</td>
                      <td>${order.orderPrice}</td>
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
