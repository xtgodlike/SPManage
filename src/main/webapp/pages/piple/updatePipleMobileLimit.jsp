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
	<title>SDK配置管理-更新配置</title>
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
   		// 更新提示信息
  			jQuery("#msgTip").attr("class","onCorrect");
  			jQuery("#msgTip").html("请求已提交，请稍候...");
  			var options = {
  				url: "<%=request.getContextPath()%>/piple/doUpdatePipleMobileLimit.do?",
  		        success: function (data){
  		        	if(data=="OK"){
  		        		jQuery("#msgTip").attr("class","onCorrect");
  		        		jQuery("#msgTip").html("配置成功！");
  		        		closeEigsx();
  		        	}else{
  		        		jQuery("#msgTip").attr("class","onError");
  		        		jQuery("#msgTip").html("配置失败！"+data);
  		        	}
  		        }
  		    };
  			jQuery("#frm").ajaxSubmit(options);
  			return false;
   		 }
   	});
       	
       	
       	$("#tradeDay").formValidator({
       		onShow:"",
    		onFocus:"用户成功金额日限为空或为0则不限制，数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:100,
   			onError:"用户成功金额日限为空或为0则不限制，数值不能大于100"
   		});
       	
       	$("#tradeMonth").formValidator({
       		onShow:"",
    		onFocus:"用户成功金额月限为空或为0则不限制，数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:100,
   			onError:"用户成功金额月限为空或为0则不限制，数值不能大于100"
   		});
       	
    	$("#successNumDay").formValidator({
       		onShow:"",
    		onFocus:"用户成功数日限为空或为0则不限制，数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:100,
   			onError:"用户成功数日限为空或为0则不限制，数值不能大于100"
   		});
    	
    	$("#successNumMonth").formValidator({
       		onShow:"",
    		onFocus:"用户成功数月限为空或为0则不限制，数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:100,
   			onError:"用户成功数月限为空或为0则不限制，数值不能大于100"
   		});
    	
    	$("#reqNumDay").formValidator({
       		onShow:"",
    		onFocus:"用户请求日限为空或为0则不限制，数值不能大于100",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:100,
   			onError:"用户请求日限为空或为0则不限制，数值不能大于100"
   		});
    	
    	$("#reqNumMonth").formValidator({
       		onShow:"",
    		onFocus:"用户请求月限为空或为0则不限制，数值不能大于500",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:0,
   			max:500,
   			onError:"用户请求月限为空或为0则不限制，数值不能大于500"
   		});
       	
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm"  method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道编号：</td>
                <td width="30%"  colspan="2">
                   ${piple.pipleNumber}
                   <input type="hidden"  id="pipleId" name="pipleId" value="${piple.pipleId }" />
                 </td>
           </tr>
           <tr>
                <td width="40%" align="right">通道名称：</td>
                <td width="30%"  colspan="2">
                   ${piple.pipleName}
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">用户成功金额日限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="tradeDay" id="tradeDay" value="${pMobileLimit.tradeDay}" />
                </td>
                <td width="30%">
                	<div id="tradeDayTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">用户成功金额月限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="tradeMonth" id="tradeMonth" value="${pMobileLimit.tradeMonth}" />
                </td>
                <td width="30%">
                	<div id="tradeMonthTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right">用户成功数日限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="successNumDay" id="successNumDay" value="${pMobileLimit.successNumDay}" />
                </td>
                <td width="30%">
                	<div id="successNumDayTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right">用户成功数月限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="successNumMonth" id="successNumMonth" value="${pMobileLimit.successNumMonth}" />
                </td>
                <td width="30%">
                	<div id="successNumMonthTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">用户请求数日限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="reqNumDay" id="reqNumDay" value="${pMobileLimit.reqNumDay}" />
                </td>
                <td width="30%">
                	<div id="reqNumDayTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right">用户请求数月限：</td>
                <td width="30%" >
                	<input type="text" style="width: 250px;" name="reqNumMonth" id="reqNumMonth" value="${pMobileLimit.reqNumMonth}" />
                </td>
                <td width="30%">
                	<div id="reqNumMonthTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                	<div id="msgTip" style="width: 40%;margin: 0 auto;"></div>
                	<input class="defaultBut" type="submit" id="confirm" value="提交" />
                    <input class="defaultBut" type="button" onclick="closeEigsx()" value="关闭" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
