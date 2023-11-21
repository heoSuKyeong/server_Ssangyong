<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<!-- ex01.jsp > ex05.jsp -->
	<h1>Map <small>Marker</small></h1>
	
	<div>
		<div id="map" style="width:1168px;height:400px;"></div>
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
	
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d9a9dc5f180000f50bb124866e70f51a"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	<script>
	
		const container = document.getElementById('map'); 
			
		const options = {
			center: new kakao.maps.LatLng(37.499316, 127.033192),
			level: 3
		};
	
		const map = new kakao.maps.Map(container, options);
		
		
		
		
		const plist = [];
		
		
		
		
		//마커 출력
		// - Identifier 'm' has already been declared
		
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
		
		
		//마커 + 클릭 이벤트
		kakao.maps.event.addListener(m${status.count}, 'click', function(evt) {
			//alert();
			//누구를 클릭했는지? > PK
			//alert(evt.target);
			//alert(evt.srcElement);
			//alert(event.currentTarget);
			//alert(this.nodeName);
			//$(this).hide();
			//this.setMap(null);
			//alert(this.seq);
			//alert(event.target.nodeName);
			
			selPlace(this.seq);
			
			$('#map > div > div > div > div > img').css('opacity', '.3');
			$(event.target).css('opacity', '1');			
			
		});
		
		
		
		//plist 배열 > 장소 정보 추가
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
		
		
		function clear() {
			$('#map > div > div > div > div > img').css('opacity', '1');
			$('#list .item').css('background-color', 'transparent');			
		}
		
		window.onkeydown = function() {
			if (event.keyCode == 27) {
				//esc
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
			//$('#result').text(map.getBounds().getSouthWest()); //남서쪽
			//$('#result').text(map.getBounds().getNorthEast());//북동쪽
			
			$('#result').text('');
			
			$(plist).each((index, item) => {
				
				if (contains(item.lat, item.lng)) {
					//$('#result').append('포함, ');
					$('#list #item' + item.seq).show();
				} else {
					//$('#result').append('미포함, ');
					$('#list #item' + item.seq).hide();
				}
				
			});
			
		});
		
		
		//지도 > 줌인, 줌아웃
		kakao.maps.event.addListener(map, 'zoom_changed', function(evt) {
			
			$('#result').text('');
			
			$(plist).each((index, item) => {
				
				if (contains(item.lat, item.lng)) {
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






