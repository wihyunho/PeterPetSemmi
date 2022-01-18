<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://tistory1.daumcdn.net/tistory/2784544/skin/images/xmltojsonPast.min.js"></script>
<link rel="shortcut icon" href="#">
<style type="text/css">
#test3 b{
	font-size: 20px;
}
</style>
</head>
<body onload="main();">
	<div id="test1" style="display: none; "></div>
	<div id="test2" style="display: none;"></div>
	<!-- 게시글 content  -->
	<div class="main-preview">
		<h3 class="recruit__header">관광지 정보</h3>
		<div class="recruit-content">
			<div class="pull-right" style="margin: 10px">
				<button onclick="back();">목록페이지</button>
			</div>
			<div id="test3"></div>
			<div id="test4"></div>
		</div>
	</div>
	<script src="js/TourSel.js"></script>
</body>
</html>