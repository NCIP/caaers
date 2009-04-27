<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO" required="true" description="The course that is being rendered" %>

<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />
<%-- <c:set var="reportingPeriodPageURL" 
	value="/pages/ae/captureRoutine?participant=${reportingPeriod.participant.id}&study=${reportingPeriod.study.id}&_page=0&adverseEventReportingPeriod=${reportingPeriod.id}&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true" /> --%>
<c:set var="reportingPeriodPageURL"
	value="/pages/ae/reviewResolver?participant=${reportingPeriod.participant.id}&study=${reportingPeriod.study.id}&adverseEventReportingPeriod=${reportingPeriod.id}" />

<tr align="center" id="${index}" class="${currClass}">
	<td>
		<chrome:collapsableElement targetID="rptable${reportingPeriod.id}" collapsed="true" id="rpID_${reportingPeriod.id}"/>
	</td>
	<td width="18%" align="left">
		<a href="<c:url value="${reportingPeriodPageURL}"/>">${reportingPeriod.evaluationPeriodName}</a>
		<%-- ${reportingPeriod.evaluationPeriodName} --%>
	</td>
	<td width="22%">${reportingPeriod.evaluationPeriodTypeName}</td>
	<td width="25%" id="reportingPeriod-${reportingPeriod.id}-status">${reportingPeriod.reviewStatus.displayName}</td>
	<td>
		<a href="#" onClick="displayPopup('reportingPeriod', ${reportingPeriod.id})">
			<img src="<chrome:imageUrl name="../edit.png" />" /></td>
		</a>
	<td width="25%">
		<select onChange="advanceWorkflow(this,${reportingPeriod.workflowId }, ${reportingPeriod.id }, 'reportingPeriod')" class="wf${reportingPeriod.workflowId }" style="width: 150px">
			<option value="Please Select">Please Select</option>
			<c:forEach items="${reportingPeriod.possibleActions}" var="aStatus">
				<option value="${aStatus }">${aStatus}</option>
			</c:forEach>
		</select>
		<img id="reportingPeriod-${reportingPeriod.id}-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
	</td>
</tr>
<tr id="rptable${reportingPeriod.id}" style="display:none;" class="${currClass}">
	<td></td>
	<td></td>
	<td colspan=6>
		<table width="100%" border="0" cellspacing="0"> <!-- This is the outer table -->
			<tr>
				<td width="100%">
					<c:choose>
						<c:when test="${not empty reportingPeriod.aeReports}">
							<c:forEach items="${reportingPeriod.aeReports}" var="aeReport" varStatus="rStatus">
								<table width="100%" border="0" cellspacing="0" class="rpTableRegionOuter">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0" class="rpTableRegionOuter eXtremeTable">
												<thead>
													<tr class="label">
														<td class="tableHeader" width="36%">Report Name</td>
														<td class="centerTableHeader" width="24%">Report Submission Status</td>
														<td class="centerTableHeader" width="10%">Review Status</td>
														<td class="centerTableHeader" width="5%">Comments</td>
														<td class="centerTableHeader" width="25%">Action</td>
													</tr>						
												</thead>
													<ae:oneRoutingExpeditedReportRow aeReport="${aeReport}" index="${rStatus.index}" />
											</table>
										</td>
									</tr>
								</table>
							</c:forEach>
						</c:when>
						<c:otherwise>
							There are no reports for this course/cycle.
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<ae:routingAndReviewListAllAeSection reportingPeriod="${reportingPeriod.adverseEventReportingPeriod}" isDCPStudy="${reportingPeriod.dcpSponsoredStudy}"/>
				</td>
			</tr>
		</table>			
	</td>
</tr>