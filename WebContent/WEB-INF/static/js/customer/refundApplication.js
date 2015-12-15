function Refund(config){
	
	var _config = config;
	var validator = new Validator(_config.validateConfig);
	var logo_path = undefined;
	
	//----------------------------邦定事件-----------------------------------//
	$("[type='submit']").bind("click",function(){
		if(!validator.validate()){
			return;
		}
		if(!logo_path){
			alertFail("please upload proof of your refund reason!");
			return;
		}
		var refund = {
			orderId : parseInt($(".order-id").text()),
			type : $("#refundReason").val(),
			refundAmounts : parseFloat($("#refundAmount").val()),
			description : $("#description").val(),
			image : logo_path
		};
		baseAjax.doAjax(_config.URL.REFUND_SUBMIT, refund, function(data){
			window.location.href = _config.URL.SUCCESS.replace("{orderId}",refund.orderId);
		}, function(data){
			console.log(data);
			alertFail(data.result.message);
		});
	});
	
	
	//----------------------------文件上传-----------------------------------//
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
	        	logo_path = data.path;
	            $("#uploading").hide();
	            $(".picture img").fadeOut('500',function(){
	            	$(".picture img").attr('src',data.path);
		            $(".picture img").fadeIn('1000');
		            $("#image").val(data.path);
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
	
	
	
	if(info){
		var dialog = {
				title : "Illegal operation",
				renderer : function(){
					return "<p>"+info+"</p>";
				},
				operation :[{
					id : 'OK',
					name : 'OK',
					css : "btn-primary",
					data_dismiss : true
				}]
				
		};
		$("#dialog").bind("hidden",function(){
			window.close();
		});
		$("#dialog").modal(dialog);
	}
}