<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="userInsert" method="post" encType="multipart/form-data">
		<!-- name : uservo에 있는 필드명과 동일하게 -->
		아이디<input type="text" name="id"><br />
		패스워드<input type="text" name="password"><br />
		이름<input type="text" name="name"><br />
		
		권한
		<input type="radio" name="role" value="Admin">Admin
		<input type="radio" name="role" value="User">User
		<!-- uploadFile의 파일명만 추출해서 테이블의 profile 칼럼에 넣을거다. -->
		<!-- type="file"가 있으면 method="post"야 되고
			 encType="multipart/form-data"도 해줘야함 -->
		첨부파일<input type="file" name="uploadFile" /><br />
		<input type="submit" value="저장">
	</form>
</body>
</html>