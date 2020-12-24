<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hello World</title>
</head>
<body>
	어드민입니다.
	<section class="content">
		<%-- <tiles:insertAttribute name="header" /><hr/> --%>
		<!--  /WEB-INF/views/common/layout/header.jsp -->
		<tiles:insertAttribute name="body" /><hr/>
		<!-- body -->
		<%-- <tiles:insertAttribute name="footer" /><hr/> --%>
		<!-- /WEB-INF/views/common/layout/footer.jsp -->
	</section>
</body>
</html>