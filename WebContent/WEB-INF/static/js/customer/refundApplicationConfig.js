$(function(){
	var config={
		URL:{
			REFUND_SUBMIT:'business/refund.json',
			FILE_UPLOAD: 'upload/fileUpload',
			SUCCESS : 'business/refund/{orderId}/success.do'
		},
		validateConfig : [{
			id : 'refundReason',
			name : 'refund reason',
			remind : 'please select your reason for refund'
		},{
			id : 'refundAmount',
			name : 'refund amount'
		}]
	};
	new Refund(config);
});