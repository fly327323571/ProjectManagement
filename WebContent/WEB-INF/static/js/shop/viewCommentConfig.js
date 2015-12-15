function ViewCommentComponent(){
	var tableRenderer = {// 数据渲染到页面上的函数
			buyerRenderer : function(data){
				return data.buyerName;
			},
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			productRenderer:function(data){
				var html='<a target="_blank" href="'+config.URL.PRODUCT_SHOW.replace("{productId}",data.productId)+'">'+data.productName+'</a>';
				return html;
			},
			commentRenderer:function(data){
				return data;
			},
			replyRenderer : function(data){
				var html = '<textarea></textarea><button style="float: right;"class="btn btn-warning" orderId="'+data+'">reply</button>';
				return html;
			}
		};
	
	
	
	var config = {
		URL : {//页面所有的URL配置
			
			LIST: 'store/{storeId}/dashboard/comment/query.json'.replace("{storeId}", storeId),
			PRODUCT_SHOW : 'product/productDetail/{productId}.json',
			REPLY : "business/comment/reply/{orderId}.json"
		},
		
		tableConfig : {
		
			
			renderRowCount : 1,
			metadata : [ {
				name : 'Order Id',
				data : 'orderId',
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
				data : 'time',
				visible : true,
				render : tableRenderer.addTimeRender
			},{
				name : 'Buyer',
				data : 'buyerId & buyerName',
				visible : true,
				render : tableRenderer.buyerRenderer
			},{
				name : 'Product',
				data : 'productId & productName',
				visible : true,
				render : tableRenderer.productRenderer
			}, {
				name : 'Comment',
				data : 'comment',
				visible : true,
				render : tableRenderer.commentRenderer
			},{
				name : "Reply",
				data : "orderId",
				visible : true,
				render : tableRenderer.replyRenderer
			}]
		},
	};
	new ViewComment(config);
}