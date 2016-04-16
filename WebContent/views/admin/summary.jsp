<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>summary</title>
<script type="text/javascript" src="/ParknShop/static/js/common/jquery-1.11.1.js"></script>

<style type="text/css">
.tabs-header li.on {
            background-color: #e3e3e3;
    }
    .tabs-con {
            width: 90%;
            height: 400px;
            border:1px solid #ccc;
            margin-top:10px;
            display: none;
    }
    .tabs-header li {
            border: 1px solid #ccc;
            border-radius: 4px 4px 0 0;
            cursor: pointer;
            display: inline;
            padding: 10px 15px;
            margin-left:-8px;
    }
    .tabs-header {
            list-style: none;
            padding: 0;
            margin:5px 0 0 8px;
    }
    .add_item td{
    	text-align:left;
    	padding-top:5px;
    }
</style>
<script src="/ParknShop/static/js/admin/dist/echarts.js"></script>



<script type="text/javascript">
	function chart(){
	require.config({
		paths:{
			echarts:"/ParknShop/static/js/admin/dist"
		}
	});
	require([
	         	'echarts',
	         	'echarts/chart/pie',
	         	'echarts/chart/funnel'
	         ],
	         function (ec){
				var myChart = ec.init(document.getElementById('div1'));
				var option = {
						title:{
							text:'order state chart',
							x:'center'
						},
						legend:{
							orient:'vertical',
							x:'left',
							data:['non-payment','not recieving','recieved']
						},
						toolbox:{
							show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {
                                    show: true,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                },
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
						},
						calculable : true,
						series:[{
							name:"type",
                            type:"pie",
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                  {value:${OrderNonPayment},name:"non-payment"},
                                  {value:${OrderNotRecieving},name:"not recieving"},
                                  {value:${OrderRecieved},name:"recieved"}
                                  
                                  ]
							}
						    ]
				};
				myChart.setOption(option);
			}
	         );

//pay type
	require([
	         	'echarts',
	         	'echarts/chart/pie',
	         	'echarts/chart/funnel'
	         ],
	         function (ec){
				var myChart = ec.init(document.getElementById('div2'));
				var option = {
						title:{
							text:'pay type chart',
							x:'center'
						},
						legend:{
							orient:'vertical',
							x:'left',
							data:['online payment','cash on delivery']
						},
						toolbox:{
							show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {
                                    show: true,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                },
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
						},
						calculable : true,
						series:[{
							name:"type",
                            type:"pie",
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                  {value:${OrderOnlinePayment},name:"online payment"},
                                  {value:${OrderCashOnDelivery},name:"cash on delivery"}
                                  ]
							}
						    ]
				};
				myChart.setOption(option);
			}
	         );
// commodity category
	require([
	         	'echarts',
	         	'echarts/chart/pie',
	         	'echarts/chart/funnel'
	         ],
	         function (ec){
				var myChart = ec.init(document.getElementById('div3'));
				var option = {
						title:{
							text:'commodity category chart',
							x:'center'
						},
						legend:{
							orient:'vertical',
							x:'left',
							data:['food','clothes']
						},
						toolbox:{
							show : true,
                         feature : {
                             dataView : {show: true, readOnly: false},
                             magicType : {
                                 show: true,
                                 type: ['pie', 'funnel'],
                                 option: {
                                     funnel: {
                                         x: '25%',
                                         width: '50%',
                                         funnelAlign: 'left',
                                         max: 1548
                                     }
                                 }
                             },
                             restore : {show: true},
                             saveAsImage : {show: true}
                         }
						},
						calculable : true,
						series:[{
							name:"type",
                         type:"pie",
                         radius : '55%',
                         center: ['50%', '60%'],
                         data:[
                               {value:${CommodityOfFood},name:"food"},
                               {value:${CommodityOfClothes},name:"clothes"}
                               ]
							}
						    ]
				};
				myChart.setOption(option);
			}
	         );
//shop state
	require([
	         	'echarts',
	         	'echarts/chart/pie',
	         	'echarts/chart/funnel'
	         ],
	         function (ec){
				var myChart = ec.init(document.getElementById('div4'));
				var option = {
						title:{
							text:'shop state chart',
							x:'center'
						},
						legend:{
							orient:'vertical',
							x:'left',
							data:['applyed','apply failure','shutdown','warning']
						},
						toolbox:{
							show : true,
                      feature : {
                          dataView : {show: true, readOnly: false},
                          magicType : {
                              show: true,
                              type: ['pie', 'funnel'],
                              option: {
                                  funnel: {
                                      x: '25%',
                                      width: '50%',
                                      funnelAlign: 'left',
                                      max: 1548
                                  }
                              }
                          },
                          restore : {show: true},
                          saveAsImage : {show: true}
                      }
						},
						calculable : true,
						series:[{
							name:"type",
                      type:"pie",
                      radius : '55%',
                      center: ['50%', '60%'],
                      data:[
                            {value:${ShopApplyed},name:"applyed"},
                            {value:${ShopApplyFailure},name:"apply failure"},
                            {value:${ShopShutdown},name:"shutdown"},
                            {value:${ShopWarning},name:"warning"}
                            ]
							}
						    ]
				};
				myChart.setOption(option);
			}
	         );
}

</script>

<script type="text/javascript">
$(document).ready(function(){
    $('div.tabs-con:first').show();
	chart();
    $('.tabs-header li').click(function(){
        $(this).addClass('on').siblings('.on').removeClass('on');
        var target = $(this).attr('target');
        $(target).show().siblings('.tabs-con').hide();
        if($(this).attr('target') == '#div1')
        	location.reload();
    	chart();
    
    });
});



</script>

</head>
<body style="background-color:#f5f5f5; padding-top:5px">
	<ul class="tabs-header">
		<li target="#div1" class="on">order state</li>
		<li target="#div2">pay type</li>
		<li target="#div3">commodity category</li>
		<li target="#div4">shop state</li>
    </ul>
    <div id="div1" class="tabs-con">
    
    </div>
    <div id="div2" class="tabs-con">
    </div>
    <div id="div3" class="tabs-con">
    </div>
    <div id="div4" class="tabs-con">
    </div>

</body>
</html>