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
    	 $('flow-next').value="Go to Manage Reports ";	 
    	 
    	 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
 	          	routingHelper.updateWorkflowActions.bind(routingHelper)();
            }
    })
       
       
    function updatePhysicianSignOff(){
        try {
            createAE.updatePhysicianSignOff($('aeReport.physicianSignOff').checked, function(output) {
                ajaxResult = output;
                if (ajaxResult.error) {
                    caaersLog(ajaxResult.errorMessage);
                } else {
                    $('report-validation-section').innerHTML = output.htmlContent;
                    if (${command.workflowEnabled == true})
                        routingHelper.updateWorkflowActions.bind(routingHelper)();
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
				if(confirm('Are you sure you want to take the action - ' + actions.options[i].text)){
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
                        var statusColumn = $("report-status-" + reportId)
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
	
	function advanceWorkflow(){
        try {
            var sbox = $('sliderWFAction');
            var sbox = $('sliderWFAction');
		if(sbox.value == '' || sbox.value == 'Please select') return;
            if (confirm('Are you sure you want to take the action - ' + sbox.value)) {
                var sboxIndicator = $('sliderWFAction-indicator');
                var selected_sbox_value = sbox.value;
                sbox.disable();
                sboxIndicator.style.display = '';
                createAE.advanceWorkflow(sbox.value, function(ajaxOutput) {
                    var ajaxResult = ajaxOutput;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        routingHelper.updateSelectBoxContent(sbox, sboxIndicator, ajaxOutput.objectContent);
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
                                        $('report-validation-section').innerHTML = output.htmlContent;
                                        routingHelper.retrieveReviewComments();
                                    }
                                });
                            }
                        }
                    }
                });
            } else {
                return false;
            }
        } catch(e) {
            caaersLog(e);
        }
		
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
        Submit the report once it is complete. You can also withdraw the report completely, or amend it from this page.<br><br>
        <ae:submitReportValidation/>
    	    	<p>&nbsp;</p>
    	<c:if test="${command.workflowEnabled == true}">
    		<table class="tablecontent" width="40%">
    			<tr>
    				<th scope="col" align="left"><b>Report Review Options</b></th>
    			</tr>
    			<tr>
    				<td class="completion-messages">
    					<select id="sliderWFAction" onChange="javascript:advanceWorkflow();">
							<option value="">Please select</option>
						</select>
						<img id="sliderWFAction-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
    				</td>
    			</tr>
    		</table>
    	</c:if>
    
     	<input type="hidden" name="_finish"/>
	
        </chrome:box>

    </jsp:attribute>    
    <jsp:attribute name="tabControls">
  		<div class="content buttons autoclear">
    		<div class="local-buttons"></div>
	    	<div class="flow-buttons">
	        <span class="prev">
	        		<tags:button color="blue" value="Back" icon="Back" cssClass="tab9"></tags:button>
	        </span>
	        <span class="next">
	            <input type="image" alt="save »" value="Go to Manage Reports " id="flow-next" src="<c:url value="/images/blue/go_to_manage_reports_btn.png" />"/>
	        </span>
	    	</div>
		</div>    
	</jsp:attribute>
</tags:tabForm>
</body>
</html>