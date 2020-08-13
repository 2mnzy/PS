<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>MVC 게시판 - view</title>
<jsp:include page="/main/header.jsp"/>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script>
   $(function(){
      $("form").submit(function(){
         if($("#b_pass").val()==''){
            alert("비밀번호를 입력하세요");
            $("#b_pass").focus();
            return false;
         }
      })
   })
   
   <%--
   var httpRequest = null;
   
   // httpRequest 객체 생성
   function getXMLHttpRequest() {
      var httpRequest = null;
      
      if(window.ActiveXObject) {
         try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
         } catch(e) {
            try {
               httpRequest = new ActiveXObjecct("Microsoft.XMLHTTP");
            } catch(e2) {
               httpRequest = null;
            }
         }
      } else if(window.XMLHttpRequest) {
         httpRequest = new window.XMLHttpRequest();
      }
      return httpRequest;
   }
   
   function writeCmt() {
      var form = document.getElementById("writeCommentForm");
      
      var board = form.comment_board.value
      var id = form.comment_id.value
      var content = form.comment_comment.value;
      
      if(!content) {
         alert("내용을 입력하세요.");
         return false;
      } else {
         var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
         
         httpRequest = getXMLHttpRequest();
         httpRequest.onreadystatechange = checkFunc;
         httpRequest.open("POST", "CommentWriteAction.com", true);
         httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
         httpRequest.send(param);
      }
   }
   
   function checkFunc() {
      if(httpRequest.readyState == 4) {
         //결과값을 가져온다.
         var resultText = httpRequest.responseText;
         if(resultText == 1) {
            document.location.reload();
         }
      }
   }   --%>
</script>
<style>
tr:nth-child(1){
   text-align: center
}

td:nth-child(1){
   width:20%
}

a{
   color:white;
}

tr:nth-child(5)>td:nth-child(2)>a{
   color:black
}

tbody tr:last-child{
   text-align: center;
}

.btn-primary{
   background-color: #4f97e5
}

#myModal{
   display:none;
}

.container {
   font-size : medium;
}

#writeCommentForm > table > tbody > tr > td:nth-child(3) > div > font > input[type=text] {
   width: 500px; height: 50px;
}

body > div.container > table.table.table-striped {
   width: 800px;
   margin: auto;
}

#writeCommentForm > table {
   width: 800px;
   margin: auto;
   
}

body > div.container > table:nth-child(2) {
   width: 800px;
   margin: auto;
}

body > div.container > div:nth-child(2) {
   position: relative;
   left: 250px;
}
</style>
</head>
<body>
<%
	int b = Integer.parseInt(request.getParameter("num"));
