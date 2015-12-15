function Comment(config){
	var _config=config;
	
	
	//------------------评论----------------------//
	
	function SubmitComment(){
   
	$("#submit").bind("click",function(){
		 var data={
		    		orderId:$("#orderId").val(),
		    		consistent:$("#consistent").val(),
		    		service:$("#service").val(),
		    		delivery:$("#delivery").val(),
		    		comment:$("#comment").val()
		    };
		 if(!data.comment){
			 alertFail("please write your comment!");
			 return;
		 }
		 if(!data.consistent){
			 alertFail("please mark the consistent degree");
			 return;
		 }
		 if(!data.service){
			 alertFail("please mark the service degree");
			 return;
		 }
		 if(!data.delivery){
			 alertFail("please mark the delivery degree");
			 return;
		 }
		baseAjax.doAjax("business/saveComment.json", data, function(){
			alertSuccess("comment successfully",function(){
				window.opener=null;window.open('','_self');window.close();
			});
		}, function(){
			
		});
	});	
	}
	//---------------点击星星变色----------------//
	
	SubmitComment();
	

	
	 
	//导航栏点击变色
  	$(".comment .result").bind("click",function(){
  		$(".comment .result").css("background","white");
  		$(".comment .result").css("color","#FF5400");
  		$(this).css("background","#FF5400");
  		$(this).css("color","white");
  		var part=$(this).html();
  		if(part=="comments"){
  			$(".comment .pdt-comment").css("display","block");
  			$(".comment .pdt-detail").css("display","none");
  		}else{
  			$(".comment .pdt-comment").css("display","none");
  			$(".comment .pdt-detail").css("display","block");
  		}
  	});
  	
  	 $(".star").click(function(e){
  		    var id=$(this).parent().attr("id");
  			var temp=$(this).parent().find(".star");
  			var number=temp.length;
  			console.log(number);
  			console.log(e.target);
  				for(var i=0;i<number;i++){
  					if(e.target!=temp[i]){
  						$(temp[i]).css("color","#ff5400");//gaiyanse
  					}else{
  					$(this).css("color","#ff5400");
  					for(var j=i+1;j<number;j++){
  						$(temp[j]).css("color","#999");
  					}
  					points=i+1;//i+1即为等级
  				  if(id=='consistentPoint'){
  	  		    	$("#consistent").val(points);
  	  		     }else if(id=='servicePoint'){
  	  		    	$("#service").val(points);
  	  		    	 }else if(id=="deliveryPoint"){
  	  		    	$("#delivery").val(points); 
  	  		     }
  					break;
  					
  				}
  		   
  	}
  	}) ; 
} 	