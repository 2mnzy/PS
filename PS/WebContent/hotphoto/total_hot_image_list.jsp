<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="photo-section">
	<section class="categories_area clearfix" id="about">
    <div class="container">
       <div class="row">
    		<c:forEach var ="i" items="${totalList }">
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

	
 </div>
