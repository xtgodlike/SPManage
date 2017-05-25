<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menu.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>

<div class="menu_info" style="vertical-align: top;">
   		<a title="个人信息" href="<%=request.getContextPath()%>/basics/userCenter.do" style="vertical-align: top;">
   			<img src="<%=request.getContextPath()%>/images/user-female.png">
   			${U_SESSION.chnName}
   		</a>
   		&nbsp;|&nbsp;
    	<a title="安全退出" href="<%=request.getContextPath()%>/basics/logOut.do" style="vertical-align: top;padding-right: 20px;">
    		<img src="<%=request.getContextPath()%>/images/logout.png">
    		安全退出
    	</a>
</div> 

<div class="mainBGDiv">
<c:if test="${U_SESSION.layoutId==0 }">
<div class="mainBG-logo"></div>
</c:if>
<c:if test="${U_SESSION.layoutId==1 }">
<div class="mainBG-SG-logo"></div>
</c:if>
   
   <div class="menuBG">
    <input type="hidden" id="_root" name="_root" value="<%=request.getContextPath()%>" />
    
    <c:forEach items="${QY_MENU.rootMenus}" var="p">
          <div class="navBG">
          	<div class="nav-title" <c:if test="${param._current==p.selfMenu.moduleCode}">id="navBG-current"</c:if> >${p.selfMenu.moduleName}</div>
          	<div class="nav-content">
          		<c:forEach items="${p.childMenu}" var="c" varStatus="status">
          			<div class="navBG-li" <c:if test="${status.last}">style="border-bottom: none"</c:if> ><a <c:if test="${c.selfMenu.moduleLink!=''}"> href="<%=request.getContextPath()%>${c.selfMenu.moduleLink}"  </c:if> >${c.selfMenu.moduleName}</a></div>
          		</c:forEach>
          	 </div>
          </div>
     </c:forEach>
   </div>
</div>