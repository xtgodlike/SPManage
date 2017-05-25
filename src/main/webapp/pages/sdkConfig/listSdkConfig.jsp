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
    <title>SP计费平台-SDK配置管理</title>
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

function toAddSdkConfig(){
	document.location.href="<%=request.getContextPath()%>/sdk/toUpdateSdkConfig.do" ;
}

function delSdkConfig(configId) {
	var mes=confirm("删除该SDK配置项，则该配置功能对应的全局、APP、渠道、省份、用户将失效。 是否确认删除？");
	 if(mes==true){
		  document.location.href = "<%=request.getContextPath()%>/sdk/doDeleteSdkConfig.do?configId="+configId;
	 }
}

</script>
</head>
<body>
    
	<div class="home_content_bg">
			<a class="home_current">SDK配置列表</a>
	</div>   
    <div class="content-mainBG">
    <form action="<%=request.getContextPath()%>/sdk/toConfigList.do"  id="frm"  name="frm"   method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="margin: auto;font-size: 12px;" > 
        <tbody>
            <tr>
                <td align="right">配置ID：</td>
                <td>
                   <input name="configId" id="configId" type="text" class="inputText" value="${sdkconfig.configId}" />
                </td>
                <td align="right">配置名：</td>
                <td>
                    <input name="configDescription" id="configDescription" type="text" class="inputText" value="${sdkconfig.configDescription}" />
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddSdkConfig()" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="15%">配置ID</th>  
                <th width="30%">配置说明</th>
                <th width="15%">全局配置值</th>
                <th width="10%">创建时间</th>
                <th width="10%">修改时间</th>
                <th width="20%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${sdkconfigs}" var="s">
        	<tr>
                <td>${s.configId}</td>
                <td>
                	${s.configDescription}
                </td>
                <td>
                	<c:if test="${s.globalConfigValue=='' || s.globalConfigValue==null }">无</c:if>
                	<c:if test="${s.globalConfigValue!=''&& s.globalConfigValue!=null }">${s.globalConfigValue}</c:if>
                	
                </td>
                <td>
                	<fmt:formatDate value="${s.createTime}" type="both"/>
                </td>
                <td>
                	<fmt:formatDate value="${s.modTime}" type="both"/>
                </td>
                <td>
                <input type="button" class="def_but" onclick="delSdkConfig('${s.configId}')"  value="删除"/>
   	            <jsp:include page="../basics/button.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.SDKCONFIG" />
   					<jsp:param name="_vId" value="?configId=${s.configId}" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty sdkconfigs}">
        	<tr>
        		<td colspan="9" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr> 
                <td colspan="9"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>