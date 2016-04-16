var toolbarFloatDisable;
$(function(){
	
	var config = {
			URL : {
				LOGIN : 'user/login',
				LOGOUT : 'user/logout',
				CUSTOMER_REGISTER : 'user/register/customerReg.html',
				SHOP_OWNER_REGISTER : 'views/user/shopOwnerRegister.jsp',
				MY_STORES : 'views/shopOwner/shopOwnerHomepage.jsp',
				MY_CART : 'cart/myCart.do',
				MY_HOMEPAGE :  'customer/customerHomePage',
				CART_COUNT : 'cart/count.json',
				SEARCH : 'search/cusSearch.do?query=',
				FAVORITE:'customer/myFavorite',
				FAVORITE_COUNT : 'user/favorites/count.json'
			}
	};
	
	$("#login").attr('href',config.URL.LOGIN);
	$("#logout").attr('href',config.URL.LOGOUT);
	$("#__register").attr('href',config.URL.CUSTOMER_REGISTER);
	$("#shopOwnerRegister").attr('href',config.URL.SHOP_OWNER_REGISTER);
	$("#myStores").attr('href',config.URL.MY_STORES);
	$(".sn-favorite").attr('href',config.URL.FAVORITE);
	$(".quick-menu .sn-homepage").attr('href',config.URL.MY_HOMEPAGE);
	$(".quick-menu .sn-cart").attr('href',config.URL.MY_CART);
	$("#quickTohomepage").attr('href','.');
	$("#quickTohomepage").css('margin-left','3px');
	$("#quickTohomepage").css('cursor','pointer');
	
	
	var minHeight = 60;
	$(window).bind("scroll",function() {
		if(toolbarFloatDisable!=undefined){
			return;
		}
		//获取窗口的滚动条的垂直位置      
		var s = $(window).scrollTop();
		//当窗口的滚动条的垂直位置大于页面的最小高度时 
		//console.log(s);
		if (s > minHeight) {//变成浮动
			if($(".site-nav").css('position')!='fixed'){
				$(".site-nav").slideUp('1000',function(){
					$(".site-nav").css('position', 'fixed');
					$(this).slideDown('1000');
				});
			}
		} else {//取消浮动
			if($(".site-nav").css('position')=='fixed'){
				$(".site-nav").slideUp('1000',function(){
					$(this).slideDown('1000',function(){
						$(".site-nav").css('position', 'static');
					});
				});
			}
		}
	});
	
	function fetchCartProductCount(){
		baseAjax.doAjax(config.URL.CART_COUNT, null, function(data){
			var count = data.result;
			if(count){
				var span = '<span class="badge" style="margin-left: 3px;background-color: #FF5400;">'+count+'</span>';
				var html = $(".quick-menu .sn-cart").html() + span;
				$(".quick-menu .sn-cart").html(html);
			}
		}, function(data){
			
		});
	}
	
	function bindSearchBox(){
		$(".search-box button[type='button']").bind("click",function(){
			var keyword = $(".search-box input[type='search']").val();
			window.location.href = config.URL.SEARCH + keyword;
		});
	}
	
	function fetchFavoriteCount(){
		baseAjax.doAjax(config.URL.FAVORITE_COUNT, null, function(data){
			var count = data.result;
			if(count){
				var span = '<span class="badge" style="margin-left: 3px;background-color: #FF5400;">'+count+'</span>';
				var html = $(".quick-menu .sn-favorite").html() + span;
				$(".quick-menu .sn-favorite").html(html);
			}
		}, function(data){
			
		});
	}
	
	fetchCartProductCount();
	bindSearchBox();
	fetchFavoriteCount();
	/**/
});