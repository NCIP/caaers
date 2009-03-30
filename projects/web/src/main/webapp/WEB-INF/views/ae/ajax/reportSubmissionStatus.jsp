<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>

<script>
    function showToolTip(text, title) {
        Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
    }
</script>
<c:set var="theReport" value="${command.aeReport.reports[command.reportIndex] }"/>
<c:set var="reportStatus" value="${command.lastVersion.reportStatus}"/>
<c:set var="lastVersion" value="${command.lastVersion}"/>

<td id="reportNameId">
	<div class="label" id="report-label">${theReport.reportDefinition.label}</div>
	<div id="report-link" style="display:none">
		<a href="<c:url value="/pages/ae/edit?aeReport=${theReport.aeReport.id}"/>">
			${theReport.reportDefinition.label}
		</a>
	</div>
</td>
<c:if test="${theReport.reportDefinition.amendable == true}">
	<td align="center">
		<div class="label">${lastVersion.reportVersionId}</div>
	</td>
</c:if>
<c:if test="${theReport.reportDefinition.amendable == false}">
	<td/>
</c:if>
<td id="reportSubmissionStatus">
	<c:if test="${lastVersion.reportStatus == 'COMPLETED'}" >
		<ae:oneListReportSubmissionStatus theReport="${theReport}" reportStatus="${lastVersion.reportStatus}" lastVersion="${lastVersion}"/>
	</c:if>	
	<c:if test="${lastVersion.reportStatus == 'FAILED'}" >
		<ae:oneListReportSubmissionStatus theReport="${theReport}" reportStatus="${lastVersion.reportStatus}" lastVersion="${lastVersion}"/>           			
	</c:if>
	<c:if test="${lastVersion.reportStatus == 'INPROCESS'}" >
		<span class="dueOn" >
			<i>Submission to AdEERS in process</i>
		</span>
		<img id="reportStatus-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style=""/>           			
	</c:if>
</td>
<td>
	<c:if test="${(theReport.reportDefinition.amendable == false) or (theReport.isLatestVersion == true)}">
		<c:if test="${(lastVersion.reportStatus == 'PENDING') or (lastVersion.reportStatus == 'FAILED')}" >
			<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${theReport.id}"/>"><img src="<chrome:imageUrl name="../buttons/button_icons/small/check_icon_small.png" />" alt=""/> Submit</a>	
		</c:if>
		<c:if test="${theReport.reportDefinition.amendable and ( (lastVersion.reportStatus == 'WITHDRAWN') or (lastVersion.reportStatus == 'COMPLETED') )}" >
			<center>
				<a href="<c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&reportId=${theReport.id}&action=amendReport"/>">Amend</a>
			</center>
		</c:if>
	</c:if>					
</td>
