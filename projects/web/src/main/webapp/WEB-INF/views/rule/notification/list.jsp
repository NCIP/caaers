
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

    </script>
</head>
<body>

<c:if test="${not empty configuration.map.pscBaseUrl}">
<p>
    View this person's schedule in the <a href="${configuration.map.pscBaseUrl}/pages/schedule?assignment=${command.assignment.gridId}" class="sso">study calendar</a>.
</p>
</c:if>

<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.notifications"
    var="notification" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass="">
    <ec:row>
        <ec:column property="notification.name" title="Name"/>
        <ec:column property="notification.subject" title="Subject"/>
    </ec:row>
</ec:table>
</body>
</html>