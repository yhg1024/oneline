<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상세</title>
</head>
<body>
	<h2>게시글 상세페이지</h2>
	<div>
		<div>제목 : ${detail.title}</div>
		<div>글번호 : ${detail.seq}</div>
		<div>작성자 : ${detail.memName}</div>
		<div>작성 날짜 : ${detail.regDate}</div>
		<div>내용 : ${detail.boardContent}</div>
	</div>
	<button onclick="loction.href='/board/update/${detail.seq}'">수정</button>
	<button onclick="location.href='/board/delete?seq=${detail.seq}'">삭제</button>
	<button onclick="location.href='/board/list'">리스트</button>
</body>
</html>