<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>현재 보고 계신 페이지는 ${page } 페이지 입니다.</h1>
	
	<!-- jstl을 활용해서 pagr가 100미만이면 하단에 h2태그를 이용해서
		"초반부입니다", 100이상 200미만이면 "중반부입니다", 200이상이면 "후반부입니다"
		라는 문장을 추가로 출력해주세요. -->
	<c:choose>
		<c:when test="${page < 100 }"><h2>초반부입니다.</h2></c:when>
		<c:when test="${page >= 100 && page < 200 }"><h2>중반부입니다.</h2></c:when>
		<c:when test="${page >= 200 }"><h2>후반부입니다.</h2></c:when>
	</c:choose>
</body>
</html>