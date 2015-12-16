$(function(){
	
	var registerConfig = {
		URL : {
			REGISTER : "user/register",
			CHECK_NAME:"user/register/check"
		},
		validateConfig : [{
			id : 'userName',
			name : 'userName name',
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
			id : 'tel',
			name : 'tel number'
		},{
			id : 'email',
			name : 'email'
		}]
	};
	
	new Register(registerConfig);
});