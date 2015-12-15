<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">  
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	
    <!--用这个的话，modal对话框可以弹出  -->
   <!--  <link href="static/css/common/bootstrap.css" rel="stylesheet"> -->
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/shop/productList.css" rel="stylesheet">
</head>


<body>
<br>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">

				<form class="navbar-form navbar-right" role="search" style="margin-right: 0px;">
					<div class="btn-group">
						<a class="btn dropdown-toggle" data-toggle="dropdown"
							href="javascript:void(0);" aria-expanded="false" title="search by"> <span
							id="_search_by">product name</span> <span class="caret"></span> </a>
						<ul id="search_by" class="dropdown-menu">
							<!-- dropdown menu links -->
							<li><a tabindex="-1" class="category_item">product name</a>
							</li>
							<li><a tabindex="-1" class="category_item">product id</a>
							</li>
						</ul>
					</div>
					<div class="form-group">
						<input id="keyword" class="form-control" placeholder="product name" type="text">
					</div>
					<div id="search" class="btn btn_color">Search</div>
				</form></div>
			</nav>
			<table id="productList" class="table table-hover">
				
			</table>
			<div id="pagination"></div>
			
			
	</div>
</div>
<script type="text/template" id="dialogTmpl">
<div class="modal" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 800px;">     
	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Warm Prompt</h4>
      </div>
      <div class="modal-body" style="text-align:center;line-height:4;">
        <p>tip</p>	
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn_color" id="delete">Delete</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
   	  </div>
   	  </div>
	</div>
</div>
</script>
<script type="text/template" id="warnDeleteDialogTmpl">
	<p>There are {count} order(s) waited to be shipped, are you sure to delete this product?</p>
	<div style="color:#888;font-size:small;">if you are going to delete it, the buyer will be refunded,
and money will be returned to the buyer.</div>
</script>
</body>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/table.js"></script>

<script src="static/js/common/bootstrap.min.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/product/productList.js"></script>
<script src="static/js/product/productListConfig.js"></script>
<script>
	var storeId = '${storeId}';
	
	
</script>
</html>
