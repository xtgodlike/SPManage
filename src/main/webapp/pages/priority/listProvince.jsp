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
    <title>SP计费平台-优先级管理</title>
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

function toAddChannel() {
//	var root ='<%=request.getContextPath()%>';
//	var url = "iframe:"+root+"/channel/toAddChannel";
//	tipsWindown("新增渠道",url,"1024","768","true","","true","","");
	document.location.href="<%=request.getContextPath()%>/channel/toAddChannel.do";
}

</script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">运营商分省列表</a>
	</div>   
    <div class="content-mainBG">
    <form action="<%=path%>/priority/toProvinceList.do" id="frm" name="frm" method="post"> 
    <table class="table-select" style="font-size: 12px;width: 40%;margin: auto;">
        <tbody>
            <tr>
                <td align="right">运营商：</td>
                   <td>
                    <select name="hostId" id="hostId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${hList}" var="s">
                    		<option value="${s.hostId}"<c:if test="${s.hostId==hostId}">selected="selected"</c:if>>${s.hostName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td>
                	<input class="defaultBut" type="button" value="确定" onclick="javascript:document.getElementById('frm').submit();" />
                </td>
            </tr>
        </tbody>
    </table>
	</form>

    <table class="table-content" border="0" cellpadding="0" cellspacing="0" style="width: 40%;margin: auto;">
        <thead>
            <tr>
                <th width="20%">运营商</th>
                <th width="40%">省份</th>
                <th width="40%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${!empty host}">
        	<c:forEach items="${provinces}" var="p">
        	<tr>
                <td>${host.hostName}</td>
                <td>${p.provinceName}</td>
                <td>
   	            <jsp:include page="../basics/button.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.PRIORITY" />
   					<jsp:param name="_vId" value="?provinceId=${p.provinceId}&hostId=${host.hostId }" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        </c:if>
        	<c:if test="${empty host}">
        	<tr>
        		<td colspan="3" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
    </table>
    </div>
</body>
</html>