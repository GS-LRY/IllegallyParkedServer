﻿<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px
}
</style>
<title>快速入门</title>

</head>
<body>
	<div id="container" tabindex="0"></div>
	<script type="text/javascript"
		src="https://webapi.amap.com/maps?v=1.3&key=872207cd7707fe014db211e620eb0e06"></script>
	<script type="text/javascript">
		var map = new AMap.Map('container', {
			resizeEnable : true,
			zoom : 10,
			center : [ 116.39, 39.9 ]
		});
	</script>
	<script type="text/javascript"
		src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
</body>
</html>
