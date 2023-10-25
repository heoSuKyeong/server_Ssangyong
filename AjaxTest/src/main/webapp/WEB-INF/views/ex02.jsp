<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex02</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<div>
		<input type="text" id="txt1">
		<input type="button" value="버튼1" id="btn1">
	</div>
	
	<div id="msg" class="message">
	
	</div>
	
	<div>
		<input type="text" id="txt2">
		<input type="button" value="버튼2" id="btn2">
	</div>


	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
		
		$('#btn1').click(function() {
			
			//Ajax
			//1. 순수 자바스크립트
			//2. jQuery에서 개량해놓은 API
			//같은 상황이면 2번을 사용하는 것이 편하다.
			
			//Ajax를 요청하고 응답하는 객체를 만든다.
			//서버와 데이터를 송수신한다.
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function() {
				
				//***** ajax가 서버로부터 응답받은 데이터를 접근하려면 반드시 readyState가 요청한 데이터의 처리가 완료되어 응답할 준비가 완료된 상태인, 4일때만 접근해야한다.
				//status도 200일 때 안전한 상태로써 아래 조건이 만족해야 업무를 진행한다.
				if (ajax.readyState == 4 && ajax.status== 200) {
				//readystate change?
				//$('#msg').html(ajax.responseText);	//100	
				$('#msg').append('<div>readyState: ' + ajax.readyState + '</div>');		
				$('#msg').append('<div>status: ' + ajax.status + '</div>');	
				
					
					$('#msg').append('<div>responseText: ' + ajax.responseText + '</div>');	
					//$('#msg').append('<xmp>responseText: ' + ajax.responseText + '</xmp>');	

				$('#msg').append('<hr>');	
				
				$('#txt1').val(ajax.responseText);
				} else {
					if(ajax.readyState == 4) {
						alert('서버와의 통신이 불안정합니다.');
					}
				};
				
			};
			
			//<form method="GET" action="주소">
			//ajax.open('GET', '서버쪽 프로그램 주소')
			//ajax.open('GET', '/ajax/ex02.txt');	//서버 연결 설정
			ajax.open('GET', '/ajax/ex02data.do');	//서버 연결 설정
			
			
			ajax.send();	//서버와 연결시도 > ajax로 요청하면 페이지 자체를 반환한다.
			
			
		});
	
	</script>
</body>
</html>