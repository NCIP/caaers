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
 
 <script type="text/javascript"><!--
 
 //to store the recommended options (aeReportId - {reportDefinitionId} 
 AE.recommendedOptions = new Hash();
 //to store the actual recomendations (so that we could reconcile recommended options) (aeReportId  -{reportDefinitionId})
 AE.referenceRecomendedOptions = new Hash();
 
 //to store group report definitions (aeReportId -{GroupId - {reportDefinitionId}}
 AE.groupDefinitions = new Hash();
 
 //to store all the report definitionIds
 AE.reportDefinitions = new Array();
 
 //to  reportDefinitionId - groupId
 AE.reportDefinitionGroupHash = new Hash();

 //to store the reportDefinitionId - ReportDefinition object.
 AE.reportDefinitionHash = new Hash();

 //to store the details of applicable report definition
 //[aeReportId - {reportDefinitionId - reportDefintion}]
 AE.applicableReportDefinitionHash = new Hash();
//same as applicable report definition, but will keep for getting 
//rules suggestion at a later point. 
 AE.referenceReportDefinitionHash = new Hash();

//store all aes and the reports in which they are submitted.
//[adverseEventId : {reportDefinitionId}]
 AE.reportedAEHash = new Hash();

 var checkImgSrc = '<chrome:imageUrl name="../check.png" />';
 var checkNoImgSrc ='<chrome:imageUrl name="../checkno.gif" />';
 var manualSelectMessage='<spring:message code="instruction_ae_override_confirmation" text="Not found key: instruction_ae_override_confirmation " />';
 var reportsWithdawnMessage = '<spring:message code="instruction_ae_report_withdrawn" text="Not found key: instruction_ae_report_withdrawn " />';
 var allCssClassNames = ['create', 'edit', 'withdraw', 'amend'];

 var theAccordion;
 
 //create an object to hold report definition details.
 var jsReportDefinition = Class.create();
 Object.extend(jsReportDefinition.prototype, {
   initialize: function(id, aeReportId, name, group , typeCode, status, grpStatus, otherStatus, due, grpDue, otherDue, action, grpAction, otherAction) {
	   this.id = id;
	   this.aeReportId = aeReportId;
	   this.name = name;
	   this.group = group;
	   this.typeCode = typeCode;

	   this.status = status;
	   this.grpStatus = grpStatus;
	   this.otherStatus = otherStatus;

	   this.due = due;
	   this.grpDue = grpDue;
	   this.otherDue = otherDue;

	   this.action = action;
	   this.grpAction = grpAction;
	   this.otherAction = otherAction;

	   this.trTemplate = '<tr class="#{cssClass}">' + 
	   '<td style="text-align:center;"><input type="checkbox" #{checked} disabled="disabled" /></td>' + 
	   '<td><span class="action-cell"><img src="<chrome:imageUrl name="../blue/#{action}-icon.png" />" alt="" /> #{action}</span></td>' +
  	   '<td><span><b>#{name}</b></span></td>' +
  	   '<td><span>#{status}</span></td>' +
  	   '<td><span>#{due}</span></td>' +
       '</tr>';
	   
   },
   toString : function(){
	   return "#{id},#{aeReportId},#{name}".interpolate({id:this.id, aeReportId:this.aeReportId, name:this.name});
   },
   select: function(){
	   $("rd_"+this.aeReportId+"_"+this.id).checked = true;
   },
   deSelect: function(){
	   $("rd_"+this.aeReportId+"_"+this.id).checked = false;
   },
   getActualAction : function(){
	   return $("rd_"+this.aeReportId+"_"+this.id + "_actualaction").value;
   },
   /*Returns true, if the current report definition is checked*/
   isChecked:function(){
	  return $("rd_"+this.aeReportId+"_"+this.id).checked
   },
   /* Returns true if any report definition in the group is selected, including the current one.*/
   isAnyInGroupChecked : function(){
	   var retVal = false;
	   $('applicable-reports-dc-' + this.aeReportId).select('.' + this.group).each(function(chkBox){
			if(chkBox.checked){
				retVal = true;
			}
	   });
	   
	   return retVal;
   },
   /* function will create and insert a recomended row, for this report definition.*/
   insertRecommendedRow : function(){
	   	var _status = "";
		var _due = "";
		var _action = "";
		var _checked = this.isChecked();

		if(_checked){
			_status = this.status;
			_action = this.action;
			_due = this.due;
		 }else{
			if(this.isAnyInGroupChecked()){
				_status = this.grpStatus;
				_action = this.grpAction;
				_due = this.grpDue;
			}else {
				_status = this.otherStatus;
				_action = this.otherAction;
				_due = this.otherDue;
			}
		}
		
		//insert a row 
		if(_action.length > 0){
			var content = "";
			if(_action == 'Amend'){
				if(_checked){
					content = this.trTemplate.interpolate({cssClass: 'recommended-tr', checked: '', name : this.name, status : this.grpStatus, due : this.grpDue, action : this.grpAction});
					content = content + this.trTemplate.interpolate({cssClass: 'recommended-tr', checked: 'checked="checked"', name : this.name, status : 'No Started', due : _due, action : 'Create'})
				}else{
					content = this.trTemplate.interpolate({cssClass: 'recommended-tr', checked: '', name : this.name, status : _status, due : _due, action : _action})
				}
				
			}else if(_action == 'Withdraw'){
				content = this.trTemplate.interpolate({cssClass: 'recommended-tr', checked: '', name : this.name, status : _status, due : _due, action : _action})
			}else {
				content = this.trTemplate.interpolate({cssClass: 'recommended-tr', checked: 'checked="checked"', name : this.name, status : _status, due : _due, action : _action})
			}

			var recommendedTRs = $('recommended-reports-dc-' + this.aeReportId).select('.recommended-tr');
			if(recommendedTRs.length > 0){
				recommendedTRs.last().insert({after:content});
			}else{
				$('tr-header-'+ this.aeReportId).insert({after:content});	
			}
			
			
		}
		
		 
   },
   /*function will change the status & due dates of a single report definition row.*/
   updateDisplayTextOfRow: function(){
	   	 var rowIdPrefix = "rd_"+this.aeReportId+"_"+this.id;

		 var _status = "";
		 var _due = "";
		 var _action = "";
		 
		 if(this.isChecked()){
			_status = this.status;
			_action = this.action;
			_due = this.due;
		 }else{
			if(this.isAnyInGroupChecked()){
				_status = this.grpStatus;
				_action = this.grpAction;
				_due = this.grpDue;
			}else {
				_status = this.otherStatus;
				_action = this.otherAction;
				_due = this.otherDue;
			}
		 } 
		 $(rowIdPrefix+"-reportStatus").innerHTML = _status;
		 $(rowIdPrefix+"-reportDue").innerHTML = _due;
		 $(rowIdPrefix+"-reportAction").innerHTML = "<img src='<chrome:imageUrl name='../blue/" + _action + "-icon.png' />' alt='' /> " + _action;
	
		 $(rowIdPrefix+"_actualstatus").value = _status;
		 $(rowIdPrefix+"_actualdue").value = _due;
		 $(rowIdPrefix+"_actualaction").value = _action;
	 
		 //update the CSS classes
		 var newCssClass = '';
		 if(_action){
			 newCssClass = _action.toLowerCase();
		 }
		 var tr = $($(rowIdPrefix+"_actualaction").parentNode.parentNode);
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
   },
   /*This method will set the object attributes to the values it should have when it is getting amended.*/
   forceAmend : function(){
	   this.status = "Being amended";
	   this.grpStatus = "Being amended";
	   this.otherStatus = "";

	   this.grpDue = "Submitted";
	   this.otherDue = "";
	   
	   this.action = "Amend";
	   this.grpAction ="Amend";
   },
   /*This method will set the manual selection flag*/
   setManualSelectionFlag:function(){
	   $('rd_' + this.aeReportId + '_' + this.id +'_manual').value=1;
   },
   /*This method will unset the manual selection flag*/
   unsetManualSelectionFlag:function(){
	   $('rd_' + this.aeReportId + '_' + this.id +'_manual').value=0;
   },
   deepCopy:function(){
	   return new jsReportDefinition(this.id, this.aeReportId,  this.name, this.group, this.typeCode, 
			   this.status, this.grpStatus, this.otherStatus, 
			   this.due, this.grpDue, this.otherDue, 
			   this.action, this.grpAction, this.otherAction);
   }
 });

 //=================================================================================
 //will show manually select, only applicable when there is no rules recomendation.
 function showManualSelectOptions(applicableSectionId, aeReportId){
	 var answer = confirm(manualSelectMessage);
	 if(!answer) return;
	 $(applicableSectionId).appear();
	 $('no-recommended-reports-dc-' + aeReportId).hide();
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
 //will unset the manual indicatior flag.
 function clearManualIndicatorFlag(aeReportId){
	 AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
	   rdObj.unsetManualSelectionFlag();
	 });
 }
 //=================================================================================
 //will show recommended options and will hide manually select DIV
 function restoreRecommended(recommendedSectionId, applicableSectionId, aeReportId){
	selectRecommendedReports(aeReportId);
	updateDisplayTexts(aeReportId);
	
	$(applicableSectionId).fade();
	$(recommendedSectionId).appear();

	clearManualIndicatorFlag(aeReportId);
	
	$('dc-' + aeReportId + '-override').show();
	$('dc-' + aeReportId + '-restore').hide();
 }
 //=================================================================================
 //function will check the recommended items
 function selectRecommendedReports(aeReportId){
	var rpHash = AE.applicableReportDefinitionHash.get(aeReportId);
	//deselect all
	rpHash.values().each(function(rdObj){
		 rdObj.deSelect();
	});

	 //check the recommended ones
	 AE.recommendedOptions.get(aeReportId).each(function(rdId){
		 rpHash.get(rdId).select();
	 });

 }
