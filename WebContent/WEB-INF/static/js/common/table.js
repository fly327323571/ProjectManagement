function Table($table ,tableConfig){
	
	var _tableConfig = tableConfig;
	var _$table = $table;
	var _columnNumber;
	
	function _init(){
		
		var $tr = $("<tr></tr>");
		
		if(_tableConfig.header){
			$.each(_tableConfig.header,function(i, head){
				var $th = $("<th></th>");
				$th.html(head);
				$tr.append($th);
			});
		}else{
			$.each(_tableConfig.metadata, function (i, head){
				var $th = $("<th></th>");
				$th.html(head.name);
				if(!head.visible){
					$th.hide();
				}
				$tr.append($th);
			});
		}
		var $thread = $("<thead></thead>");
		$thread.append($tr);
		_$table.append($thread);
		var $tbody = $("<tbody></tbody>");
		_$table.append($tbody);
		
		_columnNumber = _tableConfig.header? _tableConfig.header.length:_tableConfig.metadata.length;
	}
	
	function _loadData(data){
		var $tbody = _$table.find("tbody");
		$tbody.children().remove();
		
		var rowCount = _tableConfig.renderRowCount? _tableConfig.renderRowCount : 1;
		
		$.each(data,function(i, item){
			for(var rowIdx = 1;rowIdx <= rowCount; rowIdx++){
				
				var $tr = $("<tr></tr>");
				
				//assign the row css style
				var rowCSS = _tableConfig.rowCSS ? _tableConfig.rowCSS[rowIdx-1] : undefined;
				if(rowCSS){
					$tr.attr('class',rowCSS);
				}
				
				var td_show = 0;
				$.each(_tableConfig.metadata, function (j, head){

					if(head.row && head.row != rowIdx) 
						return;

					var $td = $("<td></td>");
					$td.attr('class', 't_' + head.name.replace(' ','_'));
					if(head.colspan){
						$td.attr("colspan",head.colspan);
					}
					//get the data and wrap it
					var dataIndexes = head.data?head.data.split('&'):[];
					var data;
					if(dataIndexes.length>1){
						var wrapData = {};
						$.each(dataIndexes, function(k, dataIndex){
							var index = dataIndex.trim();
							wrapData[index] = item[index];
						});
						data = wrapData;
					}else{
						data = item[dataIndexes[0]];
					}

					//render the data
					if(head.render){
						var html = head.render(data);
						$td.html(html);
					}else{
						$td.html(data);
					}

					$tr.append($td);
					if(!head.visible){//set visibility
						$td.hide();
					}else{
						if(!head.colspan){
							td_show++;
						}
						else{
							td_show+= head.colspan;
						}
					}
				});
				
				if(td_show < _columnNumber){
					for(var i = td_show + 1;i <= _columnNumber;i++){
						var $td = "<td></td>";
						$tr.append($td);
					}
				}
				$tbody.append($tr);
			} 
			
		});
		//添加空的td来补齐
		if($tbody.children().length==0){
			$tbody.append("<tr><td colspan='" + _columnNumber + "'class='no_record' align='center'>no record</td></tr>");
		}
	}
		
	_init();
	Table.prototype.loadData = _loadData;
}