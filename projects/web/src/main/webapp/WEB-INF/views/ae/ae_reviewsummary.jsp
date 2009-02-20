<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <tags:stylesheetLink name="extremecomponents"/>
    <tags:dwrJavascriptLink objects="captureAE"/>
    <link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>${tab.longTitle}</title>
    <tags:includeScriptaculous/>
    <tags:includePrototypeWindow />
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <style>.link {text-decoration:underline; color:blue;}</style>
<script type="text/javascript">

	Event.observe(window, "load", function() {
	
		
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


		//The below function will help showing the selected reports
	     enableReportsInPopup();
	});

		function selectReport(task, reportId){
			var form = document.getElementById('command')
			form._action.value=task;
			form._reportId.value=reportId;
		}
	
		
		function forwardControl(){
			var form = document.getElementById('command')
			
			if(form._action.value == ''){
				alert('Please choose the report to Edit, Amend or Create');
				return false;
			}

			var reportSelected = checkIfReportSelected();
			var aeSelected = checkIfAeSelected();
			var reportableAEs =  hasReportableAEs();

			if(form._action.value == 'createNew'){
				if(!aeSelected){
					alert("Please select at least one adverse event.");
					return false;
				}
				if(!reportSelected){
					alert("Please select the kind of report that needs to be created, from 'Reports Identified by caAERS' section." );
					return false;
				}
			}

			if(form._action.value == 'amendReport'){
				if(!aeSelected && reportableAEs){
					var usrDecision = confirm("You have opted to Amend an existing report, but no adverse event is selected, Do you want to continue ?");
					if(!usrDecision) return false;
				}
			}
			if(form._action.value == 'editReport'){
				if(!aeSelected && reportableAEs){
					var usrDecision = confirm("You have opted to Edit an existing report, but no adverse event is selected, Do you want to continue ?");
					if(!usrDecision) return false;
				}
			}
			
			
			form.submit();
		}
		
		function enableReportsInPopup(){
		
			if(hasReportableAEs()){
				if(checkIfReportSelected() == false){
					$('create-new-report-table').style.display = 'none';
					if(${fn:length(command.adverseEventReportingPeriod.aeReports) gt 0})
						$('box-existing-reports').style.display = '';
					else
						$('box-existing-reports').style.display = 'none';
				}
				else{
					$('create-new-report-table').style.display = '';
					$('box-existing-reports').style.display = '';
				}
			}
							
			var chkboxElements = $('report-list').select('[type="checkbox"]');
			for(var i=0; i < chkboxElements.length; i++){
				if(chkboxElements[i].checked){
					$(chkboxElements[i].name + '-p').show();
				}else{
					$(chkboxElements[i].name + '-p').hide();
				}
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

		function hasReportableAEs(){
			var aeElements = $('div-aes').select('[type="checkbox"]');
			return aeElements.length > 0;
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
          <td align="left">
          <ui:checkbox path="${rdTable.value.field.propertyName}" cssClass="rpdChk" onclick="javascript:enableReportsInPopup();"></ui:checkbox>
            <tags:renderLabel field="${rdTable.value.field}"/></td>
          <td>${rdTable.value.status}</td>
        </tr>
      </c:forEach>
    </table>
  </tags:noform>
</div>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review" saveButtonLabel="Create Report" hideBox="true">
  <jsp:attribute name="singleFields">
  
	    <c:set var="aeReportsLength" value="${fn:length(command.adverseEventReportingPeriod.aeReports)}" />
	    <input type="hidden" name="_finish"/>
	    <input type="hidden" name="_action" value="${aeReportsLength gt 0 ? '' : 'createNew' }">
	    <input type="hidden" name="_reportId" value="">
	    <input type="hidden" name="_repId" value="">
   
      <c:choose>
        <c:when test="${not empty rpdSelectedTable}">
         
        <chrome:box id="box-report-by-caaers" title="Reports Identified by caAERS" collapsable="true" autopad="true">
    <div style="border:1px solid #f00; height:100px; padding:9px; margin-bottom:10px;">
		<img src="<chrome:imageUrl name="stop_sign.png" />" alt="Stop!" style="float:left; margin-right:30px; margin-left:80px;" />
		<div style="font-size:20px; margin-bottom:5px;">Report Recommended!</div>
		<div><tags:message key="instruction_ae_require_reporting" /></div>
	</div>
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
                    <td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk" disabled="true"/>
                      <tags:renderLabel field="${rdTable.value.field}"/></td>
                    <td>${rdTable.value.status}</td>
                  </tr>
                </c:forEach>
              </table>
            </div>
          </div>
          <c:if test='${displayReportableAeTable}'>
              <%--<tags:instructions code="instruction_ae_require_reporting" />--%>
              <p>Click <a id="manualselect2" style='cursor:pointer' class="link">here</a> to manually select from the list of all reports available for this study.</p>
      	  </c:if>
        </chrome:box>
        </c:when>
        <c:otherwise>
         <chrome:box id="box-report-by-caaers" title="Reports Identified by caAERS" collapsable="true" autopad="true">

             <tags:instructions code="instruction_ae_not_require_reporting" />
             
          <div align="center" style="padding-bottom:5px;" id="report-list">
            <!-- optional reports -->
            <table class="tablecontent" width="80%">
              <tr>
                <th>Required</th>
                <th>Report</th>
                <th>Status</th>
              </tr>
              <tr>
                <td align="left" colspan="3">No reports required.</td>
              </tr>
            </table>
          </div>
             <%--<tags:instructions code="instruction_ae_require_reporting" />--%>
             <c:if test="${displayReportableAeTable}">
             <p>Click <a id="manualselect2" style='cursor:pointer' class="link">here</a> to manually select from the list of all reports available for this study.</p>
             </c:if>
            </chrome:box>
        </c:otherwise>
      </c:choose>
       
        <chrome:box id="box-existing-reports" title="Select Reporting Method" collapsable="true" autopad="true" style="display: ${aeReportsLength gt 0 ? '' : 'none'}">
            <tags:instructions code="instruction_ae_existing_reports" />

       	 
        	<c:if test="${aeReportsLength gt 0}">
          	<c:forEach items="${command.adverseEventReportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
          	<table width="100%" border="0" cellspacing="0" class="reportSet" style="margin-bottom:30px;">
             <tr id="existing-reports-row" class="${ ((statusAeReport.index % 2 ) gt 0 )? 'odd' : 'even'  }">
               <td width="5%" align="left">
               	<c:if test="${aeReport.allSponsorReportsCompleted == true and aeReport.hasAmendableReport == true}">
 	                 		<input type="radio" value="Amend" name="report-radio"  onClick="javascript:selectReport('amendReport','${aeReport.id}');"/>&nbsp;Amend
                </c:if>
                <c:if test="${aeReport.allSponsorReportsCompleted == false}">
                  		<input type="radio" value="Edit"  name="report-radio" onClick="javascript:selectReport('editReport','${aeReport.id}');"/>&nbsp;Edit
                </c:if>
              	</td>
              	<td width="95%"><div class="eXtremeTable" >
                  <table width="100%" border="0" cellspacing="0" class="tableRegion">
                    <thead>
                      <tr align="center" class="label">
                        <td width="5%"/>
                        <td class="tableHeader" width="15%">Report Type</td>
                        <td class="centerTableHeader" width="10%">Amendment #</td>
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
          	</c:forEach>
      	 	</c:if>
      	 	
      	 	
      	 	<%-- Only shown if there are reportable adverse events--%>
      	 	<c:if test="${displayReportableAeTable}">
      	 	<table width="100%" border="0" cellspacing="0" class="reportSet" style="margin-bottom:30px;" id="create-new-report-table">
      	 	<tr id="create-new-report-row" class="${aeReportsLength gt 0 ? 'even' : 'odd' }">
      	 		<td width="10%" align="left">
          			<input type="radio" value="New"  name="report-radio" onClick="javascript:selectReport('createNew','');"/>&nbsp;Create
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
        		</td>
      	 	</tr>
      	 	</table>
      	 	</c:if>
      	 	
      	         
        </chrome:box>
      <div id="div-aes">
       
		
       <chrome:box id="box-aes" title="Select Adverse Events To Report" collapsable="true" autopad="true">
       	
       	<c:if test="${!displayReportableAeTable}">
	        <p><tags:message key="instruction_ae_no_rulesengine_reports" /></p>
        </c:if>
         <c:if test="${displayReportableAeTable}">
         <p><tags:instructions code="instruction_ae_rulesengine_reports" /></p>
         <table id="seriousTable" width="100%" class="tablecontent">
              <tr>
                <th scope="col" align="left"><b>Select</b></th>
                <th scope="col" align="left"><b>Requires reporting ?</b></th>
                <th scope="col" align="left" width="30%"><b>Term</b> </th>
                <th scope="col" align="left"><b>Grade</b> </th>
                <th scope="col" align="left"><b>Attribution</b> </th>
                <th scope="col" align="left"><b>Hospitalization</b> </th>
                <th scope="col" align="left"><b>Expected</b> </th>
                <caaers:renderFilter elementID="adverseEvents[].serious">
                  <th scope="col" align="left"><b>Serious</b> </th>
                </caaers:renderFilter>
              </tr>
              
              
        <!--  begin serious aes -->
          <c:if test='${command.adverseEventReportingPeriod != null && displaySeriousTable}'>
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${ae.requiresReporting}">
                  <ae:oneSaeRow index="${status.index}" isSolicitedAE="${ae.solicited}" isAETermOtherSpecify="false" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false" showRequiresReporting="true"/>
                </c:if>
              </c:forEach>
          </c:if>
         <!--  end serious aes --> 
         <!--  begin observed aes -->  
          <c:if test='${command.adverseEventReportingPeriod != null && displayObservedTable}'>
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${(not ae.solicited) and (not ae.requiresReporting)}">
                  <ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="false" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false" showRequiresReporting="true"/>
                </c:if>
              </c:forEach>
          </c:if>
        <!--  end observed aes -->
        
        <!--  begin solicied aes -->
        <c:if test="${command.havingSolicitedAEs}">
          <c:if test='${command.adverseEventReportingPeriod != null && displaySolicitedTable}'>
              <c:forEach items="${command.adverseEventReportingPeriod.reportableAdverseEvents}" varStatus="status" var="ae">
                <c:if test="${(ae.solicited) and (not ae.requiresReporting)}">
                  <ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="false" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true" renderNotes="false" renderSubmittedFlag="false" showRequiresReporting="true"/>
                </c:if>
              </c:forEach>
          </c:if>
        </c:if>
        <!--  end solicited aes -->
        </table>
       </c:if>
       </chrome:box>
       
      </div>
    
  </jsp:attribute>
  <jsp:attribute name="tabControls">
      <div class="content buttons autoclear">
          <div class="flow-buttons">
              <span class="prev">
              	<tags:button type="submit" value="Back" cssClass="tab1" color="blue" icon="back" id="flow-prev"/>
			  </span>
			  <c:if test="${aeReportsLength gt 0 or displayReportableAeTable}">
				  <span class="next">
				  	<tags:button type="button" onclick="forwardControl();" value="Continue Expedited Reporting" color="green" icon="continue" />
				  </span>
			  </c:if>
          </div>
      </div>
  </jsp:attribute>
  </tags:tabForm>


</body>
</html>
