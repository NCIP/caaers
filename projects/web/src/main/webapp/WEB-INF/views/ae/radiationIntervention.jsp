<%@ include file="/WEB-INF/views/taglibs.jsp" %>

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
    	var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        Element.observe(window, "load", function() {
        	//only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        
			if ( $('radiationIntervention-0')){
				$('add-radiationIntervention-button').hide();
			}
			
			
            new ListEditor("radiationIntervention", createAE, "RadiationIntervention", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                	AE.registerCalendarPopups("radiationIntervention-" + index)
                	$('add-radiationIntervention-button').hide();
                	
                },
                removeCallback: function(index) {
                	$('add-radiationIntervention-button').show();
                	
                },
                deletable: true
            }, 'aeReport.radiationInterventions')
        })
    
    </script>
    <style type="text/css">
    	div.row div.label { width: 16em; }
    	div.row div.value { margin-left: 17em;}

        textarea {
            width: 20em;
            height: 5em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section12radiation">
    <jsp:attribute name="instructions">
    	<tags:instructions code="instruction_ae_radiation" />
    </jsp:attribute>
   <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.radiationInterventions}" varStatus="status">
            <ae:oneRadiationIntervention index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="radiationIntervention" label="Add a radiation" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>