<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The evaluation period that is being rendered" %>

<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />
<c:set var="reportingPeriodPageURL" 
	value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&adverseEventReportingPeriod=${reportingPeriod.id}&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true" />

<tr align="center" id="${index}" class="${currClass}">
	<td>
		<chrome:collapsableElement targetID="table${reportingPeriod.id}" collapsed="true" id="ID_01"/>
	</td>
	<td width="15%" align="left">
		<a href="<c:url value="${reportingPeriodPageURL}"/>">
			${reportingPeriod.name}
		</a>
	</td>
	<td width="10%">${reportingPeriod.numberOfReports}</td>
	<td width="10%">${fn:length(reportingPeriod.evaluatedAdverseEvents)}</td>
	<td align="left">${reportingPeriod.dataEntryStatus}</td>
	<td align="left">${reportingPeriod.reportStatus}</td>
	<td width="20%"></td>
</tr>

<tr id="table${reportingPeriod.id}" style="display:none;" class="${currClass}">
	<td></td>
	<td></td>
	<td colspan=5>
		<table width="100%" border="0" cellspacing="0"> <!-- This is the outer table -->
			<tr>
				<td width="100%">
					<div class="eXtremeTable">
						<table width="100%" border="0" cellspacing="0" class="rpTableRegion">
						  <c:choose>
							<c:when test="${fn:length(reportingPeriod.aeReports) gt 0}">
								<thead>
									<tr align="center" class="label">
										<td width="5%"/>
										<td class="tableHeader" width="15%">Report Type</td>
										<td class="centerTableHeader" width="20%"># of AEs</td>
										<td class="tableHeader" width="20%">Data Entry Status</td>
										<td class="tableHeader" width="20%">Submission Status</td>
										<td class="tableHeader" width="20%">Options</td>
									</tr>
								</thead>
								<c:forEach items="${reportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
									<ae:oneListExpeditedReportRow aeReport="${aeReport}" index="${statusAeReport.index}" />
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
					<ae:listAllAeSection reportingPeriod="${reportingPeriod}"/>
				</td>
			</tr>
		</table>			
	</td>
</tr>