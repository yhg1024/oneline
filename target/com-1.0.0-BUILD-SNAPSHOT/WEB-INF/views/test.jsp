<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>작성자(ID)</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${viewAll}" var="list" begin="0" end="10">
			<tr>
				<td>${list.seq}</td>
				<td>${list.memName}</td>
				<td>${list.title}</td>
				<td>${list.regDate}</td>
				<td>${list.uptDate}</td>
				<td>${list.viewCnt}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