//=================================================================================
 //deselect reports of the same group.
 function deselectOtherReportsOfSameGroup(aeReportId, rdId, group){
	 AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
		if((rdId != rdObj.id) && (rdObj.group == group)){
			rdObj.deSelect();
			rdObj.unsetManualSelectionFlag();
		}
	});
 }
//=================================================================================
 /*This function handles, when someone clicks on  a report definition */	
 function handleReportSelection(aeReportId, rdId){
	 var curRdObject = AE.applicableReportDefinitionHash.get(aeReportId).get(rdId);
	 
	 //check if there is at least one ae. 
	 var selectedAEs = findSelectedAdverseEvents(aeReportId);
	 if(selectedAEs.length == 0){
		 curRdObject.deSelect();
		 return;
	 }
	//deselect reports of the same group.
	curRdObject.setManualSelectionFlag();
	deselectOtherReportsOfSameGroup(aeReportId,rdId, curRdObject.group);
	//update the display text.
	updateDisplayTexts(aeReportId);
 }

//=================================================================================
/*This function return an array of all the aes that unselected currently , under a datacollection*/
function findDeselectedAdverseEvents(aeReportId){
	var aes = new Array();
	$('adverseEvents-dc-' + aeReportId).select('.ae_'+aeReportId).each(function(chkBox){
		if(!chkBox.checked){
			aes.push(chkBox.value);
		}
	});
	return aes;
}


