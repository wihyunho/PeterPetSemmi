//ID중복확인하는 함수
function idCheckFuntion() {
	var userID = $('#userID').val(); //사용자가 input태그에 넣은 값중 id가 userID인 것을 가져온다.

	//ID예외처리
	//20자 이하, 숫자, 알파벳만 포함가능 (공백도 같이 걸러진다.)
	var userID_pattern = /^[a-zA-Z0-9]{1,20}$/;
	if (!userID_pattern.test(userID)) {
		$('#checkMessage').html('아이디는 숫자와 알파벳 포함, 1~20자 이내여야 사용할 수 있습니다.');
		$('#checkType').attr('class', 'modal-content panel-warning');
		document.getElementById("idCheck").value = "0"; //중복확인을 하지 않았다는 flag
		$('#checkModal').modal("show");
		return;
	}

	$.ajax({
		type : 'POST',
		url : './UserIDCheckServlet',
		data : {
			userID : userID
		}, //{parameterName, data} 형식
		success : function(result) {
			if (result == 1) {
				$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				$('#checkType').attr('class', 'modal-content panel-success');
				document.getElementById("idCheck").value = "1"; //중복확인을 했다는 flag
			} else {
				$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				$('#checkType')
						.attr('class', 'modal-content panel-warning');
				//document.getElementById("idCheck").value="0"; //중복확인을 하지 않았다는 flag
			}
			$('#checkModal').modal("show");

		}
	});
}

//idCheck flag를 초기화 해주는 함수
function init_idCheck() {
	document.getElementById("idCheck").value = "0";
}

//nickname중복확인하는 함수
function nicknameCheckFuntion() {
	var nickname = $('#nickname').val();

	//ID예외처리
	//20자 이하, 숫자, 알파벳만 포함가능 (공백도 같이 걸러진다.)
	var nickname_pattern = /^[a-zA-Z0-9가-힣]{2,10}$/;
	if (!nickname_pattern.test(nickname)) {
		$('#checkMessage').html('닉네임은 숫자와 알파벳 한글 포함, 2~10자 이내여야 사용할 수 있습니다.');
		$('#checkType').attr('class', 'modal-content panel-warning');
		document.getElementById("nicknameCheck").value = "0"; //중복확인을 하지 않았다는 flag
		$('#checkModal').modal("show");
		return;
	}

	$.ajax({
		type : 'POST',
		url : './NicknameCheckServlet',
		data : {
			nickname : nickname
		}, //{parameterName, data} 형식
		success : function(result) {
			if (result == 1) {
				$('#checkMessage').html('사용할 수 있는 닉네임입니다.');
				$('#checkType').attr('class', 'modal-content panel-success');
				document.getElementById("nicknameCheck").value = "1"; //중복확인을 했다는 flag
			} else {
				$('#checkMessage').html('사용할 수 없는 닉네임입니다.');
				$('#checkType').attr('class', 'modal-content panel-warning');
				//document.getElementById("idCheck").value="0"; //중복확인을 하지 않았다는 flag
			}
			$('#checkModal').modal("show");
		}
	});
}


//idCheck flag를 초기화 해주는 함수
function init_nicknameCheck() {
	document.getElementById("nicknameCheck").value = "0";
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
	var idCheck = $('#idCheck').val(); //ID중복체크 여부
	var userPassword1 = $('#userPassword1').val(); //Password 같은지 여부
	var userPassword2 = $('#userPassword2').val();
	var userName = $('#userName').val(); //사용자 이름
	var nicknameCheck = $('#nicknameCheck').val(); //ID중복체크 여부
	var password_pattern = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,20}$/; //비밀번호 조건
	var blank_pattern = /[\s]/g; //공백인 것들 정규식
	var email_pattern = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

	//ID 중복체크를 하였는지 검사
	if (idCheck != "1") {
		$('#checkMessage').html('아이디 중복확인을 해주세요');
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
	if (!userName || blank_pattern.test(userName)) {
		$('#checkMessage').html('이름에 공백을 포함되어 있거나, 입력하지 않았습니다.');
		$('#checkType').attr('class', 'modal-content panel-warning');
		$('#checkModal').modal("show");
		return false;
	}
	if (nicknameCheck != "1") {
		$('#checkMessage').html('닉네임 중복확인을 해주세요');
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