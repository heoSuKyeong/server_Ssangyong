<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex03. 지도 클릭 이벤트 및 마커 조작하기</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
</head>
<body>
	
	<h1>Kakao Map <small>지도 클릭 이벤트 및 마커 조작하기</small></h1>
	
	<div id="map" style="width:768px;height:400px;"></div>
	
	<div>
		<div class="message"></div>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=65374924d90b016c441e902bee01e0c7"></script>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
		const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		const options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.499316, 127.033192), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
	
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		//이벤트 추가
		//일반적인 BOM 객체가 아닌 카카오가 만든 객체이므로 onclick 메소드가 작동하지 않는다.
		//kakao.maps.event.addListener(지도객체, 이벤트, 콜백함수)
		kakao.maps.event.addListener(map, 'click', function(evt) {
			
			
			//evt : 이벤트 관련 정보를 제공한다.
			//alert(evt.latLng); 		//좌표 객체
		
			let msg = `클릭한 위치는 (\${evt.latLng.getLat()}, \${evt.latLng.getLng()}) 입니다.`;
			
			$('.message').text(msg);
			
			
			
			//현재 좌표를 ajax를 활용하여 서버로 전송 및 DB에 insert 하기
			$.ajax({
				type: 'POST',
				url: '/toy/map/addmarker.do',
				data: {
					lat: evt.latLng.getLat(),
					lng: evt.latLng.getLng()
				},
				dataType: 'json',
				success: function(result) {
					
					if (result.result == 1){

						//마커 등록하기
						const m1 = new kakao.maps.Marker({
							position: new kakao.maps.LatLng(evt.latLng.getLat(), evt.latLng.getLng())		//마커가 출발할 좌표값
						});
					
						//마커를 지도에 추가
						m1.setMap(map);
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		
		//DB에 저장된 좌표를 마커로 출력하기
		<c:forEach items="${list}" var = "dto" varStatus="status">
			//마커 등록하기
			const m${status.count} = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
			});
			
			//마커를 지도에 추가
			m${status.count}.setMap(map);
		</c:forEach>
		
	</script>
</body>
</html>