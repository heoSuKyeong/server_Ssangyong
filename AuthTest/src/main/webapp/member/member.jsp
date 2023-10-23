<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	//인증받지 못한 사용자가 직접 접근하면 내쫓아야한다.
	//아래 body영역은 회원만 보는 페이지이기에 인증 검증을 할 때는 보통 이 영역에서 한다.
	if (session.getAttribute("auth") == null) {
		//response.sendRedirect("../index.jsp");	// 바로 내쫓는건 불친절해보임
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<script>");
		out.println("alert('회원만 접근 가능합니다.');");
		out.println("location.href='../index.jsp';");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		out.close();	// 아래의 코드를 진행하지 않고 여기서 중단한다.
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

	<h1>회원 전용 페이지</h1>

	<p>이 페이지는 회원만 접근 가능합니다.</p>
	


	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>