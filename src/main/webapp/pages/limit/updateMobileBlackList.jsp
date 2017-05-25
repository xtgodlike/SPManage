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
	<title>业务管理-黑名单管理</title>
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
   		/**********
   		var mes=confirm("此操作会覆盖填写IMSI的改项配置值，是否确定操作？");
   			if(mes==false) return false;
 			jQuery("#msgTip").attr("class","onCorrect");
 			jQuery("#msgTip").html("请求已提交，请稍候...");
 			var options = {
 				url: "<%=request.getContextPath()%>/sdk/doUpdateSdkConfigPhone.do",
 		        success: function (data){
 		        	if(data=="OK"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		jQuery("#msgTip").html("配置成功!");
 		        	}else{
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("操作异常！配置失败！"+data);
 		        	}
 		        }
 		    };
 			jQuery("#frm").ajaxSubmit(options);
 			return false;
   		***/
   		 }
   		
   		});
    	
       	$("#mobiles").formValidator({
       		empty:false,
    		onFocus:"用户手机号长度不超过1000个字符，多个手机号用逗号','隔开",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:11,
   			max:1000,
   			onError:"用户手机号长度不超过1000个字符，多个手机号用逗号','隔开"
   		});
       	
    });	
    
 // 导入
    function doUpdate(opType){
    	jQuery("#msgTip").attr("class","onCorrect");
			jQuery("#msgTip").html("请求已提交，请稍候...");
			var mobiles = jQuery("#mobiles").val();
			var options = {
				url: "<%=request.getContextPath()%>/limit/doUpdateMobileBlackList.do?mobiles="+mobiles+"&opType="+opType,
		        success: function (data){
		        	if(data=="ADD"){
		        		jQuery("#msgTip").attr("class","onCorrect");
		        		jQuery("#msgTip").html("添加成功!");
		        	}else if(data=="DEL"){
		        		jQuery("#msgTip").attr("class","onCorrect");
		        		jQuery("#msgTip").html("移除成功!");
		        	}else{
		        		jQuery("#msgTip").attr("class","onError");
		        		jQuery("#msgTip").html("操作异常！"+data);
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
			<a>黑名单设置</a>
	</div>  
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>用户手机号：</td>
                <td width="30%" >
                 <textarea style="width: 300px;height: 300px;" rows="10" cols="10" id="mobiles" name="mobiles"></textarea>
                </td>
                <td width="30%">
                	<div id="mobilesTip"></div>
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
                    <input class="defaultBut" type="button" id="confirm" onclick="doUpdate('ADD')" value="添加" />
                    <input class="defaultBut" type="button" id="confirm" onclick="doUpdate('DEL')" value="移除" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
