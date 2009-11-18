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

<div id="reportNameId" class="row">
	<div class="label">Report Name</div>
	<div id="report-link" style="display:none" class="value">
		${theReport.reportDefinition.label} (<a href="<c:url value="/pages/ae/edit?aeReport=${theReport.aeReport.id}"/>"><img src="<chrome:imageUrl name="../edit.png"/>" alt=""/>  edit</a>)
	</div>
</div>
<c:if test="${theReport.reportDefinition.amendable == true}">
	<div class="row">
		<div class="label">Amendment</div>
		<div class="value">${lastVersion.reportVersionId}</div>
	</div>
</c:if>
<div class="row">
<div class="label">Submission Status</div>
<div id="reportSubmissionStatus" class="value">
	<c:if test="${lastVersion.reportStatus == 'COMPLETED'}" >
		<ae:oneListReportSubmissionStatus theReport="${theReport}" reportStatus="${lastVersion.reportStatus}" lastVersion="${lastVersion}"/>
	</c:if>	
	<c:if test="${(lastVersion.reportStatus eq= 'FAILED') or (lastVersion.reportStatus eq 'WITHDRAW_FAILED')}" >
		<ae:oneListReportSubmissionStatus theReport="${theReport}" reportStatus="${lastVersion.reportStatus}" lastVersion="${lastVersion}"/>           			
	</c:if>
	<c:if test="${lastVersion.reportStatus == 'INPROCESS'}" >
		<span class="dueOn" >
			<i>Submission to AdEERS in process</i>
		</span>
		<img id="reportStatus-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style=""/>           			
	</c:if>
</div>
</div>
<td>
	<c:if test="${(lastVersion.reportStatus == 'PENDING') or (lastVersion.reportStatus == 'FAILED')}" >
		<c:set var="ajaxhref">
            <c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${theReport.id}"/>
        </c:set>
		<div style="float:right;">
			<tags:button color="orange" value="Submit Again" icon="next" markupWithTag="a" href="${ajaxhref}" />
		</div>
        <br style="clear:both;"/>	
	</c:if>
	<c:if test="${theReport.reportDefinition.amendable and ( (lastVersion.reportStatus == 'WITHDRAWN') or (lastVersion.reportStatus == 'COMPLETED') )}" >
		<c:set var="ajaxhref">
            <c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&reportId=${theReport.id}&action=amendReport"/>
        </c:set>
		<div style="float:right;">
			<tags:button color="blue" value="Amend" icon="next" markupWithTag="a" href="${ajaxhref}" />
		</div>
        <br style="clear:both;"/>	
	</c:if>
</td>
