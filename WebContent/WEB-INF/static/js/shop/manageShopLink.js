function ManageShopLink(manageShopLinkConfig){
	
	//--------------initialize------------------------//
	var _manageShopLinkConfig = manageShopLinkConfig;
	var table = new Table($("#shopLink"),_manageShopLinkConfig.tableConfig);
	var _shopLink = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'registerTime';
	var isAscending = false;
	var category = 'All';
	
	//----------------绑定事件--------------------------//
	$("body").on("click","input[type='checkbox']",function(){
		$(this).attr("checked",!$(this).attr("checked"));
	});
	
	
	$(".other-shop button").bind("click",function(){
		
		var checkedItem = $("input[checked='checked']");
		var info = [];
		$.each(checkedItem,function(i,item){
			 info.push("{\"shopIcon"+"\":"+"\""+$(item).attr('shopIcon')+"\","+"\"shopNo"+"\":"+$(item).attr('shopNo')+","+
						"\"linkTo"+"\":"+$(item).val()+"}");
		});
		var str=info.join(',');
		//需要跟数据库补充完整的字段
		var sub={
				info:str
		}
		
		baseAjax.doAjax(_manageShopLinkConfig.URL.SUBMIT, sub, function(rs){
			alertSuccess("Operation is successful",function(){
				window.location.href = _manageShopLinkConfig.URL.DETAILS;
			});
		},function(rs){
			alertFail("System is busy, please try again later");
		});
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
		/*if(category!='All'){
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
		}*/
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
	
	function removeThisStore(_shopLink){
		var _newShopLink = [];
		$.each(_shopLink,function(i,shop){
			if(shop.storeId!=storeId){
				_newShopLink.push(shop);
			}
		});
		return _newShopLink;
	}
	
	function loadData(){
		var queryParam = getQueryParam();
		baseAjax.doAjax(_manageShopLinkConfig.URL.LIST, queryParam, function(rs){
			var page = rs.result;
			_shopLink = page;
			_shopLink = removeThisStore(_shopLink);
			table.loadData(_shopLink);
			if(_shopLink && _shopLink.length!=0){
				$("#pagination").paginate({
					count 		: Math.ceil(_shopLink.length/5),//10,
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
			selectLinkedShops();
		}, function(data){
			
		});
	}
	
	loadData();
	
	function selectLinkedShops(){
		baseAjax.doAjax(_manageShopLinkConfig.URL.SHOP_LINKS, null, function(data){
			var links = data.result;
			$.each(links,function(i, link){
				var linkedStoreId = link.linkStoreId;
				$("input[value='"+linkedStoreId+"']").attr("checked",true);
			});
		}, function(data){
			console.log(data);
			alertFail("fail to fetch shop linkds!");
		});
	}
}