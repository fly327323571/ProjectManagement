$(function(){
	
	var validateConfig = [{
		id : 'idCard',
		name : 'id card'
	},{
		id : 'realName',
		name : 'real name'
	}];
	
	var validator = new Validator(validateConfig);
	var portrait_path = '';
	
	
	$("#register").bind("click",function(){
		
		if(!portrait_path){
			alert("please upload your real portrait!");
			return;
		}
		
		if(!validator.validate()){
			return;
		}
		baseAjax.doAjax('shop/apply', $("#form").serialize(), function(data){
			alterSuccess(data.result);
			
		}, function(data){
			alterFail(data.result);
		})
//		$("#form").submit();
	});
	
	
	function bindUploadFileEvent(){
		//上传文件
		$("#file").bind("change",function(){
			// 开始上传文件时显示一个图片
	        $("#uploading").show();
	        $("#upload_mark").hide();
			baseAjax.ajaxFileUpload('upload/fileUpload','file',function(data){
				console.log(data);
	            var $mark = $("#upload_mark");
	            $mark.attr('class','glyphicon glyphicon-ok mark_OK');
	        	$mark.show();
	        	portrait_path = data.result;
	        	$("#show-info img").attr('src',data.result);
	        	$("#portrait").val(data.result);
	            $("#uploading").hide();
	            bindUploadFileEvent();
			},function(data){
				console.log(data)
				var $mark = $("#upload_mark");
				$mark.attr('class','glyphicon glyphicon-remove mark_ERROR');
	        	$mark.show();
	            $("#uploading").hide();
	            bindUploadFileEvent();
			});
		});
	}
	
	
	bindUploadFileEvent();
	
});