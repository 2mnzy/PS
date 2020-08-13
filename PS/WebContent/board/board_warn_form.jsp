<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<meta charset="UTF-8">
<%
	int b = Integer.parseInt(request.getParameter("B_NUM"));
%>
<style>
form{height:100%}
body{background:#336699; color:white;text-align:center}
input[type="text"]{display:none;}
.white{backgroud:white; color:#336699;font-size:1.2em;border-radius:0.25em;border:none;margin-top:70px;}
</style>
</head>
<body>
<div class="jumbotron">
  <h1 class="display-3">신고하기</h1>
  <hr class="my-4">
  <c:set var="b" value="${boardinfo}"/>
	<form action="bowarnProcess.wa" method="post">
	 <div class="comment-content">
	 <br><br><br>
	 
	 <input type="text" name="id" value="${b.WM_ID}">
	 <input type="text" name="bonum" value="${b.WB_NUM}">
	 <input type="radio" name="wtag" value="1"><span>광고 게시물입니다.</span><br><br><br>
	 <input type="radio" name="wtag" value="2"><span>부적절합니다.</span>
	</div>
	<button type="submit" class="white">제출</button>
	</form>
</div>
</body>
</html>