function Homepage(config){
	var _config=config;
	
	//自适应高度
	function adaptHeight(){
		//确定高度
		var l_height=parseInt($(".list .left").css("height")); //??????????????????????
		$(".list .right").css("height",l_height);
		var r_height_head=parseInt($(".list .shop-logo").css("height"))+43;
		var height=l_height-r_height_head;
		$(".right .shop-list").css("height",height);
		
		
		var _slide;
		var i=parseInt($(".right ul").css("margin-top"));
		_slide=slide(i);
		$(".right li").hover(function(){
			clearInterval(_slide)
		},function(){
			var i=parseInt($(".right ul").css("margin-top"));
			console.log(i);
			_slide=slide(-i);
		})
		
	}
	setTimeout(adaptHeight,300);
	
	//slide
	function slide(i){
		var _ul=$(".right ul");
		
		//slide
		var flag=true;
		var _rotate=parseInt(_ul.css("height"))-parseInt($(".right .shop-list").css("height"));
		
		var  _slide=setInterval(function(){
			
			_ul.css("margin-top",-i+"px");
			
			if(flag==true){
				i++;
			}else{
				i--;
			}
			if(i>=_rotate){
				flag=false;
			}else if(i<=0){
				flad=true;
			}
		},100);
		return _slide;
	}
	
	function bindEasyNav(){
		$(".easy-nav").css("display","block");
		$(window).unbind("scroll",bindEasyNav);
	}
	
	$(window).bind("scroll",bindEasyNav);

	//in easy-nav :go to Top
	$("#goTop").bind("click",function(){
		$("body").scrollTop(0);
	});
	//carousel
	$('.carousel').carousel({
		interval:4000
	});
	
	function renderProductAd(productAd){
		var html = $("#productAdTmpl").html().replace("{logo}",productAd.logo)
			.replace("{productName}",productAd.productName)
			.replace("{storeName}",productAd.storeName)
			.replace("{price}",productAd.proPrice)
			.replace("{link}",productAd.link);
		return html;
	}
	
	function renderShopAd(shopAd){
		var html = $("#shopAdTmpl").html().replace("{link}",shopAd.link)
			.replace("{logo}",shopAd.logo);
		return html;
	}
	
	function loadProductAds(){
		baseAjax.doAjax(_config.URL.FETCH_PRODUCT_ADS,null,function(data){
			var productAds = data.result.data;
			$(".pdt-list").children().remove();
			$.each(productAds,function(i,ad){
				var html = renderProductAd(ad);
				$(".pdt-list").append(html);
			});
		},function(data){
			alertFail("can't fetch advertisement,please contact with admin");
		});
		$(".pdt-list").children().remove();
		
	}
	
	function loadShopAds(){
		
		baseAjax.doAjax(_config.URL.FETCH_SHOP_ADS,null,function(data){
			var shopAds = data.result.data;
			$(".shop-list ul").children().remove();
			$.each(shopAds,function(i,shopAd){
				var html = renderShopAd(shopAd);
				$(".shop-list ul").append(html);
			});
		},function(data){
			console.log(data);
			alertFail("can't fetch advertisement,please contact with admin");
		});
	}
	
	$(".pdt-list").on("click","div[link]",function(){
		var link = $(this).attr("link");
		if(!link){
			return;
		}
		window.location.href = link;
	});
	
	loadProductAds();
	loadShopAds();
}

