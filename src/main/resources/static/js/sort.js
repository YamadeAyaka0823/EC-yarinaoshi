$('.sort_btn').click(function(){
	
	var $elements = $('select#number > li').sort(function(a,b){
		var aa = $(a).text();
		var bb = $(b).text();
		
		if(aa > bb){
			return 1;
		}else if(aa < bb){
			return -1;
		}
		
		return 0;
	});
	
	$('select#number').empty();
	
	$elements.each(function(){
		$('select#number').append($(this));
	});
});