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
	<title>SP计费平台-修改合同</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
    <jsp:include page="../basics/datetimepicker.jsp" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dateTool.js" ></script>
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
   				var channelId = jQuery("#channelId").val();
   				var _url = "<%=request.getContextPath()%>/channel/doUpdateChannelContract.do";
   				var options = {
   					url: _url,
   			        success: function (data){
   			        	if(data=="OK"){
   			        		jQuery("#msgTip").attr("class","onCorrect");
   			        		jQuery("#msgTip").html("更新成功！");
   			        		alert("更新成功！");
   			        		closeEigsx();
   			        		parent.document.location.href="<%=request.getContextPath()%>/channel/toChannelContractlList.do?channelId="+channelId;
   			        	}else{
   			        		jQuery("#msgTip").attr("class","onError");
   			        		jQuery("#msgTip").html("更新失败！"+data);
   			        	}
   			        }
   			    };
   				jQuery("#frm").ajaxSubmit(options);
   				return false;
    		 }
    	});
    	
    	
    	jQuery('.date_class').datetimepicker({
			language: 'zh-CN',
			//startView: "year", //默认打开视图
			format: "yyyy-mm-dd",
			autoclose:true,
	        minView: "month" //选择日期后，不会再跳转去选择时分秒 
	    });
    	
    	
    	$('.date_class').datetimepicker().on('changeDate', function(ev){
    	    var validateStart = jQuery("#validateStart").val();
    	    var validateEnd = jQuery("#validateEnd").val();
    	    var signDate = jQuery("#signDate").val();
			var i = DateDiff(validateStart, validateEnd);
			var j = DateDiff(signDate, validateStart);
			if(i==-1){
				//jQuery("#empty_list_td").html("开始日期与结束日期间隔不能大于40天");
				alert("日期选择错误！开始日期需在结束日期之前！");
				jQuery(this).val("");
			}
			if(j==-1){
				//jQuery("#empty_list_td").html("开始日期与结束日期间隔不能大于40天");
				alert("日期选择错误！签署日期不能晚于开始日期！");
				jQuery(this).val("");
			}
    	});
    	
       	$("#partyA").formValidator({
       		onShow:"",
    		onFocus:"不能为空且不超过200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:200,
   			onError:"不能为空且不超过200个字符"
   		});
       	
       	$("#partyB").formValidator({
       		onShow:"",
    		onFocus:"不能为空且不超过200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:1,
   			max:200,
   			onError:"不能为空且不超过200个字符"
   		});
       	
       	$("#partyC").formValidator({
       		onShow:"",
    		onFocus:"不超过200个字符",
    		onCorrect:"正确"
   		}).inputValidator({
   			min:0,
   			max:200,
   			onError:"不超过200个字符"
   		});
       	
       	
    	$("#contractf").formValidator({
    		empty:true,
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"pdf",
    		dataType:"enum",
    		onError:"请选择PDF格式文件"
    	});
    	
    	jQuery("#closeWin").live('click', function(event){
    		closeEigsx();
		});
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="channelId" name="channelId" value="${channelId}" />
	<input type="hidden" id="contractId" name="contractId" value="${contract.contractId}" />
    <table class="table-add" style="min-width: 950px;" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>甲方：</td>
                <c:if test="${contract==null}">
                <td width="20%">
                    <input name="partyA" id="partyA" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="partyATip"></div>
                </td>
                </c:if>
                <c:if test="${contract!=null}">
                <td width="20%">
                    <input name="partyA" id="partyA" type="text" class="inputText" value="${contract.partyA}"  />
                </td>
                <td width="40%">
                	<div id="partyATip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>乙方：</td>
                <c:if test="${contract==null}">
                <td width="20%">
                    <input name="partyB" id="partyB" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="partyBTip"></div>
                </td>
                </c:if>
                <c:if test="${contract!=null}">
                <td width="20%">
                    <input name="partyB" id="partyB" type="text" class="inputText" value="${contract.partyB}"  />
                </td>
                <td width="40%">
                	<div id="partyBTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right">丙方：</td>
                <c:if test="${contract==null}">
                <td width="20%">
                    <input name="partyC" id="partyC" type="text" class="inputText" />
                </td>
                <td width="40%">
                	<div id="partyCTip"></div>
                </td>
                </c:if>
                <c:if test="${contract!=null}">
                <td width="20%">
                    <input name="partyC" id="partyC" type="text" class="inputText" value="${contract.partyC}"  />
                </td>
                <td width="40%">
                	<div id="partyCTip"></div>
                </td>
                </c:if>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>有效期开始：</td>
                <td width="20%">
                	<input style="width: 100px;" name="validateStart" id="validateStart"  value="<fmt:formatDate value='${contract.validateStart}' type='date'/>" type="text" class="inputText date_class"/>
                </td>
                <td width="40%">
                	<div id="validateStartTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>有效期结束：</td>
                 <td width="20%">
                	<input style="width: 100px;" name="validateEnd" id="validateEnd" value="<fmt:formatDate value='${contract.validateEnd}' type='date'/>" type="text" class="inputText date_class"/>
                </td>
                <td width="40%">
                	<div id="validateEndTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>签署日期：</td>
                <td width="20%" >
                	<input style="width: 100px;" name="signDate" id="signDate" value="<fmt:formatDate value='${contract.signDate}' type='date'/>" type="text" class="inputText date_class"/>
                </td>
                <td width="40%">
                	<div id="signDateTip"></div>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>合同状态：</td>
                <td width="60%" colspan="2">
                    <select name="opStatus" id="opStatus" class="selectText">
                    	<option value="1" <c:if test="${contract.opStatus==1 }">selected="selected"</c:if>>有效</option>
                    	<option value="0" <c:if test="${contract.opStatus==0 }">selected="selected"</c:if>>无效</option>
                    </select>
                </td>
           </tr>
           <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>合同文件：</td>
                <td width="20%" >
                	<c:if test="${contract!=null}"><font style="font-size: 15px;color: green;">(文件已上传，可重传)</font></c:if>
                   <input type="file" style="width: 250px;" class="inputText" name="contractf" id="contractf" />
                </td>
                <td width="40%">
                	<div style="width: 250px;" id="contractfTip"></div>
                </td>
           </tr>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="3">
                   <div id="msgTip"></div>
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
