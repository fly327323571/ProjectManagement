$(function(){
	
	var config = {
		URL : {
			ADD : "product/{shopNo}/add".replace('{shopNo}',storeId),
			DELETE : "product/{shopNo}/delete".replace('{shopNo}',storeId),
			CHECK_PRODUCT_NAME : 'product/{shopNo}/*/checkProductName'.replace("{shopNo}", storeId),
			LIST : "product/{shopNo}/list/index.do".replace('{shopNo}',storeId),
			UPLOADIMG:"product/{shopNo}/upload/CommodityImg.do".replace('{shopNo}',storeId)
		},
		validateConfig : [{
			id : 'commodityName',
			name : 'commodityName',
			asynValidate : function(value){
				AddProduct.checkProductName(value);
			}
		},{
			id : 'commodityPrice',
			name : "commodityPrice"
		},{
			id : 'type',
			name : 'category',
			remind : 'please choose the category'
		},{
			id : "commodityCount",
			name : 'commodityCount'
		},{
			id : 'commodityDetail',
			name :'commodityDetail'
		}]
	};
	
	new AddProduct(config);
});