//=================================================================================
/*This function return an array of all the aes that selected , under a datacollection*/
function findSelectedAdverseEvents(aeReportId){
	var aes = new Array();
	$('adverseEvents-dc-' + aeReportId).select('.ae_'+aeReportId).each(function(chkBox){
		if(chkBox.checked){
			aes.push(chkBox.value);
		}
	});
	return aes;
}
 
//=================================================================================
 /*This function will handle the click associated to ae*/
 function handleAdverseEventSelection(aeReportId, aeId, reportedFlag){

	  //enable /disable primary ae radio button
	  if(!$("ae-" + aeReportId + "-" + aeId).checked){
		 var primaryField = $("ae-" + aeReportId + "-" + aeId + "-primary")
		 primaryField.checked = false;
		 primaryField.disabled = true;
	  }else{
		  var primaryField = $("ae-" + aeReportId + "-" + aeId + "-primary")
	      primaryField.disabled = false;
	  }
			   
	 
	 //find all selected adverse events. 
	 var selectedAEs = findSelectedAdverseEvents(aeReportId);
	 $('report-btn-' + aeReportId).disabled = (selectedAEs.length < 1);
	 if(selectedAEs.length < 1){

	/*	 === not required --
		 //none of the AEs are selected,so deselect all the report definitions.
		 AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
			rdObj.deSelect();
		});	 
			
		//clear off all manual selection flag
		clearManualIndicatorFlag(aeReportId);

		 //update the display.
		 updateDisplayTexts(aeReportId);

	*/	 
			 
	 }else if(reportedFlag){

		 //reset every thing, so that we are on the orignal state. 
		 resetRecommendedOptions(aeReportId);
		 resetApplicableReportDefinitions(aeReportId);

			
		//find the deselected adverse events
		 var deselectedAEs = findDeselectedAdverseEvents(aeReportId);
		 var forceAmendList = new Array();
		 if(deselectedAEs.length > 0){
			//see if the action of that rd is amend or withdraw. If not then...force amend it.
			 deselectedAEs.each(function (deselectedAeId){
				 AE.reportedAEHash.get(deselectedAeId).each(function(rdId){
					 var rdObj = AE.applicableReportDefinitionHash.get(aeReportId).get(rdId);
					 var actualAction = rdObj.getActualAction();
					 if(actualAction != 'Amend' ){
					 	forceAmendList.push(rdObj);
					 }
				 });
			 });

		 }

		 forceAmendList.each(function(rdObj){
			 //force amend
			 rdObj.forceAmend();
			 //push it to recomended options.
			 AE.recommendedOptions.get(aeReportId).push(rdObj.id);
			 //deselect others from the same group.
			 deselectOtherReportsOfSameGroup(aeReportId,rdObj.id, rdObj.group);
		 });

		
		 
		 //select recomended reports.
		 selectRecommendedReports(aeReportId);

		 //clear off all manual selection flag
		 clearManualIndicatorFlag(aeReportId);

		 //update the display.
		 updateDisplayTexts(aeReportId);

		 var applicableDiv = $('applicable-reports-dc-' + aeReportId);
		 var headerDiv = $('reports-header-dc-' + aeReportId);
		 var noRecomendationDiv = $('no-recommended-reports-dc-' + aeReportId);
		 var recomendationDiv =  $('recommended-reports-dc-' + aeReportId);
		 var recomendedOptions = AE.recommendedOptions.get(aeReportId);
		 var reqMsgDiv =  $('rulesMessage-'+ aeReportId + '-required');
		 var notReqMsgDiv =  $('rulesMessage-'+ aeReportId + '-not-required')

		 //if there are recomended options, header should be visible & noRecomendation should be hidden
		 if(recomendedOptions.length > 0){
			 noRecomendationDiv.hide();
			 notReqMsgDiv.hide();
			 reqMsgDiv.appear();
			 headerDiv.appear();
			 //if applicableDiv is hidden, then only show recomendationDiv
			 if(!applicableDiv.visible()){
				 recomendationDiv.appear();
				 $('dc-' + aeReportId + '-override').show();
				 $('dc-' + aeReportId + '-restore').hide();
			 }else{
				 $('dc-' + aeReportId + '-override').hide();
				 $('dc-' + aeReportId + '-restore').show();
			 }
		 }else{
			 if(!applicableDiv.visible()){
			 	noRecomendationDiv.appear();
			 }
			 notReqMsgDiv.appear();
			 reqMsgDiv.hide();
			 headerDiv.hide();
			 recomendationDiv.hide();
		 }
	 }
	
 }


 
