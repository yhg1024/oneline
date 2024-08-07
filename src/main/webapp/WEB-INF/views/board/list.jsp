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
                	checkList.push(listValue); // 새로 선택된 도시 추가
                }
            } else {
            	// city와 cityValue(this.val)가 타입과 값이 다른 것을 배열에 담는다. = 지운다
                checkList = checkList.filter(list => list !== listValue); // 체크 해제된 경우 제거
            }

            // 전체 선택 체크 상태 업데이트
            var totalCnt = $("input[name=list]").length;		  
            var checkedCnt = $('[name="list"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            deleteList();     
        });
        
        $('#delete').click(function() {
    		var deleteList = checkList
    		alert(deleteList)	
    		$(location).attr("href", "/board/delete?seq="+deleteList)
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
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.memName}(${list.memId})</td>
				<td onclick="location.href='/board/detail?seq=${list.seq}'">${list.title}</td>
				<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${list.uptDate}" pattern="yyyy-MM-dd"/></td>
				<td>${list.viewCnt}</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" onclick="location.href='/board/write'">글쓰기</button>
	<button type="button" id="delete">삭제</button>
</body>
</html>
