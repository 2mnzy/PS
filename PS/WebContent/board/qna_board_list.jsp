<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/main/header.jsp" />
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>

<style>
.center-block {
   display: flex;
   justify-content: center; /*가운데 정렬*/
}

.container {
   height: 25%;
   font-size: 10px;
}

select.form-control {
   width: auto;
   margin-bottom: 2em;
   display: inline-block
}

.rows {
   text-align: right;
}

.row {
   height: 0
}

.gray {
   color: gray
}

body > div.container > table > thead > tr:nth-child(2) > th:nth-child(1) {
   width: 8%
}

body > div.container > table > thead > tr:nth-child(2) > th:nth-child(2) {
   width: 14%
}

body > div.container > table > thead > tr:nth-child(2) > th:nth-child(3) {
   width: 50%
}

body > div.container > table > thead > tr:nth-child(2) > th:nth-child(4) {
   width: 17%
}

body > div.container > table > thead > tr:nth-child(2) > th:nth-child(5) {
   width: 11%
}

body > div.container {
   width: 800px;
}
</style>
<script src="js/list.js"></script>

<title>게시판</title>
</head>
<body>

   <div class="container">

      <%--게시글이 있는 경우 --%>
      <c:if test="${listcount> 0}">
         <table class="table table-striped">
            <thead>
               <tr>
                  <th colspan="3"><font size=3>PHOTO STORY 게시판</font></th>
                  <th colspan="2"><font size=3>글 개수: ${listcount}</font></th>
               </tr>
               <tr>
                  <th><div><font size="3">#</font></div></th>
                  <th><div><font size="3">카테고리</font></div></th>
                  <th><div><font size="3">제목</font></div></th>
                  <th><div><font size="3">작성자</font></div></th>
                  <th><div><font size="3">조회 수</font></div></th>
                  
               </tr>
            </thead>
            <tbody>
               <c:set var="num" value="${listcount-(page-1)*limit}" />
               <c:forEach var="b" items="${boardlist}">
                  <tr>
                     <td>
                        <%--번호 --%> <font size="3"><c:out value="${num}" /> <%--num 출력 --%> 
                        <c:set var="num" value="${num-1}" /></font> <%-- num= num-1; 의미 --%>
                     </td>
                     <td>
                        <%-- 카테고리 --%>
                        <div><font size="3">${b.b_TAG}</font></div>
                     </td>
                     <td>
                        <%--제목--%>
                        <div>
                           <%--답변글 제목앞에 여백처리 부분
                    BOARD_RE_LEV, BOARD_NUM, BOARD_SUBJECT, BOARD_NAME, BOARD_DATE,
                    BOARD_READCOUNT, : property 이름 --%>
                                <font size="3">
                           <a href="BoardDetailAction.bo?num=${b.b_NUM}"> ${b.b_TITLE}
                           </a>
                           </font>
                        </div>
                     </td>
                     <td><div><font size="3">${b.b_ID}</font></div></td>
                     <td><div><font size="3">${b.b_VIEW}</font></div></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>

         <div class="center-block">
      <div class="row">
      <div class="col">
        <ul class="pagination">      
          <c:if test="${page <= 1 }">
            <li class="page-item">
              <a class="page-link gray">이전&nbsp;</a>
            </li>
          </c:if>
          <c:if test="${page > 1 }">         
            <li class="page-item">
               <a href="./BoardList.bo?page=${page-1}" 
                  class="page-link">이전&nbsp;</a>
            </li> 
          </c:if>
               
         <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <c:if test="${a == page }">
               <li class="page-item">
                  <a class="page-link gray">${a}</a>
               </li>
            </c:if>
            <c:if test="${a != page }">
                <li class="page-item">
                  <div><a href="./BoardList.bo?page=${a}" 
                     class="page-link">${a}</a></div>
                </li>   
            </c:if>
         </c:forEach>
         
         <c:if test="${page >= maxpage }">
            <li class="page-item">
            <a class="page-link gray">&nbsp;다음</a> 
            </li>
         </c:if>
         <c:if test="${page < maxpage }">
           <li class="page-item">
            <div><a href="./BoardList.bo?page=${page+1}" 
               class="page-link">&nbsp;다음</a></div>
           </li>   
         </c:if>
       </ul>
      </div>
     </div>
   </div>
      </c:if><br>


      <!-- 레코드 없으면 -->
      <c:if test="${listcount==0}">
         <font size=5>등록된 글이 없습니다.</font>
      </c:if>

      <div class="container-fluid">
         <div class="row">
            <div class="col-md-12">
               <a id="modal-36430" href="#modal-container-36430" role="button"
                  class="btn" data-toggle="modal">글쓰기</a>

               <div class="modal fade" id="modal-container-36430" role="dialog"
                  aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-lg" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="myModalLabel">게시글 작성</h5>
                           <button type="button" class="close" data-dismiss="modal">
                              <span aria-hidden="true">×</span>
                           </button>
                        </div>
                        <form action="BoardAddAction.bo" method="post" name="boardform">
                           <div class="modal-body">
                              <table class="table">
                                 <tr>
                                    <td><font size="2"><select id="B_TAG" name="B_TAG">
                                          <option value="질문" selected>질문</option>
                                          <option value="자유글">자유글</option>
                                          <option value="공지사항">공지사항</option>
                                          <option value="기타">기타</option>
                                    </select></font></td>
                                 </tr>
                                 <tr>
                                    <td><font size="2">사용자명</font></td>
                                    <td><input name="B_ID" id="B_ID" value="${id}"
                                        class="form-control" readOnly></td>
                                 </tr>
                                 <tr>
                                    <td><font size="2">비밀번호</font></td>
                                    <td><font size="2"><input type="text" name="B_PASS" id="B_PASS" style="width:300px; height:30px;"></font></td>
                                 </tr>
                                 <tr>
                                    <td><font size="2">제목</font></td>
                                    <td><font size="2"><input type="text" name="B_TITLE" id="B_TITLE" style="width:300px; height:30px;"></font></td>
                                 </tr>
                                 <tr>
                                    <td><font size="2">내용</font></td>
                                    <td><font size="2"><textarea class="form-control" name="B_CONTENT"
                                          id="B_CONTENT" rows="10"></textarea></font></td>
                                 </tr>
                              </table>
                           </div>
                           <div class="modal-footer">
                              <button type="submit" class="btn btn-success">저장</button>
                              <button type="reset" class="btn btn-danger"
                                 data-dismiss="modal">닫기</button>
                           </div>
                        </form>
                     </div>

                  </div>

               </div>
            </div>
         </div>
      </div>
</body>
</html>