<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<html>
<head>
<tags:stylesheetLink name="extremecomponents"/>
<tags:dwrJavascriptLink objects="captureAE"/>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<tags:includeScriptaculous/>
<tags:includePrototypeWindow />
<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <tags:slider renderComments="true" renderAlerts="false" display="none">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="reportingPeriod"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
<script type="text/javascript">

	Event.observe(window, "load", function() {
	
		//Event.observe('flow-next', 'click', displayOptionsPopup);
		
		$('create-new-report').observe("click", function(){ createNewReport(); });
		
		//check if workflow is enabled
 		if(${command.workflowEnabled}){
 			captureAE.retrieveReportingPeriodReviewComments( 
					function(ajaxOutput){
						document.getElementById('scrollbar_content').innerHTML = "";
						document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
						document.getElementById('enter-comment-text'). value = "";
					}) ;
			document.getElementById('entire-slider').style.display = '';
 		}
		
		
		if($('manualselect2')){
			 
		
      		 Event.observe('manualselect2', "click", function() {
      	 		var answer = confirm('Are you sure you want to bypass the caAERS-based report selection above and instead manually select from the list of all reports defined for this study?');
      	 	 	if(answer){
      	 	 		$('manualselect2').disabled=true
      	 	  	 	$('report-list').hide();
      		   		$('report-list').innerHTML = $('report-list-full').innerHTML;
      		   		$('report-list-full').innerHTML='';
 			   		AE.slideAndShow($('report-list'));  
 			  		// setUpEventObserving();	
      	 	 	}	
      	 	 });
		}

	});
	
		function createNewReport(){
			forwardControl('createNew', '');
		}
		
		function editReport(reportId){
			forwardControl('editReport', reportId);
		}
		
		function amendReport(aeReportId){
			var form = document.getElementById('command');
			form._action.value = 'amendReport';
			form._reportId.value = aeReportId;
			//form._repId.value = reportId;
			form.submit();
		}
		
		function forwardControl(task, reportId){
			var form = document.getElementById('command')
			form._action.value=task;
			form._reportId.value=reportId;
			form.submit();
		}
		
		function enableReportsInPopup(){
			var chkboxElements = $('report-list').select('[type="checkbox"]');
			for(var i=0; i < chkboxElements.length; i++){
				if(chkboxElements[i].checked)
					$(chkboxElements[i].name + '-p').show();
				else
					$(chkboxElements[i].name + '-p').hide();
			}	
		}
		
		function checkIfReportSelected(){
			var reportElements = $('report-list').select('[type="checkbox"]');
			for(var i = 0; i < reportElements.length; i++){
					if(reportElements[i].checked) return true;
			}
			return false;
		}
		
		function checkIfAeSelected(){
			var aeElements = $('div-aes').select('[type="checkbox"]');
			var selected = false;
			for(var i = 0; i < aeElements.length; i++)
				if(aeElements[i].checked)
					selected = true;
			//if(!selected){
			//	alert('At least one adverse event should be selected');
			//}
			return selected;
		}
		
		function displayOptionsPopup(){
			enableReportsInPopup();

			var reportSelected = checkIfReportSelected();
			if(reportSelected){
				$('div-reports-and-create').show();
				$('div-reports-not-selected').hide();
			}else{
				$('div-reports-and-create').hide();
				$('div-reports-not-selected').show();
			}
			
			var aeSelected = checkIfAeSelected();
			if(!aeSelected && reportSelected)
			{
				alert('Reports cannot be selected without selecting an Adverse Event(s)');
				return false;
			}
			var contentWin = new Window({className:"alphacube", 
 	 	 			destroyOnClose:true, 
 	 	 			width:700,  height:530, 
 					top: 30, left: 300});
     		contentWin.setContent( 'display_options_popup' );
      		contentWin.showCenter(true);
      		popupObserver = {
      			onDestroy: function(eventName, win) {
      				if (win == contentWin) {
      					$('display_options_popup').style.display='none';
      					contentWin = null;
      					Windows.removeObserver(this);
      				}
      			}
      		}
      		Windows.addObserver(popupObserver);
	}

		
