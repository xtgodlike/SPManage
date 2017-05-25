<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
	<title>SP计费平台-更新SDK任务配置</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
    		 formID:"frm",
    		 submitOnce:true,
    		 onError:function(msg,obj,errorlist){
    		 	
    		 },
    		 onSuccess:function(){
    		 	
    		 }
    	});
    	
    	$("#taskId").formValidator({
       		empty:true,
    		onFocus:"任务ID可为空或小于24位的字母或数字",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:5,
   			max:23,
   			onError:"任务ID可为空或小于24位的字母或数字"
   		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>"+"/sdk/verifyTaskId.do?isOper="+jQuery("#isOper").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#addSdkTask"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "已存在，验证失败",
			onWait : "正在对通道ID进行合法性校验，请稍候..."
		});
    	
    	$("#taskName").formValidator({
    		onShow:"",
    		onFocus:"任务名称至少3个字符,最多20个字符",
    		onCorrect:"可用"
		}).inputValidator({
			min:3,
			max:20,
			onError:"任务名称至少3个字符,最多20个字符"
		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>"+"/sdk/verifySdkTaskName.do?taskId="+jQuery("#taskId").val()+"&taskName="+jQuery("#taskName").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#addSdkTask"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "系统异常，验证失败",
			onWait : "正在对通道名称进行合法性校验，请稍候..."
		});
    	
    	$("#taskDesc").formValidator({
       		empty:false,
    		onFocus:"任务说明不能为空，最多200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:200,
   			onError:"任务说明不能为空，最多200个字符"
   		});
    	
    	$("#taskVersion").formValidator({
       		empty:true,
    		onFocus:"版本号最多10个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:10,
   			onError:"版本号最多10个字符"
   		});
    	
    	
    	$("#pluginf").formValidator({
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"plugin",
    		dataType:"enum",
    		onError:"请选择插件(文件格式：rar)"
    	});
       	
    });	
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>SDK任务管理</a>
			<span>></span>
			<a class="home_current">更新任务配置</a>
	</div>  
	<div class="content-mainBG">
	<form action="<%=request.getContextPath()%>/sdk/doUpdateSdkTask.do" id="frm" name="frm" method="post" enctype="multipart/form-data">
    <table class="table-add" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        	<tr>
                <td width="40%" align="right">任务ID：</td>
                <c:if test="${sdktask==null}">
                <td width="20%">
                    <input name="taskId" id="taskId" type="text" class="inputText" /><font style="font-size: 15px;color: red">（可指定ID，不填写则自动生成）</font>
                </td>
                <td width="40%">
                	<div id="taskIdTip"></div>
                </td>
                </c:if>
                <c:if test="${sdktask!=null}">
                <td width="20%" >
                <input type="hidden" id="taskId" name="taskId" value="${taskId}" />
                <input name="isOper" id="isOper"  type="hidden"  class="inputText"   style="display: none;" value="update"/>
                ${sdktask.taskId}
                </td>
                 <td width="40%" style="display: none;">
                	<div id="pipleIdTip"></div>
                </td>
                </c:if>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>任务名称：</td>
                <c:if test="${sdktask==null}">
                <td width="20%">
                    <input name="taskName" id="taskName" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="taskNameTip"></div>
                </td>
                </c:if>
                <c:if test="${sdktask!=null }">
                <td width="60%" colspan="2" >
                	${sdktask.taskName}
                	<input type="hidden" name="taskName" id="taskName" value="${sdktask.taskName}"/>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>任务说明：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="taskDesc" id="taskDesc" 
                   <c:if test="${sdktask!=null }">
                   value="${sdktask.taskDesc}"
                   </c:if>
                    />
                </td>
                <td width="30%">
                	<div id="taskDescTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>启用状态：</td>
                <td width="60%" colspan="2">
                    <select name="taskStatus" id="taskStatus" class="selectText">
                    	<option value="1" <c:if test="${sdktask.taskStatus==1 }">selected="selected"</c:if>>启用</option>
                    	<option value="0" <c:if test="${sdktask.taskStatus==0 }">selected="selected"</c:if>>停用</option>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>任务插件：</td>
                <td width="20%" >
                	<c:if test="${!empty sdktask.taskPlugin}"><font style="font-size: 15px;color: green;">(插件已上传，可更新)</font></c:if>
                	<input type="hidden"  id="taskPlugin"  name="taskPlugin" value="${sdktask.taskPlugin }"/>
                   	<input type="file" style="width: 250px;" class="inputText" name="pluginf" id="pluginf" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="pluginfTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>插件版本：</td>
                <td width="20%" >
                   <input type="text" style="width: 250px;"  name="taskVersion" id="taskVersion" value="${sdktask.taskVersion}" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="taskVersionTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="addSdkTask" value="提交" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
