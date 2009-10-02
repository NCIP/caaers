<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="true" description="The course that is being rendered" %>

<script>
	Event.observe(window, "load", function(){
		$('actions-${reportingPeriod.id}').observe('click' , function(clickEvent){
			Event.stop(clickEvent);//to prevent it from expanding/collapsing the box
		});
	});
	
	function executeReportingPeriodActions(id){
		var sbox = $("actions-" + id);
		if(sbox.value == 'editReportingPeriod'){
			if(confirm('Are you sure you want to take the action - Edit Adverse Events ?')){
				var url = '<c:url value="/pages/ae/reviewResolver?participant=${command.participant.id}&study=${command.study.id}&adverseEventReportingPeriod=' + id + '"/>';
				window.location = url;
			}else{
				return false;
			}
		} 
	}
	
</script>


<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />
<c:set var="reportingPeriodPageURL" value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&adverseEventReportingPeriod=${reportingPeriod.id}&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true" />

<tr align="center" id="${index}" class="${currClass}" onmouseout="this.className='${currClass}'" onmouseover="this.className='highlight'">
	<td><chrome:collapsableInputElement targetID="table${reportingPeriod.id}" collapsed="true" id="collapseElement${reportingPeriod.id}"/></td>
	<td width="15%" align="left" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');">${reportingPeriod.name }</td>
	<td width="10%" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');">${reportingPeriod.numberOfReports}</td>
	<td width="10%" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');">${fn:length(reportingPeriod.evaluatedAdverseEvents)}</td>
	<td align="left" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');">${reportingPeriod.dataEntryStatus}</td>
	<td align="left" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');"><span class="${reportingPeriod.reportStatus eq 'Reports Due' ? 'reportsDue' : reportingPeriod.reportStatus eq 'Report Submission Failed' ? 'reportsFailed' : reportingPeriod.reportStatus eq 'Reports Completed' ? 'reportsCompleted' : reportingPeriod.reportStatus eq 'Reports Overdue' ? 'reportsOverdue' : 'reportsNone' }" >${reportingPeriod.reportStatus}</span></td>
	<td width="20%" align="center" onclick="expandImageClick('collapseElement${reportingPeriod.id}', 'table${reportingPeriod.id}');">
		<SELECT style="width:100px;" id="actions-${reportingPeriod.id}" name="actions" onChange="javascript:executeReportingPeriodActions(${reportingPeriod.id})" >
				<OPTION selected value="none">Please select</OPTION>
		     	<OPTION value="editReportingPeriod">Edit Adverse Events</OPTION>
		</SELECT>
	</td>
</tr>

<tr id="table${reportingPeriod.id}" style="display:none;" class="${currClass}">
	<td></td>
	<td></td>
	<td colspan=5>
		<table width="100%" border="0" cellspacing="0"> <!-- This is the outer table -->
			<tr>
				<td width="100%">
					<div class="eXtremeTable">
						<table width="100%" border="0" cellspacing="0" class="rpTableRegion">
						  <c:choose>
							<c:when test="${fn:length(reportingPeriod.aeReports) gt 0}">
								<thead>
									<tr align="center" class="label">
										<td width="5%"/>
										<td class="tableHeader" width="15%">Report Type</td>
										<td class="centerTableHeader" width="10%">Amendment #</td>
										<td class="centerTableHeader" width="10%"># of AEs</td>
										<td class="tableHeader" width="20%">Data Entry Status</td>
										<td class="tableHeader" width="20%">Report Submission Status</td>
										<td class="tableHeader" width="20%">Options</td>
									</tr>
								</thead>
								<c:forEach items="${reportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
									<ae:oneListExpeditedReportRow aeReport="${aeReport}" index="${statusAeReport.index}" />
								</c:forEach>
							</c:when>					
							<c:otherwise>There are no reports for this course/cycle.</c:otherwise>
							</c:choose>
						</table>
					</div>
				</td>
			</tr>
			<%-- 
			<tr style="display:none">
				<td width="100%">
					<ae:listAllAeSection reportingPeriod="${reportingPeriod}"/>
				</td>
			</tr>
			--%>
		</table>			
	</td>
</tr>
