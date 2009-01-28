<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Enter basic AE information</title>
     <style type="text/css">
		.sae {
			color:green;
		}
	 </style>	
    <tags:stylesheetLink name="ae"/>
   
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <%-- <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="aeReport"/>
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider> --%>
     <script type="text/javascript">
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
    				<th scope="col" align="left"><b>Hospitalization or prolongation of existing hospitalization?</b> </th>
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
