<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>관리자 페이지(사용자 신고)</title>

</head>
    <style>
   .center-block {
     display: flex;  
     justify-content:center; /* 가운데 정렬 */
   }
   select.form-control{
         width:auto;margin-bottom:2em;display:inline-block}

   .gray{color:gray}
   div.table-section{width:80%;float:right;padding-left:100px;}
   section{width:80%;justify-content:center;}
  table>thead>tr:nth-child(2)>th:nth-child(1){width:30%;}
  table>thead>tr:nth-child(2)>th:nth-child(2){width:35%;}
  table>thead>tr:nth-child(2)>th:nth-child(3){width:10%;}  
  table>thead>tr:nth-child(2)>th:nth-child(4){width:30%;}
  table>thead>tr:nth-child(3)>th:nth-child(1){width:10%;}
  table>thead>tr:nth-child(3)>th:nth-child(2){width:10%;}
  table>thead>tr:nth-child(3)>th:nth-child(4){width:12%;}
  table>thead>tr:nth-child(3)>th:nth-child(5){width:10%;}
 </style>

<body>
<%
	String pagefile =request.getParameter("WarnLinkPage");
	
	if(pagefile==null){
		pagefile="board";
	}
%>
		<!--  헤더 -->
		<jsp:include page="../main/header.jsp"/>
		
		<!--  관리자 신고 게시판 좌측 메뉴-->
		<div class="aside-left" >
		
			<aside>
				<jsp:include page="warn_left.jsp"/>
			</aside>
			
		</div>

<div class="table-section">
  <section>
  
<%-- 게시글이 있는 경우--%> 
<c:if test="${listcount > 0 }">
 
  <table class="table table-striped">
   <thead>
	<tr>
	   <th colspan="6"><font size=5>신고된 사용자 - list</font></th>
	   </tr>
	   <tr>
	   <th colspan="2">
			<font  size=3>신고된 사용자 : ${listcount}</font>
	   </th>
	   <th></th>
	   <th></th>
	   <th colspan="2"> <div class="rows">
    <span>줄보기</span>
	<select class="form-control" id="viewcount">
	  <option value="1">1</option>
	  <option value="3">3</option>
	  <option value="5">5</option>
	  <option value="7">7</option>
	  <option value="10" selected>10</option>
	</select>
  </div></th>
	</tr>
	<tr>
		<th>번호</th>
		<th>회원명</th>	
		<th colspan="2">신고사유</th>
		<th>신고횟수</th>
		<th>삭제</th>
	</tr>	
   </thead>
   <tbody>
   
	<c:set var="num" value="${listcount-(page-1)*limit}"/>	
	<c:forEach var="b" items="${warnmemberlist}">	
	
	
	<tr>
	  <td><%--번호 --%>
			<c:out value="${ b.ROWNUM}"/><%-- num 출력 --%>	
	  </td>	 
		<td><div>${b.WM_ID}</div></td>	
		<td colspan="2"><a href="#" id="modal-title"  data-toggle="modal" data-target='#WarnmemberModal${b.ROWNUM}'>${b.WM_TAG1}  ${b.WM_TAG2}</a></td><!-- modal  처리부분 -->
		<td><div>${b.WM_COUNT}</div></td>
		<td><button type="button">삭제</button></td>
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
				   <a href="./member_warn_board_list.wa?page=${page-1}" 
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
					   <a href="./member_warn_board_list.wa?page=${a}" 
					      class="page-link">${a}</a>
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
				<a href="./member_warn_board_list.wa?page=${page+1}" 
				   class="page-link">&nbsp;다음</a>
			  </li>	
			</c:if>
		 </ul>
		</div>
	  </div>
	</div>
  </c:if>
	
	<%
		
	%>
	<!-- 제목 클릭시 모달 부분 -->
		
	
	<c:forEach var="bo" items="${warnmember}">	
    	

        <div class="modal" id="WarnmemberModal${bo.ROWNUM }">
		<div class="modal-dialog">
			<div class="modal-content">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-header">
					<a  ><font size=2>&nbsp;&nbsp;&nbsp;  ID :  "${bo.WM_ID }"&nbsp;&nbsp;&nbsp;&nbsp;신고 횟수 : "${bo.WM_TAG1_COUNT + bo.WM_TAG2_COUNT }"</font></a>
				</div>
				<form name="WarnMemberDelete" action="WarnMemberDeleteAction.wa" method="post">
					  	<table style="margin-top:20px;">
					  		<tr>
					  		<tr>
					  		</tr>
					  		<tr>
					  		<td colspan="2">신고사유 : ${bo.WM_TAG1 } ${bo.WM_TAG2 }</td>
					  		</tr>
					  		<tr>
					  			<td colspan="2"></td>
					  			</tr>
					  			
					  			<tr>
					  			<td colspan="2">신고 사유${bo.WM_TAG1 } ${bo.WM_TAG2 }</td>
					  			</tr>
					  	</table>
					  	
					  	
  					</form>

				<div class="modal-footer">
				 <button type="button" class="btn btn-info disabled">삭제</button>
				 
				</div>
			</div>
		</div>
		</div>
          
      </c:forEach>
	
	<!-- 레코드가 없으면 -->
	<c:if test="${listcount == 0 }">
			<font size=5>등록된 글이 없습니다.</font>
	</c:if>
	</section>
</div>

		
</body>
</html>