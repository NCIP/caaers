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
	<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
		<c:if test="${report.status ne 'WITHDRAWN' and report.status ne 'REPLACED' and report.status ne 'AMENDED' and report.status ne 'COMPLETED'}">
			<chrome:division collapsable="true" title="${report.reportDefinition.label}" id="division-${report.id}">
				<div class="row">
					<div class="leftpanel">
                        <div class="row">
                            <div class="label">
                                Status
                            </div>
                            <div class="value" id="report-status-${report.id}">
                                <c:if test="${report.lastVersion.reportStatus == 'PENDING'}">
                                    <span class="dueOn">
                                        <c:if test="${not empty report.lastVersion.dueOn}">
                                            <i>Due on</i>
                                            <b><tags:formatDate value="${report.lastVersion.dueOn}" /></b>
                                        </c:if>
                                        <c:if test="${ empty report.lastVersion.dueOn}">
                                            <i>Amendment Due</i>
                                        </c:if>
                                    </span>
                                </c:if>
                                <c:if test="${report.lastVersion.reportStatus == 'WITHDRAWN'}">
                                    <span class="submittedOn"><i>Withdrawn</i>
                                        <br>
                                        <b><tags:formatDate value="${report.lastVersion.withdrawnOn}" /></b>
                                    </span>
                                </c:if>
                                <c:if test="${report.lastVersion.reportStatus == 'COMPLETED'}">
                                    <span class="submittedOn"><i>Submitted on </i>
                                        <br>
                                        <b><tags:formatDate value="${report.lastVersion.submittedOn}" /></b>
                                    </span>
                                </c:if>
                                <c:if test="${report.lastVersion.reportStatus == 'FAILED'}">
                                    <span class="dueOn"><i>Submission to AdEERS failed </i></span>
                                </c:if>
                                <c:if test="${report.lastVersion.reportStatus == 'INPROCESS'}">
                                    <span class="dueOn"><i>Submission to AdEERS in process</i></span>
                                </c:if>
                            </div>
                        </div>
					</div>
					<div class="rightpanel">
						<c:if test="${report.reportDefinition.amendable == true}">
							<div class="row">
								<div class="label">
									Amendment #
								</div>
								<div class="value">
									${report.lastVersion.amendmentNumber}
								</div>
							</div>
						</c:if>
					</div>
				</div>
                <c:choose>
                    <c:when test="${reportMessages[report.id].submittable}">
                        <div class="row" style="margin-left:102px; background-color:#C8FFBF; padding:10px; width:500px; font-weight:bolder;">
                                <img src="<chrome:imageUrl name="../buttons/button_icons/check_icon.png"/>" alt="" style="vertical-align:middle; margin-right:10px;" /> Ready to submit!
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row" style="margin-left:102px; background-color:#FFDFDF; padding:10px; width:500px;">
                            <h3 style="border:none; color:black; font-size:1.1em;">
                                Information remaining to complete
                            </h3>
                            
                                
                                    <c:forEach items="${reportMessages[report.id].messages}" var="sectionEntry">
                                        
                                            <chrome:division title="${sectionEntry.key.displayName} section" collapsable="true" collapsed="true" id="${sectionEntry.key.displayName}-${report.id}">
											<ul>
												<c:forEach items="${sectionEntry.value}" var="msg">
													<li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
	                                        	</c:forEach>
												<li><a href="javascript:goToPage('${sectionEntry.key}')">Go to this page ></a></li>
											</ul>
											</chrome:division>
                                       
                                    </c:forEach>
                                
                            
                        </div>
                    </c:otherwise>
                </c:choose>
				<div style="text-align:right;">
					
					<a id="actions-menu-${report.id}" class="submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"><span class="ui-icon ui-icon-triangle-1-s"></span>Actions</a>
				</div>
                <div id="options-actions-menu-${report.id}" style="display:none;">
					<ul>
						<c:set var="exportOptionsCount" value="0"/>
	                    <c:if test="${command.study.caaersXMLType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=xml'/>','_self')"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/> Export caAERS XML</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
	                    <c:if test="${command.study.adeersPDFType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=pdf'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export AdEERS PDF</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
	                    <c:if test="${command.study.medwatchPDFType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=medwatchpdf'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export MedWatch 3500A PDF</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
	                    <c:if test="${command.study.dcpSAEPDFType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=dcp'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP SAE PDF</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
	                    <c:if test="${command.study.ciomsPDFType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=cioms'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export CIOMS PDF</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
	                    <c:if test="${command.study.ciomsSaePDFType}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=ciomssae'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP Safety Report PDF</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>

                        <%--
                        DISPLAY ALL SUCTOME REPORTS ASSIATES TO DATA COLLECTION
                        --%>

	                    <c:if test="${report.reportDefinition.reportFormatType.code == 7}">
	                        <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.aeReport.id}&reportId=${report.id}&format=customPDF'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> (Custom) ${report.reportDefinition.label}</a></li>
	                    	<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
						</c:if>
						<c:if test="${command.workflowEnabled == true}">
							<span id="sliderWFAction-${report.id }" style="width:100%; font-size:0;"></span>
						</c:if>
	                    <c:if test="${reportMessages[report.id].submittable}">
	                    	<c:if test="${report.reportDefinition.amendable and (report.lastVersion.reportStatus == 'COMPLETED') }">
								<li><a href="#" onclick="javascript:if(confirm('Are you sure you want to amend this report?')){doAction('amend', '${report.aeReport.id}', '${report.id}')};" >Amend</a></li>
	                    	</c:if>
	                        <c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}">
	                            <c:if test="${!command.workflowEnabled || isSuperUser}">
	                            	<li><a class="submitter-green" href="#" onclick="javascript:doAction('submit', '${report.aeReport.id}', '${report.id}');" >Submit <img src="<chrome:imageUrl name="../buttons/button_icons/small/continue_icon_small.png"/>" alt="" /></a></li>
	                            </c:if>
	                        </c:if>
	                    </c:if>
						<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}">
	                        <li><a class="submitter-red" href="#" onclick="javascript:if(confirm('Are you sure you want to withdraw this report?')){doAction('withdraw', '${report.aeReport.id}', '${report.id}')};">Withdraw</a></li>
	                    </c:if>
					</ul>
                </div>
			</chrome:division>
		</c:if>
	</c:forEach>
	
</div>