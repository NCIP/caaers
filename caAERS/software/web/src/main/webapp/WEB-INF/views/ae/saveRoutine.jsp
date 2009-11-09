<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<tags:dwrJavascriptLink objects="createAE"/>
	<%--  <tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}"
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
    </tags:slider> --%>
	<script>
		var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
		function fireAction(action, selected){
			document.getElementById('command')._target.name='_noname';
			document.saveRoutineForm._action.value=action;
			document.saveRoutineForm._selected.value=selected;
			$('finish').remove();
			document.saveRoutineForm.submit();
		}
		
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
    <tags:tabForm tab="${tab}" flow="${flow}" formName="saveRoutineForm">
        <jsp:attribute name="instructions">
            
            You have added ${fn:length(command.aeRoutineReport.adverseEvents)} AE(s). 
            To save the set of AE(s) that you entered, click on Save button. 
           	<div>
           		<input type="hidden" name="_action" value="">
           		<input type="hidden" name="_selected" value="">
            </div>
            <div id="finish">
           		<input type="hidden" name="_finish"/>
            </div>
            
        </jsp:attribute>
        
         <jsp:attribute name="repeatingFields">
    		<center>
    		<c:if test="${fn:length(command.aeRoutineReport.adverseEvents) > 0}" >
    		<table width="90%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Term:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Grade:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Attribution:</b> </th>
    				<th scope="col" align="left"><b>Did AE cause hospitalization?:</b> </th> 
    				<th scope="col" align="left"><b>Expected:</b> </th>
    				<th scope="col" align="left"> </th>
    			</tr>
    				
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" var="ae" varStatus="status">
            	<tr>
            		<td>
            			<c:out value="${ae.adverseEventTerm.universalTerm}" />
            		</td>
            		<td>
            			<c:out value="${ae.grade}" />
            		</td>
            		
            		<td>
            			<c:out value="${ae.attributionSummary}" />
            		</td>
            		
            		<td>
            			<c:out value="${ae.hospitalization.code eq 0 ? '--' : ae.hospitalization.displayName}"/>
            		</td>
            		
            		<td>
            			<c:out value="${ae.expected eq null ? '--' : ae.expected eq true ? 'Yes' : 'No' }" />
            		</td>
            		<td>
            			<c:if test="${ae.report == null }" >
            			<a href="javascript:fireAction('removeTerm',${status.index});">
								<img src="<c:url value="/images/checkno.gif"/>" border="0" alt="Delete"></a>
						</c:if>
						<c:if test="${ae.report != null }" >
            				<c:out value="SAE" />
						</c:if>
            		</td>
            	</tr>	 
            </c:forEach>
            </table>
            </c:if>
            </center>
        </jsp:attribute>
        
        <jsp:attribute name="singleFields">
            
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>
