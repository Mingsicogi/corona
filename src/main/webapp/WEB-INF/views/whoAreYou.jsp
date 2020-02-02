<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<style>
	.left-top {
		left: 13px;
		top: 100px;
		width: 70px;
		height: 70px;
		display: block;
		text-align: center;
		padding: 10px 10px 10px 10px;
		z-index: 1000;
		position: absolute;
		background-color: #fff;
		border-radius: 4px;
		box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1);
		border: 1px solid rgba(0, 0, 0, .2);
		background-clip: padding-box;
	}
</style>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<jsp:include page="common/default.jsp"/>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=kjtrlp60or&submodules=visualization"></script>
</head>
<body>
	<style type="text/css">
		html, body { height:100%; }
		#wrap2 { position:absolute;top:0px;left:0px;padding:20px;z-index:1001;width:100%;height:0;overflow:visible;color:#fff;pointer-events:none; }
		#back-link { margin-top:-10px;color:#333;font-weight:bold;color:#fff;pointer-events:auto; }
		#code-folder { margin-top:-10px;color:#333;font-weight:bold;color:#fff;pointer-events:auto; }
		#origin-marker { position:absolute;top:50%;left:50%;z-index:1000;background-color:#f00; width:10px;height:10px;border-radius:5px;pointer-events:none; }
		#snippet { width:700px;height:600px;overflow:hidden;overflow-y:auto;pointer-events:auto; }
		.buttons { position:absolute;top:0;left:100px;padding:5px; }
		.buttons .control-btn { pointer-events:auto; }
	</style>

	<div id="map" style="width: 100%; height: 100%">
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
			zoom: 3,
			minZoom: 1,
			maxZoom: 13,
			// mapTypeId: naver.maps.MapTypeId.HYBRID,
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

		var markingData = [];

		console.log(infecteeInfoList);
		for(i = 0; i < infecteeInfoList.length; i++) {
			var location = infecteeInfoList[i].location;

			for(j = 0; j < location.length; j++) {
				markingData.push(new naver.maps.LatLng(location[j].y, location[j].x));
			}

			console.log(infecteeInfoList[i].howManyPeopleMeet);
			var dotmap = new naver.maps.visualization.DotMap({
				map: map,
				data: markingData,
				radius: infecteeInfoList[i].howManyPeopleMeet/5,
				fillColor: infecteeInfoList[i].markingColor
			});

			markingData = [];
		}

		// console.log(markingData);
		// var dotmap = new naver.maps.visualization.DotMap({
		// 	map: map,
		// 	data: markingData,
		// 	radius: 30
		// });
	}
</script>
</body>
</html>