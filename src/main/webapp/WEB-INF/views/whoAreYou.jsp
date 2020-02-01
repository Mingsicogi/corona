<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<jsp:include page="common/default.jsp"/>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=kjtrlp60or"></script>
</head>
<body>
	<div class="row">
		<div class="col-lg-8">
			<div id="map" style="width: 100%; height: 400px">
			</div>

		</div>
		<div class="col-lg-4">
			HI  HI
		</div>
	</div>
<script>
	$(document).ready(function () {
		getLocation();
	});

	var x = document.getElementById("demo");

	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {
			x.innerHTML = "Geolocation is not supported by this browser.";
		}
	}

	function showPosition(position) {
		var mapOptions = {
			center: new naver.maps.LatLng(position.coords.latitude, position.coords.longitude),
			zoom: 8,
			minZoom: 1,
			mapTypeId: naver.maps.MapTypeId.HYBRID,
			zoomControl: true,
			zoomControlOptions: {
				position: naver.maps.Position.TOP_RIGHT
			},
			disableKineticPan: false
		};

		var map = new naver.maps.Map('map', mapOptions);

		map.setOptions({
			mapTypeControl: true,
			scaleControl: false,
			logoControl: false
		});

		var infecteeInfoList = JSON.parse(JSON.stringify(${infecteeInfoJsonStr}));

		for(i = 0; i < infecteeInfoList.length; i++) {
			var location = infecteeInfoList[i].location.valueOf();
			for(j = 0; j < location.length; j++) {
				var marker = new naver.maps.Marker({
					position: new naver.maps.LatLng(location[j].y, location[j].x),
					map: map
				});
			}
		}

		// var marker = new naver.maps.Marker({
		// 	position: new naver.maps.LatLng(37.3595704, 127.105399),
		// 	map: map
		// });
	}
</script>
</body>
</html>