<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="cache-control" content="no-cache" /> 
<meta http-equiv="expires" content="0" />
<title>SP计费平台-SDK任务管理</title>
<jsp:include page="../basics/baseJs.jsp" />
<jsp:include page="../basics/validator.jsp" />
<jsp:include page="../basics/dialog.jsp" />
 <jsp:include page="../basics/page.jsp" />
<script type="text/javascript">
$(document).ready(function () {
	
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

function toAddSdkTaskCross(taskId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkTaskCross.do?taskId="+taskId;
	tipsWindown("新增交叉配置信息",url,"1024","768","true","","true","","");
}

function toUpdateSdkTaskCross(taskId,appId,channelId,provinceId,taskStep) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkTaskCross.do?taskId="+taskId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&taskStep="+taskStep;
	tipsWindown("修改交叉配置信息",url,"1024","768","true","","true","","");
}

function doDeleteSdkTaskCross(taskId,appId,channelId,provinceId,taskStep) {
	var mes=confirm("是否确认删除？");
	 if(mes==true){
		 var root ='<%=request.getContextPath()%>';
		  document.location.href = root+"/sdk/doDeleteSdkTaskCross.do?taskId="+taskId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&taskStep="+taskStep;
	 }
		
}
</script>
</head>
<body>
	<div class="home_content_bg">
			<a>SDK任务管理</a>
			<span>></span>
			<a class="home_current">交叉配置列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">SDK任务名：${sdktask.taskName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	SDK任务说明：${sdktask.taskDesc}</div>
	<form action="<%=path%>/sdk/toTaskCrossList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="margin: auto;"> 
        <tbody>
            <tr>
                <td align="right">APP名称：</td>
                <td>
                    <select name="appId" id="appId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${apps}" var="a">
                    		<option value="${a.appId}"<c:if test="${a.appId==sdktaskCross.appId}">selected="selected"</c:if>>${a.appName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">渠道:</td>
                <td>
                    <select name="channelId" id="channelId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${channels}" var="c">
                    		<option value="${c.channelId}"<c:if test="${c.channelId==sdktaskCross.channelId}">selected="selected"</c:if>>${c.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">省份:</td>
                <td>
                    <select name="proviceId" id="proviceId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${provinces}" var="p">
                    		<option value="${p.provinceId}"<c:if test="${p.provinceId==sdktaskCross.provinceId}">selected="selected"</c:if>>${p.provinceName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">执行步骤:</td>
                <td>
                    <select name="taskStep" id="taskStep" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${taskSteps}" var="t">
                    		<option value="${t.dicCode}"<c:if test="${t.dicCode==sdktaskCross.taskStep}">selected="selected"</c:if>>${t.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddSdkTaskCross('${taskId}')" />
                	<input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />   
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>
    <table class="table-content" style="margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="15%">APP</th>  
                <th width="30%">渠道</th>
                <th width="10%">省份</th>
                <th width="20%">执行步骤</th>
                <th width="10%">是否执行</th>
                <th width="15%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${sdktcs}" var="s">
        	<tr>
                <td>
                <c:if test="${s.appId!=''}">${s.appName}</c:if>
                <c:if test="${s.appId==''}">全部</c:if>
                </td>
                <td>
                	<c:if test="${s.channelId!=''}">${s.channelName}</c:if>
                	<c:if test="${s.channelId==''}">全部</c:if>
                </td>
                <td>
                	<c:if test="${s.provinceId!=''}">${s.provinceName}</c:if>
                	<c:if test="${s.provinceId==''}">全部</c:if>
                </td>
                <td>
                	<c:forEach items="${taskSteps}" var="t">
                    		<c:if test="${t.dicCode==s.taskStep}">${t.dicName }</c:if>
                    </c:forEach>
                	
                </td>
                 <td>
                	 <c:if test="${s.taskExecute==1 }">是</c:if>
                     <c:if test="${s.taskExecute==0 }">否</c:if>
                </td>
                <td>
   	            	<input class="def_but" type="button" onclick="doDeleteSdkTaskCross('${s.taskId}','${s.appId}','${s.channelId}','${s.provinceId}','${s.taskStep }')" value="删除" />
                	<input class="def_but" type="button" onclick="toUpdateSdkTaskCross('${s.taskId}','${s.appId}','${s.channelId}','${s.provinceId}','${s.taskStep }')" value="修改" />
                	
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty sdktcs}">
        	<tr>
        		<td colspan="6" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="6"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
