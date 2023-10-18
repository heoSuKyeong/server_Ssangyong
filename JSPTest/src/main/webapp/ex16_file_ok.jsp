<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	request.setCharacterEncoding("UTF-8");
	
	//<from enctype="multipart/form=data">을 적용하여 인코딩 방식을 변경하면
	//1. request.getParameter() 동작 하지 않는다.
	//2. request.getParameterValues() 동작 하지 않는다.
	//String name = request.getParameter("name");
	//String age = request.getParameter("age");
	
	//업로드 된 파일을 저장할 로컬 경로가 필요하다(C:\..)
	//C:\class\code\server\JSPTest\src\main\webapp\files
	//실제 응용프로그램이 동작할 때 아래 경로처럼 지정한다.
	//C:\class\code\server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPTest\files
	
	// 슬래시는 webapp를 의미한다.
	//웹에서 쓰는 상대경로나 절대경로를 로컬 경로로 변환해준다.
	String path = application.getRealPath("/files");
	
	/* System.out.println(path); */
 
	//업로드 파일의 최대 크기 지정
	//바이트 단위
	int size = 1024*1024*100;	//100MB
	
	//변수 선언
	String name="";			//이름
	String age="";			//나이
	String filename="";		//첨부파일명
	String orgfilename="";	//첨부파일명
	
	//request > MultipartRequest > 객체 생성
	//MultipartRequest 객체를 만드는 순간 이미 첨부파일은 files 복사가 완료된다.
	//new DefaultFileRenamePolicy() : 파일명관리해주는 메소드(파일명 중복이 되면 넘버링을 해준다.)
	try {
		MultipartRequest multi = new MultipartRequest(
									request,	//원래 request
									path,		//파일 업로드 위치
									size,		//최대 파일 크기
									"UTF-8",	//파일 데이터 인코딩
									new DefaultFileRenamePolicy()
				);
		
		//데이터 수신
		name = multi.getParameter("name");
		age = multi.getParameter("age");
		
		
		//업로드한 파일 정보를 가져온다.(파일명)
		//<input type="file" name="attach">
		filename = multi.getFilesystemName("attach");		//파일명(저장된 파일명)
		orgfilename = multi.getOriginalFileName("attach");	//파일명(원본 파일명)
		
		
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex16_file_ok</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>결과</h1>

	<div>
		이름: <%= name %>
	</div>
	<div>
		나이: <%= age %>
	</div>
	<div>
		파일명: <%= filename %>
	</div>
	<div>
		파일명: <%= orgfilename %>
	</div>
	
	<h2>파일 다운로드</h2>
	
	<!-- 아래 코드의 장점 : 간단하다 / 단점 : 파일 확장자 마다 다르다.(다운로드 또는 뷰어로 뜬다.) -->
	<%-- <div>
		<a href="/jsp/files/<%= filename %>"><%= orgfilename %></a>
	</div> --%>
	
	<!-- download : 모든 파일을 무조건 다운로드 시키는 옵션 -->
	<!-- 장점 : 간단하다 / 단점 : 넘버링된 파일은 파일명이 달라진다. -->
	<%-- <div>
		<a href="/jsp/files/<%= filename %>" download><%= orgfilename %></a>
	</div> --%>

	<!-- 장점: 무조건 다운로드가 되고, 원본 파일명으로 다운도드가 된다.
	단점 : download.jsp를 작성하는 비용이 든다. -->
	<div>
		<a href="download.jsp?filename=<%= filename %>&orgfilename=<%= orgfilename %>"><%= orgfilename %></a>
	</div>
	
	
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>