function shopManage(shopManageConfig){
	
	//--------------initialize------------------------//
	var _shopManageConfig = shopManageConfig;
	var table = new Table($("#storeList"),_shopManageConfig.tableConfig);
	var _storeList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'registerTime';
	var isAscending = false;
	var category = 'All';
	var status = 'All';
	
	//----------------绑定事件--------------------------//
	$("form button").bind("click",function(){
		location.href = _shopManageConfig.URL.DETAILS;//"shopOwner/shopDetail.html";
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
	
		
	$("#category_choice .category_item").bind("click",function(){
		category = $(this).text();
		$("#category").text(category);
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
	
	function bindApproveEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".approve_btn").bind("click",function(){
			var storeId = $(this).attr("storeid");
			baseAjax.doAjax(_shopManageConfig.URL.APPROVE.replace("{storeId}", storeId), null, function(rs){
				if(_storeList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
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
	
	function bindRejectEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".reject_btn").bind("click",function(){
			var storeId = $(this).attr("storeid");
			baseAjax.doAjax(_shopManageConfig.URL.REJECT.replace("{storeId}", storeId), null, function(rs){
				if(_storeList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
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
	
	function bindBlackListEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".blacklist_btn").bind("click",function(){
			var storeId = $(this).attr("storeid");
			baseAjax.doAjax(_shopManageConfig.URL.BLACKLIST.replace("{storeId}", storeId), null, function(rs){
				if(_storeList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
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
		if(status!="All")
			{
				columnFilters.push({
					"name" : 'status_str',
					"filterType" : 'EQUAL',
					"value" : [status]
				})
			}
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
		baseAjax.doAjax(_shopManageConfig.URL.LIST, queryParam, function(rs){
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
			bindApproveEvent();
			bindRejectEvent();
			bindBlackListEvent();
		}, function(data){
			
		});
	}
	//-----------------------------对话框---------------------------//
	//错误提示的对话框
	var warnDialog = {
			title : 'Operation Failed',
			renderer : function(){
				return '<p>Sorry, this operation failed.</p>';
			},
			operation : [{
				id: 'ok',
				name : 'OK',
				css : 'btn-warning',
				data_dismiss : true
			}]
		};
	
	//删除物品的对话框配置
	var detailDialog = {
		title : 'Shop details',//对话框的标题
		renderer : function(){//渲染函数 返回一段html
			var html = $("#detailTmpl").html();
			return html;
		},
		operation : [{
			id : 'ok',
			name : 'OK',
			css : 'btn-primary dialog-ok',
			data_dismiss : true
		}],
		data : {}
	};
	
	$("body").on("click",".t_operation .detail",function(){
		
		var storeId = $(this).attr('storeid');
		var	url = _shopManageConfig.URL.DETAILS.replace("{storeId}",storeId);
		baseAjax.doAjax(url, null, function(data){
			detailDialog.data = data.result;
			$("#dialog").modal(detailDialog);
		}, function(data){
			
		});
		
	});
	
	$('body').on("change",".modal-body [name='portrait']",function(obj, data){
		$(this).attr('src',data);
	});
	
	$('body').on("change",".modal-body [name='logo']",function(obj, data){
		$(this).attr('src',data);
	});
	
	loadData();
}