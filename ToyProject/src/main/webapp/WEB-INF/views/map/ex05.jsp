<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex05 Marker</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">

<style>
	#list {
		display: grid;
		grid-template-columns: repeat(4, 1fr);
		gap: 10px;
	}
	
	#list .item {
		border: 1px solid #CCC;
		border-radius: 5px;
		padding: 5px 10px;
		display: flex;
		align-items: center;
	}
	
	#list .item img {
		height: 35px;
		margin: 7px;
	}
</style>

</head>
<body class="wide">
	
	<h1>Kakao Map <small>Marker</small></h1>
	<div>
		<div id="map" style="width:1150px;height:400px;"></div>
	</div>
	<div id="result"></div>
	
	<hr>
	
	<h3>Place</h3>
	<div id="list">
		<c:forEach items="${list}" var="dto">
		<div class="item" id="item${dto.seq}">
			<img src="/toy/asset/marker/${dto.category}.png">
			<span>${dto.name}</span>
		</div>
		</c:forEach>
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
		
		const plist = [];
	
		//마커 출력하기
		const imageSize = new kakao.maps.Size(40, 40);
		const option = {};
		
		<c:forEach items="${list}" var="dto" varStatus="status">
		
		const imageUrl${status.count} = '/toy/asset/marker/${dto.category}.png';
		
		const markerImg${status.count} = new kakao.maps.MarkerImage(imageUrl${status.count}, imageSize, option);
		
		const m${status.count} = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		});
		
		m${status.count}.setImage(markerImg${status.count});
		m${status.count}.seq = ${dto.seq};
		m${status.count}.setMap(map);
		
		
		//마커에 클릭이벤트 생성하기> 우리가 알던 이벤트 방식이 아닌 카카오 방식으로 진행해야한다.
		//kakao.maps.event.addListener(대상, 이벤트종류, 콜백함수);
		kakao.maps.event.addListener(m${status.count}, 'click', function(evt) {
			//alert();
			
			//누구를 클릭했는지 확인 방법
			//alert(event.target);
			//alert(event.srcElement);
			//alert(event.currentTarget);
			//alert(this.nodeName);  //undefind > 객체는 아니다.
			//alert(this.seq);
			
			selPlace(this.seq);
			
			$('#map > div> div > div > div >img').css('opacity', '.3');
			$(event.target).css('opacity', '1');
		});
		
		//plist 배열에 장소 정보를 추가
		plist.push({
			seq: ${dto.seq},
			lat: ${dto.lat},
			lng: ${dto.lng}
		});
		
		</c:forEach>
		
		function selPlace(seq) {
			
			$('#list .item').css('background-color', 'transparent');
			$('#list #item' + seq).css('background-color', 'gold');
		}
		
		//마커 css 초기화
		function clear() {
			
			$('#map > div> div > div > div >img').css('opacity', '1');
			$('#list .item').css('background-color', 'transparent');
			
		}
		
		window.onkeydown = function() {
			if (event.keyCode == 27) {	//ESC
				clear();
			}
		};
		
		kakao.maps.event.addListener(map, 'click', function(evt) {
			
			clear();
			
		});
		
		
		kakao.maps.event.addListener(map, 'dragend', function(evt) {
			//alert();
			
			//현재 출력되는 지도의 영역을 반환
			//$('#result').text(map.getBounds());	
			//$('#result').text(map.getBounds().getSouthWest());	//남서쪽
			//$('#result').text(map.getBounds().getNorthEast());		//북동쪽
			
			$('#result').text('');
			$(plist).each((index, item) =>{
				
				if(contains(item.lat, item.lng)) {
					//$('#result').append('포함');
					$('#list #item' + item.seq).show();
				} else {
					//$('#result').append('미포함');
					$('#list #item' + item.seq).hide();
				}
			});
			
		});
		
		kakao.maps.event.addListener(map, 'zoom_changed', function(evt) {
			$('#result').text('');
			$(plist).each((index, item) =>{
				
				if(contains(item.lat, item.lng)) {
					$('#list #item' + item.seq).show();
				} else {
					$('#list #item' + item.seq).hide();
				}
			});
		});
		
		
		function contains(lat, lng) {
			
			const sw = map.getBounds().getSouthWest();
			const ne = map.getBounds().getNorthEast();
			
			if (lat >= sw.getLat() && lat <= ne.getLat()
					&& lng >= sw.getLng() && lng <= ne.getLng()) {
				return true;
			} else {
				return false;
			}
		}
		
		
		
	</script>
</body>
</html>