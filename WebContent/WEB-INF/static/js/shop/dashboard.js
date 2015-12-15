function Dashboard(config){
	
	var _config = config;
	
	//-----------------------绑定事件------------------------//
	function getFavoriteCount(){
		baseAjax.doAjax(_config.URL.COUNT_FAVORITE, null, function(data){
			$("#favorite_count").text(data.result);
		}, function(data){
			
		});
	}
	
	function getNewOrderCount(){
		baseAjax.doAjax(_config.URL.COUNT_NEW_ORDER, null, function(data){
			$("#newOrderCount").text(data.result);
		}, function(data){
			
		});
	}
	
	function getIncome(){
		baseAjax.doAjax(_config.URL.COUNT_INCOME, null, function(data){
			$("#incomeCount").text(data.result.toFixed(2));
		}, function(data){
			
		});
	}
	
	function getNewComments(){
		baseAjax.doAjax(_config.URL.COUNT_NEW_COMMENT, null, function(data){
			$("#newComments").text(data.result);
		}, function(data){
			
		});
	}
	//查看收入详情
	$("#viewIncome").bind("click",function(){
		
		var dialog = {
			title : "View income details",
			renderer : function(){
				var html = $("#incomeDetailTmpl").html();
				return html;
			},
			operation : [{
				id : "ok",
				name : "OK",
				css : "btn-primary",
				data_dismiss : true
			}],
			onShow : function(){
				ViewIncomeComponent();
			}
		};
		
		$("#dialog").remove();
		var html = $("#dialogTmpl").html();
		$("body").append(html);
		$("#dialog").modal(dialog);
	});
	
	$("#viewOrders").bind("click",function(){
		window.location.href = _config.URL.ORDER_HISTORY;
	});
	
	//查看收入详情
	$("#viewComment").bind("click",function(){
		
		var dialog = {
			title : "View comment details",
			renderer : function(){
				var html = $("#commentDetailTmpl").html();
				return html;
			},
			operation : [{
				id : "ok",
				name : "OK",
				css : "btn-primary",
				data_dismiss : true
			}],
			onShow : function(){
				ViewCommentComponent();
			}
		};
		
		$("#dialog").remove();
		var html = $("#dialogTmpl").html();
		$("body").append(html);
		$("#dialog").modal(dialog);
	});
	
	
	//加载完毕后如果本页面是放在iframe里头的,父页面的要选中manage products导航选项
	function selectDashboardOptionMenu(){
		var shopManageHomepage =  window.parent.ShopManageHomepage;
		if(shopManageHomepage){
			shopManageHomepage.selectOption('dashboard');
		}
	}
	
	selectDashboardOptionMenu();
	getFavoriteCount();
	getNewOrderCount();
	getIncome();
	getNewComments();
}