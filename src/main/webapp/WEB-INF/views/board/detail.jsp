<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#update').click(function() {
		var updateValue = $(this).val(); 
		$(location).attr("href", "/update?seq="+updateValue) // get 방식
	});
})
function goBack() {
	location.href = '/list'
}
</script>
</head>
<body>
	<h1>${detail.seq }상세페이지</h1>
	<div>작성자 : ${detail.memName }</div>
	<div>ID : ${detail.memId}</div>
	<div>제목 : ${detail.boardSubject}</div>
	<div>내용 : ${detail.boardContent}</div>
	<div>첨부 파일 : </div>
	<c:forEach var="file" items="${fileList}">
		<img src="/img/${file.fileSeq }?seq=${file.fileSeq }"><br>
		<a href="<c:url value='/fileDownload?seq=${file.fileSeq }'/>" target="_blank">${file.realName }</a><br>
	</c:forEach>
	<button id="update" value="${detail.seq}">수정</button>
	<button onclick="location.href='/delete?seq=${detail.seq}'">삭제</button>
	<button onclick="goBack()">리스트</button>
</body>
</html>