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
		<div>
			<img id="sliderWFAction-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
			<a id="actions-menu-${report.id }" class="submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"><span class="ui-icon ui-icon-triangle-1-s"></span>Actions</a>
		</div>
		<div id="options-actions-menu-${command.reportId}" style="display:none;z-index:1">
			<ul>
				<span id="sliderWFAction"></span>
			</ul>
			<c:if test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable && isUserSAECoordinato}" >
				<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}" >
					<li><a class="submitter-green" href="#" onclick="javascript:submitReport(${report.id });" >Submit <img src="<chrome:imageUrl name="../buttons/button_icons/small/continue_icon_small.png"/>" alt="" /></a></li>	
				</c:if>
			</c:if>
			<c:if test="${command.study.caaersXMLType}">
				<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=xml'/>','_self')"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/> Export caAERS XML</a></li>
				<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
			</c:if>
			<c:if test="${command.study.adeersPDFType}">
				<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=pdf'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export AdEERS PDF</a></li>
				<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
			</c:if>
		</div> 
	</td>
</tr>