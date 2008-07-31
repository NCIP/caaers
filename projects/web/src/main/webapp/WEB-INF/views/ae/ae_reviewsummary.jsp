<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<tags:includeScriptaculous/>
<script type="text/javascript">

	Event.observe(window, "load", function() {

		
		if($('manualselect2')){
      		 Event.observe('manualselect2', "click", function() {
      	 		var answer = confirm('Are you sure you want to bypass the caAERS-based report selection above and instead manually select from the list of all reports defined for this study?');
      	 	 	if(answer){
      	 	 		$('manualselect2').disabled=true
      	 	  	 	$('report-list').hide();
      		   		$('report-list').innerHTML = $('report-list-full').innerHTML;
      		   		$('report-list-full').innerHTML='';
 			   		AE.slideAndShow($('report-list'));  
 			  		// setUpEventObserving();	
      	 	 	}	
      	 	 });
		}
	});
	
</script>
<style type="text/css">
.divNotes,.divOtherMeddra{
	font-size:8pt;
	 border-style:none;
}

</style>
</head>
<body>
<div id="report-list-full" style="display:none; padding-bottom:5px;" align="center">
	<tags:noform>
		<table class="tablecontent">
		<tr>
			<th>Required</th>
			<th>Report</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${rpdAllTable}"  var="rdTable" varStatus="rdStatus">
			<tr>
				<td align="center">${rdTable.value.required}</td>
				<td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/> <tags:renderLabel field="${rdTable.value.field}"/></td>
				<td>${rdTable.value.status}</td>
			</tr>
		</c:forEach>
		</table>
	
	</tags:noform>			
