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
    <tags:stylesheetLink name="ae"/>
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 40em;
            white-space: nowrap;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
        div.row div.label { width: 15em; } 
		div.row div.value, div.row div.extra { margin-left: 16em; }
    </style>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    
    <tags:javascriptLink name="routing_and_review" />
	<tags:stylesheetLink name="slider" />
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
     	var routingHelper = new RoutingAndReviewHelper(createAE);
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var LowLevelTerm = Class.create()
        Object.extend(LowLevelTerm.prototype, {
            initialize: function(index, lowLevelTermCode) {
                this.index = index
                var cmProperty = "aeReport.adverseEvents[" + index + "]";
                this.lowLevelTermProperty = cmProperty + ".adverseEventMeddraLowLevelTerm.lowLevelTerm"
                this.otherProperty = cmProperty + ".other"

                if (lowLevelTermCode) $(this.lowLevelTermProperty + "-input").value = lowLevelTermCode

      
                AE.createStandardAutocompleter(
                    this.lowLevelTermProperty, this.termPopulator.bind(this),
                    function(lowLevelTerm) { return lowLevelTerm.fullName })

                //this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
			    createAE.matchLowLevelTermsByCode(${command.assignment.studySite.study.aeTerminology.meddraVersion.id},text, function(values) {
                    autocompleter.setChoices(values);
                })
            }

        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status" var="aeLowLevelTerm">
            new LowLevelTerm(${status.index}, '${aeLowLevelTerm.adverseEventMeddraLowLevelTerm.lowLevelTerm.fullName}')
            </c:forEach>
            
             new ListEditor("ae-section", createAE, "AdverseEventMeddra", {
                addParameters: [aeReportId],
                reorderable: true,
                deletable: true,
                minimizeable: false,
                addCallback: function(nextIndex) {
                    new LowLevelTerm(nextIndex);
                },
                reorderCallback : function(original, target){
                    $$('span.primary-indicator').each(function(el, indx){
                        if(indx == 0) el.innerHTML='[Primary]';
                        else el.innerHTML = '';
                    });
                 }
             })    
			
			 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        }) 
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="ae_captureRoutine">
        <jsp:attribute name="instructions">
           <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status">
                <ae:oneAdverseEventMeddra index="${status.index}" collapsed="${status.index gt 0}"/>
            </c:forEach>
        </jsp:attribute>

        <jsp:attribute name="localButtons">
         <c:if test="${command.additionAllowed}">
            <tags:listEditorAddButton divisionClass="ae-section" label="Add another AE" buttonCssClass="ae-list-editor-button"/> 
       	</c:if>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>
