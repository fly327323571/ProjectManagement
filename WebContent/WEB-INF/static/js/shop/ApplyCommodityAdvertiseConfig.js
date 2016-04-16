/**
 * Created by �ѫh on 2016/1/8.
 */
$(function(){
    var config = {
        URL : {
            APPLY_COMMODITY_ADS : "product/{productId}/{shopNo}/applyAd".replace("{shopNo}", storeId),
        }
    };
    new AdApply(config);
});