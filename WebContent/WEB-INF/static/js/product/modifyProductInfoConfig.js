$(function(){
	var config = {
		URL : {
			SAVE : 'product/{productId}/modify'.replace("{productId}", productId), //保存修改的地址
			SUCCESS:'product/{storeId}/list/index.do'.replace("{storeId}", storeId), //修改成功后，或者取消修改后跳转的页面地址
			CHECK_PRODUCT_NAME : 'product/{shopNo}/*/checkProductName'.replace("{shopNo}", storeId)
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
		},
		{
			id:'commodityDetail',
			name:'commodityDetail'
		}]
	};
	new ModifyProductInfo(config);
});