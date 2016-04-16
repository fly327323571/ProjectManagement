$(function(){
	var tableRenderer = {// 数据渲染到页面上的函数
			addTimeRender : function(data) {
				var d = new Date(data.addTime);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			buyerRenderer : function(data) {
				var html = '<span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;'+
				'<a class="contact" userId="'+data.buyer.Id+'" href="javascript:void(0)">customer:' 
					+ data.buyer.userName + '</a></td><td>';
				return html;
			},
			orderNumberRenderer : function(data) {
				var html = '<em>OrderNo:</em> ' + data.orderNo
						+ '</span></td><td>';
				return html;
			},
			orderDetailsRenderer : function(data) {
				var html = '<a class="main_color" href="'+ orderHistoryListConfig.URL.ORDER_DETAILS.replace("{orderId}",data.orderNo)+ 
				'" target="_blank">View order details</a>';
				return html;
			},
			totalPriceRenderer:function(data){
				return (parseFloat(data.orderPrice).toFixed(2)+'$');
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
				switch(data.state){
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
				
				switch(data.deliveryStatus){
					case 2:btn = '<button class="btn btn_color handleShip" orderId='+ data.orderNo + '>ship</button>';break;
					case 3:btn=  '<button class="btn btn_color refund" orderId='+ data.orderNo + '>agree</button> <button class="btn btn_color reject_refund" orderId='+ data.orderNo + '>reject</button>';
					default:;
				}
				
				return btn;
			},//第二行
			productsRenderer : function(subOrders) {
				
				function renderSubOrder(subOrder){
					var link = orderHistoryListConfig.URL.VIEW_PRODUCT.replace("{productId}",subOrder.commodityNo);
					var html = $("#subOrderTmpl").html().replace("{productImage}",subOrder.commodityImg)
						.replace("{link}",link)
						.replace("{productName}",subOrder.commodityName)
						.replace("{price}",(subOrder.commodityPrice.toFixed(2)+'$'))
						.replace("{quantity}",subOrder.commodityCount)
						.replace("{totalPrice}",((subOrder.commodityCount * subOrder.commodityPrice).toFixed(2)+'$'))
						.replace("{message}",subOrder.message);
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
			
			LIST: "business/listOrderHistory/{shopNo}/".replace("{shopNo}",$("#storeId").val()),
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
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.addTimeRender
			}, {
				name : 'communicate',// 买家
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.buyerRenderer
			}, {
				name : 'orderNumber',// 页面上显示的信息
				data : 'order',//
				visible : true,
				row : 1,
				render : tableRenderer.orderNumberRenderer
			},{
				name : 'Details',
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.orderDetailsRenderer
			},{
				name : 'totalPrice',
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.totalPriceRenderer
			},{
				name : 'Status',
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.statusRenderer
			},{
				name : 'operation',
				data : 'order',
				visible : true,
				row : 1,
				render : tableRenderer.operationRenderer
			} ,{
				name : "products",
				data : "orderDetails",
				visible : true,
				row : 2,
				colspan : 7,
				render : tableRenderer.productsRenderer
			}]
		},
	};
	new orderHistoryList(orderHistoryListConfig);
});