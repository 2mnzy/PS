<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../main/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>회원가입 페이지</title>
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
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link href="css/join.css" type="text/css" rel="stylesheet"> 

<style>
.left{float:left; width:50%; font-weight:bold} 
.right{float:right; width:50%; height:48px; font-weight:bold} 
#id,#name{width:68%;}
#idcheck,#namecheck{width:30%; float:right; height:42.44px; margin-top:5px;}
#loginbtn{display:none;}
#dropout{background-color: #f44336;margin-top:7.5px; margin-bottom:7.5px; color:white;font-weight:bold;}
</style>

</head>

<body>
<c:set var="m" value="${memberinfo}"/>
	<form name="joinform" action="updateProcess.net" method="post">
		<h1>회원정보 수정</h1><br>
		<b>아이디</b>
		<input type="text" name="id" value="${m.ID}" readonly>
		<b>비밀번호</b>
		<input type="password" name="pass" id="pass" placeholder="Enter password" required>
		<span id="passmessage"></span>
		<b>비밀번호 확인</b>
		<input type="password" name="passcheck" id= "passcheck" placeholder="Enter Password" required>
		<b>닉네임</b>
		<input type="text" name="name" id="name" value="${m.NAME}" maxlength=15 required>
		<input type="button" value="닉네임 중복검사" id="namecheck" class="btn btn-warning">
		<b>이메일 주소</b>
		<input type="text" name="email" id="email" value="${m.EMAIL}" placeholder="Enter email" required><span id="email_message"></span>
		<b>나이</b>
     	 <div>
     	 <input type="text" name="age" id="age" value="${m.AGE}" maxlength=2 required>
     	 </div>
     	 
		<b>성별</b>
		<div>
			<input type="radio" name="gender" value="M"><span>남자</span>
			<input type="radio" name="gender" value="F"><span>여자</span>
		</div>
		<b>취향</b>
		<div>
          <input type="checkbox" name="tag" id="tag1" value="1"><span>자연풍경</span>
         <input type="checkbox" name="tag" id="tag2" value="2"><span>인물</span>
         <input type="checkbox" name="tag" id="tag3" value="3"><span>음식</span>
         <input type="checkbox" name="tag" id="tag4" value="4"><span>동물</span>
         <input type="checkbox" name="tag" id="tag5" value="5"><span>기타</span>
        </div>
		<div class="clearfix">
			<button type="submit" class="btn btn-info left">수정</button>
			<input type="button" class="btn right" id="dropout" onclick="drop()" value="회원탈퇴">
		</div>
	</form> 
	<script>
	
	function drop(){
		var result = confirm("정말로 탈퇴하시겠습니까?"); 
		if(result) { 
	    	location.href="member_dropout.net?id=${m.ID}"; 
	     }
	}; 
	
	$("input[value='${m.GENDER}']").prop('checked',true);
	
	var tags=['${m.TAG1}','${m.TAG2}', '${m.TAG3}', '${m.TAG4}', '${m.TAG5}'];

	$("input[type=checkbox]").each(function(index, item){
	       if(tags[index]=='1')
	       $(this).prop('checked',true);
	 });
	
		var checkemail = true;
		var checkpass = false;
		var checkname = true;
		
		$('form').submit(function(){
			if(!$.isNumeric($("input[name='age']").val())){
				alert("나이는 숫자를 입력하세요");
				$("input[name='age']").val('');
				$("input[name='age']").focus();
				return false;
			}
			if(!checkname){
				alert("사용가능한 닉네임을 입력하세요.");
				$("#name").val('').focus();
				return false
			}
			if(!checkemail){
				alert("email 형식을 확인하세요");
				$("input:eq(6)").focus();
				return false;
			}
			if(!checkpass){
				alert("비밀번호를 확인하세요");
				$("#passcheck").val('').focus();
				return false;
			}
			
			var checked = $('input:checkbox:checked').length;
			if(checked<1){
				alert("취향을 1개 이상 선택하세요.");
				return false
			}
		}); //submit
		
		$("#email").on('keyup',
				function(){
					$("#email_message").empty();
					//[A-Za-z0-9_]와 동일한 것이 \w
					//+는 1회 이상 반복을 의미. {1,}와 동일하다.
					//\w+ 는 [A-Za-z0-9_]를 1개이상 사용하라는 의미이다.
					var pattern = /\w+@\w+[.]\w{3}/;
					var email = $("#email").val();
					if(!pattern.test(email)){
						$("#email_message").css('color','red').html("이메일 형식이 맞지 않습니다.");
						checkemail=false;
					}else{
						$("#email_message").css('color','green').html("이메일 형식에 맞습니다.");
						checkemail=true;
					}
		}); //email keyup 이벤트 처리 끝
		
		$("#passcheck").on('keyup', function(){
			$("#passmessage").empty();
			if( $(this).prev().prev().prev().val() != $(this).val() ){
				$("#passmessage").css('color','red').html("비밀번호가 일치하지 않습니다.");
				checkpass=false;
			}else{
				$("#passmessage").css('color','green').html("비밀번호가 일치합니다.");
				checkpass=true;
			}
		})
		
		$("#namecheck").on('click',
				function(){
				var name = $(this).prev().val();
				if(name==''){
					alert("닉네임을 입력하세요.")
				}else{
					$.ajax({
						url : "namecheck.net",
						data : {"name": name},
						success : function(resp){
							if (resp == -1){
								alert("사용 가능한 닉네임입니다.");
								checkname = true;
							} else{
								alert("사용중인 닉네임입니다.");
								checkname = false;
							}
						}
					});
				}
		});
	</script>
</body>
</html>