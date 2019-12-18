/** 商品削除と更新を選ぶ際に使う確認ダイアログ */
function Check(action){
	if(action == 1){
		document.getElementsByName("button")[0].value = "1";
		var result = confirm('本当に削除していいですか');
		if(result == true){
			document.form.submit();
		}else{
			return false;
		}
	}
	if(action == 2){
		document.getElementsByName("button")[0].value = "2";
		document.form.submit();
	}
};

/** トッピング削除の際に使う確認ダイアログ */
function clickEvent(){
	var result = confirm('本当に削除していいですか');
	if(result == true){
		document.form.submit();
	}else{
		return false;
	}
};
//	var value = document.getElementsByName("button")[0].value;
//	if(action == 2){
//		document.form.submit();
//	}
//	var result = confirm('本当に削除していいですか');
//	if(result == true && action == 1){
//		document.form.submit();
//	}else{
//		return false;
//	}
//};
