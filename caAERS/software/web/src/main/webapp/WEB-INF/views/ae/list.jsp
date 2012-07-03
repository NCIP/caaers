<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Manage Reports</title>
<tags:dwrJavascriptLink objects="createAE,adverseEventHistory"/>
<tags:slider renderComments="false" renderAlerts="true" display="${command.assignment.labLoads != null and fn:length(command.assignment.labLoads) > 0 ? '' : 'none'}" workflowType="report">
    <jsp:attribute name="labs">
    	<div id="labs-id" style="display:none;">
    		<tags:labs labs="${command.assignment.labLoads}"/>
    	</div>
    </jsp:attribute>
</tags:slider>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<script type="text/javascript">

	function showDetails(elId){
		$(elId).show();
	}

	function hideDetails(elId){
		$(elId).hide();
	}
	
	function doAction(action, aeReportId, reportId) {
        try {
            AjaxResult = null;
            if (action == 'withdraw') {
                createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
                    ajaxResult = result;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        var statusColumn = $("status" + reportId)
                        var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";
                        Element.update(statusColumn, statusColumnData);
                        updateDropDownAfterWithdraw(reportId);
                    }
                });
            } else if (action == 'submit') {
                var url = '<c:url value="/pages/ae/reviewResolver?from=list&ras=t&viewOnly=true" />' + '&aeReport=' + aeReportId + '&reportId=' + reportId;
                window.location = url;
            } else if (action == 'amend') {
                var url = '<c:url value="/pages/ae/edit"/>' + '?aeReport=' + aeReportId + '&report=' + reportId + '&action=amendReport';
                window.location = url;
            }
        } catch(e) {
            caaersLog(e);
        }
        
    }  

    function updateDropDownAfterWithdraw(reportId) {
        jQuery('#SUBMIT_' + reportId).remove();
        jQuery('#WITHDRAW_' + reportId).remove();
        createDropDowns();
    }

    function doIt(type, reportId, aeReportId, submissionURL) {
        executeAction(type, reportId, '<c:url value='/pages/ae/generateExpeditedfPdf?aeReport='/>' + aeReportId + '&reportId=' + reportId, aeReportId, submissionURL);
    }

    function executeAction(type, reportId, url, aeReportId, submissionUrl) {
            if(confirm('Are you sure you want to take the action ?')) {
                switch (type) {
                    case "notifyPSC": notifyPsc(aeReportId); break;
                    case "submit": doAction(type, aeReportId, reportId); break;
                    case "withdraw": doAction(type, aeReportId, reportId); break;
                    case "amend": doAction(type, aeReportId, reportId);  break;
                    case "adeers": window.open(submissionUrl, "_blank");  break;
                    default: window.open(url + "&format=" + type, "_self");
                }
            }else{
                return false;
            }
     }
     
     function notifyPsc(aeReportId) {
            AE.showIndicator("notify-indicator-" + aeReportId)
            createAE.pushAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-" + aeReportId)
                var unit = $("notify-unit-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }
        
     function notifyPscRoutineEvent(aeReportId) {

            AE.showIndicator("notify-indicator-routine-" + aeReportId)
            createAE.pushRoutineAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-routine-" + aeReportId)
                var unit = $("notify-unit-routine-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }

     function executeReportingPeriodActions(id){
 		var url = '<c:url value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&adverseEventReportingPeriod=' + id + '&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true"/>';
 		window.location = url; 
 	}

     function showToolTip(text, title) {
         Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
     }
        
    </script>
</head>
<body>
<!-- AE summary  -->
<c:if test="${not empty command.participant}">
  <div>
    
    <div class="row">
      <div class="summarylabel">Study</div>
      <div class="summaryvalue shorty">(${command.study.primaryIdentifier.value}) ${command.study.longTitle}</div>
    </div>
    <div class="row">
      <div class="summarylabel">Subject</div>
      <div class="summaryvalue shorty">(${command.assignment == null ? '' : command.assignment.studySubjectIdentifier })</div>
    </div>
  </div>
</c:if>
    <tags:instructions code="instruction_manage_reports"/>

	<csmauthz:accesscontrol var="_studySitePerson"
					domainObject="${command.assignment.studySite.organization}" 
					authorizationCheckName="siteAuthorizationCheck" 
					hasPrivileges="study_subject_calendar_manager" /> 
	<csmauthz:accesscontrol var="_ccPerson"
					domainObject="${command.assignment.studySite.study.studyCoordinatingCenter.organization}" 
					authorizationCheckName="siteAuthorizationCheck" 
					hasPrivileges="study_subject_calendar_manager" /> 

	<c:if test="${not empty configuration.map.pscBaseUrl}">
		<c:if test="${_studySitePerson || _ccPerson}">
			<p>View this person's schedule in <a href="${configuration.map.pscBaseUrl}/pages/cal/schedule?assignment=${command.assignment.gridId}" class="sso" target="_psc">study calendar</a>.</p>
		</c:if>	 
	</c:if>

<div class="eXtremeTable" >

  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
    <thead>
      <tr align="center" class="label">
        <td width="2%" class="tableHeader"></td>
        <td width="18%" class="tableHeader">Course</td>
        <td width="16%" class="centerTableHeader"># of Reports</td>
        <td width="16%" class="centerTableHeader"># of AEs</td>
        <td width="16%" class="tableHeader">Report Submission Status</td>
        <td width="16%" class="tableHeader">Options</td>
      </tr>
    </thead>
    <c:if test="${fn:length(command.assignment.activeReportingPeriods) gt 0}">
      <c:forEach items="${command.assignment.activeReportingPeriods}" var="reportingPeriod" varStatus="rpStatus">
        <ae:oneListReportingPeriodRow reportingPeriod="${reportingPeriod}" index="${rpStatus.index}"/>
      </c:forEach>
    </c:if>
  </table>
  <c:set var="reportingPeriodPageURLNoPeriod" value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&_target0=0&displayReportingPeriod=true"/>
  <c:if test="${fn:length(command.assignment.activeReportingPeriods) le 0}">
  	<tags:instructions code="instruction_ae_no_courses"/>
  </c:if>
    
</div>
</body>
</html>
