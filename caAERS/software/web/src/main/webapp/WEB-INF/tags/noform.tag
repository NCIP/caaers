<%-- This tag has the same side-effects as the spring form:form tag, but does not actually write
    a <form> HTML tag --%>
<%@ tag import="org.springframework.web.servlet.tags.form.FormTag, org.springframework.web.servlet.tags.NestedPathTag" %>
<% 
   request.setAttribute(FormTag.MODEL_ATTRIBUTE_VARIABLE_NAME, "command");
   request.setAttribute(NestedPathTag.NESTED_PATH_VARIABLE_NAME, "command.");
%>
<jsp:doBody/>
<% 
   request.removeAttribute(FormTag.MODEL_ATTRIBUTE_VARIABLE_NAME);
   request.removeAttribute(NestedPathTag.NESTED_PATH_VARIABLE_NAME);
%>
