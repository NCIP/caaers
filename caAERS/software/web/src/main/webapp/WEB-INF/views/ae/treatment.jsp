<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}" 
		display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments />
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    <script type="text/javascript">
    var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
    var descArray = new Array();
    
    Element.observe(window, "load", function() {
            
            //push the description into the array
			<c:forEach items="${command.aeReport.study.treatmentAssignments}" var="ta">
        	descArray.push("${ta.escapedDescription}");
        	</c:forEach>
        	
        	// Add a new option "Other" the treatment Assignment select box.
        	var selBox = $('aeReport.treatmentInformation.treatmentAssignment');
        	var opt = new Option("Other", "");
            selBox.options.add(opt);
            
            if(${validOtherTreatmentDescription}){
            	// Here there is a valid treatmentInformation.treatmentDescription
            	// So "Other" is selected by default in the TAC dropdown.
            	// Also the editable textarea for the treatmentInformation.treatmentDescription is rendered visible and the
            	// uneditable textarea for treatmentdescription is hidden.
            	var selectElement = $('aeReport.treatmentInformation.treatmentAssignment');
            	for(var i= 1; i < selectElement.options.length; i++){
            		if(selectElement.options[i].value == '')
            			selectElement.selectedIndex = i;
            	}
            	$('static-treatment-desc').style.display = 'none';
            }else{
            	// Here this is no valid treatmentInformation.treatmentDescription
            	// So the correct tac will be selected in the TAC dropdown.
            	// Also the editable textarea for the treatmentInformation.treatmentDescription is hidden while the uneditable
            	// textarea for the treatmentdescription is rendered visible with correct description value
            	$('variable-treatment-desc').style.display = 'none';
            }
            
            
            // treatment dropdown.
			$('aeReport.treatmentInformation.treatmentAssignment').observe("change", function(event){
				selIndex = $('aeReport.treatmentInformation.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					var selectElement = $('aeReport.treatmentInformation.treatmentAssignment');
					if(selectElement.options[selIndex].value == '')
						refreshWithOtherTACDescription();
					else
						refreshWithValidTAC(selIndex - 1);
				}else{
					$('aeReport.treatmentInformation.treatmentAssignmentDescription').clear();
					$('static-treatment-desc').style.display = '';
					$('variable-treatment-desc').style.display = 'none';
				}
			});   
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
            
    })
    
    
    
    function refreshWithOtherTACDescription(){
    	if(${validOtherTreatmentDescription})
            $('aeReport.treatmentInformation.treatmentDescription').value = '${command.aeReport.treatmentInformation.treatmentDescription}';
        else
            $('aeReport.treatmentInformation.treatmentDescription').value = '${courseTreatmentAssignmentDesc}';
        $('variable-treatment-desc').style.display = '';
        $('aeReport.treatmentInformation.treatmentAssignmentDescription').clear();
        $('static-treatment-desc').style.display = 'none';
    }
            
    function refreshWithValidTAC(descArrayIndex){
    	$('aeReport.treatmentInformation.treatmentAssignmentDescription').value = descArray[descArrayIndex];
    	$('aeReport.treatmentInformation.treatmentDescription').clear();
    	$('static-treatment-desc').style.display = '';
    	$('variable-treatment-desc').style.display = 'none';
    }
    </script>
    <style type="text/css">
        div.row div.label {
            width: 21em;
        }
        div.row div.value {
            margin-left: 22em;
        }
    </style>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11courseandagent">
        <jsp:attribute name="singleFields">
        	<chrome:division title="Treatment Information">
        		<div class="row">
    	            <div class="label">
            	        <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[0]}"/>
     	           </div>
        	        <div class="value">
            	        <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[0]}" />
             	   </div>
      	    	</div>
        	    <div class="row" id="static-treatment-desc">
 	               <div class="label">
    	                <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[1]}"/>
        	        </div>
            	    <div class="value">
                	    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[1]}" disabled="true"/>
     	           </div>
       		    </div>
       		    <div class="row" id="variable-treatment-desc">
     	           <div class="label">
       	                <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[2]}"/>
       		       </div>
               	   <div class="value">
                   	    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[2]}"/>
                   </div>
                </div>
        	</chrome:division>
        	<chrome:division title="Course Information">
				<tags:renderRow field="${fieldGroups.treatmentInfo.fields[3]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[4]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[5]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[6]}"/>
			</chrome:division>
			<ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
        </jsp:attribute>
        
        <jsp:attribute name="localButtons"/>
    </tags:tabForm>
</body>
</html>