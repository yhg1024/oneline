<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function() {
    $('#joinForm').on('submit', function(event) {
    	// 변수에 저장	var id = document.getElementById("id")
    	/* id : 문자(영어), 숫자만, 중복체크, "test"  */
     	let regExId = /^[a-zA-Z0-9]*$/
    	let id = $("#id").val()
    	if (!regExId.test(id)) {
            alert('아이디를 형식에 맞게 입력해주세요.')
            return false
        }
     	if (id && memberRepository.existsByUserId(userId)) {
     		alert('이미 있는 아이디입니다.')
     		return false
        }

    	/* pw : 비밀번호확인(가입신청버튼 클릭시)   */
    	let password = $("#password").val()
    	let confirmPassword = $("#confirmPassword").val()
    	if (password != confirmPassword) {
                alert('비밀번호가 다릅니다.')
         		return false
            }
            


    	/* 이름 : 5글자(maxlength x)-script 
    	        문자(한글,영어)
    	        5글자 넘어갈 시 바로 체크 */
    	let regExName = /^[ㄱ-ㅣ가-힣a-zA-Z]*$/
    	let name = $("#name").val()
    	if (!regExName.test(name)) {
    		alert('이름을 형식에 맞게 입력해주세요.')
     		return false
    	}

    	/* 이메일 : 유효성(가입신청버튼 클릭시) */
    	let regExEmail1 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*$/i
    	let regExEmail2 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
    	let email1 = $("#email1").val()
    	let email2 = $("#email2").val()
    	if(!regExEmail1.test(email1) && !regExEmail2.test(email2)) {
    		alert('이메일을 형식에 맞게 입력해주세요.')
     		return false
    	}

    		          

    	/* 주민번호 : 숫자만 주민번호 유효성
    		 뒷자리 맨첫자리만 숫자 1******
    		 (가입 신청클릭시) */
    	let regExJcode = /^[0-9]*$/	
    	let jcode1 = $("#jcode1").val()
    	let jcode2 = $("#jcode2").val()
    	if(!regExJcode.test(jcode1) && !regExJcode.test(jcode2)){
    		alert('주민번호를 형식에 맞게 입력해주세요.')
     		return false
    	}
    	let regExJcode2 = /^[1-2]/
    	if(!regExJcode2.test(jcode2)) {
    		alert('주민번호 뒷자리를 형식에 맞게 입력해주세요.')
     		return false
    	}

    /* 휴대폰 : 숫자만 4자리 -> 3번째로
              4개 입력하면 3번째 창으로 커서이동 */
    $(".phone").keyup(function(num,fromform,toform){
	    	let str = fromform.value.length
	    	if(str == num) {
	    		toform.focus();
	    	}
    	});
	});
    /* 이름 : 5글자(maxlength x)-script 
    문자(한글,영어)
    5글자 넘어갈 시 바로 체크 */
    $("#name").keyup(function(num){
    	let regExName = /^[ㄱ-ㅣ가-힣a-zA-Z]*$/
    		let name = joinForm.name.value
    		if (!regExName.test(name)) {
    			alert('이름을 형식에 맞게 입력해주세요.')
    		}
    	let str = joinForm.name.value.length
    	if (str > num) {
    		alert('이름은 5글자 이하여야합니다.')
    	}
        // joinValidation 함수 호출
        if (!joinValidation()) {
            // joinValidation이 false를 반환하면 폼 제출을 막음
            event.preventDefault();
        }
    });
});

</script>
</head>
<body>
	<form name="joinForm" id="joinForm" onsubmit="return joinValidation()" method="post">
		<table width="1400">
			<tr>
				<td height="10%">회원가입</td>
			</tr>
			<tr>
				<td height="60%" align="center">
				<hr>
					<p align="left" style="padding-left:160px">					
					  id : <input type="text" name="id" id="id"/> <input type="button" value="중복체크"/></br></br>
					    비밀번호 : <input type="password" name="password" id="password"/></br></br>
					    비밀번호 확인 : <input type="password" name="confirmPassword" id="confirmPassword"/></br></br>
					    이름 : <input type="text" name="name" id="name" onkeyup="checkName(5)"/></br></br>
					    이메일 : <input type="text" name="email1" id="email1" /> @ <input type="text" name="email2" id="email2" /></br></br>
					    휴대폰 : <select name="phone1" class="phone">
			                    <option value="010">010</option>
			                    <option value="011">011</option>
			                    <option value="016">016</option>
			                    <option value="017">017</option>
			                    <option value="019">019</option>
			                </select>
			                - <input type="tel" maxlength="4" size="4" name="phone2" id="phone2" class="phone" onkeyup="checkNumber(4,this,this.form.phone3)">
			                - <input type="tel" maxlength="4" size="4" name="phone3" id="phone3" class="phone"></br></br>
					    성별 : <input type="radio" name="sex" id="male" value="남자">남자</input> <input type="radio" name="sex" id="female" value="여자">여자</input></br></br>
					    주민번호: <input type="text" name="jcode1" id="jcode1" size=8 maxlength="6"> - <input type="password" name="jcode2" id="jcode2" size=9 maxlength="7"></br></br>
					    주소 : <input type="text" name="address" id="address" /></br></br>
					    *주소는 (시/도)만 입력해주세요 (예: 경기도, 서울특별시, 경상남도 등)
					</p>
				</td>
			</tr>
			<tr>
				<td align="center">
					<hr>
					<input type="submit" id="regi_btn" value="가입신청">
					<input type="reset" value="다시입력">
					<input type="button" value="취소">
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>