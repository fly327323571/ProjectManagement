$(function(){
	var config = {
		URL : {
			FILE_UPLOAD : "upload/fileUpload",
			UPLOAD_ADS : "store/{storeId}/manageAd.json".replace("{storeId}", storeId),
			DASH_BOARD : "store/{storeId}/dashboard/index.do".replace("{storeId}", storeId)
		},
		validateConfig : [{
			id : 'product1',
			name : 'product1',
			remind : "please select your product for advertisement"
		},{
			id : 'product2',
			name : 'product2',
			remind : "please select your product for advertisement"
		},{
			id : 'product3',
			name : 'product3',
			remind : "please select your product for advertisement"
		}]
	};
	new AdManage(config);
});