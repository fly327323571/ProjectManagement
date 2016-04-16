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
	width: 100%;
	height: 80%;
}
tr {
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<th>OrderNO</th>
				<th>OrderPrice</th>
				<th>PayWay</th>
				<th>State</th>
				<th>CommodityName</th>
			</tr>
			<c:forEach items="${mapList }" var="orderDetail" varStatus="status">
				<tr>
					<td>${orderDetail.orderNo }</td>
					<td>${orderDetail.orderPrice }</td>
					<td>
						<c:if test="${orderDetail.payWay eq 0}">
							Online
						</c:if>
						<c:if test="${orderDetail.payWay eq 1}">
							Cash 
						</c:if>
					</td>
					<td>
						<c:if test="${orderDetail.state eq 1}">
							non-payment
						</c:if>
						<c:if test="${orderDetail.state eq 2}">
							non-consignment
						</c:if>
						<c:if test="${orderDetail.state eq 3}">
							apply for refund
						</c:if>
						<c:if test="${orderDetail.state eq 4}">
							refuse refund
						</c:if>
						<c:if test="${orderDetail.state eq 5}">
							consignment
						</c:if>
						<c:if test="${orderDetail.state eq 6}">
							sign
						</c:if>
						<c:if test="${orderDetail.state eq 7}">
							comments
						</c:if>
						<c:if test="${orderDetail.state eq 8}">
							over
						</c:if>
					</td>
					<td>${orderDetail.commodityName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>