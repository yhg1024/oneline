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
    	// #selectAll이 true인지 false인지
    	const isChecked = this.checked;
    	// 모든 input[name=list]에 checked  	
    	if (isChecked) {
        	$("input[name=list]").prop("checked", isChecked);  
    	} else {
        	$("input[name=list]").prop("checked", false);  
    	}
    	
    });
	
    $("input[name=list]").change(function() {
        const cityValue = $(this).val();
        if ($(this).is(":checked")) {
        	$("#selectAll").prop("checked", true);       	
        } else {
        	$("#selectAll").prop("checked", false); 
        }

        // 전체 선택 체크 상태 업데이트
        var totalCnt = $("input[name=list]").length;		  
        var checkedCnt = $('[name="list"]:checked').length;
        $("#selectAll").prop("checked", totalCnt === checkedCnt);
        checkList();
        
        
        
    });
})
</script>
</head>
<body>
	<table border="1">
		<tr>
			<th><input type="checkbox" name="lists" value="selectAll" class="all" id="selectAll"/></th>
			<th>글번호</th>
			<th>작성자(ID)</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr> 
		<c:forEach items="${viewAll}" var="list" begin="0" end="10"> <!-- varStatus :  index 숫자를 줄 수 있다. 리스트의 길이만큼 자동으로 index를 준다 -->
			<tr>
				<td><input type="checkbox" name="list" class="chk" value="${list.seq}" /></td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.seq}</td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.memName}</td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.title}</td>
				<td>${list.regDate}</td>
				<td>${list.uptDate}</td>
				<td>${list.viewCnt}</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" onclick="location.href='/board/write'">글쓰기</button>
	<button type="button" onclick="location.href='/board/delete?seq=${list.seq}'">삭제</button>

</body>
</html>
