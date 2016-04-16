<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>ParknShop--findBack Password</title>
    
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	
	<script src="static/js/common/jquery-1.11.1.js"></script>
    <script src="static/js/common/bootstrap.min.js"></script>
	<link href="static/css/common/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/user/headTail.css" rel="stylesheet">
	<link href="static/css/user/style.css" rel="stylesheet">
	<link href="static/css/customer/findPassword.css" rel="stylesheet" type="text/css">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
  <link rel="stylesheet" href="static/css/user/register/register.css"/>

</head>
  
  <body>
   <%@include file="../common/toolbar.jsp" %>

<header>
		<img src="static/images/logo.jpg" style="padding-top:10px;padding-left:20px;height:60px;"></img>
		<span class="identity">find back password</span>
</header>

<div class="container">
	<form class="form-horizontal">
		
		<div class="form-group">
			 <label class="col-sm-3 control-label">Email</label>
			<div class="col-sm-7" >
				<div  class="col-sm-12">
				<input class="form-control" name="email" id="email" type="text" required="required" validateType="email" autofocus="autofocus" placeholder="please input your email."/>
				</div>
			</div>
		</div>
		<div class="form-group">
			  <label class="col-sm-3 control-label">Check Word</label>	
			  <div  class="col-sm-7">				
				<div class="col-sm-6">
					<input class="form-control" name="checkWord" id="checkWord" type="text" required="required"/>
					
				</div>
				
				<div id="checkImg" class="col-sm-6">
					<img title="I can't figure out" style="cursor:pointer;"src="service/checkcodeImage.jpg" name=""/>
				</div>
				
			</div>
		</div>
		
		
		
		<div class="form-group">
			 <input class="btn btn-default"  type="button" id="find" value="find back"/>
		</div>
		
	</form>
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
</div>
    <%@include file="../common/tail.html" %> 
  </body>
   <script src="static/js/common/baseAjax.js"></script>
   <script src="static/js/common/validator.js"></script>
   <script src="static/js/user/findPassword.js"></script>
   <script src="static/js/common/bootstrap-modal.js"></script>
</html>
