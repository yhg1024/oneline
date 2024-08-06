<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>수정</title>
</head>
<body>
	<h3>수정</h3>
	
	<form action="/board/update" name="updateForm" method="post">
		<p>작성자 : <input type="text" name="memName" /></p>
		<p>ID : <input type="text" name="memId" /></p>
		<p>제목 : <input type="text" name="title" /></p>
		<p>내용 : </p>
		<div><textarea type="text" name="boardContent"></textarea></div>
		<button type="submit">수정</button>	
	</form>
</body>
</html>