<%
	String loginPath = request.getContextPath() + "/basics/toLogin.do";
	response.sendRedirect(loginPath);
%>