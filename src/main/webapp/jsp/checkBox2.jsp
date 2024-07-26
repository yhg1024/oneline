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

    <script>
    $(function() {
        let selectedCities = [];

        $("#selectAll").click(function() {
            const isChecked = this.checked;
            $("input[name=city]").prop("checked", isChecked);
            if (isChecked) {
                // ��ü ����
                $("input[name=city]:checked").each(function() {
                    const cityValue = $(this).val();             
                    // ���õ� ���� ��Ͽ� ���� ������ ���õ��� ���ٸ� �߰�
                    if (!selectedCities.includes(cityValue)) {
                        selectedCities.push(cityValue);
                    }
                });
                $("input[name=city]").each(function() {
                    if (this.checked && !selectedCities.includes(this.value)) {
                        selectedCities.push(this.value); // ���� ���õ� ���� �߰�
                    }
                });
            } else {
                // ��ü ���� ���� ��, ��� ���� ����
                selectedCities = [];
            }
            checkList();
        });

        $("input[name=city]").change(function() {
            const cityValue = $(this).val();
            if ($(this).is(":checked")) {
                if (!selectedCities.includes(cityValue)) {
                    selectedCities.push(cityValue); // ���� ���õ� ���� �߰�
                }
            } else {
                selectedCities = selectedCities.filter(city => city !== cityValue); // üũ ������ ��� ����
            }

            // ��ü ���� üũ ���� ������Ʈ
            var totalCnt = $("input[name=city]").length;		  
            var checkedCnt = $('[name="city"]:checked').length;
            $("#selectAll").prop("checked", totalCnt === checkedCnt);
            checkList();
            
            
            
        });

        function checkList() {
            // ���õ� ���ø� Ŭ�� ������� ���
            $('#result').text(selectedCities.join(' ')); // ���õ� ���ø� �������� �����Ͽ� ���
        }
        
        /* ��ư�� 4���ϳ� ��ü�϶� '����' �ƴҶ��� ���з� alert */
        $('input[type=button]').click(function(){
        	if (selectedCities.length <= 4 || $("#selectAll").is(":checked")) {
        		alert('����')
        	} else {
        		alert('����')
        	}
        })
    });
    </script>
</body>

</html>