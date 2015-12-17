$(function(){
	var modifyShopProfileConfig = {
		URL : {
			MODIFY : "shop/"+$("#storeId").val()+"/modify",
			CHECK_STORE_NAME : "shop/{storeId}/checkShopName".replace("{storeId}", $("#storeId").val()),
			POSTAGE_POLICY : "shop/{storeId}/expressRule.json".replace("{storeId}", $("#storeId").val())
		},
		basicValidateConfig : [{
			id : 'shopName',
			name : 'shop name',
			asynValidate : function(value){
				modifyShopProfile.shopNameExistCheck(value);
			}
		},{
			id : 'category',
			name : 'category',
			remind : 'please select your store category!'
		}],
		policyValidateConfig :[{
			id : 'policy',
			name : 'postage policy',
			remind : 'please select the postage policy'
		},{
			id : 'within_basicPostage',
			name : 'basic postage within province'
		},{
			id : "within_quantityLimit",
			name : 'quantity limit within province'
		},{
			id : 'within_extfee',
			name : 'extra fee per product within province'
		},{
			id : 'cross_basicPostage',
			name : 'basic postage cross the province'
		},{
			id : "cross_quantityLimit",
			name : 'quantity limit cross the province'
		},{
			id : 'cross_extfee',
			name : 'extra fee per product cross the province'
		},]
	};
	new modifyShopProfile(modifyShopProfileConfig);
});