<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1.POST 방식
	//패킷 본문(body)안에 넣어서 전송한다.
	//자바를 통해 데이터를 전송하기에 자바를 통해 인코딩이 진행한다.
	//브라우저(UTF-8)가 인터넷을 통해 데이터 전송될 때 톰캣(UTF-8)을 거쳐 자바JSP(UTF-8)로 전송된다.
	//인터넷이 ISO-8859-1로 환경이 맞춰져있어 전송될 때 한글이 깨지는 것이다.
	//POST 방식으로 넘어 온 데이터는 무조건 인코딩을 해야한다.
	
	//2.GET 방식
	//데이터가 URL 뒤에 붙어서 전송된다.
	//넘겨지는 데이터의 인코딩의 URL 규칙에 따라 인코딩된다.
	//톰캣은 (UTF-8) 환경으로 GET방식은 인코딩 단계가 필요없다.
	
	//데이터 수신하기
	//String request.getParameter(String key)
	
	//POST 방식 수신 데이터 인코딩
	
	//request.setCharacterEncoding("UTF-8");
	
	String txt = request.getParameter("txt");
	String num = request.getParameter("num");
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

	<h1>결과</h1>
	
	<div>문자: <%=txt %></div>
	<div>숫자: <%=num %></div>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>