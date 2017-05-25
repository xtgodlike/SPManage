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
    <title>SP计费平台-角色列表</title>
    <jsp:include page="baseJs.jsp" />
    <jsp:include page="page.jsp" />
    
    <script type="text/javascript">
    jQuery(document).ready(function() {
		jQuery("#pager").pager( {
			pagenumber : jQuery("#pageNumber").val(),
			pagecount : jQuery("#pagecount").val(),
			buttonClickCallback : PageClick
		});
	});
	
	function deleteRole(obj){
		var r = confirm("确定删除该角色？");
		if (r) {
			document.location.href = "<%=path%>/basics/doDeleteRole.do?roleId="+obj;
		}
	}
	
	PageClick = function(pageclickednumber) {
		jQuery("#pager").pager( {
			pagenumber : pageclickednumber,
			pagecount : jQuery("#pagecount").val(),
			buttonClickCallback : PageClick
		});
		
		jQuery("#pageNumber").val(pageclickednumber);
		jQuery("#frm").submit();
	};
    </script>
    
</head>
<body>
    <div class="content-mainBG">
    <form id="frm" name="frm" action="<%=path%>/basics/listRole.do" method="post">
    <table class="table-select">
        <tbody>
            <tr>
                <td align="right">角色名称</td>
                <td>
                   <input name="roleName" id="roleName" class="inputText" type="text" value="${rolex.roleName}" />
                </td>
                <td align="right">角色状态</td>
                <td>
                	<select name="roleStatus" id="roleStatus" class="selectText">
                        <option value="">----角色状态----</option>
                        <option value="1" <c:if test="${rolex.status eq '1'}">selected="selected"</c:if>>激活</option>
                        <option value="2" <c:if test="${rolex.status eq '2'}">selected="selected"</c:if>>禁用</option>
                    </select>      
				</td>
             
                <td colspan="2">
                    <input class="defaultBut" id="find" type="button" onclick="javascript:document.getElementById('frm').submit();" value="查询" />
                    <input class="defaultBut" onclick="javascript:location.href='<%=path%>/basics/toAddRole.do'" id="find" type="button" value="+添加角色" />
                    <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
    				<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
                </td>
            </tr>
        </tbody>
    </table>


    <table class="table-content" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="25%">角色名称</th>
                <th width="35%">角色描述</th>
                <th width="25%">状态</th>
                <th width="15%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${roles}" var="s">
        	<tr>
                <td>${s.roleName}</td>
                <td style="text-align: left;">${s.roleDesc}</td>
                <td>
                	<c:if test="${s.status eq '1'}">激活</c:if>
                	<c:if test="${s.status eq '2'}">禁用</c:if>
                </td>
                <%-- <td>
                	<fmt:formatDate value="${s.lastUpdateTime}" pattern="yyyy-MM-dd hh-mm-ss" />
                </td> --%>
                <td>
                	<c:if test="${s.writable eq '0'}">
                	<input onclick="javascript:document.location.href='<%=path%>/basics/toUpdateRole.do?roleId=${s.roleId}'" class="def_but" type="button" value="修改" />
                	<input onclick="deleteRole('${s.roleId}')" class="def_but" type="button" value="删除" />
                	<input onclick="javascript:document.location.href='<%=path%>/basics/toModifyRoleModulePage.do?roleId=${s.roleId}'" class="def_but" type="button" value="分配模块" />
                	</c:if>
                </td>
            </tr>
        	</c:forEach>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="5">
                	<div id="pager" ></div>
                </td>
            </tr>
        </tfoot>
    </table>
    </form>
    </div>
</body>
</html>