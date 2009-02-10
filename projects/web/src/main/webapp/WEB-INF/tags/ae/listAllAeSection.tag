
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The course that is being rendered" %>
<c:if test="${fn:length(reportingPeriod.evaluatedAdverseEvents) gt 0}">
<div class="eXtremeTable">
	<table width="100%" border="0" cellspacing="0" class="allAEs rpTableRegion">
		<thead>
			<tr align="center" class="label">
				<td width="5%"><chrome:collapsableElement targetID="aetable${reportingPeriod.id}" collapsed="true" id="ID_03"/></td>
				
				<td class="tableHeader" width="30%">AE Term</td>
				<td class="centerTableHeader" width="20%">Grade</td>
				<td class="tableHeader" width="20%">AE Start Date</td>
				<td class="tableHeader" width="20%">Requires Expedited Reporting?</td>
			</tr>
		</thead>
		<tr id="aetable${reportingPeriod.id}" style="display:none;">
			<td/>
			<td colspan=4>
				<div class="eXtremeTable">
					<table width="100%" border="0" cellspacing="0" class="rpTableRegion" align="center">
						<c:forEach items="${reportingPeriod.evaluatedAdverseEvents}" var="adverseEvent" varStatus="statusAdverseEvent">
							<ae:oneListAeRow index="${statusAdverseEvent.index}" ae="${adverseEvent}" width="30%"/>
						</c:forEach>
					</table>
				</div>		
			</td>	
		</tr>
	</table>
</div>
</c:if>		