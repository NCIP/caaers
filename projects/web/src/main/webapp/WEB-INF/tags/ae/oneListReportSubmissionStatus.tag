<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="theReport" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<%@attribute name="reportStatus" required="true" type="gov.nih.nci.cabig.caaers.domain.ReportStatus" %>
<%@attribute name="lastVersion" type="gov.nih.nci.cabig.caaers.domain.report.ReportVersion" required="true" description="The last version of the report" %>

<%--
  Link : completed , inprocess , failed 
  Non link : Pending , Withdrawn 
  
  Class : dueOn -  for link
  Class : submittedOn for non links
--%>
<c:set var="detailsEnabled" value="${(reportStatus eq 'COMPLETED') or (reportStatus eq 'INPROCESS') or (reportStatus eq 'FAILED')  }" />

<c:if test="${detailsEnabled}">
	<span class="dueOn"><a href="#" onclick="javascript:showDetails('table${theReport.id}');"><i>${lastVersion.statusAsString}</i></a></span>
	<div id="table${theReport.id}"	style="position: absolute; display: none;width:400px; left: 520px;">
		<table class="tableRegion" width="100%" style="background:gray;">
		   <tr align="right">
	 	     <td><a href="#" onclick="javascript:hideDetails('table${theReport.id}');"><img id="close-image" src="<c:url value="/images/rule/window-close.gif"/>"/>	</a></td>
	       </tr>
	       <tr>
	         <td>
				<c:choose>
					<c:when test="${reportStatus eq 'INPROCESS'}">
					 <font color="red">
						<i>Submission to AdEERS in process</i>
						<br />
						Refresh to update the Submission status. Incase if the submission status is hung for more than few minutes, please try the resubmit option. 
					 </font>
					</c:when>
					<c:when test="${reportStatus eq 'FAILED'}">
					 <font color="red">
						<i>Submission to AdEERS  failed!</i>
						${fn:replace(lastVersion.submissionMessage,".","<br>")}
					 </font>
					</c:when>
					<c:when test="${reportStatus eq 'COMPLETED'}">${fn:replace(lastVersion.submissionMessage,".","<br>")}</c:when>
				</c:choose>				
			
			</td>
	       </tr>
	       <tr>
	         <td>
			
				<c:choose>
					<c:when test="${reportStatus eq 'COMPLETED'}">
						<a href="${lastVersion.submissionUrl}" target="_blank">${lastVersion.submissionUrl}</a>
					</c:when>
				</c:choose>
			
			</td>
	       </tr>
	    </table>
	</div>
</c:if>

<c:if test="${not detailsEnabled}">
	<span class="submittedOn"><i>${lastVersion.statusAsString}</i></span>
</c:if>