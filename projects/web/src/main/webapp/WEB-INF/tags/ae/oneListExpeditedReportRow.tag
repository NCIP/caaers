<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" type="java.lang.Integer" description="The index of the expedited adverse event"%>
<%@attribute name="aeReport" type="gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport" description="The expedited adverse event report that is printed by this row." %>
<c:forEach items="${aeReport.reports}" var="report" varStatus="rStatus">
	<c:if test="${command.workflowEnabled}">
		<ae:oneListReportRowWorkflowEnabled report="${report}" rpIndex="${rStatus.index}" />
	</c:if>
	<c:if test="${!command.workflowEnabled}">
		<ae:oneListReportRowWorkflowDisabled report="${report}" rpIndex="${rStatus.index}"/>
	</c:if>
</c:forEach>