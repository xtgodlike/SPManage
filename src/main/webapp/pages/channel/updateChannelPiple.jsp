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
	<title>通道管理-分配渠道</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
   		 formID:"frm",
   		 debug:true,
   		 onError:function(msg,obj,errorlist){
   		 	
   		 },
   		 onSuccess:function(){
   			
 			jQuery("#msgTip").attr("class","onCorrect");
 			jQuery("#msgTip").html("请求已提交，请稍候...");
 			var channelId = jQuery("#channelId").val();
 			var options = {
 				url: "<%=request.getContextPath()%>/channel/doUpdatePipleChannel.do",
 		        success: function (data){
 		        	if(data=="OK"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		jQuery("#msgTip").html("分配成功！");
 		        		closeEigsx();
 		        		parent.document.location.href="<%=request.getContextPath()%>/channel/toChannelPipleList.do?channelId="+channelId;
 		        	}else{
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("分配失败！"+data);
 		        	}
 		        }
 		    };
 			jQuery("#frm").ajaxSubmit(options);
 			return false;
   		 }
   		});
    	
    	$("#notifyUrl").formValidator({
       		empty:true,
    		onFocus:"URL最多500个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:500,
   			onError:"URL最多500个字符"
   		});
       	
       	
       	$("#volt").formValidator({
       		onShow:"",
    		onFocus:"扣量值为空或为0则不扣量,数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:10000,
   			onError:"扣量值为空或为0则不扣量,数值不能大于100"
   		});
       	
       	$("#tradeDay").formValidator({
       		onShow:"",
    		onFocus:"日限为空或为0则不限制，数值不能大于100万",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:1000000,
   			onError:"日限为空或为0则不限制，数值不能大于100万"
   		});
       	
       	$("#tradeMonth").formValidator({
       		onShow:"",
    		onFocus:"月限为空或为0则不限制，数值不能大于100万",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:1000000,
   			onError:"月限为空或为0则不限制，数值不能大于100万"
   		});
       	
    	$("#settlementNum").formValidator({
			onShow:"",
			onFocus:"结算比例必须为数字范围为0~100",
			onCorrect:"正确"
		}).inputValidator({
			type:"num",
			min:0,
			max:100,
			onError:"结算比例必须为数字范围为0~100"
		}).regexValidator({
			regExp:"num",
			dataType:"enum",
			onError:"结算比例必须为数字范围为0~100"
		});
		
    	jQuery("#closeWin").live('click', function(event){
    		closeEigsx();
		});
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="channelId" name="channelId" value="${channelId}"/>
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道：</td>
                <td width="60%" colspan="2">
                <c:if test="${piple==null}">
                    <select id="pipleId" name="pipleId">
                    	<c:forEach items="${piples}" var="p">
							<option value="${p.pipleId}">${p.pipleNumber}-${p.pipleName}</option>                    	
                    	</c:forEach>
                    </select>
                </c:if>
                <c:if test="${piple!=null}">
                	${piple.pipleName}
                	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}"/>
                </c:if>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>配置状态：</td>
                <td width="60%" colspan="2">
                    <select name="opStatus" id="opStatus" class="selectText">
                    	<option value="1" <c:if test="${channelPiple.opStatus==1 }">selected="selected"</c:if>>开通</option>
                    	<option value="0" <c:if test="${channelPiple.opStatus==0 }">selected="selected"</c:if>>关闭</option>
                    </select>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>结算比例：</td>
                <td width="20%">
                    <input type="text" class="inputText"  style="width: 50px;" name="settlementNum"    id="settlementNum" 
                    <c:if test="${channelPiple!=null }">value="${channelPiple.settlementRatio*100}"</c:if>
                    />%
                </td>
                <td width="40%">
                	<div id="settlementNumTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>回调地址：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="notifyUrl" id="notifyUrl" value="${channelPiple.notifyUrl}" />
                </td>
                <td width="30%">
                	<div id="notifyUrlTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>扣量阀值：</td>
                <td width="30%" >
                   <input type="text" style="width: 50px;" name="volt" id="volt" value="${channelPiple.volt}" />%
                </td>
                <td width="30%">
                	<div id="voltTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>交易日限：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;display: none;" name="tradeDay" id="tradeDay" value="${channelPiple.tradeDay}"   />
                   <c:if test="${empty  channelPiple.tradeDay}">0</c:if>
                   <c:if test="${!empty  channelPiple.tradeDay}">${channelPiple.tradeDay}</c:if>
                </td>
                <td width="30%">
                	<div id="tradeDayTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>交易月限：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;display: none;" name="tradeMonth" id="tradeMonth" value="${channelPiple.tradeMonth}"  />
                   <c:if test="${empty  channelPiple.tradeMonth}">0</c:if>
                   <c:if test="${!empty  channelPiple.tradeMonth}">${channelPiple.tradeMonth}</c:if>
                </td>
                <td width="30%">
                	<div id="tradeMonthTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
            	<td colspan="3" align="center">
            		<div id="msgTip" style="width: 40%;margin: 0 auto;"></div>
            	</td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="confirm" value="提交" />
                    <input class="defaultBut" type="button" id="closeWin" value="关闭" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
