<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.dto.ReportDTO" description="The report that is printed by this row." %>

<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />

<tr align="center" id="row${rpIndex}" class="${repcurrClass}">
	<td align="left" width="15%">
		${report.name}
	</td>
	<td>
		format
	</td>
	<td align="center" width="20%" id="report-${reportingPeriod.id}-status">${report.status.displayName }</td>
	<%-- <td width="10%" id="aeReport-${aeReport.id}-status">${aeReport.reviewStatus.displayName}</td>
								<td width="10%">
									<a href="#" onClick="displayPopup('aeReport', ${aeReport.id})">
										<img src="<chrome:imageUrl name="../edit.png" />" />
									</a>
								</td>
								<td width="20%">
									<select onChange="advanceWorkflow(this,${aeReport.workflowId }, ${aeReport.id }, 'aeReport')" class="wf${aeReport.workflowId }">
										<option value="Please select">Please select</option>
										<c:forEach items="${aeReport.possibleActions}" var="rAction">
											<option value="${rAction}">${rAction}</option>
										</c:forEach>
									</select>
									<img id="aeReport-${aeReport.id}-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
								</td> --%>
</tr>
