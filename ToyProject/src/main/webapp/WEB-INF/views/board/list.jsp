<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

	#list th:nth-child(1) {width: 40px;}
	#list th:nth-child(2) {width: auto;}
	#list th:nth-child(3) {width: 70px;}
	#list th:nth-child(4) {width: 120px;}
	#list th:nth-child(5) {width: 50px;}

	#list td {text-align: center;}
	#list td:nth-child(2) {text-align:left;}

	.is-new {
		color: red;
		font-size: 14px;
		vertical-align: top;
	}
	
	#search-form {
		text-align: center;
		margin-bottom: 15px;
	}
	
	#pagebar {
		text-align: center;
		margin-bottom: 15px;
	}
	
	.comment-count {
		font-size: 12px;
	}
	
	.comment-count::before {
		content: '..';
	}
	
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<main id="main">
		<h1 class="sub">게시판 
			<!-- 검색 중일 때는 검색결과 / 아닐 때는 목록보기 -->
			<small>
			<c:if test="${map.search == 'n'}">
				목록보기
			</c:if>
			<c:if test="${map.search == 'y'}">
				검색결과
			</c:if>
			</small>
		</h1>
		
		<%-- 페이지 이동 디자인 1
		<div style="text-align:right;">
			<input type="number" id="page" class="short" min="1" max="${totalPage}" value="${nowPage }">
			<input type="button" value="이동하기" onclick="location.href='/toy/board/list.do?page=' + $('#page').val();">
		</div>
		 --%>
		
		 <!-- 페이지 이동 디자인 2 -->
		<div style="text-align:right;">
			<select id='selPage' onchange="location.href='/toy/board/list.do?page=' + $(this).val();">
			<c:forEach var="i" begin="1" end="${totalPage}">
				<option value="${i}">${i}</option>
			</c:forEach>
			</select>
			페이지
		</div>
		
		<div>
			<input type="range" min="1" max="${totalPage}" style="width: 100%;" value="${nowPage}" onchange="location.href='/toy/board/list.do?page=' + $(this).val();">
		</div>
		
		<table id="list">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>날짜</th>
				<th>조회수</th>
				
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>
					<!-- 주소로 객체는 넘길 수 없다. 변수의 값으로만 넘긴다. -->
					<a href="/toy/board/view.do?seq=${dto.seq}&search=${map.search}&column=${map.column}&word=${map.word}">${dto.subject}</a>
					
					<!-- 댓글 수 --> 
					<c:if test="${dto.ccnt > 0}">
					<span class="comment-count">${dto.ccnt}</span>
					</c:if>
					
					<!-- 새로운 글 마크 -->
					<c:if test="${dto.isnew == 1}">
					<span class="is-new">new</span>
					</c:if>
				</td>
				<td>${dto.name}</td>
				<td>${dto.regdate}</td>
				<td>${dto.readcount}</td>
			</tr>
			</c:forEach>
		</table>
		
		<!-- 페이지 바 -->
		<!-- 페이지 수 계산은 서블릿에서 작업한다. -->
		<div id="pagebar">${pagebar}</div>
		
		<!-- 검색 기능 -->
		<form id="search-form" action="/toy/board/list.do" method="GET">
			<select name="column">
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="name">이름</option>
			</select>
			<input type="text" name="word" class="long" required>
			<input type="submit" value="검색하기">
		</form>
		
			<div>
				<button type="button" class="list" onclick="location.href='/toy/board/list.do';">목록보기</button>
				<c:if test="${not empty id}">
				<button type="button" class="add primary" onclick="location.href='/toy/board/add.do';">글쓰기</button>
				</c:if>
			</div>
		
	</main>
	
	<script>
		<c:if test="${map.search == 'y'}">
		$('select[name=column]').val('${map.column}');
		$('input[name=word]').val('${map.word}');
		</c:if>
		
		$('#selPage').val(${nowPage});
	
	</script>
</body>
</html>