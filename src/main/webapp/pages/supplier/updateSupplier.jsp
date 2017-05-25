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
	<title>SP计费平台-新增供应商</title>
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
   				jQuery("#msgTip").attr("class","onCorrect");
   				jQuery("#msgTip").html("请求已提交，请稍候...");
   				var supplierId = jQuery("#supplierId").val();
   				var options = {
   					url: "<%=request.getContextPath()%>/supplier/doUpdateSupplier.do",
   			        success: function (data){
   			        	if(data=="OK"){
   			        		jQuery("#msgTip").attr("class","onCorrect");
   			        		jQuery("#msgTip").html("更新成功！");
   			        		if(supplierId==null || supplierId==""){
   			        			document.location.href="<%=request.getContextPath()%>/supplier/toSupplierList.do";
   			        		}
   			        	}else{
   			        		jQuery("#msgTip").attr("class","onError");
   			        		jQuery("#msgTip").html("更新失败！"+data);
   			        	}
   			        }
   			    };
   				jQuery("#frm").ajaxSubmit(options);
    		 }
    	});
    	
    	
    	$("#fullName").formValidator({
    		onShow:"",
    		onFocus:"供应商名称至少3个字符,最多30个字符",
    		onCorrect:"可用"
		}).inputValidator({
			min:3,
			max:30,
			onError:"供应商名称至少3个字符,最多30个字符"
		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>/supplier/verifySupplierName.do?supplierId="+jQuery("#supplierId").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#confirm"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "系统异常，验证失败",
			onWait : "正在对供应商名称进行合法性校验，请稍候..."
		});
    	
       	$("#abbrName").formValidator({
       		empty:true,
    		onFocus:"供应商简称最多20个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"供应商简称最多20个字符"
   		});
    	
       	$("#contactor").formValidator({
       		onShow:"",
    		onFocus:"联系人不能为空，且不超过15个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:20,
   			onError:"联系人不能为空，且不超过15个字符"
   		});
       	
    	//联系电话
    	$("#tel").formValidator({
    		onShow:"",
    		onFocus:"联系电话必须是7-20位数字,请确认",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:7,
    		max:20,
    		onError:"联系电话必须是7-20位数字,请确认"
    	}).regexValidator({
    		regExp:"num",
    		dataType:"enum",
    		onError:"您输入的联系电话格式不正确"
    	});
    	
    	//邮箱
    	$("#email").formValidator({
    		onShow:"",
    		onFocus:"请注意你输入的email格式",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:100,
    		onError:"email长度不能多于100个字符,请确认"
    	}).regexValidator({
    		regExp:"email",
    		dataType:"enum",
    		onError:"email格式不正确"
    	});
       	
       	$("#qq").formValidator({
       		empty:true,
    		onFocus:"联系QQ不能超过15个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:15,
   			onError:"联系QQ不能超过15个字符"
   		}).regexValidator({
    		regExp:"qq",
    		dataType:"enum",
    		onError:"email格式不正确"
    	});
       	
    });	
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>供应商管理</a>
			<span>></span>
			<a class="home_current">更新供应商</a>
	</div>
	<div class="content-mainBG" style="min-width: 950px;">
	<form id="frm" name="frm" method="post">
	<input type="hidden" id="supplierId" name="supplierId" value="${supplier.supplierId}" />
    <table class="table-add" width="100%" border="0" cellpadding="0" cellspacing="0" style="min-width: 950px;">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>供应商全称：</td>
                <c:if test="${supplier==null}">
                <td width="20%">
                    <input name="fullName" id="fullName" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="fullNameTip"></div>
                </td>
                </c:if>
                <c:if test="${supplier!=null}">
                <td width="20%">
                    <input name="fullName" id="fullName" type="text" class="inputText" value="${supplier.fullName}" readonly="readonly"  />
                </td>
                <td width="40%">
                	<div id="fullNameTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right">供应商简称：</td>
                <c:if test="${supplier==null}">
                <td width="20%">
                    <input name="abbrName" id="abbrName" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="abbrNameTip"></div>
                </td>
                </c:if>
                <c:if test="${supplier!=null}">
                <td width="20%">
                    <input name="abbrName" id="abbrName" type="text" class="inputText" value="${supplier.abbrName}"  />
                </td>
                <td width="40%">
                	<div id="abbrNameTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>联系人：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="contactor" id="contactor" value="${supplier.contactor}" />
                </td>
                <td width="30%">
                	<div id="contactorTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>联系电话：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="tel" id="tel" value="${supplier.tel}" />
                </td>
                <td width="30%">
                	<div id="telTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>联系email：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="email" id="email" value="${supplier.email}" />
                </td>
                <td width="30%">
                	<div id="emailTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">联系QQ：</td>
                <td width="30%" >
                   <input type="text" style="width: 250px;" name="qq" id="qq" value="${supplier.qq}" />
                </td>
                <td width="30%">
                	<div id="qqTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="3">
                    <div  id="msgTip" style="width: 40%;margin: 0 auto;"></div> 
                 </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="confirm" value="提交" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
