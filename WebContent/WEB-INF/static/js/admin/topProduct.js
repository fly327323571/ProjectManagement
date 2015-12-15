function topProduct(topProductConfig){
	
	//--------------initialize------------------------//
	var _topProductConfig = topProductConfig;
	var table = new Table($("#topProduct-list"),_topProductConfig.tableConfig);
	var _adverList = null;
	var pageSize = 5;
	var curPageIdx = 1;
	var logo_path="";
	var status ="All";
	
	//----------------绑定事件--------------------------//
	function changeCSS(obj)
	{
		$("#nav-select li").attr("class","");
		$(obj).addClass("nav-active");
	}
	
	$("#status_choice .status_item").bind("click",function(){
		status = $(this).text();
		$("#status").text(status);
		loadData();
	});
	
	$("#search").bind("click",function(){
		loadData();
	});
	
	function bindDeleteEvent(){
		$(".delete_btn").bind("click",function(){
			var adId = $(this).attr("adId");
			baseAjax.doAjax(_topProductConfig.URL.DELETE.replace("{adId}", adId), null, function(rs){
				if(_adverList.length==1 && curPageIdx>1){//如果当前页面只剩下一项，删掉后就必须跳回前一个页面
					curPageIdx--;
				}
				loadData();
			},function(rs){
				$("#dialog .modal-body p").html("System is busy,please try later!");
				$("#dialog .modal-footer #operate").hide();
				$("#dialog").modal();
			});
		});
	}	
	
	//-----------------常用函数------------------------//

	function getColumnFilters(){
		var columnFilters = [];
		if(status!="All"){
			columnFilters.push({
				"name" : 'status_str',
				"filterType" : 'EQUAL',
				"value" : [status]
			});
		}
		var productName = $("#product-name").val();
		if(productName){
			columnFilters.push({
				"name" : 'productName',
				"filterType" : 'CONTAIN',
				"value" : [productName]
			});
		}
		return columnFilters;
	}
	
	function getQueryParam(){
		var columnFilters = getColumnFilters();
		//过滤条件
		var queryParam = {
				"page" : {
					"pageIndex" : curPageIdx,
					"pageSize" : pageSize
				},
				"columnFilters" : columnFilters,
		};
		return queryParam;
	}
	
	function pageOnChange(clickedPageIdx){
		curPageIdx = clickedPageIdx;
		loadData();
	}
	
	
	var detailDialog = {
			title : 'Add Product Advertisement',//对话框的标题
			renderer : function(){//渲染函数 返回一段html
				var html = $("#detailTmpl").html();
				return html;
			},
			operation : [{
				id : 'ok',
				name : 'Add',
				css : 'btn-primary dialog-ok',
				callback : function(){
					var data={
							title : $("#title").val(),
							storeName :$("#store-name").val(),
							productName :$("#productad-name").val(),
							logo : logo_path
					};
					baseAjax.doAjax(_topProductConfig.URL.ADD,data,function(data){
						loadData();
					},function(data){
						alertFail(data.result.message);
						loadData();
					});
				}
				},{
				id : 'cancel',
				name: 'Cancel',
				css : 'btn-primary dialog-ok',
				data_dismiss : true
				}],
			data : {}
		};
	
	$("body").on("click",".add-product",function(){
		$("#dialog").modal(detailDialog);
	});
	
	function bindUploadFileEvent(){
		//上传文件
		$(document).on("change","#logo",function(){
			// 开始上传文件时显示一个图片
	        $("#uploading").show();
	        $("#upload_mark").hide();
			baseAjax.ajaxFileUpload('upload/fileUpload','logo',function(data){
				console.log(data);
	            var $mark = $("#upload_mark");
	            $mark.attr('class','glyphicon glyphicon-ok mark_OK');
	        	$mark.show();
	        	logo_path = data.path;
	            $("#uploading").hide();
	            $("#_shopImage img").fadeOut('1000',function(){
	            	$("#_shopImage img").attr('src',data.path);
		            $("#_shopImage img").fadeIn('1000');
	            });
	            bindUploadFileEvent();
			},function(data){
				var $mark = $("#upload_mark");
				$mark.attr('class','glyphicon glyphicon-remove mark_ERROR');
	        	$mark.show();
	            $("#uploading").hide();
	            bindUploadFileEvent();
			});
		});
	}
	bindUploadFileEvent();
	
	function loadData(){
		var queryParam = getQueryParam();
		baseAjax.doAjax(_topProductConfig.URL.LIST, queryParam, function(rs){
			var page = rs.result;
			_adverList = page.data;
			table.loadData(_adverList);
			if(_adverList && _adverList.length!=0){
				$("#pagination").paginate({
					count 		: page.totalPageCount,//10,
					start 		: curPageIdx,
					display     : 5,
					border					: true,
					border_color			: '#ddd',
					text_color  			: '#428bca',
					background_color    	: '#fff',	
					border_hover_color		: '#ddd',
					text_hover_color  		: '#428bca',
					background_hover_color	: '#eee', 
					images					: false,
					mouse					: 'press',
					onChange				: pageOnChange
				});
			}
			bindDeleteEvent();
		}, function(data){
			
		});
	}
	
	loadData();
	
	$("body").on("change",".modal-body .productId",function(){
		var productId = parseInt($(this).val());
		if(Number.isNaN(productId) || productId<1){
			alertFail("product id is invalid");
			return;
		}
		baseAjax.doAjax(_topProductConfig.URL.FETCH.replace("{productId}",productId),null,function(data){
			var detail = data.result;
			if(!detail){
				alertFail("This product does not exist");
				return;
			}
			$("#store-name").val(detail.store.storeName);			
			$("#productad-name").val(detail.product.productName);
			$("#_shopImage img").attr("src",detail.product.defaultImage);
		},function(data){
			alertFail("Fail to fetch the product information");
		});
	});
}