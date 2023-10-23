<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp" %>

<style>

</style>
</head>
<body>
	
	<%@ include file = "/WEB-INF/views/inc/header.jsp" %>
	
	<!-- 가상주소의 /는 html이나 javascript에서는 root context가 있어야 한다. -->
	<form method="POST" action="/memo/addok.do">
	<table class="vertical">
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" required></td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" name="pw" required></td>
		</tr>
		<tr>
			<th>메모</th>
			<td><textarea name="memo" required class="full"></textarea></td>
		</tr>
	</table>
	<div>
		<input type="button" value="돌아가기" onclick="location.href='/memo/list.do';">
		<input type="submit" value="쓰기">
	</div>
	</form>
	
	<script>
	
	</script>
</body>
</html>