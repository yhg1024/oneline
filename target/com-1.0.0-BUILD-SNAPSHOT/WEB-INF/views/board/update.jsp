<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- �±� ���̺귯�� ��� -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����</title>
</head>
<body>
	<h3>����</h3>
	
	<form action="/modify" method="post">
		<table border="1">
			<tr>
				<td>���̵�</td>
				<td><input type="text" name="uid" value="${user.uid}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>�̸�</td>
				<td><input type="text" name="name" value="${user.name}" ></td>
			</tr>
			<tr>
				<td>�޴���</td>
				<td><input type="text" name="hp" value="${user.hp}" ></td>
			</tr>
			<tr>
				<td>����</td>
				<td><input type="number" name="age" value="${user.age}" ></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="����"></td>
				
			</tr>
		</table>	
	</form>
</body>
</html>