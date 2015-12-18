$(function(){
	var config = {
		URL : {
			FILE_UPLOAD : "advertise/{shopNo}/upload/fileUpload".replace("{shopNo}", storeId),
			UPLOAD_ADS : "store/{shopNo}/manageAd".replace("{shopNo}", storeId),
			DASH_BOARD : "store/{shopNo}/dashboard/index".replace("{shopNo}", storeId)
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