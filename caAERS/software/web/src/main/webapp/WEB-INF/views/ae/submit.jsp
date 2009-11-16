<%@ include file="/WEB-INF/views/taglibs.jsp" %>

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

        function fireAction(action, selected) {

            document.getElementById('command')._target.name = '_noname';
            document.viewReport._action.value = action;
            document.viewReport._selected.value = selected;
            document.viewReport.submit();
        }

        function withdrawReport(aeReportId, reportId) {
            try {
                createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
                    ajaxResult = result;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        //AE.hideIndicator("notify-indicator-" + aeReportId)
                        var statusColumn = $('report-status')
                        var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";
                        Element.update(statusColumn, statusColumnData)
                    }
                });
            } catch(e) {
                caaersLog(e);
            }
        }

        Event.observe(window, "load", function() { 
    	 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	
 	          	<c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
	 	          	routingHelper.retrieveReviewCommentsAndActions('${report.id}');
 	          	</c:forEach>
 	          	
 	          	<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
					<c:if test="${report.status ne 'WITHDRAWN' and report.status ne 'REPLACED' and report.status ne 'AMENDED' and report.status ne 'COMPLETED'}">
		 	          	routingHelper.updateWorkflowActions('${report.id}');
		 	        </c:if>
		 	    </c:forEach>
            } else {
				createDropDowns();
			}
    });
       
       
    function updatePhysicianSignOff(){
        try {
            createAE.updatePhysicianSignOff($('aeReport.physicianSignOff').checked, function(output) {
                ajaxResult = output;
                if (ajaxResult.error) {
                    caaersLog(ajaxResult.errorMessage);
                } else {
                    $('report-validation-section').innerHTML = output.htmlContent;
                    if (${command.workflowEnabled == true}) {
						routingHelper.updateWorkflowActions.bind(routingHelper)();
					}
					createDropDowns();
                }
            });
        } catch(e) {
            caaersLog(e)
        }
    }   
    
	function executeAction(reportId, url, aeReportId, submissionUrl){
		var actions = $("actions-" + reportId);
		for ( i=0; i < actions.length; i++) {
			if (actions.options[i].selected && actions.options[i].value != "none") {
				if(confirm('Are you sure you want to ' + actions.options[i].title + '?')){
					switch (actions.options[i].value) {
						case "notifyPSC": notifyPsc(aeReportId); break;
						case "submit": doAction(actions.options[i].value, aeReportId, reportId); break;
						case "withdraw": doAction(actions.options[i].value, aeReportId, reportId);  updateDropDownAfterWithdraw(reportId); break;
						case "amend": doAction(actions.options[i].value, aeReportId, reportId);  break;
						case "adeers": window.open(submissionUrl, "_blank");  break;
						default: window.open(url + "&format="+ actions.options[i].value,"_self");
					}
				}else{
					return false;
				}
			}
		}
	}
	
	function doAction(action, aeReportId, reportId) {
        var ajaxResult = null;
        try {
            if (action == 'withdraw') {
                createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
                    ajaxResult = result;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        //AE.hideIndicator("notify-indicator-" + aeReportId)
                        var statusColumn = $("report-status-" + reportId);
                        var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";

                        //var optionColumn = $("action"+reportId)
                        //optionColumnData = $("action"+reportId).innerHTML;

                        Element.update(statusColumn, statusColumnData)
                        //Element.update(optionColumn, optionColumnData)
                    }
                });
            } else if (action == 'submit') {
                var url = '<c:url value="/pages/ae/submitReport?from=list" />' + '&aeReport=' + aeReportId + '&reportId=' + reportId;
                window.location = url;
            } else if (action == 'amend') {
                var url = '<c:url value="/pages/ae/edit"/>' + '?aeReport=' + aeReportId + '&reportId=' + reportId + '&action=amendReport';
                window.location = url;
            }
        } catch(e) {
            caaersLog(e)
        }
	}
	
	function updateDropDownAfterWithdraw(reportId) {
		var select = $('actions-' + reportId);
        
		for (var i = (select.options.length-1); i>=0; i--) {
			if ((select.options[i].value == 'withdraw') || (select.options[i].value == 'submit')) {
				select.options[i] = null;
			}
		}
	}
	
	function advanceWorkflow(reportId, value){
        try {
        	var sboxId = 'sliderWFAction-' + reportId;
            var sbox = $(sboxId);
		
            if (confirm('Are you sure you want to take the action - ' + value + ' ?')) {
            	var sboxIndicatorId = 'sliderWFAction-indicator-' + reportId;
                var sboxIndicator = $(sboxIndicatorId);
                var selected_sbox_value = value;
                sboxIndicator.style.display = '';
                createAE.advanceWorkflow(reportId, value, function(ajaxOutput) {
                    var ajaxResult = ajaxOutput;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        routingHelper.updateSelectBoxContent(reportId, sbox, sboxIndicator, ajaxOutput.objectContent);
                        if (${command.aeReport.physicianSignOffRequired}) {
                            if (selected_sbox_value == 'Approve Report' || selected_sbox_value == 'Request Additional Information') {
                                if (selected_sbox_value == 'Approve Report') {
                                    $('aeReport.physicianSignOff').checked = true;
                                }
                                else {
                                    $('aeReport.physicianSignOff').checked = false;
                                }
                                createAE.refreshSubmitReportValidationSection(function(output) {
                                    var ajaxResult = output;
                                    if (ajaxResult.error) {
                                        caaersLog(ajaxResult.errorMessage);
                                    } else {
										caaersLog(output.htmlContent);
                                        //$('report-validation-section').innerHTML = output.htmlContent;
                                        routingHelper.retrieveReviewComments(reportId);
                                    }
                                });
                            }
                        }
                    }
                });
            } else {
                
            }
        } catch(e) {
            caaersLog(e);
        }
		
	}

	function createDropDowns() {
		jQuery(".fg-button").each(function(){
			id = jQuery(this).attr("id");
			options = "options-" + id;
			jQuery("#"+id).menu({
				content: jQuery("#"+options).html(),		
				maxHeight: 180,
				width: 230,
                positionOpts: {
                    directionV: 'down',
                    posX: 'right',
                    posY: 'bottom',
                    offsetX: 0,
                    offsetY: 0
                },
                showSpeed: 300
			});
		});
	}
    </script>
    <style type="text/css">
        td.completion-messages p {
            margin-top: 0;
        }
        td.completion-messages h4 {
            padding: 6px 0 2px 0;
        }
        td.completion-messages ul {
            padding: 0;
            margin: 0;
        }
        td.completion-messages ul li {
            padding: 0;
            margin: 0;
            margin-left: 1em;
        }
    </style>
