$(function(){
	
	var tableRenderer = {//数据渲染到页面上的函数
		storeRenderer : function(data){
			var html = "<a target='_blank' href='" + shopListConfig.URL.SHOP_HOMEPAGE.replace("{storeId}",data.storeId)+ "'>" +
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
		operationRenderer : function(data){
			var storeId = data.storeId;
			var status = data.status;
			var html = "";
			switch(status){
			case 0 ://正常开店
				var btn_1 = "<a target='_blank' href='" + shopListConfig.URL.LINK_SHOP.replace('{storeId}',storeId) + 
				"'><button type='button' class='btn link-btn'>link shop</button></a>";
		
				var btn_2 = "<a target='_blank' href='" + shopListConfig.URL.SHOP_HOME.replace('{storeId}',storeId) + 
				"'><button type='button' class='btn link-btn'>enter</button></a>";
				html = btn_1 +" "+ btn_2;
				break;
			case 1://申请中
				var btn_1 = '<a><button style="margin-top:20px" type="button" class="btn" disabled="disabled">applying</button></a>';
				html = btn_1;
				break;
			case 2://拒绝
				var btn_1 = '<a><button style="margin-top:20px" type="button" class="btn">rejected</button></a>';
				html = btn_1;
				break;
			}
			
			return html;
		}
	};
	
	var shopListConfig = {
		URL : {//页面所有的URL配置
			LIST : "store/listMyStores.json",
			LINK_SHOP : "store/{storeId}/manageShopLink/index.do",
			SHOP_HOME : "store/{storeId}/shopManageHomePage/index.do",
			SHOP_HOMEPAGE : "business/market/{storeId}/shopHomePage.do",
		},
		tableConfig : {
			metadata : [
			  {
				  name : 'id',//页面上显示的信息
				  data : 'storeId',//对应Store.java 实体类的字段
				  visible : false,
				  render : null
			  },
			  {
				  name : 'Shop',
				  data : 'storeId & logo & storeName',
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
			  },{
				  name : 'operation',
				  data : 'storeId & status',
				  visible : true,
				  render : tableRenderer.operationRenderer
			  }]
		}
//		,
//		fieldTypeConfig : {
//			EQ : 1,
//			
//		}
	};
	
	
	
	new ShopList(shopListConfig);
});