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
    <jsp:include page="../basics/validator.jsp" />
<script type="text/javascript">
function doUpdate() {
	jQuery("#updatePiplePro").attr("disabled", "disabled");
	jQuery("#msgTip").attr("class","onCorrect");
	jQuery("#msgTip").html("已提交...");
	var options = {				
		    url:"<%=request.getContextPath()%>/priority/doUpdatePiplePriority.do",
		    success: function(data) {
		    	if(data=='OK'){
		    	 jQuery("#msgTip").attr("class","onCorrect");
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
			<a>优先级管理</a>
			<span>></span>
			<a class="home_current">通道分省优先级列表</a>
	</div>   
    <div class="content-mainBG">
    <div class="titleShow">运营商：${host.hostName}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  省份：${province.provinceName}</div>
    <form id="frm" name="frm" method="post">
    <input type="hidden" id="hostId" name="hostId" value="${host.hostId}" /> 
    <input type="hidden" id="provinceId" name="provinceId" value="${province.provinceId}" /> 
    <table class="table-content" border="0" cellpadding="0" cellspacing="0" style="width: 50%;margin: auto;">
        <thead>
	       	<tr style="height: 50px;"> 
	               <td colspan="5" align="center">
	                   <input class="defaultBut" type="button" id="updatePiplePro" onclick="doUpdate()" value="提交" />
	                   <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
	                </td>
	        </tr>
	        <tr style="height: 50px;"> 
	                <td colspan="5" align="center">
	                   <font style="font-size: 15px;color: red;font-weight: bold;">规则：优先级初始默认为0,优先级数值越大优先级越高。</font>
	                </td>
	        </tr>
	        <tr>
               <td colspan="5" align="center">
                  <div style="width: 300px;" id="msgTip"></div>
                </td>
           </tr>
            <tr>
            	<th width="20%">通道编号</th>
                <th width="40%">通道名</th>
                <th width="20%">通道类型</th>
                <th width="20%">优先级</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${pList}" var="p">
        	<tr>
        		 <td>${p.pipleNumber}</td>
                <td style="text-align: left">${p.pipleName}</td>
                <td align="left">
                <c:if test="${empty p.pipleType }">无</c:if>
                <c:if test="${!empty p.pipleType }">
                <c:forEach items="${pipleTypes }" var="pt">
                	<c:if test="${pt.dicCode==p.pipleType }">${pt.dicName}    </c:if>
                </c:forEach>
                </c:if>
                </td>
                <td><input class="_priority" id="priority${p.pipleId}" name="priority${p.pipleId}" value="${p.priority}"  maxlength="4"  style="width: 130px;" /></td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty pList}">
        	<tr>
        		<td colspan="4" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
    </table>
    </form>
    </div>
</body>
</html>