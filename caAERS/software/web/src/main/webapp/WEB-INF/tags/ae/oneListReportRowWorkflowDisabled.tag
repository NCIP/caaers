<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />
<c:set var="lastVersion" value="${report.lastVersion}" />
<c:set var="reportStatus" value="${lastVersion.reportStatus}" />

<c:if test="${reportStatus ne 'REPLACED' and reportStatus ne 'AMENDED'}">
	<tr align="center" id="row${rpIndex}" class="${repcurrClass}">
		<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
		<td align="left" width="15%">
			<c:if test="${!report.reportDefinition.amendable or report.isLatestVersion}">
					<c:if test="${report.aeReport.reportingPeriod.reportStatus != 'Reports Completed'}">
						<a href="<c:url value="/pages/ae/reviewResolver?aeReport=${report.aeReport.id}&viewOnly=true&report=${report.id }"/>">
							${report.reportDefinition.label}
						</a>
					</c:if>
					<c:if test="${report.aeReport.reportingPeriod.reportStatus == 'Reports Completed'}">
						${report.reportDefinition.label }
					</c:if>
			</c:if>
			<c:if test="${report.reportDefinition.amendable and !report.isLatestVersion}">
				${report.reportDefinition.label}
			</c:if>
		</td>
		<c:if test="${report.reportDefinition.amendable == true}">
			<td align="center" width="10%"><div class="label">${report.lastVersion.reportVersionId}</div></td>
		</c:if>
		<c:if test="${report.reportDefinition.amendable == false}">
			<td width="10%"/>
		</c:if>
		<td width="10%">${report.aeReport.numberOfAes}</td>
		<td width="20%" align="left">
			${command.reportsSubmittable[report.id] ? 'Complete' : 'In-progress'}
		</td>
		<td width="20%" id="status${report.id}" align="left">
			<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${reportStatus}" lastVersion="${lastVersion}"/>
		</td>
		<td width="20%" id="action${report.id}" align="center">
			
			<SELECT style="width:100px;" id="actions-rp-${report.id}" name="actions" onChange="executeAction(${report.id}, '<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}'/>', '${report.aeReport.id}', '${lastVersion.submissionUrl}')">
		     	<OPTION selected value="none">Please select</OPTION>
		     	<c:if test="${command.study.caaersXMLType}">
		     		<OPTION value="xml">Export caAERS XML</OPTION>
		     	</c:if>
		     	<c:if test="${command.study.adeersPDFType}">
		     		<OPTION value="pdf">Export AdEERS PDF</OPTION>
		     	</c:if>
		     	<c:if test="${command.study.medwatchPDFType}">
		     		<OPTION value="medwatchpdf">Export MedWatch 3500A PDF</OPTION>
		     	</c:if>
		     	<c:if test="${command.study.dcpSAEPDFType}">
		     		<OPTION value="dcp">Export DCP SAE PDF</OPTION>
		     	</c:if>
                <c:if test="${command.study.ciomsPDFType}">
                    <OPTION value="cioms">Export CIOMS PDF</OPTION>
                </c:if>
		     	<c:if test="${command.study.ciomsSaePDFType}">
		     		<OPTION value="ciomssae">Export DCP Safety Report PDF</OPTION>
		     	</c:if>
		     	<c:if test="${report.aeReport.notificationMessagePossible}">
		     		<OPTION value="notifyPSC">Notify PSC</OPTION>
		     	</c:if>

				<c:if test="${!report.reportDefinition.amendable or report.isLatestVersion}">
					<c:if test="${reportStatus eq 'PENDING' or reportStatus eq 'FAILED'}">
						<OPTION value="withdraw">Withdraw</OPTION>
					</c:if>
				</c:if>

                <c:if test="${command.reportsSubmittable[report.id]}">
                    <c:if test="${!report.reportDefinition.amendable or report.isLatestVersion}">
                        <c:choose>
                            <c:when test="${reportStatus eq 'PENDING' or reportStatus eq 'FAILED' or reportStatus eq 'INPROGRESS'}">
                            	<OPTION value="submit">Submit</OPTION>
                            </c:when>
                            <c:when test="${reportStatus eq 'PENDING' and (not empty lastVersion.submissionUrl)}">
                                <OPTION value="adeers">View in AdEERS</OPTION>
                                <OPTION value="amend">Amend</OPTION>
                            </c:when>
                            <c:when test="${report.reportDefinition.amendable and (reportStatus eq 'COMPLETED')}">
                                <OPTION value="amend">Amend</OPTION>
                            </c:when>
                        </c:choose>
                      </c:if>
                </c:if>

             </SELECT>
           
             <br>
	 		<c:if test="${report.aeReport.notificationMessagePossible}"><span class="notify-unit" id="notify-unit-${report.aeReport.id}"><tags:indicator id="notify-indicator-${report.aeReport.id}"/></span></c:if>
            
		</td>
	</tr>
	<tr id="reptable${report.id}" style="display:none;">
		<td/><td/>
		<td colspan=5>
			<div class="eXtremeTable">
				<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
						<thead>
						<tr align="center" class="label">
							<td class="tableHeader" width="25%">AE Term</td>
							<td class="centerTableHeader" width="25%">Grade</td>
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
</c:if>