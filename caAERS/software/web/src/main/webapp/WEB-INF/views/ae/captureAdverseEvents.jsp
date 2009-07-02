<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
 <head>
    <tags:dwrJavascriptLink objects="captureAE,createStudy,createAE,routingAndReview"/>
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="none">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments />
    		</div>
    	</jsp:attribute>
    </tags:slider>

 <script>
 	var grades = ['NORMAL','MILD', 'MODERATE', 'SEVERE', 'LIFE_THREATENING', 'DEATH'];
	var catSel = null;
	var RPCreatorClass = Class.create();
	var deleteIndex = 0;

    var oldSignatures = new Array();
    var routingHelper = new RoutingAndReviewHelper(captureAE, 'reportingPeriod');
    var LOCATION = document.location;

    Object.extend(RPCreatorClass.prototype, {
        initialize : function(rpDetailsDiv) {
            this.win = null;
            this.rpDetailsDiv = $(rpDetailsDiv);
        },

        addAdverseEvents:function(selectedTerms) {
            //find the terms that are not already added in the page
            var listOfTermIDs = new Array();
            var first = 0;
            
            $H(selectedTerms).keys().each(function(termID) {
                var tID = lookupByTermId(termID);
                if (tID == -1) listOfTermIDs.push(termID); else {
                    openDivisionById(tID);
                    if (first == 0) {
                        // jump to the box
                        document.location = document.location.toString().split("#")[0] + "#adverseEventTerm-" + termID;
                        first = termID;
                    }
                }
            }.bind(this));


            //get the HTML to add from server
            var notAddedTerms = new Array();
            captureAE.addObservedAE(listOfTermIDs, function(ajaxOutput) {
                $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
               
            }.bind(this));
        },

        isTermAgainAdded:function(termID) {
            //will tell wheter the term is already present
            $$('.eachRowTermID').each(function(aTerm) {
                if (termID == aTerm.value()) return true;
            });
            return false;
        },

       
        deleteAdverseEvent:function(indx) {
            var repIdArr = new Array();
            var listOfAEIndexes = $$('.submittedAERow');
            var aeSubmitted = 0;
            for (var i = 0; i < listOfAEIndexes.length; i++)
            {
                if (listOfAEIndexes[i].value == indx) {
                    aeSubmitted = 1;
                    deleteIndex = indx;
                    var repElementId = 'ae-section-' + indx + '-reportID';
                    repIdArr[0] = document.getElementById(repElementId).value;
                    var form = document.getElementById('command');
                    form._action.value = 'deleteAE';
                    displayAmendPopup('', repIdArr);
                    document.getElementById('command')._amendReportIds.value = repIdArr;
                }
            }
            if (aeSubmitted == 0)
            {
                if (!confirm("Are you sure you want to delete this?"))
                    return false;
                captureAE.deleteAdverseEvent(indx, '', function(ajaxOutput) {
                    $('ae-section-' + indx).remove();
                }.bind(this));
            }
        }

    });

 	
 	/*
 		Create an instance of the RPCreatorClass, by passing 'adverseEventReportingPeriod' which is the ID of Reporting Period select element.
 	*/
 	var rpCreator = null; 
 	Event.observe(window, "load", function(){
 	
 		Event.observe('flow-next', 'click', checkSubmittedAEs);
 		Event.observe('flow-prev', 'click', checkSubmittedAEs);
 		Event.observe('flow-update', 'click', checkSubmittedAEs);
 		
 		var url = document.addRoutineAeForm.action;
 		var stripped_url = '';
 		var index = -1;
 		index = url.indexOf("?");
 		if(index != -1){
			stripped_url = url.substr(0,index);
			document.addRoutineAeForm.action = stripped_url;
		}
		
 		rpCreator = new RPCreatorClass('detailSection');

 		//Check if reportingPeriod is selected and enable the slider.
 		if(${command.workflowEnabled}){
            routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
 		}


 		// Reset the value of flag AE.checkForModification to false.
        oldSignatures = getSignatures('.ae-section');
        
        Event.observe(window, "beforeunload", function(e) {
            if (checkForModificationsOnPage(e)) {
                e.returnValue = "You have unsaved information.";
            }
        });
        
 	});

    // ----------------------------------------------------------------------------------------------------------------

    function getSignatures(cssIdentifier) {
        var arr = new Array();
        var listOfAEIndexes = $$(cssIdentifier);
        for (var i = 0; i < listOfAEIndexes.length; i++) {
            var signature = createSignature(i);
            arr[i] = signature;
        }
        return arr;
    }
    
    function checkForModificationsOnPage(event){
    	if(AE.checkForModification){
	    	var newSignatures = new Array();
   	    	newSignatures = getSignatures('.ae-section');
   	    	
        	if (oldSignatures.length != newSignatures.length) return true;
        
       		for (var i = 0; i < oldSignatures.length; i++) {
            	if (oldSignatures[i] != newSignatures[i]) return true;
        	}
    	}
    	return false;
    }

    // ----------------------------------------------------------------------------------------------------------------

    function checkSubmittedAEs(event) {

        var reportIdArray = new Array();
        var totalReportIdCount = 0;
        var listOfAEIndexes = $$('.submittedAERow');

        for (var i = 0; i < listOfAEIndexes.length; i++) {
            var signature = createSignature(listOfAEIndexes[i].value);
            var oldSignatureId = 'ae-section-' + listOfAEIndexes[i].value + '-signature';
            var oldSignature = document.getElementById(oldSignatureId).value;
            if (signature != oldSignature) {
                // The ae was modified.
                var reportElementId = 'ae-section-' + listOfAEIndexes[i].value + '-reportID';
                reportIdArray[totalReportIdCount++] = document.getElementById(reportElementId).value;
            }
        }

        if (totalReportIdCount != 0) {
            var form = document.getElementById('command');
            form._action.value = 'amendmentRequired';
            displayAmendPopup(event, reportIdArray);
        }
        document.getElementById('command')._amendReportIds.value = reportIdArray;
    }

    // ----------------------------------------------------------------------------------------------------------------
    
    function createSignature(index){
		var signature = '';

		var inputOtherMeddra = $('adverseEvents[' + index + '].lowLevelTerm-input');
		var inputStartDate = $('adverseEvents[' + index + '].startDate');
		var inputEndDate = $('adverseEvents[' + index + '].endDate');
		var inputEventHour = $('adverseEvents[' + index + '].eventApproximateTime.hour');
		var inputEventMinute = $('adverseEvents[' + index + '].eventApproximateTime.minute');
		var inputEventLocation = $('adverseEvents[' + index + '].eventLocation');
		
		var verbatimId = 'adverseEvents[' + index + '].detailsForOther';
		
		var attributionId = 'adverseEvents[' + index + '].attributionSummary';
		var hospitalizationId = 'adverseEvents[' + index + '].hospitalization';
		var expectedId = 'adverseEvents[' + index + '].expected';

		signature = $(verbatimId).value + '$$' + // verbatim input
					findGradeForAE(index) + '$$' + // grade input
					document.getElementById(attributionId).value + '$$' + // attribution input
					document.getElementById(hospitalizationId).value + '$$' + // hospitalization input
					document.getElementById(expectedId).value + '$$'; // expected input

        // If otherMeddraId element exists append otherMeddra Value to the signature.
        if(inputOtherMeddra){
        	signature = signature + inputOtherMeddra.value;
        }
        signature = signature + '$$';

        //startDate
		if(inputStartDate){
			signature = signature + inputStartDate.value;
		}
		signature = signature + '$$';
		//endDate
		if(inputEndDate){
			signature = signature + inputEndDate.value;
		}
		signature = signature + '$$';
		//event hour
		if(inputEventHour){
			signature = signature + inputEventHour.value;
		}
		signature = signature + '$$';
		//event minute
		if(inputEventMinute){
			signature = signature + inputEventMinute.value;
		}
		signature = signature + '$$';
		//event location
		if(inputEventLocation){
			signature = signature + inputEventLocation.value;
		}
		signature = signature + '$$';
		
        return signature;
	}

    // ----------------------------------------------------------------------------------------------------------------
    
    function deleteOrAmendAndSubmit(){
		Windows.close('amend-popup-id');
		var form = document.getElementById('command');
		if(form._action.value == 'amendmentRequired'){
			AE.checkForModification = false;
			form.submit();
		}else if(form._action.value == 'deleteAE'){
			captureAE.deleteAdverseEvent(deleteIndex, document.getElementById('command')._amendReportIds.value, function(ajaxOutput){

					//Remove the row that is deleted
 	 	 			$('ae-section-' + deleteIndex).remove();

 	 	 			//Remove the following from other Adverse Events associated with the same Expedited Report, 
 	 	 			// this is to avoid re-ammendment of the same report. 

 	 	 		
 	 	 			// First determine all the indexes that have the reportId = repId
 	 	 			var repId = document.getElementById('command')._amendReportIds.value;
 	 	 			
 	 	 			var handleAeArr = new Array();
 	 	 			var c = 0;
 	 	 			var listOfAEIndexes = $$('.submittedAERow');
					for(var i = 0 ; i < listOfAEIndexes.length ; i++)
					{
						var repIdElement = 'ae-section-' + listOfAEIndexes[i].value + '-reportID';
						if(document.getElementById(repIdElement).value == repId)
							handleAeArr[c++] = listOfAEIndexes[i].value;
					}
					
					// Remove the image and ".submittedAERow" elements for all the indexes in handleAeArr array.
					for(var j = 0; j < handleAeArr.length; j++)
					{

					    //Uncomment the below image hiding lines, after fixing the image correctly.
						
					   //$('ae-section-' + handleAeArr[j] + '-submitted-image').remove();
					   //$('ae-section-' + handleAeArr[j] + '-submittedAERow').remove();
					}
 	 	 			
 	 		});
 	 		
		}
	}

    // ----------------------------------------------------------------------------------------------------------------
    
    function displayAmendPopup(event, reportIdArray){
		//alert('Inside displayAmendPopup, reportIdArray = ' + reportIdArray);
		var form = document.getElementById('command');
		if(form._action.value == 'amendmentRequired')
			Event.stop(event);
		
		// Show the reports that are in the reportIdArray
		for(var i = 0; i < reportIdArray.length; i++){
			//alert('HERE... amend-aeReport-' + reportIdArray[i]);
			$('amend-aeReport-' + reportIdArray[i]).show();
		}
		
		var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"amend-popup-id", width:700,  height:530, top: 30, left: 300});
        contentWin.setContent( 'display_amend_popup' );
        contentWin.showCenter(true);
        popupObserver = {
      			onDestroy: function(eventName, win) {
      				if (win == contentWin) {
      					$('display_amend_popup').style.display='none';
      					
      					// Hide the reports that are in the reportIdArray
						for(var i = 0; i < reportIdArray.length; i++){
							$('amend-aeReport-' + reportIdArray[i]).hide();
						}
						
      					contentWin = null;
      					Windows.removeObserver(this);
      				}
      			}
      		}
        Windows.addObserver(popupObserver);
	}
	
    // ----------------------------------------------------------------------------------------------------------------	
	//findGradeForAE : This function will find the selected grade value, for an adverse event identified by index
	function findGradeForAE(index){
		var gradeRowId = 'adverseEvents[' + index + '].grade-row';
		var gradeValue = '';
		$(gradeRowId).select("input[type='radio']").each(function(el){
			if(el.checked) gradeValue = el.value;
		});
		return gradeValue;
    }
    // ----------------------------------------------------------------------------------------------------------------
	//javascript:fireAction(index,section-id,sectionCSS) : This function will be called when the delete button on the AE is clicked.;
	function fireAction(index, sectionId, sectionCSS) {
		//AE.checkForModification = false; //tell app,not to complain about page modification event. 
		rpCreator.deleteAdverseEvent(index);
	}

    // ----------------------------------------------------------------------------------------------------------------

     function lookupByTermId(_id) {
        var list = $$('div.aeID-' + _id);
        for (i=0; i<list.length; i++) {
            return (list[i].id);
        }
         return -1;
     }

    // ----------------------------------------------------------------------------------------------------------------

     function closeDivisionById(_id) {
             panelDiv = $("contentOf-" + _id);
             imageId= 'image-' + _id;
             imageSource = $(imageId).src;
             CloseDown(panelDiv, arguments[1] || {});
             document.getElementById(imageId).src = imageSource.replace('down','right');
     }

     // ----------------------------------------------------------------------------------------------------------------

     function openDivisionById(_id) {
             panelDiv = $("contentOf-" + _id);
             imageId= 'image-' + _id;
             imageSource = $(imageId).src;
             OpenUp(panelDiv, arguments[1] || {});
             document.getElementById(imageId).src = imageSource.replace('right','down');
     }

     // ----------------------------------------------------------------------------------------------------------------
 
 </script>

