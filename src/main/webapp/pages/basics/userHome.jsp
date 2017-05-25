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
    <title>SP计费平台-个人资料</title>
    <jsp:include page="baseJs.jsp" />
</head>
<body>
    <div class="content-mainBG">
	<form action="<%=path%>/basics/doUpdateUser.do" id="frm" name="frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="2">
                	<div class="table-user-nav">
                		<div class="currentUserNav" onclick="javascript:document.location.href='<%=path%>/basics/userCenter.do'">
                			帐号信息
                		</div>
	                	<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/toUpdateUserInfo.do?userId=${user.userId}'" >
	                		联系信息
	                	</div>
	                	<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/toUpdatePassword.do?userId=${user.userId}'">
	                		修改密码
	                	</div>
                	</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="10%">用户名：</td>
                <td width="90%">
                	${user.userName}
                </td>
            </tr><tr>    
                <td>姓名：</td>
                <td>
                    ${user.chnName}
                </td>
            </tr>
        </tbody>
    </table>
   </form>
   </div>
</body>
</html>