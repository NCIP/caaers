<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" type="java.lang.Integer" description="The index of the expedited adverse event"%>
<%@attribute name="aeReport" type="gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO" description="The expedited adverse event report DTO that is printed by this row." %>

<c:set var="aeReportPageURL"
	value="/pages/ae/reviewResolver?aeReport=${aeReport.id}&viewOnly=true" />
		<c:forEach items="${aeReport.reports}" var="report" varStatus="rStatus">
			<%-- the following check has been moved to gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory.createReportDTOs(ExpeditedAdverseEventReport, String) --%>
				<tr class="report-row">
					 <td align="left" width="36%">
					${report.name}
					</td>
					<td align="center" width=24%" id="report-${reportingPeriod.id}-status">
						<ae:oneListReportSubmissionStatus theReport="${report.report}" reportStatus="${report.status}" lastVersion="${report.report.lastVersion}"/>
					</td>
					<td width="10%" id="report-${report.id}-status">${report.reviewStatus.displayName}</td>
				    <td width="25%" align="center">
						 <script type="text/javascript">
					 	var options_report_${report.id} = [];
						<c:forEach items="${report.possibleActions}" var="rAction">
						options_report_${report.id}.push('${rAction}');
						</c:forEach>
						</script>
						 <img border="0" style="cursor: pointer;
       					   margin-right: 15px;" id="course_routingreview_reportcycle_${report.id }"  onmouseover="showRoutingReviewCourseMenuOptions( ${report.workflowId}, ${report.id}, 'report',${aeReport.id},'${report.reviewStatus.displayName}')" src="/caaers/images/orange-actions.gif">
						 
						<img id="report-${report.id}-indicator" src="<c:url value="/images/indicator.white.gif"/>?${requestScope.webCacheId}" alt="activity indicator" style="display:none;"/>
					</td>
				</tr>
		</c:forEach>
