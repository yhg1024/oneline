<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- �±� ���̺귯�� ��� -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��</title>
</head>
<body>
	<h2>�Խñ� ��������</h2>
	<div>
		<div>���� : ${detail.title}</div>
		<div>�۹�ȣ : ${detail.seq}</div>
		<div>�ۼ��� : ${detail.memName}</div>
		<div>�ۼ� ��¥ : ${detail.regDate}</div>
		<div>���� : ${detail.boardContent}</div>
	</div>
	<button onclick="loction.href='/board/update/${detail.seq}'">����</button>
	<button onclick="location.href='/board/delete?seq=${detail.seq}'">����</button>
	<button onclick="location.href='/board/list'">����Ʈ</button>
</body>
</html>