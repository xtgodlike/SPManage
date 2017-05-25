<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
    <title>SP计费平台-SDK任务管理</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/page.jsp" />
<script type="text/javascript">
 jQuery(document).ready(function() {
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

function toAddSdkTask(){
	document.location.href="<%=request.getContextPath()%>/sdk/toUpdateSdkTask.do" ;
}

function delSdkTask(taskId) {
	var mes=confirm("删除该SDK项，则该配置功能对应的全局、APP、渠道、省份、用户将失效。 是否确认删除？");
	 if(mes==true){
		  document.location.href = "<%=request.getContextPath()%>/sdk/doDeleteSdkTask.do?taskId="+taskId;
	 }
}

</script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">SDK任务列表</a>
	</div>   
    <div class="content-mainBG">
    <form action="<%=path%>/sdk/toTaskList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="margin: auto;font-size: 12px;" > 
        <tbody>
            <tr>
                <td align="right">任务名称：</td>
                <td>
                   <input name="taskName" id="taskName" type="text" class="inputText" value="${sdktask.taskName}" />
                </td>
                <td align="right">任务状态：</td>
                <td>
                    <select name="taskStatus" id="taskStatus" class="selectText">
                    	<option value="" <c:if test="${sdktask.taskStatus==null}">selected="selected"</c:if>>请选择</option>
                    	<option value="0" <c:if test="${sdktask.taskStatus==0}">selected="selected"</c:if>>关闭</option>
                    	<option value="1" <c:if test="${sdktask.taskStatus==1}">selected="selected"</c:if>>开通</option>
                    </select>
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddSdkTask()" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="15%">任务名称</th>  
                <th width="30%">任务说明</th>
                <th width="5%">任务版本</th>
                <th width="10%">任务插件</th>
                <th width="5%">任务状态</th>
                <th width="10%">创建时间</th>
                <th width="10%">修改时间</th>
                <th width="15%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${sdktasks}" var="s">
        	<tr>
                <td>${s.taskName}</td>
                <td>${s.taskDesc}</td>
                <td>${s.taskVersion}</td>
                <td >
                	<c:if test="${empty s.taskPlugin}">未上传</c:if>
                	<c:if test="${!empty s.taskPlugin}">
                	<a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${s.taskPlugin}" title="点击下载">下载</a>
                	</c:if>
                </td>
                <td>
                	<c:if test="${s.taskStatus==0}">关闭</c:if>
                	<c:if test="${s.taskStatus==1}">开通</c:if>
                </td>
                <td>
                	<fmt:formatDate value="${s.createTime}" type="both"/>
                </td>
                <td>
                	<fmt:formatDate value="${s.modTime}" type="both"/>
                </td>
                <td>
                <input type="button" class="def_but" onclick="delSdkTask('${s.taskId}')"  value="删除"/>
   	            <jsp:include page="../basics/button.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.SDKTASK" />
   					<jsp:param name="_vId" value="?taskId=${s.taskId}" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty sdktasks}">
        	<tr>
        		<td colspan="8" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="8"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>