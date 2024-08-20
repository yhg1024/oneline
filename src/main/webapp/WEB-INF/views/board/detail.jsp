<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#update').click(function() {
		var updateValue = $(this).val(); 
		$(location).attr("href", "/board/update?seq="+updateValue) // get 방식
	});
})
function goBack() {
	window.history.back();
}
</script>
</head>
<body>
	<h2>게시글 상세페이지</h2>
	<div>
		<p>제목 : ${detail.title}</p>
		<p>글번호 : ${detail.seq}</p>
		<p>작성자 : ${detail.memName}</p>
		<p>작성 날짜 : <fmt:formatDate value="${detail.regDate}" pattern="yyyy-MM-dd"/></p>
		<p>내용 : ${detail.boardContent}</p>
	</div>
	<button id="update" value="${detail.seq}">수정</button>
	<button onclick="location.href='/board/delete?seq=${detail.seq}'">삭제</button>
	<button onclick="goBack()">리스트</button>
</body>
</html>