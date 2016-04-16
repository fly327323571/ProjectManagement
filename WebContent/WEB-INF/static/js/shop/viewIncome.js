function ViewIncome(orderHistoryListConfig){
	//--------------initialize------------------------//
	var _orderHistoryListConfig = orderHistoryListConfig;
	var pageSize = 5;
	var curPageIdx = 1;
	
	
	var table = new Table($("#orderList"),_orderHistoryListConfig.tableConfig);
	var _orderList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy ='incomeId';
	var isAscending = false;
	
	var time= getStartOfToday();//默认是当天的income
	
	
	function getStartOfToday(){
		var now = new Date();
		now.setHours(0, 0, 0, 0);
		return now.toLocaleString();
	}
	
	function getStartOfThisWeek(){
		var date = new Date();
		var day = (date.getDay() + 7 -1) % 7;
		var dateDay = date.getDate();
		var startDateDay = dateDay - day + 1;
		date.setDate(startDateDay);
		date.setHours(0, 0, 0, 0);
		return date.toLocaleString();
	}
	
	function getStartOfThisMonth(){
		var date = new Date();
		date.setDate(1);
		date.setHours(0, 0, 0, 0);
		return date.toLocaleString();
	}
	
	function getStartOfThisYear(){
		var date = new Date();
		date.setMonth(0);
		date.setDate(1);
		date.setHours(0, 0, 0, 0);
		return date.toLocaleString();
	}
	//改变导航栏样式
	function changeCSS(obj){
		$("#viewByTime li").attr("class","nav_option");
 		//改变被点击对象的样式
 		$(obj).attr("class","nav_option active");
	}
	//按照不同状态查看
	$("#viewByTime li").bind("click",function(){
		changeCSS(this);
		var $a=$(this).find('a');
		//$a.preventDefault();
		var flag=$a.attr("id");
		
		if(flag=='day'){
			time=getStartOfToday();
		}else if(flag=='week'){
			time=getStartOfThisWeek();
		}else if(flag=='month'){
			time=getStartOfThisMonth();
		}else if(flag=='year'){
			time=getStartOfThisYear();
		}else{
			//???????????????????????
		}
		
		loadData();
	});
	
	//-----------------常用函数------------------------//
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
		
			columnFilters.push({
				"name" : 'time',
				"filterType" : 'GREATER',
				"value" :[time]
			});
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
		baseAjax.doAjax(_orderHistoryListConfig.URL.LIST, queryParam, function(rs){
						
			var page = rs.result;
			_orderList = page;
			table.loadData(_orderList);
			if(_orderList && _orderList.length!=0){
				$("#pagination").paginate({
					count 		: Math.ceil(_orderList.length/5),//10,
					start 		: curPageIdx,
					display     : 5,
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

}