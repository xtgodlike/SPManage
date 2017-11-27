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
    <title>SP计费平台-通道管理</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/page.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
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

function toAddPiple(){
	document.location.href="<%=request.getContextPath()%>/piple/toAddPiple.do" ;
}

</script>
</head>
<body>
    <div class="home_content_bg">
			<a>通道列表</a>
	</div>  
    <div class="content-mainBG">
    <form action="<%=path%>/piple/toPipleList.do" id="frm" name="frm" method="post">
        <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
		<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
    <table class="table-select" style="font-size: 12px;" >
        <tbody>
            <tr>
            	<td align="right"  width="7%">通道编号：</td> 
                <td width="13%">
                   <input name="pipleNumber" id="pipleNumber" type="text" class="inputText" style="width: 100px;" value="${piple.pipleNumber}" />
                </td>
                <td align="right" width="7%">通道名称：</td>
                <td  width="13%">
                   <input name="pipleName" id="pipleName" type="text" class="inputText" style="width: 150px;" value="${piple.pipleName}" />
                </td>
                <td align="right" width="7%">供应商：</td>
                <td width="13%">
                    <select name="supplierId" id="supplierId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${suppliers}" var="s">
                    		<option value="${s.supplierId}"<c:if test="${s.supplierId==piple.supplierId}">selected="selected"</c:if>>${s.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right" width="7%">运营商：</td>
                <td width="13%">
                    <select name="hostId" id="hostId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${hosts}" var="s">
                    		<option value="${s.hostId}"<c:if test="${s.hostId==piple.hostId}">selected="selected"</c:if>>${s.hostName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="left" colspan="2" width="20%">插件类型：
                <select name="pluginType" id="pluginType" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${pluginTypes}" var="s">
                    		<option value="${s.dicCode}"<c:if test="${s.dicCode==piple.pluginType}">selected="selected"</c:if>>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
             </tr>
             <tr>
                <td align="right">通道类型：</td>
                <td>
                    <select name="pipleType" id="pipleType" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${pipleTypes}" var="s">
                    		<option value="${s.dicCode}"<c:if test="${s.dicCode==piple.pipleType}">selected="selected"</c:if>>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">结算类型：</td>
                <td>
                    <select name="calcType" id="calcType" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${calcTypes}" var="s">
                    		<option value="${s.dicCode}"<c:if test="${s.dicCode==piple.calcType}">selected="selected"</c:if>>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">代码类型：</td>
                <td>
                    <select name="codeType" id="codeType" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${codeTypes}" var="s">
                    		<option value="${s.dicCode}"<c:if test="${s.dicCode==piple.codeType}">selected="selected"</c:if>>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
                <td align="right">通道状态：</td>
                <td>
                    <select name="opStatus" id="opStatus" class="selectText">
                    	<option value="" <c:if test="${piple.opStatus==null}">selected="selected"</c:if>>请选择</option>
                    	<option value="0" <c:if test="${piple.opStatus==0}">selected="selected"</c:if>>关闭</option>
                    	<option value="1" <c:if test="${piple.opStatus==1}">selected="selected"</c:if>>开通</option>
                    </select>
                </td>
                <td colspan="2">
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                	<input class="defaultBut" type="button" value="新增" onclick="toAddPiple()" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
            	<th width="6%">通道编号</th>
                <th width="11%">通道名称</th>
                <th width="12%">供应商</th>
                <th width="5%">运营商</th>
                <th width="5%">通道类型</th>
                <th width="6%">结算类型</th>
                <th width="6%">结算比例</th>
                <th width="6%">代码类型</th>
                <th width="4%">通道端</br>文档</th>
                <th width="4%">渠道端</br>文档</th>
                <th width="5%">正式版本</th>
                <th width="5%">测试版本</th>
                 <th width="6%">插件类型</th>
                <th width="5%">状态</th>
                <th width="14%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${piples}" var="p">
        	<tr>
        		<td >${p.pipleNumber}</td>
                <td style="text-align: left;">${p.pipleName}</td>
                <td style="text-align: left;">${p.supplierName}</td>
                <td>${p.hostName}</td>
                <td>${p.pipleTypeDesc}</td>
                <td>${p.calcTypeDesc}</td>
                <td>	
					<c:if test="${p.settlementRatio==0}">未设置</c:if>
					<c:if test="${p.settlementRatio!=0}"><fmt:formatNumber value="${p.settlementRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
                </td>
                <td>${p.codeTypeDesc}</td>
                <td >
                	<c:if test="${empty p.pipleDoc}">未上传</c:if>
                	<c:if test="${!empty p.pipleDoc}"><a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${p.pipleDoc}" title="点击下载">下载</a></c:if>
                	
                </td>
                <td >
                	<c:if test="${empty p.channelDoc}">未上传</c:if>
                	<c:if test="${!empty p.channelDoc}"><a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${p.channelDoc}" title="点击下载">下载</a></c:if>
                </td>
                <td >
                	<c:if test="${empty p.pluginVersion}">未上传</c:if>
                	<c:if test="${!empty p.pluginVersion}">${p.pluginVersion} &nbsp;
                	<a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${p.pluginId}" title="点击下载">下载</a>
                	</c:if>
                </td>
                <td >
                	<c:if test="${empty p.testPluginId}">未上传</c:if>
                	<c:if test="${!empty p.testPluginId}">
                	<a href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId=${p.testPluginId}" title="点击下载">下载</a>
                	</c:if>
                </td>
                <td>
                	<c:if test="${empty p.pluginTypeDesc}">未选择</c:if>
                	<c:if test="${!empty p.pluginTypeDesc}">${p.pluginTypeDesc}</c:if>
                </td>
                <td>
                	<c:if test="${p.opStatus==0}">关闭</c:if>
                	<c:if test="${p.opStatus==1}">开通</c:if>
                </td>
                <td>
   	            <jsp:include page="../basics/newbutton.jsp" >
   					<jsp:param name="_cModuleCode" value="QY.FEE.BMGM.PIPLE" />
   					<jsp:param name="_vId" value="?pipleId=${p.pipleId}" />
   				</jsp:include>
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty piples}">
        	<tr>
        		<td colspan="15" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="15"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>