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
	
	<form action="/modify" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" value="${user.uid}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${user.name}" ></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp" value="${user.hp}" ></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age" value="${user.age}" ></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정"></td>
				
			</tr>
		</table>	
	</form>
</body>
</html>