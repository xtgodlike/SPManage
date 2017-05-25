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
    <title>计费平台-运行统计</title>
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
    		jQuery("#frm").attr("action", "<%=path%>/statistic/toStatistic");
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
	     	   var url = "<%=path%>/statistic/toStatisticReport?startTime="+jQuery("#startTime").val()+"&endTime="+jQuery("#endTime").val()+"&pipleId="+jQuery("#pipleId").val()+"&channelId="+jQuery("#channelId").val();
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
    <form action="<%=path%>/statistic/toStatistic" id="frm" name="frm" method="post">
    <input id="operation" name="operation" type="hidden" />
    <table class="table-select" style="font-size: 12px;">
        <tbody>
            <tr> 
            	<td align="right">通道编号：</td>
                <td>
                	<input type="text" id="pipleNumber" name="pipleNumber" value="${selectPipleNumber}" style="width: 100px;" />
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
            	</tr>
            	<tr>
                <td align="right">开始日期：</td>
                <td>
                   <input style="width: 100px;" name="startTime" id="startTime" value="${startTime}" type="text" class="inputText date_class"/>
                </td>
               
                <td align="right">结束日期： </td>
                <td>
                    <input style="width: 100px;" name="endTime" id="endTime" value="${endTime}" type="text" class="inputText date_class"/>
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
            	<th rowspan="2" width="7%">交易日期</th>
            	<th rowspan="2" width="6%">通道编号</th>
            	<th rowspan="2" width="10%">通道名称</th>
            	<th rowspan="2" width="10%">渠道名称</th>
            	<th rowspan="2" width="5%">总金额</th>
            	<th rowspan="2" width="5%">总笔数</th>
            	
            	<th rowspan="2" width="5%">未完成</br>金额</th>
            	<th rowspan="2" width="5%">未完成</br>笔数</th>
            	<th rowspan="2" width="5%">失败金额</th>
            	<th rowspan="2" width="5%">失败笔数</th>
            	
            	<th rowspan="2" width="5%">扣量后</br>成功金额</th>
            	<th rowspan="2" width="5%">扣量后</br>成功笔数</th>
            	<th rowspan="2" width="5%">扣量金额</th>
            	<th rowspan="2" width="5%">扣量笔数</th>            	
            	<th rowspan="2" width="5%">扣量前</br>成功率</th>
            	<th rowspan="2" width="5%">扣量后</br>成功率</th> 
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${listData}" var="d">
        	<tr>
        		<td style="text-align: center;">${d.compDateString}</td>
        		<td style="text-align: center;">${d.pipleNumber}</td>
        		<td style="text-align: left;">${d.pipleName}</td>
        		<td style="text-align: left;">${d.channelName}</td>
        		<td>
        		<fmt:formatNumber value="${d.amountTotal}" type="currency" pattern="#0.00"/>&nbsp;
        		</td>
        		<td>${d.countToltal}</td>
        		<td>${d.amountW}</td>
        		<td>${d.countW}</td>
        		<td>${d.amountF}</td>
        		<td>${d.countF}</td>
        		<td>
        		<fmt:formatNumber value="${d.amountChannel}" type="currency" pattern="#0.00"/>&nbsp;        		
        		</td>
        		<td>${d.countChannel}</td>
        		<td>
        		<fmt:formatNumber value="${d.amountDeducted}" type="currency" pattern="#0.00"/>&nbsp;        		
        		</td>
        		<td>${d.countDeducted}</td>
        		<td>${d.successRatePreDeducted}</td>
        		<td>${d.successRateAfterDeducted}</td>
            </tr>
            </c:forEach>
				<tr>
				<c:if test="${empty listData}">
					<td style="text-align: center;" colspan="16" id="empty_list_td">未查询到记录					
					</td>
				</c:if>
				</tr>
			</tbody>
    </table>
    </div>
</body>
</html>