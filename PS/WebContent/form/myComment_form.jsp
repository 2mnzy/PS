<%--
	<%@ page ....> 사용에 들어가는 form
	
 --%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../main/header.jsp"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.5.0.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap_2.css">
<style>
</style>
</head>
<body>
<%String id= request.getParameter("id"); %>

<br>
<table>
  <tr>
  	<td>
	<h1 style="width:800px">내 댓글</h1>
	</td>
  </tr>
</table><hr>
<div class="container">

<%--게시글이 있는 경우 --%>
<c:if test="${listcount> 0}">
   <table class="table table-striped">
     <thead>
        <tr>
           <th colspan="3">MVC 댓글 -list</th>
         <th colspan="2">
            <font size=3>글 개수: ${listcount}</font>
         </th>
      </tr>
      <tr>
         <th><div>댓글번호</div></th>
         <th><div>내용</div></th>
         <th><div>작성자</div></th>
         <th><div>날짜</div></th>
         <th></th>
      </tr>
     </thead>
     <tbody>
        <c:set var="num" value="${listcount-(page-1)*limit}"/>
        
        <c:forEach var="c" items="${commentlist}">
        <tr>
           <td><%--번호 --%>
              <c:out value="${c.c_NUM}"/> <%--num 출력 --%>
              <c:set var="num" value="${num-1}"/> <%--num= num-1; 의미 --%>
           </td>
           <td><%--제목--%>
              <div>
                    ${c.c_CONTENT}
              </div>
           </td>
           <td><div>${c.m_ID}</div></td>
           <td><div>${c.c_DATE}</div></td>
          
           <td>
            <a href="CommentDeleteAction.com?num=${c.c_NUM }&id=${c.m_ID}">
            <button type="button">삭제</button></a>
            </td>
           
        </tr>
        </c:forEach>
     </tbody>
   </table>
   
   <div class="center-block">
     <div class="row">
       <div class="col">
         <ul class="pagination">
           <c:if test="${page<=1}">
              <li class="page-item">
                <a class="page-link gray">이전&nbsp;</a>
               </li>
           </c:if>
            <c:if test="${page>1}">
               <li class="page-item">
                 <a href="./commentlist.bo?page=${page-1}"
                    class="page-link">이전&nbsp;</a>
               </li>
            </c:if>
            
            <c:forEach var="a" begin="${startpage}" end="${endpage}">
               <c:if test="${a==page}">
                  <li class="page-item">
                     <a class="page-link gray">${a}</a>
                  </li>
               </c:if>
               <c:if test="${a!=page}">
                  <li class="page-item">
                    <a href="./commentlist.bo?page=${a}"
                       class="page-link">${a}</a>
                  </li>
               </c:if>
            </c:forEach>
            
            <c:if test="${page>=maxpage}">
              <li class="page=-item">
              <a class="page-link gray">&nbsp;다음</a>
              </li>
            </c:if>
            <c:if test="${page<maxpage}">
              <li class="page-item">
                <a href="./commentlist.bo?page${page+1}"
                   class="page-link">&nbsp;다음</a>
               </li>
            </c:if>
         </ul>
       </div>
     </div>
   </div>
</c:if>


   <!-- 레코드 없으면 -->
   <c:if test="${listcount==0}">
      <font size=5>등록된 글이 없습니다.</font>
   </c:if>
      
</div>
</body>
