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
    <title>SP计费平台-角色模块配置</title>
    <jsp:include page="baseJs.jsp" />
</head>
<body>

	<div class="home_content_bg">
			<a class="home_current">角色分配模块</a>
	</div>
 	<div class="content-mainBG">		
	<form action="<%=path%>/basics/doUpdateRoleModule.do" id="frm" name="frm" method="post">
    <table class="table-add">
        <tbody>
            <tr>
                <td>
                  	<div id="moduleTree">
                  		<input type="hidden" id="roleId" name="roleId" value="${roleId}" />
                  		<jsp:include page="tree.jsp"></jsp:include>
                  	</div>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td>
                    <input class="defaultBut" type="submit" value="确认分配模块" />
                    <input class="defaultBut" type="button" value="取消" onclick="javascript:document.location.href='<%=path%>/basics/listRole.do'" />
                 </td>
            </tr>
        </tfoot>
    </table>
    </form>
    </div>
</body>
</html>