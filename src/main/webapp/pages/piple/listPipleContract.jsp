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
<title>SP计费平台-通道管理</title>
<jsp:include page="../basics/baseJs.jsp" />
<jsp:include page="../basics/validator.jsp" />
<jsp:include page="../basics/dialog.jsp" />
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
});	

function addContract(pipleId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/piple/toAddPipleContract.do?pipleId="+pipleId;
	tipsWindown("新增合同",url,"1024","768","true","","true","","");
}

function updateContract(pipleId,contractId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/piple/toUpdatePipleContract.do?pipleId="+pipleId+"&contractId="+contractId;
	tipsWindown("修改合同",url,"1024","768","true","","true","","");
}

function downloadContract(fileId) {
	document.location.href="<%=request.getContextPath()%>/basics/downLoadFile.do?fileId="+fileId;
}

</script>
</head>
<body>
	<div class="home_content_bg">
			<a>通道管理</a>
			<span>></span>
			<a class="home_current">合同列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">通道编号-名称：${piple.pipleNumber}-${piple.pipleName}</div>
	<form  id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}" />
        <table class="table-content" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="10%">甲方</th>
                <th width="10%">乙方</th>
                <th width="10%">丙方</th>
                <th width="10%">有效期开始</th>
                <th width="10%">有效期结束</th>
                <th width="10%">签署日期</th>
                <th width="10%">状态</th>
                <th width="30%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${contracts}" var="c">
        	<tr>
                <td>${c.partyA}</td>
                <td>${c.partyB}</td>
                <td>
                	<c:if test="${c.partyC==''}">无</c:if>
                	<c:if test="${c.partyC!=''}">${c.partyC}</c:if>
                </td>
                <td><fmt:formatDate value="${c.validateStart}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${c.validateEnd}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${c.signDate}" pattern="yyyy-MM-dd" /></td>
                <td><c:if test="${c.opStatus==0}">无效</c:if>
                	<c:if test="${c.opStatus==1}">有效</c:if>
                </td>
                <td>
                	<input class="def_but" type="button" onclick="updateContract('${piple.pipleId}','${c.contractId }')"  value="修改" />
                	<input class="def_but" type="button" onclick="downloadContract('${c.fileId }')" value="下载" />
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty contracts}">
        	<tr>
        		<td colspan="9" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="9" align="center">
                    <input class="defaultBut" type="button" id="addc" onclick="addContract('${piple.pipleId}')" value="新增合同" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
