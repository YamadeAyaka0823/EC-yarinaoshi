$(function(){
	calc_price();
	
	$(".size").on("change", function(){
		calc_price();
	});
	
	$(".checkbox").on("change", function(){
		calc_price();
	});
	
	$("#pizanum").on("change", function(){
		calc_price();
	});

function calc_price(){
	var size = $(".size:checked").val();
	var topping_count = $("#topping input.checkbox:checked").length;
	var piza_num = $("#pizanum option:selected").val();
	if(size == "M"){
		var size_price = parseInt($("#M").text().split(',').join('').trim());//split以降はparseIntに変換するときにエラーを出さないための記述
		var topping_price = 200 * topping_count;                               //もし1000円以下なら(,カンマ)がないからsplitなどの記述もいらない
	}else{
		var size_price = parseInt($("#L").text().split(',').join('').trim());//.text()は前の#Lを取ってくるという意味
		var topping_price = 300 * topping_count;
	}
	
	var price = (size_price + topping_price) * piza_num;
	var valuePrice = parseInt(price.toLocaleString().split(',').join('').trim());
	$("#totalprice").html('<input type="hidden" name="totalPrice" value= "'+ valuePrice +'">' + price.toLocaleString());
	
};
});