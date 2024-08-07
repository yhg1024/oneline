<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardWrite</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#regBtn").click(function() {
			$("#writeForm").attr("action", "/board/write").attr("method", "post").submit();
		})
		$("#uptBtn").click(function() {
			$("#writeForm").attr("action", "/board/update").attr("method", "post").submit();
		})
	})
</script>
</head>
<body>
${detail.seq}
	<h1>게시글 작성</h1>
	<form name="writeForm" id="writeForm" method="post">
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
		<p>제목 : <input type="text" name="title" value="${detail.title}" /></p>
		<p>내용 : <br> <textarea rows="5" cols="30" name="boardContent">${detail.boardContent}</textarea></p>
		
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
