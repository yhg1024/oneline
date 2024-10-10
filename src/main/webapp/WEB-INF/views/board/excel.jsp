<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    response.setHeader("Content-Disposition","attachment;filename=board.xls");    //디폴트 파일명 지정
    response.setHeader("Content-Description", "JSP Generated Data"); 
%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table border = "1px">
		<tr>
			<th>글번호</th>
			<th>작성자(아이디)</th>
			<th>제목</th>
			<th>등록일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${list }" var = "i">
			<tr>
				<td>${i.seq }</td>
				<td onclick="detail(${i.seq})">${i.memName }(${i.memId })</td>
				<td onclick="detail(${i.seq})">${i.boardSubject }</td>
				<td>${i.regDate }</td>
				<td>${i.uptDate }</td>
				<td>${i.viewCnt }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>