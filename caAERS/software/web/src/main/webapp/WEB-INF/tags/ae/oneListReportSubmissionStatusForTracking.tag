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

<script>
    
</script>

<c:set var="_statusCSS" value="${theReport.overdue ? 'reportsOverdue' : (reportStatus eq 'INPROCESS' or reportStatus eq 'PENDING') ? 'reportsDue' : reportStatus eq 'FAILED' ? 'reportsFailed' : 'reportsCompleted'}" />

<c:set var="detailsEnabled" value="${(reportStatus eq 'COMPLETED') or (reportStatus eq 'INPROCESS') or (reportStatus eq 'FAILED')  }" />


	<span class="${_statusCSS }"><i>${lastVersion.statusAsString}</i></span>
