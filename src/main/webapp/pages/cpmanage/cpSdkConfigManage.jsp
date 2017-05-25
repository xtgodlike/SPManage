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
    <title>计费平台-cp渠道策略管理</title>
     <jsp:include page="../basics/dialog.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/default/easyui.css"/>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    var row = null;
    $(document).ready(function () {
    	 $('#win').window('close');
    	 $("#window_ok").click(function(){
    		 var configValue = $("#config_Value").val();
    		 var configId = row.configId;
    		 var channelId = row.channelId;
    		 var pipleId = row.pipleId;
    		 var appId = row.appId;
    		 $.post("<%=path%>/sdk/doUpdateSdkConfigCross.do",
 				  {
    			 	pipleId:pipleId,
 				    channelId:channelId,
 				    configId:configId,
 				  	appId:appId,
 				  	configValue:configValue
 				  },
 				  function(data,status){
 					  if("UPDATE"== data || "INSERT" == data){
 					 	$('#win').window('close');
 					 	$('#dg').datagrid('reload');
 					  }else if("FAIL"==data){
 						 alert("修改失败，请重试!");
 					  }else{
 						 alert("非法操作!");
 					  }
 				  });
    	 });
 		$("#window_cancel").click(function(){
 			$('#win').window('close');
    	 });
    });
    function formatOper(val,row,index){  
        return '<a href="#"  onclick="updateConfigValue('+index+')">修改策略值</a>';
    }  
    function updateConfigValue(index){
    	 $('#dg').datagrid('selectRow',index);
         row = $('#dg').datagrid('getSelected');
         $("#config_Value").val(row.configValue);
         $("#config_desc").html(row.configDescription);
    	 $('#win').window('open');
         
    }
    </script>
</head>
<body>
    
    <div class="content-mainBG">
    <br/>
    <br/>
	   <table id="dg"  class="easyui-datagrid"  style="width:auto;height:auto"
				data-options="rownumbers:true,singleSelect:true,fitColumns:true,url:'<%=path%>/cpmanage/listSdkConfigs',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'pipleName',width:100">代码名称</th>
					<th data-options="field:'appName',width:80">应用</th>
					<th data-options="field:'configDescription',width:80">策略说明</th>
					<th data-options="field:'configValue',width:240">策略值</th>
					<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
    </div>
    <div id="win" class="easyui-window" title="修改策略" style="width:500px;height:180px;">
	<form style="padding:10px 20px 10px 40px;">
		<p  style="color:red;">说明:<span type="text" id="config_desc"/></p>
		<br/>
		<p>策略值: <input type="text" id="config_Value"  style="width:300px;"/></p>
		<br/>
		<div style="padding:5px;text-align:center;">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="window_ok">确定</a>
			<a href="#" class="easyui-linkbutton" icon="icon-cancel" id="window_cancel">取消</a>
		</div>
	</form>
	</div>
</body>
</html>