<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>MVC 게시판</title>
<jsp:include page="/main/header.jsp"/>
	
<style>
	h1{font-size:1.5rem; text-align:center; color:#1a92b9}
	.container{width:60%}
	#upfile{display:none}
</style>
</head>
<body>
<!-- 게시판 수정 -->
<div class="container">
<form action="BoardModifyAction.bo" method="post"
	   name="modifyform">
	  <input type="hidden" name="b_num" value="${boarddata.b_NUM}">
	 <h1>게시판</h1>
	 <div class="form-group">
	 	<label for="label_b_tag">카테고리</label>
	 	<div><select id="B_TAG" name="B_TAG">
			<option value="질문" selected>질문</option>
			<option value="자유글">자유글</option>
			<option value="공지사항">공지사항</option>
			<option value="기타">기타</option>
		</select></div>
	 </div>
	 <div class="form-group">
	 	<label for="label_b_name">글쓴이</label> <input type="text" 
	 		class="form-control" value="${boarddata.b_ID}" readOnly>
	 </div>
	 <div class="form-group">
	 	<label for="label_b_title">제목</label> <input type="text" 
	 		name="B_TITLE" id="B_TITLE" maxlength="100"
	 		class="form-control" value="${boarddata.b_TITLE}">
	 </div> 
	 <div class="form-group">
      <label for="label_b_content">내용</label>
      <textarea name="B_CONTENT" id="B_CONTENT" 
          class="form-control" rows="15">${boarddata.b_CONTENT}</textarea>
   	</div>
   <%--원문글인 경우에만 파일 첨부 수정 가능합니다.
   <c:if test="${boarddata.BOARD_RE_LEV==0 }">
	 <div class="form-group read">
	 	<label for="board_file">파일  첨부</label>
	 	<label for="upfile">
	 		<img src="image/attach.png" alt="파일첨부" width="20px">
	 	</label>
	 	<input type="file" id="upfile" name="BOARD_FILE">
	 	<span id="filevalue">${boarddata.BOARD_FILE}</span>
	 	<img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
	 </div>
	 <div class="form-group">
	 	<button type=submit class="btn btn-primary">등록</button>
	 	<button type=reset class="btn btn-primary">취소</button>
	 </div>--%>
	
	<div class="form-group">
		<label for="b_pass">비밀번호</label>
		<input name="b_pass"
			   id="b_pass" type="password" size="10" maxlength="30"
			   class="form-control" placeholder="Enter b_pass" value="">
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-primary">수정</button>
		<button type="reset" class="btn btn-danger" onClick="history.go(-1)">취소</button>
	</div>
  </form>
	<!-- 게시판 수정 -->
</div>
</body>
</html>