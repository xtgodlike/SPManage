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
    <title>计费平台-业务统计</title>
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
    	
    	
    	//查询
    	jQuery("#search_But").live('click', function(event){
    		jQuery("#frm").attr("action", "<%=path%>/statistic/toRunSameDay");
 	    	$("#operation").val("1");
 	    	jQuery("#frm").submit();
    	});
    	
    	//导出
    	jQuery("#export_But").live('click', function(event){
    	//	var startTime = jQuery("#startTime").val();
     	//  var endTime = jQuery("#endTime").val();
     	//    if(""==startTime || ""==endTime){
     	//    	jQuery("#empty_list_td").html("请输入日期后再导出！");
     	//    }else{
     	    	var url = "<%=path%>/statistic/getRunDailyReport?"+"pipleId="+jQuery("#pipleId").val()+"&channelId="+jQuery("#channelId").val();
	     	   	document.location.href=url;
     	//    }
    	});
    	
    });
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">当日运行统计</a>
	</div>   
    <div class="content-mainBG">
    <form action="<%=path%>/statistic/toRunSameDay" id="frm" name="frm" method="post">
    <input id="operation" name="operation" type="hidden" />
    <table class="table-select" style="font-size: 12px;">
        <tbody>
            <tr>
            	<td align="right">通道编号：</td>
                <td>
                	<input type="text" id="pipleNumber" name="pipleNumber" value="${selectPipleNumber}"  style="width: 100px;"/>
                </td>
                        	
            	<td align="right">通道：</td>
                <td>
                   <select name="pipleId" id="pipleId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${piples}" var="s">
                    		<option value="${s.pipleId}" <c:if test="${s.pipleId==selectPipleId}">selected="selected"</c:if> >${s.pipleNumber}-${s.pipleName}</option>
                    	</c:forEach>
                   </select>
                </td>
                
                <td align="right">渠道：</td>
                <td>
                   <select name="channelId" id="channelId" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${channels}" var="s">
                    		<option value="${s.channelId}" <c:if test="${s.channelId==selectChannelId}">selected="selected"</c:if> >${s.apiKey}-${s.fullName}</option>
                    	</c:forEach>
                   </select>
                </td>
            	
                <td>
                	<input class="defaultBut" type="button" value="查询" id="search_But"  />
                	<input class="defaultBut" type="button" value="导出Excel" id="export_But" />
                </td>
            </tr>
        </tbody>
    </table>
	</form>

    <table class="table-content" id="t_table_head" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
            	<th rowspan="2" width="6%">通道编号</th>
            	<th rowspan="2" width="12%">通道名称</th>
            	<th rowspan="2" width="12%">渠道名称</th>
            	<th rowspan="2" width="5%">计费点</th>
            	<th rowspan="2" width="8%">总金额</th>
            	<th rowspan="2" width="7%">未完成金额</th>
            	<th rowspan="2" width="7%">未上行金额</th>
            	<th rowspan="2" width="7%">失败金额</th>
            	<th rowspan="2" width="8%">扣量前成功金额</th>            	
            	<th rowspan="2" width="8%">扣量后成功金额</th>
            	<th rowspan="2" width="6%">扣量金额</th>
            	<th rowspan="2" width="7%">扣量前成功率</th>
            	<th rowspan="2" width="7%">扣量后成功率</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${listData}" var="d">
        	<tr>
        		<td style="text-align: center;">${d.pipleNumber}</td>
        		<td style="text-align: left;">${d.pipleName}</td>
        		<td style="text-align: left;">${d.channelName}</td>
        		<td style="text-align: center;">${d.productId}</td>
        		<td><fmt:formatNumber value="${d.amountTotal}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountUnfinished}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountNotUp}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountFail}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountSuccess}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountChannel}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.amountDeducted}" type="currency" pattern="#0.00"/>&nbsp;</td>
        		<td><fmt:formatNumber value="${d.ratePreDeducted*100}" type="currency" pattern="#0.00"/>%&nbsp;</td>
        		<td><fmt:formatNumber value="${d.rateDeducted*100}" type="currency" pattern="#0.00"/>%&nbsp;</td>
            </tr>
            </c:forEach>
				<tr>
				<c:if test="${empty listData}">
					<td style="text-align: center;" colspan="13" id="empty_list_td">未查询到记录					
					</td>
				</c:if>
				</tr>
			</tbody>
    </table>
    </div>
</body>
</html>