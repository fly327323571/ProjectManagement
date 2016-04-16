<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DataBase</title>
<style type="text/css">
	body{
		padding-left:10px;
		padding-top:10px;
		background-color:#f5f5f5;
		line-height:35px;
	}

</style>
</head>
<body >
	<form action="">
		<ul>
			<li>DataBase dump
			<div style="color:red;font-size:15px">dump database to place configuration figure out.</div>
			<input type="button" style="width:111px;height:39px;" value="dump database" onclick="dumpDatabase();"/>
			<span id="prompt_dumpDatabase" style="color:red"></span>
			</li>
			<li>Recover Backup
			<div style="color:red;font-size:15px">Recover database(Warning: it can't be undo)</div>
			<input type="button" style="width:111px;height:39px;" value="Recover database" onclick="recoverDatabase();"/>
			<span id="prompt_recoverDatabase" style="color:red"></span>
			
		</ul>
	</form>

</body>
<script type="text/javascript" src="/ParknShop/static/js/common/jquery-1.11.1.js"></script>
<script type="text/javascript">
	
	function dumpDatabase(){
		$("#prompt_dumpDatabase").text("wait!");
		$.post("dumpDatabase.do",{},function(data){
			if(data == "true")
				$("#prompt_dumpDatabase").text("dump successfully");
			else
				$("#prompt_dumpDatabase").text("dump failed,please check your configuration");
		});
	}
	
	function recoverDatabase(){
		$("#prompt_recoverDatabase").text("wait!");
		$.post("recoverDatabase.do",{},function(data){
			if(data == "true")
				$("#prompt_recoverDatabase").text("recover successfully");
			else
				$("#prompt_recoverDatabase").text("Error:recover failed,backup file is not existed or configuration error");
				
				
		});
	}


</script>

</html>