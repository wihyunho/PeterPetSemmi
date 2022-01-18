<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 가입</title>
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
						<td colspan="3">
							<img src="images/${User.userProfile }" width="300px">
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td colspan="2">${User.userID }</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2">${User.userName }</td>
					</tr>				
					<tr>
						<td style="width: 110px;"><h5>닉네임</h5></td>
						<td colspan="2">${User.userNickname} </td>
					</tr>
					<tr>
						<td colspan="3">
							<input class="btn btn-primary" onclick="location.href='UserInfoUpdateC?type=profile'" type="button"value="프로필사진 변경">
							<input class="btn btn-primary" onclick="location.href='UserInfoUpdateC?type=nickname'" type="button"value="닉네임 변경">
							<input class="btn btn-primary" onclick="location.href='UserInfoUpdateC?type=password'" type="button"value="비밀번호 변경">
						</td>
					</tr>
								
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>