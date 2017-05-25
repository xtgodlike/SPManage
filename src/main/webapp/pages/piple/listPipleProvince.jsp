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
<title>SP计费平台-通道管理</title>
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
			 if(!checkPriority()){
				jQuery("#updatePipleProvince").attr("disabled", "disabled");
				jQuery("#msgTip").attr("class","onCorrect");
				jQuery("#msgTip").html("已提交...");
				var options = {				
					    url:"<%=request.getContextPath()%>/piple/doUpdatePipleProvince.do",
					    success: function(data) {
					    	if(data=='OK'){
					    	  jQuery("#msgTip").html("更新成功！");
					      }else{
					    	  jQuery("#updatePiplePro").removeAttr("disabled");
					    	  jQuery("#msgTip").attr("class","onError");
					    	  jQuery("#msgTip").html("更新失败！"+data);
					      }
					    } 
					};		 
				jQuery("#frm").ajaxSubmit(options); 
			 }
		}
	});

	jQuery("._priority").live('blur', function(event){
		var v = jQuery(this).val();
		jQuery(this).val(v.replace(/[^\d]/g,''));
	});
	
	jQuery("._priority").live('keyup', function(event){
		var v = jQuery(this).val();
		jQuery(this).val(v.replace(/[^\d]/g,''));
		if(v>1000 || v<0){ //不能大于1000
			jQuery(this).val("");
		}
	});
	
});	

// 校验优先级不能重复
function checkPriority() {
		var ary = jQuery("._priority").toArray(); 
		var nary = ary.sort();
		for (var i = 0; i < nary.length; i++)
	    {
	        var priority = nary[i].value;
	        for (var j = i+1; j < nary.length; j++)
	        {
	            if (priority == nary[j].value && priority!="") 
	            {
	                alert("有重复优先级"+priority+"！优先级设置不能有重复，请更改！");
	                return true;
	            }
	        }
	    }
	return false;
}

</script>
</head>
<body>
	<div class="home_content_bg">
			<a>通道管理</a>
			<span>></span>
			<a class="home_current">省份列表</a>
	</div>  
	<div class="content-mainBG">
	<div class="titleShow">通道编号-名称：${piple.pipleNumber}-${piple.pipleName}</div>
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}" />
        <table class="table-content" style="width: 50%;margin: auto;" border="0" cellpadding="0" cellspacing="0">
        <thead>
        	<tr>
                <td colspan="5" align="center">
                  <div style="width: 300px;" id="msgTip"></div>
                </td>
                 </td>
            </tr>
        	<tr style="height: 50px;">
                <td colspan="5" align="center">
                    <input class="defaultBut" type="submit" id="updatePipleProvince"  value="提交" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="返回" />
                 </td>
            </tr>
            <tr>
                <th width="50%">省份</th> 
                <th width="50%">是否开通</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${pipleProvinces}" var="p">
        	<tr>
                <td>${p.provinceName}</td>
                <td>
                	<input type="radio" name="opStatus${p.provinceId}" value="0"  <c:if test="${p.opStatus==0}">checked="checked"</c:if> />关闭
                	<input type="radio" name="opStatus${p.provinceId}" value="1"  <c:if test="${p.opStatus==1}">checked="checked"</c:if>/>开通
                </td> 
            </tr>
        	</c:forEach>
        	<c:if test="${empty pipleProvinces}">
        	<tr>
        		<td colspan="3" style="color: red;">无</td>
        	</tr>
        	</c:if>
        </tbody>
    </table>
   </form>
</div>
</body>
</html>
