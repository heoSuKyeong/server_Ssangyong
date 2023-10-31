<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	#form-list {
		display: flex;
	}
	#form-list form {
		margin-right: 5px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<main id="main">
		<h1 class="sub">회원 <small>로그인</small></h1>
		<form method="POST" action="/toy/user/login.do">
		<table class="vertical">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" id="id" required class="short"></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="pw" id="pw" required class="short"></td>
			</tr>
		</table>
		
		<div>
			<button type="button" class="back" onclick="location.href='/toy/index.do';">돌아가기</button>
			<button type="submit" class="login primary">로그인하기</button>
		</div>
		
		</form>
		
		<hr>
		
		<!-- 프로젝트시 유용한 방법 : 버튼으로 한번에 로그인하기-->
		<div id="form-list">
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="susukkang">
			<input type="hidden" name="pw" value="1234">
			<button type="submit" class="login primary">수수깡</button>
		</form>
		
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="test">
			<input type="hidden" name="pw" value="1111">
			<button type="submit" class="login primary">테스트</button>
		</form>
		
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="admin">
			<input type="hidden" name="pw" value="1111">
			<button type="submit" class="login primary">관리자</button>
		</form>
		</div>
	</main>
	
	<script>
	
	</script>
</body>
</html>