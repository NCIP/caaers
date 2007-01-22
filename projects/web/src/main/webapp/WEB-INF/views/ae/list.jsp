<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>AEs for ${command.participant.fullName} on ${command.study.shortTitle}</title>
    <tags:stylesheetLink name="extremecomponents"/>
</head>
<body>

<ec:table
    items="command.assignment.aeReports"
    var="report"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass="">
    <ec:row>
        <ec:column property="primaryAdverseEvent.ctcTerm" title="CTC term">
            <a href="<c:url value="/pages/ae/edit?report=${report.id}"/>">
            <c:choose>
                <c:when test="${not empty report.primaryAdverseEvent}">
                    ${report.primaryAdverseEvent.ctcTerm.fullName}
                </c:when>
                <c:otherwise>
                    [Incomplete AE]
                </c:otherwise>
            </c:choose>
            </a>
        </ec:column>
        <ec:column property="primaryAdverseEvent.detectionDate" title="Detection date"/>
        <ec:column property="primaryAdverseEvent.grade.code" title="Grade"/>
        <ec:column property="primaryAdverseEvent.attribution.code" title="Attribution"/>
    </ec:row>
</ec:table>
</body>
</html>