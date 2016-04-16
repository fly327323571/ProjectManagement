/**
 * Created by �ѫh on 2016/1/8.
 */
$(function(){
    var config = {
        URL : {
            APPLY_ADS : "store/{shopNo}/applyAd".replace("{shopNo}", storeId),
            SHOW_ADS:"shop/{shopNo}/applyAds/index.do".replace("{shopNo}", storeId)
        }
    };
    new AdApply(config);
});