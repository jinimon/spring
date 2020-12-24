<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert.jsp</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<a href="empinsertForm">사원 등록/수정</a>
	<form:form action="empInsert" method="post" modelAttribute="emp">
		<c:if test="${not empty emp.employeeId}">
			<form:input path="employeeId" readonly="true" />
			<br>
		</c:if>
		<spring:message code="emp.firstName" htmlEscape="false" />
		<form:input path="firstName" placeholder="firstName" />
		<br>
		<form:input path="lastName" placeholder="lastName" />
		<!-- errors의 path는 EmpValidator의 필드명과 일치해야 한다. -->
		<form:errors path="lastName" cssClass="error" htmlEscape="false" />
		<br>
		<form:input path="email" placeholder="email" />
		<form:errors path="email" cssClass="error" htmlEscape="false" />
		<br>
		<form:select path="jobId">
			<form:option value="">job 선택</form:option>
			<form:options items="${jobs}" itemLabel="jobTitle" itemValue="jobId" />
		</form:select>
		<br>
		<form:errors path="jobId" cssClass="error" htmlEscape="false" />
		<br>
		<form:radiobuttons path="departmentId" items="${departments}"
			itemLabel="departmentName" itemValue="departmentId" />
		<br>
		<form:input path="hireDate" placeholder="hireDate" />
		<br>
		<form:input path="salary" placeholder="salary" />
		<br>
		<form:button>등록</form:button>
	</form:form>
</body>
</html>