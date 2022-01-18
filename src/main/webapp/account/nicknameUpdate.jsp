<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 가입</title>
<!--Ajax를 위해서 공식사이트 에서 제공하는 jquery를 가져온다.  -->
<script src="js/nicknameUpdate.js"></script>

</head>
<body>
	<!-- 회원가입 양식 -->
	<div class="container">
		<form method="post" enctype="multipart/form-data" action="UserInfoUpdateC" onSubmit="return finalCheck();">
			<input type="hidden" name="type" value="nickname">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 정보</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>현재 닉네임</h5></td>
						<td colspan="2">${User.userNickname}</td>
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