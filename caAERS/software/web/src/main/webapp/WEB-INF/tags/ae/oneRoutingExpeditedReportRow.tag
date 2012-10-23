<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" type="java.lang.Integer" description="The index of the expedited adverse event"%>
<%@attribute name="aeReport" type="gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO" description="The expedited adverse event report DTO that is printed by this row." %>

<c:set var="aeReportPageURL"
	value="/pages/ae/reviewResolver?aeReport=${aeReport.id}&viewOnly=true" />
		<c:forEach items="${aeReport.reports}" var="report" varStatus="rStatus">
			<%-- the following check has been moved to gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory.createReportDTOs(ExpeditedAdverseEventReport, String) --%>
			<%-- <c:if test="${report.status ne 'REPLACED' and report.status ne 'AMENDED'}"> --%>
				<tr class="report-row">
					 <td align="left" width="36%">
					 <%--
						<c:if test="${report.status ne 'COMPLETED'}">
							<a href="<c:url value="/pages/ae/reviewResolver?aeReport=${aeReport.id}&viewOnly=true&report=${report.id}"/>">${report.name}</a>
						</c:if>
						<c:if test="${report.status eq 'COMPLETED'}">
							${report.name}
						</c:if>
					--%>
					${report.name}
					</td>
					<td align="center" width=24%" id="report-${reportingPeriod.id}-status">
						<ae:oneListReportSubmissionStatus theReport="${report.report}" reportStatus="${report.status}" lastVersion="${report.report.lastVersion}"/>
					</td>
					<td width="10%" id="report-${report.id}-status">${report.reviewStatus.displayName}</td>
					<td width="5%" align="center">
						<a href="#" onClick="displayPopup('report', ${report.id})">
							<img src="<chrome:imageUrl name="../edit.png" />" />
						</a>
					</td>
				    <td width="25%" align="center">
				    <%--
						<select onChange="advanceWorkflow(this,${report.workflowId }, ${report.id }, 'report')" class="wf${report.workflowId }" style="width: 100px">
							<option value="Please Select">Please select</option>
							<c:forEach items="${report.possibleActions}" var="rAction">
								<option value="${rAction}">${rAction}</option>
							</c:forEach>
						</select>
					 --%>
						 <script type="text/javascript">
					 	var options_report_${report.id} = [];
						<c:forEach items="${report.possibleActions}" var="rAction">
						options_report_${report.id}.push('${rAction}');
						</c:forEach>
						</script>
						 <img border="0" style="cursor: pointer;
       					   margin-right: 15px;" id="course_routingreview_reportcycle_${report.id }"  onmouseover="showRoutingReviewCourseMenuOptions( ${report.workflowId}, ${report.id}, 'report',${aeReport.id},'${report.status}')" src="/caaers/images/orange-actions.gif">
						 
						<img id="report-${report.id}-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
					</td>
				</tr>
			<%--</c:if> --%>
		</c:forEach>
