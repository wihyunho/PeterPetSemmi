<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Anton' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Neucha' rel='stylesheet' type='text/css'>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/home.js" defer="defer"></script>
</head>
<body>
	<div id="wrapper">
		<div id="slider-wrap">
			<ul id="slider">
				<li onclick="location.href='MapC'" >
					<div>
						<h3>편의 시설 찾기</h3>
						여러분들의 주변에 반려동물들이 필요한 장소들을 보여드릴게요.<br> <span>병원, 공원, 애견카페</span>등의
						장소들을 맵으로 확인해보세요.
					</div> 
					<img src="images/park.png">
				</li>

				<li onclick="location.href='TourHomeC'">
					<div>
						<h3>관광지 찾기</h3>
						여러분들의 반려동물과 함께할 여행지를 찾아 드릴게요. <br>
						함께 같이 추억을 만들어 봐요. 
					</div> 
					<img src="images/travle.jpg">
				</li>

				<li onclick="location.href='BoardListC?type=free'">
					<div>
						<h3>사용자 게시판</h3>
						자신의 반려동물을 자랑하거나 키우면서 생긴 노하우, 더이상 필요없는 물품을 나누어보아요.<br>
					</div> 
					<img src="images/cummunication.jpg">
				</li>

				<li onclick="location.href='https://dmanimal.co.kr/'">
					<div>
						<h3>유기견 보호소</h3>
						<span>과연 버려진 아이들이 나쁜걸까요? </span>
						
					</div> 
					<img src="images/poor.jpg">
				</li>

				<li onclick="location.href='https://www.animal.go.kr/'">
					<div>
						<h3>동물등록</h3>
						<span>가족의 인연이 더욱 강력하게!</span><br>
						반려동물을 잃어버렸을 때 동물보호관리시스템(www.animal.go.kr) 상 동물등록정보를 통해 소유자를 쉽게 찾을 수 있습니다.
					</div> 
					<img src="images/redline.jpg">
				</li>
			</ul>
			<!--controls-->
			<div class="btns" id="next">
				<i class="fa fa-arrow-right"></i>
			</div>
			<div class="btns" id="previous">
				<i class="fa fa-arrow-left"></i>
			</div>
			<div id="counter"></div>

			<div id="pagination-wrap">
				<ul>
				</ul>
			</div>
			<!--controls-->
		</div>
	</div>
	
	<div class="main-preview">
		<hr>
		<!-- 컨텐트 표시 -->
		<h1 style="color: #F15F5F;">자랑하기</h1>
		<ul class="card-group">
			<c:forEach var="free" items="${freeBoards}">
				<!-- start card -->
				<li onclick="location.href='BoardViewC?no=${free.no}&type${free.type}'" class="card-item">
					<div class="card-ly1" style=" background-image: url(${free.thumbnail});"></div>
					<a href="#" class="card card_big">
						<h3 class="card__head fs_28">${free.title }</h3> 
						<span class="card__date">작성자: ${free.writer}</span><br>
						<span class="card__date">작성일: ${free.date}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<hr>
		<h1 style="color:  #F29661;">나눠봐요</h1>
		<ul class="card-group">
			<c:forEach var="free" items="${shareBoards}">
				<!-- start card -->
				<li onclick="location.href='BoardViewC?no=${free.no}&type${free.type}'" class="card-item">
					<div class="card-ly1" style=" background-image: url(${free.thumbnail});"></div>
					<a href="#" class="card card_big">
						<h3 class="card__head fs_28">${free.title }</h3> 
						<span class="card__date">작성자: ${free.writer}</span><br>
						<span class="card__date">작성일: ${free.date}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<hr>
		<h1 style="color: #F2CB61;">Q & A</h1>
		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th id="smallhidden2">작성자</th>
					<th id="smallhidden">날짜</th>
				</tr>
			</thead>
			<c:forEach var="b" items="${QABoards }">
				<tbody>
					<tr class="boardList" onclick="location.href='BoardViewC?no=${b.no}&type=${b.type}'">
						<td><b>Q&A : </b>&nbsp; ${b.title }</td>
						<td id="smallhidden2" align="center">${b.writer }</td>
						<td id="smallhidden" align="center">
							<fmt:formatDate value="${b.date }" type="both" dateStyle="short" timeStyle="short"/><br>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>