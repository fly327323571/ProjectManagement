$(function(){
	var config = {
		URL : {
			SAVE : 'product/{storeId}/modify.json'.replace("{storeId}", storeId), //保存修改的地址
			SUCCESS:'product/{storeId}/list/index.do'.replace("{storeId}", storeId), //修改成功后，或者取消修改后跳转的页面地址
			CHECK_PRODUCT_NAME : 'product/{storeId}/{productId}/checkProductName.json'.replace("{storeId}", storeId).replace("{productId}",productId)
		},
		dialogRemind : function(message){},//占位符
		validateConfig : [{
			id : 'productName',
			name : 'product name',
			remind : this.dialogRemind,
			asynValidate:function(value){
				ModifyProductInfo.checkProductName(value);
			}
		},{
			id : 'price',
			name : 'price',
			remind : this.dialogRemind
		},{
			id : 'category',
			name : 'category',
			remind : function(message){
				this.dialogRemind('please select category of your product!');
			}
		},{
			id : 'amounts',
			name : 'quantity',
			remind : this.dialogRemind
		}]
	};
	new ModifyProductInfo(config);
});