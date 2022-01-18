<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="main-preview">
	<h3 class="recruit__header">팁과 노하우 게시판</h3>
	<div class="recruit-content">
		<div class="recruit-content__btn2-group2">
			<form action="BoardSearchC" method="post" class="recruit">
				<input type="hidden" name="type" value="tip">
			    <select name="field">
			        <option value="title">제목</option>
			        <option value="writer">작성자</option>
			    </select>
			    <input type="text" name="search" placeholder="검색어를 입력해주세요." value=""/>
				<button href="BoardSearchC" class="btn btn-default">검색</button>
				<a href="BoardWriteC?type=tip" class="btn btn-default pull-right2" style="margin-bottom: 10px;">글 쓰기</a>
			</form>
		</div>	
		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th id="smallhidden2">작성자</th>
					<th id="smallhidden">날짜</th>
				</tr>
			</thead>
			<c:forEach var="b" items="${boards }">
				<tbody>
					<tr class="boardList" onclick="location.href='BoardViewC?no=${b.no}&type${b.type}&p=${curPageNo}'">
						<td align="center">${b.title }</td>
						<td id="smallhidden2" align="center">${b.writer }</td>
						<td id="smallhidden" align="center">
							<fmt:formatDate value="${b.date }" type="both" dateStyle="short" timeStyle="short"/>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	
		<!-- 페이징 처리 -->
		<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">
			<!-- 페이지 화살표 -->
			<c:choose>
				<c:when test="${curPageNo == 1 }">
					<li class="page-item disabled"><a class="page-link" href="#">◀</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo -1}&type=tip">◀</a></li>
				</c:otherwise>
			</c:choose>
			
			<!-- 초기 페이지 표시 -->
			<c:if test="${(curPageNo - 1) > 0 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=1&type=tip">1</a></li>
			</c:if>
			
			<!-- 현재페이지보다 -2 했을때 3보다 크면 ...을 표시 -->
			<c:if test="${(curPageNo - 2) > 2 }">
				<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
			</c:if>
			
			<!-- 현재페이지보다 -2 했을때 2보다 크면 페이지 표시 -->
			<c:if test="${(curPageNo - 2) > 1 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo-2 }&type=tip">${curPageNo-2 }</a></li>
			</c:if>
			
			<!-- 현재페이지보다 -1 했을때 2보다 크면 페이지 표시 -->
			<c:if test="${(curPageNo - 1) > 1 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo-1 }&type=tip">${curPageNo-1 }</a></li>
			</c:if>
			
			<!-- 현재페이지 -->
			<li class="page-item active"><a class="page-link" href="BoardPageC?p=${curPageNo }&type=tip">${curPageNo }</a></li>
			
			<!-- 현재페이지보다 +1 했을때 막페이지-1 보다크면 -->
			<c:if test="${(pageCount - 1) - (curPageNo + 1) > -1 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo+1 }&type=tip">${curPageNo+1}</a></li>
			</c:if>
			
			<!-- 현재페이지보다 +2 했을때 막페이지-1 보다크면 -->
			<c:if test="${(pageCount - 1)- (curPageNo + 2) > -1 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo+2}&type=tip">${curPageNo+2}</a></li>
			</c:if>
			
			<!-- 현재페이지보다 -2 했을때 막페이지-2 보다크면  -->
			<c:if test="${(pageCount - 2) - (curPageNo + 2) > -1}">
				<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
			</c:if>
			
			<!-- 마지막 페이지 표시 -->
			<c:if test="${(pageCount - curPageNo) > 0 }">
				<li class="page-item"><a class="page-link" href="BoardPageC?p=${pageCount }&type=tip">${pageCount}</a></li>
			</c:if>
			
			<!-- 화살표 페이지 표시 -->
			<c:choose>
				<c:when test="${curPageNo == pageCount }">
					<li class="page-item disabled"><a class="page-link" href="#">▶</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="BoardPageC?p=${curPageNo +1}&type=tip">▶</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
</body>
</html>