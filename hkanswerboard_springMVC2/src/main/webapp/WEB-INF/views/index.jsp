<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="container">
<h1>메인화면</h1>
<a href="anscontroller.jsp?command=boardlist">글목록</a>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>