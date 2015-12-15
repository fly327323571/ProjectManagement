<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--shop Owner Dashboard</title>
    
	 <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
    <script src="static/js/common/baseAjax.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common/jpagination.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/manage.css" rel="stylesheet">
	<link href="static/css/shop/dashboard.css" rel="stylesheet">
  </head>
  
  <body>
    <div class="container">
    	<div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-comment fa"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div id="newComments" class="huge">0</div>
                                    <div>New Comments!</div>
                                </div>
                            </div>
                        </div>
                        <a href="javascript:void(0)" id="viewComment">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa glyphicon glyphicon-usd"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div id="incomeCount" class="huge">0</div>
                                    <div>You have earned till now!</div>
                                </div>
                            </div>
                        </div>
                        <a href="javascript:void(0)" id="viewIncome">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa glyphicon glyphicon-shopping-cart"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div id="newOrderCount" class="huge">0</div>
                                    <div>New Orders!</div>
                                </div>
                            </div>
                        </div>
                        <a href="javascript:void(0)" id="viewOrders">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa glyphicon glyphicon-heart"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div id="favorite_count" class="huge">0</div>
                                    <div>Added to Favorite Shop!</div>
                                </div>
                            </div>
                        </div>
                        <a href="javascript:void(0)">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/template" id="dialogTmpl">
<div class="modal fade" id="dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" style="width: 800px;">
	      <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Warm Prompt</h4>
		      </div>
		      <div class="modal-body clearfix" style="text-align:center;line-height:4;">
		        <p>tip</p>	
		      </div>
		      <div class="modal-footer" style="text-align: center;">
		      	<button type="button" class="btn btn-primary" id="delete">Delete</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		   	  </div>
	   	  </div>
   	  </div>
</div>
</script>
<script type="text/template" id="incomeDetailTmpl">
<div class="row clearfix">
		<div class="col-md-12 column">
			
		<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span>
					 <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Income:</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" id="viewByTime">
						
						<li class="nav_option active">
							<!--  -->
							<a id="day">Today</a>
						</li>
						<li class="nav_option">
							<a  id="week">This Week</a>
						</li>
						<li class="nav_option">
							<a  id="month">This Month</a>
						</li>
						<li class="nav_option">
							<a  id="year">This Year</a>
						</li>
						
					</ul>
					
				</div>
		  </nav>
			
			
			<table width="84%" class="table table-hover" id="orderList">
		  </table>
		  <nav class="page">
				<div id="pagination"></div>
		  </nav>
		</div>
	</div>
</script>

<script type="text/template" id="commentDetailTmpl">
<div class="row clearfix">
	<div class="col-md-12 column">			
		<table width="84%" class="table table-hover" id="commentList"></table>
		  <nav class="page">
				<div id="pagination"></div>
		  </nav>
	</div>
</div>
</script>

<script >
	var storeId = ${storeId};
</script>
  </body>
  <script src="static/js/shop/dashboard.js"></script>
 <script src="static/js/shop/dashboardConfig.js"></script>
 <script src="static/js/common/table.js"></script>
 <script src="static/js/common/jquery.paginate.js"></script>
 <script src="static/js/common/bootstrap-modal.js"></script>
 <script src="static/js/shop/viewIncome.js"></script>
 <script src="static/js/shop/viewIncomeConfig.js"></script>
 <script src="static/js/shop/viewComment.js"></script>
 <script src="static/js/shop/viewCommentConfig.js"></script>
</html>
