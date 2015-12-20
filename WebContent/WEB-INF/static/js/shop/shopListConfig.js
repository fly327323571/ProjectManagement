$(function(){
	
	var tableRenderer = {//数据渲染到页面上的函数
		storeRenderer : function(data){
			var html = "<a target='_blank' href='" + shopListConfig.URL.SHOP_HOMEPAGE.replace("{shopNo}",data.shopNo)+ "'>" +
					"<img src='" + data.shopIcon + "'/><span id='store_name'>" + data.shopName + "</span></a>";
			return html;
		},
		categoryRenderer : function(data){
			if(data==0)
				return 'Food'
			else if(data==1)
				return 'Clothes'
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
			var storeId = data.shopNo;
			var status = data.status;
			var html = "";
			switch(status){
			case 1://正常开店
				var btn_1 = "<a target='_blank' href='" + shopListConfig.URL.LINK_SHOP.replace('{shopNo}',storeId) + 
				"'><button type='button' class='btn link-btn'>link shop</button></a>";
		
				var btn_2 = "<a target='_blank' href='" + shopListConfig.URL.SHOP_HOME.replace('{shopNo}',storeId) + 
				"'><button type='button' class='btn link-btn'>enter</button></a>";
				html = btn_1 +" "+ btn_2;
				break;
			case 0://申请中
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
			LIST : "shop/showList",
			LINK_SHOP : "shop/{shopNo}/manageShopLink/index.do",
			SHOP_HOME : "shop/{shopNo}/shopManageHomePage/index.do",
			SHOP_HOMEPAGE : "shop/{shopNo}/shopHomePage.do",
		},
		tableConfig : {
			metadata : [
			  {
				  name : 'id',//页面上显示的信息
				  data : 'Id',//对应Store.java 实体类的字段
				  visible : false,
				  render : null
			  },
			  {
				  name : 'Shop',
				  data : 'shopNo&shopIcon&shopName',
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
			  },{
				  name : 'operation',
				  data : 'shopNo & status',
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