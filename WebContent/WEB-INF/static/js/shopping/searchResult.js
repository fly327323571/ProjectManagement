function SearchResult(config){
	var _config=config;
	
	var _productList = null;
	var pageSize = 12;
	var curPageIdx = 1;
	var orderBy = 'addTime';
	var isAscending = false;
	var searchType ='commodityName';
	
	
	//改变导航栏样式
	function changeCSS(obj){
		$(".content .result").css("background","white");
 		$(".content .result").css("color","#FF5400");
 		//改变被点击对象的样式
 		obj.css("background","#FF5400");
 		obj.css("color","white");
	}
	
	
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
		
		var keyword = $("#productType").val();
		if(keyword){
			columnFilters[0]={
				"name" : searchType,
				"filterType" : searchType=='commodityName'?'CONTAIN':'EQUAL',
				"value" : [keyword]
			};
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
	
	function renderData(data){
		var $ul =$(".show");
		$ul.children().remove();
		$.each(data,function(i, item){
			var $li = $("<li></li>");
			var $a=$("<a></a>");
				$a.attr("href","product/productDetail/"+item.commodityNo);
			var $img=$("<img width='160px' weight='160px'/>");
				$img.attr("src",item.commodityImg);
			var $div=$("<div class='describe'></div>");
			var $commodityName=$("<span class='name'></span>");
				$commodityName.html(item.commodityName);
			var $storeBelong=$("<span class='pdt-shop'></span>");
				$storeBelong.html('');
			var $commodityPrice=$("<span class='price'></span>");
				$commodityPrice.html('Price:'+item.commodityPrice);
			
			$div.append($commodityName).append($storeBelong).append($commodityPrice);
			$a.append($img).append($div);
			$li.append($a);
			$ul.append($li);
		});
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
			renderData(_productList);
			if(_productList && _productList.length>0){
				$("#pagination").paginate({
					count 		: rs.totalPageCount,//10,
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
			console.log(data);
			alertFail(data.result.message);
		});
	}
	//loadData();
	
	//easy-search 框
 	$(window).scroll(function(){
 		var distance=$("body").scrollTop();
 		if(distance>60){
 			$("header").addClass("easy");
 		}else{
 			if($("header").hasClass("easy")){
 				$("header").removeClass("easy");
 			}
 		}
 	});
 	
 	//按照不同规则排序
	$(".content .result").bind("click",function(){
		changeCSS($(this));
		orderBy=$(this).attr("value");
		isAscending=!isAscending;
		loadData();
	});
	
	$("#search").bind("click",function(){
		loadData();
	});
	
 	//
	function pageOnChange(clickedPageIdx) {
		curPageIdx = clickedPageIdx;
		loadData();
	}
	
	function showSearchResult(){
		var list = page;
		var ul = $(".content .show");
		$.each(list,function(i, item){
			var html = $("#searchResultTmpl").html()
				.replace("{commodityNo}",item.commodityNo)
				.replace("{commodityImg}",item.commodityImg)
				.replace("{commodityName}",item.commodityName)
				.replace("{commodityPrice}",item.commodityPrice);
			ul.append(html);
		});
		if(list && list.length!=0){
			$("#pagination").paginate({
				count 		: pageCount,
				start 		: curPageIdx,
				display     : 12,
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
	}
	
	showSearchResult();
}