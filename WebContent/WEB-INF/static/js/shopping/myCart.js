function MyCart(config){
	var _config=config;
	
	
	var table = new Table($("#orderList"),_config.tableConfig);	
	var _orderList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'addTime';
	var isAscending = false;
	var selectedCartIds = [];
	var _cartId;
	/*var searchType = 'productName';*/
	
	
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
		
		/*var keyword = $("#keyword").val();
		if(keyword){
			columnFilters.push({
				"name" : searchType,
				"filterType" : searchType=='productName'?'CONTAIN':'EQUAL',
				"value" : [keyword]
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
			_orderList = page.data;
			table.loadData(_orderList);
			if(_orderList && _orderList.length>0){
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
				bindModifyNumber();//监听修改数量
				bindViewDetail();//监听查看商品详情，重新购买
				bindFailureOrder();//监听失效
				bindCheckOut();//绑定确认订单
				bindCheckBoxEvent();
				calculateTotalPrice();//计算总价
			}
			
		}, function(data){
			alertFail(data.result.message);
		});
	}
	
	loadData();
	
	//---------------------------对话框--------------------------//
		
	//删除物品的对话框配置
	var dialog = {
		title : 'Please Confirm',//对话框的标题
		renderer : function(){//渲染函数 返回一段html
			return '<p>Are you sure to delete the product from cart?</p>';
		},
		operation : [{//对话框底部的操作按钮
			id : 'delete',//对应按钮的id
			name : 'Delete',//对应按钮的标题
			css : 'btn-primary',//对应到按钮的class='btn-primary'
			callback : function(){//对话框中的Delete按钮
				baseAjax.doAjax(_config.URL.DELETE + "?cartId="+_cartId, null, function(rs){
					if(_orderList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
						curPageIdx--;
					}
					loadData();
				},function(rs){
					console.log(rs.result.message);
            		alertFail(rs.result.message);
				});
			}
		},{
			id : 'cancel',
			name : 'Cancel',
			css : 'btn-default',
			data_dismiss : true
		}]
	};
	
	//---------页面绑定函数-----------//
	$('body').on("click",".delete",function(){
		_cartId = $(this).attr('cartId');
		$("#dialog").modal(dialog);
	});
	
	function bindCheckBoxEvent(){
		$("#orderList input[type='checkbox']").bind("click",function(){
			var checked = $(this).attr('checked');
			$(this).attr('checked',!checked);
			calculateTotalPrice();
		});
	}
	
	function calculateTotalPrice(){
		var checkedItem = $("#orderList input[checked='checked']").parents("tr");
		var totalPrice = 0;
		selectedCartIds = [];
		$.each(checkedItem,function(i, item){
			var $item = $(item);
			var price = parseFloat($item.find(".price").text());
			totalPrice += price;
			var cartId = parseInt($item.find("input[type='text']").attr('cartid'));
			selectedCartIds.push(cartId);
		});
		$("#totalPrice").html(totalPrice);
	}
	
	//修改商品数量
	function bindModifyNumber(){
			
		  function calculatePrice(obj,operate,productNum){
		  	var $tr=obj.parent().parent();
		  	var unitPrice=$tr.find('.unitPrice').html();
		  	var price=$tr.find('.price').html(unitPrice*productNum);
		  	calculateTotalPrice();
		  }
		  
		  function updateCart($input,productNum, success, fail){
			  if(!Validator.regex.positiveInteger.test(productNum)){
				  alertFail("product quantity must be positive number!");
				  return;
			  }
			  var cart = {
				  cartId :  $input.attr('cartid'),
				  quantity : productNum
			  };
			  baseAjax.doAjax(_config.URL.UPDATE, cart, function(data){
				  success($input);
			  },function(data){
				  fail($input);
			  });
		  }
		  
		  
		  $(".product_number_input").bind("change",function(){
			  var productNum = parseInt($(this).val().trim());
			  if(Number.isNaN(productNum)|| productNum<1){
				 alertFail("invalid product quantity");
				 return;
			  }
			  updateCart($(this),productNum,function($input){
				  alertSuccess("product quantity has been changed successfully!");
				  if(productNum>=2){
					  $input.prev().attr('disabled',false);
			      }else{
			    	  $input.prev().attr('disabled',true);
			      }
			  },function($input){
				  
			  });
		  });
		  
	    $(".add_number_button").on("click",function(){
	      
	     	var productNum = parseInt($(this).prev().val());
	     	if(Number.isNaN(productNum)|| productNum<1){
				 alertFail("invalid product quantity");
				 return;
			}
	    	productNum++;
	    	updateCart($(this).prev(),productNum,function($input){
	    		alertSuccess("product quantity has been changed successfully!");
	    		$input.val(productNum);
		    	
		    	calculatePrice($input,'add',productNum);
		    	if(productNum>=2){
		    		$input.prev().attr('disabled',false);
		    	}
	    	},function($input){
	    		productNum--;
	    	});
	    	
	    });
	 
	    $(".minus_number_button").on("click",function(){
	     	var productNum=parseInt($(this).next().val());
	     	if(Number.isNaN(productNum)|| productNum<1){
				 alertFail("invalid product quantity");
				 return;
			}
	    	productNum--;
	    	updateCart($(this).next(),productNum,function($input){
	    		alertSuccess("product quantity has been changed successfully!");
	    		$input.val(productNum);
		    	calculatePrice($input,'minus',productNum);
		    	if(productNum<=1){
		    		$input.prev().attr('disabled',true);
		    	}
	    	},function($input){
	    		productNum++;
	    	});
	    	
	    });
	}
	
	//监听失效
	function bindFailureOrder(){
		var $table=$("#orderList");
		var $tr=$table.find('tr');
		$.each($tr,function(i,item){
			var status=$(item).find('.status').html();
			var pre_tr=$(item).prev();
			if(status=='failure'){
				pre_tr.find('.viewProductDetail').html('Failure!<br />you can buy again');
				$(item).find('.select').attr("disabled","disabled");
				$(item).css("background-color","#F5F5F5");
			}
		});
	}
	
	//查看商品详情，重新购买，返回到productShow页面
	function bindViewDetail(){
		$(".viewPriductShow").bind("click",function(){
			window.location.href=_config.URL.VIEW_PRODUCT_DETAIL;
		});
	}
	
	//确认订单
	function bindCheckOut(){
		
		var dialog = {
			title : 'Illegal Operation',
			renderer : function(){
				return "<p>You didn't select any goods,please select some goods and try again!</p>";
			},
			operation : [{
				id : 'ok',
				name : 'OK',
				css : 'btn-primary',
				data_dismiss : true
			}]
		};
		
		$("#check").bind("click",function(){
			if(selectedCartIds.length==0){
				$("#dialog").modal(dialog);
			}else{
				baseAjax.doAjax(_config.URL.CHECK_OUT, selectedCartIds, function(rs){
					window.location.href = _config.URL.ORDER_CONFIRM;
				},function(rs){
					alertFail(rs.data.message);
				}); 
			}
		});
	}
}