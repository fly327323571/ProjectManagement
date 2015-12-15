$(function(){
	
	var loginConfig = {
		URL : {
			LOGIN : "admin/login.json",
			ADMINHOMEPAGE : "admin/adminHomepage"
		}	
	};
	
	new Login(loginConfig);
});