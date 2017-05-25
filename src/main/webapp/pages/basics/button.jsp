<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${QY_MENU.rootMenus}" var="s">
	<c:forEach items="${s.childMenu}" var="c" varStatus="status">
		<c:if test="${c.selfMenu.moduleCode==param._cModuleCode}">
			<c:forEach items="${c.buttons}" var="b">
				<input onclick="javascript:document.location.href='<%=request.getContextPath()%>${b.moduleLink}${param._vId}'" class="def_but" type="button" value="${b.moduleName}" />
			</c:forEach>
		</c:if>
	</c:forEach>
</c:forEach>