<%@page import="com.test.todo.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. DB에서 select 문으로 조회하여 ResultSet으로 가져오기
	//2. ResultSet 화면출력

	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "select * from tblTodo order by state asc, seq desc";
		
		stat = conn.createStatement();
		
		rs = stat.executeQuery(sql);
		/* 
		화면 부분에서 사용
		while(rs.next()) {
			
		}
		 */
		 
		//아래 클라이언트 부분에서 DB를 사용중이기에 여기서 닫으면 안되고 맨 밑에서 닫는다. 
		//rs.close();
		//state.close();
		//conn.close();
		
	} catch(Exception e){
		e.printStackTrace();
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="inc/asset.jsp" %>
<style>

	table td {
	
	}
	table td:nth-child(1) {
		text-align : center;
		width: 20px;
		border-right: 0;
	}
	table td:nth-child(2) {
		width: auto;
		border-left: 0;
		border-right: 0;
	}
	table td:nth-child(3) {
		text-align: center;
		width: 20px;
		border-left: 0;
	}
	
	table td:nth-child(3) > div {
		display: none;
	}
	
	table tr:hover  td:nth-child(3) > div {
		display : block;
		cursor: pointer;
	}
	
</style>
</head>
<body class="narrow">

	<%@ include file="inc/header.jsp" %>
	
	<table>
		<% while(rs.next()) {%>
		<tr>
			<td>
				<input type="checkbox" onchange="editTodo(<%= rs.getString("seq") %>);" <% if (rs.getString("state").equals("y")) {out.print("checked");} %>>
			</td>
			<td>
				<% if (rs.getString("state").equals("n")) { %>
					<div><%= rs.getString("todo")  %></div>
				
				<% } else { %>
					<div style="text-decoration: line-through;"><%= rs.getString("todo")  %></div>
				
				<% } %>
			</td>
			<td><div onclick="delTodo(<%= rs.getString("seq")%>);">&times;</div></td>
		</tr>
		<% } %>
	</table>

	<div>
		<button class="add" onclick="location.href='add.jsp';">등록하기</button>
	</div>
	
	<script>
	
		function editTodo(seq) {
			//어떤 할일을 눌렀는지 확인 > event.target 을 많이 사용하지만, 지금 상황은 DB 속 데이터를 알아야하므로 데이터를 매개변수로 넘겨준다.
			
			//alert(seq);
			
			location.href = 'editok.jsp?seq=' + seq;
			
		}
		
		function delTodo(seq) {
			
			if (confirm('delete?')){
				location.href = 'delok.jsp?seq=' + seq;
			}
		}
	
	</script>
</body>
</html>

<%
	try {
		rs.close();
		stat.close();
		conn.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
