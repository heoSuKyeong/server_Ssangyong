<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex10_request</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<h1>Request</h1>
	<!-- 유입경로 -->
	<p>요청 URL: <%= request.getRequestURI() %></p>
	
	<p>요청 서버 도메인: <%= request.getServerName() %></p>
	
	<!-- GET / POST -->
	<p>요청 방식: <%= request.getMethod() %></p>
	
	<p>클라이언트 주소: <%= request.getRemoteAddr() %></p>
	
	<p>컨텍스트 경로: <%= request.getContextPath() %></p>
	
	<a href="ex09.jsp">9번 예제(상대경로)</a>
	<a href="/jsp/ex09.jsp">9번 예제(절대경로)</a>
	<!-- context root를 고정보다 가변으로 설정하는 것이 안정성이 높은 방법이다. -->
	<a href="<%= request.getContextPath() %>/ex09.jsp">9번 예제(절대경로)</a>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>