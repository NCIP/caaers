<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%-- 
Note: -
   This should work on the orginal adverse event list, and not on the decorated list in command
--%>
<c:if test='${not empty command.adverseEventReportingPeriod}'>

<chrome:box title="Adverse Events" collapsable="true" id="observedID" autopad="true">
<p><tags:instructions code="instruction_ae_oae"/></p>
 			<tags:aeTermQuery
                       			isMeddra="${not empty command.study.aeTerminology.meddraVersion}"
                       			noBackground="true"
                       			callbackFunctionName="rpCreator.addAdverseEvents"
                       			ignoreOtherSpecify="false"
                       			isAjaxable="true"
                       			version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}"
                       		title="">
            </tags:aeTermQuery>    
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
                        <ae:oneSaeRow index="${status.index}" editableDisplay="true" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/>
                    </c:if>
                   </c:forEach>
                   
                
                <c:if test="${noObservedAE}">
                <tr id="observedEmptyRow">
                    <td colspan="7">No adverse event added</td>
                </tr>
                </c:if>
           </table>

</chrome:box>

<c:if test="${command.havingSolicitedAEs}">
<chrome:box title="Solicited Adverse Events" collapsable="true" id="solicitatedID" autopad="true">
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
    					<ae:oneSaeRow editableDisplay="true" index="${status.index}" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="0" renderNotes="true" renderSubmittedFlag="true"/>
    				</c:if>
   				</c:forEach>
				<c:if test="${noSolictedAE}">
				<tr id="solicitedBlankRow">
					<td colspan="6">No solicited adverse event(s) associtated to this study</td>
				</tr>
				</c:if>
   			</table>
   	</center>
 </chrome:box>
</c:if>
<div id="display_amend_popup" style="display:none;text-align:left" >
    	<chrome:box title="Amendments Required" id="popupId">
    		<c:if test="${not empty command.participant}">
      			<div align="left">
        			<div class="row">
          				<div class="summarylabel">Subject</div>
          				<div class="summaryvalue">${command.participant.fullName}</div>
        			</div>
        			<div class="row">
          				<div class="summarylabel">Study</div>
          				<div class="summaryvalue">${command.study.longTitle}</div>
        			</div>
        			<div class="row">
          				<div class="summarylabel">Course</div>
          				<div class="summaryvalue">${command.adverseEventReportingPeriod.name}</div>
        			</div>
      			</div>
    		</c:if>
    		<div id="div-reports-to-be-amended" style="text-align:left;">
      			<hr/>
      			<p>
        			<tags:instructions code="instruction_ae_amendments_required"/>
      			</p>
      			<chrome:division title="Reports that will be Amended" id="div-selected-reports" collapsable="false">
      				<c:forEach items="${command.adverseEventReportingPeriod.aeReports}" var="aeReport" varStatus="statusAeReport">
      					<div class="eXtremeTable" id="amend-aeReport-${aeReport.id}" style="display:none;text-align:left">
                    		<table width="100%" border="0" cellspacing="0" class="tableRegion">
                      			<thead>
                        			<tr align="center" class="label">
                          				<td width="5%"/>
                          				<td class="tableHeader" width="15%">Report Type</td>
                          				<td class="centerTableHeader" width="10%">Report Version</td>
                          				<td class="centerTableHeader" width="10%"># of AEs</td>
                          				<td class="tableHeader" width="20%">Data Entry Status</td>
                          				<td class="tableHeader" width="20%">Submission Status</td>
                       				</tr>
                      			</thead>
                      			<ae:oneReviewExpeditedReportRow aeReport="${aeReport}" index="${statusAeReport.index}" />
                      		</table>
        		        </div>
              		</c:forEach>
              		<br><br>
                      		<table width="100%">	
                      			<tr>
                      				<td align="left">
                      					<input type="submit" value="Amend" id="amendment-required-yes" onClick="javascript:window.parent.deleteOrAmendAndSubmit();"/>
                      				</td>
                      				<td align="right">
	                      				<input type="submit" value="Don't Amend" id="amendment-required-no" onClick="javascript:window.parent.Windows.close('amend-popup-id');"/>
                      				</td>
                      			<tr>	
		                    </table>
      			</chrome:division>
      		</div>
    	</chrome:box>	
    </div>

</c:if>