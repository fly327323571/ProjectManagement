$(function(){
	
	var config = {
		URL : {
			COUNT_FAVORITE : "store/{storeId}/dashboard/favorites/count.json".replace("{storeId}", storeId),
			COUNT_NEW_ORDER : "store/{storeId}/dashboard/newOrders/count.json".replace("{storeId}", storeId),
			COUNT_INCOME : "store/{storeId}/dashboard/income/count.json".replace("{storeId}", storeId),
			ORDER_HISTORY : "store/{storeId}/orderHistory.do".replace("{storeId}", storeId),
			COUNT_NEW_COMMENT : "store/{storeId}/dashboard/newComments/count.json".replace("{storeId}", storeId),
		}
	};
	
	new Dashboard(config);
});