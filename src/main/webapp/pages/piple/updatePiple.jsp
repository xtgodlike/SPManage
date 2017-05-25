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
	<title>SP计费平台-新增通道</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
    		 formID:"frm",
    		 submitOnce:true,
    		 onError:function(msg,obj,errorlist){
    		 	
    		 },
    		 onSuccess:function(){
    		 	
    		 }
    	});
    	
     	$("#pipleId").formValidator({
       		empty:true,
    		onFocus:"通道ID可为空或固定为23位数字",
    		onCorrect:"正确"
   		}).inputValidator({
   			type:"number",
   			min:10000000000000000000000,
   			max:99999999999999999999999,
   			onError:"通道ID可为空或固定为23位数字"
   		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>"+"/piple/verifyPipleId.do?pipleNumber="+jQuery("#pipleNumber").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#addPiple"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "已存在，验证失败",
			onWait : "正在对通道ID进行合法性校验，请稍候..."
		});
    	
    	$("#pipleName").formValidator({
    		onShow:"",
    		onFocus:"通道名称至少3个字符,最多20个字符",
    		onCorrect:"可用"
		}).inputValidator({
			min:3,
			max:20,
			onError:"通道名称至少3个字符,最多20个字符"
		}).ajaxValidator({
			dataType : "html",
			async : true,
			url : "<%=request.getContextPath()%>"+"/piple/verifyPipleName.do?pipleId="+jQuery("#pipleId").val(),
			success : function(data){
	            if( data.indexOf("OK") != -1 ) 
	            	return true;
	            if( data.indexOf("FAIL") !=-1 ) 
	            	return false;
				return false;
			},
			buttons: $("#addPiple"),
			error: function(jqXHR, textStatus, errorThrown){
				alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
			},
			onError : "已存在，验证失败",
			onWait : "正在对通道名称进行合法性校验，请稍候..."
		});
    	
    	$("#piplef").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"file",
    		dataType:"enum",
    		onError:"请选择文档文件(文件格式：doc|docx|xls|xlsx|pdf)"
    	});
    	
    	$("#channelf").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"file",
    		dataType:"enum",
    		onError:"请选择文档文件(文件格式：doc|docx|xls|xlsx|pdf)"
    	});
    	
    	$("#pluginf").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"plugin",
    		dataType:"enum",
    		onError:"请选择插件(文件格式：zip)"
    	});
    	
    	$("#pluginVersion").formValidator({
       		empty:true,
    		onFocus:"版本号最多10个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:10,
   			onError:"版本号最多10个字符"
   		});
    	
    	$("#testPluginf").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"plugin",
    		dataType:"enum",
    		onError:"请选择插件(文件格式：zip)"
    	});
    	
       	$("#pipleUrlA").formValidator({
       		empty:true,
    		onFocus:"URL最多500个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"URL最多500个字符"
   		});
    	
       	$("#pipleUrlB").formValidator({
       		empty:true,
    		onFocus:"URL最多500个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"URL最多500个字符"
   		});
       	
       	$("#notifyUrlA").formValidator({
       		empty:true,
    		onFocus:"URL最多500个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"URL最多500个字符"
   		});
       	
       	$("#notifyUrlB").formValidator({
       		empty:true,
    		onFocus:"URL最多500个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"URL最多500个字符"
   		});
       	
       	$("#pipleAuthA").formValidator({
       		empty:true,
    		onFocus:"授权值最多100个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"授权值最多100个字符"
   		});
       	
       	$("#pipleAuthB").formValidator({
       		empty:true,
    		onFocus:"授权值最多100个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"授权值最多100个字符"
   		});
       	
       	$("#pipleAuthC").formValidator({
       		empty:true,
    		onFocus:"授权值最多100个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"授权值最多100个字符"
   		});
       	
       	$("#pipleAuthD").formValidator({
       		empty:true,
    		onFocus:"授权值最多100个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:500,
   			onError:"授权值最多100个字符"
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
       	
    });	
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a>通道管理</a>
			<span>></span>
			<a class="home_current">更新通道</a>
	</div>  
	<div class="content-mainBG">
	<form action="<%=request.getContextPath()%>/piple/doUpdatePiple.do" id="frm" name="frm" method="post" enctype="multipart/form-data">
    <table class="table-add" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        	<tr>
                <td width="40%" align="right">通道ID：</td>
                <c:if test="${piple==null}">
                <td width="20%">
                    <input name="pipleId" id="pipleId" type="text" class="inputText" /><font style="font-size: 15px;color: red">（可指定ID，不填写则自动生成）</font>
                </td>
                <td width="40%">
                	<div id="pipleIdTip"></div>
                </td>
                </c:if>
                <c:if test="${piple!=null}">
                <td width="20%" >
                <input name="pipleId" id="pipleId" type="text"  class="inputText"   style="display: none;" value="${piple.pipleId}"/>
                ${piple.pipleId}
                </td>
                 <td width="40%" style="display: none;">
                	<div id="pipleIdTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right">通道编号：</td>
                <c:if test="${piple==null}">
                <td width="20%" colspan="2">
                   <font style="font-size: 15px;color: red">自动生成</font>
                </td>
                </c:if>
                <c:if test="${piple!=null}">
                <td width="60%" colspan="2">
                <input type="hidden" id="pipleNumber"  name="pipleNumber" value=" ${piple.pipleNumber}"/>
                    ${piple.pipleNumber}
                </td>
                </c:if>
           </tr>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道名称：</td>
                <c:if test="${piple==null}">
                <td width="20%">
                    <input name="pipleName" id="pipleName" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="pipleNameTip"></div>
                </td>
                </c:if>
                <c:if test="${piple!=null}">
                <td width="20%">
                    <input name="pipleName" id="pipleName" type="text" class="inputText" style="display: none;" value="${piple.pipleName}"  />
                    ${piple.pipleName}
                </td>
                <td width="40%" style="display: none;">
                	<div id="pipleNameTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>所属供应商：</td>
                <td width="60%" colspan="2">
                    <select name="supplierId" id="supplierId" class="selectText">
                    	<c:forEach items="${suppliers}" var="s">
                    		<option value="${s.supplierId}"  <c:if test="${piple.supplierId==s.supplierId }">selected="selected"</c:if>
                    		>${s.fullName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>所属运营商：</td>
                <td width="60%" colspan="2">
                    <select name="hostId" id="hostId" class="selectText">
                    	<c:forEach items="${hosts}" var="s">
                    		<option value="${s.hostId}"  <c:if test="${piple.hostId==s.hostId }">selected="selected"</c:if>
                    		>${s.hostName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道类型：</td>
                <td width="60%" colspan="2">
                    <select name="pipleType" id="pipleType" class="selectText">
                    	<c:forEach items="${pipleTypes}" var="s">
                    		<option value="${s.dicCode}"  <c:if test="${piple.pipleType==s.dicCode }">selected="selected"</c:if>
                    		>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>代码类型：</td>
                <td width="60%" colspan="2">
                    <select name="codeType" id="codeType" class="selectText">
                    	<c:forEach items="${codeTypes}" var="s">
                    		<option value="${s.dicCode}"  <c:if test="${piple.codeType==s.dicCode }">selected="selected"</c:if>
                    		>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr> 
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>结算类型：</td>
                <td width="60%" colspan="2">
                    <select name="calcType" id="calcType" class="selectText">
                    	<c:forEach items="${calcTypes}" var="s">
                    		<option value="${s.dicCode}"  <c:if test="${piple.calcType==s.dicCode }">selected="selected"</c:if>
                    		>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>结算比例：</td>
                <td width="20%">
                    <input type="text" class="inputText"  style="width: 50px;" name="settlementNum"    id="settlementNum" 
                    <c:if test="${piple!=null }">value="${piple.settlementRatio*100}"</c:if>
                    />%
                </td>
                <td width="40%">
                	<div id="settlementNumTip"></div>
                </td>
           </tr>
           
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道状态：</td>
                <td width="60%" colspan="2">
                    <select name="opStatus" id="opStatus" class="selectText">
                    	<option value="1" <c:if test="${piple.opStatus==1 }">selected="selected"</c:if>>开通</option>
                    	<option value="0" <c:if test="${piple.opStatus==0 }">selected="selected"</c:if>>关闭</option>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道端文档：</td>
                <td width="20%" >
                	<c:if test="${!empty piple.pipleDoc}"><font style="font-size: 15px;color: green;">(文档已上传，可重传)</font></c:if>
                   <input type="file" style="width: 250px;" class="inputText" name="piplef" id="piplef" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="piplefTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">渠道端文档：</td>
                <td width="20%" >
                	<c:if test="${!empty piple.channelDoc}"><font style="font-size: 15px;color: green;">(文档已上传，可重传)</font></c:if>
                   <input type="file" style="width: 250px;" class="inputText" name="channelf" id="channelf" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="channelfTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">正式插件文件：</td>
                <td width="20%" >
                	<c:if test="${!empty piple.pluginId}"><font style="font-size: 15px;color: green;">(插件已上传，可更新)</font></c:if>
                   	<input type="file" style="width: 250px;" class="inputText" name="pluginf" id="pluginf" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="pluginfTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">正式插件版本：</td>
                <td width="20%" >
                   <input type="text" style="width: 250px;"  name="pluginVersion" id="pluginVersion" value="${piple.pluginVersion}" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="pluginVersionTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right">测试插件文件：</td>
                <td width="20%" >
                	<c:if test="${!empty piple.testPluginId}"><font style="font-size: 15px;color: green;">(插件已上传，可更新)</font></c:if>
                   	<input type="file" style="width: 250px;" class="inputText" name="testPluginf" id="testPluginf" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="testPluginfTip"></div>
                </td>
           </tr>
            <tr>
                <td width="40%" align="right">插件类型：</td>
                <td width="60%" colspan="2">
                    <select name="pluginType" id="pluginType" class="selectText">
                    	<option value="">请选择</option>
                    	<c:forEach items="${pluginTypes}" var="s">
                    		<option value="${s.dicCode}"  <c:if test="${piple.pluginType==s.dicCode }">selected="selected"</c:if>
                    		>${s.dicName }</option>
                    	</c:forEach>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道URL：</td>
                <td width="30%" >
                   A:<input type="text" style="width: 250px;"  name="pipleUrlA" id="pipleUrlA" value="${piple.pipleUrlA}" /><div style="width: 200px;display: inline;" id="pipleUrlATip"></div>
                </td>
                <td width="30%">
                   B:<input type="text" style="width: 250px;" name="pipleUrlB" id="pipleUrlB" value="${piple.pipleUrlB}"/><div style="width: 200px;display: inline;" id="pipleUrlBTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>回调URL：</td>
                <td width="30%" >
                   A:<input type="text" style="width: 250px;" name="notifyUrlA" id="notifyUrlA" value="${piple.notifyUrlA}"/><div style="width: 200px;display: inline;" id="notifyUrlATip"></div>
                </td>
                <td width="30%">
                   B:<input type="text" style="width: 250px;" name="notifyUrlB" id="notifyUrlB" value="${piple.notifyUrlB}"/><div style="width: 200px;display: inline;" id="notifyUrlBTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>通道授权：</td>
                <td width="60%" colspan="2">
                   A:<input type="text" style="width: 250px;" name="pipleAuthA" id="pipleAuthA" value="${piple.pipleAuthA}"/><div style="width: 200px;display: inline;" id="pipleAuthATip"></div></br>
                   B:<input type="text" style="width: 250px;" name="pipleAuthB" id="pipleAuthB" value="${piple.pipleAuthB}"/><div style="width: 200px;display: inline;" id="pipleAuthBTip"></div></br>
                   C:<input type="text" style="width: 250px;" name="pipleAuthC" id="pipleAuthC" value="${piple.pipleAuthC}"/><div style="width: 200px;display: inline;" id="pipleAuthCTip"></div></br>
                   D:<input type="text" style="width: 250px;" name="pipleAuthD" id="pipleAuthD" value="${piple.pipleAuthD}"/><div style="width: 200px;display: inline;" id="pipleAuthDTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="addPiple" value="提交" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
