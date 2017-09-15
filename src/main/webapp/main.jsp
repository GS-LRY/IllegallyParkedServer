<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<title>主页</title>
<style type="text/css">
        #result {
            margin: 0;
            padding: 0;
            z-index: 999;
            position: absolute;
            background-color: white;
            max-height: 100%;
            overflow-y: auto;
            top: 0;
            right: 0;
            width: 280px;
        }
    </style>
</head>
<body>
	<!-- S= 导航栏 -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar"></button>
				<a class="navbar-brand" href="#">TLKJ.COM</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">操作地图</a></li>
					<li><a href="#">系统运维</a></li>
					<li><a href="#">系统管理</a></li>
				</ul>
			</div>
		</div>


	</div>
	<!-- E= 导航栏 -->
	<!-- S= 探头列表 -->
	<div class="row" id="cameradiv">
		<span id='toggle' class="toggle_left"></span>
		<div class="well sidebar-nav">
			<!-- S = 标签页 -->
			<ul id="myTabs" class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#weiting"
					id="weiting-tab" role="tab" data-toggle="tab"
					aria-controls="weiting" aria-expanded="true">违停</a></li>
				<li role="presentation"><a href="#kakou" id="kakou-tab"
					role="tab" data-toggle="tab" aria-controls="kakou"
					aria-expanded="false">卡口</a></li>
				<li role="presentation"><a href="#gaosu" id="gaosu-tab"
					role="tab" data-toggle="tab" aria-controls="gaosu"
					aria-expanded="false">高速</a></li>
				<li role="presentation"><a href="#guzhang" id="guzhang-tab"
					role="tab" data-toggle="tab" aria-controls="guzhang"
					aria-expanded="false">故障设备</a></li>
			</ul>
			<!-- E = 标签页 -->
			<!-- S = 标签页内容 -->
			<div id="myTabContent" class="tab-content">
				<!-- S = 违停 -->
				<div role="tabpanel" class="tab-pane fade active in" id="weiting"
					aria-labelledby="weiting-tab">
					<ul id="weiting-tree" class="ztree"></ul>
				</div>
				<!-- E = 违停 -->
				<!-- S = 卡口 -->
				<div role="tabpanel" class="tab-pane fade" id="kakou"
					aria-labelledby="kakou-tab">
					<ul class="nav nav-list">
						<li><a href="#">HTML 4.01</a></li>
						<li><a href="#">HTML5</a></li>
						<li><a href="#">CSS</a></li>
						<li><a href="#">JavaScript</a></li>
						<li><a href="#">Twitter Bootstrap</a></li>
						<li><a href="#">Firebug</a></li>
						<li><a href="#">Backend</a></li>
						<li><a href="#">PHP</a></li>
						<li><a href="#">SQL</a></li>
						<li><a href="#">MySQL</a></li>
						<li><a href="#">PostgreSQL</a></li>
						<li><a href="#">MongoDB</a></li>
						<li><a href="#">JavaScript</a></li>

					</ul>
				</div>
				<!-- E = 卡口 -->
				<!-- S = 高速 -->
				<div role="tabpanel" class="tab-pane fade" id="gaosu"
					aria-labelledby="gaosu-tab"></div>
				<!-- E = 高速 -->
				<!-- S = 故障 -->
				<div role="tabpanel" class="tab-pane fade" id="guzhang"
					aria-labelledby="guzhang-tab"></div>
				<!-- E = 故障 -->
			</div>
			<!-- E = 标签页内容 -->

		</div>
		<!--/.well -->
	</div>
	<!--/span-->

	<!-- E= 探头列表 -->
	<!-- S= 地图操作 -->


	<div id="showmap" tabindex="0"></div>

	<div id="result"></div>

	<!-- E= 地图操作 -->
	<script type="text/javascript"
		src="https://webapi.amap.com/maps?v=1.3&key=872207cd7707fe014db211e620eb0e06"></script>

	<script type="text/javascript"
		src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript">
		// 地图显示
		var map = new AMap.Map('showmap', {
			resizeEnable : true,
			zoom : 10,
			center : [ 121.47, 31.23 ]
		});

		// 探头显示
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			}
		};

		$(document).ready(
				function() {
					$.ajax({
						type : 'GET',
						url : 'getAllpdwtInformation.json',
						dataType : 'json',
						success : function(data) {
							var Lnglats = [];//存储点的坐标
							var Lnglatss = [];
							var treeList = new Array();
							var pdwtList = eval(data.pdwtList);
							var maxpac = data.maxpac;
							// 筛选出包数
							var pacList = eval(data.pacList);
							
							for (i = 0; i < pacList.length; i++){
								treeList[i] = new Object();
								treeList[i].id = pacList[i];
								treeList[i].pId = pacList[i];
								treeList[i].name = pacList[i]+"包";
								treeList[i].icon = "img/fenlei.png";
							}
							var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
							for (i = 0; i < pdwtList.length; i++) {
								treeList[i+pacList.length] = new Object();
								treeList[i+pacList.length].id = maxpac+pdwtList[i].id;
								treeList[i+pacList.length].pId = pdwtList[i].pac;
								treeList[i+pacList.length].name = pdwtList[i].deviceaccessid;
								treeList[i+pacList.length].icon = "img/pingsuccess.png";
								
								// GPS坐标转换为高德坐标
								Lnglats[i] = [pdwtList[i].lng,pdwtList[i].lat];
								//alert("转换前："+Lnglats[i]);
								var gdgps = GPS.gcj_encrypt(Number(pdwtList[i].lat),Number(pdwtList[i].lng));
								var slng = gdgps.lon;
								var slat = gdgps.lat;
								Lnglats[i] = [slng,slat];
								//alert("转换后："+Lnglats[i]);
								
							}
		
							var zNodes = treeList;
							$.fn.zTree.init($("#weiting-tree"), setting, zNodes);
							
							$('#toggle').on('click',function(){
								var className = $(this).prop('class');
								switch(className){
									case 'toggle_left':
										$('#cameradiv').animate({width:'0'});
										$('.well').animate({padding:'0'}).css('border','none');

										$('#myTabs').hide();
										$(this).prop('class','toggle_right');
										break;
									case 'toggle_right':
										$('#cameradiv').animate({width:'20%'});
										$('.well').animate({padding:'20px'}).css('border','1px solid #e3e3e3');
										$('#myTabs').show();
										$(this).prop('class','toggle_left');
										break;
								};
							});
							
							// 在地图上显示点标记
							var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
						    for (var i = 0; i < Lnglats.length; i++) {
						        var marker = new AMap.Marker({
						            position: Lnglats[i],
						            map: map,
						            icon:new AMap.Icon({
						            	size:new AMap.Size(40,50),//图标大小
						            	image:"img/"+pdwtList[i].pac+"lnglat.png",// 包名+lnglat图片
						            	imageOffset:new AMap.Pixel(0,0)
						            })
						        });
						        marker.content = '第' + pdwtList[i].pac + '包<br/>设备接入ID：'+pdwtList[i].deviceaccessid+'<br/>设备编号：'+pdwtList[i].entrynumber+'<br/>安装地址：'+pdwtList[i].installlocation;
						        marker.on('click', markerClick);
						        marker.emit('click', {target: marker});
						    }
						    function markerClick(e) {
						        infoWindow.setContent(e.target.content);
						        infoWindow.open(map, e.target.getPosition());
						    }
						    map.setFitView();
						    
						    AMap.service(["AMap.PlaceSearch"], function() {
						        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
						            pageSize: 5,
						            pageIndex: 1,
						            city: "011",
									map: map,
						            panel: "result"
						        });
						        //多边形查
						        var polygonArr = [//多边形覆盖物节点坐标数组
						            [121.531646, 31.226785],
						            [121.52821917, 31.182179349],
						            [121.66666666666667, 31.165277777777778],
						            [121.653647, 31.216258]
						        ];
						        var polygon = new AMap.Polygon({
						            path: polygonArr,//设置多边形边界路径
						            strokeColor: "#FF33FF", //线颜色
						            strokeOpacity: 0.2, //线透明度
						            strokeWeight: 3,    //线宽
						            fillColor: "#1791fc", //填充色
						            fillOpacity: 0.35//填充透明度
						        });
						        placeSearch.searchInBounds('酒店', polygon);
						    });
						},
						error : function(XMLHttpRequest,
								textStatus, errorThrown) {
						}
					});
				});
		
		
		// GPS转高德坐标方法
		var GPS = {
			    PI : 3.14159265358979324,
			    x_pi : 3.14159265358979324 * 3000.0 / 180.0,
			    delta : function (lat, lon) {
			        // Krasovsky 1940
			        //
			        // a = 6378245.0, 1/f = 298.3
			        // b = a * (1 - f)
			        // ee = (a^2 - b^2) / a^2;
			        var a = 6378245.0; //  a: 卫星椭球坐标投影到平面地图坐标系的投影因子。
			        var ee = 0.00669342162296594323; //  ee: 椭球的偏心率。
			        var dLat = this.transformLat(lon - 105.0, lat - 35.0);
			        var dLon = this.transformLon(lon - 105.0, lat - 35.0);
			        var radLat = lat / 180.0 * this.PI;
			        var magic = Math.sin(radLat);
			        magic = 1 - ee * magic * magic;
			        var sqrtMagic = Math.sqrt(magic);
			        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
			        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);
			        return {'lat': dLat, 'lon': dLon};
			    },

			    //GPS---高德
			    gcj_encrypt : function ( wgsLat , wgsLon ) {
			        if (this.outOfChina(wgsLat, wgsLon))
			            return {'lat': wgsLat, 'lon': wgsLon};

			        var d = this.delta(wgsLat, wgsLon);
			        return {'lat' : wgsLat + d.lat,'lon' : wgsLon + d.lon};
			    },
			    outOfChina : function (lat, lon) {
			        if (lon < 72.004 || lon > 137.8347)
			            return true;
			        if (lat < 0.8293 || lat > 55.8271)
			            return true;
			        return false;
			    },
			    transformLat : function (x, y) {
			        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
			        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
			        ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
			        ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
			        return ret;
			    },
			    transformLon : function (x, y) {
			        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
			        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
			        ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
			        ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
			        return ret;
			    }
			};
	</script>
</body>
</html>