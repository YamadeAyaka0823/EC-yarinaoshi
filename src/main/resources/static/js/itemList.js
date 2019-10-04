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