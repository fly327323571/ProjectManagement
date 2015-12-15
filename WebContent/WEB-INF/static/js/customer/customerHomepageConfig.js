$(function(){
	var config={
			URL:{
				ORDER_HISTORY:'business/orderHistory/index.do',
				MY_CART:'cart/myCart',
				MY_FAVORITE:'user/getMyFavorites',
				VIEW_PROFILE:'user/showProfile',
				MODIFY_PROFILE:'user/showProfile',
			}
	}
	new CustomerHomepage(config);
})