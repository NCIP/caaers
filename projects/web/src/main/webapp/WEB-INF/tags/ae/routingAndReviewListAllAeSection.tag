
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The evaluation period that is being rendered" %>
<%@attribute name="isDCPStudy" type="java.lang.Boolean" required="true" description="True, if it is a DCP study" %>

<c:if test="${fn:length(reportingPeriod.evaluatedAdverseEvents) gt 0}">
<div class="eXtremeTable">
	<table width="100%" border="0" cellspacing="0" class="allAEs rpTableRegion">
		<thead>
			<tr align="center" class="label">
				<td width="5%"><chrome:collapsableElement targetID="aetable${reportingPeriod.id}" collapsed="true" id="ID_03"/></td>
				
				<td class="centerTableHeader" width="35%">AE Term</td>
				<td class="centerTableHeader" width="10%">Grade</td>
				<td class="centerTableHeader" width="20%">Attribution</td>
				<c:if test="${isDCPStudy}"><td class="centerTableHeader" width="10%">Seriousness</td></c:if>
				<td class="centerTableHeader" width="20%">Hospitalization</td>
			</tr>
		</thead>
		<tr id="aetable${reportingPeriod.id}" style="display:none;">
			<td/>
			<td colspan="5">
				<div class="eXtremeTable">
					<table width="100%" border="0" cellspacing="0" class="rpTableRegion" align="center" >
						<c:forEach items="${reportingPeriod.evaluatedAdverseEvents}" var="adverseEvent" varStatus="statusAdverseEvent">
							<ae:oneRoutingAndReviewListAeRow index="${statusAdverseEvent.index}" ae="${adverseEvent}" width="35%" isDCPStudy="${isDCPStudy}"/>
						</c:forEach>
					</table>
				</div>		
			</td>	
		</tr>
	</table>
</div>
</c:if>		