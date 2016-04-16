/**
 * Created by �ѫh on 2016/1/8.
 */
function AdApply(config){
    var _config = config;

    $("#rateDay").change(function(){
        var p1=$(this).children('option:selected').val();
        if(p1!=-1){
            $('#price').html("$"+p1*30*20);
        }
    });

    $("#submit").bind("click",function(){
    var data={
        totalPrice:$('#price').html()
    }
        baseAjax.doAjax(_config.URL.APPLY_ADS,data , function(data){
            alertSuccess("advertisement applying!",function(){
                window.location.href = _config.URL.SHOW_ADS;
            });
        }, function(data){
            alertFail(data.result.message);
        });
    });

}