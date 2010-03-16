<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>AE Report Mandatory Fields</title>

<style type="text/css">
   div.row div.label { width: 13em; } 
   div.row div.value { margin-left: 14em; }
   .half {
   		float:left;
		width:46%;
		margin:0 10px;
   }
   .hidden{
       display:none;
   }
</style>  
<link rel="shortcut icon" href="../../../images/caaers.ico" type="image/x-icon"/>

<script type="text/javascript">

//push all rulenames into an array.
AE.ALL_FIELD_RULES = new Array();
<tags:noform>
 <c:if test="${command.fieldRulesAvailable}">
  <c:forEach var="rule" items="${command.ruleSet.rule}">
   AE.ALL_FIELD_RULES.push('${rule.metaData.name}');
  </c:forEach>
 </c:if>
</tags:noform>

//helps in showing the rules, and selecting them.    
function showRulePicker(fldSelectPath, fldRuleBindURLPath, fldRuleNamePath){

    AE.FLD_SELECT =  fldSelectPath;
    AE.FLD_BIND_URL = fldRuleBindURLPath;
    AE.FLD_RULE_NAME = fldRuleNamePath;

    //clear off the selected fields
    $$('.selected-rules-value').each(function(adiv){
        adiv.innerHTML = "";
    });

    AE.ALL_FIELD_RULES.each(function(v){
        $$('.chk_' + v).each(function(_cb){
            _cb.checked = false;
        });
    });


    //show the window.
    var alertPopup =  Dialog.alert($('rules_popup').innerHTML, {id:'winRulePicker' , className:"alphacube", width:800, height:400, okLabel: "close" ,
        title: "Pick and choose rules", closable:true, buttonClass:"hidden",
        onOK:function(win){
           var rNames = $(AE.FLD_RULE_NAME).value;
           if(rNames) {
               $(AE.FLD_SELECT).removeClassName('required');
           }else{
              $(AE.FLD_SELECT).addClassName('required');
           }
        }
    });


    var ruleNames = $(fldRuleNamePath).value;
    $('winRulePicker').select('.selected-rules-value')[0].innerHTML= ruleNames;
    if(ruleNames){
       var ruleNameArray = ruleNames.split(',');
       $A(ruleNameArray).each(function(v){
           $('winRulePicker').select('.chk_' + v)[0].checked=true;
       });
    }
    
}


//invoked when a rule is selected
function ruleSelected(chkBox, bindUrl, ruleName){
    var selectedRuleNames = $(AE.FLD_RULE_NAME).value;
    var newRuleNames = "";
    if(chkBox.checked){
         if(!contains(selectedRuleNames, ruleName)){
             if(selectedRuleNames.length > 0){
                selectedRuleNames = selectedRuleNames + ',';
             }
             selectedRuleNames = selectedRuleNames + ruleName;
         }
    }else{
        $A(selectedRuleNames.split(',')).each(function(v){
            if(v == ruleName) return;
            if(newRuleNames.length > 0) newRuleNames = newRuleNames + ',';
            newRuleNames = newRuleNames + v;
        });
        selectedRuleNames = newRuleNames;
    }
    $(AE.FLD_RULE_NAME).value =   selectedRuleNames;
    $(AE.FLD_BIND_URL).value = bindUrl;
    
    $('winRulePicker').select('.selected-rules-value')[0].innerHTML= selectedRuleNames;
    if(selectedRuleNames.length > 0){
        $(AE.FLD_SELECT).removeClassName('required');
    }else{
        $(AE.FLD_SELECT).addClassName('required');
    }

}

//checks if the comma-seperated string 's' contains 'v'.
function contains(s, v){
    if(s == '') return false;
    
    var arr = s.split(',');
    for(var i =0; i< arr.length; i++){
      if(arr[i] == v) return true;
    }
    return false;
}

//invokde when in a mandatory dropdown is changed.
function selectFieldChanged(fldSelectPath, fldRuleBindURLPath, fldRuleNamePath){
  var fldSelect = $(fldSelectPath);
  var aDiv = $(fldSelectPath + '-adiv')
  var a = $(fldSelectPath + '-a')

  $(fldRuleBindURLPath).clear();
  $(fldRuleNamePath).clear();
    
  if(fldSelect.value == 'RULE'){
     fldSelect.addClassName('required');
     a.innerHTML ='Pick rules';
     aDiv.show();
  }else {
    fldSelect.removeClassName('required');
      a.innerHTML ='Show rules';
      aDiv.hide();
  }

}
</script>

