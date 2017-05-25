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
	<title>SP计费平台-添加角色</title>
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
    	
    	//角色名称
    	$("#roleName").formValidator({
    		onShow:"角色名称，1~6个字符",
    		onFocus:"请输入角色名称，1~6个字符",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:12,
    		onError:"长度不正确,请确认"
    	});
    	
    	//角色状态
    	$("#roleStatus").formValidator({
    		onShow:"请选择角色状态",
    		onFocus:"请选择角色状态",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择角色状态"
    	});
    	
    	//角色描述（非必录项）
    	$("#roleDesc").formValidator({
    		empty:true,
    		onShow:"角色描述，可以为空",
    		onFocus:"你要是输入了，只能输入1~50个字符",
    		onCorrect:"正确",
    		onEmpty:"您确定不输入角色描述？"
    	}).inputValidator({
    		min:1,
    		max:50,
    		onError:"角色描述支持1~50个字符"
    	});
    });
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">添加角色</a>
	</div>
    <div class="content-mainBG">
    <form action="<%=path%>/basics/doAddRole.do" id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0" >
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>角色名称：</td>
                <td width="30%">
                    <input name="roleName" class="inputText" id="roleName" maxlength="10" type="text" />
                </td>
                <td width="30%">
                	<div id="roleNameTip"></div>
                </td>
                </tr>
                
                <tr>
                <td align="right"><font color="FF0000">*</font>角色状态：</td>
                <td>
                    <select name="roleStatus" id="roleStatus" class="selectText">
                        <option value="">----角色状态----</option>
                        <option value="1">激活</option>
                        <option value="2">禁用</option>
                    </select>
                </td>
                <td>
                	<div id="roleStatusTip"></div>
                </td>
            </tr>
            <tr>
                <td align="right">角色描述：</td>
                <td>
                    <textarea name="roleDesc" id="roleDesc" class="textareaText" rows="4" cols="5"></textarea>
                </td>
                <td>
                	<div id="roleDescTip"></div>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut"  type="submit" value="保存" />
                    <input class="defaultBut" type="button" value="取消" onclick="javascript:history.go(-1);" />
                 </td>
            </tr>
        </tfoot>
    </table>
    </form>
    </div>
</body>
</html>
