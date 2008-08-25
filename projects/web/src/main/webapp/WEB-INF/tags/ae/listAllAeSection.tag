
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The evaluation period that is being rendered" %>
<c:if test="${fn:length(reportingPeriod.adverseEvents) gt 0}">
<div class="eXtremeTable">
	<table width="100%" border="0" cellspacing="0" class="rpTableRegion">
		<thead>
			<tr align="center" class="label">
				<td class="tableHeader" width="10%"><chrome:collapsableElement targetID="aetable${reportingPeriod.id}" collapsed="true" id="ID_03"/></td>
				<td class="tableHeader" width="10%">All AE's</td>
				<td class="tableHeader" width="20%">AE Term</td>
				<td class="centerTableHeader" width="20%">Grade</td>
				<td class="tableHeader" width="20%">AE Start Date</td>
				<td class="tableHeader" width="20%">Requires report</td>
			</tr>
		</thead>
		<tr id="aetable${reportingPeriod.id}" style="display:none;">
			<td/><td/>
			<td colspan=4>
				<div class="eXtremeTable">
					<table width="100%" border="0" cellspacing="0" class="rpTableRegion" align="center">
						<c:forEach items="${reportingPeriod.adverseEvents}" var="adverseEvent" varStatus="statusAdverseEvent">
							<ae:oneListAeRow index="${statusAdverseEvent.index}" ae="${adverseEvent}" width="25%"/>
						</c:forEach>
					</table>
				</div>		
			</td>	
		</tr>
	</table>
</div>
</c:if>		