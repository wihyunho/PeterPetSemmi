
// 일반적으로 하는 유효성 검사들

// 함수형태로 정리해서
// 나중에 필요할때 쓸라고(쓰기 편하게)
// .jar 쓰던것처럼(lib)

// 문제가 있을때 true, 괜찮으면 false

// <input>을 넣으면..
// 거기에 글자가 없으면 true, 있으면 false

function isEmpty(input){
	return !input.value;	//값이 없다
}

// <input>랑 글자 수를 넣으면
// 그 글자수 보다 적으면 true, 아니면 false

function lessThan(input, length){
	return input.value.length < length;
}

// <imput>을 넣으면
// 한글/특수문자가 들어있으면 true, 아니면 false

function containKR(input){
	let ok = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789@_.";
	
	for(let i = 0; i < input.value.length;i++){
		if(ok.indexOf(input.value[i]) == -1){
			return true;
		}
	}
}

//Test

// <input> x 2 넣으면
// 내용이 다르면 true, 아니면 false

function notEquals(input1, input2){
	return input1.value != input2.value;
}

// <input>, 문자열 세트
// 그 문자열 세트가 포함 안되있으면 true
// 들어있으면 false

function notContains(input, set){
	//input : 1qweASD
	
	// set : 123456789 숫자를 반드시 포함 시키고 싶다.
	
	for(let i = 0; i < set.length; i++){
		if(input.value.indexOf(set[i]) != -1){
			return false;
		}
	}	
	return true;
}

// <input>을 넣어서				전화번호, 생년월일
// 숫자가 아니면 true, 숫자면 false

function isNotNumber(input){
	return isNaN(input.value);//자바스크립트 내부에있는 기능
}

// <input>, 확장자를 넣게
// 최대한 넓은 범우로 활용하려 하는데
// 사이트마다 다 다를 수 있음
// xxx(phothoInput, "jpg")
// 그게 아니면 true, 그거면 false

function isNotType(input, type){
	type = "." + type; // .jpg
	return input.value.indexOf(type) == -1; 	// input에 jpg가 있나 없나
}














