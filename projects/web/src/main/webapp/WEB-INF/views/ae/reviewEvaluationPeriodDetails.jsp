<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ page import = "java.util.ArrayList" %>

<html>
 <head>
 	<tags:stylesheetLink name="ae"/>
 </head>
 <body>
 	<form:form commandName="command">
		<tags:renderRow field="${fieldGroups.reportingPeriodFG.fields[0]}">
			<jsp:attribute name="value">
				<tags:renderInputs field="${fieldGroups.reportingPeriodFG.fields[0]}" />
			</jsp:attribute>
		</tags:renderRow>
		<chrome:division id="rpd-div" title="Evaluation Period Details">
			<div class="leftpanel">
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
		<chrome:division title="Observed Adverse Event(s)" collapsable="true" id="observedID">
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
                    <th scope="col" align="left"> </th>
                   </tr>
                <c:set var="noObservedAE" value="true" scope="request"/>
                   <tr id="observedBlankRow" />
                   <c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
                       <c:if test="${not ae.solicited}">
                        <c:set var="noObservedAE" value="false" scope="request"/>
                        <%-- <ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/>--%>
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
		<p><tags:instructions code="instruction_ae_sae"/></p>
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
    					<%--  <ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/> --%>
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
 </body>
</html>