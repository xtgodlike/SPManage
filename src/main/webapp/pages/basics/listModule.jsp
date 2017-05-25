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
    <title>SP计费平台-模块列表</title>
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
	
	function deleteMD(obj){
		var r = confirm("确定删除该模块，及其子模块？");
		if (r) {
			document.location.href = "<%=path%>/basics/doDeleteModule.do?moduleId="+obj;
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
	    <form id="frm" name="frm" action="<%=path%>/basics/listModule.do" method="post">
	    <table class="table-select">
	        <tbody>
	            <tr>
	                <td class="select-title" align="right">模块名称</td>
	                <td class="detail">
	                   <input name="moduleName" id="moduleName" class="inputText" type="text" maxlength="10" value="${module.moduleName}" />
	                </td>
	                <td class="select-title" align="right">模块类型</td>
	                <td class="td_detail">
	                      <select name="moduleType" id="moduleType" class="selectText">
	                      	<option value="0">--模块类型--</option>
	                      	<%-- <option value="1" <c:if test="${module.moduleType eq '1'}">selected="selected"</c:if>>根节点</option> --%>
	                      	<option value="2" <c:if test="${module.moduleType eq '2'}">selected="selected"</c:if>>菜单</option>
	                      	<option value="3" <c:if test="${module.moduleType eq '3'}">selected="selected"</c:if>>按钮</option>
	                      	<%-- <option value="4" <c:if test="${module.moduleType eq '4'}">selected="selected"</c:if>>权限</option> --%>
	                    </select>
	                </td>
	                <td colspan="2">
	                    <input class="defaultBut" id="find" type="button" onclick="javascript:document.getElementById('frm').submit();" value="查询" />
	                    <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
	    				<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
	                </td>
	            </tr>
	        </tbody>
	    </table>
    
    <table class="table-content" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="20%">模块名称</th>
                <th width="20%">链接</th>
                <th width="20%">描述</th>
                <th width="5%">级别</th>
                <th width="5%">状态</th>
                <th width="5%">类型</th>
                <th width="25%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${modules}" var="s">
        		<c:if test="${s.moduleLevel>0}">
	        	<tr>
	                <td>${s.moduleName}</td>
	                <td>${s.moduleLink}&nbsp;</td>
	                <td>${s.moduleDesc}&nbsp;</td>
	                <td>${s.moduleLevel}</td>
	                <td>
	                	<c:if test="${s.status==1}">激活</c:if>
	                	<c:if test="${s.status==0}">禁用</c:if>
	                </td>
	                <td>
	                	<%-- <c:if test="${s.moduleType==1}">根节点</c:if> --%>
	                	<c:if test="${s.moduleType==2}">菜单</c:if>
	                	<c:if test="${s.moduleType==3}">按钮</c:if>
	                	<%-- <c:if test="${s.moduleType==4}">权限</c:if> --%>
	                </td>
	                <td>
	                <input onclick="javascript:document.location.href='<%=path%>/basics/toUpdateModule.do?moduleId=${s.moduleId}&parentModuleId=${s.parentMId}'" class="def_but" type="button" value="修改" />
	                <c:if test="${s.moduleLevel>1 || s.moduleType eq '4'}">
	                	<input onclick="deleteMD('${s.moduleId}')" class="def_but" type="button" value="删除" />
	                </c:if>
	                
	                <input onclick="javascript:document.location.href='<%=path%>/basics/toAddModule.do?parentModuleId=${s.moduleId}'" class="def_but" type="button" value="添加子模块" />
	                <%-- <c:if test="${(s.moduleLevel eq '1' || s.moduleLevel eq '0') && (s.moduleType eq '2' || s.moduleType eq '1')}"> <!-- (s.moduleLevel eq '1' || s.moduleLevel eq '0') &&  -->
	               		
	                </c:if> --%>
	                </td>
	            </tr>
	            </c:if>
        	</c:forEach>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="7">
                	<div id="pager" ></div>
				</td>
            </tr>
        </tfoot>
    </table>
    </form>
   </div>	
</body>
</html>