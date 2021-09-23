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
	
	<!-- 모달 코드는 작성이 안 되어있는게 아니고
		작성은 해뒀지만 css의 display옵션을 none으로 평상시에 두고
		특정한 요건을 만족했을 때만 display를 허용하도록 설계되어 있습니다
		그래서 아래와 같이 모달 예시코드를 붙여넣어도
		일반 화면에서는 보이지 않습니다. -->
	<div class="modal" id="myModal" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">글 작성 안내창</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>${vo}번 글 작성을 완료하였습니다.</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 부트스트랩용 JS파일도 마저 임포트
		코드 진행 순서가 위에서 아래이므로
		script태그 위에 먼저 js파일을 집어넣습니다. -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	
	<script>
		// 컨트롤러에서 success라는 이름으로 날린 자료가 들어오는지 확인
		// 그냥 list페이지 접근시에는 success를 날려주지 않아서
		// 아무것도 들어오지 않고
		// remove 로직의 결과로 넘어왔을 때만 데이터가 전달됨
		var result = "${result}";
		let bno = "${vo}";
		
		// 모달 사용을 위한 변수 선언
		// 모달 공식문서의 자바스크립트 관련 실행 코드를 복사합니다.
		var myModal = new bootstrap.Modal(document.getElementById("myModal"), focus);
		
		if(result === "success"){
			alert("${bno}번 글이 삭제가 완료되었습니다.");
		} else if(result === "register"){
			// 공식문서 하단의 modal.show()를 응용합니다.
			myModal.show();
		}
	</script>
	</body>
</html>