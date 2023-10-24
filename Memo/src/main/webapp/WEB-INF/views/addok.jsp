<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file = "/WEB-INF/views/inc/asset.jsp" %>

<style>

</style>
</head>
<body>
	
	<%@ include file = "/WEB-INF/views/inc/header.jsp" %>
	
	<script>
		<%-- 
		<% if ((int)request.getAttribute("result") == 1) { %>
		location.href = '/memo/list.do';
		<% } else { %>
		alert('실패');
		location.href = '/memo/add.do';
		<% } %> 
		--%>
		
		<c:if test ="${result == 1}">
			location.href = '/memo/list.do';
		</c:if>
		<c:if test ="${result != 1}">
			alert('실패');
			//location.href = '/memo/add.do'; //location.href는 페이지를 새로 요청한다.(컨트롤의 입력값이 리셋된다.)
			history.back();		//작성했던 페이지 그대로 되돌아간다.(컨트롤의 입력값도 복구된다.) 보안이 높은 페이지는 location을 사용한다.
		</c:if>
	
	</script>
</body>
</html>