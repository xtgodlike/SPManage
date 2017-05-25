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
    <title>SP计费平台-修改密码</title>
    <jsp:include page="baseJs.jsp" />
    <jsp:include page="validator.jsp" />
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
    	
    	 $("#passwd").formValidator({
    		onShow:"",
    		onFocus:"至少3个长度",
    		onCorrect:"密码合法"
    	}).inputValidator({
    		min:3,
    		max:30,
    		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},
    		onError:"原密码长度不正确，请确认"
	    });
    	
    	//新密码验证
	    $("#newPasswd").formValidator({
    		onShow:"",
    		onCorrect:"密码合法"
    	}).inputValidator({
    		min:3,
    		max:30,
    		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},
    		onError:"新密码长度不正确，请确认"
	    });
	    
		$("#newPasswd2").formValidator({
			onShow:"",
			onCorrect:"密码一致"
		}).inputValidator({
			min:3,
			max:30,
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},
			onError:"确认新密码长度不正确，请确认"
		}).compareValidator({
			desID:"newPasswd",
			operateor:"=",
			onError:"2次新密码不一致,请确认"
		});
		
    });	
    </script>
    
</head>
<body>
    <div class="content-mainBG">
    <form action="<%=path%>/basics/doUpdatePassword.do" id="frm" name="frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="3">
                	<div class="table-user-nav">
                		<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/userCenter.do'">
                			帐号信息
                		</div>
	                	<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/toUpdateUserInfo.do?userId=${user.userId}'" >
	                		联系信息
	                	</div>
	                	<div class="currentUserNav" onclick="javascript:document.location.href='<%=path%>/basics/toUpdatePassword.do?userId=${user.userId}'">
	                		修改密码
	                	</div>
                	</div>
                </th>
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td colspan="3"><div class="eMessage">${eMessage}</div></td>
        	</tr>
            <tr>
                <td width="40%"><font color="FF0000">*</font>用户名：</td>
                <td width="30%">
                	<input type="hidden" id="userId" name="userId" value="${user.userId}" />
                   ${user.userName}
                </td>
                <td width="30%"></td>
                </tr><tr>
                <td><font color="FF0000">*</font>原密码：</td>
                <td>
                    <input name="passwd" id="passwd" type="password" class="inputText" /></td>
                 <td><div id="passwdTip"></div></td>
            </tr>
            <tr>
                <td><font color="FF0000">*</font>新密码：</td>
                <td>
                    <input name="newPasswd" id="newPasswd" type="password" class="inputText" />
                 </td>
                  <td><div id="newPasswdTip"></div></td>
            </tr>
            <tr>
                <td><font color="FF0000">*</font>确认新密码：</td>
                <td>
                    <input name="newPasswd2" id="newPasswd2" type="password" class="inputText"/></td>
                 <td><div id="newPasswd2Tip"></div></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" value="修改" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
   </div>
</body>
</html>
