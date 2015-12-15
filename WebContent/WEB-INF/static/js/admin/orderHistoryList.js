function orderHistoryList(orderHistoryListConfig){
	//--------------initialize------------------------//
	var _orderHistoryListConfig = orderHistoryListConfig;
	//var table = new Table($("#storeList"),_shopListConfig.tableConfig);
	var pageSize = 5;
	var curPageIdx = 1;
	
	
	var table = new Table($("#orderList"),_orderHistoryListConfig.tableConfig);
	var _orderList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy ='orderId';
	var isAscending = false;
	var category= 'status';
	var status='all';
	var name='';
	var time=null;
	
	
	//改变导航栏样式
	function changeCSS(obj){
		$("#viewByStatus li").attr("class","nav_option");
 		//改变被点击对象的样式
 		$(obj).attr("class","nav_option active");
	}
	//按照不同状态查看
	$("#viewByStatus li").bind("click",function(){
		changeCSS(this);
		var $a=$(this).find('a');
		status=$a.attr("id");
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
		if(category=='status'){
			if(status=='contain'){
				columnFilters.push({
					"name" : 'productName',
					"filterType" : 'CONTAIN',
					"value" :[name]
				});
			}
			else if(status=='all'){
				columnFilters.push({
					"name" : 'status',
					"filterType" : 'GREATER',
					"value" :[0]
				});
			}else{
				columnFilters.push({
					"name" : 'status',
					"filterType" : 'EQUAL',
					"value" :[status]
				});
			}
			
		}else{
			columnFilters.push({
				"name" : 'addtime',
				"filterType" : 'LESS',
				"value" :addTime
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
		baseAjax.doAjax(_orderHistoryListConfig.URL.LIST, queryParam, function(rs){
						
			var page = rs.result;
			_orderList = page.data;
			table.loadData(_orderList);
			
			if(_orderList && _orderList.length!=0){
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
			bindShip();
			
		}, function(data){
			
		});
	}
	loadData();
	
	//-----------------------------对话框----------------------------//
	var trackNumberDialog = {
		title : 'Please provide delivery information',
		renderer : function(){
			var html = $("#trackNumberTmpl").html();
			return html;
		},
		operation : [{
			id : 'ok',
			name : 'Submit',
			css : 'btn-primary',
			callback : function(){
				var _orderId = trackNumberDialog.orderId,
					trackNumber = $("#dialog [name='trackNumber']").val(),
					expressType = trackNumberDialog.expressType;
				
				if(!_orderId){
					alertFail("order id can't be empty!");
					return false;
				}
				if(!trackNumber){
					alertFail("track number can't be empty!");
					return false;
				}
				if(!expressType){
					alertFail("express type can't be empty!");
					return false;
				}
				
				var url = _orderHistoryListConfig.URL.SHIP.replace("{orderId}",_orderId) +
				    	"?trackNumber=" + trackNumber +
				    	"&expressType=" + expressType;
				
				baseAjax.doAjax(url,null,function(data){
					alertSuccess("operation success");
					loadData();
				},function(data){
					console.log(data.result);
					alertFail("modify failed");
				});
			}
		},{
			id : 'cancel',
			name : 'Cancel',
			css : 'btn-default',
			data_dismiss : true
		}],
		orderId : undefined,
		expressType : undefined
	};
	
	//----------------绑定事件--------------------------//
	//选择快递公司
	$("body").on("click",".modal-body .express [name]",function(obj){
		var $obj = $(obj.currentTarget);
		var $btn = $("#expressType");
		trackNumberDialog.expressType = $obj.text();
		$btn.html($obj.text()+' <span class="caret"></span>');
	});
	
	function bindShip(){
		$(".handleShip").bind("click",function(){
			var _orderId=$(this).attr("orderId");
			trackNumberDialog.orderId = _orderId;
			$("#dialog").remove();
			$("body").append($("#dialogTmpl").html());
			$("#dialog").modal(trackNumberDialog);
		});
		
		$(".refund").bind("click",function(){
			var _orderId=$(this).attr("orderId");
			var dialog = {
				title : "Please Confirm",
				renderer : function(){
					var html  = $("#refundDialogTmp").html().replace("{money}",dialog.money);
					return html;
				},
				operation : [{
					id : "refund",
					name : "Refund",
					css : "btn-primary",
					callback : function(){
						baseAjax.doAjax(_orderHistoryListConfig.URL.REFUND.replace("{orderId}",_orderId),null,function(data){
							alertSuccess("operation success");
							loadData();
						},function(data){
							alertFail("modify failed");
						});
					}
				},{
					id : "cancel",
					name : "Cancel",
					css : "btn-default",
					data_dismiss:true
				}]
			};
			dialog.money = $(this).parents("tr").children(".t_totalPrice").text();
			$("#dialog").remove();
			$("body").append($("#dialogTmpl").html());
			$("#dialog").modal(dialog);
		});
		$(".reject_refund").bind("click",function(){
			var _orderId=$(this).attr("orderId");
			var dialog = {
				title : "Please provide your reason for reject",
				renderer : function(){
					return $("#rejectDialogTmpl").html();
				},
				operation : [{
					id : "reject",
					name : "Reject",
					css : "btn-primary",
					callback : function(){
						var reason = $("#dialog").children().find("input[name='reason']").val();
						baseAjax.doAjax(_orderHistoryListConfig.URL.REJECT_REFUND.replace("{orderId}",_orderId),reason,function(data){
							alertSuccess("operation success");
							loadData();
						},function(data){
							alertFail("modify failed");
						});
					}
				},{
					id : 'cancel',
					name : "Cancel",
					css : "btn-default",
					data_dismiss : true
				}]
			};
			$("#dialog").remove();
			$("body").append($("#dialogTmpl").html());
			$("#dialog").modal(dialog);
		});
	}
	
	$("#category_choice .category_item").bind("click",function(){
		var request = $(this).text();
		var today = new Date();  
		 
		if(request=='one month'){
			time=new Date(today.getTime()-31 * 24 * 3600 * 1000);
		}else{
			time=new Date(today.getTime()-3*31 * 24 * 3600 * 1000);
		}
		$("#category").text(category);
		loadData();
	});
	
	$("#search").bind("click",function(){
		status='contain';
		name=$("#searchContent").val();
		loadData();
	});
	
	$("#all").bind("click",function(){
		status = 'all';
		loadData();
	});
	
	$("#shipped").bind("click",function(){
		status=6;
		loadData();
	});
	
	$("#unshipped").bind("click",function(){
		status=2;
		loadData();
	});
	
	$("#done").bind("click",function(){
		status=9;
		loadData();
	});
	
	$("body").on("click",".contact",function(){
		var userId = $(this).attr("userId");
		var shopManageHomepage =  window.parent.ShopManageHomepage;
		if(shopManageHomepage){
			shopManageHomepage.contact(userId);
		}
	});
	
	//加载完毕后如果本页面是放在iframe里头的,父页面的要选中manage products导航选项
	function selectManageProductsOptionMenu(){
		var shopManageHomepage =  window.parent.ShopManageHomepage;
		if(shopManageHomepage){
			shopManageHomepage.selectOption('order_history');
		}
	}
	
	selectManageProductsOptionMenu();
}