$(function(){
	var visible = false;
	var toUserId = $("#chat").attr("userId");
	var config = {
		URL : {
			GET : "communication/getMessage.json",
			SEND : "communication/sendMessage.json",
		}
	};
	
	$("#chat").on("click",".panel-heading",function(){
		if(!visible){
			$("#chat .panel-body").hide();
		}else{
			$("#chat .panel-body").show();
			$("#chat .panel-title").html("Chatting");
		}
		visible = !visible;
	});
	
	function showMessage(data){
		var html = $("#chat .messages").html();
		var date = new Date(data.time);
		var dateString = date.getFullYear() + "/" + (date.getMonth()+1) + "/" + date.getDate() + " "
		+ date.toLocaleTimeString().substring(2);
		html = html + dateString +"<br/>"+data.fromUserName + ": "+data.content+"<br><br>";
		$("#chat .messages").html(html);
		$("#chat .messages").scrollTop( $("#chat .messages")[0].scrollHeight );
	}
	
	$("#chat").on("click",".send",function(){
		var temp = $("#chat").attr("userId");
		if(!toUserId && temp){
			toUserId = temp;
		}
		if(!toUserId){
			alertFail("You can't talk to nobody!");
			return;
		}
		var content = $("#chat textarea").val().trim();
		if(content){
			var message = {
				"content" : content,
				"toUserId" : toUserId
			};
			baseAjax.doAjax(config.URL.SEND,message,function(data){
				showMessage(data.result);
				$("#chat textarea").val("");
			},function(data){
				
			});
		}else{
			alertFail("message can't be empty!");
		}
	});
	
	function fetchMessage(){
		baseAjax.doAjax(config.URL.GET, null, function(data){
			var messageList  = data.result;
			
			if($("#chat .panel-body").css("display")=="none" && messageList.length!=0){
				var text = $("#chat .panel-title span").text();
				var count = text? parseInt(text) : 0;
				count += messageList.length;
				var span = '<span class="badge" style="margin-left: 3px;background-color: #FF5400;">'+count+'</span>';
				var html = "Chatting" + span;
				$("#chat .panel-title").html(html);
			}
			$.each(messageList,function(i, message){				
				showMessage(message);
				toUserId = message.fromUserId;
			});
			setTimeout(fetchMessage,3000);
		}, function(data){
			
		});
	}
	
	$("#chat .panel-body").hide();
	if($("#logout")){
		fetchMessage();
	}
	
	$("#contact").bind("click",function(){
		$("#chat .panel-body").show();
	});
});