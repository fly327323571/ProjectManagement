function AddProduct(config){
	
	var _config = config;
	var image_path = '';
	var validator = new Validator(_config.validateConfig);
	
	//$("#productForm").attr('action',_config.URL.ADD);
	
	//-----------------------验证函数-------------------------//
	function checkProductName(value){
		var url = _config.URL.CHECK_PRODUCT_NAME ;
		var product = {
				'commodityName' : $("#commodityName").val()}
		baseAjax.doAjax(url, product, function(data){
			var isValid = data.result;
			if(isValid=="Can Use"){
				validator.markValid('commodityName');
			}else{
				validator.markInvalid('commodityName');
			}
		}, function(data){
			
		});
	}
	AddProduct.checkProductName = checkProductName;
	
	
	function checkPhoto(){
		var imageUrl = $("#defaultImage").val();
		if(!imageUrl){
			alertFail("please upload photo of your product!");
			return false;
		}
		return true;
	}
	
	//--------------------邦定事件---------------------------//
	$("#addProductBtn").bind("click",function(){
		if(!validator.validate()){
			return;
		}
		
		if(!checkPhoto()){
			return;
		}
		var product = {
			'commodityName' : $("#commodityName").val(),
			'commodityPrice' : parseFloat($("#commodityPrice").val()),//此处需要考虑转换符号
			'type' : parseInt($("#type").val()),//
			'commodityCount' : parseInt($("#commodityCount").val()),
			'commodityDetail' : $("#commodityDetail").val(),
			'defaultImage' : $("#defaultImage").val()
		};
		
		baseAjax.doAjax(_config.URL.ADD, product, function(data){
			alertSuccess('product has been added successfully',function(){
				window.location.href = _config.URL.LIST;
			});
		}, function(data){
			alertFail(data.result.message);
		});
	});
	
	function bindUploadFileEvent(){
		//绑定事件
		$("#file").change(function(){
			
			// 开始上传文件时显示一个图片
	        $("#uploading").show();
	        $("#upload_mark").hide();
			baseAjax.ajaxFileUpload(_config.URL.UPLOADIMG,'file',function(data){
				console.log(data);
	            var $mark = $("#upload_mark");
	            $mark.attr('class','glyphicon glyphicon-ok mark_OK');
	        	$mark.show();
	        	image_path = data.result;
	            $("#uploading").hide();
	            $("#defaultImage").val(image_path);
	            $("#_productImage").attr('src',image_path);
	            $("#_productImage").fadeIn('1500');
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

	$("#storeId").val(storeId);
}