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
				<th>orderNO</th>
				<th>orderPrice</th>
				<th>payWay</th>
				<th>state</th>
				<th>commodityName</th>
			</tr>
			<c:forEach items="${mapList }" var="orderDetail" varStatus="status">
				<tr>
					<td>${orderDetail.orderNo }</td>
					<td>${orderDetail.orderPrice }</td>
					<td>${orderDetail.payWay }</td>
					<td>${orderDetail.state }</td>
					<td>${orderDetail.commodityName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>