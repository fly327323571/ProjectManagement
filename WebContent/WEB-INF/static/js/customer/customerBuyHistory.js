function customerBuyHistory(orderListConfig) {

	// --------------initialize------------------------//
	var _orderListConfig = orderListConfig;
	var table = new Table($("#orderList"), _orderListConfig.tableConfig);
	var _orderList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'orderId';
	var statusId = 'all';

	// ---------------导航绑定事件--------------------------//
	function changeCSS(obj) {
		$("#viewByStatus li").attr("class", "nav_option");
		// 改变被点击对象的样式
		$(obj).attr("class", "nav_option active");
	}
	function changeStatus(statusId) {
		var status = 0;
		switch (statusId) {
		case 'unpaid':
			status = 1;
			break;
		case 'unshipped':
			status = 2;
			break;
		case 'refunding':
			status = 3;
			break;
		case 'refund_rejected':
			status = 4;
			break;
		case 'refunded':
			status = 5;
			break;
		case 'shipped':
			status = 6;
			break;
		case 'received':
			status = 7;
			break;
		case 'commented':
			status = 8;
			break;
		case 'done':
			status = 9;
			break;
		default:
			;
		}
		return status;
	}
	$("#viewByStatus li").bind("click", function() {
		changeCSS(this);
		var $a = $(this).find('a');
		statusId = $a.attr("id");
		loadData();
	});
	$("#orderSearch").bind("click", function() {
		var keyword = $("#orderKeyWords").val();
		if (!keyword) {
			alert('please input the keyword');
			return;
		}
		loadData();
	});

	//------------------------------dialog-----------------------//
	var confirmDialog = {
		title : 'Be cautious',
		renderer : function(){
			var html = $("#confirmTmpl").html().replace("{orderId}",confirmDialog.orderId);
			return html;
		},
		operation : [{
			id : 'confirm',
			name : 'Confirm',
			css : 'btn-primary',
			callback : function(){
				var url = _orderListConfig.URL.CONFIRM.replace("{orderId}",confirmDialog.orderId);
				baseAjax.doAjax(url, null, function(data){
					alertSuccess("order has been signed on receipt successfully");
					loadData();
				}, function(data){
					console.log(data);
					alertFail(data.result.message);
				});
			}
		},{
			id : 'cancel',
			name : 'Cancel',
			css : 'btn-default',
			data_dismiss : true
		}],
		orderId : undefined
	};
	
	
	// ----------------按钮绑定事件--------------------------//
	
	$("#orderList").on("click",".confirm_btn",function(){
		var that = $(this);
		confirmDialog.orderId = that.attr('orderid');
		$("#dialog").remove();
		$("body").append($("#dialogTmpl").html());
		$("#dialog").modal(confirmDialog);
	});
	
    $("body").on("click",".conceal_btn",function() {
	        var _orderId = $(this).attr("orderId");
	        var dialog = {
	        	title : "Please confirm",
	        	renderer : function(){
	        		return "<p>Are you sure to cancel the order <span style='color:#FF5400'>" + _orderId + "</span>?</p>";
	        	},
	        	operation : [{
	        		id : "Yes",
	        		name : "Yes",
	        		css : "btn-primary",
	        		callback : function(){
	    	            baseAjax.doAjax(_orderListConfig.URL.CONCEAL.replace("{orderId}",_orderId), null,
	    	            function(rs) {
	    	                if (_orderList.length == 1 && curPageIdx > 1) { // 如果当前页面只剩下一项，删掉后就必须跳回前一个页面
	    	                    curPageIdx--;
	    	                }
	    	                loadData();
	    	            },
	    	            function(rs) {
	    	            	console.log(rs.data);
		    	            alertFail("System seemed not to performed well.");
	    	            });
	        		}
	        	},{
	        		id : "cancel",
	        		name : "Cancel",
	        		css : "btn-default",
	        		data_dismiss : true
	        	}]
	        };
	        $("#dialog").remove();
	        $("body").append($("#dialogTmpl").html());
	        $("#dialog").modal(dialog);
	 });
	
		
		
	// -----------------常用函数------------------------//
	function getOrderFilters() {
		var orderFilters = [];
		if (orderBy) {
			orderFilters[0] = {
				"name" : orderBy,
				"isAscending" : false
			};
		}
		return orderFilters;
	}

	function getColumnFilters() {
		var columnFilters = [];
		var status = 0;
		if (statusId != 'all') {
			status = changeStatus(statusId);
			columnFilters.push({
				"name" : 'status',
				"filterType" : 'EQUAL',
				"value" : [ status ]
			});
		}
		var orderKeyWords = $("#orderKeyWords").val();
		if (orderKeyWords) {
			columnFilters.push({
				"name" : 'orderKeyWords',
				"filterType" : 'CONTAIN',
				"value" : [ orderKeyWords ]
			});
		}
		return columnFilters;
	}

	function getQueryParam() {
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

	function pageOnChange(clickedPageIdx) {
		curPageIdx = clickedPageIdx;
		loadData();
	}

	function loadData() {
		var queryParam = getQueryParam();

		baseAjax.doAjax(_orderListConfig.URL.LIST, queryParam, function(rs) {
			var page = rs.result;
			_orderList = page.data;
			table.loadData(_orderList);
			if (_orderList && _orderList.length != 0) {
				$("#pagination").paginate({
					count : page.totalPageCount,// 10,
					start : curPageIdx,
					display : 5,
					border : true,
					border_color : '#ddd',
					text_color : '#428bca',
					background_color : '#fff',
					border_hover_color : '#ddd',
					text_hover_color : '#428bca',
					background_hover_color : '#eee',
					images : false,
					mouse : 'press',
					onChange : pageOnChange
				});
			}

		}, function(data) {
			alertFail(data.result.message);
		});
	}	

	loadData();
	
	//加载完毕后如果本页面是放在iframe里头的,父页面的要选中manage products导航选项
	function selectDashboardOptionMenu(){
		var customerHomepage =  window.parent.CustomerHomepage;
		if(customerHomepage){
			customerHomepage.selectOption('order_history');
		}
	}
	selectDashboardOptionMenu();
}