/**
	 * 订单的10种状态
	 * 0 无效
	 * 1 未付款
	 * 2 未发货
	 * 3 用户申请退款
	 * 4 卖家拒绝退款zz
	 * 5 退款
	 * 6 已发货
	 * 7 签收
	 * 8 已评论
	 * 9 终止
	 * private Integer orderId;
	//User层
	private Integer buyerId;
	private String buyerName;
	//Product层
	private Integer productId;
	private String productName;
	private String productImage;
	private Integer saleVolume;
	private Double price;
	private Integer storeId;
	//Store层
	private String shopName;
	private String shopOwner;
	//Order层
	private Integer quantity;
	private Long addTime;//加入订单的时间
	private String tips;
	private Integer status;//订单状态

	 */

$(function() {

	var tableRenderer = {// 数据渲染到页面上的函数
		addTimeRender : function(data) {
			var d = new Date(data);
			var html = "</td><td><b>" +d.getFullYear()+'/'+ (d.getMonth()+1) + "/" + d.getDate() +"</b>";
			return html;
		},
		vendorRenderer : function(data) {
			var html = '<span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;<a href="'
					+ orderListConfig.URL.VENDOR + '">vendor:' + data + '</a>';
			return html;
		},
		orderNumRenderer : function(data) {
			var html = '<em>OrderID:</em> <span id="orderNum">' + data
					+ '</span>';
			return html;
		},
		orderDetailsRenderer : function(data) {
			var html = '<a class="main_color" href="'
					+ orderListConfig.URL.ORDER_DETAILS + data
					+ '" target="_blank">View order details</a>';
			return html;
		},
		productRenderer : function(data) {
			var img = '<img src="' + data.productImage
					+ '" class="product_img">';
			var span = '<span><a class="btn" target="_blank" href="'
					+ orderListConfig.URL.LINK_PRODUCT+data.productId+ '">'
					+ data.productName + '</span>';
			return img + span;
		},
		totalRenderer : function(data) {
			
			var total = data.price * data.quantity;
			var html = '<center>'+total + '</center>';
			return html;
		},
		statusRenderer : function(data) {
			var html = '';
			var status = data.status;
			switch(status){
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
			if (status == 6) {
				var temp = '<br /><b><a class="main_color" href="'
						+ orderListConfig.URL.TRACK_LOGISTICS.replace("{packageNumber}",data.packageNumber)
						+ '" target="_blanket">Track the logistics</a></b>';
				res = html + temp;
			} else {
				res = html;
			}
			
			return res;
		},
		operationRenderer : function(data) {
			var btn_1, btn_2;
			if ((data.status == 2) || (data.status == 6)) {//已付款 未发货 或者 已发货
				var disabled = (data.status == 2)?'disabled="disabled"':'';
				btn_1 = '<a class="btn btn_color confirm_btn" '+disabled+'orderId="'
						+ data.orderId + '" href="javascript:void(0)">Confirm</a>';
				btn_2 = '&nbsp;&nbsp;&nbsp;<a target="_blank" class="refund_btn" href="'
						+ orderListConfig.URL.REFUND.replace("{orderId}",data.orderId)
						+ '">Refund</a>';
			} else if (data.status == 1) {//未付款
				btn_1 = '<a target="_blank" class="btn btn_color pay_btn" href="'
						+ orderListConfig.URL.PAY.replace('{orderId}',data.orderId) + '">Pay</a>';
				btn_2 = '<a class="conceal_btn" orderId="' + data.orderId
						+ '" href="javascript:void(0)">Cancel</a>';
			} else if((data.status == 7)){

				btn_1 = '<a class="btn" target="_blank" href="'
					+ orderListConfig.URL.LINK_PRODUCT+data.productId+ '">Buy Another</a>';
				btn_2 = '<a class="btn comment_btn" target="_blank" href="'
						+ orderListConfig.URL.COMMENT + data.orderId
						+ '" >Go to comment</a>';
			}else if(data.status==8 || data.status==9){
				btn_1 = '<a class="btn" target="_blank" href="'
					+ orderListConfig.URL.LINK_PRODUCT+data.productId+ '">Buy Another</a>';
				btn_2 = '<center></center>';
			}else{
				btn_1 = '<center>--</center>';
				btn_2 = '<center></center>';
			}
			return btn_1 + '<br/>' + btn_2;
		}
	};

	var orderListConfig = {
		URL : {
			LINK_PRODUCT : "product/productDetail/",
			VENDOR : "customer/{orderId}/ShopLink.do",
			PAY : "business/pay/unfinished/{orderId}/index.do",
			CONCEAL : "business/cancelOrder.json?orderId={orderId}",
			CONFIRM : "business/confirmOnReceive/{orderId}.json",
			REFUND : "business/refund/{orderId}/index.do",
			COMMENT : "business/customerComment/",
			ORDER_DETAILS : "business/viewOrderDetails/",
			TRACK_LOGISTICS : "business/logistics.do?packageNumber={packageNumber}",
			LIST : "business/orderHistory.json"
		},
		tableConfig : {
			header : [ "Products", "Status", "Quantity",
					"Current Price", "Total", "" ],// 请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一致
			rowCSS : [ "danger", "" ],
			renderRowCount : 2,
			metadata : [ {
				name : 'Products',
				data : 'addTime',
				visible : true,
				row : 1,
				render : tableRenderer.addTimeRender
			}, {
				name : 'Status',// 页面上显示的信息
				data : 'orderId',//
				visible : true,
				row : 1,
				render : tableRenderer.orderNumRenderer
			}, {
				
				name : 'Quantity',
				data : 'shopOwner',
				visible : true,
				row : 1,
				render : tableRenderer.vendorRenderer
			}, {
				name : 'Current Price',
				data : '',
				visible : true,
				row : 1,
				render : null
			}, {
				name : 'Total',
				data : '',
				visible : true,
				row : 1,
				render : null
			}, {
				name : '',
				data : 'orderId',
				visible : true,
				row : 1,
				render : tableRenderer.orderDetailsRenderer
			}, {
				name : 'Products',
				data : 'productImage & productName&productId',
				visible : true,
				row : 2,
				render : tableRenderer.productRenderer
			}, {
				name : 'Status',
				data : 'status & orderId & packageNumber',
				visible : true,
				row : 2,
				render : tableRenderer.statusRenderer
				
			}, {
				name : 'Quantity',
				data : 'quantity',
				visible : true,
				row : 2,
				render : null
			}, {
				name : 'Current Price',
				data : 'price',
				visible : true,
				row : 2,
				render : null
			}, {
				name : 'Total',
				data : 'price & quantity',
				visible : true,
				row : 2,
				render : tableRenderer.totalRenderer
			}, {
				name : '',
				data : 'status & orderId&productId',
				visible : true,
				row : 2,
				render : tableRenderer.operationRenderer
			} ]
		},
	};

	new customerBuyHistory(orderListConfig);
});