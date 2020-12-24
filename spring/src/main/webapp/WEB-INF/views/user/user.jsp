<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user.jsp</title>
</head>
<body>
	${user.id}<br>
	${user.password}<br>
	${user.name}<br>
	${user.role}<br>
	${user.profile}<br>
	<!-- href="filedown" : request mapping url 확인 -->
	<a href="filedown?uFile=${user.profile}">파일 다운</a>
	<img src="images/${user.profile}"><br>
</body>
</html>