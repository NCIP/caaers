<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />
<c:set var="lastVersion" value="${report.lastVersion}" />
<c:set var="reportStatus" value="${lastVersion.reportStatus}" />
<tr align="center" id="row${rpIndex}" class="${repcurrClass}">
	<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
	<td align="left" width="15%">
		<a href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}"/>">
			${report.reportDefinition.label}
		</a>	
	</td>
	<td width="20%">${report.aeReport.numberOfAes}</td>
	<td width="20%" align="left">
		${command.reportsSubmittable[report.id] ? 'Complete' : 'In-progress'}
	</td>
	<td width="20%" id="status${report.id}" align="left">
		<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${reportStatus}" lastVersion="${lastVersion}"/>
	</td>
	<td width="20%" id="action${report.id}" align="center">
		
		<SELECT id="actions-${report.id}" name="actions" onChange="executeAction(${report.id},'<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}'/>')">
	     	<OPTION selected label="none" value="none">None</OPTION>
	     	<c:if test="${command.study.caaersXMLType}">
	     		<OPTION label="xml" value="xml">caAERS XML</OPTION>
	     	</c:if>
	     	<c:if test="${command.study.adeersPDFType}">
	     		<OPTION label="pdf" value="pdf">AdEERS PDF</OPTION>
	     	</c:if>
	     	<c:if test="${command.study.medwatchPDFType}">
	     		<OPTION label="medwatchpdf" value="medwatchpdf">MedWatch 3500A PDF</OPTION>
	     	</c:if>
	     	<c:if test="${command.study.dcpSAEPDFType}">
	     		<OPTION label="dcp" value="dcp">DCP SAE PDF</OPTION>
	     	</c:if>
	     		<c:if test="${command.study.ciomsPDFType}">
	     	<OPTION label="cioms" value="cioms">CIOMS PDF</OPTION>
	     		</c:if>
	     	<c:if test="${command.study.ciomsSaePDFType}">
	     		<OPTION label="ciomssae" value="ciomssae">DCP Safety Report PDF</OPTION>
	     	</c:if>
	 	</SELECT>
	 	<br>
	 	<c:if test="${report.aeReport.notificationMessagePossible}">
	        <span class="notify-unit" id="notify-unit-${report.aeReport.id}">
	             <a id="notify-${report.aeReport.id}" class="notify" href="#">notify PSC</a>
	        <tags:indicator id="notify-indicator-${report.aeReport.id}"/>
	    </span>
	    </c:if>
		<c:if test="${command.reportsSubmittable[report.id]}">	
		  
			<c:choose>
				<c:when test="${reportStatus eq 'PENDING' or reportStatus eq 'FAILED'}">
					<a href="#" onClick="doAction('submit', ${report.aeReport.id},${report.id})">Submit</a><br>
					<a href="#" onClick="doAction('withdraw', ${report.aeReport.id},${report.id})">Withdraw</a>
				</c:when>
				<c:when test="${reportStatus eq 'COMPLETED' and (not empty lastVersion.submissionUrl)}">
					<a href="${lastVersion.submissionUrl}" target="_blank">View in AdEERS</a> <br>
					<a href="#" onClick="doAction('amend', ${report.aeReport.id},${report.id})">Amend</a>
				</c:when>
				<c:when test="${report.reportDefinition.amendable and (reportStatus eq 'WITHDRAWN' or reportStatus eq 'COMPLETED')}">
					<a href="#" onClick="doAction('amend', ${report.aeReport.id},${report.id})">Amend</a>
				</c:when>
				<c:when test="${reportStatus eq 'INPROGRESS'}">
					<a href="#" onClick="doAction('submit', ${report.aeReport.id},${report.id})">Resubmit</a>
				</c:when>
			</c:choose>
		  
		</c:if>
	</td>
</tr>
<tr id="reptable${report.id}" style="display:none;">
	<td/><td/>
	<td colspan=4>
		<div class="eXtremeTable">
			<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
				<thead>
					<tr align="center" class="label">
						<td class="tableHeader" width="25%">AE Term</td>
						<td class="tableHeader" width="25%">Grade</td>
						<td class="tableHeader" width="25%">AE Start Date</td>
						<td class="tableHeader" width="25%">Requires Expedited Reporting?</td>
					</tr>
				</thead>
						
				<c:forEach items="${report.aeReport.adverseEvents}" var="ae" varStatus="statusAE">
					<ae:oneListAeRow index="${statusAE.index}" ae="${ae}" width="25%"/>
				</c:forEach>	
			</table>
		</div>
	</td>
</tr>