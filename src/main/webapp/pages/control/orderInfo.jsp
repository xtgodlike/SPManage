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

<title>计费平台-交易查询</title>
	<jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	//设置回车监听
    	$(document).keyup(function(event){
    		  if(event.keyCode ==13){
    			  jQuery("#order_from").submit();
    		  }
    	});
    	
    	jQuery("#search_but").live('click', function(event){
    		jQuery("#order_from").submit();
    	});
    });
  
function showContent(trId) {
	var trObj = document.getElementById(trId);
	var styleDis = trObj.style.display;
	if(styleDis=='none'){
		trObj.style.display = 'inherit';
	}else{
		trObj.style.display = 'none';
	}
}

</script>
</head>
<body >
<div class="home_content_bg">
			<a>订单详情</a>
	</div> 
<div class="content-mainBG">
 <form  id="order_from" name="order_from" action="<%=request.getContextPath() %>/control/getOrderInfo.do" method="post">
 	<div class="select_div_bg" style="margin-top: 20px;">
 		<table class="table-select" style="table-layout:fixed;" cellpadding="0" border="0">
      			<tr>
      				<td width="70%" align="center">
      					<div class="search_div_bg">
      						<input type="text" id="orderId"  name="orderId" placeholder="输入订单号"
      						style="height: 35px;border: none;width: 400px" class="search_param"  value="${order.orderId}" />
      						<a id="search_but">&nbsp;</a>
      					</div>
      				</td>
      			</tr>
	     </table>
 	</div>
 </form>
    <table  class="table-order" border="0" cellpadding="0" cellspacing="0" >      
        <tbody>
        <c:if test="${order!=null}">
            <tr>
            	<td  align="right" style="font-weight: bold;">订单号：</td>
                <td >
                	${order.orderId}
                </td>
                <td  align="right" style="font-weight: bold;">通道订单号：</td>
                <td>
                	${order.pipleOrderId}
                </td>
                <td  align="right" style="font-weight: bold;">通道编号：</td>
                <td >
                	${order.pipleNumber}
                </td>
            </tr>
            <tr>
                <td  align="right" style="font-weight: bold;">所属通道：</td>
                <td >
                	${order.pipleName}
                </td>
                <td  align="right" style="font-weight: bold;">所属渠道：</td>
                <td >
                	${order.channelName}
                </td>
                <td  align="right" style="font-weight: bold;">所属省份：</td>
                <td >
                	${order.provinceName}
                </td>
            </tr>
            <tr>
                <td  align="right" style="font-weight: bold;">手机号：</td>
                <td >
                	${order.mobile}
                </td>
                <td  align="right" style="font-weight: bold;">IMSI：</td>
                <td >
                	${order.imsi}
                </td>
                <td  align="right" style="font-weight: bold;">ICCID：</td>
                <td >
                	${order.iccid}
                </td>
            </tr>
            <tr>
                <td  align="right" style="font-weight: bold;">交易金额：</td>
                <td >
                	${order.amount}
                </td>
                <td  align="right" style="font-weight: bold;">通道返回码：</td>
                <td >
                	${order.resultCode}
                </td>
                 <td  align="right" style="font-weight: bold;">透传信息：</td>
                <td >
                	${order.extData}
                </td>
            </tr>
            <tr>
                <td  align="right" style="font-weight: bold;">订单状态：</td>
                <td >
                	<c:choose>
                		<c:when test="${order.orderStatus==0}">未完成</c:when>
                		<c:when test="${order.orderStatus==1}">交易中</c:when>
                		<c:when test="${order.orderStatus==2}">交易成功</c:when>
                		<c:when test="${order.orderStatus==3}">交易失败</c:when>
                	</c:choose>
                </td>
                <td  align="right" style="font-weight: bold;">流程状态：</td>
                <td >
                	<c:choose>
                		<c:when test="${order.subStatus==0}">初始化</c:when>
                		<c:when test="${order.subStatus==1}">获取验证码成功</c:when>
                		<c:when test="${order.subStatus==2}">获取验证码失败</c:when>
                		<c:when test="${order.subStatus==11}">截取本地短信成功</c:when>
                		<c:when test="${order.subStatus==12}">截取本地短信失败</c:when>
                		<c:when test="${order.subStatus==13}">提交验证码成功</c:when>
                		<c:when test="${order.subStatus==14}">提交验证码失败</c:when>
                		<c:when test="${order.subStatus==15}">发送短信指令成功</c:when>
                		<c:when test="${order.subStatus==16}">发送短信指令失败</c:when>
                		<c:when test="${order.subStatus==3}">支付成功</c:when>
                		<c:when test="${order.subStatus==4}">支付失败</c:when>
                	</c:choose>
                <td  align="right" style="font-weight: bold;">扣量状态：</td>
                <td >
                	<c:choose>
						<c:when test="${empty order.decStatus}">未扣量</c:when>
                		<c:when test="${order.decStatus==0}">未扣量</c:when>
                		<c:when test="${order.decStatus==1}">已扣量</c:when>
                	</c:choose>
                </td>
            </tr>
            <tr>
                <td  align="right" style="font-weight: bold;">请求时间：</td>
                <td >
                	<c:choose>
						<c:when test="${empty order.createTime}">无</c:when>
                		<c:when test="${order.createTime!=null}"><fmt:formatDate value="${order.createTime}" type="both"/></c:when>
                	</c:choose>
                </td>
                <td  align="right" style="font-weight: bold;">更新时间：</td>
                <td >
                	<c:choose>
						<c:when test="${empty order.modTime}">无</c:when>
                		<c:when test="${order.modTime!=null}"><fmt:formatDate value="${order.modTime}" type="both"/></c:when>
                	</c:choose>
                </td>
                <td  align="right" style="font-weight: bold;">完成时间：</td>
                <td >
                	<c:choose>
						<c:when test="${empty order.completeTime}">无</c:when>
                		<c:when test="${order.completeTime!=null}"><fmt:formatDate value="${order.completeTime}" type="both"/></c:when>
                	</c:choose>
                </td>
             </tr>
        </c:if>
        <c:if test="${empty order }">
        	<tr>
        		<td colspan="6" style="text-align: center;color: red;">无记录</td> 
        	</tr>
        </c:if>  
        </tbody>
    </table>
    
    <div class="hr_bg">交易拓展字段信息</div>
    <table  class="table-content"  cellpadding="0" cellspacing="0"  width="100%" style="table-layout:fixed;"> 
    	<thead>
    		<tr>
        		<th width="10%"  style="text-align: center;font-weight: bold;">拓展字段</th>
        		<th width="90%"  style="text-align: center;font-weight: bold;">字段值</th>
        	</tr>
    	</thead>
    	<tbody>
    			<c:if test="${!empty orderExts}">
    			<c:forEach items="${orderExts}" var="oext">
    			  <tr>
    			  	<td style="text-align: left;">${oext.extKey}</td>
    			  	<td style="text-align: left;word-wrap: break-word;">${oext.extValue}</td>
    			  </tr>
    			</c:forEach>
    			</c:if>
    			<c:if test="${empty orderExts}">
        		<tr>
        			<td colspan="2" style="color: red;">无记录</td>
        		</tr>
        	</c:if>
    	</tbody>
    </table>
    
    <div class="hr_bg">服务端请求日志</div>
    <table  class="table-content" style="table-layout:fixed;" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr>
        		<th width="10%" style="text-align: center;font-weight: bold;">记录时间</th>
        		<th width="20%" style="text-align: center;font-weight: bold;">记录步骤</th>
        		<th width="70%" style="text-align: center;font-weight: bold;">报文内容</th>
        	</tr>
    	</thead>
        <tbody>
        	<c:if test="${!empty serverOperations}">
        	<c:forEach items="${serverOperations}" var="op">
        	<tr style="text-align: left;">
        		<td style="color: red;text-align:center;word-break : break-all;"><fmt:formatDate value="${op.createTime}" type="both"/></td>
        		<td style="word-break : break-all;">${op.operationStep}-${op.stepDesc}</td>
        		<td style="text-align:left; color: #005d9f;word-break : break-all;word-wrap:break-word;">${op.operationContent}</td>
        	</tr>
        	</c:forEach>
        	</c:if>
        	<c:if test="${empty serverOperations}">
        		<tr>
        			<td colspan="3" style="color: red;">无记录</td>
        		</tr>
        	</c:if>
        </tbody>
    </table>
    
    <div class="hr_bg">SDK端请求日志</div>
    <table  class="table-content" style="table-layout:fixed;" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr>
        		<th width="10%" style="text-align: center;font-weight: bold;">记录时间</th>
        		<th width="20%" style="text-align: center;font-weight: bold;">记录步骤</th>
        		<th width="70%" style="text-align: center;font-weight: bold;">报文内容</th>
        	</tr>
    	</thead>
        <tbody>
        	<c:if test="${!empty sdkOperations}">
        	<c:forEach items="${sdkOperations}" var="op">
        	<tr style="text-align: left;">
        		<td style="color: red;text-align:center;word-break : break-all;"><fmt:formatDate value="${op.createTime}" type="both"/></td>
        		<td style="word-break : break-all;">${op.operationStep}-${op.stepDesc}</td>
        		<td style="text-align:left; color: #005d9f;word-break : break-all;word-wrap:break-word;">${op.operationContent}</td>
        	</tr>
        	</c:forEach>
        	</c:if>
        	<c:if test="${empty sdkOperations}">
        		<tr>
        			<td colspan="3" style="color: red;">无记录</td>
        		</tr>
        	</c:if>
        </tbody>
    </table>
    
	</div>
</body>
</html>
