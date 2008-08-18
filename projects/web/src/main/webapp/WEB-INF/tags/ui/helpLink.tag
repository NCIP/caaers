<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="path" description="The propery path, which is used to derive the help key suffix" required="true"%>
<tags:hoverHelp path="${path}"><%-- <spring:message code="${path}" text="No help available ${path}" />--%></tags:hoverHelp>
