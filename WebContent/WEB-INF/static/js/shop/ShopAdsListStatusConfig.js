
$(function(){
    var tableRenderer={
    	idRenderer:function(data){
    			return data;
    	},
    	totalPriceRenderer:function(data) {
            return (parseFloat(data).toFixed(2) + '$');
        },
        RemainingRenderer:function(data){
            return data;
        },
        statusRenderer : function(data) {
            var html="";
            switch(data){
                case 0:
                    html += 
                    	'<button class="well" style="padding: 8px 8px;margin-bottom: 0px;">applying</button>';
                    break;
                case 1:html+=
                    '<button class="well" style="padding: 8px 8px;margin-bottom: 0px;">using</button>'
                        break;
                case 2:html+= '<button class="well" style="padding: 8px 8px;margin-bottom: 0px;">Stop</button>'
                default:break;
            }

            return html;
        },
         operationRenderer:function(data){
        	 var html="";
             switch(data){
                 case 1:html+=
                     '<a class="btn btn_color" href="store/{shopNo}/renewAd"><button class="btn btn_color warning" style="padding: 8px 8px;margin-bottom: 0px;">Renew</button></a>'.replace("{shopNo}",storeId);
                         break;
                 case 2: html+='<a class="btn btn_color" href="store/{shopNo}/renewAd"><button  style="padding: 8px 8px;margin-bottom: 0px;">Renew</button></a>'.replace("{shopNo}",storeId);
                 			break;
                 default:break;
             }

             return html;
        }

    };

    var config = {
        URL : {
            APPLY_ADS : "store/{shopNo}/applyAd".replace("{shopNo}", storeId),
            SHOW_ADS:"store/{shopNo}/showAd".replace("{shopNo}",storeId),
            RENEW_ADS:"store/{shopNo}/renewAd".replace("{shopNo}",storeId)
        },
        tableConfig : {
			header : ["Ads Type","Price","Remaining Days","Status","Operation"],//请注意,此处的""字符串不可删除,目的是为了保持列表的列数目一
			renderRowCount : 1,
			metadata : [
			{
				name : 'no',
				data : 'ad_type',
				visible : true,
				row : 1,
				render : tableRenderer.idRenderer
			},{
				name : 'ad_rate',//销量
				data : 'ad_rate',
				visible : true,
				row : 1,
				render : tableRenderer.totalPriceRenderer
			},{
				name : 'days',//页面上显示的信息
				data : 'days',//对应Store.java 实体类的字段
				visible : true,
				row : 1,
				render : tableRenderer.RemainingRenderer
			},{
				name : 'status',
				data : 'status',
				visible : true,
				row : 1,
				render : tableRenderer.statusRenderer
			},
			{
				name : 'operation',
				data : 'status',
				visible : true,
				row : 1,
				render : tableRenderer.operationRenderer
			}]
		}
    };
    new AdList(config);
});