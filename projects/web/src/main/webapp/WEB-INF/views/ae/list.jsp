
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
    View this person's schedule in the <a href="${configuration.map.pscBaseUrl}/pages/schedule?assignment=${command.assignment.gridId}" class="sso">study calendar</a>.
</p>
</c:if>






<h2>Expedited Reports
<a href="<c:url value="/pages/ae/create?participant=${command.participant.id}&study=${command.study.id}&action=create"/>">( create )</a>
</h2>

<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.assignment.aeReports"
    var="report" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass=""
    filterable="false">
    <ec:row>
        <ec:column property="adverseEvents[0].adverseEventTerm.universalTerm" title="Term">
            <a href="<c:url value="/pages/ae/edit?aeReport=${report.id}"/>">
            <c:choose>
                <c:when test="${not empty report.adverseEvents[0].adverseEventTerm}">
                    <c:forEach items="${report.adverseEvents}" var="adverseEvent">
                		${adverseEvent.adverseEventTerm.universalTerm}<br />
                
    				</c:forEach>
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
        <ec:column property="detectionDate" title="Detection date">
            <tags:formatDate value="${report.detectionDate}"/>
        </ec:column>
        <ec:column property="adverseEvents[0].grade.code" title="Grade"/>
        <ec:column property="adverseEvents[0].attribution.code" title="Attribution"/>
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
<br>
<h2>Routine AEs
<a href="<c:url value="/pages/ae/createRoutine?participant=${command.participant.id}&study=${command.study.id}&action=create"/>">( create )</a>
</h2>
<ec:table
    items="command.assignment.aeRoutineReports"
    var="routineReport" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass=""
    filterable="false">
    <ec:row>
        <ec:column property="adverseEvents[0].adverseEventTerm.universalTerm" title="Term">
            <a href="<c:url value="/pages/ae/editRoutine?aeRoutineReport=${routineReport.id}"/>">
            <c:choose>
                <c:when test="${not empty routineReport.adverseEvents[0].adverseEventTerm}">
                	<c:forEach items="${routineReport.adverseEvents}" var="adverseEvent">
                		${adverseEvent.adverseEventTerm.universalTerm}<br />
                
    				</c:forEach>
                    
                </c:when>
                <c:otherwise>
                    [Incomplete AE]
                </c:otherwise>
            </c:choose>
            </a>
        </ec:column>
        <ec:column property="startDate" title="Start date">
            <tags:formatDate value="${routineReport.startDate}"/>
        </ec:column>
        <ec:column property="adverseEvents[0].grade.code" title="Grade"/>
    </ec:row>
</ec:table>
</body>
</html>
