<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex01</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<!-- 
	사용자가 클라이언트 작업 중에 서버에게 데이터를 전송해야 하거나 서버로부터 데이터를 수신해야하는 통신이 필요하다면 기존의 페이지를 새로고침해야한다.
	입력했던 값이 날라가는 현상이 있다.
	
	이것을 보완하기 위해 예전에는 프레임을 사용했으나 지금은 Ajax를 많이 사용한다.
	
 	-->
	<div>
		<input type="text" id="txt1" value=${count}>
		<input type="button" value="버튼1" id="btn1">
	</div>
	<div>
		<input type="text" id="txt2">
		<input type="button" value="버튼2" id="btn2">
	</div>


	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
		
		$('#btn1').click(function() {
			location.href = '/ajax/ex01data.do';
		})
	
	</script>
</body>
</html>