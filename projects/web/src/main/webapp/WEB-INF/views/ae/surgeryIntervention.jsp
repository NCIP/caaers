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
     
     <style type="text/css">
    	div.row div.label { width: 15em; }
    	div.row div.value { margin-left: 16em;}
    	
    	 textarea {
            width: 20em;
            height: 5em;
        }
    	
    </style>
    
    <script type="text/javascript">
    	var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
    	var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
    	
    	
    	 var EnterAnatomicSite = Class.create()
        Object.extend(EnterAnatomicSite.prototype, {
            initialize: function(index, interventionSiteName) {
                this.index = index
                var cmProperty = "aeReport.surgeryInterventions[" + index + "]";
                this.interventionSiteProperty = cmProperty + ".interventionSite"

                if (interventionSiteName) $(this.interventionSiteProperty + "-input").value = interventionSiteName
						
       
                AE.createStandardAutocompleter(
                    this.interventionSiteProperty, this.termPopulator.bind(this),
                    function(interventionSiteCondition) { return interventionSiteCondition.name })
                    initSearchField()
                },

            termPopulator: function(autocompleter, text) {
                createAE.matchInterventionSites(text, function(values) {
                    autocompleter.setChoices(values)
                })
            }
        })
    	

        Element.observe(window, "load", function() {
        	
        	<c:forEach items="${command.aeReport.surgeryInterventions}" varStatus="status" var="surgeryIntervention">
            new EnterAnatomicSite(${status.index}, '${surgeryIntervention.interventionSite.name}')
            </c:forEach>
           

			if ( $('surgeryIntervention-0')){
				$('add-surgeryIntervention-button').hide();
			}
			
            new ListEditor("surgeryIntervention", createAE, "SurgeryIntervention", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                	AE.registerCalendarPopups("surgeryIntervention-" + index)
                	new EnterAnatomicSite(index);
                	$('add-surgeryIntervention-button').hide();
                	
                },
                removeCallback: function(index) {
                	$('add-surgeryIntervention-button').show();
                	
                },
                 deletable: true
            }, 'aeReport.surgeryInterventions')
            
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        })
    
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section13surgery">
    <jsp:attribute name="instructions">
    <tags:instructions code="instruction_ae_surgery" />   
    </jsp:attribute>
   <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.surgeryInterventions}" varStatus="status">
            <ae:oneSurgeryIntervention index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="surgeryIntervention" label="Add a Surgery intervention" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>