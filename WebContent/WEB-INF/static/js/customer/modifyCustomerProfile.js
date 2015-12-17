function ModifyCustomerProfile(config){
	
	var _config = config;
	var logo_path = $(".pic img").attr("src");
	var validator = new Validator(_config.validateConfig);
	
	//-------------------------验证-----o m g-----------------//
	
	
	$( "#birthday" ).datepicker({dateFormat: 'yy-mm-dd'});
	
	function bindUploadFileEvent(){
		//上传文件
		$("#file").bind("change",function(){
			// 开始上传文件时显示一个图片
	        $("#uploading").show();
	        $("#upload_mark").hide();
			baseAjax.ajaxFileUpload(_config.URL.FILE_UPLOAD,'file',function(data){
				console.log(data);
	            var $mark = $("#upload_mark");
	            $mark.attr('class','glyphicon glyphicon-ok mark_OK');
	        	$mark.show();
	        	logo_path = data.result;
	            $("#uploading").hide();
	            $(".pic img").fadeOut('500',function(){
	            	$(".pic img").attr('src',data.result);
		            $(".pic img").fadeIn('1000');
		            $("#image").attr('src',data.result);
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
	$("input[type='radio']").bind("click",function(){
		$("input[checked='checked']").attr("checked",false);
		$(this).attr("checked",true);
	});
	//保存
	$("#save").bind("click",function(){
		if(!validator.validate()){
			return;
		}
		var profile = {
			gender : $("input[checked='checked']").val(),
			birthday : $("#birthday").val(),
			phoneNumber : $("#phoneNumber").val(),
			email : $("#email").val(),
			portrait : logo_path
		};
		baseAjax.doAjax(_config.URL.SAVE, profile, function(data){
			alertSuccess("Modified successfully");
			
		}, function(data){
			alertFail(data.result.message);
		});
	});
	
	
}