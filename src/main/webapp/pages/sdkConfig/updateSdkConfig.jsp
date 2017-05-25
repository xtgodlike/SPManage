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
	<title>SDK配置管理-更新配置</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
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
       	
    	$("#configId").formValidator({
       		empty:false,
    		onFocus:"配置标识最多15个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:15,
   			onError:"配置标识最多15个字符"
   		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>/sdk/verifySdkConfigId.do?configId="+jQuery("#configId").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#confirm"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "系统异常，验证失败",
			onWait : "正在对配置标识进行合法性校验，请稍候..."
		});
       	
       	$("#configDescription").formValidator({
       		empty:false,
    		onFocus:"配置说明最多50个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:50,
   			onError:"配置说明最多50个字符"
   		});
       	
       	$("#globalConfigValue").formValidator({
       		empty:true,
    		onFocus:"配置值最多50个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:50,
   			onError:"配置值最多50个字符"
   		});
       	
    });	
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>SKD配置管理</a>
			<span>></span>
			<a class="home_current">更新配置</a>
	</div>  
	<div class="content-mainBG" style="min-width: 950px;">
	<form action="<%=request.getContextPath()%>/sdk/doUpdateSdkConfig.do" id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>配置标识：</td>
                <c:if test="${sdkConfig!=null }">
                <td width="60%" colspan="2" >
                	${sdkConfig.configId}
                	<input type="hidden" name="configId" id="configId" value="${sdkConfig.configId}"/>
                </td>
                </c:if>
                <c:if test="${sdkConfig==null}">
                <td width="30%" >
                   <input type="text" style="width: 200px;" name="configId" id="configId" />
                </td>
                <td width="30%">
                	<div id="configIdTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>配置说明：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="configDescription" id="configDescription" 
                   <c:if test="${sdkConfig!=null }">
                   value="${sdkConfig.configDescription}"
                   </c:if>
                    />
                </td>
                <td width="30%">
                	<div id="configDescriptionTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">全局配置值：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="globalConfigValue" id="globalConfigValue" 
                   <c:if test="${sdkConfig!=null }">
                   value="${sdkConfig.globalConfigValue}"
                   </c:if>
                    />
                </td>
                <td width="30%">
                	<div id="globalConfigValueTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
            	<td colspan="3" align="center">
            		<div id="msgTip" style="width: 40%;margin: 0 auto;"></div>
            	</td>
            </tr>
            <tr>
                <td colspan="3">
                <c:if test="${sdkConfig==null }">
                    <input class="defaultBut" type="submit" id="confirm" value="添加" />
                </c:if>
                <c:if test="${sdkConfig!=null }">
                    <input class="defaultBut" type="submit" id="confirm" value="修改" />
                </c:if>
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
