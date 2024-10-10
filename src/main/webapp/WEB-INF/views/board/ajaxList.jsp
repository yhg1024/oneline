<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<table border = "1px">
		<tr>
			<th><input type="checkbox" class="checkAll"></th>
			<th>�۹�ȣ</th>
			<th>�ۼ���(���̵�)</th>
			<th>����</th>
			<th>�����</th>
			<th>������</th>
			<th>��ȸ��</th>
		</tr>
		
		<c:forEach items="${list }" var = "i">
			<tr>
				<td><input type="checkbox" value="${i.seq }" class="checkBox"></td>
				<td>${i.seq }</td>
				<td onclick="detail(${i.seq})">${i.memName }(${i.memId })</td>
				<td onclick="detail(${i.seq})">${i.boardSubject }</td>
				<td>${i.regDate }</td>
				<td>${i.uptDate }</td>
				<td>${i.viewCnt }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="paginatinon">
		<!-- ó���������� �̵� : ���� �������� 1���� ũ�� [ó��]�����۸�ũ�� ȭ�鿡 ��� -->
		<c:if test="${pagination.nowPage >= 11 }">
			<span onclick="fncPageClick('1', ${pagination.listSize})">[ó��]</span>
		</c:if>	
		<!-- ���������� ��� �̵� : ���� ������ ���� 1���� ũ�� [����] -->
		<c:if test="${pagination.startPage != 1 }">
			<span onclick="fncPageClick(${pagination.startPage - 1 }, ${pagination.listSize}) ">[����]</span>
		</c:if>
		<!-- �ϳ��� ������ �ݺ��� ����, �������������� ������������ -->
		<c:forEach begin="${pagination.startPage }" end="${pagination.endPage }" var="p">
			<c:choose>
				<c:when test="${p == pagination.nowPage }">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != pagination.nowPage }">
					<span class="page" onclick="fncPageClick('${p }', '${pagination.listSize}')">${p }</span>
				</c:when>
			</c:choose>
		</c:forEach>
		<!-- ���������� ������� �̵� : ���� ������ ���� ��ü ������ ��Ϻ��� �۰ų� ������ [����] ȭ�� ��� -->
		<c:if test="${pagination.endPage != pagination.lastPage}">
			<span onclick="fncPageClick('${pagination.endPage+1 }', '${pagination.listSize}')">[����]</span>
		</c:if>
		<!-- �������� �̵� : ���� �������� ��ü ���������� �۰ų� ������ [��] ȭ�� ��� -->
		<c:if test="${pagination.lastPage > 10 and pagination.nowPage != pagination.lastPage }">
			<span onclick="fncPageClick('${pagination.lastPage}', '${pagination.listSize}')">[��]</span>
		</c:if>
	</div>