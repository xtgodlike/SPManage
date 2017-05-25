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
	<title>SDK任务管理-交叉配置</title>
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
 			var taskId = jQuery("#taskId").val();
 			var options = {
 				url: "<%=request.getContextPath()%>/sdk/doUpdateSdkTaskGlobal.do",
 		        success: function (data){
 		        	if(data=="OK"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		jQuery("#msgTip").html("更新配置成功！");
 		        		closeEigsx();
 		        		parent.document.location.href="<%=request.getContextPath()%>/sdk/toTaskGlobalList.do?taskId="+taskId;
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
    	
    	jQuery("#closeWin").live('click', function(event){
    		closeEigsx();
		});
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="taskId" name="taskId" value="${taskId}"/>
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>执行步骤:</td>
                <td width="60%" colspan="2">
                    <select id="taskStep" name="taskStep">
                    	<option value="">全部</option>
                    	<c:forEach items="${taskSteps}" var="t">
                    		<option value="${t.dicCode}"<c:if test="${t.dicCode==stc.taskStep}">selected="selected"</c:if>>${t.dicName}</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>是否执行：</td>
                <td width="60%" colspan="2">
                    <select name="taskExecute" id="taskExecute" class="selectText">
                    	<option value="1" <c:if test="${stg.taskExecute==1 }">selected="selected"</c:if>>是</option>
                    	<option value="0" <c:if test="${stg.taskExecute==0 }">selected="selected"</c:if>>否</option>
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
                <td colspan="7">
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
