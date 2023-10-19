<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//입력 받은 데이터를 다른 페이지에서도 사용하고 싶을 때
	//첫번째 페이지에서 데이터를 생성하여 두번째 페이지로 전달한다.
	
	//데이터의 종류
	//1. 지역변수 > 페이지간 공유되지않음
	int a = 10;
	
	//3. pageContext 객체 > 실패
	pageContext.setAttribute("c", 30);
	
	//4. request > pageContext에선 가능, 가장 간단한 방법이다.
	request.setAttribute("d", 40);
	
	//5. session 객체 > 가능
	session.setAttribute("e", 50);
	
	//6. application 객체 > 가능
	application.setAttribute("f", 60);

	response.sendRedirect("ex19_scope_2.jsp");
	//pageContext.forward("ex19_scope_2.jsp");
	
	//서버끼리 데이터를 전송할 때, request + forward 조합으로 사용한다.
	

%>
<%!
	//2. 멤버 변수 > 페이지간 공유되지않음
	int b = 20;

	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex19_scope_1</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	
	<h1>첫번째 페이지</h1>
	
	<!-- 다른 페이지를 호출 다양한 방법 -->

	<a href="ex19_scope_2.jsp?a=<%=a%>">두번째 페이지</a>

	<hr>
	
	<input type="button" value="두번째 페이지" id="btn1">
	
	<hr>
	
	<!-- 단순한 이동이 아니라 데이터를 넘긴다는 의미가 강하다. -->
	<form method="GET" action="ex19_scope_2.jsp">
		<input type="hidden" name="a" value="<%=a %>">
		<input type="submit" value="두번째 페이지">
	</form>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
		$('#btn1').click(function() {
			location.href = 'ex19_scope_2.jsp?a=<%=a%>';
		})
	</script>
</body>
</html>