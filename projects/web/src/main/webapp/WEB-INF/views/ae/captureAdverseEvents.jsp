<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ page import = "java.util.ArrayList" %>

<html>
 <head>
    <tags:includePrototypeWindow />
    <tags:javascriptLink name="dropdown_menu" />
    <tags:javascriptLink name="routing_and_review" />
    <tags:stylesheetLink name="ae"/>
    <tags:dwrJavascriptLink objects="captureAE,createStudy,createAE,routingAndReview"/>
    <tags:stylesheetLink name="aeTermQuery_box" />
    <tags:stylesheetLink name="slider" />
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="none">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments />
    		</div>
    	</jsp:attribute>
    </tags:slider>
	
    <style type="text/css">
        .selectdiv { width: 170px; overflow: hidden; }
        .shortselectdiv { width: 115px; overflow: hidden; }
        .selectbox { width: 165px; }
        .shortselectbox { width: 110px; }
        .selectboxClick { width: 750px; }
        .divNotes, .divOtherMeddra { font-size: 8pt; margin-top: 5px; float: left; }
        div.row div.value { font-weight: normal; white-space: normal; margin-left: 13em; }
        div.row div.label { width: 12em; }
        .reportingPeriodSelector {}
        .ae-section { padding-top: 5px; }
        #contentOf-observedID .even { background-color: #e8e8ff; }
        #contentOf-solicitatedID .odd { background-color: #ffe2ff; }
        .thterm { position: absolute; left: 10px; top: 10px; }
        #boxholder { position: relative; height: 210px; width: 100%; border-top: 1px solid #0066ff; padding-top: 10px; }
        #gradehead { position: absolute; left: 10px; top: 75px; }
        #attributionhead { position: absolute; left: 350px; top: 75px; }
        #hospitalizationhead { position: absolute; left: 500px; top: 75px; }
        #expectedhead { position: absolute; left: 10px; top: 145px; }
        #serioushead { position: absolute; left: 180px; top: 145px; }
        /*Grade*/
        .selectbox0 { position: absolute; left: 10px; top: 95px; max-width: 300px; }
        /*Attribution*/
        .selectbox1 { position: absolute; left: 350px; top: 95px; }
        /*Hospitalization*/
        .selectbox2 { position: absolute; left: 500px; top: 95px; }
        /*Expected*/
        .selectbox3 { position: absolute; left: 10px; top: 165px; }
        /*Serious*/
        .selectbox4 { position: absolute; left: 180px; top: 165px; }
        .delete { position: absolute; right: 20px; }
    </style>
 
 <script>
	var catSel = null;
	var RPCreatorClass = Class.create();
	var deleteIndex = 0;
    var hasChanges = false;

    var oldSignatures = new Array();
    var routingHelper = new RoutingAndReviewHelper(captureAE);

    Object.extend(RPCreatorClass.prototype, {
        initialize : function(rpDetailsDiv) {
            this.win = null;
            this.rpDetailsDiv = $(rpDetailsDiv);
        },

        addAdverseEvents:function(selectedTerms) {
            //find the terms that are not already added in the page
            var listOfTermIDs = new Array();

            $H(selectedTerms).keys().each(function(termID) {
                listOfTermIDs.push(termID);
            }.bind(this));


            //get the HTML to add from server
            var notAddedTerms = new Array();
            captureAE.addObservedAE(listOfTermIDs, function(ajaxOutput) {
                $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
                if ($('observedEmptyRow')) $('observedEmptyRow').remove();
                this.initializeOtherMeddraAutoCompleters(listOfTermIDs);
            }.bind(this));
        },

        isTermAgainAdded:function(termID) {
            //will tell wheter the term is already present
            $$('.eachRowTermID').each(function(aTerm) {
                if (termID == aTerm.value()) return true;
            });
            return false;
        },

        initializeOtherMeddraAutoCompleters: function(listOfTermIDs) {
            listOfTermIDs.each(function(aTermId) {
                var acEls = $$('om' + aTermId);
            }.bind(this));
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
// 		Event.observe('flow-update', 'click', checkSubmittedAEs);
 		
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
                hasChanges = true;

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

        var otherMeddraId = 'adverseEvents[' + index + '].lowLevelTerm-input';
		var verbatimId = 'adverseEvents[' + index + '].detailsForOther';
		var gradeId = 'adverseEvents[' + index + '].grade';
		var attributionId = 'adverseEvents[' + index + '].attributionSummary';
		var hospitalizationId = 'adverseEvents[' + index + '].hospitalization';
		var expectedId = 'adverseEvents[' + index + '].expected';
		var seriousId = 'adverseEvents[' + index + '].serious';

		signature = $(verbatimId).value + '$$' + // verbatim input
					document.getElementById(gradeId).value + '$$' + // grade input
					document.getElementById(attributionId).value + '$$' + // attribution input
					document.getElementById(hospitalizationId).value + '$$' + // hospitalization input
					document.getElementById(expectedId).value; // expected input

        // If otherMeddraId element exists append otherMeddra Value to the signature.
		if(document.getElementById(otherMeddraId) != null)
			signature = signature + '$$' + document.getElementById(otherMeddraId).value;// otherMeddra input
		else
			signature = signature + '$$';
		
		
		// If seriousId element exists append serious Value to the signature.
		if(document.getElementById(seriousId) != null)
			signature = signature + '$$' + document.getElementById(seriousId).value;
		else
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
					//alert('Entered callback method');
 	 	 			$('ae-section-' + deleteIndex).remove();
 	 	 			if($('ae-section-' + deleteIndex + '-submittedAERow'))
	 	 	 			$('ae-section-' + deleteIndex + '-submittedAERow').remove();
 	 	 			var repId = document.getElementById('command')._amendReportIds.value;
 	 	 			// Remove the image and .submittedAERow where repId is the one of the AE deleted. 
 	 	 			// This is needed to avoid re-amendment of the same report.
 	 	 			// First determine all the indexes that have the reportId = repId
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
					//alert('Number of aes to be handles  = ' + handleAeArr.length); 
					for(var j = 0; j < handleAeArr.length; j++)
					{
						$('ae-section-' + handleAeArr[j] + '-submitted-image').remove();
						$('ae-section-' + handleAeArr[j] + '-submittedAERow').remove();
					}
 	 	 			
 	 		});
 	 		
		}
	}

  
	

    // ----------------------------------------------------------------------------------------------------------------

    Event.observe(window, "load", function(e) {
        // Reset the value of flag AE.checkForModification to false.
        oldSignatures = getSignatures('.ae-section');
        
        Event.observe(window, "beforeunload", function(e) {
            if (checkForModificationsOnPage(e)) {
                e.returnValue = "You have unsaved information.";
            }
        });
        
    });

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
					<ae:reportingPeriodAEDetails />
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