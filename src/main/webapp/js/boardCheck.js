function checkBoard(){
	let title = document.myForm.title;
	let content = document.myForm.content;
	
	//제목이 있는지 검사
	if(isEmpty(title)){
		alert('제목을 입력해 주세요.');
		title.focus();
		
		return false;
	}
	
	//내용이 있는지 검사
	if(content.value=='<p><br></p>' || isEmpty(content)){
		alert('내용을 입력해 주세요');
		content.focus();

		return false;
	}

}