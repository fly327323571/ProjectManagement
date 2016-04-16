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
</head>


<body>
<div class="container">
			<table id="shopAdsList" class="table table-hover"></table>
			 <nav class="page">
				<div id="pagination"></div>
		  </nav>
	</div>
	
</body>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/table.js"></script>

<script src="static/js/common/bootstrap.min.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/shop/ShopAdsListStatus.js"></script>
<script src="static/js/shop/ShopAdsListStatusConfig.js"></script>
<script>
	var storeId = '${storeId}';
	
	
</script>
</html>
