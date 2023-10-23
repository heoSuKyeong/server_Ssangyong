<%@page import="com.test.todo.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 데이터 수신(seq)
	//2. DB에서 delete
	//3. 사용자 피드백
	
	//1.
	String seq = request.getParameter("seq");

	//2.
	Connection conn = null;
	PreparedStatement stat = null;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "delete from tblTodo where seq = ?";
		
		stat = conn.prepareStatement(sql);
		stat.setString(1, seq);
		
		int result = stat.executeUpdate();
		
		//3.
		if (result == 1) {
			response.sendRedirect("list.jsp");
		} else {
			
			out.println("<script>");
			out.println("alert('실패')");
			out.println("location.href='list.jsp';");
			out.println("</script>");
			
		}
		
	} catch(Exception e) {
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
	
	목록보기 or 글쓰기

	
	
	<script>
	
	</script>
</body>
</html>