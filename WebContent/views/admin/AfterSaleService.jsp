<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin-top: 1%;
	width: 100%;
	height: 80%;
}
td {
	text-align: center;
}
</style>
</head>
<body>
<div>
	<table>
		<tr>
			<th>UserName</th><th>ServiceType</th><th>Resource</th><th>OrderDetail</th><th>Deal</th>
		</tr>
		<c:forEach items="${map }" var="afterservice" varStatus="status">
			<tr>
				<td>${afterservice.userName }</td>
				<td>
					<c:if test="${afterservice.serviceType eq '1' }">Exchange</c:if>
					<c:if test="${afterservice.serviceType eq '0' }">Refund</c:if>
				</td>
				<td>${afterservice.reason }</td>
				<td><a href="/ParknShop/afterSaleService/detail?commodityNo=${afterservice.commodityNo }&userName=${afterservice.userName}">Detail</a></td>
				<td>
					<a href="/ParknShop/afterSaleService/approve?serviceNo=${afterservice.serviceNo }&serviceType=${afterservice.serviceType}">Approve</a>
					<a href="/ParknShop/afterSaleService/disapprove?serviceNo=${afterservice.serviceNo }&serviceType=${afterservice.serviceType}">Disapprove</a>
				</td>
			</tr>
			
		</c:forEach>
	</table>
</div>
</body>
</html>