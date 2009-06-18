<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 20em;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
        
      	div.row div.label { width: 13em; }
    	div.row div.value { margin-left: 14em;}
    </style>
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
			//var otherTextId= "aeReport.medicalDevice.otherDeviceOperator"
    		//var otherSelectId= "aeReport.medicalDevice.deviceOperator"
    	    //showOther(otherTextId,otherSelectId);
            //Event.observe("aeReport.medicalDevice.deviceOperator", "change", function() { showOther(otherTextId,otherSelectId) })

			if ( $('medicalDevice-0') != null ){
				$('add-medicalDevice-button').hide();
			}
			
            new ListEditor("medicalDevice", createAE, "MedicalDevice", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                	AE.registerCalendarPopups("medicalDevice-" + index)
                	$('add-medicalDevice-button').hide();
                	
                },
                removeCallback: function(index) {
                	$('add-medicalDevice-button').show();
                	
                },
                 deletable: true
            }, 'aeReport.medicalDevices')
        })
    
    	
    	function showOther(otherTextId,otherSelectId){
    			if ($(otherSelectId).options[2].selected){
    				$(otherTextId).disabled=false
    			}
    			else{
    				$(otherTextId).value=""
    				$(otherTextId).disabled=true
    			}
    		}
    	
    	/*
    	Event.observe(window, "load", function() {
    		var otherTextId= "aeReport.medicalDevice.otherDeviceOperator"
    		var otherSelectId= "aeReport.medicalDevice.deviceOperator"
    	    showOther(otherTextId,otherSelectId);
           Event.observe("aeReport.medicalDevice.deviceOperator", "change", function() { showOther(otherTextId,otherSelectId) })
        })
        */
    
    </script>
    <style type="text/css">
        textarea {
            width: 30em;
            height: 12em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section14device">
    <jsp:attribute name="instructions">
    <tags:instructions code="instruction_ae_device" />
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.medicalDevices}" varStatus="status">
            <ae:oneMedicalDevice index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="medicalDevice" label="Add a Medical device" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>