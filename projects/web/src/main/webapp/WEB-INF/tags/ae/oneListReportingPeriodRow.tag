<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The reporting period that is being rendered" %>


<% String currClass=index%2==0? "odd":"even"; %>
<tr align="center" id="${index}" class="<%= currClass %>" onMouseOver="this.className='highlight'"
				onMouseOut="this.className='<%= currClass %>'">
	<td><chrome:collapsableElement targetID="table${reportingPeriod.id}" collapsed="true" id="ID_01"/></td>
	<td width="15%">
		<a style="text-decoration:none" href="<c:url value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&adverseEventReportingPeriod=${reportingPeriod.id}&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true"/>">
			${reportingPeriod.name}</td>
		</a>	
	<td width="10%">${reportingPeriod.numberOfReports}</td>
	<td width="10%">${reportingPeriod.numberOfAes}</td>
	<td>${reportingPeriod.dataEntryStatus}</td>
	<td>${reportingPeriod.reportStatus}</td>
	<td width="20%"></td>
</tr>
<tr><td/></tr>
<tr id="table${reportingPeriod.id}" display="">
	<td></td><td></td>
	<td colspan=5>
		<table width="100%" border="0" cellspacing="0"> <!-- This is the outer table -->
			<tr>
				<td width="100%">
					<div class="eXtremeTable">
						<table width="100%" border="0" cellspacing="0" class="rpTableRegion">
							<c:if test="${fn:length(reportingPeriod.aeReports) > 0}">
								<thead>
									<tr align="center" class="label">
										<td width="5%"/>
										<td class="tableHeader" width="15%">Report Type</td>
										<td class="tableHeader" width="20%"># of AEs</td>
										<td class="tableHeader" width="20%">Data Entry Status</td>
										<td class="tableHeader" width="20%">Submission Status</td>
										<td class="tableHeader" width="20%">Options</td>
									</tr>
								</thead>
							</c:if>					
							<%int k=0; %>
							<c:forEach items="${reportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
							<c:forEach items="${aeReport.reports}" var="report" varStatus="statusReport">
								<% String repcurrClass=k%2==0? "odd":"even"; %>
								<tr align="center" id="row<%= k++ %>" class="<%= repcurrClass %>" onMouseOver="this.className='highlight'"
											onMouseOut="this.className='<%= repcurrClass %>'">
									<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
									<td width="15%">
										<a style="text-decoration:none" href="<c:url value="/pages/ae/editExpeditedReport?aeReport=${aeReport.id}"/>">
											${report.reportDefinition.name}
										</a>	
									</td>
									<td width="20%">${aeReport.numberOfAes}</td>
									<td width="20%">${report.status.displayName}</td>
									<td width="20%">${report.lastVersion.statusAsString}</td>
									<td width="20%"></td>
								</tr>
								<tr id="reptable${report.id}">
									<td/><td/>
									<td colspan=4>
										<div class="eXtremeTable">
											<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
												<thead>
													<tr align="center" class="label">
														<td class="tableHeader" width="25%">AE Term</td>
														<td class="tableHeader" width="25%">Grade</td>
														<td class="tableHeader" width="25%">Start of AE(Primary)</td>
														<td class="tableHeader" width="25%">Requires report</td>
													</tr>
												</thead>
														
												<%int l=0; %>
												<c:forEach items="${aeReport.adverseEvents}" var="ae" varStatus="statusReportAe">
													<ae:oneListAeRow index="<%= l++ %>" ae="${ae}" width="25%"/>
												</c:forEach>	
											</table>
										</div>
									</td>
								</tr>
							</c:forEach>	
							</c:forEach>	
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<ae:listAllAeSection reportingPeriod="${reportingPeriod}"/>
				</td>
			</tr>
		</table>			
	</td>
</tr>