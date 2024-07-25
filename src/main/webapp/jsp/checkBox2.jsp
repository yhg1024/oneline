<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>üũ�ڽ�</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <label><input type="checkbox" name="cities" value="selectAll" id="selectAll"/> <b>��ü</b></label> 
    <input type="button" value="��ư"> <br />
    <div class="cities">
        <label><input type="checkbox" name="city" value="����"/> ���� </label>
        <label><input type="checkbox" name="city" value="��õ"/> ��õ</label>
        <label><input type="checkbox" name="city" value="����"/> ���� </label>
        <label><input type="checkbox" name="city" value="����"/> ���� </label>
        <label><input type="checkbox" name="city" value="�λ�"/> �λ� </label>
        <label><input type="checkbox" name="city" value="����"/> ���� </label>
        <label><input type="checkbox" name="city" value="�뱸"/> �뱸 </label>
        <label><input type="checkbox" name="city" value="��õ"/> ��õ </label>
        <label><input type="checkbox" name="city" value="����"/> ���� </label>
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
                selectedCities.push(cityValue); // üũ�� ��� �߰�
            } else {
                selectedCities = selectedCities.filter(city => city !== cityValue); // üũ ������ ��� ����
            }

            var totalCnt = $("input[name=city]").length;		  
            var checkedCnt = $('[name="city"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            checkList();
        });

        function checkList() {
            $('#result').text(selectedCities.join(' ')); // ���õ� ���ø� �������� �����Ͽ� ���
        }
    });
    </script>
</body>

</html>