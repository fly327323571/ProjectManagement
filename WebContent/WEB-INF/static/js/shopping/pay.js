function Pay(config){
	
	var _config=config;
	var paymentType;
	
	//--------------页面绑定函数-------------//
	$("input[type='radio']").bind("click",function(){
		$("input[type='radio']").attr("selected",false);
		$(this).attr("selected",true);
	});
	
	function gotoPay(){
		$("#dialog").modal('hide');
		window.location.href = _config.URL.CONFIRM + "?payType="+paymentType;
//		baseAjax.doAjax(,null,function(){
//			window.location.href=_config.URL.PAY_SUCCESS;
//		},function(){
//			window.location.href=_config.URL.PAY_FAIL;
//		});
	}
	
	$("#__pay").bind("click",function(){
			var selected = $("input[selected='selected']");
			if(selected.length!=1){
				alertFail("please choose the payment type!");
				return;
			}
			paymentType = selected.val();
			if(paymentType != 'COD'){
				$("#dialog").modal();
	     		$("#confirm").bind("click",gotoPay);
			}else{
				gotoPay();
			}
     });
	
	//确认支付
	$("#confirm").bind("click",function(){
		$("form").attr("action",_config.URL.CONFIRM);
		$("form").submit();
	});
}