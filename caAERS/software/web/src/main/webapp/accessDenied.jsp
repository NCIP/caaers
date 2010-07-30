<%@ page import="org.acegisecurity.context.SecurityContextHolder" %>
<%@ page import="org.acegisecurity.Authentication" %>
<%@ page import="org.acegisecurity.ui.AccessDeniedHandlerImpl" %>
<%@page language="java" isErrorPage="true" %>
<%@page isErrorPage="true" %>
<%@page language="java" %>
<%@include file="/WEB-INF/views/taglibs.jsp" %>
<page:applyDecorator name="standard">
<h1>Sorry, access is denied</h1>


<p>
<%= request.getAttribute(AccessDeniedHandlerImpl.ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY)%>

<p>

<%		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) { %>
			Authentication object as a String: <%= auth.toString() %><BR><BR>
<%      } %>
    
</page:applyDecorator>