function ShopList(shopListConfig){
	
	//--------------initialize------------------------//
	var _shopListConfig = shopListConfig;
	var table = new Table($("#storeList"),_shopListConfig.tableConfig);
	var _storeList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'registerTime';
	var isAscending = false;
	var category = 'All';
	
	//----------------绑定事件--------------------------//
	$("form button").bind("click",function(){
		location.href = _shopListConfig.URL.DETAILS;//"shopOwner/shopDetail.html";
	});
	
	$("#orderByRegTime").bind("click",function(){
		var $a_reg_time = $(this);
		$a_reg_time.css('background-color','#eee');
		var $a_credit = $("#orderByCredit");
		$a_credit.css('background-color','rgba(0,0,0,0)');
		if(orderBy == 'registerTime'){
			isAscending = !isAscending;
		}else{
			isAscending = false;
			$("#reg_time_caret").show();
			$("#credit_caret").hide();
		}
		if(isAscending){
			$a_reg_time.attr('class','dropup');
		}else{
			$a_reg_time.attr('class','');
		}
		orderBy = 'registerTime';
		loadData();
	});
	
	$("#orderByCredit").bind("click",function(){
		var $a_credit = $(this);
		$a_credit.css('background-color','#eee');
		var $a_reg_time = $("#orderByRegTime");
		$a_reg_time.css('background-color','rgba(0,0,0,0)');
		if(orderBy == 'creditValue'){
			isAscending = !isAscending;
		}else{
			isAscending = false;
			$("#reg_time_caret").hide();
			$("#credit_caret").show();
		}
		if(isAscending){
			$a_credit.attr('class','dropup');
		}else{
			$a_credit.attr('class','');
		}
		orderBy = 'creditValue';
		loadData();
	});
		
	$("#category_choice .category_item").bind("click",function(){
		category = $(this).text();
		$("#category").text(category);
		loadData();
	});
	
	$("#search").bind("click",function(){
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
		if(category!='All'){
			columnFilters.push({
				"name" : 'category',
				"filterType" : 'EQUAL',
				"value" : [category]
			});
		}
		var storeName = $("#store_name").val();
		if(storeName){
			columnFilters.push({
				"name" : 'storeName',
				"filterType" : 'CONTAIN',
				"value" : [storeName]
			});
		}
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
		baseAjax.doAjax(_shopListConfig.URL.LIST, queryParam, function(rs){
			/*data 的格式如下  {
			 *  code : 0 , 
			 *  result : [{
			 *  	storeId : '',
			 *  	storeName : '',
			 *      userId : '',
			 *      type : '',
			 *      address : '',
			 *      degree : '',
			 *      creditValue : '',
			 *      status : '',
			 *      sortOrder : '',
			 *      logo : '',
			 *      description : '',
			 *      image1 : '',
			 *      image2 : '',
			 *      image3 : '',
			 *      imQq : '',
			 *      imWw : ''
			 *  }]
			 *}
			 */
			var page = rs.result;
			_storeList = page.data;
			table.loadData(_storeList);
			if(_storeList && _storeList.length!=0){
				$("#pagination").paginate({
					count 		: page.totalPageCount,//10,
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