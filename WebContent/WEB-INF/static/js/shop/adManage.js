function AdManage(config){
	var _config = config;
	var validator = new Validator(_config.validateConfig);
	
	function bindUploadFileEvent($file,callback){
		//上传文件
		$file.bind("change",function(){
			// 开始上传文件时显示一个图片
			var $parent = $(this).parent();
			var loadingspan = '<span class="upload_mark loading" style="top: 7px;right: -48px;width: 16px;height: 16px;"></span>';
	        var markOk = '<span class="upload_mark glyphicon glyphicon-ok mark_OK" style="position: absolute; top: 8px; right: -64px;"></span>';
			var markError = '<span class="upload_mark glyphicon glyphicon-ok mark_ERROR" style="position: absolute; top: 8px; right: -64px;"></span>';
			$parent.siblings(".upload_mark").remove();
			$parent.after(loadingspan);
			baseAjax.ajaxFileUpload(_config.URL.FILE_UPLOAD,$file.attr("id"),function(data){
				console.log(data);
				$parent.siblings(".upload_mark").remove();
				$parent.after(markOk);
	        	callback ? callback(data.result) : undefined;
	            bindUploadFileEvent($file,callback);
			},function(data){
				$parent.siblings(".upload_mark").remove();
				$parent.after(markError);
	            bindUploadFileEvent($file,callback);
			});
		});
	}
	
	function bindFiles(){
		var fileInputs = $("input[type='file']");
		$.each(fileInputs,function(i, file){
			var $file = $(file);
			var $section = $file.parents("section");
			bindUploadFileEvent($file,function(path){
				var $img = $section.find("img");
				$img.fadeOut('500',function(){
		    		$img.attr('src',path);
		    		$img.fadeIn('1000');
		        });
			});
		});
	}
	
	bindFiles();
	
	function checkImage(){
		var img = $("section img[src='']");
		if(img.length != 0){
			alertFail("please upload your advertisement image!");
			return false;
		}
		return true;
	}
	//提交
	$("#submit").bind("click",function(){
		if(!validator.validate() || !checkImage()){
			return;
		};
		var ads = [];
		var $sections = $("section");
		$.each($sections,function(i,section){
			var $section = $(section);
			var img_path = $section.find("img").attr("src");
			var productId = $section.find("select").val();
			var description = $section.find("textarea").val();
				
			ads.push("{\"productId\":"+productId+","+
					"\"title\":"+"\""+description+"\","+
					"\"adImg\":"+"\""+img_path+"\"}");
			
		});
		var str=ads.join(',');
		var jsonStr={
				adsJson:str
		};
		baseAjax.doAjax(_config.URL.UPLOAD_ADS, jsonStr, function(data){
			alertSuccess("advertisement updated successfully!",function(){
				window.location.href = _config.URL.DASH_BOARD;
			});
		}, function(data){
			alertFail(data.result.message);
		});
	});
}