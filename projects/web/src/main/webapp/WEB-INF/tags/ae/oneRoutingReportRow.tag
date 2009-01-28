<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.dto.ReportDTO" description="The report that is printed by this row." %>

<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />

<tr align="center" id="row${rpIndex}" class="${repcurrClass}">

	<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="rID_${report.id}"/></td>
	<td align="left" width="15%">
		${report.name}
	</td>
	<td align="center" width="10%">${report.reportVersionId}</td>
	<td align="center" width="20%" id="report-${reportingPeriod.id}-status">${report.status.displayName }</td>
	<td width="10%">${report.noOfAe}</td>
	<%-- <td width="10%">${report.report.aeReport.reviewStatus.displayName}</td>
	<td>
		<a href="#" onClick="displayPopup('report', ${report.id})">
			<img src="<chrome:imageUrl name="../edit.png" />" />
		</a>
	</td>

	<td/>
	   <td>

		<select onChange="advanceWorkflow(this,${report.report.aeReport.workflowId }, ${report.report.aeReport.id }, 'report')" class="wf${report.report.aeReport.workflowId }">
			<option value="Please select">Please Select</option>
			<c:forEach items="${report.report.aeReport.possibleActions}" var="rStatus">
				<option value="${rStatus }">${rStatus}</option>
			</c:forEach>
		</select>

	</td> --%>
</tr>

<tr id="reptable${report.id}" style="display:none;">
	<td/><td/>
	<td colspan=5>
		<div class="eXtremeTable">

			<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
				<thead>
					<tr align="center" class="label">
						<td class="tableHeader" width="25%">AE Term</td>
						<td class="centerTableHeader" width="25%">Grade</td>
						<td class="tableHeader" width="25%">AE Start Date</td>
						<td class="tableHeader" width="25%">Requires Expedited Reporting?</td>
					</tr>
				</thead>
						
				<c:forEach items="${report.report.aeReport.adverseEvents}" var="ae" varStatus="statusAE">
					<ae:oneListAeRow index="${statusAE.index}" ae="${ae}" width="25%"/>
				</c:forEach>	
			</table>

		</div>
	</td>
</tr>