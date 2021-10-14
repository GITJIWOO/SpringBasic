<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.uploadResult {
		width: 100%;
		background-color: gray;
	}
	.uploadResult ul {
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-content: center;
	}
	.uploadResult ul li {
		list-style: none;
		padding: 10px;
	}
	.uploadResult ul li img {
		width: 20px;
	}
</style>
</head>
<body>
	
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			<!-- 파일 목록 -->
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	
	<script>
		$(document).ready(function(){
			
			let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			let maxSize = 5242880; // 5MB
			
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과(5MB)");
					return false;
				}
				
				if(regex.test(fileName)){
					alert("해당 종류의 확장자는 업로드 할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			let cloneObj = $(".uploadDiv").clone();
			
			$("#uploadBtn").on("click", function(e){
				
				// ajax는 제출버튼을 눌렀을 떄 목적지가 없기 때문에
				// 빈 폼을 만들고 거기에 정보를 채워나갑니다.
				let formData = new FormData();
				
				let inputFile = $("input[name='uploadFile']");
				console.log(inputFile);
				
				let files = inputFile[0].files;
				console.log(files);
				
				for(var i = 0; i < files.length; i++){	
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url: "/uploadAjaxAction",
					processData: false,
					contentType: false,
					data: formData,
					type: "POST",
					dataType: "json",
					success: function(result){
						console.log(result);
						alert("Uploaded");
						showUploadedFile(result);
						$(".uploadDiv").html(cloneObj.html());
					}
				}); // ajax
			}); // end uploadBtn click
			
			let uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr){
				let str = "";
				
				$(uploadResultArr).each(function(i, obj){
					if(!obj.image){
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
						str += "<li><a href='/download?fileName=" + fileCallPath + "'>" + "<img src='/resources/다운로드.png'>" + obj.fileName + "</a>" + "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span>" + "</li>";
					} else {
						// str += "<li>" + obj.fileName + "</li>";
						
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
						
						str += "<li><a href='/download?fileName=" + fileCallPath + "'>" + "<img src='/display?fileName=" + fileCallPath + "'></a>" + "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span>" + "</li>";
					}
				});
				uploadResult.append(str);
			} // end showUploadedFile
			
			$(".uploadResult").on("click", "span", function(e){
				let targetFile = $(this).data("file");
				let type = $(this).data("type");
				
				let targetLi = $(this).closest("li");
				
				$.ajax({
					url: "/deleteFile",
					data: {fileName: targetFile, type:type},
					dataType: "text",
					type: "POST",
					success: function(result){
						alert(result);
						targetLi.remove();
					}
				});
			}); // end click span
			
		}); // end document
		
	</script>
	
</body>
</html>