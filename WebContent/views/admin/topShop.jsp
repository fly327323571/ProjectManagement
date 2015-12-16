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
    
    <title>ParknShop--Top Shop</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="static/js/common/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="static/js/common/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="static/css/common/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="static/css/admin/topShop.css" />
	<link rel="stylesheet" type="text/css" href="static/css/common/jpagination.css" />
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
  		<div class="topShop-main-container">
	  		<nav class="navbar navbar-default navbar-style" role="navigation">
				<div class="topShop-navbar-left">
					Shop Advertisement:
				</div>
				
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
					      <c:forEach var="status" items="${adverStatus }">
					      	<li><a tabindex="-1" class="status_item">${status}</a></li>
						  </c:forEach>
					  </ul>
					  <!-- 待修改 -->
					</div>
				</div>
				
				<div class="add_product">
					<button class="btn btn-style add-shop">add Shop</button>
				</div>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input id="shop-name" class="form-control" placeholder="shop name" type="text">
					</div>
					<button id="search" class="btn btn-style" type="button">Search</button>
				</form>
			</nav>
			<div class="topProduct-list">
				<table id="topShop-list" class="table"></table>
				<nav class="page">
					<div id="pagination"></div>
				</nav>
			</div>
		</div>
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
		<script type="text/template" id="detailTmpl">
			<div class="dialog-block">
				<p class="dialog-block-title">Shop Advertisement<p>
				<div class="clearfix bs-callout bs-callout-warning">
					<form class="form-horizontal" style="float: left;width: 80%;">
						<div class="form-group">
							<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Shop id</label>
	 						<div class="col-sm-7">
	  							<input class="form-control shopId" name="title" type="text" autofocus="autofocus" style="background-color: white;cursor: text;">
	 						</div>
    					</div>
    					<div class="form-group">
							<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Title</label>
	 						<div class="col-sm-7">
	  							<input class="form-control" id="title" name="title" type="text" autofocus="autofocus" style="background-color: white;cursor: text;">
	 						</div>
    					</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Store Name</label>
	 						<div class="col-sm-7">
	  							<input class="form-control" id="store-name" name="store-name" type="text" autofocus="autofocus"  style="background-color: white;cursor: text;">
	 						</div>
    					</div>
						
					</form>
					<div id="_shopImage" style="float: left;width: 20%;margin-left: -47px;">
							<img src="" alt="" name="portrait" style="width:180px;height:180px;"/>
							<div class="btn btn-default" style="position: relative; width: 120px; height:34px; margin-top:10px;overflow: hidden;">
 								<div>Select File</div>
								<input type="file" id="logo" name="myfiles" accept="image/*">
							</div>
   		 				</div>
				</div>
				
			</div>
		</div><!-- end of block-->
	 </script>
  </body>
 <script src="static/js/common/ajaxfileupload.js"></script>
 <script src="static/js/common/bootstrap-modal.js"></script>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/admin/topShop.js"></script>
 <script src="static/js/admin/topShopConfig.js"></script>
</html>
  