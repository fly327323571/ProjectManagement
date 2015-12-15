$(function(){

	var tableRenderer = {//数据渲染到页面上的函数
			addTimeRender : function(data){
				var d = new Date(data);
				var html = "<b>" +d.getFullYear()+'/'+ d.getMonth() + "/" + d.getDay() +"</b>";
				return html;
			},
			saleVolumeRenderer : function(data){
				var html = "<a>SaleVolume:" + data +"</a>";
				return html;
			},
			productIdRenderer : function(data){
				var html = "<em>ProductID:</em>" + data;
				return html;
			},
			categoryRenderer : function(data){
				var html = '<a class="main_color" href="viewOrderDetails.html " target="_blanket">category:'+data+'</a>';
				return html;
			},
			productRender : function(data){
				var img = '<img src="'+data.defaultImage+'" class="product_img">';
				var span = '<span>'+data.productName+'</span>';
				return img + span;
			},
			operationRenderer : function(productId){
				var btn_1 = '<button class="btn btn_color delete"  productId="'+productId+'">delete</button>';

				var btn_2 = '<a class="btn btn_color" href="'+ config.URL.MODIFY.replace('{productId}',productId)+'"  target="_self">modify</a>';
				return btn_1 + '<br>\n<br>\n' + btn_2;
			}
	};


	var config = {
			URL : {
				ADD : "product/{storeId}/add.json".replace('{storeId}',storeId),
				DELETE : "product/{storeId}/delete.json".replace('{storeId}',storeId),
				MODIFY : "product/{storeId}/modify/{productId}/index.do".replace('{storeId}',storeId),
				LIST : "product/{storeId}/select.json".replace('{storeId}',storeId),
				DELETE_CHECK : "product/{storeId}/delete/check.json".replace('{storeId}',storeId),
			},
			tableConfig : {
				header : ["Products","Price","Quantity","Introduction",""],//请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一致
				rowCSS : ["danger",""],
				renderRowCount : 2,
				metadata : [
				{
					name : 'addTime',
					data : 'addTime',
					visible : true,
					row : 1,
					render : tableRenderer.addTimeRender
				},{
					name : 'saleVolume',//销量
					data : 'saleVolume',
					visible : true,
					row : 1,
					render : tableRenderer.saleVolumeRenderer
				},{
					name : 'id',//页面上显示的信息
					data : 'productId',//对应Store.java 实体类的字段
					visible : true,
					row : 1,
					render : tableRenderer.productIdRenderer
				},{
					name : 'category',
					data : 'type',
					visible : true,
					row  : 1,
					render : tableRenderer.categoryRender
				},{
					name : 'product',
					data : 'defaultImage & productName',
					visible : true,
					row : 2,
					render : tableRenderer.productRender
				},{
					name : 'price',
					data : 'presentPrice',
					visible : true,
					row : 2,
					render : null
				},{
					name : 'amounts',
					data : 'amounts',
					visible : true,
					row : 2,
					render : null
				},{
					name : 'description',
					data : 'description',
					visible : true,
					row : 2,
					render : null
				},{
					name : 'operation',
					data : 'productId',
					visible : true,
					row : 2,
					render : tableRenderer.operationRenderer
				}]
			},//如果本页面被加载在父页面shopManageHomepage的iframe里头,加载此页面的时候最好选中父页面的 manage_products选项
			parentOptionNavigation : 'manage_products'
	};

	new ProductList(config);
});