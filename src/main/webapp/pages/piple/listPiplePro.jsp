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
<script type="text/javascript">
function doUpdate() {
		jQuery("#updatePiplePro").attr("disabled", "disabled");
		jQuery("#msgTip").attr("class","onCorrect");
		jQuery("#msgTip").html("已提交...");
		var options = {				
			    url:"<%=request.getContextPath()%>/piple/doUpdatePiplePro.do",
			    success: function(data) {
			    	if(data=='OK'){
			    	  jQuery("#msgTip").html("更新成功！");
			      }else{
			    	  jQuery("#updatePiplePro").removeAttr("disabled");
			    	  jQuery("#msgTip").attr("class","onError");
			    	  jQuery("#msgTip").html("更新失败！"+data);
			      }
			    } 
			};		 
		jQuery("#frm").ajaxSubmit(options); 
}
</script>
</head>
<body>
	<div class="home_content_bg">
			<a>通道管理</a>
			<span>></span>
			<a class="home_current">产品列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">通道编号-名称：${piple.pipleNumber}-${piple.pipleName}</div>
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}" /> 
        <table class="table-content" style="width: 80%;margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
        	<tr>
               <td colspan="5" align="center">
                  <div style="width: 300px;" id="msgTip"></div>
                </td>
           </tr>
	       	<tr style="height: 50px;"> 
	               <td colspan="5" align="center">
	                   <input class="defaultBut" type="button" id="updatePiplePro" onclick="doUpdate()" value="提交" />
	                   <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
	                </td>
	        </tr>
            <tr>
                <th width="15%">产品代码</th>
                <th width="15%">产品名称</th>
                <th width="25%">通道产品代码</th>
                <th width="25%">通道产品简码</th>
                <th width="20%">状态</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${piplePros}" var="p">
        	<tr>
                <td>${p.productCode}</td>
                <td>${p.productName}</td>
                <td><input id="pipleProductCode${p.pipleId}${p.productId}" name="pipleProductCode${p.pipleId}${p.productId}" value="${p.pipleProductCode}" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" maxlength="25" /></td>
                <td><input id="pipleProductAbbrCode${p.pipleId}${p.productId}" name="pipleProductAbbrCode${p.pipleId}${p.productId}" value="${p.pipleProductAbbrCode}" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" maxlength="25" /></td>
                <td>
                	<input type="radio" name="opStatus${p.pipleId}${p.productId}" value="0"  <c:if test="${p.opStatus==0}">checked="checked"</c:if> />关闭
                	<input type="radio" name="opStatus${p.pipleId}${p.productId}" value="1"  <c:if test="${p.opStatus==1}">checked="checked"</c:if>/>开通
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty piplePros}">
        	<tr>
        		<td colspan="5" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
    </table>
   </form>
</div>
</body>
</html>
