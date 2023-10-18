<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex13_out</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>out</h1>
	
	<% int dan = 2; %>
	<!-- 성능문제가 아닌 개인 스타일 문제 -->
	
	<h2>구구단 - 스트립틀릿 + 표현식</h2>
	
	<% for (int i=1; i<=9; i++) { %>
	<div><%= dan %> X <%=i %> = <%= i*dan %></div>
	<% } %>
	
	<!-- 중간에 끊지않고 온전한 자바스타일의 for문을 작성할 수 있다 -->
	<!-- 예전 방식으로 출력을 지원한다. -->
	<h2>구구단 - 스트립틀릿 + out</h2>
	<%
		for(int i=1; i<=9; i++) {
			out.println(String.format("<div>%d X %d = %d</div>", dan, i, dan*i));
		}
	%>
	
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>