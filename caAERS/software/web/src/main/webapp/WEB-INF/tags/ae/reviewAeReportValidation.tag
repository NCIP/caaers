<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>


<tr>    				
	<td>
    	<div class="label" align="left">${report.reportDefinition.label}</div>
	</td>
	<c:if test="${report.reportDefinition.amendable == true}">
		<td align="center">
			<div class="label" align="center">${report.lastVersion.reportVersionId}</div>
		</td>
	</c:if>
	<c:if test="${report.reportDefinition.amendable == false}">
		<td/>
	</c:if>
	<td class="completion-messages">
		<c:choose>
			<c:when test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable}" >
				<div class="label" align="center">Yes</div>
			</c:when>
			<c:otherwise>
				<c:if test="${report.status ne 'COMPLETED'}">
					<p>Not yet.  Remaining to complete:</p>
					<c:forEach items="${reportMessages[report.id].messages}" var="sectionEntry">
						<h4>${sectionEntry.key.displayName} section</h4>
						<c:forEach items="${sectionEntry.value}" var="msg">
							<ul>
								<li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
							</ul>
						</c:forEach>
					</c:forEach>
				</c:if>
			</c:otherwise>
		</c:choose>
	</td>
	<td id="report-status">
		<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${report.lastVersion.reportStatus}" lastVersion="${report.lastVersion}"/>
	</td>
	<td id="report-action">
		<c:if test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable && isUserSAECoordinato}" >
				<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}" >
					<a href="javascript:submitReport(${report.id });"><img src="<chrome:imageUrl name="../buttons/button_icons/small/check_icon_small.png" />" alt=""/> Submit</a>	
				</c:if>
		</c:if>
	</td>
</tr>