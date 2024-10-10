<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){	
		$(".search").click(function(){
			// $(".searchForm").attr("action","/list").submit();
			list();
			$("#nowPage").val(1);
		});
		
	    $("input.insert").click(function(){
	        location.href = '/insert'
	    });
	    
	    var checkList = [];
	    
	    $(".checkAll").click(function(){
	    	if ($(".checkAll").is(":checked")){
	    		$(".checkBox").prop("checked", true);
	    	} else {
	    		$(".checkBox").prop("checked", false);
	    	}

	    	if($(".checkBox").is(":checked")) {
	    		$(".checkBox:checked").each(function(){
		    		var value = $(this).val();
		    		checkList.pop(value);
		    	})
	    	}
	    	
	    })
	    
	    $(".checkBox").click(function(){
	    	if ($(".checkBox:checked").length < 10){
	    		if ($(".checkAll").is(":checked")){
	    			$(".checkAll").prop("checked", false);
		    	} 
	    	} else {
	    		$(".checkAll").prop("checked", true);
	    	}
	    	if($(".checkBox").is(":checked")) {
	    		$(".checkBox:checked").each(function(){
		    		var value = $(this).val();
		    		checkList.pop(value);
		    	})
	    	}
	    })
	    
	    $(".delete").click(function(){
	    	var len = $(".checkBox:checked").length;
	    	if (len >= 1){
		    	$(".checkBox:checked").each(function(){
		    		var value = $(this).val();
		    		checkList.push(value);
		    	})
	    	} else {
	    		checkList = [];
	    	}
	    	
	    	var deleteList = checkList; 

            deleteList.forEach(function(seq) {
                $(location).attr("href", "/delete?seq=" + seq);
            });
	    });
	    
	    $(".excel").click(function(){
	    	location.href = '/exceldown'
		})
	});
	
	function detail(seq){
		location.href = '/detail?seq=' + seq
	};
	
	function fncPageClick(nowPage, listSize){
		$("#nowPage").val(nowPage);
		$("#listSize").val(listSize);
		$(".search").click();
	};
	
	function list() {
		var searchType = $("#searchType>option:selected").val();
		var keyword = $("#keyword").val();
		var startdate = $("#startdate").val();
		var endDate = $("#endDate").val();
		var data = {
			"searchType" : searchType,
			"keyword" : keyword,
			"startdate" : startdate,
			"endDate" : endDate
		}
		
		$.ajax({
			url : "/ajax",
			data : $('.searchForm').serialize(),
			type : "POST",
			
			success : function(data) {
    			$(".board").html(data);
			},
			error : function() {
				alert("에러");
			}

		})
	}
	
</script>
</head>
<body>
	<form class="searchForm" method="post">
		<input type = "hidden" name = "nowPage"  id = "nowPage" value = "1">
		<input type = "hidden" name = "listSize"  id = "listSize" value = "10">
		<select name="searchType" >
			<option value="memName" ${param.searchType == 'memName' ? 'selected' : ''}>작성자</option>
			<option value="boardSubject" ${param.searchType == 'boardSubject' ? 'selected':''} >제목</option>
			<option value="boardSubject+boardContent">제목+내용</option>
		</select>
		<input type="text" name="keyword" value="${param.keyword }">
		<input type="date" name="startDate" value="${param.startDate}" >  ~ <input type="date" name="endDate" value="${param.endDate}">
		<input type="button" class="search" value="검색">
	</form>
	<input type="button" value="글쓰기" class="insert">
	<input type="button" value="삭제" class="delete">
	<input type="button" value="엑셀 다운로드" class="excel">
	<div class="board">
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
	</div>
</body>
</html>