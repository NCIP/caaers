<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="aeReportId" required="true" type="java.lang.Integer" description="Id of the data collection"%>
<%@attribute name="rulesMessages" required="true" type="java.util.List" description="The messages from rules engine,  to show" %>
<div class="row">
	<div class="rightpanel" style="text-align:right;">
	 <tags:button type="button" onclick="forwardToReport(${aeReportId}, this.form);" value="Report" color="green" icon="continue" />
	</div>
</div>
<div id="rulesMessage-${aeReportId}" class="rulesMessage">

<div id="rulesMessage-${aeReportId}-required" style="${empty rulesMessages ? 'display:none;' : ''}" >
	<tags:message key="instruction_ae_require_reporting" />
</div>
<div id="rulesMessage-${aeReportId}-not-required" style="${not empty rulesMessages ? 'display:none;' : ''}">
	<tags:message key="instruction_ae_not_require_reporting" />
</div>

<ul id="rulesMessageList-${aeReportId}">
<c:if test="${not empty rulesMessages}">
 <c:forEach var="msg" items="${rulesMessages}">
 <li>${msg}</li>
 </c:forEach>
</c:if>
</ul>

</div>