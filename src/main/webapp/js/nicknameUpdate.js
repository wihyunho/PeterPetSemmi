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

//submit전에 모든 예외처리를 확인하는 함수
function finalCheck() {
	var nicknameCheck = $('#nicknameCheck').val(); //닉네임중복체크 여부

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