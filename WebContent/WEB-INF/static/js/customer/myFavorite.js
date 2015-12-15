function MyFavorite(config){
	
	var _config = config;
	var orderBy = undefined;
	var isAscending = false;
	var pageSize = 4;
	//-------------常用函数--------------//
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
		
		return columnFilters;
	}
	
	function getQueryParam(clickedPageIdx){
		var orderFilters = getOrderFilters();
		
		var columnFilters = getColumnFilters();
		
		var queryParam = {
				"page" : {
					"pageIndex" : clickedPageIdx,
					"pageSize" : pageSize
				},
				"columnFilters" : columnFilters,
				"orderFilters" : orderFilters
		};
		return queryParam;
	}
	
	function pageOnChange(clickedPageIdx,url,renderData,li){
		loadData(clickedPageIdx,url,renderData,li);
	}
	
	function renderFavoriteProduct(list){
		$(".tab-content .f_product .favorite").children().remove();
		$.each(list, function(i, product){
			var url = _config.URL.PRODUCT_DETAIL.replace("{productId}",product.productId);
			var html = $("#favProductTmpl").html().replace("{detailUrl}",url)
				.replace("{productImage}",product.productImage)
				.replace("{productName}",product.productName)
				.replace("{shopName}",product.shopName);
			$(".tab-content .f_product .favorite").append(html);
		});
		
	}
	
	function renderFavoriteStore(list){
		$(".tab-content .f_store .favorite").children().remove();
		$.each(list,function(i,store){
			var url = _config.URL.STORE_HOMEPAGE.replace("{storeId}",store.storeId);
			var html = $("#favProductTmpl").html().replace("{detailUrl}",url)
				.replace("{productImage}",store.logo)
				.replace("{productName}",store.storeName)
				.replace("{shopName}",store.category);
			$(".tab-content .f_store .favorite").append(html);
		});
		
	}
	
	function loadData(clickedPageIdx,url,renderData,li){
		var queryParam = getQueryParam(clickedPageIdx);
		baseAjax.doAjax(url, queryParam, function(rs){
			
			var page = rs.result;
			var _list = page.data;
			renderData(_list);
			var _li = li;
			_li.unbind("click");
			_li.bind("click",function(){
				var that = $(this);
				if(that.hasClass("active")){
					return;
				}
				if(_list && _list.length>0){
					$("#pagination").paginate({
						count 		: page.totalPageCount,//10,
						start 		: clickedPageIdx,
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
						onChange				: function(idx){
							pageOnChange(idx,url,renderData,_li);
						}
					});
					
				}
			});
			
			if(_list && _list.length>0){
				$("#pagination").paginate({
					count 		: page.totalPageCount,//10,
					start 		: clickedPageIdx,
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
					onChange				: function(idx){
						pageOnChange(idx,url,renderData,_li);
					}
				});
				
			}
			
		}, function(data){
			console.log(data);
			alertFail(data.result.message);
		});
	}
	
	
	loadData(1,_config.URL.LOAD_FAVORITE_PRODUCT,renderFavoriteProduct,$(".tabbable .nav li:eq(0)"));
	loadData(1,_config.URL.LOAD_FAVORITE_STORE,renderFavoriteStore,$(".tabbable .nav li:eq(1)"));
}