function main() {
    var a = document.getElementById("test3");
    var b = document.getElementById("test4");
    a.innerHTML;
    b.innerHTML;
    if(a.innerHTML != undefined ||  b.innerHTML != undefined){
    startXHR(); //function startXHR로 이동
    }
    else{
        windows.location.reload(true);
    }
}

function getParam(sname) {
	params = location.search.substr(location.search.indexOf("?") + 1);
	var sval = "";
	params = params.split("&");
	for (var i = 0; i < params.length; i++) {
		temp = params[i].split("=");
		if ([temp[0]] == sname) { sval = temp[1]; }
	}
	return sval;
}
document.getElementById("test1").innerHTML = getParam("co");
document.getElementById("test2").innerHTML = getParam("ty");
//////////////////////
var xhrP;
var xhrD;

var urlP = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro'; /*URL*/
urlP += "?ServiceKey=" + "Bko8yvz3KtGQk46gPKevjyb3G14HeLUyIxe9zAbgF50BrSG7DZ0%2FNqIrBLmNu%2Fbc3J%2FqFjbxBGAKuREN%2FPOAYg%3D%3D";
urlP += "&pageNo=";
urlP += "&MobileOS=ETC";
urlP += "&MobileApp=AppTest";
urlP += "&contentId=" + getParam("co");
urlP += "&contentTypeId=" + getParam("ty");

var urlD = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon'; /*URL*/
urlD += "?ServiceKey=" + "Bko8yvz3KtGQk46gPKevjyb3G14HeLUyIxe9zAbgF50BrSG7DZ0%2FNqIrBLmNu%2Fbc3J%2FqFjbxBGAKuREN%2FPOAYg%3D%3D";
urlD += "&numOfRows=";
urlD += "&pageNo=";
urlD += "&MobileOS=ETC";
urlD += "&MobileApp=AppTest";
urlD += "&contentId=" + getParam("co");
urlD += "&defaultYN=Y";
urlD += "&firstImageYN=Y";
urlD += "&addrinfoYN=Y";
urlD += "&overviewYN=Y";



function createXHR() {
	if (window.ActiveXObject) {
		// 브라우저 IE (뉴버전 Msxml2.XMLHTTP / Microsoft.XMLHTTP)
		xhrP = new ActiveXObject("Msxml2.XMLHTTP");
		xhrD = new ActiveXObject("Msxml2.XMLHTTP");
	} else {
		// 그 외 브라우저
		xhrP = new XMLHttpRequest(); // xhr을 얻음
		xhrD = new XMLHttpRequest(); // xhr을 얻음
	}
}

function startXHR() {
	createXHR();
	xhrP.open('GET', urlP, true);
	xhrD.open('GET', urlD, true);
	xhrP.onreadystatechange = function() {
		if (xhrP.readyState == 4 && xhrD.readyState == 4) {
			if (xhrP.status == 200 && xhrD.status == 200) {
				process();
			}
		}
	}
	xhrP.send("");
	xhrD.send("");

}