</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" hideBox="true">
    	<jsp:attribute name="header">
    		<div class="summary">
    			<caaers:message code="header.reportdefinition.mandatoryfields" arguments="${command.reportDefinition.name},${command.reportDefinition.organization.name}" />
    		</div>
		</jsp:attribute>
    	<jsp:attribute name="instructions">
    	 <tags:instructions code="mandatoryfields" />
    	</jsp:attribute>
		<jsp:attribute name="repeatingFields">
        <caaers:message code="LBL_captureAdverseEvents.heading.adverseEvents" var="_title" />
		<chrome:box title="${_title}">
			<rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION~Adverse events" />
		</chrome:box>
		<chrome:box title="Course & Agent">
				<div class="half">
                    <caaers:message code="LBL_aeReport.treatmentInformation.heading" var="_title" />
			   		<chrome:division title="${_title}"  cssClass="paired" >
					 <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION" />
			   		</chrome:division>
				</div>
				<div class="half">
                    <caaers:message code="LBL_aeReport.studyInterventions.agents.heading" var="_title" />
                    <chrome:division title="${_title}" cssClass="paired">
                        <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION~Study Agents" />
                         <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION" />
                    </chrome:division>
				</div>
				<br style="clear:both;"/>
			</chrome:box>
		<chrome:box title="Reporter">
				<div class="half">
                    <caaers:message code="LBL_aeReport.reporter.heading" var="_title"/>
			   		<chrome:division title="${_title}" >
					 <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details" />
					<rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details~Address" />
			   		</chrome:division>
				</div>
				<div class="half">
                    <caaers:message code="LBL_aeReport.physician.heading" var="_title"/>
			   		<chrome:division title="${_title}">
			    	  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details" />
					  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details~Address" />
					</chrome:division>
				</div>
			<br style="clear:both;"/>
		</chrome:box>
        <caaers:message code="LBL_aeReport.responseDescription.heading" var="_title" />
		<chrome:box title="${_title}">
		   <div class="half">
					 <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="0" endIndex="5" />
				</div>
			 	  <div class="half">
			    	  <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="6"/>
				</div>
<br style="clear:both;"/>
		</chrome:box>
            
        <caaers:message code="LBL_aeReport.participantHistory.subjectDetails.heading" var="_title" />
		<chrome:box title="${_title}">
		   
			<div class="half">
			  	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION" />

                  <caaers:message var="x" code="LBL_aeReport.diseaseHistory.diagnosisDate" />
			  	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~${x}" heading="${x}" tabular="true"/>
			</div>
			<div class="half">
		    	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Weight" heading="Weight" tabular="true" />
  	    	      <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Height" heading="Height" tabular="true" />
			</div>
			<br style="clear:both;"/>
			 <div class="half">
                    <caaers:message code="LBL_aeReport.saeReportPreExistingConditions.heading" var="_title" />
					<chrome:division title="${_title}">
						<rd:renderMandatoryFields key="PRE_EXISTING_CONDITION_SECTION~Pre-existing conditions" />
					</chrome:division>
					</div>
				<div class="half">
                    <caaers:message code="LBL_aeReport.diseaseHistory.metastaticDiseaseSites.heading" var="_title" />
					<chrome:division title="${_title}">
						<rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Metastatic disease sites" />
				  	</chrome:division>
					</div>
					<br style="clear:both;"/>
		            <div class="half">
                    <caaers:message code="LBL_aeReport.saeReportPriorTherapies.heading" var="_title"/>
					<chrome:division title="${_title}">

						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys" />

                        <caaers:message var="x" code="LBL_aeReport.saeReportPriorTherapies.startDate" />
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~${x}" heading="${x}" tabular="true"/>

                        <caaers:message var="x" code="LBL_aeReport.saeReportPriorTherapies.endDate" />
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~${x}" heading="${x}" tabular="true" />
                        
					</chrome:division>
					</div>
				<div class="half">
                    <caaers:message code="LBL_aeReport.concomitantMedications.heading" var="_title" />
					<chrome:division title="${_title}">
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications" />

                        <caaers:message var="x" code="LBL_aeReport.concomitantMedications.startDate" />
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~${x}" heading="${x}" tabular="true" />

                        <caaers:message var="x" code="LBL_aeReport.concomitantMedications.endDate" />
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~${x}" heading="${x}" tabular="true"/>
					</chrome:division>