//=================================================================================
/*This function will update the header for primary AE*/	
function handlePrimaryAdverseEvent(aeReportId, aeId, aeTerm, grade){
	$('dc-section-' + aeReportId).innerHTML= aeTerm + ", " + grade;
	$("ae-" + aeReportId + "-" + aeId ).checked = true;
}
 
//=================================================================================
 /*This function will reset the recommended options*/
 function resetRecommendedOptions(aeReportId){
	 AE.recommendedOptions.unset(aeReportId);
	 AE.recommendedOptions.set(aeReportId, AE.referenceRecomendedOptions.get(aeReportId).clone());
 }
//=================================================================================
  /*This method will reset the recomended report defs display*/
 function clearRecommendedReportsDisplay(aeReportId){
	 //remove every tr having .forced-tr class
	$('recommended-reports-dc-' + aeReportId).select('.recommended-tr').each(function(tr){
		tr.remove();
	}); 
 }
 //=================================================================================
 /*Will reset the applicable report definition hash, from the reference. */
 function resetApplicableReportDefinitions(aeReportId){
	var refHash =  AE.referenceReportDefinitionHash.get(aeReportId);
	var newHash = new Hash();
	refHash.each(function(pair){
		newHash.set(pair.key, pair.value.deepCopy());
	});
	 
	 AE.applicableReportDefinitionHash.unset(aeReportId);
	 AE.applicableReportDefinitionHash.set(aeReportId,newHash);
 }
