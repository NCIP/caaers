<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />
<c:set var="lastVersion" value="${report.lastVersion}" />
<c:set var="reportStatus" value="${lastVersion.reportStatus}" />

<style>
    .fg-menu a:link, .fg-menu a:visited, .fg-menu a:hover, .fg-menu a:active {
        font-size:10pt;
    }
</style>

<script>

    jQuery(document).ready(function() {
        initPage();
    });

    function initPage() {
        createDropDowns();
    }

    function createDropDowns() {
        jQuery(".actionsButton").each(function() {
            id = jQuery(this).attr("id");
            options = "options-" + id;
            jQuery("#"+id).menu({
                content: jQuery("#" + options).html(),
                maxHeight: 180,
                width: 230,
                positionOpts: {
                    directionV: 'down',
                    posX: 'right',
                    posY: 'bottom',
                    offsetX: 0,
                    offsetY: 0
                },
                showSpeed: 50
            });
        });
    }

</script>

<c:if test="${reportStatus ne 'REPLACED' }">
	<tr align="center" id="row${rpIndex}" class="${repcurrClass}">
		<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
		<td align="left" width="15%">
			<c:if test="${report.active}">
				<a href="<c:url value="/pages/ae/reviewResolver?aeReport=${report.aeReport.id}&viewOnly=true&report=${report.id }"/>">
					${report.reportDefinition.label}
				</a>
			</c:if>
			<c:if test="${not report.active}">
				${report.reportDefinition.label }
			</c:if>
		</td>
		<c:if test="${report.reportDefinition.amendable == true}">
		            		<td align="center" width="10%"><div class="label">${report.lastVersion.amendmentNumber}</div></td>
		            	</c:if>
		            	<c:if test="${report.reportDefinition.amendable == false}">
		            		<td width="10%"/>
		            	</c:if>
		<td width="10%">${report.aeReport.numberOfAes}</td>
		<td width="20%" align="left">
			${command.dataEntryStatus[report.id] ? 'Complete' : 'In-progress'}
		</td>
		<td width="20%" id="status${report.id}" align="left">
			<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${reportStatus}" lastVersion="${lastVersion}"/>
		</td>
		<td width="20%" id="action${report.id}" align="center">

            <div style="text-align:right;">
                <img id="actions-menu-${report.id}" class="actionsButton" src='<c:url value="/images/orange-actions.gif" />' border='0' style='cursor:pointer;'>
                <%--<a id="actions-menu-${report.id}" class="submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"><span class="ui-icon ui-icon-triangle-1-s"></span><span style="color:white">Actions</span></a>--%>
            </div>
            <div id="options-actions-menu-${report.id}" style="display: none;">
                <ul>
                	<c:if test="${report.aeReport.notificationMessagePossible and (not empty configuration.map.pscBaseUrl)}"><li><a href="#" onclick="doIt('notifyPSC', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/notify.png"/>" alt=""/>&nbsp;Notify PSC</a></li></c:if>
                    <c:if test="${reportStatus eq 'PENDING' or reportStatus eq 'FAILED' or reportStatus eq 'WITHDRAW_FAILED'}"><li id="WITHDRAW_${report.id}"><a href="#" onclick="doIt('withdraw', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')" class="submitter-red"><img src="<chrome:imageUrl name="../blue/Withdraw-icon-small.png" />" alt="" />&nbsp;Withdraw</a></li></c:if>
                    <c:if test="${command.reportsSubmittable[report.id]}"><li id="SUBMIT_${report.id}"><a class="submitter-green" href="#" onclick="doIt('submit', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/submit-small.png"/>" alt="" />&nbsp;Review &amp; Submit</a></li></c:if>
                    <c:if test="${report.reportDefinition.amendable and (reportStatus eq 'COMPLETED') and command.amendAnOption}"><li><a href="#" onclick="doIt('amend', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/Amend-icon-small.png"/>" alt="" />&nbsp;Amend</a></li></c:if>
                    <c:if test="${(reportStatus eq 'COMPLETED' or reportStatus eq 'AMENDED' )and (not empty lastVersion.submissionUrl)}"><li><a class="submitter-green" href="#" onclick="doIt('adeers', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt="" />&nbsp;View in AdEERS</a></li></c:if>
                    <li><a href="#" onclick="doIt('xml', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/>&nbsp;Export caAERS XML</a></li>
                    <li><a href="#" onclick="doIt('e2b', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/>&nbsp;Export E2B</a></li>
                    <li><a href="#" onclick="doIt('pdf', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/>&nbsp;Export AdEERS PDF</a></li>
                    <li><a href="#" onclick="doIt('medwatchpdf', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/>&nbsp;Export MedWatch 3500A PDF</a></li>
                    <li><a href="#" onclick="doIt('dcp', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/>&nbsp;Export DCP SAE PDF</a></li>
                    <li><a href="#" onclick="doIt('cioms', '${report.id}', '${report.aeReport.id}', '${lastVersion.submissionUrl}')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/>&nbsp;Export CIOMS PDF</a></li>                    
                </ul>
            </div>
            <c:if test="${report.aeReport.notificationMessagePossible}"><span class="notify-unit" id="notify-unit-${report.aeReport.id}"><tags:indicator id="notify-indicator-${report.aeReport.id}"/></span></c:if>

		</td>
	</tr>
	<tr id="reptable${report.id}" style="display:none;">
		<td/><td/>
		<td colspan=5>
			<div class="eXtremeTable">
				<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
						<thead>
						<tr align="center" class="label">
							<td class="tableHeader" width="25%">AE Term</td>
							<td class="centerTableHeader" width="25%">Grade</td>
							<td class="tableHeader" width="25%">AE Start Date</td>
							<td class="tableHeader" width="25%">Requires Expedited Reporting?</td>
						</tr>
					</thead>
							
					<c:forEach items="${report.aeReport.adverseEvents}" var="ae" varStatus="statusAE">
						<ae:oneListAeRow index="${statusAE.index}" ae="${ae}" width="25%"/>
					</c:forEach>	
				</table>
			</div>
		</td>
	</tr>
</c:if>