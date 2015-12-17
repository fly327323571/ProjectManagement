$(function(){
	var config = {
		URL : {
			FILE_UPLOAD : "upload/fileUpload",
			SAVE : "shop/modifyProfile"
		},
		validateConfig : [{
			id : "phoneNumber",
			name : "phone number"
		},{
			id : "email",
			name :"email"
		}]
	};
	new ModifyCustomerProfile(config);
});