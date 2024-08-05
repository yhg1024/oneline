<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardList</title>
</head>
<body>
	<table border="1">
		<tr>
			<th><input type="checkbox" name="list" value="selectAll" id="selectAll"/></th>
			<th>글번호</th>
			<th>작성자(ID)</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${viewAll}" var="list" begin="0" end="10">
			<tr>
				<td><input type="checkbox" name="list" /></td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.seq}</td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.memName}</td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.title}</td>
				<td>${list.regDate}</td>
				<td>${list.uptDate}</td>
				<td>${list.viewCnt}</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" onclick="location.href='/board/write'">글쓰기</button>
	<button type="button" onclick="location.href='/board/delete?seq=${list.seq}'">삭제</button>
</body>
</html>
