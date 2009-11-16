<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}" reports="${command.selectedReportsAssociatedToWorkflow}" 
		display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}" workflowType="report">
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
            descArray.push(''); //please-select
			<c:forEach items="${command.aeReport.study.treatmentAssignments}" var="ta">
        	descArray.push("${ta.escapedDescription}");
        	</c:forEach>
        	
        	// Add a new option "Other" the treatment Assignment select box.
        	var selBox = $('aeReport.treatmentInformation.treatmentAssignment');
        	var opt = new Option("Other", "");
            selBox.options.add(opt);
            
            //if there is valid other description, select Other. 
            if(${validOtherTreatmentDescription}){
            	selBox.selectedIndex = selBox.options.length - 1;
				selBox.removeClassName('mandatory')
            }
          
            
            
            // treatment dropdown.
			selBox.observe("change", function(event){

				var selIndex = selBox.selectedIndex;

				if(selIndex > 0 && selIndex == (selBox.options.length -1)){
					refreshWithOtherTACDescription(); //Other
					selBox.removeClassName('required')
					selBox.removeClassName('mandatory')
					Event.stop(event);
				} else {
					refreshWithValidTAC(selIndex); //TAC or Please select
				}
				
				
			});   
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
            	<c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
	 	          	routingHelper.retrieveReviewCommentsAndActions('${report.id}');
 	          	</c:forEach>
            }
            
    })
    
    
    
    function refreshWithOtherTACDescription(){
        var otherTACDescription = "<c:out value="${command.aeReport.treatmentInformation.treatmentDescription}" escapeXml="true"/>";
        $('aeReport.treatmentInformation.treatmentDescription').value = otherTACDescription;
        $('static-treatment-desc').hide();
        $('variable-treatment-desc').show();
    }
            
    function refreshWithValidTAC(descArrayIndex){
    	$('aeReport.treatmentInformation.treatmentAssignmentDescription').value = descArray[descArrayIndex];
    	$('aeReport.treatmentInformation.treatmentDescription').clear();
    	$('static-treatment-desc').show();
        $('variable-treatment-desc').hide();
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
        	    <div class="row" id="static-treatment-desc" style="${validOtherTreatmentDescription ? 'display:none' : '' }">
 	               <div class="label">
    	                <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[1]}"/>
        	        </div>
            	    <div class="value">
                	    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[1]}" disabled="true"/>
     	           </div>
       		    </div>
       		    <div class="row" id="variable-treatment-desc" style="${validOtherTreatmentDescription ? '' :'display:none'}" >
     	           <div class="label">
       	                <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[2]}"/>
       		       </div>
               	   <div class="value">
                   	    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[2]}" cssClass="${validOtherTreatmentDescription ? 'valueOK' : ''}"/>
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