</script>
<style type="text/css">
.divNotes, .divOtherMeddra {
	font-size:8pt;
	border-style:none;
}
div.row div.summarylabel {
	width: 10em;
	padding-right:0.5em;
}
.reportSet {
	border:2px solid #A7A7A7;
	padding:10px;
	background-color: #b2bbff;
	background-image: url(/caaers/images/blue/report_set_bg.png);
	background-repeat: repeat-x;
	background-position: top;	
}
.eXtremeTable .tableRegion {
border:1px solid silver;
font-family:arial,verdana,helvetica,sans-serif;
font-size:12px;
margin-top:0px;
padding:2px;
background-color:#e5e8ff;
}
</style>
</head>
<body>
<c:if test='${not empty command.adverseEventReportingPeriod and not empty rpdAllTable}'>
  <div class="row">
    <div class="summarylabel">Evaluation Period</div>
    <div class="summaryvalue">
      <tags:formatDate value="${command.adverseEventReportingPeriod.startDate}"/>
      -
      <tags:formatDate value="${command.adverseEventReportingPeriod.endDate}" />
      ; ${command.adverseEventReportingPeriod.epoch.name}</div>
  </div>
</c:if>
<div id="report-list-full" style="display:none; padding-bottom:5px;" align="center">
  <tags:noform>
    <table class="tablecontent">
      <tr>
        <th>Required</th>
        <th>Report</th>
        <th>Status</th>
      </tr>
      <c:forEach items="${rpdAllTable}"  var="rdTable" varStatus="rdStatus">
        <tr>
          <td align="center">${rdTable.value.required ? 'Yes' : 'No' }</td>
          <td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/>
            <tags:renderLabel field="${rdTable.value.field}"/></td>
          <td>${rdTable.value.status}</td>
        </tr>
      </c:forEach>
    </table>
  </tags:noform>
