<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="path" description="The propery path, which is used to derive the help key suffix" required="true"%>
<spring:message code="${path}" var="helptext" text="default" />
<c:if test="${helptext ne 'default'}">
<tags:hoverHelp path="${path}">${helptext}</tags:hoverHelp>
</c:if>
