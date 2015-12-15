function ShopOwnerHomepage(config){
	var _config = config;
	
	//------------------------绑定事件----------------------//
	$(".content > .nav li  a").bind("click",function(e){
		e.preventDefault();
	});
	$(".content > .nav  li .option").bind("click",function(){
		var target=$(this).attr("href");
		$(".container").children("iframe").attr("src",target);
		$(".content > .nav  li .option").css("background-color","#F5F5F5");
		$(".content > .nav  li .option").css("color","black");
		$(this).css("background-color","#FF5400");
		$(this).css("color","white");
	});
	
	$("#nav_shoplist").bind("click",function(){
		$("#iframe").attr('src',_config.URL.MYSTORES);
	});
	
	$("#nav_new_shop").bind("click",function(){
		$("#iframe").attr('src',_config.URL.REG_STORE);
	});
	
	function _selectOption(optionId){
		$(".content > .nav  li .option").css("background-color","#F5F5F5");
		$(".content > .nav  li .option").css("color","black");
		$("#"+optionId).css("background-color","#FF5400");
		$("#"+optionId).css("color","white");
	}
	
	
	ShopOwnerHomepage.selectOption = _selectOption;
	
}

	
