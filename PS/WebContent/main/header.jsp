<%-- 로그인 했을 때와 안했을 때 
	
	1. 로그인시 관리자인지 확인하는것 입력해야 합니다.
	
	2. 관리자, 사용자 별로   <% if %> <% else %> 사용 해야 합니다.
	
	3. 로그인 버튼 클릭시 modal 로그인 폼을 <header> 아랫부분에 code 추가 하면 될것 같습니다.
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap/popper.min.js"></script>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
 <link href="style.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> <!-- 버튼 CSS -->
<style>
.bg-light{
	border: 1px solid rgba(0, 0, 0, 0.1);
}

#ps-logo{
	width:80px;

	
}
.form-group{margin-left:20px; margin-right:20px;}
.orange{color:white;background:orange;}
#loginbtn{margin-top:15px; margin-left:10px;border-radius: .25rem}
.navbar-nav{
	margin-left:auto;
}
.navbar-collapse {
	
	margin-top:auto;
}
.modal-content{padding:10px}
.modal img{margin:0 auto; margin-top:50; width:230}

table{
	margin-bottom:30px;
	margin-left:20px;
	font-size:0.5;
	
}
td {
	
	width: 100px;
}


button .close{
    position: absolute;
    top: 10;
    right: 10;
    width:30px;
}
.right{float:right; }

form{padding:0 20 20 20;}


</style>

<script>
$(function(){
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
	
	$('#mypost').on("click", function(){
	     window.location.href= $(this).attr('href');
	});
	
	$('#mycomment').on("click", function(){
	     window.location.href= $(this).attr('href');
	});
	
	$("#loginbtn").on('click',
			function(){
			var id = $("#id").val();
				$.ajax({
					url : "Remember.net",
					data : {"id": id},
					success : function(resp){
							var id=resp;
							console.log("id값은~"+id);
								if(id){
									$("#id").val(id);
									$("#remember").prop("checked",true);
								}
						}
				});
	});
})

window.onload = function(){
	$.ajax({
		url : "myboardcount.bo",
		data : {"id":'${id}'},
		success : function(resp){
			document.getElementById("mypost").innerText += resp;
		}
	});
	
	$.ajax({
		url : "mycommentcount.com",
		data : {"id":'${id}'},
		success : function(resp){
			document.getElementById("mycomment").innerText += resp;
		}
	});
	
	$.ajax({
		url : "myname.net",
		data : {"id":'${id}'},
		success : function(resp){
			document.getElementById("myname").innerText += resp;
		}
	});
	
	$.ajax({
		url : "myimagecount.im",
		data : {"id":'${id}'},
		success : function( data){
			document.getElementById("myimage").innerText += data;
		}
	});
	
	$.ajax({
		url : "mywarncount.wa",
		data : {"id":'${id}'},
		success : function( data){
			document.getElementById("mywarn").innerText += data;
		}
	});
	
	
	$.ajax({
		url : "myimagelikecount.im",
		data : {"id":'${id}'},
		success : function( data){
			document.getElementById("mylikecount").innerText += data;
		}
	});
	
	
};

</script>
 
<%
%>
 <!-- ****** Top Header Area Start ****** -->
    <div class="top_header_area">
        <div class="container">
            <div class="row">
                <div class="col-5 col-sm-6">
                    <!--  Top Social bar start -->
                    <div class="top_social_bar">
                        <a href="#" id="myname"></a>
                        <a href="myboard.bo?id=${id }" id="mypost">내 게시글</a>
                        <a href="#" id="myimage">내 사진</a>
                        <a href="mycomment.com?id=${id }"  id="mycomment">내 댓글</a>
                        <a href="#" id="mylikecount">받은하트</a>
                        <a href="#" id="mywarn">신고횟수</a>
                    </div>
                </div>
                <!--  Login Register Area -->
                
            </div>
        </div>
    </div>
    <!-- ****** Top Header Area End ****** -->
    <!-- ****** Header Area Start ****** -->
    <header class="header_area">
        <div class="container">
            <div class="row">
                <!-- Logo Area Start -->
                <div class="col-3">
                    <div class="logo_area ">
                        <a href="index.jsp">
                        	<img class="icon" id="icon"src="image/Logo.png">
                        </a>
                    </div>
                </div>
            <!-- </div> -->

           <!--  <div class="row"> -->
                <div class="col-8">
                    <nav class="navbar navbar-expand-lg">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav" aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true"></i> Menu</button>
                        <!-- Menu Area Start -->
                        <div class="collapse navbar-collapse justify-content-center" id="yummyfood-nav">
                            <ul class="navbar-nav" id="yummy-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="total_hot_image_list.im">HOT PHOTO <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item ">
                                    <a class="nav-link" href="BoardList.bo">게시판</a>
                                </li>
                                <c:if test="${id=='admin'}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">관리자</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="board_warn_board_list.wa">관리자페이지</a>
                                        <a class="dropdown-item" href="BoardList.bo">게시판</a>
                                    </div>
                                </li>
                                </c:if>
                                 <c:if test='${empty id }'>
                                  <li class="nav-item">
							      	<button type="button" class="btn btn-primary disabled" data-toggle="modal" data-target='#myModal' id="loginbtn" >로그인</button>
      								  <div class="modal" id="myModal">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="right">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
													<img alt="logo" src="image/Logo.png">
									  				<form name="loginform" action="loginProcess.net" method="post">
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
													  		<input class="form-check-input" type="checkbox" name="remember" id="remember" value="store">Remember me
													  		</label>
													  	</div>
														<div class="modal-footer">
														 <button type="submit" class="w3-btn orange w3-round" >로그인</button>
														 <button type="button" class="w3-btn w3-border w3-text-orange w3-white w3-round w3-border-orange" id="join">회원가입</button>
														</div>
													</form>
												</div>
											</div>
										</div>
								      </li>     
     							 </c:if>
     							 <c:if test="${!empty id }">
                                <li class="nav-item">
                                    <a class="nav-link" href="member_update.net">마이페이지</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="logout.net">로그아웃</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="Imageuploadview.im?id=${id }">업로드</a>
                                </li>
                                </c:if>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
      </div> 
    </header>
    <!-- ****** Header Area End ****** -->






