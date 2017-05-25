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
<title>SP计费平台-SDK配置管理</title>
<jsp:include page="../basics/baseJs.jsp" />
<jsp:include page="../basics/validator.jsp" />
<jsp:include page="../basics/dialog.jsp" />
<jsp:include page="../basics/page.jsp" />
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
	jQuery("#pager").pager( {
		pagenumber : jQuery("#pageNumber").val(),
		pagecount : jQuery("#pagecount").val(),
		buttonClickCallback : PageClick
	});
});	

PageClick = function(pageclickednumber) {
	jQuery("#pager").pager( {
		pagenumber : pageclickednumber,
		pagecount : jQuery("#pagecount").val(),
		buttonClickCallback : PageClick
	});
	
	jQuery("#pageNumber").val(pageclickednumber);
	jQuery("#frm").submit();
};
function toAddSdkConfigCross(configId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkConfigCross.do?configId="+configId;
	tipsWindown("新增交叉配置信息",url,"1024","768","true","","true","","");
}

function toUpdateSdkConfigCross(configId,appId,channelId,provinceId,pipleId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkConfigCross.do?configId="+configId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&pipleId="+pipleId;
	tipsWindown("修改交叉配置信息",url,"1024","768","true","","true","","");
}

function delSdkConfigCross(configId,appId,channelId,provinceId,pipleId) {
	var mes=confirm("是否确认删除？");
	 if(mes==true){
		 var root ='<%=request.getContextPath()%>';
		  document.location.href = root+"/sdk/doDeleteSdkConfigCross.do?configId="+configId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&pipleId="+pipleId;
	 }
		
}
</script>
</head>
<body>
	<div class="home_content_bg">
			<a>SDK配置管理</a>
			<span>></span>
			<a class="home_current">交叉配置列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">SDK配置标识：${sdkconfig.configId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	SDK配置名：${sdkconfig.configDescription}</div>
	<form action="<%=request.getContextPath()%>/sdk/toConfigCrossList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
		<input type="hidden" value="${sdkconfig.configId}" id="configId" name="configId" />
    <table class="table-select" style="width:80%;margin: auto;"> 
        <tbody>
            <tr>
                <td align="right">APP名称：</td>
                <td>
                    <select name="appId" id="appId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${apps}" var="a">
                    		<option value="${a.appId}"<c:if test="${a.appId==sdkconfigCross.appId}">selected="selected"</c:if>>${a.appName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">渠道：</td>
                <td colspan="3">
                    <select name="channelId" id="channelId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${channels}" var="c">
                    		<option value="${c.channelId}"<c:if test="${c.channelId==sdkconfigCross.channelId}">selected="selected"</c:if>>${c.apiKey }-${c.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
                </tr>
                <tr>
                <td align="right">省份：</td>
                <td>
                    <select name="proviceId" id="proviceId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${provinces}" var="p">
                    		<option value="${p.provinceId}"<c:if test="${p.provinceId==sdkconfigCross.provinceId}">selected="selected"</c:if>>${p.provinceName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">通道：</td>
                <td>
                    <select name="pipleId" id="pipleId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${piples}" var="p">
                    		<option value="${p.pipleId}"<c:if test="${p.pipleId==sdkconfigCross.pipleId}">selected="selected"</c:if>>${p.pipleNumber }-${p.pipleName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td colspan="2" style="text-align: left;">
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddSdkConfigCross('${configId}')" />
                	<input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />   
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="width:80%; margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="15%">APP</th>  
                <th width="25%">渠道</th>
                <th width="10%">省份</th>
                <th width="25%">通道</th>
                <th width="10%">配置值</th>
                <th width="15%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${sdkcss}" var="s">
        	<tr>
                <td>
                <c:if test="${s.appId!=''}">${s.appName}</c:if>
                <c:if test="${s.appId==''}">全部</c:if>
                </td>
                <td>
                	<c:if test="${s.channelId!=''}">${s.apiKey}-${s.channelName}</c:if>
                	<c:if test="${s.channelId==''}">全部</c:if>
                </td>
                <td>
                	<c:if test="${s.provinceId!=''}">${s.provinceName}</c:if>
                	<c:if test="${s.provinceId==''}">全部</c:if>
                </td>
                <td>
                	<c:if test="${s.pipleId!=''}">${s.pipleNumber}-${s.pipleName}</c:if>
                	<c:if test="${s.pipleId==''}">全部</c:if>
                </td>
                <td>
                	${s.configValue}
                </td>
                <td>
   	            	<input class="def_but" type="button" onclick="delSdkConfigCross('${s.configId}','${s.appId}','${s.channelId}','${s.provinceId}','${s.pipleId }')" value="删除" />
                	<input class="def_but" type="button" onclick="toUpdateSdkConfigCross('${s.configId}','${s.appId}','${s.channelId}','${s.provinceId}','${s.pipleId }')" value="修改" />
                	
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty sdkcss}">
        	<tr>
        		<td colspan="5" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="5"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
