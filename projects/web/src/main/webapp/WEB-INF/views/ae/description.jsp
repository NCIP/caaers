<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
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
    
    <style type="text/css">
        
        div.row div.label { width: 18em;} 
		div.row div.value, div.row div.extra { margin-left: 19em; }
    </style>
    
    <script language="JavaScript">
    	var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');

        Event.observe(window, "load", function() {
			if($("aeReport.responseDescription.studyDrugInterrupted")){
				Event.observe("aeReport.responseDescription.studyDrugInterrupted", "change", function() { viewSelection() })
				viewSelection();
			}
			
			 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
			
		});
		
		function viewSelection(){
			if ($('aeReport.responseDescription.studyDrugInterrupted').options[1].selected){
                $('aeReport.responseDescription.reducedDose').removeAttribute('readOnly')
                $('aeReport.responseDescription.daysNotGiven').removeAttribute('readOnly')
                $('aeReport.responseDescription.reducedDate').removeAttribute('readOnly')
			}else{
				$('aeReport.responseDescription.reducedDose').setAttribute('readOnly',true);
                $('aeReport.responseDescription.daysNotGiven').setAttribute('readOnly',true);
                $('aeReport.responseDescription.reducedDate').setAttribute('readOnly',true);
                
                $('aeReport.responseDescription.reducedDose').value=""
                $('aeReport.responseDescription.daysNotGiven').value=""
                $('aeReport.responseDescription.reducedDate').value=""
                
			}
		}

        function pageSetup() {
            Event.observe($('aeReport.responseDescription.presentStatus'), "change", function() {
                checkPresense();
            });
        }

        function checkPresense() {
            if ($('aeReport.responseDescription.presentStatus')) {
                    if ($('aeReport.responseDescription.presentStatus').value == "DEAD") {
                        $('aeReport.responseDescription.autopsyPerformed-row').show();
                        $('aeReport.responseDescription.causeOfDeath-row').show();
                    } else {
                        $('aeReport.responseDescription.autopsyPerformed-row').hide();
                        $('aeReport.responseDescription.causeOfDeath-row').hide();
                    }
            }

            if ($('aeReport.responseDescription.presentStatus')) {
                    var c = $('aeReport.responseDescription.presentStatus').value;
                    if (c == "RECOVERED_WITH_SEQUELAE" || c == "RECOVERED_WITHOUT_SEQUELAE" || c == "DEAD") {
                        $('aeReport.responseDescription.recoveryDate-row').show();
                    } else {
                        $('aeReport.responseDescription.recoveryDate-row').hide();
                    }
            }
        }

        checkPresense.defer();
        pageSetup.defer();
    </script>
	<!--[if lte IE 6]>
<style>
		#reporter-summary {
			margin-top:80px;
			margin-bottom:0;
		}
	</style>
<![endif]-->
    
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section5describeevent">
    <jsp:attribute name="instructions">
    <tags:instructions code="instruction_ae_description" />
    </jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.desc.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>

       	<c:forEach items="${fieldGroups.DCP_INFO.fields}" var="field">
           	<tags:renderRow field="${field}"/>
       	</c:forEach>
		
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
