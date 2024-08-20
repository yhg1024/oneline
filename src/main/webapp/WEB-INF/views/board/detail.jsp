<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- �±� ���̺귯�� ��� -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#update').click(function() {
		var updateValue = $(this).val(); 
		$(location).attr("href", "/board/update?seq="+updateValue) // get ���
	});
})
function goBack() {
	window.history.back();
}
</script>
</head>
<body>
	<h2>�Խñ� ��������</h2>
	<div>
		<p>���� : ${detail.title}</p>
		<p>�۹�ȣ : ${detail.seq}</p>
		<p>�ۼ��� : ${detail.memName}</p>
		<p>�ۼ� ��¥ : <fmt:formatDate value="${detail.regDate}" pattern="yyyy-MM-dd"/></p>
		<p>���� : ${detail.boardContent}</p>
	</div>
	<button id="update" value="${detail.seq}">����</button>
	<button onclick="location.href='/board/delete?seq=${detail.seq}'">����</button>
	<button onclick="goBack()">����Ʈ</button>
</body>
</html>