<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UI 설계</title>

<style>

	#dept-select {
    	width: 150px;
    	padding: 5px;
    }
    
    .info-table {
    	border: 1px solid black;
    	border-collapse: collapse;
    	width: 600px;
    }
    
    .info-table th, .info-table td {
    	border: 1px solid black;
    	padding: 8px;
    	text-align: center;
    }
    
    .info-table th:nth-child(1) { width: 65px; }
    .info-table th:nth-child(2) { width: 180px; }
    .info-table th:nth-child(3) { width: 150px; }
    .info-table th:nth-child(4) { width: 130px; }
    
</style>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>
	
	<div>
		<div id="header">
	        <select id="dept-select">
	        	<c:forEach items="${list}" var="item" varStatus="status">
	            <option value="${item.department_id}">${item.department_name}</option>
	            </c:forEach>
	        </select>
	        <input type="button" value="부서생성" id="deptBtn" >
    	</div>
    	
    	<h2>사원정보</h2>
		<table class="info-table">
			<tbody>
			
			<tr>
				<th>사원번호</th>
				<th>사원명</th>
				<th>직무</th>
				<th>입사일</th>
			</tr>
			<!-- 
			<tr>
				<td>100</td>
				<td>허수경</td>
				<td>전산직</td>
				<td>2023-12-27</td>
			</tr>
			 -->
			</tbody>
		</table>
	
	</div>
	
	<script>
	$('#deptBtn').click(function() {
		
		$.ajax({
			type: 'POST',
			url: '/ajax/emp.do',
			data: {department_id: $('#dept-select').val()},
			dataType: 'json',
			success: function(result) {
				
				$('.info-table tbody').html('');
				
				$('.info-table tbody').append(`<tr>
						<th>사원번호</th>
						<th>사원명</th>
						<th>직무</th>
						<th>입사일</th>
					</tr>`);
				
				$(result).each((index, item) => {
					
					let temp = `
						<tr>
							<td>\${item.id}</td>
							<td>\${item.name}</td>
							<td>\${item.job}</td>
							<td>\${item.date}</td>
						</tr>`;
					$('.info-table tbody').append(temp);
				});
				
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
	});
	
	</script>
	
</body>
</html>