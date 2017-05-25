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
	<title>SDK任务管理-更新用户配置</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
   		 formID:"frm",
   		 debug:true,
   		 onError:function(msg,obj,errorlist){
   		 	
   		 },
   		 onSuccess:function(){
   			var mes=confirm("此操作会覆盖填写IMSI的改项配置值，是否确定操作？");
   			if(mes==false) return false;
 			jQuery("#msgTip").attr("class","onCorrect");
 			jQuery("#msgTip").html("请求已提交，请稍候...");
 			var options = {
 				url: "<%=request.getContextPath()%>/sdk/doUpdateSdkTaskPhone.do",
 		        success: function (data){
 		        	if(data=="OK"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		jQuery("#msgTip").html("配置成功!");
 		        		// parent.document.location.href="<%=request.getContextPath()%>/sdk/toTaskList.do";
 		        	}else{
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("操作异常！配置失败！"+data);
 		        	}
 		        }
 		    };
 			jQuery("#frm").ajaxSubmit(options);
 			return false;
   		 }
   		});
    	
       	$("#imeis").formValidator({
       		empty:false,
    		onFocus:"用户IMEI长度不超过1000个字符，多个IMEI号用逗号隔开",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:10,
   			max:1000,
   			onError:"用户IMEI长度不超过1000个字符，多个IMEI号用逗号隔开"
   		});
       	
       	$("#configValue").formValidator({
       		empty:false,
    		onFocus:"配置值最多50个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:50,
   			onError:"配置值最多50个字符"
   		});
    });	
    
 // 删除
    function doDel(){
    	var imeis = jQuery("#imeis").val();
	 	if(imeis==""){
	 		alert("用户IMEI不能为空！");
	 		return false;
	 	}
    	jQuery("#msgTip").attr("class","onCorrect");
			jQuery("#msgTip").html("请求已提交，请稍候...");
			var options = {
				url: "<%=request.getContextPath()%>/sdk/doDelSdkTaskPhone.do?configId="+jQuery("#taskId").val()+"&imeis="+imeis,
		        success: function (data){
		        	if(data=="OK"){
		        		jQuery("#msgTip").attr("class","onCorrect");
		        		jQuery("#msgTip").html("移除成功!");
		        	}else{
		        		jQuery("#msgTip").attr("class","onError");
		        		jQuery("#msgTip").html("移除失败！"+data);
		        	}
		        }
		    };
			jQuery("#frm").ajaxSubmit(options);
			return false;
    }
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>SKD任务管理</a>
			<span>></span>
			<a class="home_current">更新用户配置</a>
	</div> 
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>任务名称：</td>
                <td width="60%" colspan="2" >
                	${sdktask.taskName}
                	<input type="hidden" name="taskId" id="taskId" value="${sdktask.taskId}"/>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>任务说明：</td>
                <td width="60%" colspan="2" >
                 ${sdktask.taskDesc}
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>用户IMEI：</td>
                <td width="30%" >
                 <textarea style="width: 300px;height: 300px;" rows="10" cols="10" id="imeis" name="imeis"></textarea>
                </td>
                <td width="30%">
                	<div id="imeisTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>执行步骤:</td>
                <td width="60%" colspan="2">
                    <select id="taskStep" name="taskStep">
                    	<option value="">全部</option>
                    	<c:forEach items="${taskSteps}" var="t">
                    		<option value="${t.dicCode}">${t.dicName}</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>是否执行：</td>
                <td width="60%" colspan="2">
                    <select name="taskExecute" id="taskExecute" class="selectText">
                    	<option value="1" >是</option>
                    	<option value="0" >否</option>
                    </select>
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
                    <input class="defaultBut" type="submit" id="confirm" value="添加" />
                    <input class="defaultBut" type="button" id="delbtn" onclick="doDel()" value="移除" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
