<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%--최세동 추가1  --%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74162ff0220482d9d27524e8c83d0364"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74162ff0220482d9d27524e8c83d0364&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript" src="//s1.daumcdn.net/svc/attach/U03/cssjs/mapapi/libs/1.0.1/1515130215283/services.js?appkey=74162ff0220482d9d27524e8c83d0364&libraries=services"></script>
<%--최세동 추가1 끝 --%>
<jsp:include page="../main/header.jsp"/>
	<!-- Jquery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap-4 js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins JS -->
    <script src="js/others/plugins.js"></script>
    <!-- Active JS -->
    <script src="js/active.js"></script>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<meta charset="UTF-8">
<style>
body{
	align:center;
}
#image1{
	float:left;
	margin-left:100px;
	margin-right:100px;
}


#button-all{
	position: absolute;
	left:650px;
	width:700px;
}

<%--최세동 추가2 --%>
 .modal-dialog ,.modal-content, .modal-wrap{max-width:800px;}
 .location-close{position:relative;float:right;top:5px;width:50px;}
      #attach{width:20px;}
      .map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
<%--추가2 끝--%>

.pink{color:hotpink; font-size:1.6em;}
.red{color:red;border-color:red;}
</style>
<script src="js/image_view.js"></script>
<%
	String j = request.getParameter("FILENAME");
	int i = Integer.parseInt(request.getParameter("I_NUM"));
%>
</head>
<body>
<script>
$(function() {

	$("#button_like").click(function(){
		  location.href="Imagelike.im?I_NUM=<%=i%>&id=${id}&FILENAME=<%=j%>";
  });
	
	$("#button_addcomment").click(function(){
		var content= $(this).prev().val();
		var content2 =document.getElementById('image_comment').value;
		console.log(content2);
		location.href="ImageCommentAddAction.com?I_NUM=<%=i%>&FILENAME=<%=j%>&id=${id}&C_CONTENT="+content2;
  });

	$("#creport").click(function(){
		var c = $(this).val();
		window.open("reportcom.wa?C_NUM="+c, "newWindow", "width=600, height=400, scrollbar=no");
	});
	
	$(".crnum").click(function(){
	      var c = $(this).val();
	      window.open("reportcom.wa?C_NUM="+c, "newWindow", "width=600, height=400, scrollbar=no");
	   });
	<%-- 최세동 추가3 --%>
	$('#location-button').on('click',function(){
		
		location.href='Imagelocation.im?I_NUM=<%=i%>';
	});
	<%-- 추가3 끝--%>
})

function imgreport(){
	window.open("reportimg.wa?I_NUM=<%=i%>", "newWindow", "width=600, height=400, scrollbar=no");
}


window.onload = function(){
	$.ajax({
		url : "imagelikecount.im",
		data : {"I_NUM":'<%=i%>'},
		success : function(resp){
			document.getElementById("I_LIKECOUNT").innerText += resp;
		}
	});
	
	$.ajax({
		url : "imagecontent.im",
		data : {"I_NUM":'<%=i%>'},
		success : function(resp){
			document.getElementById("I_CONTENT").innerText += resp;
		}
	});
};
</script>
<br><br>

<img id=image1 src="uploadedimage/<%=j %>" alt="Image" style="max-width:550px; max-height:550px">
<p id="I_LIKECOUNT" class="pink" ><span id="pink">♥&nbsp;</span></p>
<p id="I_CONTENT" style="display:inline; font-size:5"></p>
<br><br>
<hr>
<c:if test="${listcount> 0}">
	<table style="text-align:center">
	  <thead>
		<tr>
			<th style="width:100px; text-align:center"><div>댓글번호</div></th>
        	<th style="width:100px; text-align:center"><div>작성자</div></th>
         	<th style="width:300px; text-align:center"><div>내용</div></th>
         	<th style="width:200px; text-align:center"><div>날짜</div></th>
       </tr>
      </thead>
      <tbody>
		<c:forEach var="c" items="${commentlist}">
        <tr>
        	<td><div>${c.c_NUM}</div></td>
           <td><div>${c.m_ID}</div></td>
           <td><div>${c.c_CONTENT}</div></td>
           <td><div>${c.c_DATE}</div></td>
           <td ><button type="button" class="btn btn-primary btn-sm crnum"  id="creport" value="${c.c_NUM}">신고하기</button></td>
        </tr>
        </c:forEach>
	  </tbody>
	</table>
	<hr>
</c:if>
	<!-- 레코드 없으면 -->
   <c:if test="${listcount==0}">
   	  <br><br><br>
      <font size=4>등록된 댓글이 없습니다.</font>
      <br><br><br>
   </c:if>
<table >
	<tr>
			<td style="width:300px">댓글달기</td>
			<td id="comment_input" style="width:600px">
				 <textarea name="IMAGE_COMMENT" id="image_comment" cols="67" rows="1" class="form-control"></textarea>
			</td>
			<td >
				<button type="button" class="btn btn-info" id="button_addcomment">댓글달기</button>
			</td>
	</tr>
</table><hr>
<table id="button-all">
<tr>
		<td><!-- 최세동 변경1 -->
			<button type="button" class="btn btn-outline-secondary" id="location-button" >위치출력</button>
		</td>
		<%--최세동 변경 끝 --%>
		<td>
			<button type="button" class="btn btn-outline-secondary" id="button_like">좋아요
			</button>
		</td>
		<td>
			<button type="button" class="btn btn-outline-secondary" id="button_linkcopy">링크복사</button>
		</td>
		<td>
			<button type="button" class="btn btn-outline-secondary red" onclick="imgreport()" id="button_report">이미지 신고</button>
		</td>
	</tr>
</table>
</body>