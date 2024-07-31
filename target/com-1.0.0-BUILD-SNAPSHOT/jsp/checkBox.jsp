<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>체크박스</title>
</head>
<body>
	<label><input type ="checkbox" name="cities" value="selectAll"  id ="selectAll"/> <b>전체</b></label> 
	<input type ="button" value="버튼"> <br />
	<label><input type ="checkbox" name="city" value="서울"> 서울 </label>
	<label><input type ="checkbox" name="city" value="인천" /> 인천  </label>
	<label><input type ="checkbox" name="city" value="경기" /> 경기 </label>
	<label><input type ="checkbox" name="city" value="원주"/> 원주</label>
	<label><input type ="checkbox" name="city" value="부산" /> 부산  </label>
	<label><input type ="checkbox" name="city" value="광주" /> 광주 </label>
	<label><input type ="checkbox" name="city" value="대구" /> 대구 </label>
	<label><input type ="checkbox" name="city" value="춘천" /> 춘천 </label>
	<label><input type ="checkbox" name="city" value="제주" /> 제주 </label> </br>
	
<script>
	/* 전체 체크하면 다 체크  */
	const selectAll = document.getElementById("selectAll");
	const checkboxes = document.querySelectorAll('[name="city"]');
	// 전체를 클릭하면
	selectAll.addEventListener("click", function() {
		const isChecked = selectAll.checked;
		
		// selectAll이 체크 되어있는지
		if(isChecked){
	        /* 
	        	for (변수 of 배열) {
				    반복동작부분
				} 
	        */
	        // name이 city인 checkbox를 반복하며
	        // checkbox에 checked를 true로 만들어라
	        for(const checkbox of checkboxes){
	            checkbox.checked = true;
	        }
	    } else { // selectAll이 체크 되어있지 않는지
	        const checkboxes = document.querySelectorAll('[name="city"]');
	     	// name이 city인 checkbox를 반복하며
	        // checkbox에 checked를 false로 만들어라
	        for(const checkbox of checkboxes){
	            checkbox.checked = false;
	        }
	    }
	})
	/* 밑에 개별 체크를 하나라도 풀면 전체 체크쪽 체크도 체크가 풀림 */
	// 개별 checkbox를 클릭하면 
	for(const checkbox of checkboxes){
	  checkbox.addEventListener('click',function(){
	    
	    const totalCnt = checkboxes.length;
	  
	    const checkedCnt = document.querySelectorAll('[name="city"]:checked').length;
	    
	    // 전체 배열의 갯수와 체크된 갯수가 일치하면 전체에 체크가 된다.
	    if(totalCnt == checkedCnt){
	      document.querySelector('#selectAll').checked = true;
	    } else {
	      document.querySelector('#selectAll').checked = false;
	    }
	    
	    /* 체크를 하면 밑에 체크한 순서대로 나타남 체크 해제하면 밑에서도 사라짐 */
	    const checked = checkbox.value	    
	    const newSpan = document.createElement('span');

	    
	    
	    // 체크한거 밑에 생성
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

	
	
	/* 버튼은 4이하나 전체일때 '성공' 아닐때는 실패로 alert */
	//alert('totalCnt = ' + totalCnt + ", checkedCnt = " + checkedCnt)

</script>
</body>
</html>