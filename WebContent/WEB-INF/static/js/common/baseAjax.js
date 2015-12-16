//ajax封装库

function getCookie(c_name) { 
         if (document.cookie.length > 0) { 
             c_start = document.cookie.indexOf(c_name + "="); 
             if (c_start != -1) { 
                 c_start = c_start + c_name.length + 1; 
                 c_end = document.cookie.indexOf(";", c_start); 
                 if (c_end == -1) c_end = document.cookie.length; 
                 return (document.cookie.substring(c_start, c_end)); 
             } 
         } 
         return "" ;
     }

 

     function setCookie(c_name, value, expiredays) { 
         var exdate = new Date(); 
         exdate.setDate(exdate.getDate() + expiredays); 
         document.cookie = c_name + "=" + value + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/"; 
         
     }

 

     function checkCookie() { 
         master_headertop_narrow_show = getCookie('master_headertop_narrow_show') ;
         alert(master_headertop_narrow_show); ////// 
         if (master_headertop_narrow_show != null && master_headertop_narrow_show != "") { 
             if (master_headertop_narrow_show == 'true') { 
                 var master_headertop = document.getElementById('master_headertop'); 
                 if (master_headertop) 
                     master_headertop.style.display = 'none'; 
                 var master_headertop_narrow = document.getElementById('master_headertop_narrow'); 
                 if (master_headertop_narrow) 
                     master_headertop_narrow.style.display = 'block';

             } 
         } 
     }

var baseAjax = {
	doAjax : function(_url, _data, _success, _fail){
		$.ajax({
			type : "post",
			url : _url,
			dataType : "json",
//			contentType: "application/json",
			data :  jQuery.parseJSON(JSON.stringify(_data)),
			success : function(obj){
				var response = obj.result;
				if(response.code >= 0){
					_success(response);
				}else if(response.code == -5){
					setCookie( "redirectUrl",location.href);
	                location.href = '/ParknShop/user/login.html';
	                if(window.parent.location.href != location.href){
	                	//如果本窗口是装载在父窗口的iframe里头,父窗口调整到登陆
	                	window.parent.location = location.href;
	                }
				}else{
					_fail(response);
				}
			},
			error : function(o){
				console.log("ERROR:"+o);
			}
		});
	},
	ajaxFileUpload : function(URL,elementId,_success,_fail) {
        var elementIds=["flag"]; //flag为id、name属性名
        $.ajaxFileUpload({
            url: URL, 
            type: 'post',
            secureuri: false, //一般设置为false
            fileElementId: elementId, // 上传文件的id、name属性名
            dataType: 'json', //返回值类型，一般设置为json、application/json
            elementIds: elementIds, //传递参数到服务器
            success: function(data, status){  
            	if(data.code==0){
                	_success(data);
                }else if(data.code==-1){
                	_fail(data);
                }
            },
            error: function(data, status, e){ 
            	_fail(data);
            }
        });
    }
};