//=================================================================================
 //function will change the status , recommended display & due dates of report definitions.
 function updateDisplayTexts(aeReportId){
	 
	 //remove the recomended display options. 
	 clearRecommendedReportsDisplay(aeReportId);
	 
	 AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
		 rdObj.updateDisplayTextOfRow();
		 rdObj.insertRecommendedRow();
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
function showNewDataCollection(){
	$('new-dc-section-0').show();
	$('add-dc-btn-row').remove();
	theAccordion.expand($('dc-section-0'));
}
//=================================================================================
/*This function will generate the summary display*/	
function generateMessages(aeReportId){
	var processed = new Array();
	var messages = new Array();

	AE.reportDefinitions.each(function(rdId){
		
		if(processed.indexOf(rdId) > 0) return;
		
		var rdObj = AE.applicableReportDefinitionHash.get(aeReportId).get(rdId);
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
					msg = "<img src='<chrome:imageUrl name='../blue/" + actualAction + "-icon.png' />' alt='' class='action-icon' /> <span style='font-weight:bold'>" + actualAction + "</span> " + rdObj.name;
				}
			}else if(actualAction == 'Edit'){
				msg = "<img src='<chrome:imageUrl name='../blue/" + actualAction + "-icon.png' />' alt='' class='action-icon' /> <span style='font-weight:bold'>" + actualAction + "</span> " + rdObj.name;	
			}else if(actualAction == 'Withdraw'){
				msg = "<img src='<chrome:imageUrl name='../blue/" + actualAction + "-icon.png' />' alt='' class='action-icon' /> <span style='font-weight:bold'>" + actualAction + "</span> " + rdObj.name;
				connector = " and replace with";
			}else if(actualAction == 'Amend'){
				msg = "<img src='<chrome:imageUrl name='../blue/" + actualAction + "-icon.png' />' alt='' class='action-icon' /> <span style='font-weight:bold'>" + actualAction + "</span> " + rdObj.name;
				connector = " with";
			}

			if(actualAction == 'Withdraw' || actualAction == 'Amend'){			
				var otherSelected = selectedReportDefinitionsFromGroup(aeReportId,rdObj.group);
				if(otherSelected.length > 0){
					msg = msg + connector;
					otherSelected.each(function(otherId){
						var rdOther = AE.applicableReportDefinitionHash.get(aeReportId).get(otherId);
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

	if(!validate(aeReportId)){
		return;
	}
	
	$('activeAeReportId').value = aeReportId;

	//validations
	
	//confirm withdrawls
	 var withdrawnReports = "";
	 AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
		 if(rdObj.getActualAction() == 'Withdraw'){
			 if(withdrawnReports.length > 0){
				 withdrawnReports = withdrawnReports + ",";
			 }
			 withdrawnReports = withdrawnReports +  rdObj.name;
		 }
	 });

	 if(withdrawnReports.length > 0){
		if(confirm(withdrawnReports + reportsWithdawnMessage)){
			//submit the form
			frm.submit();
		} 
	
	 }else{
		//submit the form
		frm.submit();
	 }
	
	
	
}

function validate(aeReportId){
	//determine if there is a create-edit action?
	var createOrEditAction = false;
	var noActualAction = true;
	var onlyWithdrawAction = true;
	var noPrimaryAE = true;

	var selectedAEs = findSelectedAdverseEvents(aeReportId);
	if(selectedAEs.length < 1){
		alert("At least one adverse event should be selected");
		return false;
	}

	//check for actual action.
	AE.applicableReportDefinitionHash.get(aeReportId).values().each(function(rdObj){
		if(rdObj.getActualAction()){
			noActualAction = false;
		}
	});
	
	
	//make sure, atleast one actual action is there. 
	if(noActualAction){
		alert("At least one report should be selected");
		return false;
	}

	$$('.ae_' + aeReportId + '_primary').each(function (el){
		if(el.checked){
			noPrimaryAE = false;
		}
	});
	
	if(noPrimaryAE){
		alert('At least one primary adverse event should be selected');
		return false;
	}

	if(onlyWithdrawAction){
		//make sure no ae is selected
	}

	return true;
}

 --></script>
 
 </head>
 <body>
  <tags:tabForm tab="${tab}" flow="${flow}" formName="review" hideBox="true">
   <jsp:attribute name="singleFields">
   
   <script type="text/javascript">
   
   Event.observe(window, "load", function() {

		//remove the query string from form url
		removeQueryStringFromForm('command');
	   
		//initialize accordion
		theAccordion = new Accordion("review-content", 1);
		//initialize the submitted ae datastructure
		<c:forEach var="aeEntry" items="${command.evaluationResult.reportedAEIndexMap}">
			var rdIdArr = new Array();
			<c:forEach var="rd" items="${aeEntry.value}" >
			 rdIdArr.push(${rd.id});
			</c:forEach>
			AE.reportedAEHash.set(${aeEntry.key}, rdIdArr);
		</c:forEach>
	   //initialize the datastructure. 
	   <c:forEach var="entry" items="${command.applicableReportTableMap}" >
	    AE.recommendedOptions.set(${entry.key}, new Array());
	    AE.referenceRecomendedOptions.set(${entry.key}, new Array());
	    AE.groupDefinitions.set(${entry.key}, new Hash());
	    
	    AE.applicableReportDefinitionHash.set(${entry.key}, new Hash());
	    AE.referenceReportDefinitionHash.set(${entry.key}, new Hash());
	    
	    var grp_${entry.key} = null;
	    
	    <c:forEach var="row" items="${entry.value}">
	     <c:if test="${row.preSelected}">
	      AE.recommendedOptions.get(${entry.key}).push(${row.reportDefinition.id});
	      AE.referenceRecomendedOptions.get(${entry.key}).push(${row.reportDefinition.id});
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

	     //create and store applicableReportDefinitions.
		 var rdObject =  new jsReportDefinition(${row.reportDefinition.id}, ${entry.key},
			     "${row.reportDefinition.label}" , "${row.group}" , "${row.reportDefinition.reportType.code}",
			     "${row.status}", "${row.grpStatus}", "${row.otherStatus}",
			     "${row.due}", "${row.grpDue}", "${row.otherDue}",
			     "${row.action}", "${row.grpAction}", "${row.otherAction}" );

		 AE.applicableReportDefinitionHash.get(${entry.key}).set(rdObject.id, rdObject);

		 AE.referenceReportDefinitionHash.get(${entry.key}).set(rdObject.id, rdObject.deepCopy());
		 
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
    <div style="border:1px solid #f00; height:100px; padding:9px; margin-bottom:10px; margin-left:150px; width:600px;">
		<img src="<chrome:imageUrl name="stop_sign.png" />" alt="Stop!" style="float:left; margin-right:30px; margin-left:40px;" />
		<div style="font-size:20px; margin-bottom:5px; margin-top:35px;"><tags:message key="instruction_ae_action_recommended" /></div>
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
		<chrome:accordion id="dc-section-${_aeReportId}" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code} : ${_primaryAE.grade.displayName}" >
			<c:set var="_rulesMsgs" value="${command.rulesEngineMessageMap[_aeReportId]}" />
			<!--  Rules Message Top -->
			 <ae:rulesMessageTop rulesMessages="${_rulesMsgs}" aeReportId="${_aeReportId}" />
			
			<!--  Listing the reports -->
			<ae:recommendedReportRow applicableTableRows="${command.applicableReportTableMap[_aeReportId]}" 
				recommendedTableRows="${command.recommendedReportTableMap[_aeReportId]}"
			    aeReportId="${_aeReportId}" />
			<!--  Listing of adverse events -->
			<ae:seriousAdverseEvents adverseEvents="${command.evaluationResult.allAeMap[_aeReportId]}" aeReportId="${_aeReportId}" 
				primaryAeId="${_primaryAE.id}" />
			<!--  Rules Message Bottom -->
			<div class="rulesMessageBottom">
			 	<ae:rulesMessageBottom rulesMessages="${_rulesMsgs}" aeReportId="${_aeReportId}" />
				<div class="row" style="text-align:right;">
			 		<tags:button id="report-btn-${_aeReportId}" type="button" onclick="forwardToReport(${_aeReportId}, this.form);" value="Report" color="green" icon="continue" />
				</div>
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
	<chrome:accordion  id="dc-section-0" title="${_primaryAE.adverseEventTerm.universalTerm}, Grade ${_primaryAE.grade.code} : ${_primaryAE.grade.displayName}" >
		
		<!--  Rules Message Top -->
		 <ae:rulesMessageTop rulesMessages="${_rulesMsgs}" aeReportId="${_aeReportId}" />
		
		<!--  Listing the reports -->
		<ae:recommendedReportRow applicableTableRows="${command.applicableReportTableMap[_aeReportId]}" 
				recommendedTableRows="${command.recommendedReportTableMap[_aeReportId]}"
			    aeReportId="${_aeReportId}" />
		<!--  Listing of adverse events -->
		<ae:seriousAdverseEvents adverseEvents="${command.evaluationResult.allAeMap[_aeReportId]}" aeReportId="${_aeReportId}" 
			primaryAeId="${_primaryAE.id}" />
		<!--  Rules Message Bottom -->
		<div class="rulesMessageBottom">
			<ae:rulesMessageBottom rulesMessages="${_rulesMsgs}" aeReportId="0" />
			<div class="row" style="text-align:right;">
			 	<tags:button id="report-btn-0" type="button" onclick="forwardToReport(0, this.form);" value="Report" color="green" icon="continue" />
			</div>
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