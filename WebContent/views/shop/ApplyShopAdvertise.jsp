<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container {
	margin-top: 50px;
}
.intro {
	font-weight: bold;
	color: #FF5400;
}
.btn {
	background: #FF5400;
	color: #FFF;
}
</style>
<title>Apply Shop/Commodity Advertisement</title>
</head>
<body>
<form id="applyAdsform" class="form-horizontal" role="form">
 <div class="container">
<div class="row clearfix">
<div class="col-md-12 column">
<h4 class="intro">Apply Homepage ShopAdvertisement($20/day)</h4>
</div>
</div>
<section class="clearfix">
<div class="form-horizontal" style="float:left;width: 60%;">
<div class="form-group">
 <label class="col-sm-3 control-label" for="rateDay">adDays</label>
 <div class="col-sm-7" >
<select id="rateDay" name="rate" name="rateDay" class="form-control">
<option value="-1" style="display:none;">select your adDays</option>
<option value="1">1 months</option>
<option value="3">3 months</option>
<option value="12">1 year</option>
</select>
</div>
</div>
</div>
</section>
</div>
<div class="form-horizontal" style="float:left;width: 85%;">
<div class="form-group">

<label class="col-sm-3 control-label" for="price">Total Price</label>
 <div class="col-sm-6" >
<span id="price" name="price">$0</span>
</div>
</div>
</div>
<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<button id="submit" class="btn btn_color" type="button">Submit</button>
	</div>
</div>
</form>
<script type="text/javascript">
	 var storeId = "${storeId}";
</script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/shop/ApplyShopAdvertise.js"></script>
<script src="static/js/shop/ApplyShopAdvertiseConfig.js"></script>
</body>
</html>