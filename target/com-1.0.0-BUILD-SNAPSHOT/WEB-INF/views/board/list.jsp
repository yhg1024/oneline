<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardList</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function() {
	var checkList = [];
	// 전체 선택 클릭
    $("#selectAll").click(function() {
            const isChecked = this.checked;
            $("input[name=list]").prop("checked", isChecked);
            if (isChecked) {
                // 전체 선택
                $("input[name=list]:checked").each(function() {
                    const listValue = $(this).val();             
                    // 선택된 도시 목록에 지금 선택한 도시들이 없다면 추가
                    if (!checkList.includes(listValue)) {
                    	checkList.push(listValue);
                    }
                });
                $("input[name=list]").each(function() {
                    if (this.checked && !checkList.includes(this.value)) {
                    	checkList.push(this.value); // 새로 선택된 도시 추가
                    }
                });
            } else {
                // 전체 선택 해제 시, 모든 도시 제거
                checkList = [];
            }
            deleteList();
        });

        $("input[name=list]").change(function() {
            const listValue = $(this).val();
            if ($(this).is(":checked")) {
                if (!checkList.includes(listValue)) {
                	checkList.push(listValue); 
                }
            } else {
                checkList = checkList.filter(list => list !== listValue); 
            }

            // 전체 선택 체크 상태 업데이트
            var totalCnt = $("input[name=list]").length;		  
            var checkedCnt = $('[name="list"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            deleteList();     
        });
        
        $('#delete').click(function() {
            var deleteList = checkList; 

            deleteList.forEach(function(seq) {
                $(location).attr("href", "/board/delete?seq=" + seq);
            });
        });
})

function fncPageClick(nowPage, cntPerPage){
	
		$("#nowPage").val(nowPage);
		$("#cntPerPage").val(cntPerPage);
		$(".searchBtn").click();
}

$(document).ready(function () {
	$(".searchBtn").click(function() {
		list();
	})
});
function list() {
	$.ajax({
    	url : "/test", // action
    	// data : $('#search').serialize(), // parameter json // serialize() ex) /list? 뒤에꺼
    	data : JSON.stringify(),
    	type : "POST", // 메소드

    	success : function(data) {
    		alert(data);
	    	},
    	error : function() {
    		alert("에러");
    	}
    });
}
</script>
</head>
<body>
	<form action="/board/list" class="search" id="search" method="post">
		<input type = "hidden" name = "nowPage"  id = "nowPage" value = "1">
		<input type = "hidden" name = "cntPerPage"  id = "cntPerPage" value = "10">
	 
 		<select name="searchType">
	        <option value="">선택</option>
	        <option value="memName" ${param.searchType == 'memName' ? 'selected' : ''}>작성자</option>
	        <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
	        <option value="title+boardContent">제목+내용</option>
	    </select>
	    <input type="text" name="keyword" class="searchInput" type="text" value="${param.keyword}">
	    <input type="button" class="searchBtn" value="검색"> <br/>
	    <input type="date" name="startDate"  value="${param.startDate}" /> ~ <input type="date" name="endDate"  value="${param.endDate}" />
	    <select name="cntPerPage">
	        <option value="10" ${pageVO.cntPerPage eq 10 ? 'selected' : '' }>10</option>
	        <option value="30" ${pageVO.cntPerPage eq 30 ? 'selected' : '' }>30</option>
	        <option value="50" ${pageVO.cntPerPage eq 50 ? 'selected' : '' }>50</option>
	        <option value="100" ${pageVO.cntPerPage eq 100 ? 'selected' : '' }>100</option>
	    </select>
    </form>
    
	<button type="button" onclick="location.href='/board/write'">글쓰기</button>
	<button type="button" id="delete">삭제</button>	
	<table border="1" id="list">
		<tr>
			<th><input type="checkbox" name="lists" value="selectAll" class="all" id="selectAll"/></th>
			<th>글번호</th>
			<th>작성자(ID)</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr> 
		<c:forEach items="${list}" var="list"> <!-- varStatus :  index 숫자를 줄 수 있다. 리스트의 길이만큼 자동으로 index를 준다 -->
			<tr>
				<td><input type="checkbox" name="list" class="chk" value="${list.seq}" /></td>
				<td id="seqTd" onclick="location.href='/board/detail?seq=${list.seq}'">${list.seq}</td>
				<td id="memNameTd" id="tlssk" onclick="location.href='/board/detail?seq=${list.seq}'">${list.memName}(${list.memId})</td>
				<td id="titleTd" onclick="location.href='/board/detail?seq=${list.seq}'">${list.title}</td>
				<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${list.upDate}" pattern="yyyy-MM-dd"/></td>
				<td><c:out value="${list.viewCnt}"/></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">	
		<c:if test="${pagination.nowPage >= 11 }">
			<span onclick="fncPageClick('1', ${pagination.cntPerPage})">맨앞</span>
		</c:if>	
		<c:if test="${pagination.startPage != 1 }">
			<span onclick="fncPageClick(${pagination.startPage - 1 }, ${pagination.cntPerPage}) ">이전</span>
		</c:if>
		<c:forEach begin="${pagination.startPage }" end="${pagination.endPage }" var="p">
			<c:choose>
				<c:when test="${p == pagination.nowPage }">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != pagination.nowPage }">
					<span class="page" onclick="fncPageClick('${p }', '${pagination.cntPerPage}')">${p }</span>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${pagination.endPage != pagination.lastPage}">
			<span onclick="fncPageClick('${pagination.endPage+1 }', '${pagination.cntPerPage}')">다음</span>
		</c:if>
		<c:if test="${pagination.lastPage > 10 and pagination.nowPage != pagination.lastPage }">
			<span onclick="fncPageClick('${pagination.lastPage}', '${pagination.cntPerPage}')">맨뒤</span>
		</c:if>
	</div>
</body>
</html>
