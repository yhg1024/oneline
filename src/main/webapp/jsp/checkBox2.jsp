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
	<label><input type ="checkbox" name="cities" value="selectAll"  id ="selectAll"/> <b>��ü</b></label> 
	<input type ="button" value="��ư"> <br />
	<div class="cities">
	<label><input type ="checkbox" name="city" value="����"/> ���� </label>
	<label><input type ="checkbox" name="city" value="��õ"/> ��õ</label>
	<label><input type ="checkbox" name="city" value="���"/> ����</label>
	<label><input type ="checkbox" name="city" value="����"/> ����</label>
	<label><input type ="checkbox" name="city" value="�λ�"/> �λ�  </label>
	<label><input type ="checkbox" name="city" value="����"/> ���� </label>
	<label><input type ="checkbox" name="city" value="�뱸"/> �뱸 </label>
	<label><input type ="checkbox" name="city" value="��õ"/> ��õ </label>
	<label><input type ="checkbox" name="city" value="����"/> ���� </label> </br>
	</div>
	
<script>
$(function() {
	var cityArr = new Array();
	$("#selectAll").click(function(){
		if($("#selectAll").is(":checked")){
			$("input[name=city]").prop("checked",true);	        
	    } else {
	    	$("input[name=city]").prop("checked",false);
	    }
		putCheckList();
	});
	
	$("input[name=city]").click(function(){
		var totalCnt = $("input[name=city]").length;		  
	    var checkedCnt = $('[name="city"]:checked').length;
	    
	    if(totalCnt == checkedCnt){
	    	$("#selectAll").prop("checked",true);	
	    } else {
	    	$("#selectAll").prop("checked",false);
	    }
	    
	    putCheckList();
	    
	    var str = "";
		for (var i = 0; i < cityArr.length; i++) {
			str += cityArr[i]
		}
		console.log(str)
		
		
		var temp = '<div>' + str + '</div>'
		$('.cities').append(temp);
	});
	
	$("input[name=city]").change(function(){
		if($(this).length != $('[name="city"]:checked').length){
			$("#selectAll").prop("checked", false);
			putCheckList();
		}
	});
	
	
	
	function putCheckList() {
		cityArr = new Array();
		var idxArr = new Array();
		
		$("input[name=city]:checked").each(function() {
			idxArr.push($("input[name=city]").index(this));
		});
		
		for (var i = 0; i < idxArr.length; i++) {
			var obj = new Object();
			obj = $(".cities").children().eq(idxArr[i]).text();
			
			cityArr.push(obj);
		}
		
	}
})
</script>
</body>
</html>