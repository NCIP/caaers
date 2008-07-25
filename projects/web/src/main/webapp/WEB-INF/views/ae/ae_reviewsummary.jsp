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
</head>
<body>
<div id="report-list-full" style="display:none;">
	<tags:noform>
		<c:if test="${(empty command.adverseEventReportingPeriod.aeReport && not empty command.requiredReportDefinitions) || 
              (not empty command.selectedReportDefinitions && not empty command.adverseEventReportingPeriod.aeReport)}">
			<c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
   				<div class="row">
    				<div class="label"><tags:renderInputs field="${field}"/></div>
    				<div class="value"><tags:renderLabel field="${field}"/></div>
    			</div>
			</c:forEach>
		</c:if>
	</tags:noform>			
</div>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review">
	
		<jsp:attribute name="instructions">
	 		<tags:instructions code="instruction_ae_checkpoint" />
		</jsp:attribute>
		<jsp:attribute name="singleFields">
  	 <c:if test="${empty command.adverseEventReportingPeriod.aeReport}">
  	 	<c:choose>
  	 	 <c:when test="${not empty command.requiredReportDefinitions}">
  	 	 	<p><strong>Reports Identified by caAERS</strong></p>
               <tags:instructions code="instruction_ae_checkpointReports" heading=" "/>
              	<div id="report-list" class="report-list">
            	  <!-- required reports -->
            	  <c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
            	   <c:if test="${fn:contains(command.requiredReportDefinitionNames, field.propertyName)}">
                   <div class="row">
                    <div class="label"><tags:renderInputs field="${field}"/></div>
                    <div class="value"><tags:renderLabel field="${field}"/></div>
                   </div>
                   </c:if>
            	  </c:forEach>
        		</div> 
        		<p>
        		If you agree with this assessment and wish to proceed, click Continue. 
        		Once you click this button, the report will be initiated and the countdown to the due date will begin.
        		</p>		
        		
        		<p>
        		At your discretion, you may elect to bypass the caAERS-based report selection above and 
        		instead manually select from the list of all reports defined for this study the expedited 
        		reports you wish to complete and submit. To do so, click the Manually Select Reports button below.
        		</p>
				<div class="autoclear" align="center" ><input type="button" id="manualselect2" value="Manually select reports" /></div>
  	 	 </c:when>
  	 	 <c:otherwise>
  	 	    <p>The AEs you have entered <strong>do not</strong> seem to require any expedited reporting. 
            If you wish to override this decision, please choose the notification and reporting schedule below.</p>
            <div class="report-list">
            	<!-- optional reports -->
            	<c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
                 <div class="row">
                    <div class="label"><tags:renderInputs field="${field}"/></div>
                    <div class="value"><tags:renderLabel field="${field}"/></div>
                 </div>
				</c:forEach>
        	</div>   
  	 	 </c:otherwise>
  	 	</c:choose>
  	 </c:if>
	
	
	<chrome:division title="Solicited adverse event(s)">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
    			<table id="solicitedTable" width="100%" class="tablecontent">
    				<tr>
    					<th scope="col" align="left"><b>Select</b></th>
    					<th scope="col" align="left" width="30%"><b>Term</b> </th>
    					<th scope="col" align="left"><b>Grade</b> </th>
    					<th scope="col" align="left"><b>Attribution</b> </th>
   						<th scope="col" align="left"><b>Hospitalization</b> </th>
    					<th scope="col" align="left"><b>Expected</b> </th>
    				</tr>
    				<tr id="solicitedBlankRow" />
       				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       					<c:if test="${ae.solicited == true}">
	       					<ae:oneSaeRow index="${status.index}"/>
	       				</c:if>
       				</c:forEach>
       			</table>
       		</c:if>	
	</chrome:division>
	<chrome:division title="Observed adverse event(s)">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
        	<table id="observedTable" width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b>Select</b></th>
    				<th scope="col" align="left" width="30%"><b><tags:requiredIndicator/>Term</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Hospitalization</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Expected</b> </th>
    			</tr>
    			<tr id="observedBlankRow" />
    			<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            		<c:if test="${ae.solicited == false}">
	            		<ae:oneSaeRow index="${status.index}"/>
	            	</c:if>
            	</c:forEach>
            </table>
        
        </c:if> 
	</chrome:division>

  	</jsp:attribute>
	
</tags:tabForm>
</body>
</html>