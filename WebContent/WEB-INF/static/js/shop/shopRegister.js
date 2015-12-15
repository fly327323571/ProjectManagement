function ShopRegister(shopRegisterConfig){
	
	var _shopRegisterConfig = shopRegisterConfig;
	var logo_path = '';
	var validator = new Validator(_shopRegisterConfig.validateConfig);
	
	//---------------------验证函数-------------------------//
	function checkShopName(value){//ajax异步验证
		
		var url = _shopRegisterConfig.URL.STORE_NAME_CHECK + "?storeName="+value;
		
		baseAjax.doAjax(url, null, function(data){
			var isValid = data.result;
			if(!isValid){
				validator.markInvalid('shopName');
			}else{
				validator.markValid('shopName');
			}
			
		}, function(data){
			alertFail(data.result.message);
		});
		
	}
	ShopRegister.checkShopName = checkShopName;
	
	
	function checkCategory(value){
		return parseInt(value) >= 0;
	}
	ShopRegister.checkCategory = checkCategory;

	//---------------------绑定事件----------------------------//
		
	//注册按钮
	$("#register").bind("click", function(){
		
		if(!validator.validate())
			return;
		
		if(logo_path==""){
			alertFail("please upload your store logo");
			return;
		}
		
		var store = {
			"storeName" : $("#shopName").val(),
			"type" : $("#category").val(),
			"address" : {
				"province" : getSelectText($("#province")),
				"city" : getSelectText($("#city")),
				"address" : $("#address").val()
			},
			"description" : $("#remarks").val(),
			"logo" : logo_path
		};
		
		baseAjax.doAjax(_shopRegisterConfig.URL.OPEN_STORE, store, function(data){
			alertSuccess('request for new store has been submitted',function(){
				window.location.href = _shopRegisterConfig.URL.SHOP_LIST;
				var shopOwnerHomepage = window.parent.ShopOwnerHomepage;
				if(shopOwnerHomepage)
				{
					shopOwnerHomepage.selectOption("nav_shoplist");
				}
			});
		},function(data){
			alertFail(data.result.message);
		});
	});
	
	function bindUploadFileEvent(){
		//上传文件
		$("#logo").bind("change",function(){
			// 开始上传文件时显示一个图片
	        $("#uploading").show();
	        $("#upload_mark").hide();
			baseAjax.ajaxFileUpload('upload/fileUpload','logo',function(data){
				console.log(data);
	            var $mark = $("#upload_mark");
	            $mark.attr('class','glyphicon glyphicon-ok mark_OK');
	        	$mark.show();
	        	logo_path = data.path;
	            $("#uploading").hide();
	            $("#_shopImage img").fadeOut('1000',function(){
	            	$("#_shopImage img").attr('src',data.path);
		            $("#_shopImage img").fadeIn('1000');
	            });
	            bindUploadFileEvent();
			},function(data){
				var $mark = $("#upload_mark");
				$mark.attr('class','glyphicon glyphicon-remove mark_ERROR');
	        	$mark.show();
	            $("#uploading").hide();
	            bindUploadFileEvent();
			});
		});
	}
	bindUploadFileEvent();
	
	//----------------------------常用函数------------------------------//
	function loadProvinces(){
		baseAjax.doAjax(_shopRegisterConfig.URL.LOAD_PROVINCE,null,function(data){
			var provinces = data.result;
			$.each(provinces,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#province").append(option);
			});
			
		},function(data){
			console.log(data);
			alertFail(data.result.message);
		});
	}
	
	function loadCities(province){
		var url = _shopRegisterConfig.URL.LOAD_CITY.replace("{province}",province);
		baseAjax.doAjax(url,null,function(data){
			var provinces = data.result;
			$("#city").children().remove();
			$("#city").append('<option value="-1" style="display:none;">select your city</option>');
			$.each(provinces,function(i,province){
				var option = '<option value="'+i+'" class="tips">'+province+'</option>';
				$("#city").append(option);
			});
			
		},function(data){
			
		});
	}
	
	function getSelectText($select){
		var idx = parseInt($select.val())+1;
		var text = $($select.children().get(idx)).text();
		return text;
	}
	
	$("#province").bind("change",function(){
		var province = getSelectText($("#province"));
		loadCities(province);
	});
	
	loadProvinces();
}