window.onload = function() {
	document.getElementById("btnOk").onclick = function() {
		document.getElementById("Paging").value = 1;
		url = apiUrl();
		startXHR(); //function startXHR로 이동
	}
}

//////// api_url
var xhr;
var page;
var areacode;
var typecode;
var catcode;
var cat2code;
var url;
var tyID;
var coID;
var a = 1000;


function apiUrl(){
	page = 1;
	var area = document.getElementById("areacode");
	areacode = area.options[area.selectedIndex].value;
	var type = document.getElementById("typecode");
	typecode = type.options[type.selectedIndex].value;


	var urlA = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"; // URL
	urlA += "?ServiceKey=" + "Bko8yvz3KtGQk46gPKevjyb3G14HeLUyIxe9zAbgF50BrSG7DZ0%2FNqIrBLmNu%2Fbc3J%2FqFjbxBGAKuREN%2FPOAYg%3D%3D";
	urlA += "&pageNo=" + page;
	urlA += "&numOfRows=" + a;	//한페이지결과
	urlA += "&MobileApp=AppTest";
	urlA += "&MobileOS=ETC";
	urlA += "&arrange=O";		//대표이미지 반드시 있는(O=제목순, P=조회순, Q=수정일순, R=생성일순 / 없는(A=제목순, B=조회순, C=수정일순, D=생성일순)
	urlA += "&cat1=";
	urlA += "&contentTypeId=" + typecode;
	urlA += "&areaCode=" + areacode;
	urlA += "&sigunguCode=";
	urlA += "&cat2=";
	urlA += "&cat3=";
	urlA += "&listYN=Y";
	urlA += "&modifiedtime=";
	return urlA;
}



function createXHR() {
	if (window.ActiveXObject) {
		// 브라우저 IE (뉴버전 Msxml2.XMLHTTP / Microsoft.XMLHTTP)
		xhr = new ActiveXObject("Msxml2.XMLHTTP");
	} else {
		// 그 외 브라우저
		xhr = new XMLHttpRequest(); // xhr을 얻음
	}
}

function startXHR() {
	//apiUrl();
	createXHR();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				process();
				
			}
		}
	}
	xhr.send("");
}



/////// 데이터 불러오기
function process() {
	// xml 데이터 
	var status = xhr.status;	// 상태 200
	var headers = xhr.getAllResponseHeaders;
	var dataX = xhr.responseXML;

	// json으로 변환
	var data = xmlToJson.parse(dataX);
	var item = data.response["body"].items.item;

	a = data.response["body"].totalCount;
	// 출력 
	var tabB = "";
	var img;
	var tab;
	/////////////////////////////////////////////////////////
	
	var curPage = document.getElementById("Paging").value;//현재페이지
	
	
	// 규칙
	var dataSize = item.length;	//총데이터의 갯수
	var pageSize = 18;	//한페이지당 보여주는 갯수
	
	//총페이지수 = 나눈거 올림
	var totalPage = Math.ceil(dataSize/pageSize);//총토탈 페이지수
	
	// 시작데이터 번호, 끝데이터 번호
	var startData = (curPage -1) * pageSize + 1;//
	var endData = (curPage == totalPage) ? dataSize : startData + pageSize-1; 
	////////////////////////////////////////////////
	for (var i = startData; i <= endData; i++) {
		if(item[i] != null){
			if (item[i].firstimage != null) {
				img = item[i].firstimage;
			} else {
				//이미지없음
				img = "images/noimage.png";
			}
			coID = item[i].contentid;
			tyID = item[i].contenttypeid;
			
			tabB += "<li onclick='api2("+tyID +","+coID +");' class='card-item'>";
				tabB += "<div class='card-ly1' style='background-image: url("+img+");'></div>";
				tabB += "<a href='#' class='card card_big'>";
					tabB += "<h3 class='card__head fs_28'>"+item[i].title+"</h3> ";
					tabB += "<span class='card__date'>"+item[i].addr1+"</span>";
				tabB += "</a>";	
			tabB == "</li>";
		}
	}


	
	var pageB ="";
	//페이징 처리
	pageB += "<ul class='pagination' style='display: table; margin-left: auto; margin-right: auto;'>"
		//페이지 화살표 
		if(curPage==1){
			pageB += "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">◀</a></li>";
		}else{
			pageB += "<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)-1)+")\">◀</a></li>";
		}
		
		//1페이지
		if(parseInt(curPage)-1 > 0){
			pageB += "<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)-1)+")\">1</a></li>";
		}
		
		//...페이지
		if(parseInt(curPage)-2 > 2){
			pageB += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>";
		}
		
		//현재 페이지 - 2
		if(parseInt(curPage)-2 > 1){
			pageB += "<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)-2)+")\">"+(parseInt(curPage)-2)+"</a></li>";
		}
		
		//현재 페이지 - 1
		if(parseInt(curPage)-1 > 1){
			pageB += "<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)-1)+")\">"+(parseInt(curPage)-1)+"</a></li>";
		}

		//현재페이지
		pageB +="<li class=\"page-item active\"><a class=\"page-link\" href=\"#\">"+parseInt(curPage)+"</a></li>"
		
		//현재페이지 + 1
		if(totalPage-curPage-2 > -1){
			pageB +="<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)+1)+")\">"+(parseInt(curPage)+1)+"</a></li>";
		}
		
		//현재페이지 + 2
		if(totalPage-curPage-3> -1){
			pageB +="<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)+2)+")\">"+(parseInt(curPage)+2)+"</a></li>";
		}
		
		//...
		if(totalPage-curPage-4 > -1){
			pageB +="<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>";
		}
		
		//마지막 페이지 표시
		if(totalPage-curPage > 0){
			pageB +="<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(totalPage))+")\">"+totalPage+"</a></li>";
		}
		/*		
		
		<!-- 마지막 페이지 표시 -->
		<c:if test="${(pageCount - curPageNo) > 0 }">
			<li class="page-item"><a class="page-link" href="BoardPageC?p=${pageCount }&type=free">${pageCount}</a></li>
		</c:if>
		*/
		
		//화살표 페이지 표시
		if(curPage == totalPage){
			pageB += "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">▶</a></li>"
		}else{
			pageB += "<li class=\"page-item\"><a class=\"page-link\" onclick=\"movePage("+(parseInt(curPage)+1)+")\">▶</a></li>"
		}
	pageB += "</ul>"
	
	document.getElementById("rsV").innerHTML = tabB;
	document.getElementById("Page").innerHTML = pageB;
}

function movePage(mpage){
	document.getElementById("Paging").value = mpage;
	process();
	window.scrollTo(0,0);
}


function api2(tyID,coID){
     location.href='TourViewC?'+"ty=" +tyID+"&co=" +coID;
}


