<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="aeReportId" required="true" type="java.lang.Integer" description="Id of the data collection"%>
<%@attribute name="alertShown" required="true" type="java.lang.Boolean" description="If alert is shown by caaers"%>
<%@attribute name="rulesMessages" required="true" type="java.util.List" description="The messages from rules engine,  to show" %>
<div id="rulesMessageTop-${aeReportId}" class="rulesMessage">

<div id="rulesMessage-${aeReportId}-required" style="${empty rulesMessages ? 'display:none;' : ''}" >
   <c:if test="${alertShown}">
   <tags:message key="instruction_ae_require_reporting" />
   </c:if>
   <c:if test="${not alertShown}">
   <tags:message key="instruction_ae_require_reporting_noAlert" />
   </c:if>
	
</div>
<div id="rulesMessage-${aeReportId}-not-required" style="${not empty rulesMessages ? 'display:none;' : ''}">
   <c:if test="${alertShown}">
   <tags:message key="instruction_ae_not_require_reporting" />
   </c:if>
   <c:if test="${not alertShown}">
   <tags:message key="instruction_ae_not_require_reporting_noAlert" />
   </c:if>
</div>
</div>
