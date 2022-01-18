<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 가입</title>
<!--Ajax를 위해서 공식사이트 에서 제공하는 jquery를 가져온다.  -->
<script src="js/join.js"></script>

</head>
<body>
	<!-- 회원가입 양식 -->
	<div class="container">
		<form method="post" enctype="multipart/form-data" action="UserRegisterC" onSubmit="return finalCheck();">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 정보</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input onkeyup="init_idCheck();" class="form-control"
							type="text" id="userID" name="userID" maxlength="20"
							placeholder="20자 이하의 숫자, 알파벳"></td>
						<td style="width: 110px;"><button class="btn btn-primary"
								onclick="idCheckFuntion();" type="button">중복체크</button> <input
							type="hidden" name="idCheck" id="idCheck" value="0" /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();"
							class="form-control" type="password" id="userPassword1"
							name="userPassword1" maxlength="20"
							placeholder="알파벳,숫자,특수문자 포함 8-20자"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();"
							class="form-control" type="password" id="userPassword2"
							name="userPassword2" maxlength="20" placeholder="비밀번호 확인을 입력해주세요"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="userName" name="userName" maxlength="20"
							placeholder="이름을 입력해주세요"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>닉네임</h5></td>
						<td>
							<input onkeyup="init_nicknameCheck();" class="form-control" type="text" id="nickname" name="nickname" maxlength="20"
							placeholder="10자 이하의 한글과 영문 조합">
						</td>
						<td style="width: 110px;">
							<button class="btn btn-primary"onclick="nicknameCheckFuntion();" type="button">중복체크</button> 
							<input type="hidden" name="nickNamecheck" id="nicknameCheck" value="0" />
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>사진</h5></td>
						<td colspan="2">
							<input class="form-control" type="file" id="profile" name="profile">
						</td>
					</tr>			
					<tr>
						<td style="text-align: left;" colspan="3"><h5
								style="color: red;" id="passwordCheckMessage"></h5>
							<input class="btn btn-primary pull-right" type="submit"
							value="등록"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>