</div>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review" saveButtonLabel="Create Report">
  <jsp:attribute name="instructions">
    <input type="hidden" name="_finish"/>
    <input type="hidden" name="_action" value="">
    <input type="hidden" name="_reportId" value="">
    <input type="hidden" name="_repId" value="">
    <c:set var="reportingPeriodType" value="${command.adverseEventReportingPeriod.epoch.name}" />
    <c:if test="${reportingPeriodType != 'Baseline'}">
      <tags:instructions code="instruction_ae_checkpoint" />
    </c:if>
  </jsp:attribute>
  <jsp:attribute name="singleFields">
      <c:choose>
        <c:when test="${not empty rpdSelectedTable}">
          <p><strong>Reports Identified by caAERS</strong></p>
          <p>
            <tags:message key="instruction_ae_require_reporting" />
          </p>
          <div align="center">
            <div id="report-list" align="center" style="padding-bottom:5px;">
              <!-- required reports -->
              <table class="tablecontent">
                <tr>
                  <th>Required</th>
                  <th>Report</th>
                  <th>Status</th>
                </tr>
                <c:forEach items="${rpdSelectedTable}"  var="rdTable" varStatus="rdStatus">
                  <tr>
                    <td align="center"> ${rdTable.value.required ? 'Yes' : 'No' }</td>
                    <td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/>
                      <tags:renderLabel field="${rdTable.value.field}"/></td>
                    <td>${rdTable.value.status}</td>
                  </tr>
                </c:forEach>
              </table>
            </div>
          </div>
        </c:when>
        <c:otherwise>
          <p>
            <tags:message key="instruction_ae_not_require_reporting"/>
          </p>
          <div align="center" style="padding-bottom:5px;" id="report-list">
            <!-- optional reports -->
            <table class="tablecontent" width="80%">
              <tr>
                <th>Required</th>
                <th>Report</th>
                <th>Status</th>
              </tr>
              <%--<c:forEach items="${rpdAllTable}"  var="rdTable" varStatus="rdStatus">--%>
              <tr>
                <td align="left" colspan="3">No reports required.</td>
              </tr>
              <%--</c:forEach>--%>
            </table>
          </div>
        </c:otherwise>
      </c:choose>
      <c:if test='${displaySeriousTable || displayObservedTable || displaySolicitedTable}'>
	  	<div class="autoclear" align="center" style="padding-top: 10px;">
   			<input type="button" id="manualselect2" value="Manually Select Report(s)"  class="manualSelectBtn"/>
      	</div>
      	<p>Click "Manually Select Reports" above to manually select from the list of all reports available for this study.</p>
      </c:if>
      <div id="div-aes">
        <c:if test='${!displaySeriousTable}'>
          <p>
            <tags:message key="instruction_ae_no_rulesengine_reports" />
          </p>
        </c:if>
        <c:if test='${displaySeriousTable}'>
       		<p>
            	<tags:message key="instruction_ae_rulesengine_reports" />
        	</p>
        </c:if>

        <p><tags:message key="instruction_ae_note" /></p>

        <chrome:division id="div-saes" title="Adverse Event(s) Requiring Reporting" collapsable="true" >
          <c:if test='${command.adverseEventReportingPeriod != null && displaySeriousTable}'>
            <table id="seriousTable" width="100%" class="tablecontent">
              <tr>
                <th scope="col" align="left"><b>Select</b></th>
                <th scope="col" align="left" width="30%"><b>Term</b> </th>
                <th scope="col" align="left"><b>Grade</b> </th>
                <th scope="col" align="left"><b>Attribution</b> </th>
                <th scope="col" align="left"><b>Hospitalization</b> </th>
                <th scope="col" align="left"><b>Expected</b> </th>
                <caaers:renderFilter elementID="adverseEvents[].serious">
                  <th scope="col" align="left"><b>Serious</b> </th>
                </caaers:renderFilter>
                <%-- <th scope="col" align="left"><b>Is primary?</b></th> --%>
              </tr>
              <tr id="seriousBlankRow" />
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${ae.requiresReporting}">
                  <ae:oneSaeRow index="${status.index}" isSolicitedAE="${ae.solicited}" isAETermOtherSpecify="false" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false"/>
                </c:if>
              </c:forEach>
            </table>
          </c:if>
          <c:if test='${!displaySeriousTable}'>
            <tags:message key="instruction_ae_saes_na" />
          </c:if>
        </chrome:division>
        <chrome:division id="div-oaes" title="Observed Adverse Event(s)"  collapsable="true">
          <c:if test='${command.adverseEventReportingPeriod != null && displayObservedTable}'>
            <table id="observedTable" width="100%" class="tablecontent">
              <tr>
                <th scope="col" align="left"><b>Select</b></th>
                <th scope="col" align="left" width="30%"><b>
                  Term</b> </th>
                <th scope="col" align="left"><b>
                  Grade</b> </th>
                <th scope="col" align="left"><b>Attribution</b> </th>
                <th scope="col" align="left"><b>Hospitalization</b> </th>
                <th scope="col" align="left"><b>Expected</b> </th>
                <caaers:renderFilter elementID="adverseEvents[].serious">
                  <th scope="col" align="left"><b>Serious</b> </th>
                </caaers:renderFilter>
                <%-- <th scope="col" align="left"><b>Is primary?</b></th> --%>
              </tr>
              <tr id="observedBlankRow" />
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${(not ae.solicited) and (not ae.requiresReporting)}">
                  <ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="false" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false"/>
                </c:if>
              </c:forEach>
            </table>
          </c:if>
          <c:if test='${!displayObservedTable}'>
            <tags:message key="instruction_ae_oaes_na" />
          </c:if>
        </chrome:division>
        <chrome:division title="Solicited Adverse Event(s)" id="div-soaes"  collapsable="true">
          <c:if test='${command.adverseEventReportingPeriod != null && displaySolicitedTable}'>
            <table id="solicitedTable" width="100%" class="tablecontent">
              <tr>
                <th scope="col" align="left"><b>Select</b></th>
                <th scope="col" align="left" width="30%"><b>Term</b> </th>
                <th scope="col" align="left"><b>Grade</b> </th>
                <th scope="col" align="left"><b>Attribution</b> </th>
                <th scope="col" align="left"><b>Hospitalization</b> </th>
                <th scope="col" align="left"><b>Expected</b> </th>
                <caaers:renderFilter elementID="adverseEvents[].serious">
                  <th scope="col" align="left"><b>Serious</b> </th>
                </caaers:renderFilter>
                <%-- <th scope="col" align="left"><b>Is primary?</b></th> --%>
              </tr>
              <tr id="solicitedBlankRow" />
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${(ae.solicited) and (not ae.requiresReporting)}">
                  <ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="false" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false"/>
                </c:if>
              </c:forEach>
            </table>
          </c:if>
          <c:if test='${!displaySolicitedTable}'>
            <tags:message key="instruction_ae_soaes_na" />
          </c:if>
        </chrome:division>
      </div>
    
  </jsp:attribute>
  <jsp:attribute name="tabControls">
    <div class="content buttons autoclear">
      <div class="flow-buttons"> <span class="prev">
        <input type="submit" value="« Back" class="tab1" id="flow-prev"/>
        </span> <span class="next">
        <a href="javascript:displayOptionsPopup()">
        	<img src="<chrome:imageUrl name="../blue/continue_btn.png" />"  alt="Continue" title="Continue" style="border:0" />
        </a>
        </span> </div>
    </div>
  </jsp:attribute>
