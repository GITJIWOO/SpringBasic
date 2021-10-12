<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All</title>
</head>
<body>
	<h1>All 페이지</h1>
	
	<sec:authorize access="isAnonymous()">
		<!-- 로그인 안한(익명)사용자인 경우 -->
		<a href="/customLogin">로그인</a>
		<a href="/secu/join">회원가입</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<!-- 로그인 한(인증된)사용자인 경우 -->
		<p><sec:authentication property="principal.member.userid"/>님 환영합니다.</p>
		<!-- 아래와 같이 var 속성을 지정하면 property의 정보를 var변수에 저장합니다. -->
		<sec:authentication property="principal" var="secuInfo"/>
		<c:if test="${secuInfo.member.userName eq '운영자25'}">
			${secuInfo.member.userName}님 너무 반갑습니다.<br/>
		</c:if>
		<a href="/customLogout">로그아웃</a>
	</sec:authorize>
</body>
</html>