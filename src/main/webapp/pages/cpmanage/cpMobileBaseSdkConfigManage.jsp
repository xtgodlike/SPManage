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
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui/default/easyui.css"/>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    var row = null;
    $(document).ready(function () {
    	 $('#win').window('close');
    	 $("#window_ok").click(function(){
    		 var startCodeTime = $("#startCodeTime").val();
    		 var isUseBWhite = $("#isUseBWhite").val();
    		 var appId = row.appId;
    		 var contentId = row.contentId;
    		 var cpId = row.cpId;
    		 var releaseChannelId = row.releaseChannelId;
    		 $.post("<%=path%>/cpmanage/doUpdateMobileBaseSdkConfig",
 				  {
 				  	 appId:appId,
	    			 contentId:contentId,
	    			 cpId:cpId,
	    			 releaseChannelId:releaseChannelId,
	    			 startCodeTime:startCodeTime,
	    			 isUseBWhite:isUseBWhite
 				  },
 				  function(data,status){
 					  if("UPDATE"== data){
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
         $("#startCodeTime").val(row.startCodeTime);
         $("#isUseBWhite").val(row.isUseBWhite);
    	 $('#win').window('open');
         
    }
    </script>
</head>
<body>
    
    <div class="content-mainBG">
    <br/>
    <br/>
	   <table id="dg"  class="easyui-datagrid"  style="width:auto;height:auto"
				data-options="rownumbers:true,singleSelect:true,fitColumns:true,url:'<%=path%>/cpmanage/listMobileBaseSdkConfigs',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'cpName',width:80">内容提供商</th>
					<th data-options="field:'contentName',width:100">内容名称</th>
					<th data-options="field:'releaseChannelName',width:80">发行渠道名称</th>
					<th data-options="field:'startCodeTime',width:80">开启时间段</th>
					<th data-options="field:'isUseBWhite',width:80">开启黑白包</th>
					<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
    </div>
    <div id="win" class="easyui-window" title="修改策略" style="width:500px;height:250px;">
	<form style="padding:10px 20px 10px 40px;">
		<p>每天开启时间段: <input type="text" id="startCodeTime"  style="width:300px;"/>
		<br/>
		<span style="color:red">例如:[00:00:00-08:00:00],[22:00:00-23:59:59]</span></p>
		<br/>
		<p>是否开启黑白包: <input type="text" id="isUseBWhite"  style="width:300px;"/>
		<br/>
		<span style="color:red">输入true或者false</span></p>
		<br/>
		<div style="padding:5px;text-align:center;">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="window_ok">确定</a>
			<a href="#" class="easyui-linkbutton" icon="icon-cancel" id="window_cancel">取消</a>
		</div>
	</form>
	</div>
</body>
</html>