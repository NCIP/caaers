<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ page import = "java.util.ArrayList" %>

<html>
 <head>
 	<tags:stylesheetLink name="tabbedflow"/>
 	<tags:stylesheetLink name="ae"/>
 	<tags:javascriptLink name="routing_and_review" />
 	<tags:dwrJavascriptLink objects="reviewRP,captureAE"/>
 	<tags:stylesheetLink name="slider" />
 	
 	<tags:slider renderComments="true" renderAlerts="false" display="none">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
 	
 	 <style type="text/css">
        .selectdiv { width: 170px; overflow: hidden; }
        .shortselectdiv { width: 115px; overflow: hidden; }
        .selectbox { width: 165px; }
        .shortselectbox { width: 110px; }
        .selectboxClick { width: 750px; }
        .divNotes, .divOtherMeddra { font-size: 8pt; margin-top: 5px; float: left; }
        div.row div.value { font-weight: normal; white-space: normal; margin-left: 13em; }
        div.row div.label { width: 12em; }
        .reportingPeriodSelector {}
        .ae-section { padding-top: 5px; }
        #contentOf-observedID .even { background-color: #e8e8ff; }
        #contentOf-solicitatedID .odd { background-color: #ffe2ff; }
        .thterm { position: absolute; left: 10px; top: 10px; }
        #boxholder { position: relative; height: 210px; width: 100%; border-top: 1px solid #0066ff; padding-top: 10px; }
        #gradehead { position: absolute; left: 10px; top: 75px; }
        #attributionhead { position: absolute; left: 350px; top: 75px; }
        #hospitalizationhead { position: absolute; left: 500px; top: 75px; }
        #expectedhead { position: absolute; left: 10px; top: 145px; }
        #serioushead { position: absolute; left: 180px; top: 145px; }
        /*Grade*/
        .selectbox0 { position: absolute; left: 10px; top: 95px; max-width: 300px; }
        /*Attribution*/
        .selectbox1 { position: absolute; left: 350px; top: 95px; }
        /*Hospitalization*/
        .selectbox2 { position: absolute; left: 500px; top: 95px; }
        /*Expected*/
        .selectbox3 { position: absolute; left: 10px; top: 165px; }
        /*Serious*/
        .selectbox4 { position: absolute; left: 180px; top: 165px; }
        .delete { position: absolute; right: 20px; }
    </style>
    <script>
    	var routingHelper = new RoutingAndReviewHelper(reviewRP);
    	
		Event.observe(window, "load", function(){
			routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
   		});
    </script>
 </head>
	<body>
		
		<chrome:box title="Adverse Events">
 							<form:form commandName="command">
								<tags:renderRow field="${fieldGroups.reportingPeriodFG.fields[0]}">
									<jsp:attribute name="value">
										<tags:renderInputs field="${fieldGroups.reportingPeriodFG.fields[0]}" />
									</jsp:attribute>
								</tags:renderRow>
								<chrome:division id="rpd-div" title="Course Details">
									<div class="leftpanel">
										<div class="row">
											<div class="label">
												<label>Start date</label>
											</div>
											<div class="value">
												<tags:formatDate value="${command.adverseEventReportingPeriod.startDate}"/>
											</div>
										</div>
										<div class="row">
											<div class="label">
												<label>End date</label>
											</div>
											<div class="value">
												<tags:formatDate value="${command.adverseEventReportingPeriod.endDate}"/>
											</div>
										</div>
										<c:forEach items="${fieldGroups.reportingPeriodDetailsFG.fields}" var="field">
      										<tags:renderRow field="${field}" />
      									</c:forEach>
									</div>
			
									<div class="rightpanel">
										<c:forEach items="${fieldGroups.treatmentAssignmentFG.fields}" var="field">
     										<tags:renderRow field="${field}"/>
     									</c:forEach> 
									</div>
								</chrome:division>
				
								<chrome:division title="Adverse Events" collapsable="true" id="observedID">
									<table id="observedTable" width="100%" class="tablecontent">
       	    	        				<tr>
       	    	            				<th scope="col" align="center" width="20px" />
       	    	            				<th scope="col" align="left" width="200px"><b>Term</b> </th>
       	    	            				<th scope="col" align="left" width="200px"><b>Grade</b> </th>
       	    	            				<c:if test='${command.adverseEventReportingPeriod.baselineReportingType == false}'>
	   	    	                				<th scope="col" align="left" width="100px"><b>Attribution</b> </th>
	   	    	            				</c:if>
	   	    	            				<c:if test='${command.adverseEventReportingPeriod.baselineReportingType == true}'>
	   	    	                				<th scope="col" align="left" width="100px"><b>Attribution</b> </th>
	   	    	            				</c:if>    
       	    	            				<th scope="col" align="left" width="109px"><b>Hospitalization</b> </th>
       	    	            				<th scope="col" align="left"><b>Expected</b> </th>
       	    	            				<caaers:renderFilter elementID="adverseEvents[].serious"><th scope="col" align="left"><b>Serious</b> </th></caaers:renderFilter>
       	    		       				</tr>
       	        		 				<c:set var="noObservedAE" value="true" scope="request"/>
       	            					<tr id="observedBlankRow" />
       	            					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       	                					<c:if test="${not ae.solicited}">
       	                						<c:set var="noObservedAE" value="false" scope="request"/>
       	                						<ae:oneReviewSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/>
       	             					</c:if>
       		          		  			</c:forEach>
       	            					<c:if test="${noObservedAE}">
            	    						<tr id="observedEmptyRow">
               		     						<td colspan="7">No observed adverse event added</td>
                							</tr>
                						</c:if>
            						</table>   
								</chrome:division>
			
								<chrome:division title="Solicited Adverse Event(s)" collapsable="true" id="solicitatedID">
									<center>
										<table id="solicitedTable" width="100%" class="tablecontent" border="0">
											<tr>
												<th scope="col" align="center" width="20px" />
												<th scope="col" align="left" width="200px"><b>Term</b> </th>
												<th scope="col" align="left" width="200px"><b>Grade</b> </th>
												<th scope="col" align="left" width="100px"><b>Attribution</b> </th>
												<th scope="col" align="left" width="109px"><b>Hospitalization</b> </th>
												<th scope="col" align="left"><b>Expected</b> </th>
												<caaers:renderFilter elementID="adverseEvents[].serious"><th scope="col" align="left"><b>Serious</b> </th></caaers:renderFilter>
											</tr>
											<c:set var="noSolictedAE" value="true" scope="request"/>
   											<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
   												<c:if test="${ae.solicited}">
													<c:set var="noSolictedAE" value="false" scope="request" />
    												<ae:oneReviewSaeRow index="${status.index}" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/>
    											</c:if>
   											</c:forEach>
											<c:if test="${noSolictedAE}">
												<tr id="solicitedBlankRow">
													<td colspan="6">No solicited adverse event(s) associtated to this study</td>
												</tr>
											</c:if>
   										</table>
   									</center>
								</chrome:division> 	
							</form:form>
		</chrome:box>
	</body>
</html>