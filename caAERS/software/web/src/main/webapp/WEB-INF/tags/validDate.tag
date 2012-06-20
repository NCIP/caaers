<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="date" required="true" %>

<c:if test="${date ne '00/00/0000'}">
    <c:set var="monthValue" value="${fn:substring(date, 0, 2)}" />
    <c:set var="dateValue" value="${fn:substring(date, 3, 5)}" />
    <c:set var="yearValue" value="${fn:substring(date, 6, 10)}" />
    <c:if test="${dateValue ne '00'}">
        <c:out value="${date}" />
    </c:if>
    <c:if test="${dateValue eq '00'}">
        <c:out value="${monthValue}" />/<c:out value="${yearValue}" />
    </c:if>
</c:if>