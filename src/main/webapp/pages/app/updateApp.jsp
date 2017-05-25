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
	<title>SP计费平台-更新应用信息</title>
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
    	
    	$("#appName").formValidator({
    		onShow:"",
    		onFocus:"应用名称至少3个字符,最多20个字符",
    		onCorrect:"可用"
		}).inputValidator({
			min:3,
			max:20,
			onError:"应用名称至少3个字符,最多20个字符"
		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>"+"/app/verifyAppName.do?appId="+jQuery("#appId").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#addApp"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "系统异常，验证失败",
			onWait : "正在对通道名称进行合法性校验，请稍候..."
		});
    	
    	$("#appPacketname").formValidator({
       		empty:false,
    		onFocus:"应用包名不能为空，最多200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:200,
   			onError:"任务说明不能为空，最多200个字符"
   		});
    	
    	$("#appSigin").formValidator({
       		empty:true,
    		onFocus:"应用签名不能为空，最多200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:200,
   			onError:"应用签名不能为空，最多200个字符"
   		});
    	
    	
    	$("#apkId").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"apk",
    		dataType:"enum",
    		onError:"请选择APK(文件格式：apk)"
    	});
       	
    });	
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>应用管理</a>
			<span>></span>
			<a>更新应用信息</a>
	</div>  
	<div class="content-mainBG">
	<form action="<%=request.getContextPath()%>/app/doUpdateApp.do" id="frm" name="frm" method="post" enctype="multipart/form-data">
	
    <table class="table-add" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
         	<tr>
                <td width="40%" align="right"><font color="FF0000">*</font>应用ID</td>
                <td width="30%" >
                <c:if test="${app==null}"><input type="text" id="appId" name="appId"  /></c:if>
                <c:if test="${app!=null}"><input type="text" id="appId" name="appId" value="${app.appId}" readonly="readonly" /></c:if>
                </td>
                <td width="30%">
                	<div >不填时，自动生成</div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>应用名称：</td>
                <c:if test="${app==null}">
                <td width="20%">
                    <input type="text" class="inputText" name="appName" id="appName"  />
                </td>
                <td width="40%">
                	<div id="appNameTip"></div>
                </td>
                </c:if>
                <c:if test="${app!=null }">
                <td width="60%" colspan="2" >
                	<input type="text" name="appName" id="appName" value="${app.appName}"/>
                </td>
                </c:if>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>所属供应商：</td>
                <td width="60%" colspan="2">
                    <select name="cpId" id="cpId" class="selectText">
                    	<c:forEach items="${cps}" var="c">
                    		<option value="${c.cpId}"  <c:if test="${c.cpId==app.cpId}">selected="selected"</c:if>
                    		>${c.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>应用包名：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="appPacketname" id="appPacketname" 
                   <c:if test="${app!=null }">
                   value="${app.appPacketname}"
                   </c:if>
                    />
                </td>
                <td width="30%">
                	<div id="appPacketnameTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>应用签名：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="appSigin" id="appSigin" 
                   <c:if test="${app!=null }">
                   value="${app.appSigin}"
                   </c:if>
                    />
                </td>
                <td width="30%">
                	<div id="appSiginTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>启用状态：</td>
                <td width="60%" colspan="2">
                    <select name="appStatus" id="appStatus" class="selectText">
                    	<option value="1" <c:if test="${app.appStatus==1 }">selected="selected"</c:if>>启用</option>
                    	<option value="0" <c:if test="${app.appStatus==0 }">selected="selected"</c:if>>停用</option>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>应用APK：</td>
                <td width="20%" >
                	<c:if test="${!empty app.apkId}"><font style="font-size: 15px;color: green;">(插件已上传，可更新)</font></c:if>
                   	<input type="file" style="width: 250px;" class="inputText" name="apkId" id="apkId" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="apkIdTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="addApp" value="提交" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
