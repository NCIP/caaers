
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>List Report Calendars</title>
    <tags:stylesheetLink name="extremecomponents"/>
    <style type="text/css">
        .notify-unit.success {
            color: #090;
        }

        .notify-unit.failure {
            color: #900;
        }
    </style>
</head>
<body>
<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.reportCalendarTemplateList"
    var="rct" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass="">
    <ec:row>
        <ec:column property="name" title="Name">${name}</ec:column>
        <ec:column property="description" title="Description"/>
        <ec:column property="duration" title="Final Report Due">${rct.duration} ${rct.timeScaleUnitType.displayName}</ec:column>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>