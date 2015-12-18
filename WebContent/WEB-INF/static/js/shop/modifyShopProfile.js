function modifyShopProfile(modifyShopProfileConfig){
	
	var _modifyShopProfileConfig = modifyShopProfileConfig;
	var logo_path = '';
	var storeNameAvailable = true;
	var basicValidator = new Validator(_modifyShopProfileConfig.basicValidateConfig,$("#basicProfile"));
	var policyValidator = new Validator(_modifyShopProfileConfig.policyValidateConfig,$("#policyProfile"));
	
	//------------------验证函数------------------//
	
	function checkShopName(){
//		var storeName = $("#shopName").val().trim();
		var url = _modifyShopProfileConfig.URL.CHECK_STORE_NAME;
		baseAjax.doAjax(url,$("#shopName").serialize(), function(data){
			var isValid = data.result;
			$("span.validate").remove();
			if(isValid=="can Use"){
				storeNameAvailable = true;
				basicValidator.markValid('shopName');
				//$("#storeName").after("<span style='color:green;position: absolute;right: -5px;top: 5px;color:green;' class='validate glyphicon glyphicon-ok'></span>");
			}else{
				storeNameAvailable = false;  
				basicValidator.markInvalid('shopName');
				//$("#storeName").after("<span style='color:red;position: absolute;right: -5px;top: 5px;' class='validate glyphicon glyphicon-remove'></span>");
			}
		}, function(data){
			alertFail("server internal error");
			basicValidator.markInvalid('storeName');
		});
		return true;
	}
	modifyShopProfile.shopNameExistCheck = checkShopName;
		
	function checkLogo(){
		var logo = $(".pic > img").attr('src');
		if(!logo){
			alertFail('please upload logo of your shop');
			return false;
		}
		return true;
	}
		
	//----------------绑定事件---------------------//
		
	$("#save").bind("click",function(){

		if(!storeNameAvailable){
			alertFail("shop name has been used by other shop owner!");
			return;
		}
		if(!checkLogo()){
			return;
		}
		
		var modifyShopProfile={
				shopNo: $("#shopNo").val(),
				shopName: $("#shopName").val().trim(),
				shopDesc: $("#remarks").val(),
				shopCategories: $("#category").val(),
				shopIcon: $(".pic > img").attr('src')
		}; 
		
		baseAjax.doAjax(_modifyShopProfileConfig.URL.MODIFY, modifyShopProfile, function(data){//成功操作
			//console.log(data.result);
			alertSuccess("modify success",function(){
				if(window.parent.location.href!=location.href){
					window.parent.location.reload();
				}else{
					location.reload();
				}
			});
		}, function(data){//失败操作
			alertFail(data.result.message);
		});
		
		
	});
	
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
	        	logo_path = data.result;
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
	
	function policyOnChange(){
		var that = $("#policy");
		var policy = that.val();
		if(policy==0){//free of postage
			$(".charge-policy").children().remove();
		}else if($(".charge-policy").children().length==0){
			var html = $("#policyTmpl").html();
			$(".charge-policy").append(html);
		}
	}
	$("#policy").bind("change",policyOnChange);
	
	//保存邮费策略
	$("#savePolicy").bind("click",function(){
		if(!policyValidator.validate()){
			return;
		}
		var policies = [];
		var policy = {
			policyType : parseInt($("#policy").val()),
		};
		policies.push(policy);
		if(policy.policyType!=0){
			policy.destination = 'within province';
			policy.baseFee = $("#within_basicPostage").val();
			policy.quantityLimit = $("#within_quantityLimit").val();
			policy.appendUnitFee = $("#within_extfee").val();
			var policy_extra = {
				policyType : policy.policyType,
				destination : 'cross province',
				baseFee : $("#cross_basicPostage").val(),
				quantityLimit :  $("#cross_quantityLimit").val(),
				appendUnitFee : $("#cross_extfee").val()
			};
			policies.push(policy_extra);
		}
		var url = _modifyShopProfileConfig.URL.POSTAGE_POLICY;
		baseAjax.doAjax(url, policies, function(data){
			alertSuccess("modify success",function(){
				if(window.parent.location.href!=location.href){
					window.parent.location.reload();
				}else{
					location.reload();
				}
			});
		}, function(data){
			console.log(data);
			alertFail(data.result.message);
		});
	});
	
	function renderExpressRules(){
		if(!expressRules || expressRules.length==0){
			return;
		}
		var policyType = expressRules[0].policyType;
		$("#policy").val(policyType);
		policyOnChange();
		if(!policyType){
			return;
		}
		$.each(expressRules,function(i,rule){
			var type = "";
			if(rule.destination == "within province"){
				type = "within";
			}else if(rule.destination == "cross province"){
				type = "cross";
			}
			$("#"+type+"_basicPostage").val(rule.baseFee);
			$("#"+type+"_quantityLimit").val(rule.quantityLimit);
			$("#"+type+"_extfee").val(rule.appendUnitFee);
		});
	}
	renderExpressRules();
}

