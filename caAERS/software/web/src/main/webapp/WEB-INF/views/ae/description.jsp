<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

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
            	<c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
	 	          	routingHelper.retrieveReviewCommentsAndActions('${report.id}');
 	          	</c:forEach>
            }

            checkPresense.defer();
            pageSetup.defer();
			
        });
		
		function viewSelection(){
			if ($('aeReport.responseDescription.studyDrugInterrupted').options[1].selected){
                if($('aeReport.responseDescription.reducedDose')) $('aeReport.responseDescription.reducedDose').removeAttribute('readOnly')
                if($('aeReport.responseDescription.daysNotGiven')) $('aeReport.responseDescription.daysNotGiven').removeAttribute('readOnly')
                if($('aeReport.responseDescription.reducedDate')) $('aeReport.responseDescription.reducedDate').removeAttribute('readOnly')
			}else{
				if($('aeReport.responseDescription.reducedDose')){
					 $('aeReport.responseDescription.reducedDose').setAttribute('readOnly',true);
					 $('aeReport.responseDescription.reducedDose').value=""
				}
                if($('aeReport.responseDescription.daysNotGiven')){
                    $('aeReport.responseDescription.daysNotGiven').setAttribute('readOnly',true);
                    $('aeReport.responseDescription.daysNotGiven').value=""
                }
                if($('aeReport.responseDescription.reducedDate')){
                	$('aeReport.responseDescription.reducedDate').setAttribute('readOnly',true);
                    $('aeReport.responseDescription.reducedDate').value=""
                    
                }
                
			}
		}

        function pageSetup() {
            if ($('aeReport.responseDescription.presentStatus')) {
                Event.observe($('aeReport.responseDescription.presentStatus'), "change", function() {
                    checkPresense();
                });
            }
        }

        function checkPresense() {
            if ($('aeReport.responseDescription.presentStatus')) {
                    if ($('aeReport.responseDescription.presentStatus').value == "DEAD") {
                        if ($('aeReport.responseDescription.autopsyPerformed-row')) $('aeReport.responseDescription.autopsyPerformed-row').show();
                        if ($('aeReport.responseDescription.causeOfDeath-row')) $('aeReport.responseDescription.causeOfDeath-row').show();
                    } else {
                        if ($('aeReport.responseDescription.autopsyPerformed-row')){
                             $('aeReport.responseDescription.autopsyPerformed-row').hide();
                             $('aeReport.responseDescription.autopsyPerformed').checked = false;
                        }
                        if ($('aeReport.responseDescription.causeOfDeath-row')){
                             $('aeReport.responseDescription.causeOfDeath-row').hide();
                             $('aeReport.responseDescription.causeOfDeath').clear();
                        }
                    }
            }

            if ($('aeReport.responseDescription.presentStatus')) {
                    var c = $('aeReport.responseDescription.presentStatus').value;
                    if (c == "RECOVERED_WITH_SEQUELAE" || c == "RECOVERED_WITHOUT_SEQUELAE" || c == "DEAD") {
                        if ($('aeReport.responseDescription.recoveryDate-row')) $('aeReport.responseDescription.recoveryDate-row').show();
                    } else {
                        if ($('aeReport.responseDescription.recoveryDate-row')) {
                            $('aeReport.responseDescription.recoveryDate-row').hide();
                            $('aeReport.responseDescription.recoveryDate').clear();
                        }
                    }
            }
        }

 
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
		<ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
