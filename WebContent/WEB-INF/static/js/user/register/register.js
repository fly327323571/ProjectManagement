function Register(registerConfig){
	
	var _registerConfig = registerConfig;
	var nameValid = true;
	var validator = new Validator(_registerConfig.validateConfig);
	//----------------验证函数-------------------//
	function nameCheck(){
		return nameValid;
	}
	
	function confirmPasswordCheck(value){
		var password = $("#password").val();
		var confirm = value;
		return password == confirm;
	}
	
	Register.confirmPasswordCheck = confirmPasswordCheck;
	
	function userNameExistCheck(value){//后台验证用户名是否合法
		var info={
			userName:value
		};
			
		baseAjax.doAjax(_registerConfig.URL.CHECK_NAME, info, function(data){//成功操作
			console.log("success"+data);
			validator.markValid('userName');
		}, function(data){//失败操作
			console.log("fail"+data);
			
			validator.markInvalid('userName');
		});
	}
	
	Register.userNameExistCheck = userNameExistCheck;
	//----------------绑定事件---------------------//
		
	$("form").bind("submit",function(){
		return validator.validate();
	});
	
	$("#password").bind("change",function(){
		$("#confirm").val('');
	});
}