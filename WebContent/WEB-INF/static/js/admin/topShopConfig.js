$(function(){

	var tableRenderer = {//数据渲染到页面上的函数
		shopRenderer : function(data){
			var img = data.logo;
			var html = '<img src="'+data.logo+'" height="80px" width="80px";>'
						+'<span>'+ data.storeName +'</span>';
			return html;
		},
		titleRenderer : function(data){
			return data;
		},
		linkRenderer : function(data){
			return data;
		},
		timeRenderer : function(data){
			var html = "<b>"+data+"</b>";
			return html;
		},
		payMoneyRenderer : function(data){
			return data;
		},
		operationRenderer : function(data){
			var adId = data.adId;
			var status = data.status;
			var html="";
				var btn_0 = '<button type="button" class="btn btn-style link-btn edit_btn" adId="'+adId+'">Edit</button></a>';
				var btn_1 = '<button type="button" class="btn btn-style link-btn delete_btn"  adId="'+adId+'">Delete</button></a>';
				html= btn_0 + '\n' + btn_1;
			return html;
		}
	};
	
	
	var topShopConfig = {
			URL : {//页面所有的URL配置
				LIST : "admin/adver/shop/list",//获得table中个元素信息
				ADD : "admin/adver/shop/add",
				DELETE : "admin/adver/shop/delete/{adId}",
				EDIT : "admin/adver/shop/edit/{adId}",
				FETCH : "admin/adver/shop/detail/{shopId}.json"
			},
			tableConfig : {
				header : ["Shop","Title","Link","Show Time",
				          "Pay Money","Opreation"],
				metadata : [
				  {
					  name : 'id',
					  data : 'adId',
					  visible : false,
					  render : null
				  },
				  {
					  name : 'Shop',
					  data : 'logo & storeName',
					  visible : true,
					  render : tableRenderer.shopRenderer
				  },
				  {
					  name : 'Title',
					  data : 'title',
					  visible : true,
					  render : tableRenderer.titleRenderer
				  },
				  {
					  name : 'Link',
					  data : 'link',
					  visible : true,
					  render : tableRenderer.linkRenderer
				  },
				  {
					  name : 'Show Time',
					  data : 'showTime',
					  visible : true,
					  render : tableRenderer.timeRenderer
				  },
				  {
					  name : 'Pay Money',
					  data : 'payMoney',
					  visible : true,
					  render : tableRenderer.payMoneyRenderer
				  },
				  {
					  name : 'Operation',
					  data : 'adId & status',
					  visible : true,
					  render : tableRenderer.operationRenderer
				  }]
			}
		};
	
	new topShop(topShopConfig);
});