<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Enter basic AE information</title>
     <style type="text/css">
		.sae {
			color:green;
		}
	 </style>	
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
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        var descArray = new Array();

        var LowLevelTerm = Class.create()
        Object.extend(LowLevelTerm.prototype, {
            initialize: function(index, lowLevelTermCode) {
                this.index = index
                var cmProperty = "aeRoutineReport.adverseEvents[" + index + "]";
                this.lowLevelTermProperty = cmProperty + ".adverseEventMeddraLowLevelTerm.lowLevelTerm"
                //this.otherProperty = cmProperty + ".other"

                if (lowLevelTermCode) $(this.lowLevelTermProperty + "-input").value = lowLevelTermCode

      
                AE.createStandardAutocompleter(
                    this.lowLevelTermProperty, this.termPopulator.bind(this),
                    function(lowLevelTerm) { return lowLevelTerm.fullName })

                //this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchLowLevelTermsByCode(text, function(values) {
                    autocompleter.setChoices(values)
                })
            }

        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" varStatus="status" var="aeLowLevelTerm">
            	<c:if test="${command.aeRoutineReport.adverseEvents[status.index].report == null }" >
		            new LowLevelTerm(${status.index}, '${aeLowLevelTerm.adverseEventMeddraLowLevelTerm.lowLevelTerm.fullName}')
           		</c:if>
            </c:forEach>
            
             new ListEditor("ae-section", createAE, "RoutineAeMeddra", {
             	addFirstAfter: "koi",
                addParameters: [aeReportId],
                addCallback: function(nextIndex) {
                    new LowLevelTerm(nextIndex);
                }
             }) 
             
             //push the description into the array
			<c:forEach items="${command.aeRoutineReport.study.treatmentAssignments}" var="ta">
        		descArray.push("${ta.escapedDescription}");
        	</c:forEach>	
        	
        	// treatment dropdown.
			$('aeRoutineReport.treatmentAssignment').observe("change", function(event){
				selIndex = $('aeRoutineReport.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					$('aeRoutineReport.treatmentAssignmentDescription').value = descArray[selIndex-1];
				}else{
					$('aeRoutineReport.treatmentAssignmentDescription').clear();
				}
			}); 
			
			 //set the initial value of the description text area. 
            	selIndex = $('aeRoutineReport.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					$('aeRoutineReport.treatmentAssignmentDescription').value = descArray[selIndex-1];
				}else{
					$('aeRoutineReport.treatmentAssignmentDescription').clear();
				}

			 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
            	<c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
	 	          	routingHelper.retrieveReviewCommentsAndActions('${report.id}');
 	          	</c:forEach>
            }
        }) 
    </script>
    <style type="text/css">
    
	div.row div.label {
    	width: 16em;
    }
    div.row div.value {
    	margin-left: 18em;
    }
	</style>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enterbasicaeinformation">
        <jsp:attribute name="instructions">
            You are entering AEs  for ${participantSummaryLine} on
            ${studySummaryLine}.
             <div class="row">
        	<div class="label">
                MedDRA Version	
            </div>
            <div class="value">
            	${term}
            </div>
        </div>    
        </jsp:attribute>
        <jsp:attribute name="singleFields">
        	
             <chrome:division title="Periods of Observation " id="observation_period">
            	<div class="row">
            	<div class="label"><tags:renderLabel field="${fieldGroups.report.fields[0]}"/></div>
            	<div class="value">
                	<tags:renderInputs field="${fieldGroups.report.fields[0]}"/>
                	&nbsp;&nbsp;&nbsp;<strong><tags:renderLabel field="${fieldGroups.report.fields[1]}"/></strong>
                	&nbsp;&nbsp;&nbsp;<tags:renderInputs field="${fieldGroups.report.fields[1]}"/>
            	</div>
        		</div>
            </chrome:division>
            <chrome:division title="Treatment Assignment Code" id="treatment_assignment_code">
            	<div class="row">
            	<div class="label"><tags:renderLabel field="${fieldGroups.report.fields[2]}"/></div>
            	<div class="value">
                	<tags:renderInputs field="${fieldGroups.report.fields[2]}"/>
            	</div>
        		</div>
        		
        		<div class="row" >
        		<div class="label">
               	 	Description	
           		 </div>
            	<div class="value">
            		<textarea id="aeRoutineReport.treatmentAssignmentDescription" rows="2" cols="65" name="fake" disabled="true"></textarea>  
            	</div>
       			</div>
            </chrome:division>
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
        	<center>
        	<table id="test" width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Term:</b> </th>
    				<th ><b><tags:requiredIndicator/>Grade:</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution:</b> </th>
    				<th scope="col" align="left"><b>Did AE cause hospitalization?</b> </th>
    				<th scope="col" align="left"><b>Expected:</b> </th>
    			</tr>
    			<tr id="koi" />
    			
            	<c:forEach items="${command.aeRoutineReport.adverseEvents}" varStatus="status">
                	<ae:oneMeddraTerm index="${status.index}"/>
            	</c:forEach>
            	</table>
            	</center>
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="ae-section" label="Add another AE" buttonCssClass="ae-list-editor-button"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>
