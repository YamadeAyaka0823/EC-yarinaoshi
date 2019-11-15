function clock(){
	//現在の日時
	var now = new Date();
	
	//時間の取得
	var hour = now.getHours();
	//分の取得
	var min = now.getMinutes();
	//秒の取得
	var sec = now.getSeconds();
	
	//2ケタ表示にする
	if(hour < 10) hour = "0" + hour;
	if(min < 10) min = "0" + min;
	if(sec < 10) sec = "0" + sec;
	
	//時間表示用の要素の取得
	var timeElement = document.getElementById("time");
	// 時間をセット
	timeElement.innerHTML = hour + ":" + min + ":" + sec;
	
	//年の取得
	var year = now.getFullYear();
	//月の取得
	var month = now.getMonth() + 1;
	//日の取得
	var day = now.getDate();
	
	//曜日の配列
	var weekArray = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	//曜日の取得
	var week = weekArray[now.getDay()];
	
	//日付表示用の要素の取得
	var dateElement = document.getElementById("date");
	//時間をセット
	dateElement.innerHTML = year + "/" + month + "/" + day + " " + week;
	
	//タイマー(1秒後に関数clockを実行)(1秒=1000ミリ秒)
	setTimeout("clock()", 1000);
	
}