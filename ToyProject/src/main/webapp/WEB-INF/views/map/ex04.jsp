<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex04</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
</head>

<style>
	#main {
		display : flex;
		align-items: flex-start;
	}
	
	#main table {
		width: 384px;
		margin: 0px 16px;
		margin-bottom: 10px;
	}
</style>
<body class="wide">
	
	<h1>Kakao Map <small>즐겨 찾기(CRD)</small></h1>
	
	<div id="main">
		<div id="map" style="width:768px;height:400px;"></div>
		<div>
		<table>
			<tr>
				<td>
					<select name="category" id="category">
						<option value="default">기본</option>
						<option value="cafe">카페</option>
						<option value="food">음식점</option>
						<option value="private">개인</option>
					</select>
					<input type="text" name="name" id="name" class="middle">
					<input type="button" name="btn" id="btn" value="추가하기">
				</td>
			</tr>
			<tr>
				<td>AAA</td>
			</tr>
		</table>
			
		<table>
			<tr>
				<td>AAA</td>
			</tr>
		</table>
		</div>
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
		
		let m = null;
		let lat = null;
		let lng = null;
		// 클릭이벤트로 원하는 장소에 마커 추가하기
		kakao.maps.event.addListener(map, 'click', function(evt) {
			
			lat = evt.latLng.getLat();
			lng = evt.latLng.getLng();
			
			if (m != null){
				//기존 마커 제거
				m.setMap(null);
			}
			m = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(lat, lng)
			});
			
			m.setMap(map);
			
			$('#name').select();
			
			//추가하기
			$('#btn').click(function() {
				$.ajax({
					type: 'POST',
					url: '/toy/map/addplace.do',
					data: {
						lat: lat,
						lng: lng,
						name: $('#name').val(),
						category: $('#category').val()
					},
					dataType: 'json',
					success: function(result) {
						
						if (result.result == 1) {
							
							//다음 작업을 위해 리셋하기
							$('#category').val('default');
							$('#name').val('');
							$('#name').select();
							
							//추가한 목록을 아래 테이블에 출력하기
							//11/07에 진행예정
							
						} else {
							alert('failed');
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c);
					}
				});
			});
			
			
		});
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>