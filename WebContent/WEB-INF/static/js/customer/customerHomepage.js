function CustomerHomepage(config){
	var _config=config;
	
	if(portrait){
		$(".profile .img").css("background-image","url("+portrait+")");
		
	}
	//------------------------绑定事件----------------------//
	$(".content > .nav li  a").bind("click",function(e){
		e.preventDefault();
	});
	
	$("#view_profile").bind("click",function(){
		$("iframe").attr('src',_config.URL.VIEW_PROFILE);
	});
	
	$("#modify_profile").bind("click",function(){
		$("iframe").attr('src',_config.URL.MODIFY_PROFILE);
	});
	
	$("#order_history").bind("click",function(){
		$("iframe").attr('src',_config.URL.ORDER_HISTORY);
	});
	
	$("#myCart").bind("click",function(){
		window.location.href =_config.URL.MY_CART;
	});
	
	$("#myFavorite").bind("click",function(){
		window.location.href = _config.URL.MY_FAVORITE;
	});
	
	
	
	$(".content .img").hover(function(){
		if($(".content .img > div").css('display')=='none'){
			$(".content .img > div").slideDown('1000');
		}
	},function(){
		$(".content .img > div").slideUp('1000');
	});
	
	$(".content > .nav li  a").bind("click",function(e){
		e.preventDefault();
	});
	
	$(".content > .nav  li .option").bind("click",function(){
		$(".content > .nav  li .option").css("background-color","#F5F5F5");
		$(".content > .nav  li .option").css("color","black");
		$(this).css("background-color","#FF5400");
		$(this).css("color","white");
	});
	
	function _selectOption(optionId){
		$(".content > .nav  li .option").css("background-color","#F5F5F5");
		$(".content > .nav  li .option").css("color","black");
		$("#"+optionId).css("background-color","#FF5400");
		$("#"+optionId).css("color","white");
	}
	CustomerHomepage.selectOption = _selectOption;
}