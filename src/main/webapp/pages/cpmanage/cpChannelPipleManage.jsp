<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
    <title>计费平台-cp渠道代码管理</title>
     <jsp:include page="../basics/dialog.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/default/easyui.css"/>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    var row = null;
    $(document).ready(function () {
    	 $('#win').window('close');
    	 $("#channel_day_limit_ok").click(function(){
    		 var tradeDay = $("#channel_day_limit").val();
    		 var tradeMonth = $("#channel_month_limit").val();
    		 var channelId = row.channelId;
    		 var pipleId = row.pipleId;
    		 $.post("<%=path%>/piple/doUpdatePipleChannel.do",
 				  {
    			 	pipleId:pipleId,
 				    channelId:channelId,
 				    tradeMonth:tradeMonth,
 				  	tradeDay:tradeDay,
 				  	notifyUrl:row.notifyUrl,
 				  	volt:row.volt,
 				  	opStatus:row.opStatus,
 				  	settlementNum:row.settlementNum
 				  },
 				  function(data,status){
 					  if("OK"== data){
 					 	$('#win').window('close');
 					 	$('#dg').datagrid('reload');
 					  }else{
 						 alert("修改失败，请重试!");
 					  }
 				  });
    	 });
 		$("#channel_day_limit_cancel").click(function(){
 			$('#win').window('close');
    	 });
    });
    function formatOper(val,row,index){  
        return '<a href="#"  onclick="updateChannelLimit('+index+')">修改代码日月限</a>&nbsp; <a href="#"  onclick="updateChannelProvinceLimit('+index+')">省份日月限</a>';
    }  
    function updateChannelProvinceLimit(index){
    	$('#dg').datagrid('selectRow',index);
        row = $('#dg').datagrid('getSelected');  
    	var url = "iframe:"+'<%=path%>/cpmanage/toChannelProvinceLimit?'+'pipleId='+row.pipleId+'&channelId='+row.channelId;
    	tipsWindown("修改省份限量信息",url,"1024","768","true","","true","","");
    }  
    function updateChannelLimit(index){
    	 $('#dg').datagrid('selectRow',index);
         row = $('#dg').datagrid('getSelected');
         $("#channel_day_limit").val(row.tradeDay);
         $("#channel_month_limit").val(row.tradeMonth);
    	 $('#win').window('open');
         
    }
    </script>
</head>
<body>
    
    <div class="content-mainBG">
    <br/>
    <br/>
	   <table id="dg"  class="easyui-datagrid"  style="width:auto;height:auto"
				data-options="rownumbers:true,singleSelect:true,fitColumns:true,url:'<%=path%>/cpmanage/listPiples',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'pipleName',width:100">代码名称</th>
					<th data-options="field:'tradeDay',width:80">代码日限（元）</th>
					<th data-options="field:'tradeMonth',width:80">代码月限（元）</th>
					<th data-options="field:'_operate',width:200,align:'center',formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
    </div>
    <div id="win" class="easyui-window" title="修改代码日月限" style="width:400px;height:200px;">
	<form style="padding:10px 20px 10px 40px;">
		<p style="color:red;">
		提示:0为默认值，不限制	。
		</p>
		<br/>
		<p>日限(元): <input type="text" id="channel_day_limit"/></p>
		<br/>
		<p>月限(元): <input type="text" id="channel_month_limit"/></p>
		<br/>
		<div style="padding:5px;text-align:center;">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="channel_day_limit_ok">确定</a>
			<a href="#" class="easyui-linkbutton" icon="icon-cancel" id="channel_day_limit_cancel">取消</a>
		</div>
	</form>
	</div>
</body>
</html>