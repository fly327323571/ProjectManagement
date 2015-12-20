$(function(){
	
	var config = {
		URL : {
			COUNT_FAVORITE : "shop/{storeId}/dashboard/favorites/count".replace("{storeId}", storeId),
			COUNT_NEW_ORDER : "shop/{storeId}/dashboard/newOrders/count".replace("{storeId}", storeId),
			COUNT_INCOME : "shop/{storeId}/dashboard/income/count".replace("{storeId}", storeId),
			ORDER_HISTORY : "shop/{storeId}/orderHistory.do".replace("{storeId}", storeId),
			COUNT_NEW_COMMENT : "shop/{storeId}/dashboard/newComments/count".replace("{storeId}", storeId),
		}
	};
	
	new Dashboard(config);
});