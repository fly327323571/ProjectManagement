$(function(){
	var config = {
		URL : {
			SUBMIT : "user/resetPassword.do"
		},
		validateConfig : [{
			id : 'password',
			name : 'password'
		},{
			id : 'confirm',
			name : "confirm password",
			validate : function(){
				var confirm = $("#confirm").val();
				var password = $("#password").val();
				return confirm == password;
			}
		}]
	};
	var validator = new Validator(config.validateConfig);
	
	$("#password").bind("change",function(){
		$("#confirm").val("");
	});
	
	$("#reset").bind("click",function(){
		if(!validator.validate()){
			return;
		}
		$("form").attr("action",config.URL.SUBMIT);
		$("form").submit();
	});
});