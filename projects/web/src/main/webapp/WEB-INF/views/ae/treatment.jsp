<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <tags:labs labs="${command.assignment.labLoads}"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
		var descArray = new Array();
        function switchModified(checkbox) {
            var index = checkbox.id.substring(18)
            var hasModified = checkbox.checked
            enableModified(index, hasModified)
            var baseProp = "aeReport.treatmentInformation.courseAgents[" + index + "]."
            if (hasModified) {
                $(baseProp + "modifiedDose.amount").value = ""
                $(baseProp + "modifiedDose.units").value = $(baseProp + "dose.units").value
                //$(baseProp + "modifiedDose.route").value = $(baseProp + "dose.route").value
            } else {
                $A([
                    baseProp + "modifiedDose.amount",
                    baseProp + "modifiedDose.units"
                ]).each(function(input) {
                    $(input).value = "";
                })
            }
        }

        function enableModified(index, enable) {
            var baseProp = "aeReport.treatmentInformation.courseAgents[" + index + "]."
            $A([
                baseProp + "modifiedDose.amount",
                baseProp + "modifiedDose.units"
            ]).each(function(input) {
                $(input).disabled = !enable;
            })
        }

        function switchModifiedHandler(event) {
            switchModified(Event.element(event))
        }

        function renableModified() {
            var courseAgentCount = $$(".courseAgent").size
            for (var i = 0 ; i < courseAgentCount ; i++) {
                enableModified(index, true)
            }
        }

        function registerDoseModHandler(index) {
            var checkbox = $('dose-mod-checkbox-' + index)
            checkbox.observe('click', switchModifiedHandler)
            enableModified(index, checkbox.checked)
        }

        Element.observe(window, "load", function() {
            new ListEditor("courseAgent", createAE, "CourseAgent", {
                addParameters: [aeReportId],
                addFirstAfter: "single-fields",
                addCallback: function(index) {
                    registerDoseModHandler(index)
                    AE.registerCalendarPopups("courseAgent-" + index)
                },
                deletable: true
            }, 'aeReport.treatmentInformation.courseAgents')
            Event.observe("command", "submit", renableModified)
            $$('.dose-mod-checkbox').each(function(elt) {
                var index = elt.id.substring(18)
                registerDoseModHandler(index)
            })
			//push the description into the array
			<c:forEach items="${command.aeReport.study.treatmentAssignments}" var="ta">
        	descArray.push("${ta.escapedDescription}");
        	</c:forEach>			
			
			// treatment dropdown.
			$('aeReport.treatmentInformation.treatmentAssignment').observe("change", function(event){
				selIndex = $('aeReport.treatmentInformation.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					$('aeReport.treatmentInformation.treatmentAssignmentDescription').value = descArray[selIndex-1];
				}else{
					$('aeReport.treatmentInformation.treatmentAssignmentDescription').clear();
				}
			});            
            //radio button.
            $('description-type-default').observe("click" , function(event){
          		enableTac();  	
            });
            $('description-type-other').observe("click", function(event){
            	disableTac();
            });
            
            //set the initial value of the description text area. 
            if($('aeReport.treatmentInformation.treatmentDescription').value){
              	disableTac();
              	$('description-type-other').checked=true;
            }else{
            	enableTac();
            }
            
        })
        
        function enableTac(){
          $('aeReport.treatmentInformation.treatmentAssignment').enable();
          $('aeReport.treatmentInformation.treatmentDescription').clear();
          $('aeReport.treatmentInformation.treatmentDescription').disable();
        }
        function disableTac(){
          $('aeReport.treatmentInformation.treatmentAssignment').selectedIndex = 0;
          $('aeReport.treatmentInformation.treatmentAssignmentDescription').clear();
          $('aeReport.treatmentInformation.treatmentAssignment').disable();
          $('aeReport.treatmentInformation.treatmentDescription').enable();
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
        <jsp:attribute name="instructions">
            You are entering treatment information for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <div class="row">
                <div class="label">
                	<input type="radio" id="description-type-default" value="default" name="treatmentDescriptionType" checked="checked"/>
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[0]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[0]}" />
                </div>
            </div>
            <div class="row">
                <div class="label">
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[1]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[1]}" disabled="true"/>
                </div>
            </div>
            <div class="row">
                <div class="label">
                    <input type="radio" id="description-type-other" value="other" name="treatmentDescriptionType"/>
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[2]}"/>
                </div>
                <div class="value">
                   <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[2]}"/>
                </div>
            </div>
			<chrome:division title="Course information">
				<tags:renderRow field="${fieldGroups.treatmentInfo.fields[3]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[4]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[5]}"/>
            	<tags:renderRow field="${fieldGroups.treatmentInfo.fields[6]}"/>
			</chrome:division>
            
            
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
            <c:forEach items="${command.aeReport.treatmentInformation.courseAgents}" varStatus="status">
                <ae:oneCourseAgent index="${status.index}"/>
            </c:forEach>
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="courseAgent" label="Add a study agent" buttonCssClass="ae-list-editor-button"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>
