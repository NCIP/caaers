<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="s" required="true" %>
<%@attribute name="l" required="true" %>

<c:if test="${fn:length(s) > l}">
    <c:out value="${fn:substring(s, 0, l)}" />&hellip;
</c:if>
<c:if test="${fn:length(s) <= l}">
    <c:out value="${s}" />
</c:if>
