<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>SP计费平台-用户列表</title>
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
	
	PageClick = function(pageclickednumber) {
		jQuery("#pager").pager( {
			pagenumber : pageclickednumber,
			pagecount : jQuery("#pagecount").val(),
			buttonClickCallback : PageClick
		});
		
		jQuery("#pageNumber").val(pageclickednumber);
		jQuery("#frm").submit();
	};
	
	function deleteUser(obj){
		var r = confirm("确定删除该用户？");
		if (r) {
			document.location.href = "<%=path%>/basics/doDeleteUser.do?userId="+obj;
		}
	}
	
	function resetPassword(obj){
		var url = "<%=path%>/basics/resetPassword.do?userId="+obj;
		$.ajax({
			url: url,
			cache:false, //设置为 false 将不会从浏览器缓存中加载请求信息。
			dataType:'html', //接受数据格式 
			success: function(data,textStatus){
				if(data=="OK"){
					alert("密码重置成功！");
				}else{
					alert("重置密码失败！");
				}
			},
			error: function(XMLHttpRequest,textStatus, errorThrown){
				alert(" 请求出错!"+errorThrown);
			}
		});
	}
    </script>
</head>
<body>
    <div class="content-mainBG">
    <form action="<%=path%>/basics/listUser.do" id="frm" name="frm" method="post">
    <table class="table-select">
        <tbody>
            <tr>
                <td align="right">用户名</td>
                <td>
                   <input name="userAccount" id="userAccount" type="text" class="inputText" value="${user.userName}" />
                    <input type="hidden" value="${page.pageCount}" id="pagecount" name="pagecount" />
    				<input type="hidden" value="${page.pageNumber}" id="pageNumber" name="pageNumber" />
                </td>
               
                <td align="right">状态 </td>
                <td>
                    <select name="status" id="status" class="selectText">
                    	<option value="">----用户状态----</option>
                    	<option value="1" <c:if test="${user.userStatus=='1'}">selected="selected"</c:if>>激活</option>
                    	<option value="2" <c:if test="${user.userStatus=='2'}">selected="selected"</c:if>>禁用</option>
                    </select>
                </td>
                <td>
                	<input class="defaultBut" type="button" value="查询" onclick="javascript:document.getElementById('frm').submit();" />
                </td>
            </tr>
            
            <tr>
                 <td align="right">手机号</td>
                <td >
                    <input name="mobile" id="mobile" type="text" value="${user.telephone}" class="inputText" />
                </td>
                
                 <td align="right">姓名</td>
                <td>
                 <input name="Name" id="Name" type="text" value="${user.chnName}" class="inputText" /></td>
                <td>
                	<input class="defaultBut" onclick="javascript:location.href='<%=path%>/basics/toAddUser.do'" id="find" type="button" value="+添加用户" />
                </td>
            </tr>
           
        </tbody>
    </table>
	</form>

    <table class="table-content" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="20%">用户名</th>
                <th width="20%">姓名</th>
                <th width="20%">手机号</th>
                <th width="20%">状态</th>
                <th width="20%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${users}" var="s">
        	<tr>
                <td>${s.userName}</td>
             
                <td>${s.chnName}&nbsp;</td>
              <%--   <td>
                	${s.level}
                </td> --%>
                <td>${s.telephone}</td>
                <td>
                	<c:if test="${s.userStatus eq '1'}">激活</c:if>
                	<c:if test="${s.userStatus eq '2'}">禁用</c:if>
                </td>
                <td>
                	<input onclick="javascript:document.location.href='<%=request.getContextPath()%>/basics/toUpdateUser.do?userId=${s.userId}'" class="def_but" type="button" value="修改" />
                	<input onclick="resetPassword('${s.userId}')" class="def_but" type="button" value="密码重置" />
                	
                </td>
            </tr>
        	</c:forEach>
        </tbody>
        <tfoot>
        	<tr>
                <td colspan="6"><div id="pager" ></div></td>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>