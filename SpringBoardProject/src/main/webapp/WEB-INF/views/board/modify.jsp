<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.content {
		weight: 500px;
	}
</style>
</head>
<body>
	<div class="content">
		<!-- 수정을 하면서 결과페이지에 페이지번호, 검색조건, 키워드를 넘겨야함
			수정로직은 post이기때문에 어쩔수 없이 컨트롤러를 경유해서 데이터를 전달합니다.
			controller내부의 modify메서드에 위의 3가지 정보를 모두 처리할 수 있는
			SearchCriteria를 추가로 선언해서 받아줍니다. -->
		<form action="/board/modify" method="post">
			<input type="hidden" value="${board.bno}" name="bno">
			<input type="hidden" value="${param.pageNum}" name="pageNum">
			<input type="hidden" value="${param.searchType}" name="searchType">	
			<input type="hidden" value="${param.keyword}" name="keyword">
			글 번호<input type="text" name="bno" value="${board.bno}" class="form-control" readonly/><br/>
			글 제목<input type="text" name="title" value="${board.title}" class="form-control"/><br/>
			글 내용<textarea rows="10" cols="20" name="content" class="form-control">${board.content}</textarea><br/>
			글쓴이<input type="text" name="writer" value="${board.writer}" class="form-control" readonly/><br/>
			<input type="hidden" value="${board.bno}"><br/>
			<input type="submit" value="수정하기">
		</form>
		<!-- 목록으로 돌아가는 버튼 -->
		<form action="/board/list" method="get">
			<input type="submit" value="돌아가기">
		</form>
	</div>
</body>
</html>