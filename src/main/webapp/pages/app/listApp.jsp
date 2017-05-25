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
    <title>SP计费平台-供应商管理</title>
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

function toAddApp() {
//	var root ='<%=request.getContextPath()%>';
//	var url = "iframe:"+root+"/supplier/toAddChannel";
//	tipsWindown("新增渠道",url,"1024","768","true","","true","","");
	document.location.href="<%=request.getContextPath()%>/app/toUpdateApp.do";
}

</script>
</head>
<body>
     <div class="home_content_bg">
			<a>应用列表</a>
	</div>  
    <div class="content-mainBG">
    <form action="<%=path%>/app/toAppList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="width: 100%;margin: auto;font-size: 12px;"> 
        <tbody>
            <tr>
                <td align="right">应用名称：</td>
                <td>
                   <input name="appName" id="appName" type="text" class="inputText" value="${app.appName}" />
                </td>
                <td align="right">供应商：</td>
                <td>
                   <select name="cpId" id="cpId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${cps}" var="c">
                    		<option value="${c.cpId}"<c:if test="${c.cpId==app.cpId}">selected="selected"</c:if>>${c.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddApp()" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="width: 100%;margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
            	<th width="10%">应用ID</th>
                <th width="10%">应用名称</th>
                <th width="10%">供应商</th>
                <th width="13%">应用包名</th>
                <th width="10%">应用签名</th>
                <th width="6%">应用APK</th>
                <th width="6%">应用状态</th>
                <th width="10%">创建时间</th>
                <th width="10%">更新时间</th>
                <th width="15%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${apps}" var="a">
        	<tr>
        		<td>${a.appId}</td>
                <td>${a.appName}</td>
                <td>${a.cpName}</td>
                <td>
                	${a.appPacketname}
                </td>
                <td>
                	${a.appSigin}
                </td>
                <td>
                	<c:if test="${empty a.apkId}">未上传</c:if>
                	<c:if test="${!empty a.apkId}">
                	<a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${a.apkId}" title="点击下载">下载</a>
                	</c:if>
                </td>
                <td>
                	<c:if test="${a.appStatus==1 }">是</c:if>
                    <c:if test="${a.appStatus==0 }">否</c:if>
                </td>
                <td>
                	<fmt:formatDate value="${a.createTime}" type="both"/>
                </td>
                 <td>
                	<fmt:formatDate value="${a.modTime}" type="both"/>
                </td>
                <td>
   	            <jsp:include page="../basics/button.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.APP" />
   					<jsp:param name="_vId" value="?appId=${a.appId}" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty apps}">
        	<tr>
        		<td colspan="10" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="10"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>