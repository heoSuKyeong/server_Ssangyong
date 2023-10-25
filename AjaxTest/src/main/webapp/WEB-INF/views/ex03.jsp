<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex03-jquery</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>
	<div>
		<input type="text" id="txt1">
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
			const hong = {
				name: '홍길동',
				age: 200,
				hello: function() {
					
				}
				
			};
			/* ajax()는 태그에 종속되지않은 독립적인 태그이다. */
			$.ajax({
				//ajax.open('GET', 'do') : 페이지 요청 정보
				type:'GET',
				url: '/ajax/ex03data.do', //> result를 반환한다.
				
				//true(기본값) : 비동기 / false : 동기
				//ajax에선 동기로 사용하는 일이 많지않다.
				async: true,
				
				//tblMemo에서 해당 작성자가 쓴 글의 갯수를 반환받는다.
				//ex03data.dp?name=홍길동
				data: 'name=수수깡',
				
				//onredaystatechage + readyState(4) + status(200)인 상태
				success : function(result) {
					//result == ajax.responseText
					$('#txt1').val(result);
				},
				
				//에러 발생 시 호출되는 이벤트(디버깅 시 확인용)
				error : function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
	
	</script>
</body>
</html>