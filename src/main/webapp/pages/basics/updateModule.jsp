<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
    <title>SP计费平台-修改模块信息</title>
   	<jsp:include page="baseJs.jsp" />
    <jsp:include page="validator.jsp" />
    <script type="text/javascript">
       $(document).ready(function () {
    	$.formValidator.initConfig({
    		 formID:"frm",
    		 submitOnce:true,
    		 
    		 onError:function(msg,obj,errorlist){
    		 	//alert("验证不通过！");
    		 },
    		 onSuccess:function(){
    		 	
    		 	//return false;
    		 }
    	});
    	
    	//模块名称
    	$("#moduleName").formValidator({
    		onShow:"",
    		onFocus:"请输入模块名称，1~32个字符",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:32,
    		onError:"长度不正确,请确认"
    	});
    	
    	//模块链接
    	$("#moduleLink").formValidator({
    		empty:true,
    		onShow:"",
    		onFocus:"你要是输入了，只能输入1~100个字符",
    		onCorrect:"正确",
    		onEmpty:"您确定不输入模块链接？"
    	}).inputValidator({
    		min:1,
    		max:100,
    		onError:"模块链接支持1~100个字符"
    	});
    	
    	//模块状态
    	$("#status").formValidator({
    		onShow:"",
    		onFocus:"请选择模块状态",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择模块状态"
    	});
    	
    	//模块排列顺序
    	$("#showSeq").formValidator({
    		autoModify:true,
    		onShow:"",
    		onFocus:"只能输入1~99之间的数字哦",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:99,
    		type:"value",
    		onErrorMin:"你输入的值必须大于等于1",
    		onError:"模块排列顺序必须在1~99之间，请确认"
    	});
    	
    	//描述信息（非必录项）
    	$("#moduleDesc").formValidator({
    		empty:true,
    		onShow:"",
    		onFocus:"描述信息可空，你要是输入了，只能输入1~40个字符",
    		onCorrect:"正确",
    		onEmpty:"您确定不输入描述信息？"
    	}).inputValidator({
    		min:1,
    		max:80,
    		onError:"描述信息支持1~40个字符"
    	});
    	
    });
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">修改模块</a>
	</div>  
	
 <div class="content-mainBG">
  <form id="frm" name="frm" action="<%=path%>/basics/doUpdateModule.do" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td width="40%" align="right">父模块：</td>
                <td width="30%">
                	<input type="hidden" name="moduleId" id="moduleId" value="${module.moduleId}"  />
                	<input type="hidden" name="parentModuleId" id="parentModuleId" value="${module.parentMId}"  />
                   ${pModuleName}
                </td>
                <td width="30%">&nbsp;</td>
            </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>模块名称：</td>
                <td>
                     <input name="moduleName" id="moduleName" value="${module.moduleName }" class="inputText" type="text" />
                </td>
                <td>
                	<div id="moduleNameTip"></div>&nbsp;
                </td>
            </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>模块代码：</td>
                <td>
                     ${module.moduleCode }
                </td>
                <td>
                	<div id="moduleNameTip"></div>&nbsp;
                </td>
            </tr>
            <tr>
                <td align="right">模块链接：</td>
                <td>
                    <input name="moduleLink" id="moduleLink" value="${module.moduleLink}" type="text" class="inputText" />
                </td>
                 <td>
                	<div id="moduleLinkTip"></div>&nbsp;
                </td>
            </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>模块状态：</td>
                <td>
                    <select name="status" id="status" class="selectText">
                        <option value="0">--模块状态--</option>
                      	<option value="1" <c:if test="${module.status eq '1'}">selected="selected"</c:if>>激活</option>
                      	<option value="2" <c:if test="${module.status eq '2'}">selected="selected"</c:if>>禁用</option>
                    </select>  
                </td>
                <td>
                	<div id="statusTip"></div>&nbsp;
                </td>
            </tr>
            
            <tr>
                <td width="15%" align="right"><font color="FF0000">*</font>模块类型：</td>
                <td width="500">
                    <select name="_moduleType" id="_moduleType" disabled="disabled" class="selectText">
                        <option value="0">--模块类型--</option>
                      	<%-- <option value="1" <c:if test="${module.moduleType eq '1'}">selected="selected"</c:if>>根节点</option> --%>
                      	<option value="2" <c:if test="${module.moduleType eq '2'}">selected="selected"</c:if>>菜单</option>
                      	<option value="3" <c:if test="${module.moduleType eq '3'}">selected="selected"</c:if>>按钮</option>
                      	<%-- <option value="4" <c:if test="${module.moduleType eq '4'}">selected="selected"</c:if>>权限</option> --%>
                    </select>
                </td>
                 <td>
                	<div id="moduleTypeTip"></div>&nbsp;
                </td>
           </tr>
           
           <tr>
                <td align="right">显示方式：</td>
                <td>
                    <select name="showType" id="showType" class="selectText">
                        <option value="0">--请选择--</option>
                      	<option value="1" <c:if test="${module.showType eq '1'}">selected="selected"</c:if>>跳转</option>
                      	<option value="2" <c:if test="${module.showType eq '2'}">selected="selected"</c:if>>弹框</option>
                    </select> 
                    <font style="font-size: 15px;color: red;">（模块类型为【按钮】时选择）</font>
                </td>
                <td>
                	<div id="showTypeTip"></div>&nbsp;
                </td>
            </tr>
           
           <tr>
                <td align="right"><font color="FF0000">*</font>模块排列顺序：</td>
                <td>
                     <input name="showSeq" id="showSeq" type="text" class="inputText" value="${module.showSeq}" />
                </td>
                 <td>
                	<div id="showSeqTip"></div>&nbsp;
                </td>
            </tr>
            
            <tr>
                <td align="right">模块描述：</td>
                <td>
                    <textarea name="moduleDesc" id="moduleDesc" rows="4" cols="5" class="textareaText">${module.moduleDesc}</textarea>
                </td>
                 <td>
                	<div id="moduleDescTip"></div>&nbsp;
                </td>
            </tr>
        </tbody>
        
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" value="修改模块" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="取消" />
                 </td>
            </tr>
        </tfoot>
    </table>
    </form>
    </div>
</body>
</html>
