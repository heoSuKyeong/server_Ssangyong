<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//POST 방식일 때 한글 인코딩
	request.setCharacterEncoding("UTF-8");

	//텍스트 박스
	//입력값이 없으면 빈문자열을 반환한다.(null 아님)
	//컨트롤x(key 오류) null 반환
	String txt1 = request.getParameter("txt1");
	
	System.out.println(txt1==null);		//false
	System.out.println(txt1.equals(""));	//true
	
	//암호 박스
	String txt2 = request.getParameter("txt2");
	
	//다중 라인 텍스트 박스
	String txt3 = request.getParameter("txt3");
	txt3 = txt3.replace("\r\n", "<br>");
	
	//체크 박스
	String cb1 = request.getParameter("cb1");
	
	//체크 박스들
	/*
	String temp="";
	
	for (int i=2; i<=4; i++){
		temp += request.getParameter("cb" + i) + ",";
	}
	*/
	
	//체크 박스들(cd5)
	//동일한 name의 컨트롤이 여려개 전송될 때
	String[] cd5 = request.getParameterValues("cd5");
	
	//라디오 버튼
	String rb = request.getParameter("rb");
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex08_ok</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>결과</h1>
	
	<h2>텍스트 박스	</h2>
	<div><%=txt1 %></div>
	
	<h2>암호 박스	</h2>
	<div><%=txt2 %></div>
	
	<h2>다중 텍스트</h2>
	<div><%=txt3 %></div>
	
	<h2>체크 박스</h2>
	<div><%=cb1 %></div>
	
<%-- 	<h2>체크 박스들</h2>
	<div><%=temp %></div> --%>

	<h2>체크 박스들</h2>
	<div><%=Arrays.toString(cd5) %></div>
	
	<h2>라디오 버튼</h2>
	<div><%=rb %></div>
	
	
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>