</head>
<body>
     <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes" formName="addRoutineAeForm" hideBox="true">
      	
      	<jsp:attribute name="singleFields">
      		<input type="hidden" name="_action" id="_action" value="">
      		<input type="hidden" name="_amendReportIds" id="_amendReportIds" value="">
			
      		<div id="detailSection">
				<c:if test="${not empty command.adverseEventReportingPeriod}">
				
				
					<%--  Begining Of Observed AE section --%>
					<chrome:box title="Adverse Events" collapsable="true" id="observedID" autopad="true">
					 
						<p><tags:instructions code="instruction_ae_oae"/></p>

<%--
                        <div id="flash-message" class="info" style="width:300px;">
                            <img src="<c:url value="/images/board.png" />" alt="" align="middle">&nbsp;
                            <a style="text-decoration:none; color:black; font-weight:bold;" href="<c:url value="/pages/ae/blankForm?st=${command.study.id}&sb=${command.participant.id}&cs=${command.adverseEventReportingPeriod.id}" />">Download Blank AE Print Form</a>
                        </div>
--%>

 						<tags:aeTermQuery
                       			isMeddra="${not empty command.study.aeTerminology.meddraVersion}"
                       			noBackground="true"
                       			callbackFunctionName="rpCreator.addAdverseEvents"
                       			ignoreOtherSpecify="false"
                       			isAjaxable="true"
                       			version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}"
                       		title="">
            			</tags:aeTermQuery>

            			<span id="observedBlankRow"></span>
            			<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            				<c:if test="${not ae.solicited}">
            					<ae:oneRoutineAdverseEvent index="${status.index}" adverseEvent="${ae}" collapsed="true" enableDelete="true" isSolicited="false"/>
            				</c:if> 
            			</c:forEach>   
					</chrome:box>
					<%-- End of Observed AE section --%>
					
					<%-- Begining of Solicited AE section --%>
					<c:if test="${command.havingSolicitedAEs}">
						<chrome:box title="Solicited Adverse Events" collapsable="true" id="solicitatedID" autopad="true">
							<p><tags:instructions code="instruction_ae_sae"/></p>
							<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            				<c:if test="${ae.solicited}">
            					<ae:oneRoutineAdverseEvent index="${status.index}" adverseEvent="${ae}" collapsed="true" enableDelete="false" isSolicited="true"/>
            				</c:if> 
            			</c:forEach>   
 						</chrome:box>
					</c:if>
					<%--  End of Solicited AE section --%>
					
					<%-- Begin : Sections that will be displayed in Amend PopUp --%>
					<div id="display_amend_popup" style="display:none;text-align:left" >
				    	<chrome:box title="Amendments Required" id="popupId">
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
				          				<div class="summarylabel">Course</div>
				          				<div class="summaryvalue">${command.adverseEventReportingPeriod.name}</div>
				        			</div>
				      			</div>
				    		</c:if>
				    		<div id="div-reports-to-be-amended" style="text-align:left;">
				      			<hr/>
				      			<p>
				        			<tags:instructions code="instruction_ae_amendments_required"/>
				      			</p>
				      			<chrome:division title="Reports that will be Amended" id="div-selected-reports" collapsable="false">
				      				<c:forEach items="${command.adverseEventReportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
				      					<div class="eXtremeTable" id="amend-aeReport-${aeReport.id}" style="display:none;text-align:left">
				                    		<table width="100%" border="0" cellspacing="0" class="tableRegion">
				                      			<thead>
				                        			<tr align="center" class="label">
				                          				<td class="tableHeader" width="15%">Report Type</td>
				                          				<td class="centerTableHeader" width="10%">Report Version</td>
				                          				<td class="centerTableHeader" width="10%"># of AEs</td>
				                          				<td class="tableHeader" width="20%">Data Entry Status</td>
				                          				<td class="tableHeader" width="20%">Submission Status</td>
				                       				</tr>
				                      			</thead>
				                      			<ae:oneReviewExpeditedReportRow aeReport="${aeReport}" index="${statusAeReport.index}" />
				                      		</table>
				        		        </div>
				              		</c:forEach>
				              		<br><br>
				                      		<table width="100%">	
				                      			<tr>
				                      				<td align="left">
				                      					<input type="submit" value="Amend" id="amendment-required-yes" onClick="javascript:window.parent.deleteOrAmendAndSubmit();"/>
				                      				</td>
				                      				<td align="right">
					                      				<input type="submit" value="Don't Amend" id="amendment-required-no" onClick="javascript:window.parent.Windows.close('amend-popup-id');"/>
				                      				</td>
				                      			</tr>	
						                    </table>
				      			</chrome:division>
      						</div>
    					</chrome:box>	
    				</div>
					<%-- End : Sections displayed in amend popup --%>
					
					
				</c:if>
			</div>
      		
					
       </jsp:attribute>
       <jsp:attribute name="tabControls">
      <div class="content buttons autoclear">
          <div class="flow-buttons">
              <span class="prev">
              	<tags:button type="submit" color="blue" icon="Save &amp; Back" id="flow-prev" cssClass="tab0" value="Save &amp; Back"/>
			  </span>
				  <span class="next">
				  	<tags:button type="submit" color="blue" icon="save" id="flow-update" cssClass="tab1" value="Save" />
					<tags:button type="submit" icon="Save & Continue" color="green" id="flow-next" value="Save & Report"/>
				  </span>
          </div>
      </div>
  </jsp:attribute>
    </tags:tabForm>
 </body>
</html>