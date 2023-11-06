<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex02. 지도 좌표 이동 및 레벨 변경하기</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
</head>
<body>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	<h1>Kakao Map <small>지도 좌표 이동 및 레벨 변경하기</small></h1>
	
	<div>
		<div id="map" style="width:768px;height:400px;"></div>
	</div>
	<hr>
	
		<div>
			<input type="button" value="종각역으로 이동하기" id="btn1">	<!-- 37.570180, 126.983029 -->
			<input type="button" value="역삼역으로 이동하기" id="btn2">   <!-- 37.500612, 127.036277 -->
			<input type="button" value="잠실역으로 이동하기" id="btn3">   <!-- 37.513251, 127.099935 -->
		</div>
		<div>
			<input type="button" value="확대" id="btn4">
			<input type="button" value="축소" id="btn5">
		</div>
		<div>
			<input type="button" value="이동 제어" id="btn6">
			<input type="button" value="확대/축소 제어" id="btn7">
		</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=65374924d90b016c441e902bee01e0c7"></script>
	
	
	<script>
		const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		const options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.499316, 127.033192), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
	
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		
		$('#btn1').click(function() {
			
			const p1 = new kakao.maps.LatLng(37.570180, 126.983029);
			map.setCenter(p1);	//순간이동
			
		});
		
		$('#btn2').click(function() {
			
			const p2 = new kakao.maps.LatLng(37.500612, 127.036277);
			//map.setCenter(p1);
			map.panTo(p2);	//스크롤이동 느낌, 단 어느정도 거리내에서 작동하고 멀어지면 center와 같이 순간이동을 한다.
		});
		
		$('#btn3').click(function() {
			
			const p3 = new kakao.maps.LatLng(37.513251, 127.099935);
			map.setCenter(p3);
			
		});
		
		$('#btn4').click(function() {
			map.setLevel(map.getLevel() - 1);
			
		});
		
		$('#btn5').click(function() {
			
			map.setLevel(map.getLevel() + 1);
			
		});
		
		$('#btn6').click(function() {
			//토글 버튼(드래그 on/off)
			
			if (map.getDraggable()){
				map.setDraggable(false);
				$(this).css('background-color', '#efefef');
			} else {
				map.setDraggable(true);
				$(this).css('background-color', 'gold');
			}
		});
		
		map.setZoomable(false);
		$('#btn7').click(function() {
			//토글 버튼(스크롤 on/off)
			
			if (map.getZoomable) {
				map.setZoomable(false);
				$(this).css('background-color', '#efefef');
			} else {
				map.setZoomable(true);
				$(this).css('background-color', 'gold');
			}
		});
		
		
		
		
		
		
	</script>
	
</body>
</html>