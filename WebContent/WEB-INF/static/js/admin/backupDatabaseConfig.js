$(function(){

	
	var tableRenderer = {//数据渲染到页面上的函数
			fileNameRenderer : function(fileName){
				var href = backupDatabaseConfig.URL.DOWNLOAD.replace("{fileName}",fileName);
				var a = '<a target="_blank" href="'+href+'">'+fileName+'</a>';
				return a;
			},
			operationRenderer : function(fileName){
				var html = '<button type="button" class="btn link-btn recover_btn" fileName="'+fileName+'">Recover</button></a>';
				return html;
			}
	};
	var backupDatabaseConfig = {
			URL : {//页面所有的URL配置
				LIST : "admin/backupDb/listHistory.json",
				BACKUP:"admin/backupDb.json",
				DOWNLOAD : "service/download?sql={fileName}",
				RECOVER : "admin/loadDb.json",
			},
			tableConfig : {
				metadata : [
				  {
					  name : 'Sequence number',//页面上显示的信息
					  data : 'id',//对应User.java 实体类的字段
					  visible : true,
					  render : null
				  },{
					  name : "File name",
					  data : "fileName",
					  visible : true,
					  render : tableRenderer.fileNameRenderer
				  },{
					  name : "Backup time",
					  data : "time",
					  visible : true,
					  render : null
				  },{
					  name : "",
					  data : "fileName",
					  visible : true,
					  render : tableRenderer.operationRenderer
				  }]
			}
		};
	new backupDatabase(backupDatabaseConfig);
});