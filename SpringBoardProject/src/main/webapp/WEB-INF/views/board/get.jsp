<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
</head>
<body>
	<table>
		<tr>
			<td>글 번호</td>
			<td>${board.bno }</td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td>${board.title }</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td>${board.content }</td>
		</tr>
		<tr>
			<td>글 작성일</td>
			<td>${board.regdate }</td>
		</tr>
	</table>
</body>
</html>