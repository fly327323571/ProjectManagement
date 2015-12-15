function ViewComment(config){
	
	var _config = config;
	
	var pageSize = 5;
	var curPageIdx = 1;
	
	var table = new Table($("#commentList"),_config.tableConfig);
	var _list = null;
	var orderBy ='productsOfOrderId';
	var isAscending = false;
	
	function getOrderFilters(){
		var orderFilters = [];
		if(orderBy){
			orderFilters[0] = {
				"name" :	orderBy,
				"isAscending" : isAscending
			};
		}
		return orderFilters;
	}
	
	function getColumnFilters(){
		var columnFilters = [];
		
//			columnFilters.push({
//				"name" : 'time',
//				"filterType" : 'GREATER',
//				"value" :[time]
//			});
		return columnFilters;
	}
	
	function getQueryParam(){
		var orderFilters = getOrderFilters();
		
		var columnFilters = getColumnFilters();
		
		var queryParam = {
				"page" : {
					"pageIndex" : curPageIdx,
					"pageSize" : pageSize
				},
				"columnFilters" : columnFilters,
				"orderFilters" : orderFilters
		};
		return queryParam;
	}
	
	function pageOnChange(clickedPageIdx){
		curPageIdx = clickedPageIdx;
		loadData();
	}
	

	function loadData(){
		var queryParam = getQueryParam();
		baseAjax.doAjax(_config.URL.LIST, queryParam, function(rs){
			
			var page = rs.result;
			_list = page.data;
			table.loadData(_list);
			if(_list && _list.length!=0){
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
			
			
		}, function(data){
			
		});
	}
	loadData();
	
	$("#commentList").on("click",".t_Reply .btn",function(){
		var textarea = $(this).prev();
		var orderId = $(this).attr("orderId");
		baseAjax.doAjax(_config.URL.REPLY.replace("{orderId}",orderId), textarea.val(),function(data){
			alertSuccess("Replied successfully!");
			textarea.attr("disabled",true);
			$("#newComments").text($("#newComments").text()-1);
			
		},function(data){
			console.log(data);
			alertFail("server internal error,please contact with admin");
		});
	});
}