<%@page language="java" isErrorPage="true" %>
<%@page isErrorPage="true" %>
<%@page language="java" %>
<%@include file="/WEB-INF/views/taglibs.jsp" %>
<page:applyDecorator name="standard">
<h1><caaers:message code="access.denied.header" text="Sorry! you cannot access the page... " /></h1>
<p>
  <caaers:message code="access.denied.content" text="The User you are trying to login with is not a CSM user" />

</page:applyDecorator>