<%-- 
	This page renders the report validation on the submit page in the aeReport flow.
	It was moved to this tag to support refreshing this part on the page once the physician signs-off on the
	submit page. 
	Author : Sameer Sawant.
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="report-validation-section">
    	<table class="tablecontent">
 			   	<c:if test="${not reportMessages[command.ZERO].submittable}">
    			<tr>
    				<th colspan="5" align="left">Common validation errors</th>
    			</tr>
    			<tr>
    				<td colspan="5" class="completion-messages">
    				<c:forEach items="${reportMessages[command.ZERO].messages}" var="sectionEntry">
                       <h4>${sectionEntry.key.displayName} section</h4>
                       <c:forEach items="${sectionEntry.value}" var="msg">
                          <ul>
                           <li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
                         </ul>
                       </c:forEach>
                     </c:forEach>
    				</td>
    			</tr>
    			<tr><td colspan="5"></td></tr>
    			</c:if>
    			<tr>
    				<th scope="col" align="left"><b>Report</b> </th>
    				<th scope="col" align="left"><b>Amendment #</b> </th>
    				<th scope="col" align="left"><b>Ready to submit?</b> </th>
    				<th scope="col" align="left"><b>Status</b> </th>
    				<th scope="col" align="left"><b>Options</b> </th>
    			</tr>
    			<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
    			<c:if test="${report.status ne 'WITHDRAWN' and report.status ne 'REPLACED' and report.status ne 'AMENDED'}">
    			<tr>    				
            		<td><div class="label">${report.reportDefinition.label}</div></td>
            		<c:if test="${report.reportDefinition.amendable == true}">
	            		<td align="center"><div class="label">${report.lastVersion.reportVersionId}</div></td>
	            	</c:if>
	            	<c:if test="${report.reportDefinition.amendable == false}">
	            		<td/>
	            	</c:if>
            		<td class="completion-messages">
                        <c:choose>
                            <c:when test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable}" >
                                Yes
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
            		<td id="report-status-${report.id}">
            			<c:if test="${report.lastVersion.reportStatus == 'PENDING'}" >
							<span class="dueOn" >
								<c:if test="${not empty report.lastVersion.dueOn}" >
            						<i>Due on</i> <br> <b><tags:formatDate value="${report.lastVersion.dueOn}" /></b>
            					</c:if>
            					<c:if test="${ empty report.lastVersion.dueOn}" >
            						<i>Amendment Due</i>
            					</c:if>
            				</span>
            			</c:if>
            			<c:if test="${report.lastVersion.reportStatus == 'WITHDRAWN'}" >
							<span class="submittedOn" >
            						<i>Withdrawn</i><br> <b><tags:formatDate value="${report.lastVersion.withdrawnOn}" /></b>
            				</span>
            			</c:if>
            			<c:if test="${report.lastVersion.reportStatus == 'COMPLETED'}" >
            				<span class="submittedOn" >
            					<i>Submitted on </i><br> <b><tags:formatDate value="${report.lastVersion.submittedOn}" /></b>
            				</span>
            			</c:if>	
            			<c:if test="${report.lastVersion.reportStatus == 'FAILED'}" >
             				<span class="dueOn" >
            					<i>Submission to AdEERS failed </i>
            				</span>           			
            			</c:if>
             			<c:if test="${report.lastVersion.reportStatus == 'INPROCESS'}" >
             				<span class="dueOn" >
            					<i>Submission to AdEERS in process</i>
            				</span>           			
            			</c:if>           		
            		</td>
            		<td id="action${report.id}">
						<SELECT style="width:100px;" id="actions-${report.id}" name="actions" onChange="executeAction(${report.id}, '<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}'/>', '${report.aeReport.id}', '${lastVersion.submissionUrl}')">
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
		     				<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}" >
		     					<OPTION value="withdraw">Withdraw</OPTION>
		     				</c:if>
		     				<c:if test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable}" >
								<c:if test="${(report.reportDefinition.amendable == false) or (report.isLatestVersion == true)}">
									<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}" >
										<c:if test="${!command.workflowEnabled || isSuperUser}">
											<OPTION value="submit">Submit</OPTION>
										</c:if>
									</c:if>
									<c:if test="${report.reportDefinition.amendable and ( (report.lastVersion.reportStatus == 'WITHDRAWN') or (report.lastVersion.reportStatus == 'COMPLETED') )}" >
										<OPTION value="amend">Amend</OPTION>
									</c:if>
								</c:if>					
							</c:if>
		     			</SELECT>
						
            		</td>
    			</tr>
    			</c:if>
    			</c:forEach>    						
    	</table>		
</div>