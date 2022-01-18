<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>Peter Pet</title>
	<!--Ajax를 위해서 공식사이트 에서 제공하는 jquery를 가져온다.  -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="${navBar}"></jsp:include>

	<jsp:include page="${contentPage}"></jsp:include>

	<!-- 알림 jsp -->
	<jsp:include page="function/alert.jsp"></jsp:include>
</body>
</html>