function AdList(config){
    var _config = config;
    var table = new Table($("#shopAdsList"),_config.tableConfig);
    var _orderList=null;
    var pageSize = 5;
	var curPageIdx = 1;
    
    function loadData(){
        var data={
        	data:"test"
        };
        baseAjax.doAjax(_config.URL.SHOW_ADS, data, function(rs){

            var page = rs.result;
            _orderList = page;
            table.loadData(_orderList);

        }, function(data){

        });
    }
    loadData();

}