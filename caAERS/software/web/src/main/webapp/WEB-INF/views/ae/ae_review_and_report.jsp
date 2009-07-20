<%@include file="/WEB-INF/views/taglibs.jsp"%>
<html>
 <head>
 
 <style type="text/css">

  .withdraw{
	color:gray;  
  }
  
  .amend{
  	color:gray; 
  }
  
  .retired{
  	color:gray; 
  }
  
  td.withdraw{
	color:gray;  
  }
  
  td.amend{
  	color:gray; 
  }
  
  td.retired{
  	color:gray; 
  }
  
  div.serious-aes{
  	padding-top: 0.3em;
  	padding-bottom: 0.8em;
  }
  div.recommended-reports{
  	padding-top: 0.2em;
  	padding-bottom: 1.3em;
  }
  div.applicable-reports{
  	padding-top: 0.2em;
  	padding-bottom: 1.3em;
  }
  	 
 </style>
 
 <script type="text/javascript">
 
 //to store the recommended options (aeReportId - {reportDefinitionId} 
 AE.recommendedOptions = new Hash();
 
 //to store group report definitions (aeReportId -{GroupId - {reportDefinitionId}}
 AE.groupDefinitions = new Hash();
 
 //to store all the report definitionIds
 AE.reportDefinitions = new Array();
 
 //to  reportDefinitionId - groupId
 AE.reportDefinitionGroupHash = new Hash();

 //to store the reportDefinitionId - ReportDefinition object.
 AE.reportDefinitionHash = new Hash();

 var manualSelectMessage='<spring:message code="instruction_ae_override_confirmation" text="Not found key: instruction_ae_override_confirmation " />';
 var allCssClassNames = ['create', 'edit', 'withdraw', 'amend'];

 var theAccordion;
 
 //create an object to hold report definition details.
 var jsReportDefinition = Class.create();
 Object.extend(jsReportDefinition.prototype, {
   initialize: function(id, name, group ) {
	   this.id = id;
	   this.name = name;
	   this.group = group;
   }
 });

 //=================================================================================
 //will show manually select, only applicable when there is no rules recomendation.
 function showManualSelectOptions(applicableSectionId, aeReportId){
	 var answer = confirm(manualSelectMessage);
	 if(!answer) return;
	 $(applicableSectionId).appear();
 }
 
 //=================================================================================
 //will show manually select display and hide recommended DIV
 function overrideRecommendedActions(recommendedSectionId, applicableSectionId, aeReportId){
	 var answer = confirm(manualSelectMessage);
	 if(!answer) return;
	 
	 selectRecommendedReports(aeReportId);
	 updateDisplayTexts(aeReportId);
	 
	 $(recommendedSectionId).fade();
	 $(applicableSectionId).appear();
	
	 $('dc-' + aeReportId + '-override').hide();
	 $('dc-' + aeReportId + '-restore').show();
	 
 }
 //=================================================================================
 //will show recommended options and will hide manually select DIV
 function restoreRecommended(recommendedSectionId, applicableSectionId, aeReportId){
	selectRecommendedReports(aeReportId);
	updateDisplayTexts(aeReportId);
	
	$(applicableSectionId).fade();
	$(recommendedSectionId).appear();

	$$(".manual-indicator-"+aeReportId).each(function(el){
		el.value=0;
	}); 
	
	$('dc-' + aeReportId + '-override').show();
	$('dc-' + aeReportId + '-restore').hide();
 }
 //=================================================================================
 //function will check the recommended items
 function selectRecommendedReports(aeReportId){
	 
	 var sectionId = "applicable-reports-dc-" + aeReportId;
	//uncheck all select boxes
	 $$(".chk_"+aeReportId).each(function(el){
		 el.checked=false;
	  });
	  
	 //check the recommended ones
	 AE.recommendedOptions.get(aeReportId).each(function(rdId){
		 $("rd_"+aeReportId+"_"+rdId).checked = true;
	 });

 }
 //=================================================================================
 //function will deselect other items selected in the same group.
 function deSelectItemsOfSameGroup(aeReportId, rdId){
	 $('rd_' + aeReportId + '_' + rdId +'_manual').value=1;//shows that this is manually selected
	 var groupName = AE.reportDefinitionGroupHash.get(rdId);
	 var grpMap = AE.groupDefinitions.get(aeReportId);
	 if(grpMap){
		 var grpRdArray = grpMap.get(groupName);
		 grpRdArray.each(function(rd){
			 if(rd != rdId){
				 $("rd_"+aeReportId+"_"+rd).checked = false;
				 $('rd_' + aeReportId + '_' + rd +'_manual').value=0;
			 }
		 })
	 }
	 updateDisplayTexts(aeReportId);
 }

