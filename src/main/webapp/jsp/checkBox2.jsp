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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(function() {
        let selectedCities = [];

        $("#selectAll").click(function() {
            $("input[name=city]").prop("checked", this.checked);
            checkList();
        });

        $("input[name=city]").change(function() {
            const cityValue = $(this).val();
            if ($(this).is(":checked")) {
                selectedCities.push(cityValue); // 체크된 경우 추가
            } else {
                selectedCities = selectedCities.filter(city => city !== cityValue); // 체크 해제된 경우 제거
            }

            var totalCnt = $("input[name=city]").length;		  
            var checkedCnt = $('[name="city"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            checkList();
        });

        function checkList() {
            $('#result').text(selectedCities.join(' ')); // 선택된 도시를 공백으로 구분하여 출력
        }
    });
    </script>
</body>

</html>