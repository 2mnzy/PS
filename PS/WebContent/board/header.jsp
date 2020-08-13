<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap/popper.min.js"></script>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
 <link href="style.css" rel="stylesheet">
<style>
	body>nav.navbar{
		justify-content: flex-end; /*오른쪽 정렬*/
	}
	.dropdown-menu{
		min-width:0rem;
	}
	
	/* nav 색상 지정*/
	.navbar{
		background: #096988;
		margin-bottom: 3em;
		padding-right:3em;
	}
	
	.navbar-dark .navbar-nav .nav-link{
		color: rgb(255,255,255);
	}
</style>
<c:if test="${empty id}">
	<script>
		location.href="login.net";
	</script>
</c:if>

<nav class="navbar navbar-expand-sm right-block navbar-dark">
	<ul class="navbar-nav">
		<c:if test="${!empty id}">
			<li class="nav-item">
			<a class="nav-link" href="logout.net">
					${id}님 (로그아웃)</a></li>
			<li class="nav-item"><a class="nav-link"
				href="member_update.net">정보수정</a></li>
				
			<c:if test="${id=='admin'}">
				<!-- Dropdown -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown">관리자</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="member_list.net">회원정보</a>
							<a class="dropdown-item" href="BoardList.bo">게시판</a>
						</div></li>
			</c:if>
		</c:if>
	</ul>
</nav>