</head>
<body>
		
        
<tags:tabForm formName="viewReport" tab="${tab}" flow="${flow}" pageHelpAnchor="section18submit" hideBox="true">
	
	<jsp:attribute name="singleFields">
    	<input type="hidden" name="_action" value="">
        <input type="hidden" name="_selected" value="">

		<c:if test="${command.aeReport.physicianSignOffRequired}">
			<chrome:box title="Physician signoff">
			 	<div class="row">
   		     		<div class="label">
			        	<ui:checkbox path="aeReport.physicianSignOff" onclick="javascript:updatePhysicianSignOff();"></ui:checkbox>
    	   			</div>
      	 	 	<div class="value">
       		  		<b>I certify that this report has been reviewed and approved by a physician or his/her medically certified designee responsible for the care of this patient.</b>
        		</div>
        	</div>
			</chrome:box>
	       
        </c:if>
        <chrome:box title="${tab.shortTitle}" >
        <ae:submitReportValidation/>
     	<input type="hidden" name="_finish"/>
	
        </chrome:box>
	<ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
    </jsp:attribute>    
    <jsp:attribute name="tabControls">
  		<div class="content buttons autoclear">
    		<div class="local-buttons"></div>
	    	<div class="flow-buttons">
	        <span class="prev">
	        		<tags:button id="flow-prev" color="blue" value="Back" icon="Back" cssClass="tab9"></tags:button>
	        </span>
	        <%--<span class="next">
	            <input type="image" alt="save »" value="Go to Manage Reports " id="flow-next" src="<c:url value="/images/blue/go_to_manage_reports_btn.png" />"/>
	        </span>--%>
	    	</div>
		</div>    
	</jsp:attribute>
</tags:tabForm>
</body>
</html>