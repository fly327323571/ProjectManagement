<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--shop order history</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	
	<link href="static/css/shop/orderHistory.css" rel="stylesheet">
</head>
	   
<body>
<br/>
	<div class="col-sm-7" style="display: none">
		<input class="form-control" type="text" value="${storeId}" id="storeId" />
	</div>
	<div class="container">
	<div class="row clearfix">
	
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			
		<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span>
					 <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Orders:</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" id="viewByStatus">
						
						<li class="nav_option active">
							<!-- 默认 -->
							<a id="all">All</a>
						</li>
						<li class="nav_option">
							<a  id="shipped">Shipped</a>
						</li>
						<li class="nav_option">
							<a  id="unshipped">Unshipped</a>
						</li>
						<li class="nav_option">
							<a  id="done">Done</a>
						</li>
						
					</ul>
					<form class="navbar-form navbar-right" role="search">
						<div class="btn-group">
							<a class="btn dropdown-toggle" data-toggle="dropdown"href="javascript:void(0);" aria-expanded="false" title="search by"> 
								<span id="category">one month</span> 
								<span class="caret"></span> 
							</a>
							<ul id="category_choice" class="dropdown-menu">
								<!-- dropdown menu links -->
								<li><a tabindex="-1" class="category_item">one month</a>
								</li>
								<li><a tabindex="-1" class="category_item">three months</a>
								</li>
							</ul>
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="name" type="text" id="searchContent">
						</div>
						<button class="btn btn_color" type="button" id="search">Search</button>
					</form>
				</div>
		  </nav>
			
			
			<table width="84%" class="table table-hover" id="orderList">
		  </table>
		  <nav class="page">
				<div id="pagination"></div>
		  </nav>
		</div>
	</div>
			
</div>
<script type="text/template" id="subOrderTmpl">
<tr>
   <td class="t_Products"><img src="{productImage}" class="product_img"><span><a class="btn" href="{link}">{productName}</a></span></td>
   <td class="t_Original_Price"><span class="unitPrice">{price}<span></span></span></td>
   <td class="t_Quantity">{quantity}</td>
   <td class="t_totalPrice">{totalPrice}</td>
   <td class="t_message"><textarea readOnly="true" class='form-control'>{message}</textarea></td>
</tr>
</script>
<script type="text/template">

</script>
<script type="text/template" id="dialogTmpl">
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
</script>

<script type="text/template" id="trackNumberTmpl">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Express Type:</label>
	 		<div class="col-sm-7" style="margin-left: 7px;text-align: left;">
	  			<div class="btn-group">
  					<button id="expressType" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown"> Select your express type <span class="caret"></span>
  					</button>
  					<ul class="dropdown-menu express" role="menu">
    				<li><a href="javascript:void(0)" name="YTO" class="clearfix"><i style="background-position:0px -468px"></i><span>YTO</span></a></li>
    				<li><a href="javascript:void(0)" name="STO" class="clearfix"><i style="background-position:0px -1008px"></i><span>STO</span></a></li>
    				<li><a href="javascript:void(0)" name="YUNDA" class="clearfix"><i style="background-position:0px -576px"></i><span>Yun Da</span></a></li>
					<li><a href="javascript:void(0)" name="ZTO" class="clearfix"><i style="background-position:0px -324px"></i><span>ZTO</span></a></li>
					<li><a href="javascript:void(0)" name="SF" class="clearfix"><i style="background-position:0px -180px"></i><span>SF</span></a></li>
  					<li><a href="javascript:void(0)" name="EMS" class="clearfix"><i style="background-position:0px -828px"></i><span>EMS</span></a></li>
					</ul>
				</div>
	 		</div>
		</div
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Track number:</label>
	 		<div class="col-sm-7">
	  			<input class="form-control" name="trackNumber" type="text" autofocus="autofocus" required="required">
	 		</div>
		</div>
	</form>
</script>
<script type="text/template" id="refundDialogTmp">
	<p>You will refund buyer for <span style="color:#FF5400;">$ {money}</span>,are you sure to refund?</p>
</script>
<script type="text/template" id="rejectDialogTmpl">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label" style="line-height: 20px;padding-bottom: 8px;">Reason :</label>
	 		<div class="col-sm-7">
	  			<input class="form-control" name="reason" type="text" autofocus="autofocus" required="required">
	 		</div>
		</div>
	</form>
</script>
 <script src="static/js/common/baseAjax.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/common/bootstrap-modal.js"></script>
 <script src="static/js/shop/orderHistoryList.js"></script>
 <script src="static/js/shop/orderHistoryListConfig.js"></script>
</body>
</html>

