$(function(){
	var config={
			URL:{
				CONFIRM:'business/pay.json',
				PAY_SUCCESS:'',
				PAY_FAIL:''
			}
	};
	new Pay(config);
});