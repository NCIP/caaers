<%@ tag import = "org.acegisecurity.context.SecurityContext" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.security.CaaersUser" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.security.WebSSOUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
Object caaersUser = null;

try{
	SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
	if(context != null){
	   if(context.getAuthentication().getPrincipal() instanceof WebSSOUser){
		   caaersUser = (WebSSOUser)context.getAuthentication().getPrincipal();
	   }else if(context.getAuthentication().getPrincipal() instanceof CaaersUser){
		   caaersUser = (CaaersUser)context.getAuthentication().getPrincipal();
	   }
	}
	
}catch(Exception exx){
	
}
%>
<c:set var="user" value="<%= caaersUser %>" />
<span id="welcome-user">
	<c:if test="${user != null }">
		Welcome <b> ${user.firstName} ${user.lastName}</b>
	</c:if>
</span>