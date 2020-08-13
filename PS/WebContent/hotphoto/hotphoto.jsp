<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/main/header.jsp" />
<script src="js/bootstrap/bootstrap.min.js"></script>
<title>HOT PHOTO</title>
</head>
<body>
<%
	String pages =request.getParameter("hot_tag");
	if(pages==null){
		pages="total";
	}
%>

 
	<div class="aside-left" >
		<aside>
			<jsp:include page="hotphoto_left.jsp"/>
			</aside>
		</div>
 <section>
 	<jsp:include page='<%= ""+pages+"_hot_image_list.jsp" %>'/>
 </section>

</body>
</html>