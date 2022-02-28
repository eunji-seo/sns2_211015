<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="d-flex justify-content-between align-items-center">
	<div class="logo">
		<h1 class="text-white p-3 font-weight-bold">instartgram</h1>
	</div>
		<div>
		<c:if test="${not empty userName}">
			<span class="text-white">${userName}님 안녕하세요.</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a> <%-- get 리다이렉트 --%>
		</c:if>
		<c:if test="${empty userName}">
			<a href="/user/sign_in_view" class="mr-5 text-white font-weight-bold">로그인</a> <%-- get 리다이렉트 --%>
		</c:if>
		</div>
		
		
</div>