$(function(){
	
	var tableRenderer = {//数据渲染到页面上的函数
		storeRenderer : function(data){
			var html = "<a target='_blank' href='" + manageShopLinkConfig.URL.SHOP_HOMEPAGE.replace("{storeId}",data.storeId) + "'>" +
					"<img src='" + data.logo + "'/><span>" + data.storeName + "</span></a>";
			return html;
		},
		categoryRenderer : function(data){
			return data;
		},
		creditRenderer : function(data){
			var span = "<span class='glyphicon glyphicon-star star'></span>";
			var html = "";
			for(var i=1;i<=data;i++){
				html = html + span + '\n';
			}
			return html;
		},
		regTimeRenderer : function(data){
			return data;
		},
		operationRenderer : function(storeId){
			var html="<input type='checkbox' name='linkTo' value="+storeId+" />";
			return html;
		}
	};
	
	var manageShopLinkConfig = {
		URL : {//页面所有的URL配置
			SUBMIT:'store/{storeId}/manageShopLink'.replace("{storeId}", storeId),
			DETAILS : "store/index.do",
			LIST : "store/listMyStores.json",
			SHOP_LINKS : "store/{storeId}/shopLinks.json".replace("{storeId}", storeId),
			SHOP_HOMEPAGE : "business/market/{storeId}/shopHomePage.do",
		},
		tableConfig : {
			metadata : [
			  {
				  name : '',//页面上显示的信息
				  data : 'storeId',//对应Store.java 实体类的字段
				  visible : true,
				  render : tableRenderer.operationRenderer
			  },
			  {
				  name : 'Store',
				  data : 'logo & storeName & storeId',
				  visible : true,
				  render : tableRenderer.storeRenderer
			  },{
				  name : 'Category',
				  data : 'category',
				  visible : true,
				  render : tableRenderer.categoryRenderer
			  },{
				  name : 'Credit',
				  data : 'creditValue',
				  visible : true,
				  render : tableRenderer.creditRenderer
			  },{
				  name : 'Register Time',
				  data : 'registerTimeString',
				  visible : true,
				  render : tableRenderer.regTimeRenderer
			  }]
		}
//		,
//		fieldTypeConfig : {
//			EQ : 1,
//			
//		}
	};
	
	
	
	new ManageShopLink(manageShopLinkConfig);
});