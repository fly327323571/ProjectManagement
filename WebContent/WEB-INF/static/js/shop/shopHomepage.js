function ShopHomepage(config){
	
	var _config=config;
	var pageSize = 12;
	var curPageIdx = 1;
	var orderBy ='productId';
	var isAscending = false;
	var _productList = [];
	
	
	
	function scroll_1(){
   		$("body").scrollTop(700);
   		$("body").css("overflow-y","auto");
   		$("#p").css("overflow-y","auto");
   		$(window).unbind("scroll",scroll_1);
   }
   $(window).bind("scroll",scroll_1);
	//carousel
    $('.carousel').carousel({
    	interval:4000
    });
    //show easy-nav
    function scroll_2(){
    	$(".easy-nav").css("display","block");
    	$(window).unbind("scroll",scroll_2);
    }
    $(window).scroll(scroll_2);
    
	
	
	
	//获取轮播广告的图片地址
	baseAjax.doAjax(_config.URL.ADVERTISE_IMG, storeId,function(rs){
		var ads = rs.result;
		var item = $(".item img");
		$.each(ads,function(i, ad){
			item.eq(i).attr("src",ad.logo);
			item.eq(i).bind("click",function(){
				window.location.href = ad.link;
			});
			item.eq(i).css("cursor","pointer");
		});
	},function(){
		alertFail("Can't fetch advertisement.");
	});
	
    
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
		baseAjax.doAjax(_config.URL.PRODUCT_LIST, queryParam, function(rs){
			
			var page = rs.result;
			_productList = page.data;
			renderProductList(_productList);
			if(_productList && _productList.length!=0){
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
			alertFail(data.result.message);
		});
	}
	loadData();
	
    function renderProduct(product){
    	var link = _config.URL.PRODUCT_SHOW.replace("{productId}",product.productId);
    	var html = $("#productShelfTmpl").html().replace("{link}",link)
    		.replace("{defaultImage}",product.defaultImage)
    		.replace("{productName}",product.productName)
    		.replace("{presentPrice}",product.presentPrice);
    	return html;
    }
    
    function renderProductList(productList){
    	var pdtList = $("section .pdt-list");
    	pdtList.children().remove();
    	$.each(productList,function(i,product){
    		pdtList.append(renderProduct(product));
    	});
    }
    
    function renderShopLinks(shopLinks){
    	var $shopLink = $(".link-shop ul");
    	$shopLink.children().remove();
    	$.each(shopLinks,function(i,shopLink){
    		var link = _config.URL.SHOP_HOMEPAGE.replace("{storeId}",shopLink.linkStoreId);
    		var html = $("#shopLinkTmpl").html().replace("{link}",link)
    			.replace("{logo}",shopLink.image);
    		$shopLink.append(html);
    	});
    }
    
    function getShopLinks(){
    	baseAjax.doAjax(_config.URL.SHOP_LINKS,null,function(data){
    		renderShopLinks(data.result);
    	},function(data){
    		console.log(data);
    		alertFail("fail to fetch shop links");
    	});
    }
    getShopLinks();
    
    function isBlackList(){
		if(status==3){//blackList
			var dialog = {
				title : "Please be cautious",
				renderer : function(){
					return "<p>This store is on black list, you can't purchase any products from this store!<p>";
				},
				operation : [{
					id : 'OK',
					name : "OK",
					css : "btn-primary",
					callback : function(){
						window.opener=null;
						window.open('','_self');
						window.close();
					}
				}]
			};
			$("#dialog").modal(dialog);
			$("#dialog").bind("hidden",function(){
				window.opener=null;
				window.open('','_self');
				window.close();
			});
		}
	}
	isBlackList();
}