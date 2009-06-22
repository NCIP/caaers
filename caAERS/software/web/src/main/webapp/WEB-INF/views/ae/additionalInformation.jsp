<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        div.row div.label { width: 17em; } 
		div.row div.value, div.row div.extra { margin-left: 18em; }
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
    	
    	Event.observe(window, "load", function() {
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section17attachments">
    <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_additionalInfo" />
    </jsp:attribute>
    <jsp:attribute name="singleFields">
    	<div class="leftpanel">
    	<c:forEach items="${fieldGroups.desc.fields}" var="field" begin="0" end="5">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    	
    	</div>
    	<div class="rightpanel">
    	<c:forEach items="${fieldGroups.desc.fields}" var="field" begin="6" end="11">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    	
    	</div>
        <div id="spacer" style="clear: both;"> </div>
        <div class="row">
         <div class="label"><tags:renderLabel field="${fieldGroups.desc.fields[12]}" /></div>
         <div class="value">
         	<tags:renderInputs field="${fieldGroups.desc.fields[12]}"/>
         </div>

        </div>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>