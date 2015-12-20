$(function(){
	
	var tableRenderer = {//数据渲染到页面上的函数
		storeRenderer : function(data){
			var html = "<a target='_blank' href='" + manageShopLinkConfig.URL.SHOP_HOMEPAGE.replace("{storeId}",data.shopNo) + "'>" +
					"<img src='" + data.shopIcon + "'/><span>" + data.shopName + "</span></a>";
			return html;
		},
		categoryRenderer : function(data){
			switch(data){
			case 0:return 'Food'; 
			case 1:return 'Clothes';
			}
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
			var html="<input type='checkbox' name='linkTo' value="+data.shopNo+" shopIcon="+data.shopIcon+" shopNo="+storeId+"  />";
			return html;
		}
	};
	
	var manageShopLinkConfig = {
		URL : {//页面所有的URL配置
			SUBMIT:'store/{shopNo}/manageShopLink'.replace("{shopNo}", storeId),
			DETAILS : "store/index.do",
			LIST : "shop/link/{shopNo}/showList".replace("{shopNo}", storeId),
			SHOP_LINKS : "store/{shopNo}/shopLinks.json".replace("{shopNo}", storeId),
			SHOP_HOMEPAGE : "business/market/{shopNo}/shopHomePage.do".replace("{shopNo}", storeId),
		},
		tableConfig : {
			metadata : [
			  {
				  name : '',//页面上显示的信息
				  data : 'shopNo&shopIcon',//对应Store.java 实体类的字段
				  visible : true,
				  render : tableRenderer.operationRenderer
			  },
			  {
				  name : 'Store',
				  data : ' shopIcon & shopName & shopNo',
				  visible : true,
				  render : tableRenderer.storeRenderer
			  },{
				  name : 'Category',
				  data : 'shopCategories',
				  visible : true,
				  render : tableRenderer.categoryRenderer
			  },{
				  name : 'Credit',
				  data : 'shopRank',
				  visible : true,
				  render : tableRenderer.creditRenderer
			  },{
				  name : 'Register Time',
				  data : 'regTime',
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