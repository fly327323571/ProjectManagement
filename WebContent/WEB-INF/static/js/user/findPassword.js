$(function(){
	var config = {
		URL : {
			CHECK_CODE : "user/checkCode.json?code=",
			FIND_PASSWORD : "user/findPassword.json",
			IMAGE : "service/checkcodeImage.jpg",
		},
		validateConfig : [{
			id : "email",
			name : "email"
		},{
			id : "checkWord",
			name : "check code",
			asynValidate : function(value){
				
				baseAjax.doAjax(config.URL.CHECK_CODE+value, null, function(data){
					if(data.result){
						validator.markValid('checkWord');
					}else{
						validator.markInvalid('checkWord');
					}
				}, function(data){
					alertFail("server internal error,please contact with admin");
				});
			}
		}]
	};
	
	var validator = new Validator(config.validateConfig);
	
	function close(){
		window.opener = null;
		window.open("", "_self");
		window.close();
	}
	
	$("#find").bind("click",function(){
		if(!validator.validate()){
			return;
		}
		var data = {
			email : $("#email").val(),
			checkCode : $("#checkWord").val()
		};
		baseAjax.doAjax(config.URL.FIND_PASSWORD, data, function(data){
			var word  = "";
			var dialog = {
					title : "Notice",
					renderer : function(){
						return "<p>"+word+"</p>";
					},
					operation : [{
						id : "ok",
						name : "OK",
						css : "btn-primary",
						data_dismiss : true
					}]
			};
			
			if(data.result==0){//success
				word = "We have send you a reset password link to your email,please reset your password with this email in 2 hours.";
				
				$("body").on("hidden","#dialog",function(){
					close();
				});
			}else if(data.result == 1){
				word =  "Sorry,we didn't find the related account,please check your email again.</p>";
			}else{
				word = "Sorry, wrong check code.";
			}
			$("#dialog").remove();
			$("body").append($("#dialogTmpl").html());
			$("#dialog").modal(dialog);
		}, function(data){
			console.log(data);
			alertFail("failed to find password!");
		});
	});
	
	$("#checkImg img").bind("click",function(){
		var date = new Date();
		$(this).attr("src",config.URL.IMAGE+"?code="+date.getTime());
	});
	
});