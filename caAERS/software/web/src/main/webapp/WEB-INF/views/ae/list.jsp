<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Manage Reports</title>
<tags:dwrJavascriptLink objects="createAE,adverseEventHistory"/>
<%-- <tags:slider renderComments="false" renderAlerts="true" display="">
    <jsp:attribute name="labs">
    	<div id="labs-id" style="display:none;">
    		<tags:labs labs="${command.assignment.labLoads}"/>
    	</div>
    </jsp:attribute>
</tags:slider> --%>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<style type="text/css">
.notify-unit.success {
	color: #090;
}
.notify-unit.failure {
	color: #900;
}
.eXtremeTable .centerTableHeader {
	background-color: #DFE4E8;
	color: black;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 17px;
	font-weight: bold;
	text-align: center;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/manage-reports-gradient.png);
	background-repeat: repeat-x;
	background-position: bottom;
}
.eXtremeTable .eXtremeTable {
	border:1px solid silver;
	padding:2px;
	background-color:#FFFFFF;
	font-size:12px;
	font-weight:normal;
}
.eXtremeTable .eXtremeTable .even{
	background-color:#FFEFEF;
}
.eXtremeTable .eXtremeTable .eXtremeTable .even{
	background-color:#fff;
}
.eXtremeTable .odd td, .eXtremeTable .even td {
	padding-top: 7px;
	padding-right: 3px;
	padding-bottom: 7px;
	padding-left: 3px;
	vertical-align: middle;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 16px;
	font-weight:bold;
}
.eXtremeTable .eXtremeTable .odd td, .eXtremeTable .eXtremeTable .even td {
	padding-top: 6px;
	padding-right: 3px;
	padding-bottom: 6px;
	padding-left: 3px;
	vertical-align: middle;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 12px;
	font-weight: normal;
	border-top:none;
}
.eXtremeTable .highlight td {
	color: black;
	padding-top: 7px;
	padding-right: 3px;
	padding-bottom: 7px;
	padding-left: 3px;
	vertical-align: middle;
	background-color: #F09B5D;
	font-size: 16px;
	font-weight:bold;
}
.eXtremeTable .eXtremeTable .highlight td {
	color:black;
	padding-top: 6px;
	padding-right: 3px;
	padding-bottom: 6px;
	padding-left: 3px;
	vertical-align: middle;
	background-color:#f09b5d;
	font-size: 12px;
	font-weight: normal;
}
.eXtremeTable .tableHeader {
	background-color: #DFE4E8;
	color: black;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 17px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/manage-reports-gradient.png);
	background-repeat: repeat-x;
	background-position: bottom;
}
.eXtremeTable .eXtremeTable .tableHeader {
	background-color: #2b4186;
	color: white;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 13px;
	font-weight: normal;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
}
.eXtremeTable .eXtremeTable .centerTableHeader {
	background-color: #2b4186;
	color: white;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 13px;
	font-weight: normal;
	text-align: center;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
}
.eXtremeTable .allAEs .tableHeader {
	background-color:#DE5900;
	background-image: url(../../images/blue/eXtable_allAE_header_bg.png);
	background-repeat: repeat-x;
	color:black;
}
.eXtremeTable .allAEs .centerTableHeader {
	background-color:#DE5900;
	background-image: url(../../images/blue/eXtable_allAE_header_bg.png);
	background-repeat: repeat-x;
	color:black;
	text-align:center;
}
.eXtremeTable .odd {
	background-color: #fff;
}
.eXtremeTable .eXtremeTable .odd {
	background-color: #eaeaea;
}
.eXtremeTable a:hover{
color:#0033FF;
}
</style>
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
                        //AE.hideIndicator("notify-indicator-" + aeReportId)
                        var statusColumn = $("status" + reportId)
                        var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";

                        var optionColumn = $("action" + reportId)
                        optionColumnData = $("action" + reportId).innerHTML;

                        Element.update(statusColumn, statusColumnData)
                        Element.update(optionColumn, optionColumnData)
                    }
                });
            } else if (action == 'submit') {
                var url = '<c:url value="/pages/ae/submitReport?from=list" />' + '&aeReport=' + aeReportId + '&reportId=' + reportId;
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
        var select = $('actions-' + reportId);
        
        for (var i = (select.options.length-1); i>=0; i--) {
            var o = select.options[i];
            if ((select.options[i].value == 'submit') || (select.options[i].value == 'withdraw')) {
            	select.options[i] = null;
            }
        }
    }

    function executeAction(reportId, url, aeReportId, submissionUrl){
        var actions = $("actions-rp-" + reportId);
        
    	for ( i=0; i < actions.length; i++) {
            if (actions.options[i].selected && actions.options[i].value != "none") {
            	if(confirm('Are you sure you want to take the action - ' + actions.options[i].text + ' ?')){
	                switch (actions.options[i].value) {
    	                case "notifyPSC": notifyPsc(aeReportId); break;
        	            case "submit": doAction(actions.options[i].value, aeReportId, reportId); break;
            	        case "withdraw": doAction(actions.options[i].value, aeReportId, reportId);  updateDropDownAfterWithdraw(reportId); break;
                	    case "amend": doAction(actions.options[i].value, aeReportId, reportId);  break;
                    	case "adeers": window.open(submissionUrl, "_blank");  break;
                    	default: window.open(url + "&format="+ actions.options[i].value,"_self");
                	}
                }else{
                	return false;
                }
            }
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
      <div class="summaryvalue">(${command.study.primaryIdentifier.value}) ${command.study.longTitle}</div>
    </div>
    <div class="row">
      <div class="summarylabel">Subject</div>
      <div class="summaryvalue">(${command.participant.primaryIdentifier.value}) ${command.participant.fullName}</div>
    </div>
  </div>
</c:if>
    <tags:instructions code="instruction_manage_reports"/>



<c:if test="${not empty configuration.map.pscBaseUrl}">
    <p>View this person's schedule in the <a href="${configuration.map.pscBaseUrl}/pages/cal/schedule?assignment=${command.assignment.gridId}" class="sso" target="psc">study calendar</a>.</p>
</c:if>

<div class="eXtremeTable" >

  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
    <thead>
      <tr align="center" class="label">
        <td width="2%" class="tableHeader"></td>
        <td width="18%" class="tableHeader">Course</td>
        <td width="16%" class="centerTableHeader"># of Reports</td>
        <td width="16%" class="centerTableHeader"># of AEs</td>
        <td width="16%" class="tableHeader">Data Entry Status</td>
        <td width="16%" class="tableHeader">Report Submission Status</td>
        <td width="16%" class="tableHeader">Options</td>
      </tr>
    </thead>
    <c:if test="${fn:length(command.assignment.reportingPeriods) gt 0}">
      <c:forEach items="${command.assignment.reportingPeriods}" var="reportingPeriod" varStatus="rpStatus">
        <ae:oneListReportingPeriodRow reportingPeriod="${reportingPeriod}" index="${rpStatus.index}"/>
      </c:forEach>
    </c:if>
  </table>
  <c:set var="reportingPeriodPageURLNoPeriod" value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&_target0=0&displayReportingPeriod=true"/>
  <c:if test="${fn:length(command.assignment.reportingPeriods) le 0}">
  	<tags:instructions code="instruction_ae_no_courses"/>
  </c:if>
    
</div>
</body>
</html>
