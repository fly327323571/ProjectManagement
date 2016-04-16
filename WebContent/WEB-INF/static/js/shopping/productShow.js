function ProductShow(config){
	var _config=config;
	var curPageIdx = 1;
	var amounts = parseInt($("#amounts").text());
	
	//------------------加载评论----------------------//
	
	function renderComments(data){
		var $ul =$(".pdt-comment");
		$ul.children().remove();
		
		$.each(data,function(i, item){
			var $li=$("<li></li>");
			var $self_info = $("<div class='self-info'></div>");
				var $spanName=$("<span>"+item.buyerName+"</span>");
				var points=item.points.toString();
				var $Points=$("<span>"+"<label>Delivery:</label>"+points.charAt(2)+"&nbsp&nbsp<label>Service:</label>"+points.charAt(1)+"&nbsp&nbsp<label>Consistent:</label>"+points.charAt(0)+"</span>");
			var $self_comment = $("<div class='self-comment'></div>");
				var $spanContent=$("<span>"+item.comment+"</span>");
				
			$self_info.append($spanName).append($Points);
			$self_comment.append($spanContent);
			$li.append($self_info).append($self_comment);
			if(item.reply){
				$li.append('<div class="reply"><span style="margin-left: 20px;">Shop owner Reply:</span>'+item.reply+'</div>');
			}
			$ul.append($li);
		});
	}
	
	function getOrderFilters(){
		var orderFilters = [];
		orderFilters[0] = {
				"name" :	"productsOfOrderId",
				"isAscending" : true
			};
		
		return orderFilters;
	}
	
	function getColumnFilters(){
		var columnFilters = [];
		columnFilters.push({
				"name" : 'productId',
				"filterType" : 'EQUAL',
				"value" : [parseInt($("#productId").val())]
		});
		
		return columnFilters;
	}
	
	function getQueryParam(){
		var orderFilters = getOrderFilters();
		
		var columnFilters = getColumnFilters();
		
		var queryParam = {
				"page" : {
					"pageIndex" : curPageIdx,
					"pageSize" : 5
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
	
	function loadComments(){
		var queryParam = getQueryParam();
		baseAjax.doAjax(_config.URL.LOAD_COMMENTS,queryParam, function(rs){
			var page = rs.result;
			renderComments(page.data);
			if(!page.data.length)return;
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
		},function(rs){
			var html=$("<span>no records</span>");
			$(".pdt-comment").append(html);
		});
		
	}
	loadComments();
	//------------------页面绑定函数------------------//
	//修改数量
	function modifyNumber(){
		$("#product_number_input").bind("change",function(){
			var productNum=parseInt($("#product_number_input").val());
			if(Number.isNaN(productNum) || productNum<1 || productNum > amounts){
				 alertFail("invalid product quantity");
				 return;
			 }
			if(productNum>1){
	 			$("#minus_number_button").attr('disabled',false);
	 		}else{
	 			$("#minus_number_button").attr('disabled',true);
	 		}
			if(productNum < amounts){
				$("#add_number_button").attr("disabled",false);
			}else{
				$("#add_number_button").attr("disabled",true);
			}
		});
		
		 function addProductNumber(){
	 		var productNum=parseInt($("#product_number_input").val());
	 		 if(Number.isNaN(productNum)|| productNum<1 || productNum >= amounts){
				 alertFail("invalid product quantity");
				 return;
			 }
	 		productNum++;
	 		$("#product_number_input").val(productNum);
	 		if(productNum>1){
	 			$("#minus_number_button").attr('disabled',false);
	 		}
	 		if(productNum >= amounts){
	 			$("#add_number_button").attr("disabled",true);
	 		}
	 		calcPostage();
		}
		$("#add_number_button").bind("click",addProductNumber);
		function minusProductNumber(){
			 var productNum=parseInt($("#product_number_input").val());
			 if(Number.isNaN(productNum)|| productNum<1 || productNum > amounts){
				 alertFail("invalid product quantity");
				 return;
			 }
			 productNum--;
			 $("#product_number_input").val(productNum);
			 if(productNum<=1){
		    	$("#minus_number_button").attr('disabled',true);
		     }
			 if(productNum < amounts){
					$("#add_number_button").attr("disabled",false);
			  }
			 calcPostage();
		}
		$("#minus_number_button").bind("click",minusProductNumber);
	}
	modifyNumber();
	
	
	 //买东西
    function buy(){
    	var date=new Date();
    	 var order={
  			   productId:$("#productId").val(),
  			   price:$("#price").html(),
  			   quantity:$("#product_number_input").val(),
  			   productImage:$(".view .img-thumbnail").attr("src"),
  			   addTime:date.getTime()
  	     };
    	 
	    function validateOrder(){
	    	var temp=$(".option");
     		var amount=temp.length;
     		for(var t=0;t<amount;t++){
     			if($(temp[t]).hasClass("checked")){
     				//order.option=$(t).html();
     			}
     		}
     			
     		if(order.option=='' ||order.number==''){
     			$("#info-show").css("display","block");
     			$(".right").css("border","1px solid #FF5400");
     			return;
     		}
	    }
	    
	     $("#buyNow").bind("click",function(){
	    	    order.quantity = $("#product_number_input").val();
	    	    validateOrder();
	     		//给后台数据obj即可
	     		baseAjax.doAjax(_config.URL.BUY_NOW, order, function(rs){
	     			window.location.href=_config.URL.CONFIRM;
	     		}, function(rs){
	     			alert(rs.result.message);
	     		});
		    	
		    });	
		 
	   //添加到购物车点击事件
	 	$("#cart").bind("click",function(){
	 		 order.quantity = $("#product_number_input").val();
	     	 validateOrder();
	  		//给后台数据obj即可
	     	 baseAjax.doAjax(_config.URL.ADD_TO_CART, order, function(data){
	     		 alertSuccess("product has been added to the cart!",function(){
	     			 window.location.href=_config.URL.ADD_SUCCESS;
	     		 });
	      	}, function(data){
	      		console.log(data.result);
	      		alertFail("Sorry, the server seems not performing well!");
	      	});
	     });	
	     
	 }
	 buy();
	 
	//导航栏点击变色
  	$(".comment .result").bind("click",function(){
  		$(".comment .result").css("background","white");
  		$(".comment .result").css("color","#FF5400");
  		$(this).css("background","#FF5400");
  		$(this).css("color","white");
  		var part=$(this).html();
  		if(part=="comments"){
  			$(".comment .pdt-comment").css("display","block");
  			$(".comment .pdt-detail").css("display","none");
  			$("#pagination").show();
  		}else{
  			$(".comment .pdt-comment").css("display","none");
  			$(".comment .pdt-detail").css("display","block");
  			$("#pagination").hide();
  		}
  	});
  	
  	//单项选择号码,点击改变样式
 	$(".option").bind("click",function(){
 		$(".option").removeClass("checked");
 		$(this).addClass("checked");
 	});
 	//add favorite
 	$("#favorite").bind("click",function(){
 		$(this).css("color","red");
 		
 		baseAjax.doAjax(_config.URL.ADD_FAVORITE_PRODUCT, $("#productId").val(), function(data){
 			alertSuccess("add product to favorite successfully");
 		},function(data){
 			console.log(data);
 			alertFail("sorry, add favorite failed."+data.result.message);
 		});
 	});
 	
 	$("#addFavoriteShop").bind("click",function(){
 		baseAjax.doAjax(_config.URL.ADD_FAVORITE_STORE, $(this).attr("storeId"), function(data){
 			alertSuccess("add shop to favorite successfully");
 		},function(data){
 			console.log(data);
 			alertFail("sorry, add favorite failed."+data.result.message);
 		});
 	});
 	//------------------------------计算邮费-------------------------------//
 	function calcPostage(){
 		var province = getSelectText($("#province"));
 		var quantity = $("#product_number_input").val();
 		var url = _config.URL.POSTAGE_CALC + "?province=" + province + "&quantity=" + quantity;
 		baseAjax.doAjax(url,null,function(data){
 			$("#postage").text("$"+data.result);
 		},function(data){
 			
 		});
 		
 	}
 	//---------------------------省市自治区------------------------------//
 	function loadProvinces(){
		baseAjax.doAjax(_config.URL.LOAD_PROVINCE,null,function(data){
			var provinces = data.result;
			$.each(provinces,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#province").append(option);
			});

			$("#province").trigger("change");
		},function(data){
			
		});
	}
	
	function loadCities(province){
		var url = _config.URL.LOAD_CITY.replace("{province}",province);
		baseAjax.doAjax(url,null,function(data){
			var provinces = data.result;
			$("#city").children().remove();
			$.each(provinces,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#city").append(option);
			});
		},function(data){
			
		});
	}
	
	function getSelectText($select){
		var idx = parseInt($select.val());
		var text = $($("#province").children().get(idx)).text();
		return text;
	}
	
	$("#province").bind("change",function(){
		var province = getSelectText($("#province"));
		loadCities(province);
		calcPostage();
	});
	
	loadProvinces();
	
	$("#enterShop").bind("click",function(){
		window.location.href = _config.URL.ENTER_SHOP;
	});
	
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
	$("#minus_number_button").attr('disabled',true);
	
	$("#contact").bind("click",function(){
		var username = $(this).attr("username");
		$("#chat").attr("userId",username);
	});
}