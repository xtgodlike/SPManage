<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>计费平台-运行监控</title>
	<jsp:include page="../basics/baseJs.jsp" />
	<jsp:include page="../basics/page.jsp" />
	<jsp:include page="../basics/datetimepicker.jsp" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dateTool.js" ></script>
    <script type="text/javascript">
    $(document).ready(function () {
    	jQuery("#pager").pager( {
    		pagenumber : jQuery("#pageNumber").val(),
    		pagecount : jQuery("#pagecount").val(),
    		buttonClickCallback : PageClick
    	});

    	jQuery('.date_class').datetimepicker({
			language: 'zh-CN',
			//startView: "year", //默认打开视图
			format: "yyyy-mm-dd",
			autoclose:true,
	        minView: "month" //选择日期后，不会再跳转去选择时分秒 
	    });
    	
    	$('.date_class').datetimepicker().on('changeDate', function(ev){
    	    var startTime = jQuery("#startTime").val();
    	    var endTime = jQuery("#endTime").val();
			var i = DateDiff(startTime, endTime);
			if(i>40){
				jQuery("#empty_list_td").html("开始日期与结束日期间隔不能大于40天");
				jQuery(this).val("");
			}else if(i==-1){
				jQuery("#empty_list_td").html("日期选择错误！");
				jQuery(this).val("");
			}
    	});
    	
    	
    	//查询
    	jQuery("#search_But").live('click', function(event){
    		jQuery("#frm").attr("action", "<%=path%>/control/toOrderList.do");
 	    	$("#operation").val("1");
 	    	jQuery("#frm").submit();
    	});
    	
    	//导出
    	jQuery("#export_But").live('click', function(event){
    		var startTime = jQuery("#startTime").val();
     	    var endTime = jQuery("#endTime").val();
     	    if(""==startTime || ""==endTime){
     	    	jQuery("#empty_list_td").html("请输入日期后再导出！");
     	    }else{
     	    	var url = "<%=path%>/control/getOrderInfoReport.do?orderId="+jQuery("#orderId").val()+"&pipleOrderId="+jQuery("#pipleOrderId").val()
     	    			+"&mobile="+jQuery("#mobile").val()+"&imsi="+jQuery("#imsi").val()
     	    			+"&pipleId="+jQuery("#pipleId").val()+"&channelId="+jQuery("#channelId").val()
     	    			+"&startTime="+jQuery("#startTime").val()+"&endTime="+jQuery("#endTime").val()
     	    			+"&provinceId="+jQuery("#provinceId").val()+"&orderStatus="+jQuery("#orderStatus").val()
     	    			+"&subStatus="+jQuery("#subStatus").val()+"&decStatus="+jQuery("#decStatus").val();
	     	   	document.location.href=url;
     	    }
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
<body>
<div class="home_content_bg">
			<a>订单列表</a>
	</div> 
<div class="content-mainBG">
 <form  id="frm" name="frm"  method="post" >
 <input id="operation" name="operation" type="hidden" />
 <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
 <table class="table-select" style="font-size: 12px;">
        <tbody>
            <tr> 
            	<td align="right" width="10%">订单号：</td>
                <td width="10%">
                   <input type="text" id="orderId" name="orderId" value="${orderId}" maxlength="32"  style="width: 170px;"/>
                </td>
                <td align="right" width="10%">通道订单号：</td>
                <td width="10%">
                   <input type="text" id="pipleOrderId" name="pipleOrderId" value="${pipleOrderId}" maxlength="50"  style="width: 170px;"/>
                </td>
            	<td align="right"  width="10%">手机号：</td>
                <td width="10%">
                   <input type="text" id="mobile" name="mobile" value="${mobile}" maxlength="11"  style="width: 150px;"/>
                </td>          	
            	<td align="right"  width="10%">IMSI：</td>
                <td width="30%"  colspan="2">
                   <input type="text" id="imsi" name="imsi" value="${imsi}" maxlength="50"  style="width: 150px;"/>
                </td> 
            </tr>
            <tr>
            	<td align="right">通道：</td>
                <td>
                   <select name="pipleId" id="pipleId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${piples}" var="s">
                    		<option value="${s.pipleId}" <c:if test="${s.pipleId==order.pipleId}">selected="selected"</c:if> >${s.pipleNumber}-${s.pipleName}</option>
                    	</c:forEach>
                   </select>
                </td>
                
                <td align="right">渠道：</td>
                <td>
                   <select name="channelId" id="channelId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${channels}" var="s">
                    		<option value="${s.channelId}" <c:if test="${s.channelId==order.channelId}">selected="selected"</c:if> >${s.apiKey}-${s.fullName}</option>
                    	</c:forEach>
                   </select>
                </td>
            	
                <td align="right">开始日期：</td>
                <td>
                   <input style="width: 100px;" name="startTime" id="startTime" value="${startTime}" type="text" class="inputText date_class"/>
                </td>
               
                <td align="right">结束日期： </td>
                <td colspan="2">
                    <input style="width: 100px;" name="endTime" id="endTime" value="${endTime}" type="text" class="inputText date_class"/>
                </td>
            </tr>
            <tr>
            	<td align="right">省份：</td>
                <td>
                   <select name="provinceId" id="provinceId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${provinces}" var="s">
                    		<option value="${s.provinceId}" <c:if test="${s.provinceId==order.provinceId}">selected="selected"</c:if> >${s.provinceName}</option>
                    	</c:forEach>
                   </select>
                </td>
            	<td align="right">订单状态：</td>
                <td>
                   <select name="orderStatus" id="orderStatus" class="selectText">
                    	<option value=""  <c:if test="${empty order.orderStatus}">selected="selected"</c:if>>请选择</option>
                		<option value="0" <c:if test="${order.orderStatus==0}">selected="selected"</c:if>>未完成</option> 
                		<option value="1" <c:if test="${order.orderStatus==1}">selected="selected"</c:if>>交易中</option>
                		<option value="2" <c:if test="${order.orderStatus==2}">selected="selected"</c:if>>交易成功</option>
                		<option value="3" <c:if test="${order.orderStatus==3}">selected="selected"</c:if>>交易失败</option>
                   </select>
                </td>
                <td align="right">流程状态：</td>
                <td>
                   <select name="subStatus" id="subStatus" class="selectText">
                    	<option value=""  <c:if test="${empty order.subStatus}">selected="selected"</c:if>>请选择</option>
                		<option value="0" <c:if test="${order.subStatus==0}">selected="selected"</c:if>>初始化</option> 
                		<option value="1" <c:if test="${order.subStatus==1}">selected="selected"</c:if>>获取验证码成功</option>
                		<option value="2" <c:if test="${order.subStatus==2}">selected="selected"</c:if>>获取验证码失败</option>
                		<option value="11" <c:if test="${order.subStatus==11}">selected="selected"</c:if>>截取本地短信成功</option>
                		<option value="12" <c:if test="${order.subStatus==12}">selected="selected"</c:if>>截取本地短信失败</option>
                		<option value="13" <c:if test="${order.subStatus==13}">selected="selected"</c:if>>提交验证码成功</option>
                		<option value="14" <c:if test="${order.subStatus==14}">selected="selected"</c:if>>提交验证码失败</option>
                		<option value="15" <c:if test="${order.subStatus==15}">selected="selected"</c:if>>发送短信指令成功</option>
                		<option value="16" <c:if test="${order.subStatus==16}">selected="selected"</c:if>>发送短信指令失败</option>
                		<option value="3" <c:if test="${order.subStatus==3}">selected="selected"</c:if>>支付成功</option>
                		<option value="4" <c:if test="${order.subStatus==4}">selected="selected"</c:if>>支付失败</option>
                   </select>
                </td>
                <td align="right">扣量状态：</td>
                <td>
                   <select name="decStatus" id="decStatus" class="selectText">
                    	<option value="" <c:if test="${empty order.decStatus}">selected="selected"</c:if>>请选择</option>
                    	<option value="0" <c:if test="${order.decStatus==0}">selected="selected"</c:if>>未扣量</option>
                    	<option value="1" <c:if test="${order.decStatus==1}">selected="selected"</c:if>>已扣量</option>
                   </select>
                </td>
                 <td style="text-align: left;">
                	<input class="defaultBut" type="button" value="查询" id="search_But"  />
                <input class="defaultBut" type="button" value="导出Excel" id="export_But" /> 
                </td>
            </tr>
        </tbody>
    </table>
 </form>
    <table  class="table-content"  cellpadding="0" cellspacing="0">
    	<thead>
    		<tr>
        		<th width="10%" style="text-align: center;font-weight: bold;">订单号</th>
        		<th width="14%" style="text-align: center;font-weight: bold;">通道订单号</th>
        		<th width="9%" style="text-align: center;font-weight: bold;">所属通道</th>
        		<th width="9%" style="text-align: center;font-weight: bold;">所属渠道</th>
        		<th width="6%" style="text-align: center;font-weight: bold;">所属省份</th>
        		<th width="6%" style="text-align: center;font-weight: bold;">手机号码</th>
        		<th width="6%" style="text-align: center;font-weight: bold;">IMSI</th>
        		<th width="3%" style="text-align: center;font-weight: bold;">金额</th>
        		<th width="4%" style="text-align: center;font-weight: bold;">返回码</th>
        		<th width="5%" style="text-align: center;font-weight: bold;">订单状态</th>
        		<th width="8%" style="text-align: center;font-weight: bold;">流程状态</th>
        		<th width="5%" style="text-align: center;font-weight: bold;">扣量状态</th>
        		<th width="5%" style="text-align: center;font-weight: bold;">请求时间</th>
        		<th width="5%" style="text-align: center;font-weight: bold;">完成时间</th>
        		<th width="5%" style="text-align: center;font-weight: bold;">操作</th>
        	</tr>
    	</thead>
        <tbody>
        	<c:forEach items="${orders}" var="o">
        	<tr>
	        	<td>${o.orderId}&nbsp;</td>
	        	<td>
	        		<c:choose>
						<c:when test="${empty o.pipleOrderId}">无</c:when>
                		<c:when test="${o.pipleOrderId!=null}">${o.pipleOrderId }</c:when>
                	</c:choose>
	        	</td>
	        	<td style="text-align: left;">${o.pipleName}</td>
	        	<td style="text-align: left;">${o.channelName}</td>
	        	<td>${o.provinceName}</td>
				<td>${o.mobile}&nbsp;</td>
				<td>
					<c:choose>
						<c:when test="${empty o.imsi}">无</c:when>
                		<c:when test="${o.imsi!=null}">${o.imsi }</c:when>
                	</c:choose>
				</td>
				<td>${o.amount}&nbsp;</td>
				<td>
					<c:choose>
						<c:when test="${empty o.resultCode}">无</c:when>
                		<c:when test="${o.resultCode!=null}">${o.resultCode }</c:when>
                	</c:choose>
				</td>
				<td>
					<c:choose>
                		<c:when test="${o.orderStatus==0}">未完成</c:when>
                		<c:when test="${o.orderStatus==1}">交易中</c:when>
                		<c:when test="${o.orderStatus==2}">交易成功</c:when>
                		<c:when test="${o.orderStatus==3}">交易失败</c:when>
                	</c:choose>
				</td>
				<td>
					 <c:choose>
                		<c:when test="${o.subStatus==0}">初始化</c:when>
                		<c:when test="${o.subStatus==1}">获取验证码成功</c:when>
                		<c:when test="${o.subStatus==2}">获取验证码失败</c:when>
                		<c:when test="${o.subStatus==11}">截取本地短信成功</c:when>
                		<c:when test="${o.subStatus==12}">截取本地短信失败</c:when>
                		<c:when test="${o.subStatus==13}">提交验证码成功</c:when>
                		<c:when test="${o.subStatus==14}">提交验证码失败</c:when>
                		<c:when test="${o.subStatus==15}">发送短信指令成功</c:when>
                		<c:when test="${o.subStatus==16}">发送短信指令失败</c:when>
                		<c:when test="${o.subStatus==3}">支付成功</c:when>
                		<c:when test="${o.subStatus==4}">支付失败</c:when>
                	</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty o.decStatus}">未扣量</c:when>
                		<c:when test="${o.decStatus==0}">未扣量</c:when>
                		<c:when test="${o.decStatus==1}">已扣量</c:when>
                	</c:choose>
				</td>
				<td><fmt:formatDate value="${o.createTime}" type="both"/></td>
				<td>
					<c:choose>
						<c:when test="${empty o.completeTime}">无</c:when>
                		<c:when test="${o.completeTime!=null}"><fmt:formatDate value="${o.completeTime}" type="both"/></c:when>
                	</c:choose>
				</td>
				<td>
					<input type="button" class="def_but"  onclick="javascript:document.location.href='<%=request.getContextPath()%>/control/getOrderInfo.do?orderId=${o.orderId}'"  value="详情" />
				</td>
			</tr>  	
        	</c:forEach>
        	<c:if test="${empty orders}">
      			<tr>
      				<td colspan="15" id="empty_list_td" >未查询到记录...</td>
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
