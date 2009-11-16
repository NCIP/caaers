<%@include file="/WEB-INF/views/taglibs.jsp"%>

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
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        Element.observe(window, "load", function() {
            new ListEditor("otherCause", createAE, "OtherCause", {
                addParameters: [aeReportId],
                addFirstAfter: "add-otherCause-button",
                deletable: true,
                addOnTop:true
            }, 'aeReport.otherCauses')
            
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
        textarea {
            width: 30em;
        }
    </style>
	<style>
		#main {
			/*top:55px;*/
		}
	</style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section10othercauses">
    <jsp:attribute name="instructions">
     <tags:instructions code="instruction_ae_otherCause" />
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
   		 <tags:listEditorAddButton divisionClass="otherCause" label="Add a cause" buttonCssClass="ae-list-editor-button"/>
        <c:forEach items="${command.aeReport.otherCauses}" varStatus="status">
            <ae:oneOtherCause index="${status.index}"/>
        </c:forEach>
        <ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
    </jsp:attribute>
    <jsp:attribute name="localButtons">
    </jsp:attribute>
</tags:tabForm>
</body>
</html>