//$(function(){
//	var fm = document.getElementById("form");
//	$("#next").on("click", function(){
//		
//		document.form.submit();
//		
//	})
//})

$(function(){
	$(".pageNumber").on("click", function(){		
		var fm = document.getElementById("form");
		var action = $(this).val();
//		fm.method = "post"
		var currentPage = parseInt($('#form [name=pageNumber]').val());
		if (action == "pre") {
			$('#form [name=pageNumber]').val(currentPage - 1);
		} else {
			$('#form [name=pageNumber]').val(currentPage + 1);
		}
		fm.submit();
	})
});

$(function(){
	$(".price1").on("click", function(){
//		var fm = document.getElementById("form");
		$('#form [name=pageNumber]').val(1);
		$('#form [name=priceSort]').val($(this).val());
//		var action = $(this).val();
//		var pushButton = parseInt($('#form [name=priceSort]').val());
//		if(action == "1"){
//			$('#form [name=priceSort]').val($(this).val());
//		} else{
//			$('#form [name=priceSort]').val(2);
//		}
		document.getElementById("form").submit();
	})
});

//$(function(){
//	$(".price2").on("click", function(){
//		var fm = document.getElementById("form");
//        fm.submit();
//	})
//});