function ViewIncomeComponent(){
	var tableRenderer = {// 数据渲染到页面上的函数
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<span>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</span>';
				return html;
			},
			
			orderNumberRenderer : function(data) {
				var html = '<span>' + data.orderNo
						+ '</span>';
				return html;
			},
			priceRenderer:function(data){
				var html='$<span>'+parseFloat(data.realEarn/(1-data.shareRate*0.01)*data.shareRate*0.01).toFixed(2)+'<span>';
				return html;
			},
			
			incomeRenderer:function(data){
				var html='$<span>'+data.toFixed(2)+'<span>';
				return html;
			}
		};
	
	
	
	var config = {
		URL : {//页面所有的URL配置
			
			LIST: 'store/{storeId}/dashboard/income/query'.replace("{storeId}", storeId),
			
		},
		
		tableConfig : {
			rowCSS : ["danger",""],
			renderRowCount : 1,
			metadata : [ {
				name : 'addTime',
				data : 'time',
				visible : true,
				render : tableRenderer.addTimeRender
			}, {
				name : 'orderNumber',// 页面上显示的信息
				data : 'order',//
				visible : true,
				render : tableRenderer.orderNumberRenderer
			} ,{
				name : 'Deal Price',
				data : 'shareRate&realEarn',
				visible : true,
			
				render : tableRenderer.priceRenderer
			}, {
				name : 'Real Income',
				data : 'realEarn',
				visible : true,
				
				render : tableRenderer.incomeRenderer
			}]
		},
	};
	new ViewIncome(config);
}