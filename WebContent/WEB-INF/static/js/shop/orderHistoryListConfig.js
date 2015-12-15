$(function(){
	var tableRenderer = {// 数据渲染到页面上的函数
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			buyerRenderer : function(data) {
				var html = '<span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;'+
				'<a class="contact" userId="'+data.userId+'" href="javascript:void(0)">customer:' 
					+ data.userName + '</a></td><td>';
				return html;
			},
			orderNumberRenderer : function(data) {
				var html = '<em>OrderId:</em> ' + data
						+ '</span></td><td>';
				return html;
			},
			orderDetailsRenderer : function(data) {
				var html = '<a class="main_color" href="'+ orderHistoryListConfig.URL.ORDER_DETAILS.replace("{orderId}",data)+ 
				'" target="_blank">View order details</a>';
				return html;
			},
			totalPriceRenderer:function(data){
				return parseFloat(data).toFixed(2);
			},statusRenderer : function(data) {
				var html = '';
				/**
				 * 订单的10种状态
				 * * 0 无效
	 * 1 未付款
	 * 2 未发货
	 * 3 用户申请退款
	 * 4 卖家拒绝退款
	 * 5 退款
	 * 6 已发货
	 * 7 签收
	 * 8 已评论
	 * 9 终止
				 */
				switch(data){
				case 1:html+='unpaid';break;
				case 2:html+='unshipped';break;
				case 3:html+='refunding';break;
				case 4:html+='refund rejected';break;
				case 5:html+='refunded';break;
				case 6:html+='shipped';break;
				case 7:html+='received';break;
				case 8:html+='commented';break;
				case 9:html+='done';break;
				default:;
				}
				html = '<button class="well" style="padding: 8px 8px;margin-bottom: 0px;">{status}</button>'.replace('{status}', html);
				return html;
			},			
			operationRenderer : function(data) {
				var btn = "";
				
				switch(data.status){
					case 2:btn = '<button class="btn btn_color handleShip" orderId='+ data.mainOrderId + '>ship</button>';break;
					case 3:btn=  '<button class="btn btn_color refund" orderId='+ data.mainOrderId + '>agree</button> <button class="btn btn_color reject_refund" orderId='+ data.mainOrderId + '>reject</button>';
					default:;
				}
				
				return btn;
			},//第二行
			productsRenderer : function(subOrders) {
				
				function renderSubOrder(subOrder){
					var link = orderHistoryListConfig.URL.VIEW_PRODUCT.replace("{productId}",subOrder.productId);
					var html = $("#subOrderTmpl").html().replace("{productImage}",subOrder.img)
						.replace("{link}",link)
						.replace("{productName}",subOrder.productName)
						.replace("{price}",subOrder.realPrice.toFixed(2))
						.replace("{quantity}",subOrder.quantity)
						.replace("{totalPrice}",(subOrder.quantity * subOrder.realPrice).toFixed(2))
						.replace("{message}",subOrder.remark);
					return html;
				}
				var $table = $("<table class='table table-hover'></table>");
				$.each(subOrders,function(i,subOrder){
					var $tr = $(renderSubOrder(subOrder));
					$table.append($tr);
				});
				return $table;
			},
		};
	
	
	
	var orderHistoryListConfig = {
		URL : {//页面所有的URL配置
			
			LIST: "business/listOrderHistory/{storeId}/".replace("{storeId}",$("#storeId").val()),
			SHIP: "business/ship/{orderId}",
			REFUND: "business/refund/{orderId}",
			REJECT_REFUND:"business/rejectHandle/{orderId}",
			ORDER_DETAILS:'business/viewOrderDetails/{orderId}',
			VIEW_PRODUCT:'product/{storeId}/modify/{productId}/index.do'.replace("{storeId}",$("#storeId").val()),
			CHECK_COMENTS: "business/checkcomments"
		},
		
		tableConfig : {
			header : [ "Orders", "", "Price", "Quantity","Total", "Message",""],// 请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一致
			rowCSS : [ "danger", "" ],
			renderRowCount : 2,
			metadata : [ {
				name : 'addTime',
				data : 'addTime',
				visible : true,
				row : 1,
				render : tableRenderer.addTimeRender
			}, {
				name : 'communicate',// 买家
				data : 'userName & userId',
				visible : true,
				row : 1,
				render : tableRenderer.buyerRenderer
			}, {
				name : 'orderNumber',// 页面上显示的信息
				data : 'mainOrderId',//
				visible : true,
				row : 1,
				render : tableRenderer.orderNumberRenderer
			},{
				name : 'Details',
				data : 'mainOrderId',
				visible : true,
				row : 1,
				render : tableRenderer.orderDetailsRenderer
			},{
				name : 'totalPrice',
				data : 'totalPrice',
				visible : true,
				row : 1,
				render : tableRenderer.totalPriceRenderer
			},{
				name : 'Status',
				data : 'status',
				visible : true,
				row : 1,
				render : tableRenderer.statusRenderer
			},{
				name : 'operation',
				data : 'status & mainOrderId',
				visible : true,
				row : 1,
				render : tableRenderer.operationRenderer
			} ,{
				name : "products",
				data : "productsOfOrder",
				visible : true,
				row : 2,
				colspan : 7,
				render : tableRenderer.productsRenderer
			}]
		},
	};
	new orderHistoryList(orderHistoryListConfig);
});