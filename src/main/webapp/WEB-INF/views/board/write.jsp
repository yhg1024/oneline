<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function() {
	$("#regBtn").click(function() {
		$("#writeForm").attr("action", "/insert").attr("method", "post").submit();
	})
	$("#uptBtn").click(function() {
		$("#writeForm").attr("action", "/update").attr("method", "post").submit();
	})
	
	$(".fileDel").click(function(){
		$(this).prev().val('');
		$(this).prev().prev().children().remove();
	})
	
	var tmp = 0;
	$("#fileAdd").click(function(){
		// 이미지 파일 첨부 버튼 추가 하기
        // 새로운 div 생성
        
        var file = document.createElement('input');
		file.setAttribute("type", "file");
		file.setAttribute("class", "fileUpload");
		file.setAttribute("name", "fileUpload" + tmp);
        var del = document.createElement('input');
		del.setAttribute("type", "button");
		del.setAttribute("class", "fileDel");
		del.setAttribute("value", "-");
		
		del.onclick = function() {
			$(this).prev().prev().children().remove();
			$(this).prev().remove();
			$(this).remove();
		}

		// 추가
        $("#fileDiv").append(file);
        $("#fileDiv").append(del);
        $("#fileDiv").append('<div></div>');
        
        tmp++;
	})
	
	$(document).on("change", ".fileUpload", function(e){
		var file = this.files[0]; // 한개만 첨부해도 배열로 인식한다.
		var fileName = file.name;
		var ext = fileName.slice(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "jpeg" || ext == "bmp")) {
			alert("이미지파일 (.jpg, .jpeg, .png, .gif, .bmp) 만 업로드 가능합니다.");
			$(this).val(''); // 파일 입력 초기화
			return false;
		} 	

		// 이미지 크기 체크
		var img = new Image();
		img.onload = function () {
            // 이미지의 가로와 세로 차원을 검사
            var maxWidth = 500;
            var maxHeight = 500;
            if (img.width > maxWidth || img.height > maxHeight) {
                // 차원 초과시 경고후 해당 이미지의 차원도 보여줌
                alert('이미지 크기는 500x500 픽셀 이하여야 합니다.\n\n' + '현재 이미지: ' + img.width + 'x' + img.height + ' 픽셀');
                $(e.target).val(''); // 파일 입력 필드 초기화
            }
        };
        img.src = URL.createObjectURL(file); // createObjectURL : 브라우저에 임시 저장되도록 파일에 url을 만들어준다.
        $(this).prev().append(img);
	});
});
</script>
</head>
<body>
	<h3>게시글 작성</h3>
	<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
		<c:choose>
			<c:when test="${empty detail.seq}">
				<p>작성자 : <input type="text" name="memName" value="${detail.memName}" /></p>
				<p>ID : <input type="text" name="memId" value="${detail.memId}" /></p>
			</c:when>
			<c:otherwise>
				<p>작성자 : ${detail.memName}</p>
				<p>ID : ${detail.memId}</p>
			</c:otherwise>
		</c:choose>
		<div>제목 : <input type="text" name="boardSubject"></div>
		<div>내용 : </div>
		<textarea rows="10" cols="40" name="boardContent"></textarea><br>
		<div id="fileDiv">
			파일첨부 : <input type="button" id="fileAdd" value="추가"><div></div>
	        </div>
		<c:choose>
			<c:when test="${empty detail.seq}">
				<input type="button" name="regBtn" id="regBtn" value="등록">
			</c:when>
			<c:otherwise>
				<input type="button" name="uptBtn" id="uptBtn" value="수정">
				<input type="hidden" name="seq" id="seq" value="${detail.seq}">
			</c:otherwise>
		</c:choose>	
			<input type="button" name="canBtn" id="canBtn" value="뒤로가기" onclick="history.go(-1)">
	</form>
</body>
</html>