
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>AEs for ${command.participant.fullName} on ${command.study.shortTitle}</title>
    <tags:stylesheetLink name="extremecomponents"/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <style type="text/css">
        .notify-unit.success {
            color: #090;
        }

        .notify-unit.failure {
            color: #900;
        }
    </style>
    <script type="text/javascript">
        function notifyPsc(aeReportId) {
            AE.showIndicator("notify-indicator-" + aeReportId)
            createAE.pushAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-" + aeReportId)
                var unit = $("notify-unit-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }

        Event.observe(window, "load", function() {
            $$("a.notify").each(function(a) {
                Event.observe(a, "click", function(e) {
                    Event.stop(e);
                    var aeReportId = Event.element(e).id.substring(7)
                    notifyPsc(aeReportId)
                })
            })
        })
    </script>
</head>
<body>

<c:if test="${not empty configuration.map.pscBaseUrl}">
<p>
    View this person's schedule in <a href="${configuration.map.pscBaseUrl}/pages/schedule?assignment=${command.assignment.gridId}" class="sso">the study calendar</a>
</p>
</c:if>

<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.assignment.aeReports"
    var="report" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass="">
    <ec:row>
        <ec:column property="primaryAdverseEvent.ctcTerm" title="CTC term">
            <a href="<c:url value="/pages/ae/edit?aeReport=${report.id}"/>">
            <c:choose>
                <c:when test="${not empty report.primaryAdverseEvent.ctcTerm}">
                    ${report.primaryAdverseEvent.ctcTerm.fullName}
                </c:when>
                <c:when test="${not empty report.labs}">
                    [Lab-based incomplete AE]
                </c:when>
                <c:otherwise>
                    [Incomplete AE]
                </c:otherwise>
            </c:choose>
            </a>
        </ec:column>
        <ec:column property="primaryAdverseEvent.detectionDate" title="Detection date">
            <tags:formatDate value="${report.primaryAdverseEvent.detectionDate}"/>
        </ec:column>
        <ec:column property="primaryAdverseEvent.grade.code" title="Grade"/>
        <ec:column property="primaryAdverseEvent.attribution.code" title="Attribution"/>
        <ec:column title="Notify Study Calendar" sortable="false" filterable="false" property="dc">
            <c:if test="${report.notificationMessagePossible}">
                <span class="notify-unit" id="notify-unit-${report.id}">
                    <a id="notify-${report.id}" class="notify" href="#">Notify</a>
                    <tags:indicator id="notify-indicator-${report.id}"/>
                </span>
            </c:if>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>