<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<table border = "1px">
		<tr>
			<th><input type="checkbox" class="checkAll"></th>
			<th>글번호</th>
			<th>작성자(아이디)</th>
			<th>제목</th>
			<th>등록일</th>
			<th>수정일</th>
			<th>조회수</th>
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
		<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면 [처음]하이퍼링크를 화면에 출력 -->
		<c:if test="${pagination.nowPage >= 11 }">
			<span onclick="fncPageClick('1', ${pagination.listSize})">[처음]</span>
		</c:if>	
		<!-- 이전페이지 블록 이동 : 현재 페이지 블럭이 1보다 크면 [이전] -->
		<c:if test="${pagination.startPage != 1 }">
			<span onclick="fncPageClick(${pagination.startPage - 1 }, ${pagination.listSize}) ">[이전]</span>
		</c:if>
		<!-- 하나의 블럭에서 반복문 수행, 시작페이지부터 끝페이지까지 -->
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
		<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블록보다 작거나 같으면 [다음] 화면 출력 -->
		<c:if test="${pagination.endPage != pagination.lastPage}">
			<span onclick="fncPageClick('${pagination.endPage+1 }', '${pagination.listSize}')">[다음]</span>
		</c:if>
		<!-- 끝페이지 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝] 화면 출력 -->
		<c:if test="${pagination.lastPage > 10 and pagination.nowPage != pagination.lastPage }">
			<span onclick="fncPageClick('${pagination.lastPage}', '${pagination.listSize}')">[끝]</span>
		</c:if>
	</div>