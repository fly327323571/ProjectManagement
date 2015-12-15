function ViewIncomeComponent(){
	var tableRenderer = {// 数据渲染到页面上的函数
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			
			orderNumberRenderer : function(data) {
				var html = '<span><em>OrderNumber:</em> ' + data
						+ '</span>';
				return html;
			},
			priceRenderer:function(data){
				var html='$<span>'+parseFloat(data).toFixed(2)+'<span>';
				return html;
			},
			
			incomeRenderer:function(data){
				var html='$<span>'+data.toFixed(2)+'<span>';
				return html;
			},
			orderIdRender:function(data){
				return data;
			}
		};
	
	
	
	var config = {
		URL : {//页面所有的URL配置
			
			LIST: 'store/{storeId}/dashboard/income/query.json'.replace("{storeId}", storeId),
			
		},
		
		tableConfig : {
		
			
			renderRowCount : 1,
			metadata : [ {
				name : 'addTime',
				data : 'time',
				visible : true,
				
				render : tableRenderer.addTimeRender
			}, {
				name : 'orderNumber',// 页面上显示的信息
				data : 'orderId',//
				visible : true,
				row : 1,
				render : tableRenderer.orderNumberRenderer
			} ,{
				name : 'orderId',
				data : 'orderId',
				visible : false,
				
				render : tableRenderer.orderIdRenderer
			},{
				name : 'Deal Price',
				data : 'description',
				visible : true,
			
				render : tableRenderer.priceRenderer
			}, {
				name : 'Real Income',
				data : 'incomeValue',
				visible : true,
				
				render : tableRenderer.incomeRenderer
			}]
		},
	};
	new ViewIncome(config);
}