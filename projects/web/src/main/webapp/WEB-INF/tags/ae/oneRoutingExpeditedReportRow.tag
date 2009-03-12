<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" type="java.lang.Integer" description="The index of the expedited adverse event"%>
<%@attribute name="aeReport" type="gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO" description="The expedited adverse event report DTO that is printed by this row." %>
<c:set var="aeReportPageURL"
	value="/pages/ae/reviewResolver?aeReport=${aeReport.id}&viewOnly=true" />
<tr class="report-row">
	<td colspan=3>
		<table width="100%">
		<c:forEach items="${aeReport.reports}" var="report" varStatus="rStatus">
			<tr>
				<td align="left" width="50%">
					<a href="<c:url value="${aeReportPageURL}"/>">${report.name}</a>
				</td>
				<td width="25%">
					<%-- <SELECT id="actions-${report.id}" name="actions" onChange="executeAction(${report.id},'<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}'/>')"> --%>
					<SELECT id="actions-${report.id}" name="actions" onChange="executeAction(${report.id}, '<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${aeReport.id}'/>')">
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
				</td>
				<td align="center" width="25%" id="report-${reportingPeriod.id}-status">
					${report.status.displayName }
				</td>
			</tr>
		</c:forEach>
		</table>
	</td>
	<td width="10%" id="aeReport-${aeReport.id}-status">${aeReport.reviewStatus.displayName}</td>
	<td width="5%" align="center">
		<a href="#" onClick="displayPopup('aeReport', ${aeReport.id})">
			<img src="<chrome:imageUrl name="../edit.png" />" />
		</a>
	</td>
	<td width="25%" align="center">
		<select onChange="advanceWorkflow(this,${aeReport.workflowId }, ${aeReport.id }, 'aeReport')" class="wf${aeReport.workflowId }" style="width: 100px">
			<option value="Please select">Please Select</option>
			<c:forEach items="${aeReport.possibleActions}" var="rAction">
				<option value="${rAction}">${rAction}</option>
			</c:forEach>
		</select>
		<img id="aeReport-${aeReport.id}-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
	</td>
</tr>