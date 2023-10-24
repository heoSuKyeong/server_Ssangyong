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
			<th>이름</th>
			<td><input type="text" name="name" required value=${dto.name}></td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" name="pw" required></td>
		</tr>
		<tr>
			<th>메모</th>
			<!-- textarea 사이에 공백을 무조건 인식하기때문에 화면에서도 인식된다. -->
			<td><textarea name="memo" required class="full">${dto.memo}</textarea></td>
		</tr>
	</table>
	<div>
		<input type="button" value="돌아가기" onclick="location.href='/memo/list.do';">
		<input type="submit" value="수정하기">
	</div>
	<input type="hidden" name="seq" value="${dto.seq}">
	</form>
	
	<script>
	
	</script>
</body>
</html>