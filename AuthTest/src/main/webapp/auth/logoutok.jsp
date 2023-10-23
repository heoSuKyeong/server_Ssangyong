<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 로그아웃 처리
	//2. 피드백
	
	//1.
	//인증 후는 인증 티켓을 소유하고 있는 상태
	//인증 전은 인증 티켓이 없는 상태
	//로그아웃이란건 인증 전 상태로 돌아가는 것
	
	session.removeAttribute("auth");	//로그아웃
	
	response.sendRedirect("../index.jsp");
	

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>