<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Peter Pet</title>
<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="js/summernote.js"></script>
<script type="text/javascript" src="js/boardCheck.js"></script>
<script type="text/javascript" src="js/validCheck.js"></script>
</head>
<body>
<div class="main-preview">
		<form action="BoardUpdateC?no=${board.no }" method="post" class="recruit" name="myForm" onsubmit="return checkBoard();">
			<input type="hidden" name="type" value="${board.type}">
			<input type="hidden" name="page" value="${page}">
			<h3 class="recruit__header">팁과 노하우 글 작성</h3>
			<div class="recruit-content">
				<label for="" class="recruit-content__label">제목</label> 
				<input type="text" placeholder="ex) 자유롭게 작성해주세요."
					class="recruit-content__input recruit-content__input_full" name="title" value="${board.title }" />
					
				<label for="" class="recruit-content__label">내용</label>
				<textarea rows="5" id="summernote" name="content" >${board.content }</textarea>
				
				<!-- 버튼 -->
				<div class="recruit-content__btn-group">
					<input type="submit"
						class="recruit-content__btn recruit-content__btn_submit"
						value="수정" /> 
						<a href="BoardViewC?no=${board.no}&type${board.type}&p=${page}"
						class="recruit-content__btn recruit-content__btn_cancel">취소</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>