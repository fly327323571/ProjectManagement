function Login(loginConfig){
	//alert(window.document.referrer);
	var _loginConfig = loginConfig;
	
	//----------------绑定事件---------------------//
	
	$("#password").bind("keydown",function(e){
		if(e.keyCode == 13){
			$("#login").click();
		}
	});
	
	$(document).on("click","#login",function(e){
		var user={
				userName:$("#userName").val(),
				password:$("#password").val()
		}; 
		if(user.userName=='' && user.password==''){
			$(".login-info").html("please input the account and password!");
			$("#show-info").append($(".login-info"));
			return;
		}else if(user.userName==''){
			$(".login-info").html("please input the account!");
			$("#show-info").append($(".login-info"));
			return;
		}else if(user.password==''){
			$(".login-info").html("please input the password!");
			$("#show-info").append($(".login-info"));
			return;
		}else{
			//doAjax包装后的返回数据格式
			//var data = {
//				code : 0,
//				result : ...
			//};
			
			//var data = {
//				code : -2,
//				result : {
//					message : "error message"
//				}
			//};
			baseAjax.doAjax(_loginConfig.URL.LOGIN, user, function(data){//成功操作
				console.log(data.result);
				if(redirectUrl){
					location.href = redirectUrl;
				}else{
					location.href = _loginConfig.URL.ADMINHOMEPAGE;
				}
			}, function(data){//失败操作
				alert(data.result.message);
			});
		}
	});
	
	function checkParent(){//如果本窗口是装载在父窗口的iframe里头,父窗口调整到登陆
		if(window.parent.location.href != location.href){
			window.parent.location.href = location.href;
		}
	}
	
	checkParent();
}

