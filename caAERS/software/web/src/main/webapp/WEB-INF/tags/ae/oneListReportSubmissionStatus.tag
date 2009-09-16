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
<c:set var="_statusCSS" value="${theReport.overdue ? 'reportsOverdue' : (reportStatus eq 'INPROCESS' or reportStatus eq 'PENDING') ? 'reportsDue' : (reportStatus eq 'FAILED' or reportStatus eq 'WITHDRAW_FAILED') ? 'reportsFailed' : 'reportsCompleted'}" />

<c:set var="detailsEnabled" value="${(reportStatus eq 'COMPLETED') or (reportStatus eq 'INPROCESS') or (reportStatus eq 'FAILED') or (reportStatus eq 'WITHDRAW_FAILED') or (reportStatus eq 'AMENDED') }" />

<c:if test="${detailsEnabled}">
	<span class="${_statusCSS }"><a style="cursor:pointer;" onClick="showToolTip(($('_ctx_${theReport.id}').innerHTML), '${lastVersion.statusAsString}')"><i><u class="${_statusCSS }" >${lastVersion.statusAsString}</u></i></a></span>
	<div id="_table${theReport.id}"	style="position: absolute; display: none; width:400px; left: 520px;">
        <div id="_ctx_${theReport.id}">
        <c:choose>
            <c:when test="${reportStatus eq 'INPROCESS'}">
                <i>Submission to AdEERS in process</i>
                <br />
                Refresh to update the Submission status. Incase if the submission status is hung for more than few minutes, please try the resubmit option.
            </c:when>
            <c:when test="${(reportStatus eq 'FAILED') or (reportStatus eq 'WITHDRAW_FAILED')}">
                <i>Submission to AdEERS  failed!</i>
                ${fn:replace(lastVersion.submissionMessage,".","<br>")}
            </c:when>
            <c:when test="${(reportStatus eq 'COMPLETED') or (reportStatus eq 'AMENDED')}">
            ${fn:replace(lastVersion.submissionMessage,".","<br>")}<br><a href="${lastVersion.submissionUrl}" target="_blank">${lastVersion.submissionUrl}</a>
            </c:when>
        </c:choose>
        </div>

    </div>
</c:if>

<c:if test="${not detailsEnabled}">
	<span class="${_statusCSS }"><i>${lastVersion.statusAsString}</i></span>
</c:if>