</tags:tabForm>
<div id="display_options_popup" style="display:none;text-align:left" >
  <chrome:box title="Create New / Edit / Amend Existing Report" id="popupId">
    <c:if test="${not empty command.participant}">
      <div align="left">
        <div class="row">
          <div class="summarylabel">Subject</div>
          <div class="summaryvalue">${command.participant.fullName}</div>
        </div>
        <div class="row">
          <div class="summarylabel">Study</div>
          <div class="summaryvalue">${command.study.longTitle}</div>
        </div>
        <div class="row">
          <div class="summarylabel">Evaluation period</div>
          <div class="summaryvalue">${command.adverseEventReportingPeriod.name}</div>
        </div>
      </div>
    </c:if>
    <div id="div-reports-and-create" style="display:none;">
      <hr/>
      <p>
        <tags:instructions code="instruction_ae_adverseevents"/>
      </p>
      <chrome:division title="Selected Reports" id="div-selected-reports" collapsable="false">
        <table class="reportSet" width="100%">
         <tr>
          <td width="10%" align="left">
          <input type="button" value="Create" id="create-new-report"/>
          </td>
        <td>
        <div class="eXtremeTable">
          <table width="100%" border="0" cellspacing="0"  class="tableRegion">
            <thead>
              <tr align="center" class="label">
                <td class="tableHeader">Report</td>
                <td class="tableHeader">Status</td>
              </tr>
            </thead>
            <c:forEach items="${command.allReportDefinitions}"  var="repDefn" varStatus="rdStatus">
              <tr id="reportDefinitionMap[${repDefn.id}]-p" style="display:none">
                <td align="left">${repDefn.label}</td>
                <td align="left">${command.reportStatusMap[repDefn.id]}</td>
              </tr>
            </c:forEach>
          </table>
        </div>
         </tr>
        </table>
      </chrome:division>
    </div>
    <%-- The below div is only visible if there are no reports selected --%>
    <div id="div-reports-not-selected">
      <chrome:division title="Selected Reports" id="div-selected-reports-2" collapsable="false">
        <div style="text-align: left;">
          <div class="eXtremeTable">
            <table width="60%" border="0" cellspacing="0" class="tableRegion">
              <thead>
                <tr align="center" class="label">
                  <td class="tableHeader">Report</td>
                  <td class="tableHeader">Status</td>
                </tr>
              </thead>
              <tr>
                <td><tags:message key="instruction_ae_no_reports" />
                </td>
              </tr>
            </table>
          </div>
        </div>
      </chrome:division>
      <div style="margin-left:10px; margin-bottom:20px;"><tags:message key="instruction_ae_no_saes"  /></div>
    </div>
    <chrome:division title="Edit/Amend Existing Report" id="div-report-summary" collapsable="false">
      <%-- <div class="eXtremeTable" > --%>
      <c:choose>
        <c:when test="${fn:length(command.adverseEventReportingPeriod.aeReports) gt 0}">
          <c:forEach items="${command.adverseEventReportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
            <table width="100%" border="0" cellspacing="0" class="reportSet" style="margin-bottom:30px;">
              <tr>
	                <td width="10%" align="left">
	                	<c:if test="${aeReport.allSponsorReportsCompleted == true and aeReport.hasAmendableReport == true}">
   	                 		<input type="button" value="Amend" id="amend-report" onClick="javascript:amendReport('${aeReport.id}');"/>
                  		</c:if>
                  		<c:if test="${aeReport.allSponsorReportsCompleted == false}">
                    		<input type="button" value="Edit" id="edit-report" onClick="javascript:editReport('${aeReport.id}');"/>
                  		</c:if>
                	</td>
                <td><div class="eXtremeTable" >
                    <table width="100%" border="0" cellspacing="0" class="tableRegion">
                      <thead>
                        <tr align="center" class="label">
                          <td width="5%"/>
                          <td class="tableHeader" width="15%">Report Type</td>
                          <td class="centerTableHeader" width="10%">Report Version</td>
                          <td class="centerTableHeader" width="10%"># of AEs</td>
                          <td class="tableHeader" width="20%">Data Entry Status</td>
                          <td class="tableHeader" width="20%">Submission Status</td>
                        </tr>
                      </thead>
                      <ae:oneReviewExpeditedReportRow aeReport="${aeReport}" index="${statusAeReport.index}" />
                    </table>
                  </div></td>
              </tr>
            </table>
            </table>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <table width="100%" border="0" cellspacing="0" class="tableRegion">
            No reports are available to edit or amend in this evaluation period.
          </table>
        </c:otherwise>
      </c:choose>
      <%-- </div> --%>
    </chrome:division>
  </chrome:box>
</div>
</body>
</html>
