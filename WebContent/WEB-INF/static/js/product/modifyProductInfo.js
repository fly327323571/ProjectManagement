function ModifyProductInfo(config){
	var _config=config;
	var validator = new Validator(config.validateConfig);
	//--------modal函数----------//
	function showTip(txt){
		$("#dialog .modal-body p").html(txt);
		$("#dialog").modal();
	}
	_config.dialogRemind = showTip;
	
	//----------------验证函数-----------------------//
	
	function checkProductName(value){
		var url = _config.URL.CHECK_PRODUCT_NAME + "?commodityName=" + value;
		baseAjax.doAjax(url, null, function(data){
			var isValid = data.result;
			if(isValid){
				validator.markValid('commodityName');
			}else{
				validator.markInvalid('commodityName');
			}
		}, function(data){
			
		});
	}
	ModifyProductInfo.checkProductName = checkProductName;
	//保存更新
	$("#save").bind("click",function(){
		var product = {
				"productId" : productId,
				"commodityName" : $("#commodityName").val(),
				"commodityPrice" : $("#commodityPrice").val(),
				"category" : $("#category").val(),
				"commodityCount" : $("#commodityCount").val(),
				"commodityDetail" : $("#commodityDetail").val(),
				"defaultImage": $("#file").val(),
			};
		
		if(!validator.validate()){
			return;
		}
		
		if(product.defaultImage=="")
		{
			showTip("You must choose a picture as your product's image");
			return;
		}
		baseAjax.doAjax(_config.URL.SAVE, product, function(data){
			alertSuccess("modified successfully!",function(){
				window.location.href = _config.URL.SUCCESS;
			});
		},function(data){
			showTip(data.result);
		});
	});
	//取消修改
	$("#close").bind("click",function(){
		window.location.href=_config.URL.SUCCESS;
	});
	//图片上传
	function bindUploadEvent(){
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
	        	$(".pic > img").attr('src', logo_path);
	            $("#uploading").hide();
	            bindUploadEvent();
			},function(data){
				var $mark = $("#upload_mark");
				$mark.attr('class','glyphicon glyphicon-remove mark_ERROR');
	        	$mark.show();
	            $("#uploading").hide();
	            bindUploadEvent();
			});
		});
	}
	
	bindUploadEvent();
}