</div>
<br style="clear:both;"/>
		</chrome:box>
		
		
		<caaers:message code="LBL_aeReport.otherCauses.heading" var="_title" />
		<chrome:box title="${_title}">
			<rd:renderMandatoryFields key="OTHER_CAUSE_SECTION~OtherCausess" />
		</chrome:box>
        <caaers:message code="LBL_aeReport.studyInterventions.heading" var="_title"/>
		<chrome:box title="${_title}">
			<div class="half">
                    <caaers:message code="LBL_aeReport.radiationInterventions.heading" var="_title"/>
			   		<chrome:division title="${_title}">
					 <rd:renderMandatoryFields key="RADIATION_INTERVENTION_SECTION~RadiationInterventions" />
			   		</chrome:division>
					</div>
			 	  <div class="half">
                       <caaers:message code="LBL_aeReport.surgeryInterventions.heading" var="_title" />
			   		<chrome:division title="${_title}">
			    	  <rd:renderMandatoryFields key="SURGERY_INTERVENTION_SECTION~SurgeryInterventions" />
			   		</chrome:division>
			 	</div>
			<br style="clear:both;"/>
		</chrome:box>
		<caaers:message code="LBL_aeReport.medicalDevices.heading" var="_title" />
		<chrome:box title="${_title}">
		<div class="half">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="0" endIndex="10"/>
					 </div>
			 	  <div class="half">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="11" />
			 	  </div>
				  <br style="clear:both;"/>
		</chrome:box>

        <caaers:message code="LBL_aeReport.labs.heading" var="_title" />
		<chrome:box title="${_title}">
		<div class="half">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs" />
					</div>
			 	  <div class="half">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Baseline" heading="Baseline" tabular="true"/>
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Worst" heading="Worst" tabular="true" />
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Recovery" heading="Recovery" tabular="true" />
			 	  </div>
				  <br style="clear:both;"/>
		</chrome:box>
        <caaers:message code="LBL_aeReport.additionalInformation.heading" var="_title" />
		<chrome:box title="${_title}">
			<rd:renderMandatoryFields key="ADDITIONAL_INFO_SECTION" />
		</chrome:box>

        <div id="rules_popup" style="display:none;">
            <div class="rules_popup_content eXtremeTable">
            <c:if test="${command.fieldRulesAvailable}">
                <div class="row selected-rules-row">
                    <div class="label">Selected Rules</div>
                    <div class="value selected-rules-value"></div>
                </div>
                <div style="align:left;">
                    Pick and choose the rules from below
                    <br />
                    <hr />
                </div>
                <c:forEach var="rule" items="${command.ruleSet.rule}" varStatus="ruleStatus">
                  <div class="row ${(ruleStatus.index %2) gt 0 ? 'odd' : 'even' } ">
                      <div class="label">
                          <input type="checkbox" value="${rule.metaData.name}" class="chk_${rule.metaData.name}"
                                 onclick="ruleSelected(this,'${command.ruleSet.name}' , '${rule.metaData.name}');"/>
                          ${rule.metaData.name}
                      </div>
                      <div class="value">
                          
                          <c:forEach var="line" items="${rule.readableRule.line}">
                              ${line} <br />
                         </c:forEach>
                         Then <br />
                          <c:forEach var="_act" items="${rule.readableAction}">
                              &nbsp;&nbsp;&nbsp; ${_act}
                          </c:forEach>
                      </div>
                  </div>
                </c:forEach>

            </c:if>
            <c:if test="${not command.fieldRulesAvailable}">
                 <div class="row">
                     <div class="value">No field level rules available</div>
                 </div>
            </c:if>
            </div>
        </div>
		</jsp:attribute>
	</tags:tabForm> 
</body>


</html>
