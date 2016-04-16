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

	<form action="admin/${currentuser.userName}/editCustomer" method="post">
		<table  class="table table-striped table-bordered table-hover">
		 <colgroup align="right"></colgroup>
		<tr>
		    
			<td>UserName</td>
			<td><input type="text" name="userName" readonly="readonly" value="${currentuser.userName}"/></td>
			<td>Time</td>
			<td><input type="text" name="" readonly="readonly" value="${currentuser.registerTime}"/></td>
					
		</tr>
		<tr>
		    <td>NickName</td>
			<td><input type="text" name="nickName"  value="${currentuser.nickName}"/></td>	
			<td>Password</td>
			<td><input type="text" name="password" value="${currentuser.password}"/></td>
		    						
		</tr>
		<tr>			
			<td>Telephone</td>
			<td><input type="text" name="tel" value="${currentuser.tel}"/></td>
			<td>Address</td>
			<td><input type="text" name="address"  value="${currentuser.address}"/></td>
		</tr>
		<tr>
		    <td>Email</td>
			<td><input type="text" name="email"  value="${currentuser.email}"/></td>
			<td>State</td>
			<%-- <td><input type="text" name="state" value="${currentuser.state}"/></td> --%>
			<td> 
			     <select name="state">
                        	<option value=0> please select !</option>
                        	<option ${currentuser.state eq 0?"selected='selected'":"" } value=0>normal</option>
                            <option ${currentuser.state eq 1?"selected='selected'":"" } value=1>waring</option>
                            <option ${currentuser.state eq 2?"selected='selected'":"" } value=2>blacklist</option>
                 </select>
			</td>
			
		</tr>
		
		<tr>
		    <td></td>
		    <td></td>
		    <td>
			<input type="submit" value="Handle"/>
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
