/**
 * Created by �ѫh on 2016/1/8.
 */
function AdApply(config){
    var _config = config;

    $("#rateDay").change(function(){
        var p1=$(this).children('option:selected').val();
        if(p1!=-1){
            $('#price').html("$"+p1*30*10);
        }
    });

    $("#submit").bind("click",function(){
    	var productId= $("#product").children('option:selected').val();
    	var data={
    			totalPrice:$('#price').html()
    	}
        baseAjax.doAjax(_config.URL.APPLY_COMMODITY_ADS.replace('{productId}',productId),data , function(data){
            alertSuccess("advertisement applying!",function(){
                window.location.href ="shop/"+storeId+"/applyAds/index.do";
            });
        }, function(data){
            alertFail(data.result.message);
        });
    });

}