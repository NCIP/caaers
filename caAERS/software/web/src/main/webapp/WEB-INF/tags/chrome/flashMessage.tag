<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="key" %>
<%@attribute name="messageType" %>

<c:if test="${empty messageType || (messageType ne 'info' && messageType ne 'success' && messageType ne 'error' && messageType ne 'warning')}">
    <c:set var="messageType" value="success" />
</c:if>

<c:if test="${not empty flashMessage}">
    <div class="${messageType}-box message"><p>${flashMessage}</p></div>
</c:if>
<c:if test="${not empty key}">
    <div class="${messageType}-box message"><p>${requestScope[key]}</p></div>
</c:if>