$(function(){
	var tableRenderer = {//数据渲染到页面上的函数
			addTimeRender : function(data){
				var d = new Date(data);
				var html = "</td><td><b>" +d.getFullYear()+'/'+ (d.getMonth()+1) + "/" + d.getDate() +"</b>";
				return html;
			},
			shopOwnerRenderer:function(data){
				var html='<span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;<a href="javascript(0)">vendor:'+data+' </a></td><td>';
				return html;
			},
			viewDetailRenderer:function(){
				var html='<a class="main_color viewProductDetail" href="javascript(0)" target="_blanket">View order details</a>';
				return html;
			},

			productRender : function(data){
				var img = '<img src="'+data.productImage+'" class="product_img">';
				var span = '<span>'+data.productName+'</span>';
				return img + '<br />'+ span;
			},
			shopNameRenderer:function(data){
				var html='<b>'+data+'</b>';
				return html;
			},
			unitPriceRenderer : function(data){
				var html = '$<span class="unitPrice">' + data+'</span>';
				return html;
			},
			
			quantityRenderer : function(data){
				var number=data?data:1;
				var html =
	             '<input readOnly="true" class="product_number_input" type="text" style="width:40px;text-align:center" value="'+number+'">';
				return html;
			},
			
			priceRenderer : function(data){
				//var price=$("#unitPrice").html()*$("#product_number_input").val();
				var price=data.price * data.quantity;
				var html = '$<span class="price">' + price+'</span>';
				return html;
			},
			
			messageRenderer : function(){
				var html= '<textarea rows="3" class="form-control" placeholder="You can leave your special requirement to the shopOwner."></textarea>';
				return html;
			},
			/*statusRenderer:function(data){
				var html='<span class="status">'+data+'</span>';
				return html;
			},*/
			orderIdRenderer:function(data){
				var html='<span class="orderId">'+data+'</span>';
				return html;
			}
	};
	
	var config = {
			URL : {
				VIEW_ORDER_DETAIL : '',
				SUBMIT:'business/orderConfirm.json', //提交订单
				PAY: 'business/pay/index.do',//转向付款页面
				LIST :'business/orderToPay.json',
				LOAD_PROVINCE : "province.json",
				LOAD_CITY : "{province}/cities.json",
				ADD_ADDRESS : 'user/addAddress.json',
				UPDATE_ADDRESS : "user/modifyAddress.json",
				GET_ADDRESS : "user/getAddress.json",
				DELETE_ADDRESS : "user/deleteAddress.json?addressId={addressId}",
			},
			
			tableConfig : {
				header : ["Product Detail","Shop Name","Unit Price","Number","Price","Message to Seller"],//请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一致
				rowCSS : ["danger",""],
				renderRowCount : 2,
				metadata : [
				{
					name : 'addTime', //添加到购物车时间
					data : 'addTime', //？？？？？？？？？？？？？？？？？？？？？？？？？数据库里寸的也是addTime，
					visible : true,
					row : 1,
					render : tableRenderer.addTimeRender
				},{
					name : 'shopOwner',//该商品所属店铺店主的名字
					data : 'shopOwner',
					visible : true,
					row : 1,
					render : tableRenderer.shopOwnerRenderer
				},{
					name : 'viewOrderDetail',
					data : null,
					visible : true,
					row  : 1,
					render : tableRenderer.viewDetailRenderer
				},{
					name : 'product',
					data : 'productImage & productName',
					visible : true,
					row : 2,
					render : tableRenderer.productRender
				},{
					name : 'shopName',
					data : 'shopName',
					visible : true,
					row : 2,
					render : tableRenderer.shopNameRenderer
				},{
					name : 'unitPrice',
					data : 'price',
					visible : true,
					row : 2,
					render : tableRenderer.unitPriceRenderer
				},{
					name : 'number',
					data : 'quantity',
					visible : true,
					row : 2,
					render : tableRenderer.quantityRenderer
				},{
					name : 'price',
					data : 'price & quantity',
					visible : true,
					row : 2,
					render : tableRenderer.priceRenderer
				},{
					name : 'message',
					data : 'message',
					visible : true,
					row : 2,
					render : tableRenderer.messageRenderer
				},{
					name : 'productId',
					data : 'productId', //根据不同的状态，显示出不同的信息
					visible : false,
					row : 2,
					render : tableRenderer.orderIdRenderer
				}]
			},
			validateConfig : [{
				id : 'consignee',
				name : 'consignee'
			},{
				id : 'province',
				name : 'province',
				remind : "please select your province"
			},{
				id : 'city',
				name : 'city',
				remind : "please select your city"
			},{
				id : 'address',
				name : 'address'
			},{
				id : 'phoneNumber',
				name : 'phone number'
			}]
			
	};
	new ConfirmOrder(config);
});