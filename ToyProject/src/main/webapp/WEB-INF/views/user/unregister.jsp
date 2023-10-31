<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<main id="main">
		<h1>회원<small>탈퇴하기</small></h1>
		
		<form method="POST" action="/toy/user/unregister.do">
		<div>
			<button type="button" class="back" onclick="location.href='/toy/index.do';">돌아가기</button>
			 <!-- 로그인 정보가 세션에 저장되어있으므로 내가 누구인지 전송을 하지 않아도 괜찮다
			 POST로 전송하기 위해 form태그를 사용했다. -->
			<button type="submit" class="out primary">탈퇴하기</button>
		</div>
		</form>
		
		
	</main>
	
	<script>
	
	</script>
</body>
</html>