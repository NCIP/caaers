<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="aeReportId" required="true" type="java.lang.Integer" description="Id of the data collection" %>
<%@attribute name="rulesMessages" required="true" type="java.util.List" description="The messages from rules engine,  to show" %>

<div id="rulesMessageNone-${aeReportId}" style="display:none">
    <b><caaers:message code="capture.ae.flow.please.select" /></b>
</div>
<div id="rulesMessage-${aeReportId}">
	<b><b><caaers:message code="capture.ae.flow.actions" /></b>
    <ul id="rulesMessageList-${aeReportId}">
        <c:if test="${not empty rulesMessages}">
            <c:forEach var="msg" items="${rulesMessages}">
                <li>
                    ${msg}
                </li>
            </c:forEach>
        </c:if>
    </ul>
</div>
