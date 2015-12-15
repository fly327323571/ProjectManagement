$(function(){

	
	var tableRenderer = {//数据渲染到页面上的函数
		customerRenderer : function(data){
			var html = "<a href='" + customerManageConfig.URL.DETAILS + "?userId=" + data.userId + "'>" +
					"<img src='" + data.portrait + "'/><span>" + data.userName + "</span></a>";
			return html;
		},
		emailRender : function(data){
			return data;
		},
		phonenumberRenderer : function(data){
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
			var html="";
			switch(status){
			case 0:	//NORMAL
				var btn_0 = '<button type="button" class="btn link-btn detail_btn" userId="'+userId+'">Detail</button></a>';
				var btn_1 = '<button type="button" class="btn link-btn blacklist_btn"  userId="'+userId+'">Blacklist</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn" disabled="disabled" userId="'+userId+'">Normal</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			case 1:	//BLACKLIST
				var btn_0 = '<button type="button" class="btn link-btn detail_btn" userId="'+userId+'">Detail</button></a>';
				var btn_1 = '<button type="button" class="btn link-btn" disabled="disabled" userId="'+userId+'">Blacklist</button></a>';
				var btn_2 = '<button type="button" class="btn link-btn normal_btn" userId="'+userId+'">Normal</button></a>';
				html= btn_0+'\n'+btn_1 + '\n' + btn_2;
				break;
			}
			return html;
		}
	};
	
	
	var customerManageConfig = {
			URL : {//页面所有的URL配置
				LIST : "admin/customerManage/list",
				BLACKLIST : "admin/customerManage/addblacklist/{userId}.json",
				NORMAL : "admin/customerManage/normal/{userId}.json",
				DETAILS : "admin/customerManage/detail/{userId}"
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
					  name : 'Customer',
					  data : 'userId & portrait & userName',
					  visible : true,
					  render : tableRenderer.customerRenderer
				  },
				  {
					  name : 'Email',
					  data : 'email',
					  visible : true,
					  render : tableRenderer.emailRender
				  },
				  {
					  name : 'Phone Number',//页面上显示的信息
					  data : 'phoneNumber',//对应User.java 实体类的字段
					  visible : true,
					  render : tableRenderer.phonenumberRenderer
				  },
				  {
					  name : 'Status',//页面上显示的信息
					  data : 'statusString',
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

	
	
	
	new customerManage(customerManageConfig);
});