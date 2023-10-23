<%@page import="com.test.todo.DBUtil"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//addok.jsp
	//1. 데이터 수신하기
	//2. DB 연결해서 insert 작업
	//3. 사용자에게 피드백(선택사항)
	//4. list.jsp으로 이동

	//1. 데이터 수신하기
	//POST로 받았기때문에 인코딩을 한다.
	request.setCharacterEncoding("UTF-8");

	String todo = request.getParameter("todo");

	//2. DB 연결해서 insert 작업
	Connection conn = null;
	PreparedStatement stat = null;
	int result = 0;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "insert into tblTodo(seq, todo, state, regdate) values (seqTodo.nextVal, ?, default, default)";
		
		stat = conn.prepareStatement(sql);
		stat.setString(1, todo);
		
		result = stat.executeUpdate();
		
		stat.close();
		conn.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="inc/asset.jsp" %>
<style>

</style>
</head>
<body class="narrow">

	<%@ include file="inc/header.jsp" %>
	
	<%-- <div><%= result %></div> --%>
	<%--
	3. 사용자 피드백 첫번째 방법
	<% if (result ==1) { %>
	<div class="message">할일을 등록했습니다.</div>
	<div><a href="list.jsp">목록보기</a></div>
	<%} else { %>
	<div class="message">할일 등록을 실패했습니다.</div>
	<div><a href="add.jsp">돌아가기</a></div>
	<%} %>
	 --%>
	
	<script>
	/* 3. 사용자 피드백 두번째 방법 : alert 창으로 안내한다. (요즘에는 최소한의 클릭을 위해 성공시 alert창을 잘 안띄운다.) */
		<% if (result ==1) { %>
			//alert('할일을 등록했습니다.');
			location.href='list.jsp';
		<%} else { %>
			alert('할일 등록을 실패했습니다.');
			location.href='list.jsp';
		<%} %>
	
	</script>
</body>
</html>