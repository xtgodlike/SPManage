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

function addChannelPiple(pipleId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/piple/toAddChannelPiple.do?pipleId="+pipleId;
	tipsWindown("分配渠道信息",url,"1024","768","true","","true","","");
}

function updateChannelPiple(pipleId,channelId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/piple/toUpdateChannelPiple.do?pipleId="+pipleId+"&channelId="+channelId;
	tipsWindown("修改渠道信息",url,"1024","768","true","","true","","");
}

function updateChannelProvince(pipleId,channelId) {
	var root ='<%=request.getContextPath()%>';
	var url = "iframe:"+root+"/piple/toChannelProvinceLimit.do?pipleId="+pipleId+"&channelId="+channelId;
	tipsWindown("修改分省限量信息",url,"1024","768","true","","true","","");
}

function delChannelPiple(pipleId,channelId) {
	var mes=confirm("是否确认删除？");
	 if(mes==true){
		 var root ='<%=request.getContextPath()%>';
		  document.location.href = root+"/piple/doDeleteChannelPiple.do?pipleId="+pipleId+"&channelId="+channelId;
	 }
		
}
</script>
</head>
<body>
	<div class="home_content_bg">
			<a>通道管理</a>
			<span>></span>
			<a class="home_current">渠道列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">通道编号-名称：${piple.pipleNumber}-${piple.pipleName}</div>
	<form action="<%=request.getContextPath()%>/piple/doUpdatePiplePro.do" id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}" />
        <table class="table-content" border="0" cellpadding="0" cellspacing="0"  > 
        <thead>
        	<tr style="height: 50px;"> 
	               <td colspan="9" align="center">
	            	<input class="defaultBut" type="button" id="addcp" onclick="addChannelPiple('${piple.pipleId}')"  value="分配渠道" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
	                </td>
	        </tr>
            <tr>
            	<th width="8%">APIKEY</th>
                <th width="15%">渠道名称</th>
                <th width="7%">结算比例</th>
                <th width="25%">回调地址</th>
                <th width="8%">扣量阀值</th>
                <th width="8%">交易日限</th>
                <th width="8%">交易月限</th>
                <th width="7%">是否开通</th>
                <th width="14%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${channelPiples}" var="c">
        	<tr>
        		<td>${c.apiKey}</td>
                <td style="text-align: left;">${c.channelName}</td>
                <td>	
					<c:if test="${c.settlementRatio==0}">未设置</c:if>
					<c:if test="${c.settlementRatio!=0}">${c.settlementRatio*100 }%</c:if>
                </td>
                <td style="text-align: left;">
                 <c:if test="${empty c.notifyUrl}">无</c:if>
				<c:if test="${!empty c.notifyUrl}">${c.notifyUrl}</c:if>
                </td>
                <td>${c.volt}</td>
                <td>${c.tradeDay}</td>
                <td>${c.tradeMonth}</td>
                <td>
                	<c:if test="${c.opStatus==0}">关闭</c:if>
                	<c:if test="${c.opStatus==1}">开通</c:if>
                </td>
                <td>
                	<input class="def_but" type="button" onclick="delChannelPiple('${piple.pipleId}','${c.channelId}')" value="删除" />
                	<input class="def_but" type="button" onclick="updateChannelPiple('${piple.pipleId}','${c.channelId}')" value="修改" />
                	<input class="def_but" type="button" onclick="updateChannelProvince('${piple.pipleId}','${c.channelId}')" value="分省限量" />
                </td>
            </tr>
        	</c:forEach>
        	<c:if test="${empty channelPiples}">
        	<tr>
        		<td colspan="9" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
    </table>
   </form>
</div>
</body>
</html>
