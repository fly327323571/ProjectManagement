$(function(){
	var config = {
			URL:{
					PRODUCTSHOW:'',  //商品详细信息页
					LIST:'search/updateData.json'           //列出查到的商品
			}
	};
	new SearchResult(config);
});