<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap/popper.min.js"></script>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
 <link href="style.css" rel="stylesheet">
 <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>회원관리 시스템 로그인 페이지</title>
<link rel="stylesheet" href="css/bootstrap.css"  type="text/css">
<script src="js/jquery-3.5.0.js"></script>
<script>
$(document).ready(function(){
	$('.navbar-toggler').click(function(){
		if($(this).hasClass('collapsed')){
			
			$(this).removeClass('collapsed');
			$('#navbarColor03').addClass('show');
		}else{
			
			$(this).addClass('collapsed');		
			$('#navbarColor03').removeClass('show');
		}
		});
		
		$("#join").click(function(){
			location.href="join.net";
		});
		
		var id='${id}';
		if(id){
			$("#id").val(id);
			$("#remember").prop("checked",true);
		}
	});
</script>
<style>
.bg-light{
	border: 1px solid rgba(0, 0, 0, 0.1);
}

#ps-logo{
	width:80px;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.form-group{margin-left:20px; margin-right:20px;}
.orange{color:white;background:orange;margin-left:150px;}

#loginbtn{margin-top:15px; margin-left:10px;border-radius: .25rem}

.navbar-nav{
	margin-left:auto;
}
.navbar-collapse {
	
	margin-top:auto;
}

img{width:230; margin-top:100px;}

button .close{
    position: absolute;
    top: 10;
    right: 10;
    width:30px;
}

.right{float:right; }

form{width:500px;}
</style>
</head>
<body>
<a href="index.jsp"><img src="image/Logo.png" class="center"></a>

<form name="loginform" action="loginProcess.net" method="post" class="center">
  	<div class="form-group">
  		<label for="id">ID</label>
  		<input type="text" class="form-control" id="id" placeholder="아이디" name="id" required>
  	</div>
  	<div class="form-group">
  		<label for="pwd">Password</label>
  		<input type="password" class="form-control" id="pass" placeholder="비밀번호" name="pass" required>
  	</div>
  	<div class="form-group form-check">
  		<label class="form-check-label">
  		<input class="form-check-input" type="checkbox" name="remember">Remember me
  		</label>
  	</div>
  	<div class="form-group">
	 <button type="submit" class="w3-btn orange w3-round">로그인</button>
	 <button type="button" class="w3-btn w3-border w3-text-orange w3-white w3-round w3-border-orange" id="join">회원가입</button>
	 </div>
</form>

</body>
</html>