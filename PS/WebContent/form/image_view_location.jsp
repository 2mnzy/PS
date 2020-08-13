<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <script src="js/jquery/jquery-2.2.4.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74162ff0220482d9d27524e8c83d0364"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74162ff0220482d9d27524e8c83d0364&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript" src="//s1.daumcdn.net/svc/attach/U03/cssjs/mapapi/libs/1.0.1/1515130215283/services.js?appkey=74162ff0220482d9d27524e8c83d0364&libraries=services"></script>

<style>

body{
	text-align:center;
}
</style>

</head>
<body>
	<div id="map" style="width:500px;height:400px;"></div>
	
<script>


var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(${lat},${lng}), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
//마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(${lat},${lng}); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);


//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
 infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

//현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

//지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
 searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
     if (status === kakao.maps.services.Status.OK) {
         var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
         detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
         
         var content = '<div class="bAddr">' +
                         '<span class="title">법정동 주소정보</span>' + 
                         detailAddr + 
                     '</div>';

         // 마커를 클릭한 위치에 표시합니다 
         marker.setPosition(mouseEvent.latLng);
         marker.setMap(map);

         // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
         infowindow.setContent(content);
         infowindow.open(map, marker);
     }   
 });
});

//중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
 searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
 // 좌표로 행정동 주소 정보를 요청합니다
 geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
 // 좌표로 법정동 상세 주소 정보를 요청합니다
 geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

//지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
 if (status === kakao.maps.services.Status.OK) {
     var infoDiv = document.getElementById('centerAddr');

     for(var i = 0; i < result.length; i++) {
         // 행정동의 region_type 값은 'H' 이므로
         if (result[i].region_type === 'H') {
             infoDiv.innerHTML = result[i].address_name;
             break;
         }
     }
 }    
}
</script>

</body>
</html>