<%-- This tag has the same side-effects as the spring form:form tag, but does not actually write
    a <form> HTML tag --%>
<%@ tag import="org.springframework.web.servlet.tags.form.FormTag" %>
<% request.setAttribute(FormTag.COMMAND_NAME_VARIABLE_NAME, "command"); %>
<jsp:doBody/>
<% request.removeAttribute(FormTag.COMMAND_NAME_VARIABLE_NAME); %>
