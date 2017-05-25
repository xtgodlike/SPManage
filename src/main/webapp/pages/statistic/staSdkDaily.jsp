<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
    <title>计费平台-SDK操作日报</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/page.jsp" />
    <jsp:include page="../basics/datetimepicker.jsp" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dateTool.js" ></script>
    <script type="text/javascript">
    $(document).ready(function () {
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
    		jQuery("#frm").attr("action", "<%=path%>/statistic/toStaSdkDaily");
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
     	    	var url = "<%=path%>/statistic/toStaSdkDailyReport?startTime="+jQuery("#startTime").val()+"&endTime="+jQuery("#endTime").val()+"&appId="+jQuery("#appId").val()+"&channelId="+jQuery("#channelId").val()+"&provinceId="+jQuery("#provinceId").val();
	     	   	document.location.href=url;
     	    }
    	});
    	
    });
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">运行统计</a>
	</div>   
    <div class="content-mainBG">
    <form action="<%=path%>/statistic/toStaSdkDaily" id="frm" name="frm" method="post">
    <input id="operation" name="operation" type="hidden" />
    <table class="table-select" style="font-size: 12px;">
        <tbody>
            <tr> 
                           	
            	<td align="right">APP：</td>
                <td>
                   <select name="appId" id="appId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${apps}" var="s">
                    		<option value="${s.appId}" <c:if test="${s.appId==param.appId}">selected="selected"</c:if> >${s.appName}</option>
                    	</c:forEach>
                   </select>
                </td>
                
                <td align="right">渠道：</td>
                <td>
                   <select name="channelId" id="channelId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${channels}" var="s">
                    		<option value="${s.channelId}" <c:if test="${s.channelId==param.channelId}">selected="selected"</c:if> >${s.apiKey}-${s.fullName}</option>
                    	</c:forEach>
                   </select>
                </td>
                <td align="right">省份：</td>
                <td>
                   <select name="provinceId" id="provinceId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${provinces}" var="s">
                    		<option value="${s.provinceId}" <c:if test="${s.provinceId==param.provinceId}">selected="selected"</c:if> >${s.provinceName}</option>
                    	</c:forEach>
                   </select>
                </td>
            	</tr>
            	<tr>
                <td align="right">开始日期：</td>
                <td>
                   <input style="width: 100px;" name="startTime" id="startTime"  value="${startTime}"  type="text" class="inputText date_class"/>
                </td>
               
                <td align="right">结束日期： </td>
                <td>
                    <input style="width: 100px;" name="endTime" id="endTime"  value="${endTime}"  type="text" class="inputText date_class"/>
                </td>
                <td>&nbsp;</td>
                <td style="text-align: left;">
                	<input class="defaultBut" type="button" value="查询" id="search_But"  />&nbsp;&nbsp;&nbsp;
                	<input class="defaultBut" type="button" value="导出Excel" id="export_But" />
                </td>
            </tr>
        </tbody>
    </table>
	</form>

    <table class="table-content" id="t_table_head" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
            	<th rowspan="2" width="5%">统计日期</th>
            	<th rowspan="2" width="7%">APP</th>
            	<th rowspan="2" width="8%">渠道</th>
            	<th rowspan="2" width="5%">省份</th>
            	<th rowspan="2" width="5%">启动<br/>用户</th>
            	<th rowspan="2" width="5%">新增<br/>用户</th>
            	<th rowspan="2" width="5%">付费请求<br/>用户</th>
            	<th rowspan="2" width="5%">付费成功<br/>用户</th>
            	<th rowspan="2" width="5%">移动付费<br/>用户</th>
            	<th rowspan="2" width="5%">移动付费<br/>成功率</th>
            	<th rowspan="2" width="5%">联通付费<br/>用户</th>
            	<th rowspan="2" width="5%">联通付费<br/>成功率</th>
            	<th rowspan="2" width="5%">电信付费<br/>用户</th>
            	<th rowspan="2" width="5%">电信付费<br/>成功率</th>
            	<th rowspan="2" width="5%">信息费</th>
            	<th rowspan="2" width="5%">付费率</th>
            	<th rowspan="2" width="5%">付费成功率</th>
            	<th rowspan="2" width="5%">转化率</th>
            	<th rowspan="2" width="5%">ARPU值</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${staSdkde.staSdkDailies}" var="d">
        	<tr>
        		<td style="text-align: center;"><fmt:formatDate value="${d.compDate}" type="date"/></td>
        		<td style="text-align: left;">${d.appName}</td>
        		<td style="text-align: left;">${d.channelName}</td>
        		<td style="text-align: left;">${d.provinceName}</td>
        		<td>${d.startUserNum}</td>
        		<td>${d.addUserNum}</td>
        		<td>${d.payreqUserNum}</td>
        		<td>${d.paysucUserNum}</td>
        		<td>${d.cmPuserNum}</td>
        		<td>
        				<c:if test="${d.cmPaysucRatio==0}">无</c:if>
        				<c:if test="${d.cmPaysucRatio!=0}"><fmt:formatNumber value="${d.cmPaysucRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>${d.uniPuserNum}</td>
        		<td>
        			<c:if test="${d.uniPaysucRatio==0}">无</c:if>
        			<c:if test="${d.uniPaysucRatio!=0}"><fmt:formatNumber value="${d.uniPaysucRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>${d.telePuserNum}</td>
        		<td>
        				<c:if test="${d.telePaysucRatio==0}">无</c:if>
        				<c:if test="${d.telePaysucRatio!=0}"><fmt:formatNumber value="${d.telePaysucRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<fmt:formatNumber value="${d.infoFee}" type="currency" pattern="#0.00"/> 
        		</td>
        		<td>
        			<c:if test="${d.payRatio==0}">无</c:if>
        			<c:if test="${d.payRatio!=0}"><fmt:formatNumber value="${d.payRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<c:if test="${d.paySucRatio==0}">无</c:if>
        			<c:if test="${d.paySucRatio!=0}"><fmt:formatNumber value="${d.paySucRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<c:if test="${d.translateRatio==0}">无</c:if>
        			<c:if test="${d.translateRatio!=0}"><fmt:formatNumber value="${d.translateRatio*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        				<c:if test="${d.arpu==0}">无</c:if>
        			<c:if test="${d.arpu!=0}"><fmt:formatNumber value="${d.arpu}" type="currency" pattern="#0.00"/></c:if>
        		</td>
            </tr>
            </c:forEach>
            <c:if test="${!empty staSdkde.staSdkDailies}">
            	<tr style="background-color: #FFE48D;">
					<td colspan="4" style="text-align: right;font-size: 15px;font-weight: bold;color:white; background-color: #379BE9;">合计：</td>
					<td>${staSdkde.suNumTotal}</td>
					<td>${staSdkde.auNumTotal}</td>
					<td>${staSdkde.pruNumTotal}</td>
					<td>${staSdkde.psuNumTotal}</td>
					<td>${staSdkde.cmPuserNumTotal}</td>
					<td>
        				<c:if test="${staSdkde.cmPaysucRatioTotal==0}">无</c:if>
        				<c:if test="${staSdkde.cmPaysucRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.cmPaysucRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
        			</td>
					<td>${staSdkde.uniPuserNumTotal}</td>
					<td>
	        			<c:if test="${staSdkde.uniPaysucRatioTotal==0}">无</c:if>
	        			<c:if test="${staSdkde.uniPaysucRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.uniPaysucRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
        			</td>
					<td>${staSdkde.telePuserNumTotal}</td>
	        		<td>
	        				<c:if test="${staSdkde.telePaysucRatioTotal==0}">无</c:if>
	        				<c:if test="${staSdkde.telePaysucRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.telePaysucRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
	        		</td>
					<td>
        			<fmt:formatNumber value="${staSdkde.infoFeeTotal}" type="currency" pattern="#0.00"/> 
        		</td>
        		<td>
        			<c:if test="${staSdkde.payRatioTotal==0}">无</c:if>
        			<c:if test="${staSdkde.payRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.payRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<c:if test="${staSdkde.paySucRatioTotal==0}">无</c:if>
        			<c:if test="${staSdkde.paySucRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.paySucRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<c:if test="${staSdkde.translateRatioTotal==0}">无</c:if>
        			<c:if test="${staSdkde.translateRatioTotal!=0}"><fmt:formatNumber value="${staSdkde.translateRatioTotal*100}" type="currency" pattern="#0.00"/>%</c:if>
        		</td>
        		<td>
        			<c:if test="${staSdkde.arpuTotal==0}">无</c:if>
        			<c:if test="${staSdkde.arpuTotal!=0}"><fmt:formatNumber value="${staSdkde.arpuTotal}" type="currency" pattern="#0.00"/></c:if>
        		</td>
      		</tr>
		</c:if>
		<c:if test="${empty staSdkde.staSdkDailies}">		
			<tr>
				<td style="text-align: center;" colspan="19" id="empty_list_td">未查询到记录					
				</td>
			</tr>
		</c:if>
			</tbody>
    </table>
    </div>
</body>
</html>