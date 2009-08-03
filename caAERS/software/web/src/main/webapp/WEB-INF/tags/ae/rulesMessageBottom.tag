<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="aeReportId" required="true" type="java.lang.Integer" description="Id of the data collection" %>
<%@attribute name="rulesMessages" required="true" type="java.util.List" description="The messages from rules engine,  to show" %>
<div id="rulesMessage-${aeReportId}">
	<b>When you press the Report button, you will initiate the following actions:</b>
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
