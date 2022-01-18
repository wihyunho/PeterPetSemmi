<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 가입</title>
<script src="js/passwordUpdate.js"></script>

</head>
<body>
	<!-- 회원가입 양식 -->
	<div class="container">
		<form method="post" enctype="multipart/form-data" action="UserInfoUpdateC" onSubmit="return finalCheck();">
			<input type="hidden" name="type" value="password">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>비밀번호 수정</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>현재 비밀번호</h5></td>
						<td colspan="2"><input class="form-control"
							type="password" id="nowPassword" name="nowPassword" maxlength="20"
							placeholder="현재 비밀번호를 입력해 주세요"></td>
						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>변경할 비밀번호</h5></td>
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