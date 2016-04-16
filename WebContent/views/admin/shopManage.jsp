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
    
    <title>ParknShop--shop Manage</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet"/>
	<link href="static/css/common/jpagination.css" rel="stylesheet"/>
	<link href="static/css/user/headTail.css" rel="stylesheet"/>
	<link href="static/css/user/style.css" rel="stylesheet"/>
	<link href="static/css/admin/shopManage.css" rel="stylesheet"/>
	<link href="static/css/shopOwner/shopList.css" rel="stylesheet" type="text/css"/>
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png"/>
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png"/>
  <link rel="shortcut icon" href="img/favicon.png"/>
 
	
	
</head>
	
 
  
  <body>
  
  <div class="container">
  	<nav class="navbar navbar-default" role="navigation">
		<!-- <div class="navbar-header">
			 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span>
			 <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand">Sort by:</a>
		</div> -->
		
		
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<!-- <ul class="nav navbar-nav">
				<li class="nav_option">
					<a href="javascript:void(0);" id="orderByRegTime">Register time<span id="reg_time_caret" class="caret" style="display:none;"></span></a>
				</li>
			</ul> -->
			
			<%-- <div class="status-style">
				<span id="status_span">Status:</span>
				<div class="btn-group">
				  <a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
				    <span id="status">All</span>
				    <span class="caret"></span>
				  </a>
				  <ul id="status_choice" class="dropdown-menu">
				    <!-- dropdown menu links -->
				      <li><a tabindex="-1" class="status_item">All</a></li>
				      <c:forEach var="status" items="${shopStatus }">
				      	<li><a tabindex="-1" class="status_item">${status}</a></li>
					  </c:forEach>
				  </ul>
				  <!-- 待修改 -->
				</div>
			</div> --%>
			
			<form action="admin/shopSearch" class="navbar-form navbar-right" role="search" method="post">
			<%-- 	<div style="float:left;">
				<span id="category_span">Category:</span>
				<div class="btn-group">
				  <a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
				    <span id="category">All</span>
				    <span class="caret caret_style"></span>
				  </a>
				  <ul id="category_choice" class="dropdown-menu">
				    <!-- dropdown menu links -->
				      <li><a tabindex="-1" class="category_item">All</a></li>
				      <c:forEach var="category" items="${categories }">
				      	<li><a tabindex="-1" class="category_item">${category }</a></li>
					  </c:forEach>
				  </ul>
				</div>
			</div> --%>
				<div class="form-group">
					<input id="store_name" class="form-control" name="shopname" placeholder="store name" type="text">
				</div>
				<input id="search" class="btn btn_color" type="submit" value="Search">
			</form>
		</div>
	</nav>
     <table  class="table">
          <thead>
             <tr>
                <th>No</th>
                <th>ShopNo</th>
                <th>ShopName</th>
                <th>ShopCategories</th>
                <th>ShopRank</th>
                <th>QwnerTel</th>             
                <th>ShopAddr</th>
                <th>Register time</th>
                <th>Operation</th>               
             </tr>
          </thead>
           <tbody >
                  <c:forEach items="${ShopList}" var="person" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${person.shopNo }</td>
                      <td>${person.shopName}</td>
                      <td>${Categories[person.shopCategories]}</td>
                      <td>${person.shopRank}</td>
                      <td>${person.ownerTel}</td>
                      <td>${person.shopAddr}</td>
                      <td>${person.regTime}</td> 
                      <td>                 	  
                  	     <a href="admin/handleShop?shopNo=${person.shopNo }">&nbsp;&nbsp;&nbsp;Handle</a>            	   
                      </td>                    	
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
<script type="text/template" id="detailTmpl">
	<div class="dialog-block">
		<p class="dialog-block-title">Shop owner<p>
	<div class="clearfix bs-callout bs-callout-warning">
	<form class="form-horizontal" style="float: left;width: 80%;">
    	<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">User name</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="userName" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Real name</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="realName" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Shop No</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="idCard" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Phone number</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="phoneNumber" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Email</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="email" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
	</form>
	<div style="float: left;width: 20%;margin-left: -47px;">
		<img src="" alt="" name="portrait" style="width:180px;height:180px;"/>
    </div>
	</div>
	</div><!-- end of block-->
	<!--start of block-->
	<div class="dialog-block">
		<p class="dialog-block-title">Shop informations<p>
	<div class="clearfix bs-callout bs-callout-info">
	<form class="form-horizontal" style="float: left;width: 80%;">
    	<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Shop name</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="storeName" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
    	<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Category</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="category" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
    	<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Register time</label>
	 		<div class="col-sm-7">
	  		<input class="form-control" name="registerTime" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;">
	 		</div>
    	</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Remarks</label>
	 		<div class="col-sm-7">
	  		<textarea class="form-control" name="remark" type="text" autofocus="autofocus" readonly="true" style="background-color: white;cursor: text;"></textarea>
	 		</div>
    	</div>
	</form>
	<div style="float: left;width: 20%;margin-left: -47px;">
		<img src="" alt="" name="logo" style="width:180px;height:180px;"/>
    </div>
	</div>
	</div><!-- end of block-->
</script>
  </body>
 <!-- <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/common/bootstrap-modal.js"></script>
 <script src="static/js/admin/shopManage.js"></script>
 <script src="static/js/admin/shopManageConfig.js"></script> -->
</html>