%>
<script>
function boardreport(){
	window.open("reportbo.wa?B_NUM=<%=b%>", "newWindow", "width=600, height=400, scrollbar=no");
}
</script>
  <div class="container">
    <table class="table table-striped">
      <tr><th colspan="2"><font size="2">게시판</font></th></tr>
      <tr>
            <td><p><font size="2">카테고리</font></p><p>${boarddata.b_TAG}</p></td>
            <td><p><font size="2">조회수</font></p><p>${boarddata.b_VIEW}</p></td>
      </tr>
      <tr>
           <td><div><font size="2">글쓴이</font></div></td>
           <td><div><font size="2">${boarddata.b_ID}</font></div></td>
      </tr>
      <tr>
           <td><div><font size="2">제목</font></div></td>
           <td><div><font size="2">${boarddata.b_TITLE}</font></div></td>
      </tr>
      <tr>
           <td><div><font size="2">내용</font></div></td>
           <td><textarea class="form-control" cols="50" rows="5"
                 readOnly style="width:102%">${boarddata.b_CONTENT}</textarea></td>
      </tr>
      <tr>
         <td colspan="2" class="center">
         <c:if test="${boarddata.b_ID == id || id == 'admin' }">
         
         <a href="BoardModifyView.bo?num=${boarddata.b_NUM}">
            <button class="btn btn-info">수정</button>
         </a>
         <%--href 주소를 #으로 설정합니다. --%>
         <a href="#myModal">
         <button class="btn btn-danger" data-toggle="modal"
               data-target="#myModal">삭제</button>
         </a>
         </c:if>
       <a href="BoardList.bo">
          <button class="btn btn-primary">목록</button>
       </a>
        <a href="#">
          <button class="btn btn-warning" onclick="boardreport()" id="button_report">신고</button>
       </a>
    </table>
    <div>
       <c:forEach var="comment" items="${commentlist}">
             <!-- 아이디, 작성 날짜 -->
             <!--  <td width="150">-->
                <div>
                   ${comment.m_ID}<br>
                   <font size="2" color="lightgray">${comment.c_DATE}</font>
                </div>
             <!-- 본문 내용 -->
             <!-- <td width="550"> -->
                <div class="text_wrapper">
                   ${comment.c_CONTENT}
                </div>
             <!-- 버튼 -->
             <!--  <td width="100">-->
                <div id="btn" style="text-align:center;">
                   <a href="#">[답변]</a><br>
                   <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
                   <c:if test="${comment.m_ID == sessionScope.sessionID}">
                      <a href="#">[수정]</a><br>
                      <a href="#">[삭제]</a>
                   </c:if>
                </div>
       </c:forEach>
    </div>
   
    <!-- 로그인 했을 경우만 댓글 작성 가능 -->
    <c:if test="${boarddata.b_ID != null}">
       <form action="CommentAddAction.com" id="writeCommentForm" >
       <input type="hidden" name="num" value="${param.num}">
        <%--http://localhost:8088/PS/BoardDetailAction.bo?num=1 에서 보여주는 페이지이기 때문에 이곳에서 파라미터로 넘길 num을 저장합니다.
                                이것을 submit했을 때 같이  CommentAddAction.com으로 전달합니다. 제 게시판에서도 똑같이 적용하고 있습니다.--%>
       <table>
      <tr bgcolor = "#F5F5F5">
      <td>
      <%-- ${boarddata.b_NUM} 값이 보드 번호 맞나요? 네 맞아요--%>
          <input type="hidden" name="comment_board" value="${boarddata.b_NUM}">
          <input type="hidden" name="comment_id" value="${boarddata.b_ID}"></td>
          <!-- 아이디 -->
          <td width="150">
             <div><font size="2">
                ${boarddata.b_ID}
             </font></div>
          </td>
          <!-- 본문 작성 -->
          <td width="500">
             <div>
                <font size="2"><input type="text" name="comment_content" rows="4" cols="70"></font>
             </div>
          </td>
          <!-- 댓글 등록 버튼 -->
          <td width="100">
             <div id="btn" style="text-align:center;">
             <div><button type="submit"><font size="2">등록</font></button></div>
             </div>
          </td>
       </tr>
       </table>
       </form>
       
    </c:if>
           <%--게시판 수정 end --%>
           
           <%--modal 시작 --%>
           <div class="modal" id="myModal">
             <div class="modal-diaolog">
               <div class="modal-content">
                 <!-- Modal body -->
                   <div class="modal-body">
                     <form name="deleteForm" action="BoardDeleteAction.bo" method="post">
                        <%--http://localhost:8088/Board_Ajax_bootstrap/BoardDetailAction.bo?num==22
                            주소를 보면 num을 파라미터로 넘기고 있습니다. 
                           이값을 가져와서 ${param.num}를 사용
                           또는 ${boarddata.BOARD_NUM}
                         --%>
                         <input type="hidden" name="num" value="${boarddata.b_NUM}">
                         <div class="form-group">
                            <label for="pwd">진심이에요..?</label>
                            <input type="password"
                                  class="form-control" placeholder="비밀번호를...."
                                  name="B_PASS" id="B_PASS">
                         </div>
                         <button type="submit" class="btn btn-primary">전송</button>
                         <button type="button" class="btn btn-dnager"
                                data-dismiss="modal">취소</button>
                     </form>
                    </div>
               </div>
                   
             </div>
           </div>
  </div>
</body>
</html>