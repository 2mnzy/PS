<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
	text-align:center;
}
#image1{
	float:left;
}


#button-all{
	position: absolute;
	left:650px;
	width:700px;
}
</style>
<script src="js/image_view.js"></script>
<%
	int i = Integer.parseInt(request.getParameter("I_NUM"));
%>
</head>
<body>
<script>
function open_window(){
		window.open("hotphoto/image_view_hotphoto_form?I_NUM=<%=i%>", "newWindow", "width=600, height=400, scrollbar=no");
}
</script>
<br><br>
<img id=image1 src="image_user/<%=i %>.jpg" alt="Image" style="max-width:550px; max-height:550px">
<h1 >사진설명~</h1>
<hr>
<fieldset>
	<table>
		<tr>
			<td style="width:300px">아이디1</td>
			<td style="width:600px"> 내용~~~~~~~~~~~~~~~~~~~~</td>
			<td ><button type="button" class="btn btn-primary btn-sm"  onclick="open_window();">신고하기</button></td>
		</tr>
		<tr>
			<td>아이디2</td>
			<td> 내용~~</td>
			<td><button type="button" class="btn btn-primary btn-sm" onclick="open_window();">신고하기</button></td>
		</tr>
		<tr>
			<td>아이디3</td>
			<td> 안녕하세요!!!! 여긴 이미지 보는 곳입니다!</td>
			<td><button type="button" class="btn btn-primary btn-sm" onclick="open_window();">신고하기</button></td>
		</tr>
		
	</table>
	<hr>
	
	
</fieldset>
<table>
	<tr>
			<td style="width:300px">댓글달기</td>
			<td id="comment_input" style="width:600px">
				 <textarea name="IMAGE_COMMENT" id="image_comment" cols="67" rows="1" class="form-control"></textarea>
			</td>
			<td >
				<button type="button" class="btn btn-info">댓글달기</button>
			</td>
	</tr>
</table><hr>
<table id="button-all">
<tr>
		<td>
			<button type="button" class="btn btn-outline-secondary" id="button_location">위치출력</button>
		</td>
		<td>
			<button type="button" class="btn btn-outline-secondary" id="button_like">좋아요</button>
		</td>
		<td>
			<button type="button" class="btn btn-outline-secondary" id="button_linkcopy">링크복사</button>
		</td>
	</tr>
</table>
</body>