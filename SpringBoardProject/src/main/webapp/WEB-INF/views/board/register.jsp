<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<form action="/board/register" method="post">
		글 제목<input type="text" name="title"/><br/>
		글 내용<textarea rows="10" cols="20" name="content"></textarea><br/>
		글쓴이<input type="text" name="writer"/><br/>
		<input type="submit" value="글쓰기">
	</form>
</body>
</html>