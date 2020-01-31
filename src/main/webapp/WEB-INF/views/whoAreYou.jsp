<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<title>누 구 야?</title>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=kjtrlp60or"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<div id="map" style="width:100%;height:400px;"></div>

<div>

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
		// var mapOptions = {
		// 	center: new naver.maps.LatLng(position.coords.latitude, position.coords.longitude),
		// 	zoom: 10
		// };
		//
		// var map = new naver.maps.Map('map', mapOptions);
		var map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(37.3595704, 127.105399),
			zoom: 10
		});

		var marker = new naver.maps.Marker({
			position: new naver.maps.LatLng(37.3595704, 127.105399),
			map: map
		});
	}
</script>
</body>
</html>