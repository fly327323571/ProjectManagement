function ProductList(config){
	
	var _config = config;
	
	var table = new Table($("#productList"),_config.tableConfig);	
	var _productList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'addTime';
	var isAscending = false;
	var searchType = 'productName';
	
	
	
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
		
		var keyword = $("#keyword").val();
		if(keyword){
			columnFilters.push({
				"name" : searchType,
				"filterType" : searchType=='productName'?'CONTAIN':'EQUAL',
				"value" : [keyword]
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
		baseAjax.doAjax(_config.URL.LIST, queryParam, function(rs){
			/*data = {
			 *  code : 0 , 
			 *  result : [{
			 *  	storeId : ''
			 *  }]
			 *}
			 */
			var page = rs.result;
			_productList = page;
			table.loadData(_productList);
			if(_productList && _productList.length>0){
				$("#pagination").paginate({
					count 		: Math.ceil(page.length/5),//10,
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
				bindDeleteEvent();
			}
			
		}, function(data){
			
		});
	}
	
	//加载完毕后如果本页面是放在iframe里头的,父页面的要选中manage products导航选项
	function selectManageProductsOptionMenu(){
		var shopManageHomepage =  window.parent.ShopManageHomepage;
		if(shopManageHomepage){
			shopManageHomepage.selectOption(_config.parentOptionNavigation);
		}
	}

	//----------------------------事件绑定-----------------------//
	$("#search_by li").bind("click",function(){
		var searchBy = $(this).text();
		$("#_search_by").text(searchBy);
		$("#keyword").attr('placeHolder',searchBy);
		if(searchBy){
			if(searchBy == 'product name'){
				searchType = 'productName';
			}else{
				searchType = 'productId';
			}
		}
	});
	
	//搜索
	$("#search").bind("click",function(){
		var keyword = $("#keyword").val();
		if(!keyword){
			alert('please input the keyword');
			return;
		}
		loadData();
	});
	

	function bindDeleteEvent(){//推迟绑定事件到加载完成之后 ajax获取到数据并且完成table渲染时绑定
		$(".delete").bind("click",function(){
			
			var tr=$(this).parent().parent();
			var tr_prev=tr.prev();
			var _productId=$(this).attr("productId");
			baseAjax.doAjax(_config.URL.DELETE_CHECK+"?productId="+_productId, null, function(data){
				
				var count = data.result;
				var dialog = {
					title : "Please confirm",
					renderer : function(){
						if(count>0){
							var html = $("#warnDeleteDialogTmpl").html().replace("{count}",count);
							return html;
						}
						return "<p>Are you sure to delete this product?</p>";
					},
					operation : [{
						id : "delete",
						name : "Delete",
						css : "btn-primary",
						callback : function(){
							$("#dialog").modal('hide');
							//console.log(_config.URL.DELETE);
							baseAjax.doAjax(_config.URL.DELETE + "?productId="+_productId, null, function(rs){
								if(_productList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
									curPageIdx--;
								}
								loadData();
								alertSuccess("deleted successfully!");
							},function(rs){
								alertFail("System is busy,please try later!");
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
				
			}, function(data){
				alertFail("can't connect to Internet");
			});
			
			
		});
	}
	
	loadData();
	selectManageProductsOptionMenu();
}