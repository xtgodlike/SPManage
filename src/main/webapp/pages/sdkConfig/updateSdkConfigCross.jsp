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
	<title>SDK配置管理-交叉配置</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
   		 formID:"frm",
   		 debug:true,
   		 onError:function(msg,obj,errorlist){
   		 	
   		 },
   		 onSuccess:function(){
   			// 更新提示信息
 			jQuery("#msgTip").attr("class","onCorrect");
 			jQuery("#msgTip").html("请求已提交，请稍候...");
 			var configId = jQuery("#configId").val();
 			var options = {
 				url: "<%=request.getContextPath()%>/sdk/doUpdateSdkConfigCross.do",
 		        success: function (data){
 		        	if(data=="UPDATE" || data=="INSERT"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		if(data=="UPDATE"){
 		        			jQuery("#msgTip").html("有此选择范围项，更新配置成功！");
 		        		}
 		        		if(data=="INSERT"){
 		        			jQuery("#msgTip").html("无此选择范围项，新增配置成功！");
 		        		}
 		        		closeEigsx();
 		        		parent.document.location.href="<%=request.getContextPath()%>/sdk/toConfigCrossList.do?configId="+configId;
 		        	}else if(data=="ALL"){
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("APP|渠道|省份|通道不能同时选择全部！配置失败！");
 		        	}else if(data=="EXIST"){
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("包含此选择范围的配置项已存在！配置失败！");
 		        	}else{
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("操作异常！配置失败！"+data);
 		        	}
 		        }
 		    };
 			jQuery("#frm").ajaxSubmit(options);
 			return false;
   		 }
   		});
    	
       	$("#configValue").formValidator({
       		empty:true,
    		onFocus:"配置值最多50个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:50,
   			onError:"配置值最多50个字符"
   		});
       	
    	jQuery("#closeWin").live('click', function(event){
    		closeEigsx();
		});
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="configId" name="configId" value="${configId}"/>
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>APP:</td>
                <td width="60%" colspan="2">
                    <select id="appId" name="appId">
                    	<option value="" <c:if test="${empty scc.appId}">selected="selected"</c:if>>全部</option>
                    	<c:forEach items="${apps}" var="a">
                    		<option value="${a.appId}"<c:if test="${a.appId==scc.appId}">selected="selected"</c:if>>${a.appName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>渠道:</td>
                <td width="60%" colspan="2">
                    <select id="channelId" name="channelId">
                    	<option value="" <c:if test="${empty scc.channelId}">selected="selected"</c:if>>全部</option>
                    	<c:forEach items="${channels}" var="c">
                    		<option value="${c.channelId}"<c:if test="${c.channelId==scc.channelId}">selected="selected"</c:if>>${c.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>省份:</td>
                <td width="60%" colspan="2">
                    <select id="provinceId" name="provinceId">
                    	<option value="" <c:if test="${empty scc.provinceId}">selected="selected"</c:if>>全部</option>
                    	<c:forEach items="${provinces}" var="p">
                    		<option value="${p.provinceId}"<c:if test="${p.provinceId==scc.provinceId}">selected="selected"</c:if>>${p.provinceName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道:</td>
                <td width="60%" colspan="2">
                    <select id="pipleId" name="pipleId">
                    	<option value="" <c:if test="${empty scc.pipleId}">selected="selected"</c:if>>全部</option>
                    	<c:forEach items="${piples}" var="p">
                    		<option value="${p.pipleId}"<c:if test="${p.pipleId==scc.pipleId}">selected="selected"</c:if>>${p.pipleNumber }-${p.pipleName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">配置值：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="configValue" id="configValue" value="${scc.configValue}" />
                </td>
                <td width="30%">
                	<div id="configValueTip"></div>
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
                <td colspan="7">
                    <input class="defaultBut" type="submit" id="confirm" value="提交" />
                    <input class="defaultBut" type="button" id="closeWin" value="关闭" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
