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

function toAddSdkTaskGlobal(taskId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkTaskGlobal.do?taskId="+taskId;
	tipsWindown("新增全局配置信息",url,"1024","768","true","","true","","");
}

function toUpdateSdkTaskGlobal(taskId,taskStep) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/sdk/toUpdateSdkTaskGlobal.do?taskId="+taskId+"&taskStep="+taskStep;
	tipsWindown("修改全局配置信息",url,"1024","768","true","","true","","");
}

function doDeleteSdkTaskGlobal(taskId,taskStep) {
	var mes=confirm("是否确认删除？");
	 if(mes==true){
		 var root ='<%=request.getContextPath()%>';
		  document.location.href = root+"/sdk/doDeleteSdkTaskGlobal.do?taskId="+taskId+"&taskStep="+taskStep;
	 }
		
}
</script>
</head>
<body>
	<div class="home_content_bg">
			<a>SDK任务管理</a>
			<span>></span>
			<a class="home_current">全局配置列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">SDK任务名：${sdktask.taskName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	SDK任务说明：${sdktask.taskDesc}</div>
	<form action="<%=path%>/sdk/toSdkTaskGlobalList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="width: 70%;margin: auto;"> 
        <tbody>
            <tr>
                <td align="right">执行步骤:</td>
                <td>
                    <select name="taskStep" id="taskStep" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${taskSteps}" var="t">
                    		<option value="${t.dicCode}"<c:if test="${t.dicCode==sdktaskGlobal.taskStep}">selected="selected"</c:if>>${t.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td style="text-align: right;">
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddSdkTaskGlobal('${taskId}')" />
                	<input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />   
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="width: 70%;margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="50%">执行步骤</th>
                <th width="20%">是否执行</th>
                <th width="30%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${sdktgs}" var="s">
        	<tr>
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
   	            	<input class="def_but" type="button" onclick="doDeleteSdkTaskGlobal('${s.taskId}','${s.taskStep }')" value="删除" />
                	<input class="def_but" type="button" onclick="toUpdateSdkTaskGlobal('${s.taskId}','${s.taskStep }')" value="修改" />
                	
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty sdktgs}">
        	<tr>
        		<td colspan="3" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="3"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
