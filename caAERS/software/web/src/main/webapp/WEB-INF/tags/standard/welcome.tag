<%@ tag import = "org.acegisecurity.context.SecurityContext" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.security.CaaersUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
CaaersUser caaersUser = null;
SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
if(context != null)
	caaersUser = (gov.nih.nci.cabig.caaers.security.CaaersUser)context.getAuthentication().getPrincipal();
%>
<c:set var="user" value="<%= caaersUser %>" />
<span id="welcome-user">
	<c:if test="${user != null }">
		Welcome <b>
		<c:if test="${user.fullName != ''}">${user.fullName } </c:if>
		<c:if test="${user.fullName == ''}">${user.userName } </c:if>
		</b>
	</c:if>
</span>