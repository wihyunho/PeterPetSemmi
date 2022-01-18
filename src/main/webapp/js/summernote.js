$(document).ready(function() {
	 $('#summernote').summernote({
		 height: 788,                 // 에디터 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '내용',
          disableResizeEditor: true,	// 크기 조절 기능 삭제
          toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['picture','link']],
            ['view', ['fullscreen', 'help']]
          ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
        callbacks: {
			onImageUpload : function(files, editor, welEditable){
				for(var i = files.length-1; i>=0 ; i--){
					if(files[i].size > 1024 * 1024 * 16){
						alert('16MB 미만만 넣어야됨');
						return;
					}
				}
				
				sendFile(files[0], this);
			}
		}
   	});
	
	$('.dropdown-toggle').dropdown();
});

function sendFile(file, editor) {
    // 파일 전송을 위한 폼생성
	data = new FormData();
    data.append("uploadFile", file);
    $.ajax({ // ajax를 통해 파일 업로드 처리
        data : data,
        type : "POST",
        url : "/PeterPet/function/summernote_imageUpload.jsp",
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) { // 처리가 성공할 경우
            // 에디터에 이미지 출력
            console.log(data);
        	
            console.log(data.url);
        	
        	$(editor).summernote('editor.insertImage', data.url);
        }
    });
}