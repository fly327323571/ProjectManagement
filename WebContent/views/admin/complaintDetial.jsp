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

	<form action="admin//${ComplaintDetial.complaintNo}/handleComplaint" method="post">
		<table class="table table-striped table-bordered table-hover">
		 <colgroup align="right"></colgroup>
		<tr>
		    <td>UserName</td>
			<td><input type="text" name="userName" readonly="readonly" value="${ComplaintDetial.complaintUser.userName}"/></td>
			<td>ShopName</td>
			<td><input type="text" name="shopName" readonly="readonly" value="${ComplaintDetial.complaintedShop.shopName}"/></td>	
		</tr>
		<tr>
		    <td>NickName</td>
			<td><input type="text" name="nickName" readonly="readonly"  value="${ComplaintDetial.complaintUser.nickName}"/></td>
		    <td>ShopOwner</td>
			<td><input type="text" name="shopowner" readonly="readonly" value="${ComplaintDetial.complaintedShop.shopOwner.userName}"/></td>	
		</tr>
		<tr>			
			<td>UserTel</td>
			<td><input type="text" name="usertel" readonly="readonly" value="${ComplaintDetial.complaintUser.tel}"/></td>
			<td>OwnerTel</td>
			<td><input type="text" name="ownertel" readonly="readonly" value="${ComplaintDetial.complaintedShop.ownerTel}"/></td>	
		</tr>

		<tr>
			<td>UserRegTime</td>
			<td><input type="text" name="userregtime" readonly="readonly" value="${ComplaintDetial.complaintUser.registerTime}"/></td>
			<td>ShopRegTime</td>
			<td><input type="text" name="shopregtime" readonly="readonly" value="${ComplaintDetial.complaintedShop.regTime}"/></td>
		</tr>
		<tr>	    
			<td>Reason</td>
			<td><input type="text" name="reason" readonly="readonly" value="${ComplaintDetial.reason}"/></td>
			    <td>ShopRank</td>
		     <td>   
		           <select name="rank">
                        	<option  value="-3">${ComplaintDetial.complaintedShop.shopRank}</option>
                        	<option  value="1">1.0</option>
                            <option  value="2">2.0</option>
                            <option  value="3">3.0</option>
                            <option  value="4">4.0</option>
                            <option  value="5">5.0</option>
                   </select>
            </td>
		</tr>
		<tr>
			<td>UserState</td>
			<%-- <td><input type="text" name="userstate" value="${CommentsDetial.user.state}"/></td> --%>
			<td>
			   <select name="userstate">
                        	<option value=-1> please select !</option>
                        	<option ${ComplaintDetial.complaintUser.state eq 0?"selected='selected'":"" } value=0>normal</option>
                            <option ${ComplaintDetial.complaintUser.state eq 1?"selected='selected'":"" } value=1>waring</option>
                            <option ${ComplaintDetial.complaintUser.state eq 2?"selected='selected'":"" } value=2>blacklist</option>
               </select>		
			</td>
			<td>ShopState</td>
			<%-- <td><input type="text" name="shopstate" value="${CommentsDetial.shop.status}"/></td> --%>
			<td>   <select name="shopstate">
                        	<option value=-1> please select !</option>
                        	<option ${ComplaintDetial.complaintedShop.status eq 1?"selected='selected'":"" } value=1>normal</option>
                            <option ${ComplaintDetial.complaintedShop.status eq 4?"selected='selected'":"" } value=4>waring</option>
                            <option ${ComplaintDetial.complaintedShop.status eq 3?"selected='selected'":"" } value=3>blacklist</option>
                   </select>
            </td>	
		</tr>
		<tr> 
		     <td>HandleResult</td>
		     <td>
		          <select name="handleResult">
                        	<option ${ComplaintDetial.handleResult eq 0?"selected='selected'":"" } value="0">Select !</option>                    	
                            <option ${ComplaintDetial.handleResult eq 1?"selected='selected'":"" } value="1">Negotiate</option>
                            <option ${ComplaintDetial.handleResult eq 2?"selected='selected'":"" } value="2">Buyer take charge</option>
                            <option ${ComplaintDetial.handleResult eq 3?"selected='selected'":"" } value="3">Seller take charge</option>
                   </select>	     
		     </td>
		 
            <td>Truth</td>
			<%-- <td><input type="text" name="complaint_view" value="${ComplaintDetial.complaint_view}"/></td> --%>
			<td>
			      <select name="complaint_view">
                        	<option ${ComplaintDetial.handleResult eq 0?"selected='selected'":"" } value="0">Select !</option>
                        	<option ${ComplaintDetial.handleResult eq 1?"selected='selected'":"" } value="1">Falsity</option>
                            <option ${ComplaintDetial.handleResult eq 2?"selected='selected'":"" } value="2">Reality</option>
                   </select>
			
			</td>
		</tr>	
		<tr>
		   
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
