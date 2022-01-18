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
	<div class="container">
		<form method="post" enctype="multipart/form-data" action="UserInfoUpdateC">
			<input type="hidden" name="type" value="profile">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>프로필 사진 변경</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="3"><img src="images/${User.userProfile  }" width="300px"></td>
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