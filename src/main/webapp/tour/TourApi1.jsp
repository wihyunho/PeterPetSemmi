<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TourApi</title>
<script
src="https://tistory1.daumcdn.net/tistory/2784544/skin/images/xmltojsonPast.min.js"></script>
<link rel="shortcut icon" href="#">

</head>
<body>
	<div class="main-preview">
		<h3 class="recruit__header">관광지 정보</h3>
		<div class="recruit-content">
			<div class="recruit-content__btn2-group2">
				<select id="areacode">
					<option value="1" selected>서울</option>
					<option value="2">인천</option>
					<option value="3">대전</option>
					<option value="4">대구</option>
					<option value="5">광주</option>
					<option value="6">부산</option>
					<option value="7">울산</option>
					<option value="8">세종특별자치시</option>
					<option value="31">경기도</option>
					<option value="32">강원도</option>
					<option value="33">충청북도</option>
					<option value="34">충청남도</option>
					<option value="35">경상북도</option>
					<option value="36">경상남도</option>
					<option value="37">전라북도</option>
					<option value="38">전라남도</option>
					<option value="39">제주도</option>
				</select> <select id=typecode onchange="apiUrl();">
					<option value="12" selected>관광지</option>
					<option value="14">문화시설</option>
					<option value="28">레포츠</option>
					<option value="38">쇼핑</option>
					<option value="32">숙박</option>
				</select>
				<input type="hidden" id="Paging" value="1">
				<button id="btnOk" onclick="resetPage();">검색</button>
			</div>
			<hr>
	
			<!-- 컨텐츠 표시 -->
			<ul id="rsV" class="card-group">

			</ul>
			
			<div id="Page">
			
			</div>
		</div>
	</div>

	<script src="js/TourUrl.js"></script>
</body>
</html>