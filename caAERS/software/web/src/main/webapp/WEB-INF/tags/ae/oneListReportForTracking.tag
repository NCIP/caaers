<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="reportVersion" type="gov.nih.nci.cabig.caaers.domain.report.ReportVersion" required="true" description="The report that is being rendered" %>




<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />

	<tr align="center" id="${index}" class="${currClass}" onmouseout="this.className='${currClass}'" onmouseover="this.className='highlight'">
		<td width="2%"><chrome:collapsableInputElement targetID="table${reportVersion.id}" collapsed="true" id="collapseElement${reportVersion.id}"/></td>
		<td width="10%" align="center" onclick="expandImageClick('collapseElement${reportVersion.id}', 'table${reportVersion.id}');">${reportVersion.id }</td>
		<td width="13%" align="center" onclick="expandImageClick('collapseElement${reportVersion.id}', 'table${reportVersion.id}');">${reportVersion.reportVersionId}</td>
		<td width="25%" align="left" onclick="expandImageClick('collapseElement${reportVersion.id}', 'table${reportVersion.id}');">${reportVersion.submitter.firstName} ${reportVersion.submitter.lastName}</td>
		<td width="25%" align="left" onclick="expandImageClick('collapseElement${reportVersion.id}', 'table${reportVersion.id}');">
			<tags:formatDate value="${reportVersion.submittedOn}"/>
		</td>
		<td width="25%" align="left" onclick="expandImageClick('collapseElement${reportVersion.id}', 'table${reportVersion.id}');">
			<ae:oneListReportSubmissionStatus theReport="${reportVersion.report}" reportStatus="${reportVersion.reportStatus}" lastVersion="${reportVersion}"/>
		</td>
	</tr>


      
<tr id="table${reportVersion.id}" style="display:none;">
	<td></td>
	<td colspan=5>
	   <div class="eXtremeTable">	    
		<table width="100%" border="0" cellspacing="0" class="steps"> 		
			<c:forEach items="${reportVersion.reportTrackingsForDisplay}" var="reportTracking" varStatus="rtStatus">
				<tr >
					<td width="2%"><chrome:collapsableInputElement targetID="attempttable${reportTracking.id}" collapsed="true" id="collapseElement${reportTracking.id}"/></td>
					<td width="10%" align="left">Attempt # ${reportTracking.attemptNumber}</td>
				</tr>
				<ae:listTrackingSteps reportVersion="${reportVersion}" reportTracking="${reportTracking}"/>
			 </c:forEach>
		</table>
		</div>			
	</td>
</tr>
      




