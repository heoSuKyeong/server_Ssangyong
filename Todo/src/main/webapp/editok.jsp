<%@page import="com.test.todo.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 데이터 수신(seq)
	//2. DB에 update 하기
	//3. 사용자 피드백
	
	//1.
	String seq = request.getParameter("seq");

	//2.
	Connection conn = null;
	PreparedStatement stat = null;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "";
		
		sql = "select state from tblTodo where seq = ?";
		
		stat = conn.prepareStatement(sql);
		stat.setString(1, seq);
		
		ResultSet rs = stat.executeQuery();
		String state = "";
		
		if (rs.next()) {
			state = rs.getString("state");
		}
		
		if (state.equals("n")){
			state = "y";		
		} else {
			state = "n";
		}
		
		sql = "update tblTodo set state = ? where seq = ?";
		
		stat = conn.prepareStatement(sql);
		stat.setString(1, state);
		stat.setString(2, seq);

		int result = stat.executeUpdate();
		
		//3. 사용자 피드백(자바로 구성하기)
		if (result == 1) {
			response.sendRedirect("list.jsp");
		} else {
			out.println("<script>");
			out.println("alert('failed');");
			out.println("location.href='list.jsp';");
			out.println("</script>");
		}
		
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
	
	목록보기 or 글쓰기

	
	
	<script>
	
	</script>
</body>
</html>