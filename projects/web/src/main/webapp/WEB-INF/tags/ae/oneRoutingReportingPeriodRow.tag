<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO" required="true" description="The evaluation period that is being rendered" %>

<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />
<c:set var="reportingPeriodPageURL" 
	value="/pages/ae/captureRoutine?participant=${reportingPeriod.participant.id}&study=${reportingPeriod.study.id}&_page=0&adverseEventReportingPeriod=${reportingPeriod.id}&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true" />

<tr align="center" id="${index}" class="${currClass}">
	<td>
		<chrome:collapsableElement targetID="rptable${reportingPeriod.id}" collapsed="true" id="rpID_${reportingPeriod.id}"/>
	</td>
	<td width="15%" align="left">
		<a href="<c:url value="${reportingPeriodPageURL}"/>">${reportingPeriod.evaluationPeriodName}</a>
	</td>
	<td width="10%">${reportingPeriod.evaluationPeriodTypeName}</td>
	<td width="10%">${reportingPeriod.noOfAe}</td>
	<td width="10%">${reportingPeriod.noOfReport}</td>
	<td>
		<a href="#" onClick="displayPopup('reportingPeriod', ${reportingPeriod.id})">
			<img src="<chrome:imageUrl name="../edit.png" />" /></td>
		</a>
	<td width="20%">
		<select onChange="advanceWorkflow(this,${reportingPeriod.workflowId }, ${reportingPeriod.id }, 'reportingPeriod')" class="wf${reportingPeriod.workflowId }">
			<option value="${ reportingPeriod.reviewStatus.name}">${reportingPeriod.reviewStatus.displayName}</option>
			<c:forEach items="${reportingPeriod.possibleReivewStatuses}" var="aStatus">
				<option value="${aStatus.name }">${aStatus.displayName}</option>
			</c:forEach>
		</select>
	</td>
</tr>

<tr id="rptable${reportingPeriod.id}" style="display:none;" class="${currClass}">
	<td></td>
	<td></td>
	<td colspan=5>
		<table width="100%" border="0" cellspacing="0"> <!-- This is the outer table -->
			<tr>
				<td width="100%">
					 
					<div class="eXtremeTable">

						<table width="100%" border="0" cellspacing="0" class="rpTableRegion">
						  <c:choose>
							<c:when test="${fn:length(reportingPeriod.reports) gt 0}">
								<thead>
									<tr align="center" class="label">
										<td width="5%"/>
										<td class="tableHeader" width="15%">Report Type</td>
										<td class="centerTableHeader" width="10%">Report Version</td>
										<td class="centerTableHeader" width="20%">Report Status</td>
										<td class="centerTableHeader" width="10%"># of AEs</td>
										<td class="tableHeader" width="10%">Review Comments</td>
										<td class="tableHeader" width="20%">Review Status</td>
									</tr>
								</thead>
								<c:forEach items="${reportingPeriod.reports}" var="report" varStatus="rStatus">
									<%--<ae:oneRoutingExpeditedReportRow aeReport="${report}" index="${statusAeReport.index}" /> --%>
									<ae:oneRoutingReportRow report="${report}" rpIndex="${rStatus.index}" />
								</c:forEach>
							</c:when>					
							<c:otherwise>There are no reports for this reporting period.</c:otherwise>
							</c:choose>
						</table>

					</div>
	
				</td>
			</tr>
			<tr>
				<td width="100%">
				    <span style="font-size:13px; font-weight:bold;">All Adverse Events for this Reporting Period</span>
					<ae:routingAndReviewListAllAeSection reportingPeriod="${reportingPeriod.adverseEventReportingPeriod}" isDCPStudy="${reportingPeriod.dcpSponsoredStudy}"/>
				</td>
			</tr>
		</table>			
	</td>
</tr>