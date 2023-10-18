<%@page import="com.test.jsp.MyMath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//멤버 변수인가 지역 변수인가
	int a = 10;	//지역변수

	//메서드를 생성할 수 없다 > 이미 어떤 메서드의 진영이기 때문에
	/*
	public void test() {
		
	}
	*/
	
	MyMath m = new MyMath();
	
%>
<%!
	int b = 20;
	
	public int sum(int a, int b) {
		return a+b;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div><%=10 + 20 %></div>
	<div><%=m.sum(10,20) %></div> 

</body>
</html>