//=================================================================================
 //function will change the status & due dates of other report definitions.
 function updateDisplayTexts(aeReportId){
	 var idPrefix = "rd_"+aeReportId+"_";
	 var allReportDef = AE.reportDefinitions;
	 allReportDef.each(function(rdId){
		 var curEl = $(idPrefix + rdId);
		 if(curEl.checked){
			 updateDisplayTextOfRow(aeReportId, rdId, 'current');
		 }else {
			 //is any in my group checked?
			 if(isAnyInGroupChecked(aeReportId,rdId)){
				 //update me with group status
				 updateDisplayTextOfRow(aeReportId, rdId, 'group');
			 }else{
				//update me with other status
				 updateDisplayTextOfRow(aeReportId, rdId, 'other');
			 }
		 }
		 
	 });


	//update the rules engine messages area.
	var msgs = generateMessages(aeReportId);
	var htmlMsg = "";
	msgs.each(function(msg){
		htmlMsg = htmlMsg + "<li>" + msg + "</li>";
	});
	$('rulesMessageList-' + aeReportId).innerHTML = htmlMsg;
 }

//=================================================================================
 //function will change the status & due dates of a single report definition row.
 function updateDisplayTextOfRow(aeReportId, rdId, state){
	 var rowIdPrefix = "rd_"+aeReportId+"_"+rdId;
	 var elNamePrefix = "";
	 
	 if(state == 'current'){
		 elNamePrefix ="";
	 }
	 if(state == 'group'){
		 elNamePrefix ="grp";
	 }
	 if(state == 'other'){
		 elNamePrefix = "other";
	 }

	 $(rowIdPrefix+"-reportStatus").innerHTML = $(rowIdPrefix+ "_" + elNamePrefix + "status").value;
	 $(rowIdPrefix+"-reportDue").innerHTML = $(rowIdPrefix+ "_" + elNamePrefix +"due").value;
	 $(rowIdPrefix+"-reportAction").innerHTML = $(rowIdPrefix+ "_" + elNamePrefix +"action").value;

	 $(rowIdPrefix+"_actualstatus").value = $(rowIdPrefix+ "_" + elNamePrefix + "status").value;
	 $(rowIdPrefix+"_actualdue").value = $(rowIdPrefix+ "_" + elNamePrefix +"due").value;
	 var elActualAction = $(rowIdPrefix+"_actualaction");
	 elActualAction.value = $(rowIdPrefix+ "_" + elNamePrefix +"action").value;

	 //update the CSS classes
	 var newCssClass = '';
	 if(elActualAction.value){
		 newCssClass = elActualAction.value.toLowerCase();
	 }
	 var tr = $(elActualAction.parentNode.parentNode);
	 allCssClassNames.each(function(cssClass){
		 tr.removeClassName(cssClass);
	 });

	 tr.childElements().each(function(td){
		 allCssClassNames.each(function(cssClass){
			 td.removeClassName(cssClass);
		 });
	 });

	 
	 if(newCssClass){
		 tr.addClassName(newCssClass);
		 tr.childElements().each(function(td){
			 td.addClassName(newCssClass);
		 });
	 }
 }
//=================================================================================
 //function will return true, if any report definition of this report definitions group is checked. 
 function isAnyInGroupChecked(aeReportId, rdId){
	 var groupName = AE.reportDefinitionGroupHash.get(rdId);
	 var grpRdArray = AE.groupDefinitions.get(aeReportId).get(groupName);
	 var retVal = false;
	 grpRdArray.each(function(rd){
		 if(rd != rdId){
			 if($("rd_"+aeReportId+"_"+rd).checked){
				  retVal = true;
			 }
		 }
	 });
	 return retVal;
 }
//=================================================================================
function showNewDataCollection(){
	$('new-dc-section-0').show();
	$('add-dc-btn-row').remove();
	theAccordion.expand($('dc-section-0'));
}
//=================================================================================
function generateMessages(aeReportId){
	var processed = new Array();
	var messages = new Array();

	AE.reportDefinitions.each(function(rdId){
		
		if(processed.indexOf(rdId) > 0) return;
		
		var rdObj = AE.reportDefinitionHash.get(rdId);
		var actualAction = $('rd_'+aeReportId+'_'+rdId+'_actualaction').value;
		var grpRdArray = AE.groupDefinitions.get(aeReportId).get(rdObj.group);
		
		processed.push(rdId);
		
		var msg = "";
		var connector = "";
		if(actualAction){
			if(actualAction == 'Create'){
				var otherActionAmendOrWithdraw = false;
				if(grpRdArray){
					grpRdArray.each(function(otherRdId){
						var otherAction = $('rd_'+aeReportId+'_'+otherRdId+'_actualaction').value;
						if(otherAction == 'Withdraw' || otherAction == 'Amend'){
							otherActionAmendOrWithdraw = true;
						}
					});
				}
				if(!otherActionAmendOrWithdraw){
					msg = actualAction + " " + rdObj.name;
				}
			}else if(actualAction == 'Edit'){
				msg = actualAction + " " + rdObj.name;	
			}else if(actualAction == 'Withdraw'){
				msg = actualAction + " " + rdObj.name;
				connector = " replace with";
			}else if(actualAction == 'Amend'){
				msg = actualAction + " " + rdObj.name;
				connector = " with";
			}

			if(actualAction == 'Withdraw' || actualAction == 'Amend'){			
				var otherSelected = selectedReportDefinitionsFromGroup(aeReportId,rdObj.group);
				if(otherSelected.length > 0){
					msg = msg + connector;
					otherSelected.each(function(otherId){
						var rdOther = AE.reportDefinitionHash.get(otherId);
						msg = msg + " " + rdOther.name;
						processed.push(otherId);
					});
				}
			}

			if(msg) messages.push(msg);
			
		}
		
	});

	return messages;
}

