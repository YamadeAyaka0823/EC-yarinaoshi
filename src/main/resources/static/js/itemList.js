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
	$(".price").on("click", function(){
		var fm = document.getElementById("form");
		var action = $(this).val();
		var pushButton = parseInt($('#form [name=priceSort]').val());
		if(action == "1"){
			$('#form [name=priceSort]').val(pushButton = 1);
		} else{
			$('#form [name=priceSort]').val(pushButton = 2);
		}
        fm.submit();
	})
});

//$(function(){
//	$(".price2").on("click", function(){
//		var fm = document.getElementById("form");
//        fm.submit();
//	})
//});