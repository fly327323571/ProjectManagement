$(function(){

	
	var tableRenderer = {//数据渲染到页面上的函数
		shopOwnerRenderer : function(data){
			var html = "<a href='" + shopOwnerManageConfig.URL.DETAILS + "?userId=" + data.userId + "'>" +
					"<img src='" + data.portrait + "'/><span>" + data.userName + "</span></a>";
			return html;
		},
		idCardRenderer : function(data){
			return data;
		},
		statusRenderer : function(data){
			return data;
		},
		regTimeRenderer : function(data){
			return data;
		},
		operationRenderer : function(data){
			var userId = data.userId;
			var status = data.status;
			var html = "";
			var btn_2="";
			var btn_0 = '<button type="button" class="btn link-btn detail_btn" userId="'+userId+'">Detail</button></a>';
			switch(status){
				case 0://正常
					btn_2 = '<button type="button" class="btn link-btn blacklist_btn" userId="'+userId+'">Blacklist</button></a>';
					break;
				case 1://被拉黑
					btn_2 = '<button type="button" class="btn link-btn normal_btn" userId="'+userId+'">Recover</button></a>';
					break;
			}
			html = btn_0 +'\n'+btn_2;
			return html;
		}
	};
	
	
	var shopOwnerManageConfig = {
			URL : {//页面所有的URL配置
				LIST : "admin/shopOwnerManage/list",
				BLACKLIST : "admin/shopOwnerManage/addblacklist/{userId}.json",
				NORMAL : "admin/shopOwnerManage/normal/{userId}.json",
				DETAILS : "admin/shopOwnerManage/detail/{userId}"
			},
			tableConfig : {
				metadata : [
				  {
					  name : 'id',//页面上显示的信息
					  data : 'userId',//对应User.java 实体类的字段
					  visible : false,
					  render : null
				  },
				  {
					  name : 'shopOwner',
					  data : 'userId & portrait & userName',
					  visible : true,
					  render : tableRenderer.shopOwnerRenderer
				  },
				  {
					  name : 'Shop No',//页面上显示的信息
					  data : 'idCard',//对应User.java 实体类的字段
					  visible : true,
					  render : tableRenderer.idCardRenderer
				  },
				  {
					  name : 'Status',//页面上显示的信息
					  data : 'statusString',//对应User.java 实体类的字段
					  visible : true,
					  render : tableRenderer.statusRenderer
				  },
				  {
					  name : 'Register Time',
					  data : 'regTimeString',
					  visible : true,
					  render : tableRenderer.regTimeRenderer
				  },
				  {
					  name : 'Operation',
					  data : 'userId & status',
					  visible : true,
					  render : tableRenderer.operationRenderer
				  }]
			}
//			,
//			fieldTypeConfig : {
//				EQ : 1,
//				
//			}
		};

	
	
	
	new shopOwnerManage(shopOwnerManageConfig);
});