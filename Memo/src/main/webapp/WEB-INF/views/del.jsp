<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp" %>

<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
<style>

</style>
</head>
<body>
	
	<%@ include file = "/WEB-INF/views/inc/header.jsp" %>
	
	<form method="POST" action="/memo/editok.do">
	<table class="vertical">
		<tr>
			<th>암호</th>
			<td><input type="password" name="pw" required></td>
		</tr>
	</table>
	<div>
		<input type="button" value="돌아가기" onclick="location.href='/memo/list.do';">
		<input type="submit" value="삭제하기">
	</div>
	<!-- Del.java에서 setAttribute로 넘긴 변수명 잘 확인하기 -->
	<input type="hidden" name="seq" value="${seq}">
	</form>
	
	<script>
	
	</script>
</body>
</html>