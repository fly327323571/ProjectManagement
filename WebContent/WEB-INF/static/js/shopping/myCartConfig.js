$(function(){
	var tableRenderer = {//数据渲染到页面上的函数
			addTimeRender : function(data){
				var d = new Date(data);
				var html = "</td><td><b>" +d.getFullYear()+'/'+ (d.getMonth()+1) + "/" + d.getDate() +"</b>";
				return html;
			},
			shopOwnerRenderer:function(data){
				var html='<span class="glyphicon glyphicon-comment sn title_color"></span>&nbsp;<a href="javascript:void(0)">vendor:'+data.shop.shopOwner.userName+' </a></td><td>';
				return html;
			},
			saleVolumeRenderer:function(data){
				var html='<em>saleVolume:</em>'+data.saleVolumn+'</td><td>';
				return html;
			},
			viewDetailRenderer:function(data){
				var url = config.URL.VIEW_PRODUCT_DETAIL.replace("{productId}",data.commodityNo);
				var html='<a class="main_color viewProductDetail" href="'+url+'" target="_blanket">View product details</a>';
				return html;
			},
			
			selectRenderer:function(){
				var html='<input type = "checkbox" class="select">';
				return html;
			},
			productRender : function(data){
				var img = '<img src="'+data.commodityImg+'" class="product_img">';
				var span = '<span>'+data.commodityName+'</span>';
				return img + '<br />'+ span;
			},
			shopNameRenderer:function(data){
				var link = config.URL.VISIT_SHOP.replace("{storeId}",data.shop.shopNo);
				var html='<a href='+link+'>'+'<b>'+data.shop.shopName+'</b></a>';
				return html;
			},
			unitPriceRenderer : function(data){
				var html = '$<span class="unitPrice">' +parseFloat(data.price)/parseInt(data.commodityNum)+'</span>';
				return html;
			},
			
			quantityRenderer : function(data){
				var number=data.commodityNum?data.commodityNum:1;
				var cartid = data.id;
				var html =
	              ' <button class="minus_number_button" style = "width:20px" '+ (number==1?'disabled="disabled"':'')+'>-</button>'+
	             '<input class="product_number_input" type="text" style="width:40px;text-align:center" cartid="'+cartid+'" value="'+number+'">'+ 
	              '<button class="add_number_button" style="width:20px">+</button>';
				return html;
			},
			
			priceRenderer : function(data){
				//var price=$("#unitPrice").html()*$("#product_number_input").val();
				var price=data.price*data.commodityNum;
				var html = '$<span class="price">' + price+'</span>';
				return html;
			},
			
			operationRenderer : function(data){
				var btn= '<button class="btn btn_color delete"  cartId="'+data+'">delete</button>';
				return btn;
			},
			statusRenderer:function(data){
				var html='<span class="status">'+data+'</span>';
				return html;
			},
			orderIdRenderer:function(data){
				var html='<span class="orderId">'+data+'</span>';
				return html;
			}
	};
	
	
	var config = {
			URL : {
				VIEW_PRODUCT_DETAIL : 'product/productDetail/{productId}',
				DELETE : 'cart/delete.json',
				CHECK_OUT: 'cart/checkOut.json',
				UPDATE : 'cart/modify.json',
				LIST :'cart/list.json',
				ORDER_CONFIRM : 'customer/confirmOrders',
				VISIT_SHOP : "business/market/{storeId}/shopHomePage.do"
			},
			
			tableConfig : {
				header : ["","Product Detail","Shop Name","Unit Price","Quantity","Total Price",""],//请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一致
				rowCSS : ["danger",""],
				renderRowCount : 2,
				//?????????????是否要根据传回来的数据顺序调整顺序
				metadata : [{
					name : '',
					data : null,
					visible : true,
					row : 1,
					render : null
				},
				{
					name : 'cartId',
					data : 'id', //根据不同的状态，显示出不同的信息
					visible : false,
					row : 1,
					render : tableRenderer.orderIdRenderer
				},{
					name : 'addTime', //添加到购物车时间
					data : 'addTime', //？？？？？？？？？？？？？？？？？？？？？？？？？数据库里寸的也是addTime，
					visible : true,
					row : 1,
					render : tableRenderer.addTimeRender
				},{
					name : 'shopOwner',//该商品所属店铺店主的名字
					data : 'commodity',
					visible : true,
					row : 1,
					render : tableRenderer.shopOwnerRenderer
				},{
					name : '',
					data : '',
					visible : true,
					row : 1,
					render : null
				},{
					name : 'saleVolume',//
					data : 'commodity',//对应Store.java 实体类的字段
					visible : true,
					row : 1,
					render : tableRenderer.saleVolumeRenderer
				},{
					name : 'viewProductDetail',
					data : "commodity",
					visible : true,
					row  : 1,
					render : tableRenderer.viewDetailRenderer
				},{
					name : 'select',
					data : null,
					visible : true,
					row : 2,
					render : tableRenderer.selectRenderer
				},{
					name : 'product',
					data : 'commodity',
					visible : true,
					row : 2,
					render : tableRenderer.productRender
				},{
					name : 'shopName',
					data : 'commodity',
					visible : true,
					row : 2,
					render : tableRenderer.shopNameRenderer
				},{
					name : 'unitPrice',
					data : 'price&commodityNum',
					visible : true,
					row : 2,
					render : tableRenderer.unitPriceRenderer
				},{
					name : 'number',
					data : 'id&commodityNum',
					visible : true,
					row : 2,
					render : tableRenderer.quantityRenderer
				},{
					name : 'price',
					data : 'price&commodityNum',
					visible : true,
					row : 2,
					render : tableRenderer.priceRenderer
				},{
					name : 'operation',
					data : 'id',
					visible : true,
					row : 2,
					render : tableRenderer.operationRenderer
				},{
					name : 'status',
					data : 'status', //根据不同的状态，显示出不同的信息
					visible : false,
					row : 2,
					render : tableRenderer.statusRenderer
				}]
			}
			
	};
	new MyCart(config);
});