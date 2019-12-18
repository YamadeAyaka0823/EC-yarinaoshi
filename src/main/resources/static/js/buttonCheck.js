/** 商品更新のときの登録か戻るの際に使う */
function buttonCheck(action){
	if(action == 1){
		document.getElementsByName("button")[0].value = "1";
		document.form.submit();
	}
	if(action == 2){
		document.getElementsByName("button")[0].value = "2";
		document.form.submit();
	}
};