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
	
	<form action="/board/update" name="updateForm" method="post">
		<p>�ۼ��� : <input type="text" name="memName" /></p>
		<p>ID : <input type="text" name="memId" /></p>
		<p>���� : <input type="text" name="title" /></p>
		<p>���� : </p>
		<div><textarea type="text" name="boardContent"></textarea></div>
		<button type="submit">����</button>	
	</form>
</body>
</html>