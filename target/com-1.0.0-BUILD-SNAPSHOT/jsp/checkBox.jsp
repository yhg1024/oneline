<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>üũ�ڽ�</title>
</head>
<body>
	<label><input type ="checkbox" name="cities" value="selectAll"  id ="selectAll"/> <b>��ü</b></label> 
	<input type ="button" value="��ư"> <br />
	<label><input type ="checkbox" name="city" value="����"> ���� </label>
	<label><input type ="checkbox" name="city" value="��õ" /> ��õ  </label>
	<label><input type ="checkbox" name="city" value="���" /> ��� </label>
	<label><input type ="checkbox" name="city" value="����"/> ����</label>
	<label><input type ="checkbox" name="city" value="�λ�" /> �λ�  </label>
	<label><input type ="checkbox" name="city" value="����" /> ���� </label>
	<label><input type ="checkbox" name="city" value="�뱸" /> �뱸 </label>
	<label><input type ="checkbox" name="city" value="��õ" /> ��õ </label>
	<label><input type ="checkbox" name="city" value="����" /> ���� </label> </br>
	
<script>
	/* ��ü üũ�ϸ� �� üũ  */
	const selectAll = document.getElementById("selectAll");
	const checkboxes = document.querySelectorAll('[name="city"]');
	// ��ü�� Ŭ���ϸ�
	selectAll.addEventListener("click", function() {
		const isChecked = selectAll.checked;
		
		// selectAll�� üũ �Ǿ��ִ���
		if(isChecked){
	        /* 
	        	for (���� of �迭) {
				    �ݺ����ۺκ�
				} 
	        */
	        // name�� city�� checkbox�� �ݺ��ϸ�
	        // checkbox�� checked�� true�� ������
	        for(const checkbox of checkboxes){
	            checkbox.checked = true;
	        }
	    } else { // selectAll�� üũ �Ǿ����� �ʴ���
	        const checkboxes = document.querySelectorAll('[name="city"]');
	     	// name�� city�� checkbox�� �ݺ��ϸ�
	        // checkbox�� checked�� false�� ������
	        for(const checkbox of checkboxes){
	            checkbox.checked = false;
	        }
	    }
	})
	/* �ؿ� ���� üũ�� �ϳ��� Ǯ�� ��ü üũ�� üũ�� üũ�� Ǯ�� */
	// ���� checkbox�� Ŭ���ϸ� 
	for(const checkbox of checkboxes){
	  checkbox.addEventListener('click',function(){
	    
	    const totalCnt = checkboxes.length;
	  
	    const checkedCnt = document.querySelectorAll('[name="city"]:checked').length;
	    
	    // ��ü �迭�� ������ üũ�� ������ ��ġ�ϸ� ��ü�� üũ�� �ȴ�.
	    if(totalCnt == checkedCnt){
	      document.querySelector('#selectAll').checked = true;
	    } else {
	      document.querySelector('#selectAll').checked = false;
	    }
	    
	    /* üũ�� �ϸ� �ؿ� üũ�� ������� ��Ÿ�� üũ �����ϸ� �ؿ����� ����� */
	    const checked = checkbox.value	    
	    const newSpan = document.createElement('span');

	    
	    
	    // üũ�Ѱ� �ؿ� ����
	    newSpan.innerHTML = checked;	    
	    document.body.appendChild(newSpan);
	    
	    newSpan.id = checked	    
	    newSpan.setAttribute("value", checked); 
	    
	    
	    console.log(checked)	 
	    
	    const spans = document.getElementById(checked);
	    console.log(spans)
	    const delSpan = spans.getAttribute('id')
	    console.log('delSpan = ' + delSpan)	
	   
	    /* if (checked == delSpan &&  <2) {
	    	// spans.remove()
	    } */
	    
	    
	    
	  });	  
	}

	
	
	/* ��ư�� 4���ϳ� ��ü�϶� '����' �ƴҶ��� ���з� alert */
	//alert('totalCnt = ' + totalCnt + ", checkedCnt = " + checkedCnt)

</script>
</body>
</html>