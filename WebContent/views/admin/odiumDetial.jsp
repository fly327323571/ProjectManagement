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

	<form action="admin//${OdiumDetial.number}/handleOdium" method="post">
		<table class="table table-striped table-bordered table-hover">
		 <colgroup align="right"></colgroup>
		<tr>
		    <td>UserName</td>
			<td><input type="text" name="userName" readonly="readonly" value="${OdiumDetial.user.userName}"/></td>
			<td>ShopName</td>
			<td><input type="text" name="shopName" readonly="readonly" value="${OdiumDetial.shop.shopName}"/></td>
			<td>GoodsName</td>
			<td><input type="text" name="commodityName" readonly="readonly" value="${OdiumDetial.commodity.commodityName}"/></td>			
		</tr>
		<tr>
		    <td>NickName</td>
			<td><input type="text" name="nickName" readonly="readonly"  value="${OdiumDetial.user.nickName}"/></td>
		    <td>ShopOwner</td>
			<td><input type="text" name="shopowner" readonly="readonly" value="${OdiumDetial.shop.shopOwner.userName}"/></td>
			<td>GoodsPrice</td>
			<td><input type="text" name="commodityPrice" readonly="readonly" value="${OdiumDetial.commodity.commodityPrice}"/></td>						
		</tr>
		<tr>			
			<td>UserTel</td>
			<td><input type="text" name="usertel" readonly="readonly" value="${OdiumDetial.user.tel}"/></td>
			<td>OwnerTel</td>
			<td><input type="text" name="ownertel" readonly="readonly" value="${OdiumDetial.shop.ownerTel}"/></td>
			<td>GoodsCategory</td>
			<td><input type="text" name="goodscategory" readonly="readonly" value="${OdiumDetial.commodity.category}"/></td>		
		</tr>

		<tr>
			<td>UserRegTime</td>
			<td><input type="text" name="userregtime" readonly="readonly" value="${OdiumDetial.user.registerTime}"/></td>
			<td>ShopRegTime</td>
			<td><input type="text" name="shopregtime" readonly="readonly" value="${OdiumDetial.shop.regTime}"/></td>
			<td>CommentsTime</td>
			<td><input type="text" name="commenttime" readonly="readonly" value="${OdiumDetial.commentsTime}"/></td>
		</tr>
		<tr>	    
			<td>Comment</td>
			<td><input type="text" name="comments" readonly="readonly" value="${OdiumDetial.comments}"/></td>
			<td>avgRank</td>
			<td><input type="text" name="avgRank" readonly="readonly" value="${OdiumDetial.commodity.avgRank}"/></td>
			<td>GoodsCount</td>
			<td><input type="text" name="volumn" readonly="readonly" value="${OdiumDetial.commodity.commodityCount}"/></td>
			
		</tr>
		<tr>
			<td>UserState</td>
			<%-- <td><input type="text" name="userstate" value="${CommentsDetial.user.state}"/></td> --%>
			<td>
			   <select name="userstate">
                        	<option value=-1> please select !</option>
                        	<option ${OdiumDetial.user.state eq 0?"selected='selected'":"" } value=0>normal</option>
                            <option ${OdiumDetial.user.state eq 1?"selected='selected'":"" } value=1>waring</option>
                            <option ${OdiumDetial.user.state eq 2?"selected='selected'":"" } value=2>blacklist</option>
               </select>		
			</td>
			<td>ShopState</td>
			<%-- <td><input type="text" name="shopstate" value="${CommentsDetial.shop.status}"/></td> --%>
			<td>   <select name="shopstate">
                        	<option value=-2> please select !</option>
                        	<option ${OdiumDetial.shop.status eq 1?"selected='selected'":"" } value=1>normal</option>
                            <option ${OdiumDetial.shop.status eq 4?"selected='selected'":"" } value=4>waring</option>
                            <option ${OdiumDetial.shop.status eq 3?"selected='selected'":"" } value=3>blacklist</option>
                   </select>
            </td>	
			<td>ShopRank</td>
			<%-- <td><input type="text" name="rank" value="${CommentsDetial.shop.shopRank}"/></td> --%>
			<td>   <select name="rank">
                        	<option  value="-3">${OdiumDetial.shop.shopRank}</option>
                        	<option  value="1">1.0</option>
                            <option  value="2">2.0</option>
                            <option  value="3">3.0</option>
                            <option  value="4">4.0</option>
                            <option  value="5">5.0</option>
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
