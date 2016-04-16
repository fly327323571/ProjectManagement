function ViewCommentComponent(){
	var tableRenderer = {// 数据渲染到页面上的函数
			buyerRenderer : function(data){
				return data.userName;
			},
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			productRenderer:function(data){
				var html='<a target="_blank" href="'+config.URL.PRODUCT_SHOW.replace("{productId}",data.commodityNo)+'">'+data.commodityName+'</a>';
				return html;
			},
			commentRenderer:function(data){
				return data;
			},
			replyRenderer : function(data){
				var html = '<textarea></textarea><button style="float: right;"class="btn btn-warning" commentsId="'+data+'">reply</button></br>'+
							'<input type="text" id="reason" style="width:120px;margin-right:6px;"><button type="button" onclick="complain()" id="complain" commentId="'+data+'"  class="btn btn-warning">complain</button>';
				return html;
			},
			complainRenderer:function(data){
				
			},
			rankRenderer:function(data){
				var span = "<span class='glyphicon glyphicon-star star'></span>";
				var html = "";
				for(var i=1;i<=data;i++){
					html = html + span + '\n';
				}
				return html;
			}
		};
	
	
	
	var config = {
		URL : {//页面所有的URL配置
			
			LIST: 'store/{shopNo}/dashboard/comment/query'.replace("{shopNo}", storeId),
			PRODUCT_SHOW : 'product/{shopNo}/productDetail/{productId}.do'.replace("{shopNo}", storeId),
			REPLY : "business/comment/reply/{orderId}.json",
			COMPLAIN:"business/comment/{commentsId}/complain"
		},
		
		tableConfig : {
		
			
			renderRowCount : 1,
			metadata : [ {
				name : 'Id',
				data : 'commentsId',
				visible : true,
				render : null
			}, {
				name : 'subOrderId',// 页面上显示的信息
				data : 'subOrderId',//
				visible : false,
				row : 1,
				render : null
			},{
				name : 'Deal Time',
				data : 'commentsTime',
				visible : true,
				render : tableRenderer.addTimeRender
			},{
				name : 'Buyer',
				data : 'user',
				visible : true,
				render : tableRenderer.buyerRenderer
			},{
				name : 'Rank',
				data : 'rank',
				visible : true,
				render : tableRenderer.rankRenderer
			},{
				name : 'Product',
				data : 'commodity',
				visible : true,
				render : tableRenderer.productRenderer
			}, {
				name : 'Comment',
				data : 'comments',
				visible : true,
				render : tableRenderer.commentRenderer
			},{
				name : "Reply Or Complain",
				data : "commentsId",
				visible : true,
				row : 1,
				render : tableRenderer.replyRenderer
			}]
		},
	};
	new ViewComment(config);
}