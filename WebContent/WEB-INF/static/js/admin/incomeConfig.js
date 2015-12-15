$(function(){
	var tableRenderer = {// 数据渲染到页面上的函数
			orderIdRenderer : function(data) {
				if(data==null)
					data = "-";
				var html = '<span> ' + data
						+ '</span>';
				return html;
			},
			addTimeRender : function(data) {
				var d = new Date(data);
				var html = '<b>' + d.getFullYear() + '-' + (d.getMonth() + 1)+'-'
						+ d.getDate() + '</b>';
				return html;
			},
			typeRenderer:function(data){
				var type = "";
				switch(data){
				case 1: type = "Business Income"; break;
				case 2: type = "Advertisement Income"; break;
				}
				var html='<span>'+type+'<span>';
				return html;
			},
			incomeValueRenderer:function(data){
				var html='$<span>'+data.toFixed(2)+'<span>';
				return html;
			},
			descriptionRenderer:function(data){
				var html='<span>'+data+'<span>';
				return html;
			},
		};
	
	
	
	var incomeConfig = {
		URL : {
			LIST: 'admin/income/list',
			MODIFY: 'admin/income/commission/modify'
		},
		
		tableConfig : {
			metadata : [ 
			{
				name : 'id',
				data : 'incomeId',
				visible : false,
				render : null
			} ,
			{
				name : 'order Id',
				data : 'orderId',
				visible : true,
				render : tableRenderer.orderIdRenderer
			} ,
			{
				name : 'Add Time',
				data : 'time',
				visible : true,
				render : tableRenderer.addTimeRender
			}, 
			{
				name : 'Type',
				data : 'type',
				visible : true,
				render : tableRenderer.typeRenderer
			} ,
			{
				name : 'Income Value',
				data : 'incomeValue',
				visible : true,
				render : tableRenderer.incomeValueRenderer
			},
			{
				name : 'Description',
				data : 'description',
				visible : true,
				render : tableRenderer.descriptionRenderer
			}, 
			]
		},
	};
	new income(incomeConfig);
});