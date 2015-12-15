function backupDatabase(backupDatabaseConfig){
	
	var pageSize = 10;
	var curPageIdx = 1;
	var _config = backupDatabaseConfig;
	var table = new Table($("#backupHistory"),_config.tableConfig);
	
	$("#add").bind("click",function(){
		baseAjax.doAjax(_config.URL.BACKUP,null,function(data){
			alertSuccess("Success");
			loadData();
		},
		function(){
			alertFail("Fail");
		});
	});
	
	$("#backupHistory").on("click",".recover_btn",function(data){
		var fileName = $(this).attr("fileName");
		var dialog = {
			title : "Please confirm",
			renderer : function(){
				return "<p>Are you sure to recover with <span style='color:#FF5400'>"+fileName+"</span> ?</p>";
			},
			operation :[{
				id : "Yes",
				name :"Yes",
				css : "btn-primary",
				callback : function(){
					baseAjax.doAjax(_config.URL.RECOVER,dialog.fileName,function(data){
						alertSuccess("Database recovered successfully");
					},function(data){
						console.log(data);
						alertFail("Failed to recover data");
					});
				}
			},{
				id : "cancel",
				name : "Cancel",
				css : "btn-default",
				data_dismiss : true
			}]
		};
		dialog.fileName = fileName;
		$("#dialog").remove();
		$("body").append($("#dialogTmpl").html());
		$("#dialog").modal(dialog);
	});
	
	
	function organizeData(historyList){
		var list = [];
		$.each(historyList,function(i, fileName){
			var splits = fileName.split(".")[0].split("_");
			var date = splits[0];
			var time = splits[1].replace("-",":").replace("-",":");
			
			var item = {
				id : i+1,
				"fileName" : fileName,
				"time" : date + " " + time
			};
			list.push(item);
		});
		return list;
	}
	
	
	//-----------------常用函数------------------------//
		
	function getQueryParam(){		
		var queryParam = {
				"page" : {
					"pageIndex" : curPageIdx,
					"pageSize" : pageSize
				},
				"columnFilters" : [],
				"orderFilters" : []
		};
		return queryParam;
	}
	
	function pageOnChange(clickedPageIdx){
		curPageIdx = clickedPageIdx;
		loadData();
	}
	
	function loadData(){
		var queryParam = getQueryParam();
		baseAjax.doAjax(_config.URL.LIST, queryParam, function(data){
			var page = data.result;
			var list = organizeData(page.data);
			table.loadData(list);
			if(list && list.length!=0){
				$("#pagination").paginate({
					count 		: page.totalPageCount,//10,
					start 		: curPageIdx,
					display     : pageSize,
					border					: true,
					border_color			: '#ddd', 
					text_color  			: '#428bca',
					background_color    	: '#fff',	
					border_hover_color		: '#ddd',
					text_hover_color  		: '#428bca',
					background_hover_color	: '#eee', 
					images					: false,
					mouse					: 'press',
					onChange				: pageOnChange
				});
			}
			
		},function(data){
			console.log(data);
			alertFail("failed to fetch backup history");
		});
	}
	
	loadData();
}