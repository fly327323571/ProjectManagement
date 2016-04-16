<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    <title>ParknShop--shop detail</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--append â#!watchâ to the browser URL, then refresh the page. -->
	

	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	
	<link href="static/css/shop/addProduct.css" rel="stylesheet">
</head>
	
<body>
<div class="container">
     
      <table width="700" align="center">
	<tr>
	<td>

	<form action="admin/${currentshop.shopNo}/editShop" method="post">
		<table class="table table-striped table-bordered table-hover">
		 <colgroup align="right"></colgroup>
		<tr>
			<td>Shop No</td>
			<td><input type="text" name="shopNo" readonly="readonly" value="${currentshop.shopNo}"/></td>
			<td>Shop Name</td>
			<td><input type="text" name="shopName" readonly="readonly" value="${currentshop.shopName}"/></td>
		</tr>
		<tr>
		    <td>Shop Rank</td>
			<td><input type="text" name="shopRank" value="${currentshop.shopRank}"/></td>
			<td>Shop Owner</td>
			<td><input type="text" name="" readonly="readonly"  value="${currentshop.shopOwner.userName}"/></td>			
		</tr>
		<tr>
			
			<td>Owner Telephone</td>
			<td><input type="text" name="ownerTel" value="${currentshop.ownerTel}"/></td>
			<td>Shop Categories</td>
			<%-- <td><input type="text" name="shopCategories"  readonly="readonly" value="${Categories[currentshop.shopCategories]}"/></td> --%>
			<td>
			    <select name="shopCategories">
                        <option value=0> please select !</option>
                        <option ${currentshop.shopCategories eq 0?"selected='selected'":"" } value=0>Food</option>
                        <option ${currentshop.shopCategories eq 1?"selected='selected'":"" } value=1>Clothes</option>
                        <option ${currentshop.shopCategories eq 2?"selected='selected'":"" } value=2>Tools</option>
                 </select>
			
			</td>

		</tr>
		<tr>
			<td>Shop Address</td>
			<td><input type="text" name="shopAddr" value="${currentshop.shopAddr}"/></td>
			<td>Shop Description</td>
			<td><input type="text" name="shopDesc" value="${currentshop.shopDesc}"/></td>
		</tr>
		<tr>
			<td>Shop Sourse</td>
			<%-- <td><input type="text" name="shopSourse" value="${currentshop.shopSourse}"/></td> --%>
			<td>    <select name="shopSourse">
                        <option value=0> please select !</option>
                        <option ${currentshop.shopSourse eq 0?"selected='selected'":"" } value=0>SelfSale</option>
                        <option ${currentshop.shopSourse eq 1?"selected='selected'":"" } value=1>OfflineSale</option>
                        <option ${currentshop.shopSourse eq 2?"selected='selected'":"" } value=2>Distribution</option>
                        <option ${currentshop.shopSourse eq 3?"selected='selected'":"" } value=3>Undefined</option>
                 </select>
            </td>
			<td>Status</td>
			<%-- <td><input type="text" name="status" value="${currentshop.status}"/></td> --%>
			 <td>    <select name="status">
                        <option value=1> please select !</option>
                        <option ${currentshop.status eq 1?"selected='selected'":"" } value=1>Normal</option>
                        <option ${currentshop.status eq 4?"selected='selected'":"" } value=4>Waring</option>
                        <option ${currentshop.status eq 3?"selected='selected'":"" } value=3>BlackList</option>
                 </select>
            </td>
		</tr>
		<tr>
			<td>Remarks</td>
			<td><input type="text" name="remarks" value="${currentshop.remarks}"/></td>
			<td>RegTime</td>
			<td><input type="text" name="" readonly="readonly" value="${currentshop.regTime}"/></td>
		</tr>
		<tr>
		    
		    <td>
			<input type="submit" value="save"/>	&nbsp;
			</td>
			  <td></td>
			<td >
			
			</td>
		</tr>

		</table>
	</form>

	</td>
	</tr>
	</table>


</div>
	
		

</body>
<script src="static/js/common/jquery-1.11.1.js"></script>
<script src="static/js/common/bootstrap.min.js"></script>
<script src="static/js/common/bootstrap-modal.js"></script>
<script src="static/js/common/baseAjax.js"></script>
<script src="static/js/common/validator.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script src="static/js/product/addProduct.js"></script>
<script src="static/js/product/addProductConfig.js"></script>
<script type="text/javascript">
	
	var storeId = '${storeId}';
	var result = '${result.result}';
	if(result) {
		//alert(result);
		$("#dialog #tip").html(result);
		$("#dialog").modal();
	}
	
</script>
</html>