function process() {
	var statusP = xhrP.status;	// 상태 200
	var headersP = xhrP.getAllResponseHeaders;
	var dataXP = xhrP.responseXML;
	var statusD = xhrD.status;	// 상태 200
	var headersD = xhrD.getAllResponseHeaders;
	var dataXD = xhrD.responseXML;

	// json으로 변환
	var dataP = xmlToJson.parse(dataXP);
	var dataD = xmlToJson.parse(dataXD);

	var itemP = dataP.response["body"].items.item;	// 소개정보
	var itemD = dataD.response["body"].items.item;	// 공통정보
	//	var testt = item.sponsor1;
	//	var test22 = item.chkpet;


	///////////
	var a = {// 관광지 12
		"<b style=\"font-size: 20px;\">동물동반가능여부</b><br>": itemP.chkpet+"<hr>",
		"<b>문의및안내: </b>": itemP.infocenter,
		"<b>이용시간: </b>": itemP.usetime,
		"<b>쉬는날: </b>": itemP.restdate,
		"<b>주차시설: </b>": itemP.parking
	};
	var b = {// 문화시설 14
		"<b style=\"font-size: 20px;\">동물동반가능여부</b><br>": itemP.chkpetculture+"<hr>",
		"<b>문의및안내: </b>": itemP.infocenterculture,
		"<b>관람소요시간: </b>": itemP.spendtime,
		"<b>이용요금: </b>": itemP.usefee,
		"<b>이용시간: </b>": itemP.usetimeculture,
		"<b>쉬는날: </b>": itemP.restdateculture,
		"<b>주차시설: </b>": itemP.parkingculture,
		"<b>주차요금: </b>": itemP.parkingfee
	};
	var c = {// 레포츠 28
		"<b style=\"font-size: 20px;\">동물동반가능여부</b><br>": itemP.chkpetleports+"<hr>",
		"<b>문의및안내: </b>": itemP.infocenterleports,
		"<b>예약안내: </b>": itemP.reservation,
		"<b>체험가능연령: </b>": itemP.expagerangeleports,
		"<b>입장료: </b>": itemP.usefeeleports,
		"<b>이용시간: </b>": itemP.usetimeleports,
		"<b>쉬는날: </b>": itemP.restdateleports,
		"<b>주차시설: </b>": itemP.parkingleports,
		"<b>주차요금: </b>": itemP.parkingfeeleports
	};
	var d = {// 숙박 32 (문의 ㄱ ㄱ)
		"<b>문의및안내: </b>": itemP.infocenterlodging,
		"<b>예약안내 홈페이지: </b>": itemP.reservationurl,
		"<b>환불규정: </b>": itemP.refundregulation,
		"<b>입실시간: </b>": itemP.checkintime,
		"<b>퇴실시간: </b>": itemP.checkouttime,
		"<b>주차시설: </b>": itemP.parkinglodging,
		"<b>노래방여부: </b>": itemP.karaoke,
		"<b>객실내취사여부: </b>": itemP.chkcooking,
		"<b>바비큐장여부: </b>": itemP.barbecue
	};
	var e = {// 쇼핑 38
		"<b style=\"font-size: 20px;\">동물동반가능여부</b><br>": itemP.chkpetshopping+"<hr>",
		"<b>매장안내: </b>": itemP.shopguide,
		"<b>문의및안내: </b>": itemP.infocentershopping,
		"<b>영업시간: </b>": itemP.opentime,
		"<b>쉬는날: </b>": itemP.restdateshopping,
		"<b>주차시설: </b>": itemP.parkingshopping,
		"<b>판매품목: </b>": itemP.saleitem,
		"<b>판매품목별가격: </b>": itemP.saleitemcost,
		"<b>화장실설명: </b>": itemP.restroom
	};
	if (getParam("ty") == 12) var arrayP = a;
    else if (getParam("ty") == 14) var arrayP = b;
    else if (getParam("ty") == 28) var arrayP = c;
    else if (getParam("ty") == 32) var arrayP = d;
    else if (getParam("ty") == 38) var arrayP = e;
	///////////
	var arrayD = {		
		"<img src='": itemD.firstimage + "' style=\"width: 100%\"><hr>",
		"<b>제목 </b><br>": itemD.title+"<hr>",
		"<b>주소 </b><br>": itemD.addr1 +"<hr>",	
		"<b>홈페이지 </b><br>": itemD.homepage+"<hr>",
		"<b>설명 </b><br>": itemD.overview+"<hr>"
	};
	///////////
	var putD = "<ul>"
	for (var key in arrayD) {
		if (arrayD[key] != undefined && String(arrayD[key]).indexOf("undefined") == -1  ){
			putD += "<ul><li>" + key + arrayD[key] + "</li></ul>";
		}

	}
	putD += "</ul>"
	///////////
	var putP = "<ul>"
    for (var key in arrayP) {
      	if (arrayP[key] != undefined && String(arrayP[key]).indexOf("[object Object]") == -1  ) {
            putP += "<ul><li>" + key + arrayP[key] + "</li></ul>";
        }else{
            continue;
        }
    }
	putP += "</ul>"
	
	console.log(arrayP);
	///////////
	/*
	var putD = "<ul>"
	for (var key in itemD) {
		putD += "<li>" + key + "<br>" + itemD[key] + "</li>";

	}
	putD += "</ul>"

	var putP = "<ul>"
	for (var key in itemP) {
		putP += "<li>" + key + "<br>" + itemP[key] + "</li>";

	}
	putP += "</ul>"
	*/

	document.getElementById("test4").innerHTML = putP;
	document.getElementById("test3").innerHTML = putD;
	//console.log(data);
	//console.log(test);
	console.log(itemD);
}

function back() {
	history.back();
}
