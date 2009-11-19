<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
 <head>
    <tags:dwrJavascriptLink objects="captureAE,createStudy,createAE,routingAndReview"/>
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" 
    		display="none" workflowType="reportingPeriod" reportingPeriod="${command.adverseEventReportingPeriod }">
    </tags:slider>

 <script><!--
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

                var otherSepcifyTerm = selectedTerms.get(termID).indexOf('Other (Specify') > 0;
                var tID = (otherSepcifyTerm ? -1 : lookupByTermId(termID));
                if (tID == -1){
                     listOfTermIDs.push(termID); 
                }else {
                    openDivisionById(tID);
                    if (first == 0) {
                        // jump to the box
                        document.location = document.location.toString().split("#")[0] + "#adverseEventTerm-" + termID;
                        first = termID;
                    }
                }
            }.bind(this));


            //get the HTML to add from server
            if(listOfTermIDs.size() > 0){
            	captureAE.addObservedAE(listOfTermIDs, function(ajaxOutput) {
                    $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
                   
                }.bind(this));
            }
            
        },

        deleteAdverseEvent:function(indx) {

        	 if (!confirm("Are you sure you want to delete this?"))
                 return false;
             captureAE.deleteAdverseEvent(indx, '', function(ajaxOutput) {
                 $('ae-section-' + indx).remove();
             }.bind(this));
        }

    });

 	
 	/*
 		Create an instance of the RPCreatorClass, by passing 'adverseEventReportingPeriod' which is the ID of Reporting Period select element.
 	*/
 	var rpCreator = null; 
 	Event.observe(window, "load", function(){
 	
		//remove the query string from form url
		removeQueryStringFromForm('command');
		
 		rpCreator = new RPCreatorClass('detailSection');

 		//Check if reportingPeriod is selected and enable the slider.
 		if(${command.workflowEnabled}){
            routingHelper.retrieveReviewCommentsAndActions('${command.adverseEventReportingPeriod.id}').bind(routingHelper)();
 		}


 	});


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
 
 --></script>

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

                        <div align="right"><a style="text-decoration:none; color:black; font-weight:bold;" href="<c:url value="/pages/ae/blankForm?st=${command.study.id}&sb=${command.participant.id}&cs=${command.adverseEventReportingPeriod.id}&ep=${command.adverseEventReportingPeriod.epoch.id}" />"><img src="<c:url value='/images/pdf.gif'></c:url>" border="0">&nbsp;</a></div>
						<p>
                            <c:if test="${empty command.study.aeTerminology.meddraVersion}"><tags:instructions code="instruction_ae_oae"/></c:if>
                            <c:if test="${not empty command.study.aeTerminology.meddraVersion}"><tags:instructions code="instruction_ae_oae_meddra"/></c:if>
                            <div class="instructions row" style="position:relative; top:-20px;"><div class="label"></div><div class="value">${command.adverseEventReportingPeriod.epoch.descriptionText}</div></div>
						</p>

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
            			<c:if test="${fn:length(command.adverseEventReportingPeriod.adverseEvents) > 0}">
	            			<c:forEach var="i" step="1" begin="0" end="${fn:length(command.adverseEventReportingPeriod.adverseEvents) - 1}">
   		         				<c:set var="j" value="${fn:length(command.adverseEventReportingPeriod.adverseEvents) - 1 - i}"/>
		            			<c:if test="${not command.adverseEventReportingPeriod.adverseEvents[j].solicited and not command.adverseEventReportingPeriod.adverseEvents[j].retired}">
        	    					<ae:oneRoutineAdverseEvent index="${j}" adverseEvent="${command.adverseEventReportingPeriod.adverseEvents[j]}" 
        	    						collapsed="true" enableDelete="true" isSolicited="false" hasOtherMeddra="${study.otherMeddra != null}"/>
           		 				</c:if>
            				</c:forEach>
            			</c:if>
					</chrome:box>
					<%-- End of Observed AE section --%>
					
					<%-- Begining of Solicited AE section --%>
					<c:if test="${command.havingSolicitedAEs}">
						<chrome:box title="Solicited Adverse Events" collapsable="true" id="solicitatedID" autopad="true">
							<p><tags:instructions code="instruction_ae_sae"/></p>
							<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            				<c:if test="${ae.solicited and not ae.retired}">
            					<ae:oneRoutineAdverseEvent index="${status.index}" adverseEvent="${ae}" collapsed="true" 
            					enableDelete="false" isSolicited="true" hasOtherMeddra="${study.otherMeddra != null}"/>
            				</c:if> 
            			</c:forEach>   
 						</chrome:box>
					</c:if>
					<%--  End of Solicited AE section --%>
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