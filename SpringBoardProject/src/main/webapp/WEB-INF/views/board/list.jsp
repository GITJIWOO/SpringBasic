<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.bno}</td>
					<td><a href="/board/get/${list.bno}">${list.title}</a></td>
					<td>${list.writer}</td>
					<td>${list.regdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/board/register" method="get">
		<input type="submit" value="글쓰기">
	</form>
	<form action="/board/list" method="get">
		<input type="text" name="keyword" value="${keyword}">
		<input type="submit" value="검색">
	</form>
	<script>
		// 컨트롤러에서 success라는 이름으로 날린 자료가 들어오는지 확인
		// 그냥 list페이지 접근시에는 success를 날려주지 않아서
		// 아무것도 들어오지 않고
		// remove 로직의 결과로 넘어왔을 때만 데이터가 전달됨
		var result = "${success}";
		if(result === "success"){
			alert("${bno}번 글이 삭제가 완료되었습니다.");
		}
		console.log(result);
	</script>
</body>
</html>