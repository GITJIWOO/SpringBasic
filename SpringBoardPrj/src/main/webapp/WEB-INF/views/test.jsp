<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	
	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY <input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	<ul id="replies">
		
	</ul>
</body>
<script>
	let bno = 2;
	
	function getAllList(){
		$.getJSON("/replies/all/" + bno, function(data){
			let str = "";
			
			$(data).each(function(){
				str += "<li data-rno='" + this.rno + "' class='replyLi'>" + this.rno + ":" + this.reply + "</li>";
			});
			$("#replies").html(str);
		});
	}
	
	$("#replyAddBtn").on("click", function(){
		
		let replyer = $("#newReplyWriter").val();
		let reply = $("#newReply").val();
		
		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"x-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				reply : reply
			}),
			success : function(result) {
				if(result === "SUCCESS") {
					alert("댓글이 등록 되었습니다.");
					getAllList();
				}
			}
		});
	});
</script>
</html>