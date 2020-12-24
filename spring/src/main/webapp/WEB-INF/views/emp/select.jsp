<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select.jsp</title>
</head>
<body>
	<h1>사원 조회</h1>
	<c:forEach items="${list}" var="emp">
		<a href="empUpdateForm?employeeId=${emp.employeeId}">${emp.employeeId}</a>
		${emp.firstName}
		${emp.lastName}<br>
	</c:forEach>
</body>
</html>