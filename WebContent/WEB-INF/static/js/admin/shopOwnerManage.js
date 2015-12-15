function shopOwnerManage(shopOwnerManageConfig){
	
	//--------------initialize------------------------//
	var _shopOwnerManageConfig = shopOwnerManageConfig;
	var table = new Table($("#shopOwnerList"),_shopOwnerManageConfig.tableConfig);
	var _shopOwnerList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'regTime';
	var isAscending = false;
	var status = 'All';
	
	//----------------绑定事件--------------------------//
	$("form button").bind("click",function(){
		location.href = _shopOwnerManageConfig.URL.DETAILS;//"shopOwnerOwner/shopOwnerDetail.html";
	});
	
	$("#orderByRegTime").bind("click",function(){
		var $a_reg_time = $(this);
		$a_reg_time.css('background-color','#eee');
		var $a_credit = $("#orderByCredit");
		$a_credit.css('background-color','rgba(0,0,0,0)');
		if(orderBy == 'regTime'){
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
		orderBy = 'regTime';
		loadData();
	});
	
	$("#status_choice .status_item").bind("click",function(){
		status = $(this).text();
		$("#status").text(status);
		loadData();
	});
	
	$("#search").bind("click",function(){
		loadData();
	});
	
	function bindBlacklistEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".blacklist_btn").bind("click",function(){
			var userId = $(this).attr("userId");
			baseAjax.doAjax(_shopOwnerManageConfig.URL.BLACKLIST.replace("{userId}", userId), null, function(rs){
				if(_shopOwnerList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
					curPageIdx--;
				}
				loadData();
			},function(rs){
				$("#dialog .modal-body p").html("System is busy,please try later!");
				$("#dialog .modal-footer #operate").hide();
				$("#dialog").modal();
			});
		});
	}	
	
	function bindNormalEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".normal_btn").bind("click",function(){
			var userId = $(this).attr("userId");
			baseAjax.doAjax(_shopOwnerManageConfig.URL.NORMAL.replace("{userId}", userId), null, function(rs){
				if(_shopOwnerList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
					curPageIdx--;
				}
				loadData();
			},function(rs){
				$("#dialog .modal-body p").html("System is busy,please try later!");
				$("#dialog .modal-footer #operate").hide();
				$("#dialog").modal();
			});
		});
	}	
	
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
		if(status!="All"){
			columnFilters.push({
				"name" : 'status_str',
				"filterType" : 'EQUAL',
				"value" : [status]
			})
		}
		var userName = $("#user_name").val();
		if(userName){
			columnFilters.push({
				"name" : 'userName',
				"filterType" : 'CONTAIN',
				"value" : [userName]
			});
		}
		return columnFilters;
	}
	
	function getQueryParam(){
		var orderFilters = getOrderFilters();
		//排序
		var columnFilters = getColumnFilters();
		//过滤条件
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
		baseAjax.doAjax(_shopOwnerManageConfig.URL.LIST, queryParam, function(rs){
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
			_shopOwnerList = page.data;
			table.loadData(_shopOwnerList);
			if(_shopOwnerList && _shopOwnerList.length!=0){
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
			bindBlacklistEvent();
			bindNormalEvent();
		}, function(data){
			
		});
	}
	
	loadData();
}