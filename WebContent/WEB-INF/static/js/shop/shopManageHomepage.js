function ShopManageHomepage(config){
	var _config = config;
	
	if(logo){
		$(".profile .img").css("background-image","url("+logo+")");
	}
	//------------------------绑定事件----------------------//
	$(".content > .nav li  a").bind("click",function(e){
		e.preventDefault();
	});
	
	$("#modify_my_profile").bind("click",function(){
		$("#iframe").attr("src",_config.URL.MODIFY_MY_PROFILE);
	});
	
	$("#view_my_profile").bind("click",function(){
		$("#iframe").attr("src",_config.URL.MODIFY_MY_PROFILE);
	});
		
	$("#modify_shop_profile").bind("click",function(){
		$("#iframe").attr('src',_config.URL.MODIFY_SHOP_PROFILE);
	});
	
	$("#order_history").bind("click",function(){
		$("#iframe").attr('src',_config.URL.ORDER_HISTORY);
	});
	
	$("#manage_products").bind("click",function(){
		$("#iframe").attr('src',_config.URL.MANAGE_PRODUCT);
	});
	
	$("#dashboard").bind("click",function(){
		$("#iframe").attr('src',_config.URL.DASHBOARD);
	});
	
	$("#add_products").bind("click",function(){
		$("#iframe").attr('src',_config.URL.ADD_PRODUCT);
	});
	
	$("#manage_ads").bind("click",function(){
		$("#iframe").attr('src',_config.URL.MANAGE_ADS);
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
	
	//-------------------------外部可见函数----------------------//
	function _selectOption(optionId){
		$(".content > .nav  li .option").css("background-color","#F5F5F5");
		$(".content > .nav  li .option").css("color","black");
		$("#"+optionId).css("background-color","#FF5400");
		$("#"+optionId).css("color","white");
	}
	
	function _redirectToShopOwnerHomepage(){
		window.location.href = _config.URL.SHOP_OWNER_HOMEPAGE;
	}
	
	//使类ShopManageHomepage外部可以调用 selectOption方法
	//调用方式 ShopManageHomepage('manage_products') 'manage_products'为标签的id
	ShopManageHomepage.selectOption = _selectOption;
	//跳转到所有店铺的管理员面
	ShopManageHomepage.redirectToShopOwnerHomepage = _redirectToShopOwnerHomepage;
	
	ShopManageHomepage.contact = function(userId){
		$("#chat").attr("userId",userId);
		$("#chat .panel-body").show();
	};
	
	if(info){
		_redirectToShopOwnerHomepage();
	}
}