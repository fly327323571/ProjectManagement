<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <table  width="98%" style="text-align: center;align=center;">
             <thead>
                   <tr align="center" valign="middle">
                      
                      <th width="50px">序号</th><th>买家</th><th>卖家</th><th>投诉原因</th><th>处理结果</th>
                        
                   </tr>
                   
             </thead>
             <tbody id="group_one">
                  <c:forEach items="${applyList}" var="person" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td> 
                      <td>${person.complaintedShop.shopName }</td>
                      <td>${person.complaintUser.userId }</td>
                      <td>${person.reason}</td>                                       
                      <td>${person.handleResult }</td>	
                    
                   </tr>
                 </c:forEach>
             </tbody>
         </table>

</body>
</html>