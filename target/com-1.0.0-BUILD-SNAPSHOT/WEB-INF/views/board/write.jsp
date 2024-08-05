<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>biardWrite</title>
</head>
<body>
	<h1>게시글 작성</h1>
	<form action="/board/writeProc" name="writeForm" method="post">
		<p>작성자 : <input type="text" name="memName" /></p>
		<p>ID : <input type="text" name="memId" /></p>
		<p>제목 : <input type="text" name="title" /></p>
		<p>내용 : </p>
		<div><textarea type="text" name="boardContent"></textarea></div>
		<button type="submit">등록</button>
	</form>
</body>
</html>
