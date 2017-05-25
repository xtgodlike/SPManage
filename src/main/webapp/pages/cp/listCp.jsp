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
    <title>SP计费平台-内容商管理</title>
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

function toAddCp() {
//	var root ='<%=request.getContextPath()%>';
//	var url = "iframe:"+root+"/supplier/toAddChannel";
//	tipsWindown("新增渠道",url,"1024","768","true","","true","","");
	document.location.href="<%=request.getContextPath()%>/cp/toAddCp.do";
}

</script>
</head>
<body>
    <div class="content-mainBG">
    <form action="<%=path%>/cp/toCpList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="width: 60%;margin: auto;font-size: 12px;"> 
        <tbody>
            <tr>
                <td align="right">供应商名称：</td>
                <td>
                   <input name="fullName" id="fullName" type="text" class="inputText" value="${cp.fullName}" />
                </td>
                <td align="right">联系手机号：</td>
                <td>
                    <input name="tel" id="tel" type="text" class="inputText" value="${cp.tel}" />
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddCp()" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" style="width: 60%;margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="25%">供应商名称</th>  
                <th width="10%">联系人</th>
                <th width="20%">联系电话</th>
                <th width="20%">联系EMAIL</th>
                <th width="10%">联系QQ</th>
                <th width="15%">操作</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${cps}" var="c">
        	<tr>
                <td>${c.fullName}</td>
                <td>
                	<c:if test="${empty c.contactor}">无</c:if>
                	<c:if test="${!empty c.contactor}">${c.contactor}</c:if>
                </td>
                <td>
                	<c:if test="${empty c.tel}">无</c:if>
                	<c:if test="${!empty c.tel}">${c.tel}</c:if>
                </td>
                <td>
                	<c:if test="${empty c.email}">无</c:if>
                	<c:if test="${!empty c.email}">${c.email}</c:if>
                </td>
                <td>
                	<c:if test="${empty c.qq}">无</c:if>
                	<c:if test="${!empty c.qq}">${c.qq}</c:if>
                </td>
                <td>
   	            <jsp:include page="../basics/button.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.CP" />
   					<jsp:param name="_vId" value="?cpId=${c.cpId}" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty cps}">
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