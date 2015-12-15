$(function(){

	
	var tableRenderer = {//数据渲染到页面上的函数
		storeRenderer : function(data){
			var html = "<a href='" + shopManageConfig.URL.DETAILS + "?storeId=" + data.storeId + "'>" +
					"<img src='" + data.logo + "'/><span>" + data.storeName + "</span></a>";
			return html;
		},
		categoryRenderer : function(data){
			return data;
		},
		statusRenderer : function(data){
			return data;
		},
		regTimeRenderer : function(data){
			return data;
		},
		operationRenderer : function(data){
			var storeId = data.storeId;
			var status = data.status;
			var html="";
			switch(status){
			case 1://申请中  applying
				var btn_0 = '<button type="button" class="btn link-btn detail" storeId="'+storeId+'">detail</button></a>';
				var btn_1 = '<button type="button" class="btn link-btn approve_btn" storeId="'+storeId+'">approve</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn reject_btn" storeId="'+storeId+'">reject</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			case 0://已开店 approved
				var btn_0 = '<button type="button" class="btn link-btn detail" storeId="'+storeId+'">detail</button></a>';
				var btn_1 ='<button type="button" class="btn link-btn" disabled="disabled" storeId="'+storeId+'">approved</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn blacklist_btn" storeId="'+storeId+'">blacklist</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			case 2://已拒绝 rejected
				var btn_0 = '<button type="button" class="btn link-btn detail" storeId="'+storeId+'">detail</button></a>';
				var btn_1 = '<button type="button" class="btn link-btn approve_btn" storeId="'+storeId+'">approve</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn" disabled="disabled" storeId="'+storeId+'">rejected</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			case 3://黑名单 blacklist
				var btn_0 = '<button type="button" class="btn link-btn" storeId="'+storeId+'">detail</button></a>';
				var btn_1 = '<button type="button" class="btn link-btn approve_btn" storeId="'+storeId+'">approve</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn" disabled="disabled" storeId="'+storeId+'">blacklist</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			}
			return html;
		}
	};
	
	
	var shopManageConfig = {
			URL : {//页面所有的URL配置
				LIST : "admin/shopRegMessage/list",
				REJECT : "admin/reject/{storeId}.json",
				APPROVE : "admin/approve/{storeId}.json",
				BLACKLIST : "admin/blacklist/{storeId}.json",
				DETAILS : "admin/detail/{storeId}.json"
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
					  name : 'Store',
					  data : 'storeId & logo & storeName',
					  visible : true,
					  render : tableRenderer.storeRenderer
				  },
				  {
					  name : 'Category',
					  data : 'category',
					  visible : true,
					  render : tableRenderer.categoryRenderer
				  },
				  {
					  name : 'Status',
					  data : 'statusStr',
					  visible : true,
					  render : tableRenderer.statusRenderer
				  },
				  {
					  name : 'Register Time',
					  data : 'registerTimeString',
					  visible : true,
					  render : tableRenderer.regTimeRenderer
				  },
				  {
					  name : 'operation',
					  data : 'storeId & status',
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

	
	
	
	new shopManage(shopManageConfig);
});