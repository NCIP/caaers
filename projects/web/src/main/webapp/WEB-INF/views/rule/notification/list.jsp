
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
    <link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
</head>
<body>
<p>
<tags:instructions code="listreportdefinitions" />
</p>
<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.reportCalendarTemplateList"
    var="rct" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="90%"
    style="width:100%;" styleClass=""
    filterable="false">
    <ec:row>
        <ec:column property="name" title="Name">
         <a href="<c:url value="/pages/rule/notification/edit?repDefId=${rct.id}" />">${rct.name}</a>
        </ec:column>
        <ec:column property="description" title="Description"/>
        <ec:column property="organization" title="Organization">${rct.organization.fullName}</ec:column>
        <ec:column property="duration" title="Final Report Due">${rct.duration} ${rct.timeScaleUnitType.displayName}(s)</ec:column>
  
    </ec:row>
</ec:table>
</body>
</html>