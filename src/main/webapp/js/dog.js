let url = "http://api.odcloud.kr/api/15050442/v1/uddi:825b02de-f7ad-469a-b0ff-91b8e958c2df_201909241343?page=1&perPage=1528&serviceKey=Bko8yvz3KtGQk46gPKevjyb3G14HeLUyIxe9zAbgF50BrSG7DZ0%2FNqIrBLmNu%2Fbc3J%2FqFjbxBGAKuREN%2FPOAYg%3D%3D";
fetch(url)
	.then(res => res.json())
	.then(data => {
		var i = data.data[0].축종;
		var cnt = data.matchCount;
		console.log(data);
		console.log(cnt);

		var tabB = "<table border='1'>";
		tabB += "<th>번호</th><th>축종</th><th>질병명</th><th>정의</th><th>주요증상</th><th>원인</th>"
		var cnt2 = 1;
		for (var i = 0; i < cnt; i++) {
			if (data.data[i].축종.indexOf("개") != -1) {
				tabB += "<tr>";
				tabB += "<td>" + cnt2 + "</td>";
				tabB += "<td>" + data.data[i].축종 + "</td>";
				tabB += "<td>" + data.data[i].질병명 + "</td>";
				tabB += "<td>" + data.data[i].정의 + "</td>";
				tabB += "<td>" + data.data[i].주요증상 + "</td>";
				tabB += "<td>" + data.data[i].원인 + "</td>";
				tabB += "</tr>";

				cnt2++;
			}
		}
		tabB += "</table>";



		document.getElementById("rsV").innerHTML = tabB;
	})
	.catch(error => console.log(error));
	