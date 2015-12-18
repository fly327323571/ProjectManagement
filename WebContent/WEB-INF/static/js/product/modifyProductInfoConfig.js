$(function(){
	var config = {
		URL : {
			SAVE : 'product/{storeId}/modify'.replace("{storeId}", storeId), //保存修改的地址
			SUCCESS:'product/{storeId}/list/index.do'.replace("{storeId}", storeId), //修改成功后，或者取消修改后跳转的页面地址
			CHECK_PRODUCT_NAME : 'product/{storeId}/{productId}/checkProductName'.replace("{storeId}", storeId).replace("{productId}",productId)
		},
		dialogRemind : function(message){},//占位符
		validateConfig : [{
			id : 'commodityName',
			name : 'commodityName',
			remind : this.dialogRemind,
			asynValidate:function(value){
				ModifyProductInfo.checkProductName(value);
			}
		},{
			id : 'commodityPrice',
			name : 'commodityPrice',
			remind : this.dialogRemind
		},{
			id : 'category',
			name : 'category',
			remind : function(message){
				this.dialogRemind('please select category of your product!');
			}
		},{
			id : 'commodityCount',
			name : 'commodityCount',
			remind : this.dialogRemind
		}]
	};
	new ModifyProductInfo(config);
});