//=================================================================================
//function will return the ids, of the report definitions checked from the same group.
function selectedReportDefinitionsFromGroup(aeReportId, groupName){
	var reportDefs = new Array();
	var grpRdArray = AE.groupDefinitions.get(aeReportId).get(groupName);
	grpRdArray.each(function(rd){
		if($("rd_"+aeReportId+"_"+rd).checked){
			reportDefs.push(rd);
		 }
	 });
	return reportDefs;
}

//=================================================================================
//function will submit the report to server. 
function forwardToReport(aeReportId, frm){
	
	$('activeAeReportId').value = aeReportId;

	//validations

	//submit the form
	frm.submit();
	
}

function validate(aeReportId){
	//determine if there is a create-edit action?
	var createOrEditAction = false;
	var noActualAction = true;
	var onlyWithdrawAction = true;
	
	//make sure, atleast one actual action is there. 
	if(noActualAction){
	}
	
	if(createOrEditAction){
		//make sure at least one ae is selected
	}

	if(onlyWithdrawAction){
		//make sure no ae is selected
	}
}

 </script>
 
 </head>
 <body>
  <tags:tabForm tab="${tab}" flow="${flow}" formName="review" hideBox="true">
   <jsp:attribute name="singleFields">
   
   <script type="text/javascript">
   
   Event.observe(window, "load", function() {
		//initialize accordion
		theAccordion = new Accordion("review-content", 1);
		
	   //initialize the datastructure. 
	   <c:forEach var="entry" items="${command.applicableReportTableMap}" >
	    AE.recommendedOptions.set(${entry.key}, new Array());
	    AE.groupDefinitions.set(${entry.key}, new Hash());
	    var grp_${entry.key} = null;
	    
	    <c:forEach var="row" items="${entry.value}">
	     <c:if test="${row.preSelected}">
	      AE.recommendedOptions.get(${entry.key}).push(${row.reportDefinition.id});
	     </c:if>

	     //add the group info
	     grp_${entry.key} = AE.groupDefinitions.get(${entry.key})
	     if( !(grp_${entry.key}.get('${row.group}')) ){
	    	 grp_${entry.key}.set('${row.group}', new Array());
	     }
	     grp_${entry.key}.get('${row.group}').push(${row.reportDefinition.id});

	     //add the report definition IDs
	     AE.reportDefinitions.push(${row.reportDefinition.id});

	     //put the reportdefintion-id : group
	     AE.reportDefinitionGroupHash.set(${row.reportDefinition.id}, '${row.group}');

		 //create & populate the report definition objects.
	     var rdObject = AE.reportDefinitionHash.get(${row.reportDefinition.id});
	     if(!rdObject){
		     rdObject =  new jsReportDefinition(${row.reportDefinition.id}, "${row.reportDefinition.label}" , "${row.group}" );
	    	 AE.reportDefinitionHash.set(${row.reportDefinition.id}, rdObject );
	     }
	    </c:forEach>

	    AE.reportDefinitions = AE.reportDefinitions.uniq();
	    
	    //default select the recommended report definitions
	    selectRecommendedReports(${entry.key});

		//update the statuses
		updateDisplayTexts(${entry.key});
	    
	  </c:forEach>
   });
   
   </script>


	<!--  for processing -->
	<input type="hidden" name="activeAeReportId" value="" id="activeAeReportId" />
	<input type="hidden" name="_finish"/>
	<!--  ============== -->
  
   <!--  ALERT -->
   <c:if test="${command.evaluationResult.alertRecommended}">
    <div style="border:1px solid #f00; height:100px; padding:9px; margin-bottom:10px;">
		<img src="<chrome:imageUrl name="stop_sign.png" />" alt="Stop!" style="float:left; margin-right:30px; margin-left:80px;" />
		<div style="font-size:20px; margin-bottom:5px;"><tags:message key="instruction_ae_action_recommended" /></div>
    </div>
   </c:if>
   <!--  ALERT END -->
  <div id="review-content"> 
   <c:set var="noOfAEReports" value="${fn:length(command.adverseEventReportingPeriod.aeReports)}" />
   <c:set var="noOfNewAe" value="${fn:length(command.adverseEventReportingPeriod.nonExpeditedAdverseEvents)}" />
   
	<!--  HAS Expedited Reports -->
	<c:if test="${noOfAEReports gt 0}">
		<c:forEach var="aeReport" items="${command.adverseEventReportingPeriod.aeReports}" varStatus="aeReportStatus">
		<c:set var="_aeReportId" value="${aeReport.id}" />
		<c:set var ="_primaryAE" value="${aeReport.adverseEvents[0]}" />
		<c:set var="_rulesMsgs" value="${command.rulesEngineMessageMap[_aeReportId]}" />
		<%-- 
		<chrome:division id="dc-section-${_aeReportId}" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code}:${_primaryAE.grade.displayName}" 
			collapsable="true">
		--%>	
		<chrome:accordion id="dc-section-${_aeReportId}" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code}:${_primaryAE.grade.displayName}" >
			<c:set var="_rulesMsgs" value="${command.rulesEngineMessageMap[_aeReportId]}" />
			<!--  Rules Message -->
			 <ae:rulesMessage rulesMessages="${_rulesMsgs}" aeReportId="${_aeReportId}" />
			
			<!--  Listing the reports -->
			<ae:recommendedReportRow applicableTableRows="${command.applicableReportTableMap[_aeReportId]}" 
				recommendedTableRows="${command.recommendedReportTableMap[_aeReportId]}"
			    aeReportId="${_aeReportId}" />
			<!--  Listing of adverse events -->
			<ae:seriousAdverseEvents adverseEvents="${command.evaluationResult.allAeMap[_aeReportId]}" aeReportId="${_aeReportId}" 
				primaryAeId="${_primaryAE.id}" />
				
			<div class="row" style="text-align:right;">
		 		<tags:button type="button" onclick="forwardToReport(${_aeReportId}, this.form);" value="Report" color="green" icon="continue" />
			</div>
		</chrome:accordion>
		<%-- 
		</chrome:division>	
		<hr></hr>	
		--%>
			
		</c:forEach>
	</c:if>
	
	<!--  New data collection -->
	<c:set var="_aeReportId" value="${command.zero}" />
	<c:set var ="_primaryAE" value="${command.evaluationResult.allAeMap[_aeReportId][0]}" />
	<c:set var="_rulesMsgs" value="${command.rulesEngineMessageMap[_aeReportId]}" />
	<%--
	<chrome:division id="dc-section-0" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code}:${_primaryAE.grade.displayName} " 
		collapsable="true" style="${noOfAEReports gt 0 ? 'display:none;' : ''}">
	--%>
	<div id="new-dc-section-0" style="${noOfAEReports gt 0 ? 'display:none;' : ''}">
	<chrome:accordion  id="dc-section-0" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code}:${_primaryAE.grade.displayName}" >
		
		<!--  Rules Message -->
		 <ae:rulesMessage rulesMessages="${_rulesMsgs}" aeReportId="${_aeReportId}" />
		
		<!--  Listing the reports -->
		<ae:recommendedReportRow applicableTableRows="${command.applicableReportTableMap[_aeReportId]}" 
				recommendedTableRows="${command.recommendedReportTableMap[_aeReportId]}"
			    aeReportId="${_aeReportId}" />
		<!--  Listing of adverse events -->
		<ae:seriousAdverseEvents adverseEvents="${command.evaluationResult.allAeMap[_aeReportId]}" aeReportId="${_aeReportId}" 
			primaryAeId="${_primaryAE.id}" />
		
		<div class="row" style="text-align:right;">
		 <tags:button type="button" onclick="forwardToReport(${_aeReportId}, this.form);" value="Report" color="green" icon="continue" />
		</div>
	</chrome:accordion>	
	</div>
	
   </div>
	<%--
	</chrome:division>	
	--%>

   <!--  Add new DC Button -->		
   <c:if test="${(noOfAEReports gt 0) and (noOfNewAe gt 0)}">
    <div id="add-dc-btn-row">
   	 <tags:button type="button" icon="add" id="add-dc-btn" value="Add Data Collection" color="green"  size="small" onclick="showNewDataCollection()" />
    </div>
   </c:if>
   </jsp:attribute>
   
   <jsp:attribute name="tabControls">
      <div class="content buttons autoclear">
          <div class="flow-buttons">
            <span class="prev">
              	<tags:button type="submit" value="Back" cssClass="tab1" color="blue" icon="back" id="flow-prev"/>
			</span>
          </div>
      </div>
  </jsp:attribute>
   
  </tags:tabForm>
 </body>
</html>