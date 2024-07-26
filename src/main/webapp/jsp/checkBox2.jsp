<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>체크박스</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <label><input type="checkbox" name="cities" value="selectAll" id="selectAll"/> <b>전체</b></label> 
    <input type="button" value="버튼"> <br />
    <div class="cities">
        <label><input type="checkbox" name="city" value="서울"/> 서울 </label>
        <label><input type="checkbox" name="city" value="인천"/> 인천</label>
        <label><input type="checkbox" name="city" value="전주"/> 전주 </label>
        <label><input type="checkbox" name="city" value="원주"/> 원주 </label>
        <label><input type="checkbox" name="city" value="부산"/> 부산 </label>
        <label><input type="checkbox" name="city" value="광주"/> 광주 </label>
        <label><input type="checkbox" name="city" value="대구"/> 대구 </label>
        <label><input type="checkbox" name="city" value="춘천"/> 춘천 </label>
        <label><input type="checkbox" name="city" value="제주"/> 제주 </label>
    </div>
    <div id='result'></div>

    <script>
    $(function() {
        let selectedCities = [];

        $("#selectAll").click(function() {
            const isChecked = this.checked;
            $("input[name=city]").prop("checked", isChecked);
            if (isChecked) {
                // 전체 선택
                $("input[name=city]:checked").each(function() {
                    const cityValue = $(this).val();             
                    // 선택된 도시 목록에 지금 선택한 도시들이 없다면 추가
                    if (!selectedCities.includes(cityValue)) {
                        selectedCities.push(cityValue);
                    }
                });
                $("input[name=city]").each(function() {
                    if (this.checked && !selectedCities.includes(this.value)) {
                        selectedCities.push(this.value); // 새로 선택된 도시 추가
                    }
                });
            } else {
                // 전체 선택 해제 시, 모든 도시 제거
                selectedCities = [];
            }
            checkList();
        });

        $("input[name=city]").change(function() {
            const cityValue = $(this).val();
            if ($(this).is(":checked")) {
                if (!selectedCities.includes(cityValue)) {
                    selectedCities.push(cityValue); // 새로 선택된 도시 추가
                }
            } else {
                selectedCities = selectedCities.filter(city => city !== cityValue); // 체크 해제된 경우 제거
            }

            // 전체 선택 체크 상태 업데이트
            var totalCnt = $("input[name=city]").length;		  
            var checkedCnt = $('[name="city"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            checkList();
            
            
            
        });

        function checkList() {
            // 선택된 도시를 클릭 순서대로 출력
            $('#result').text(selectedCities.join(' ')); // 선택된 도시를 공백으로 구분하여 출력
        }
        
        /* 버튼은 4이하나 전체일때 '성공' 아닐때는 실패로 alert */
        $('input[type=button]').click(function(){
        	if (selectedCities.length <= 4 || $("#selectAll").is(":checked")) {
        		alert('성공')
        	} else {
        		alert('실패')
        	}
        })
    });
    </script>
</body>

</html>