<%@page import="com.test.auth.DBUtil"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 데이터 수신(id, pw)
	//2. DB에서 select 하기 (해당 id, pw를 가지고 있는 회원을 조회)
	//3.1 존재하면 > 인증 티켓 발급
	//3.2 존재 하지않으면 > 아무일 발생하지 않는다.
	//4. 피드백
	
	//1.
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	//2.
	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	try {
		
		conn = DBUtil.open();
		
		String sql = "select * from tblUser where id = ? and pw = ?";
		
		stat = conn.prepareStatement(sql);
		stat.setString(1, id);
		stat.setString(2, pw);
		
		rs = stat.executeQuery();
		
		if (rs.next()) {
			System.out.println("로그인 성공");
			//해당 구문에서 로그인 처리를 끝낸다. 다른 곳에서 작성하지 않는다.
			//로그인을 성공했을 때, 인증 티켓을 발급한다. 세션안에 로그인을 성공했다는 표시를 저장한다.(세션은 전역변수로써 개인 공간에 저장된다.)
			//세션은 서버 메모리를 차지하기 때문에 필요한 정보만 session 객체에 넣어야한다.
			session.setAttribute("auth", id);	// 인증 티켓 역할
			
			session.setAttribute("name", rs.getString("name"));
			session.setAttribute("lv", rs.getString("lv"));
			
			response.sendRedirect("../index.jsp");
			
		} else {
			System.out.println("로그인 실패");
			response.sendRedirect("login.jsp");
		}
		
		rs.close();
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
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>