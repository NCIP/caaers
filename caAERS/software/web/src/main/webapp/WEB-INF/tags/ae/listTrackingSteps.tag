<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@attribute name="reportVersion" type="gov.nih.nci.cabig.caaers.domain.report.ReportVersion" required="true" description="The report that is being rendered" %>
<%@attribute name="reportTracking" type="gov.nih.nci.cabig.caaers.domain.report.ReportTracking" required="true" description="The report that is being rendered" %>
<style type="text/css">

.steps {
	color: black;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 17px;

	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-repeat: repeat-x;
	background-position: bottom;
	
}
.steptrue {
	color: black;
}

.stepfalse {
	color: black;
}

.step {
	color: #969696;
}

.orow {
	background-color: #DFE4E8;
}
.info {
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 12px;
	text-align: left;
	font-style: normal;
}
</style>


<tr id="attempttable${reportTracking.id}" style="display:none;">
	<td></td>
	<td colspan=5>
	   <div class="eXtremeTable">
	    
		<table width="100%" border="0" cellspacing="0" class="steps"> <!-- This is the outer table -->			
			
			<ae:oneTrackingStep step="1" stepDesc="Initiate Submission Process" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.submissionInitiated}"  />
			
			<ae:oneTrackingStep step="2" stepDesc="Generate caAERS XML" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.submissionInitiated}"  />
		

		<c:choose>
		  <c:when test="${reportVersion.report.externalSystem == false}">
			<ae:oneTrackingStep step="3" stepDesc="Generate caAERS attachment(PDF)" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.attachmentGenerated}"  />

			<ae:oneTrackingStep step="4" stepDesc="Notify Email Recipients with caAERS attachment " reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.emailNotification}"  />
		  </c:when>
		  <c:otherwise>
			<ae:oneTrackingStep step="5" stepDesc="Send Message to ESB" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.connectedToESB}"  />
<!--
			<ae:oneTrackingStep step="6" stepDesc="Connect to External System (AdEERS Web Service)" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.connectedToExternalSystem}"  />
				
-->			<ae:oneTrackingStep step="7" stepDesc="Submit to External System (AdEERS Web Service)" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.submissionToExternalSystem}"  />

			<ae:oneTrackingStep step="8" stepDesc="Notify Submitter with Submission Status" reportTracking="${reportTracking}" reportVersionId="${reportVersion.id}" reportTrackingStatus="${reportTracking.notificationToSubmitter}"  />
		  </c:otherwise>
		</c:choose>		
		</table>
		</div>			
	</td>
</tr>