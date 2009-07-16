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
<c:if test="${not empty rulesMessages}">
<div>
	<tags:message key="instruction_ae_require_reporting" />
</div>
<ul id="rulesMessageList-${aeReportId}">
 <c:forEach var="msg" items="${rulesMessages}">
 <li>${msg}</li>
 </c:forEach>
</ul>
</c:if>
<c:if test="${ empty rulesMessages}">
<div>
	<tags:message key="instruction_ae_not_require_reporting" />
</div>
<ul id="rulesMessageList-${aeReportId}">

</ul>
</c:if>

</div>