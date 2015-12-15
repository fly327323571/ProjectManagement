$(function(){
	
	var config = {
		URL : {
			ADD : "product/{storeId}/add.json".replace('{storeId}',storeId),
			DELETE : "product/{storeId}/delete.json".replace('{storeId}',storeId),
			CHECK_PRODUCT_NAME : 'product/{storeId}/*/checkProductName.json'.replace("{storeId}", storeId),
			LIST : "product/{storeId}/list/index.do".replace('{storeId}',storeId)
		},
		validateConfig : [{
			id : 'productName',
			name : 'product name',
			asynValidate : function(value){
				AddProduct.checkProductName(value);
			}
		},{
			id : 'presentPrice',
			name : "price"
		},{
			id : 'type',
			name : 'category',
			remind : 'please choose the category'
		},{
			id : "amounts",
			name : 'quantity'
		},{
			id : 'description',
			name :'description'
		}]
	};
	
	new AddProduct(config);
});