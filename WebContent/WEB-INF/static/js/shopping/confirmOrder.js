function ConfirmOrder(config){
	var _config=config;
	
	
	var table = new Table($("#orderToPay"),_config.tableConfig);	
	var _orderList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var orderBy = 'productsOfOrderId';
	var isAscending = false;
	var validator;
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
			calculateTotalPrice();
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
				
				bindViewDetail();//监听查看商品详情，重新购买
				$(".address-frame input[type='radio']").bind("click",function(){
					var checked = $(this).attr('checked');
					$(this).attr('checked',!checked);
				});
			}
			
		}, function(data){
			alertFail(data.result.message);
		});
	}
	
	function calculateTotalPrice(){
		var prices = $("#orderToPay .price");
		var totalPrice = 0;
		$.each(prices,function(i, item){
			var $item = $(item);
			var price = parseFloat($item.text());
			totalPrice += price;
		});
		$("#totalPrice").html(totalPrice);
	}
	
	loadData();
	
	
	//---------页面绑定函数-----------//
	//查看商品详情，重新购买，返回到productShow页面
	function bindViewDetail(){
		$(".viewPriductShow").bind("click",function(){
			window.location.href=_config.URL.VIEW_ORDER_DETAIL;
		});
	}
	
	function getAddressId()
	{
		var checkbox=$(".address-frame .active input");	
		var addressId = parseInt(checkbox.attr("id"));
		return addressId;
	}
	
	//确认订单
	$("#pay").bind("click",function(){
		    var date=new Date();
		    var addressId = getAddressId();
		    if(!addressId){
		    	alertFail('please select the address');
		    	return;
		    }
		    
			baseAjax.doAjax(_config.URL.SUBMIT + "?addressId="+addressId,null,function(rs){
				window.location.href=_config.URL.PAY;
			},function(rs){
				alertFail(rs.result.message);
			});
	});
	
	//----------------------------------address -----------------------------//
	var addressDialog = {
		title : "Add your address",
		renderer : function(){
			var html = $("#newAddressTmpl").html();
			return html;
		},
		operation : [{
			id : "ok",
			name : "OK",
			css : "btn-primary",
			callback : function(){
				if(!validator.validate()){
					return false;
				}
				var address = {
					consignee:$("#consignee").val(),
					province : getSelectText($("#province")),
					city : getSelectText($("#city")),
					address:$("#address").val(),
					phoneTel:$("#phoneNumber").val()
				};
				baseAjax.doAjax(config.URL.ADD_ADDRESS, address, function(rs){
					alertSuccess("Address is added successfully!");
					var address=rs.result;
					var html = appendAddress(address); 
					$(".address-frame").append(html);
				}, function(data){
					console.log(data);
					alertFail(data.result.message);
				});
			}
		},{
			id : "cancel",
			name : "Cancel",
			css : "btn-default",
			data_dismiss : true
		}],
		onShow : function(){
			loadProvinces();
			validator = new Validator(_config.validateConfig,$("#dialog"));
		}
	};
	//添加地址
	$("#newAddress").bind("click",function(){
		$("#dialog").remove();
		$("body").append($("#dialogTmpl").html());
		$("#dialog").modal(addressDialog);
	});
	
	function loadProvinces(callback){
		baseAjax.doAjax(_config.URL.LOAD_PROVINCE,null,function(data){
			var provinces = data.result;
			$.each(provinces,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#province").append(option);
			});
			callback ? callback() : undefined;
		},function(data){
			console.log(data);
			alertFail(data.result.message);
		});
	}
	
	function loadCities(province,callback){
		var url = _config.URL.LOAD_CITY.replace("{province}",province);
		baseAjax.doAjax(url,null,function(data){
			var cities = data.result;
			$("#city").children().remove();
			$("#city").append('<option value="-1" style="display:none;">select your city</option>');
			$.each(cities,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#city").append(option);
			});
			callback ? callback() : undefined;
		},function(data){
			
		});
	}
	
	function getSelectText($select){
		var idx = parseInt($select.val())+1;
		var text = $($select.children().get(idx)).text();
		return text;
	}
	
	function appendAddress(address){
		var html = $("#addressTmpl").html().replace("{addrId}",address.addrId)
			.replace("{province}",address.province)
			.replace("{city}",address.city)
			.replace("{address}",address.address)
			.replace("{consignee}",address.consignee)
			.replace("{phoneTel}",address.phoneTel);
		return html;
	}
	
	function renderAddressList(data){
		var $div =$(".address-frame");
		$div.children().remove();
		$.each(data,function(i, address){
			var $label = $(appendAddress(address));
			if(i==0){
				$label.addClass("active");
			}
			$div.append($label);
		});
	}
	
	var modifyAddrDialog = {
			title : "Modify your address",
			renderer : function(){
				var html = $("#newAddressTmpl").html();
				return html;
			},
			operation : [{
				id : "ok",
				name : "OK",
				css : "btn-primary",
				callback : function(){
					if(!validator.validate()){
						return false;
					}
					var address = {
						addrId : modifyAddrDialog.data.addrId,
						consignee:$("#consignee").val(),
						province : getSelectText($("#province")),
						city : getSelectText($("#city")),
						address:$("#address").val(),
						phoneTel:$("#phoneNumber").val()
					};
					baseAjax.doAjax(_config.URL.UPDATE_ADDRESS, address, function(rs){
						var address=rs.result;
						var html = appendAddress(address);
						$(".address-frame input[id='16']").parent().remove();
						var $label = $(html).addClass("active");
						$(".address-frame").append($label);
						alertSuccess("Address is added successfully!");
					}, function(data){
						console.log(data);
						alertFail(data.result.message);
					});
				}
			},{
				id : "cancel",
				name : "Cancel",
				css : "btn-default",
				data_dismiss : true
			}],
			onShow : function(){
				loadProvinces(function(){
										
					var val = getValByText($("#province"),modifyAddrDialog.data.province);
					$("#province").val(val);
				});
				
				loadCities(modifyAddrDialog.data.province,function(){
					var val = getValByText($("#city"),modifyAddrDialog.data.city);
					$("#city").val(val);
				});
				validator = new Validator(_config.validateConfig,$("#dialog"));
			}
		};
	
	$("body").on("change","#province",function(){
		var province = getSelectText($("#province"));
		loadCities(province);
	});
	
	//修改address
	$(".address-frame").on("click",".modifyAddress",function(){
		var label = $(this).parents("label");
		var consignee = label.find(".consignee").text(),
			addrId = label.find("input").attr("id"),
			address = label.find(".address").text(),
			province = label.find(".province").text(),
			city = label.find(".city").text(),
			phoneNumber = label.find(".phoneNumber").text();
		    
		modifyAddrDialog.data = {
			"consignee" : consignee,
			"addrId" : addrId,
			"address" : address,
			"province" : province,
			"city" : city,
			"phoneNumber" : phoneNumber
		};
		$("#dialog").remove();
		$("body").append($("#dialogTmpl").html());
		$("#dialog").modal(modifyAddrDialog);
		
	});
	
	//删除address
	$(".address-frame").on("click",".deleteAddress",function(){
		var label = $(this).parents("label");
		var addrId = label.find("input").attr("id");
		var url = _config.URL.DELETE_ADDRESS.replace("{addressId}",addrId);
		baseAjax.doAjax(url,null,function(data){
			label.remove();
			alertSuccess("delete the address successfully");
		},function(data){
			console.log(data);
			alertFail("fail to delete address");
		});
	});
	
	function getValByText($select,text){
		return $select.children("option:contains('"+text+"')").val();
	}
	
	function getAddress(){
		baseAjax.doAjax(_config.URL.GET_ADDRESS, null, function(data){
			var addressList = data.result;
			renderAddressList(addressList);
		}, function(data){
			alertFail("can't fetch adress");
		});
		
	}
	
	getAddress();
	
}