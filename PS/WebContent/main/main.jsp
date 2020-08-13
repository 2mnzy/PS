<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %> 
<%@ page import="java.sql.*" %> 
<!DOCTYPE html>
<html >

<head>
	<jsp:include page="header.jsp"/>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>메인페이지</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
	<style>
		#welcome-post-sliders owl-carousel{
			padding-top:
		}
		
		#user_tag{
			text-align:center;
		}
	</style>
</head>
<body>
<% 
request.setCharacterEncoding("utf-8"); 
String realFolder = ""; 
int filename1 = 1; 
 
String fullpath ="upload_image/" +  filename1+".jpg"; 

%> 
<script>
$(".checkbox").change(function() {
    if(this.checked) {
       alert(this.val());
    }
});

</script>
<c:set var="m" value="${memberinfo}"/>
    <!-- ****** Top Header Area End ****** -->

    

    <!-- ****** Welcome Post Area Start ****** -->
    <section class="welcome-post-sliders owl-carousel">

 <c:forEach var="i" items="${imagelist}">
        <!-- Single Slide -->
        <div class="welcome-single-slide">
            <!-- Post Thumb -->
            <a href="Imageview.im?FILENAME=${i.FILENAME}&I_NUM=${i.i_NUM}">
            <img src="uploadedimage/${i.FILENAME}" alt="">
            </a>
            <!-- Overlay Text -->
            <div class="project_title">
                <div class="post-date-commnents d-flex">
                    <a href="#">May 19, 2017</a><!-- 받은 좋아요 수 출력 -->
                    <a href="#">5 Comment</a>
                </div>
                <a href="#">
                  I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street
                </a>
            </div>
        </div>
</c:forEach>

</section>
    <!-- ****** Welcome Area End ****** -->
<hr>
      <div id="user_tag">
      <b>취향</b>
         <input type="checkbox" name="tag" id="tag1" value="1"><span>자연풍경</span>
         <input type="checkbox" name="tag" id="tag2" value="2"><span>인물</span>
         <input type="checkbox" name="tag" id="tag3" value="3"><span>음식</span>
         <input type="checkbox" name="tag" id="tag4" value="4"><span>동물</span>
         <input type="checkbox" name="tag" id="tag5" value="5"><span>기타</span>
</div>
    <!-- ****** Categories Area Start ****** -->
   <section class="categories_area clearfix" id="about">
    <div class="container">
       <div class="row">
    <c:forEach var="i" items="${imagelist}">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".3s">
                        <a href="Imageview.im?FILENAME=${i.FILENAME}&I_NUM=${i.i_NUM}">
                        <img src="uploadedimage/${i.FILENAME}" alt="">
                       </a>
                    </div>
                </div>
    </c:forEach>
         </div>
         </div>
    </section>
    <!-- ****** Categories Area End ****** -->
    
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
    <script>  var tags=['${m.TAG1}','${m.TAG2}', '${m.TAG3}', '${m.TAG4}', '${m.TAG5}'];
  if(${!empty id}){
     $("input[type=checkbox]").each(function(index, item){
          if(tags[index]=='1')
          $(this).prop('checked',true);
    });
  }   
  </script>
   
</body>
