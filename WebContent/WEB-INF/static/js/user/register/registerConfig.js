$(function(){
	
	var registerConfig = {
		URL : {
			REGISTER : "user/register.json",
			CHECK_NAME:"user/register/check.json"
		},
		validateConfig : [{
			id : 'userName',
			name : 'user name',
			asynValidate : function(value){
				Register.userNameExistCheck(value);
			}
		},{
			id : 'password',
			name : 'password'
		},{
			id : 'confirm',
			name : 'password confirm',
			validate : function(value){
				return Register.confirmPasswordCheck(value);
			}
		},{
			id : 'phoneNumber',
			name : 'phone number'
		},{
			id : 'email',
			name : 'email'
		}]
	};
	
	new Register(registerConfig);
});