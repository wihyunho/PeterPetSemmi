function PasswordCheckFuntion() {
	var password = $('#nowPassword').val(); //사용자가 input태그에 넣은 값중 id가 userID인 것을 가져온다.
	
	$.ajax({
		type : 'POST',
		url : './UserPasswordCheckServlet',
		async: false,
		data : {
			Password : password
		}, //{parameterName, data} 형식
		success : function(result) {
			if (result == 1) {
				data =  false;
			} else {
				data = true;
			}
		}
	});
	return data;
}

//password가 서로같은지, 조건에 맞는지 확인하여 passwordCheckMessage에 에러 메시지 출력
function passwordCheckFunction() {
	var userPassword1 = $('#userPassword1').val();
	var userPassword2 = $('#userPassword2').val();
	var password_pattern = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,20}$/;

	//password 조건 확인
	if (!password_pattern.test(userPassword1)) {
		$('#passwordCheckMessage')
				.html('비밀번호는 알파벳,숫자,특수문자 포함 8-20자 여야합니다.');
		return;
	} else {
		$('#passwordCheckMessage').html('');

		//password서로 일치하는지 확인
		if (userPassword1 != userPassword2) {
			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
		} else {
			$('#passwordCheckMessage').html('');
		}
	}
}
//submit전에 모든 예외처리를 확인하는 함수
function finalCheck() {
	var userPassword1 = $('#userPassword1').val(); //Password 같은지 여부
	var userPassword2 = $('#userPassword2').val();
	var password_pattern = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,20}$/; //비밀번호 조건

	//현재 비밀번호가 맞는지 확인
	if(PasswordCheckFuntion()){
		$('#checkMessage').html('현재 비밀번호가 일치하지 않습니다.');
		$('#checkType').attr('class', 'modal-content panel-warning');
		$('#checkModal').modal("show");
		return false;
	}
	
	if (userPassword1 != userPassword2
			|| !(password_pattern.test(userPassword1))) {
		$('#checkMessage').html('비밀번호를 확인해주세요');
		$('#checkType').attr('class', 'modal-content panel-warning');
		$('#checkModal').modal("show");
		return false;
	}
}



//a태그를 post 방식으로 전송
function mySubmit(val){
  var f = document.myForm;
  f.contentPage.value = val;
  f.submit();
}