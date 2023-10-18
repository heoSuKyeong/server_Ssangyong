<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//페이지 이동
	//1. HTML에서
	//<a href="">
	//사용자가 클릭을 해야 이동한다.
	
	//2. JavaScript에서
	//location.href=''>
	//호출할 수 있는 상황을 마음대로 통제할 수 있다.
	
	//3. Servlet/JSP
	//response.sendRedirect(URL)
	//JavaScript 방법과 비슷하다
	//서버 코드
	
	response.sendRedirect("ex11_response_2.jsp");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex11_response_1</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>첫번째 페이지</h1>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>