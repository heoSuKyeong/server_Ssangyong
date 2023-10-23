<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증/허가</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>인증/허가</h1>
	
	<% if(session.getAttribute("auth") == null) {%>
	<fieldset>
		<input type="button" value="로그인" onclick = "location.href='auth/login.jsp';">
	</fieldset>
	<% } else { %>
	<fieldset>
		<input type="button" value="로그아웃" onclick = "location.href='auth/logoutok.jsp';">
		<div class="message">
			<div>아이디: <%= session.getAttribute("auth") %> </div>
			<div>이름: <%= session.getAttribute("name") %> </div>
			<div>등급: <%=session.getAttribute("lv").toString().equals("1")?"일반회원":"관리자" %></div>
		</div>
	</fieldset>
	<% } %>
	
	<hr>
	
	<!-- 회원 전용 페이지  -->
	<!-- 노출 유무 방법 1 : 디스플레이 노출 유무를 -->
	<% if(session.getAttribute("auth") != null) { %>
	<div><a href="member/member.jsp">회원 페이지</a></div>
	<% } %>
	 
	<%-- 
	<!-- 노출 유무 방법 2 : 기능 차이를 둔다.-->
	<% if(session.getAttribute("auth") != null) { %>
	<div><a href="member/member.jsp">회원 페이지</a></div>
	<% } else { %>
	<!-- 회원이 아닌 사람 -->
	<!-- #!로 공수표를 만든다 -->
	<a href="#!" onclick = "alert('로그인한 회원만 접근가능합니다.');">회원 페이지</a>
	<%} %>
	--%>
	
	<!-- 관리자 전용 페이지 -->
	<% if (session.getAttribute("auth") != null && session.getAttribute("lv").toString().equals("2")) { %>
	<div><a href="admin/admin.jsp">관리자 페이지</a></div>
	<% } %>
	

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>