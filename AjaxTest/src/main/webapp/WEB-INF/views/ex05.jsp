<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<h1>Ajax 데이터 보내는 방법</h1>

	<form id="form1">
	<table class="vertical">
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" value="수수깡"></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="number" name="age" id="age" value=26></td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<input type="radio" name="gender" id="gender1" value="m">남자
				<input type="radio" name="gender" id="gender2" value="f" checked>여자
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address" id="address" value="서울시 성동구"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="tel" id="tel" value="010-1234-1234"></td>
		</tr>
	</table>
	
	<div>
		<input type="button" value="확인" id="btn">
	</div>
	
	
	</form>


	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
		$('#btn').click(function() {
			//가져올때 get, 전송할 때 post
			//ajax는 기본적으로 UTF-8 통신을하여 따로 인코딩을 하지 않아도 된다.
			
			//1. 단일 데이터 전송
			/*
			$.ajax({
				type: 'POST',
				url: '/ajax/ex05data.do',
				data: 'name=' + $('name').val(),
				//success: function(result) {
					//데이터를 수신받을 때만 의미있고, 전송만 할 때는 작성하지 않는다.
				//}
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			*/
			
			//전송하기 전에 alert으로 확인
			//alert('name=' + $('#name').val() + '&age=' + $('#age').val());
			/*
			$.ajax({
				type: 'POST',
				url: '/ajax/ex05data.do',
				data: 'name=' + $('#name').val() + '&age=' + $('#age').val(),
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			*/
			
			//라디오 버튼 값넘기기
			//alert($('input[name=gender]:checked').val());
			/*
			1. 가장 기본적으로 데이터 전송하는 방법
			$.ajax({
				type: 'POST',
				url: '/ajax/ex05data.do',
				data: 'name=' + $('#name').val() + '&age=' + $('#age').val() + '&gender=' + $('input[name=gender]:checked').val() + '&address=' + $('#address').val()+ '&tel=' + $('#tel').val(),
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			*/
			/*
			//2. 객체로 전송하는 방법(가장 많이 사용한다)
			$.ajax({
				type: 'POST',
				url: '/ajax/ex05data.do',
				data: {
					name: $('#name').val(), 
					age: $('#age').val(),
					gender: $('input[name=gender]:checked').val(),
					address: $('#address').val(),
					tel: $('#tel').val()},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			*/
			
			//폼태그가 있을 때 가능한 방법
			//alert($('#form1').serialize());
			
			//ajax는 폼태그가 필요하지않지만 해당 방법을 사용하려면 폼태그가 필요하다.
			//name 프로퍼티로 데이터를 전송하기때문에 태그 안에 name를 꼭 기재해줘야한다.
			
			$.ajax({
				type: 'POST',
				url: '/ajax/ex05data.do',
				data: $('#form1').serialize(),
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
	
	
	</script>
</body>
</html>