</div>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review" saveButtonLabel="Save &amp; Manage Reports">
	
		<jsp:attribute name="instructions">
		<input type="hidden" name="_finish"/>
		<c:set var="reportingPeriodType" value="${command.adverseEventReportingPeriod.epoch.name}" />
	 		<c:if test="${reportingPeriodType != 'Baseline'}">
	 			<tags:instructions code="instruction_ae_checkpoint" />
	 		</c:if>
		</jsp:attribute>
		<jsp:attribute name="singleFields">
		<c:if test="${reportingPeriodType != 'Baseline'}">
	  	 	<c:choose>
  		 	 <c:when test="${not empty rpdSelectedTable}">
  		 	 	<p><strong>Reports Identified by caAERS</strong></p>
    	        <tags:instructions code="instruction_ae_checkpointReports" heading=" "/>
				<div align="center">
              	<div id="report-list" align="center" style="padding-bottom:5px;">
            	  <!-- required reports -->
				<table class="tablecontent">
					<tr>
						<th>Required</th>
						<th>Report</th>
						<th>Status</th>
					</tr>
				<c:forEach items="${rpdSelectedTable}"  var="rdTable" varStatus="rdStatus">
					<tr>
						<td align="center"> ${rdTable.value.required ? 'Yes' : 'No' }</td>
						<td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/> <tags:renderLabel field="${rdTable.value.field}"/></td>
						<td>${rdTable.value.status}</td>
					</tr>
				</c:forEach>
				</table>
				<div class="autoclear" align="center" ><input type="button" id="manualselect2" value="Manually Select Report(s)"  class="manualSelectBtn"/></div>
				</div>
        		</div> 
        		<p>
        		If you agree with this assessment and wish to proceed, click Continue. 
        		Once you click this button, the report will be initiated and the countdown to the due date will begin.
        		</p>		
        		
        		<p>
        		At your discretion, you may elect to bypass the caAERS-based report selection above and 
        		instead manually select from the list of all reports defined for this study the expedited 
        		reports you wish to complete and submit. To do so, click the Manually Select Reports button above.

        		</p>
				
  	 	 	</c:when>
  	 	 	<c:otherwise>
  	 	    	<p>The AEs you have entered <strong>do not</strong> seem to require any expedited reporting. 
            	If you wish to override this decision, please choose the notification and reporting schedule below.</p>
            	<div align="center" style="padding-bottom:5px;">
            	<!-- optional reports -->
				<table class="tablecontent">
					<tr>
						<th>Required</th>
						<th>Report</th>
						<th>Status</th>
					</tr>
					<c:forEach items="${rpdAllTable}"  var="rdTable" varStatus="rdStatus">
					<tr>
						<td align="center">${rdTable.value.required}</td>
						<td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/> <tags:renderLabel field="${rdTable.value.field}"/></td>
						<td>${rdTable.value.status}</td>
					</tr>
					</c:forEach>
				</table>
        		</div>   
  	 		 </c:otherwise>
  	 		</c:choose>
  	 	
	  	 	
		  	<chrome:division id="div-saes" title="Adverse Event(s) Requiring Reporting" collapsable="true">
  				<c:if test='${command.adverseEventReportingPeriod != null}'>
  					<table id="seriousTable" width="100%" class="tablecontent">
  						<tr>
    						<th scope="col" align="left"><b>Select</b></th>
    						<th scope="col" align="left" width="30%"><b>Term</b> </th>
    						<th scope="col" align="left"><b>Grade</b> </th>
    						<th scope="col" align="left"><b>Attribution</b> </th>
    						<th scope="col" align="left"><b>Hospitalization</b> </th>
    						<th scope="col" align="left"><b>Expected</b> </th>
							<th scope="col" align="left"><b>Is primary?</b></th>
    					</tr>
    					<tr id="seriousBlankRow" />
    					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
    						<c:if test="${ae.serious}">
    							<ae:oneSaeRow index="${status.index}" isSolicitedAE="${ae.solicited}" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true"/>
    						</c:if>
    					</c:forEach>
  					</table>
  				</c:if>
  			</chrome:division>
  	
			<chrome:division id="div-oaes" title="Observed Adverse Event(s)" collapsable="true">
				<c:if test='${command.adverseEventReportingPeriod != null}'>
        			<table id="observedTable" width="100%" class="tablecontent">
    					<tr>
    						<th scope="col" align="left"><b>Select</b></th>
    						<th scope="col" align="left" width="30%"><b><tags:requiredIndicator/>Term</b> </th>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    						<th scope="col" align="left"><b>Attribution</b> </th>
    						<th scope="col" align="left"><b>Hospitalization</b> </th>
    						<th scope="col" align="left"><b>Expected</b> </th>
							<th scope="col" align="left"><b>Is primary?</b></th>
    					</tr>
    					<tr id="observedBlankRow" />
    					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            				<c:if test="${(not ae.solicited) and (not ae.serious)}">
	            				<ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true"/>
	            			</c:if>
            			</c:forEach>
            		</table>
        
        		</c:if> 
			</chrome:division>
	
			<chrome:division title="Solicited Adverse Event(s)" id="div-soaes" collapsable="true">
				<c:if test='${command.adverseEventReportingPeriod != null}'>
    				<table id="solicitedTable" width="100%" class="tablecontent">
    					<tr>
    						<th scope="col" align="left"><b>Select</b></th>
    						<th scope="col" align="left" width="30%"><b>Term</b> </th>
    						<th scope="col" align="left"><b>Grade</b> </th>
    						<th scope="col" align="left"><b>Attribution</b> </th>
   							<th scope="col" align="left"><b>Hospitalization</b> </th>
    						<th scope="col" align="left"><b>Expected</b> </th>
							<th scope="col" align="left"><b>Is primary?</b></th>
    					</tr>
    					<tr id="solicitedBlankRow" />
       					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       						<c:if test="${(ae.solicited) and (not ae.serious)}">
	       						<ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="false" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="1" hideDeleteCtrl="true"/>
	       					</c:if>
       					</c:forEach>
       				</table>
       			</c:if>	
			</chrome:division>
		</c:if>
		<%-- Till this point was for non-baseline reporting period --%>
		
		
		<%-- This is for baseline reporting period --%> 
		<c:if test="${reportingPeriodType == 'Baseline'}">
			<chrome:division title="Observed Adverse Event(s)" id="div-oaes" collapsable="true">
				<c:if test='${command.adverseEventReportingPeriod != null}'>
        			<table id="observedTable" width="100%" class="tablecontent">
    					<tr>
    						<th scope="col" align="left" width="30%"><b>Term</b> </th>
    						<th scope="col" align="left"><b>Grade</b> </th>
    						<th scope="col" align="left"><b>Attribution</b> </th>
    						<th scope="col" align="left"><b>Hospitalization</b> </th>
    						<th scope="col" align="left"><b>Expected</b> </th>
						</tr>
    					<tr id="observedBlankRow" />
    					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            				<c:if test="${not ae.solicited}">
	            				<ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="0" hideDeleteCtrl="true"/>
	            			</c:if>
            			</c:forEach>
            		</table>
        
        		</c:if> 
			</chrome:division>
	
			<chrome:division title="Solicited Adverse Event(s)" id="div-soaes" collapsable="true">
				<c:if test='${command.adverseEventReportingPeriod != null}'>
    				<table id="solicitedTable" width="100%" class="tablecontent">
    					<tr>
    						<th scope="col" align="left" width="30%"><b>Term</b> </th>
    						<th scope="col" align="left"><b>Grade</b> </th>
    						<th scope="col" align="left"><b>Attribution</b> </th>
   							<th scope="col" align="left"><b>Hospitalization</b> </th>
    						<th scope="col" align="left"><b>Expected</b> </th>
						</tr>
    					<tr id="solicitedBlankRow" />
       					<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       						<c:if test="${ae.solicited}">
	       						<ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="false" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="0" hideDeleteCtrl="true"/>
	       					</c:if>
       					</c:forEach>
       				</table>
       			</c:if>	
			</chrome:division>
		</c:if> 
		
  	</jsp:attribute>
	
</tags:tabForm>
</body>
</html>