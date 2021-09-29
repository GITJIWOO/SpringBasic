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
			<td>${board.bno}</td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td>${board.title}</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td>${board.content}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<td>글 작성일</td>
			<td>${board.regdate}</td>
		</tr>
		<tr>
			<td>글 최종수정일</td>
			<td>${board.updatedate}</td>
		</tr>
	</table>
	
	<!-- 수정페이지로 넘어가는 버튼 -->
	<form action="/board/boardmodify/${board.bno}" method="post">
		<input type="hidden" value="${board.bno}" name="bno">
		<input type="hidden" value="${param.pageNum}" name="pageNum">
		<input type="hidden" value="${param.searchType}" name="searchType">
		<input type="hidden" value="${param.keyword}" name="keyword">
		<input type="submit" value="수정하기">
	</form>
	
	<!-- 목록으로 돌아가는 버튼 -->
	<!-- pageNum, searchType, keyword -->
	<a href="/board/list?pageNum=${param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}">돌아가기</a>
	
	<!-- 글 삭제용 버튼 -->
	<button onclick="remove()">삭제하기</button>
	<script>
		function remove(){
			if(confirm("삭제하시겠습니까?")) {
				let choice = document.getElementById("remove");
				choice.submit();
			} else {
				location.href="/board/get/${board.bno}";
			}
		}
	</script>
	<script>
		var result = "${modifysuccess}";
		if(result === "modifysuccess"){
			alert("${bno}번 글 수정이 완료되었습니다.");
		}
		console.log(result);
	</script>
</body>
</html>