<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>View Routine AE</title>
    <style type="text/css">
	
	div.row div.label {
    	width: 16em;
    }
    div.row div.value {
    	margin-left: 18em;
    }
	</style>
</head>
<body>

<chrome:box title="Legacy routine AE">
	<p id="instructions">
&nbsp; A Legacy Routine AE for ${aeRoutineReport.participantSummaryLine} on ${aeRoutineReport.studySummaryLine}.</p>

	<chrome:division title="Periods of Observation " id="observation_period">
   		
		<div class="row">
        	
			<div class="label">From	</div>
            
			<div class="value">
				<tags:formatDate value="${aeRoutineReport.startDate}"/>
            	
				&nbsp;&nbsp;&nbsp;<b>To&nbsp;&nbsp;&nbsp;</b>  
				<tags:formatDate value="${aeRoutineReport.endDate}"/>
            
			</div>
        
		</div>
   
	</chrome:division>
   
	<chrome:division title="Treatment Assignment Code" id="treatment_assignment_code">
   		
		<div class="row">
        	
			<div class="label">Treatment assignment code</div>
            
			<div class="value">${aeRoutineReport.treatmentAssignment.code}</div>
        
		</div>
        
        
		<div class="row" >
        	
			<div class="label">Description	</div>
            
			<div class="value">${aeRoutineReport.treatmentAssignment.description}</div>
        
		</div>
   
	</chrome:division> 
   	<chrome:division title="AEs" id="ae">
   		 
   		<center>
    		
   		<c:if test="${fn:length(aeRoutineReport.adverseEvents) > 0}" >
    		
   			<table width="90%" class="tablecontent">
    			
   			<tr>
    				
   				<th scope="col" align="left"><b> Term:</b> </th>
    				
   				<th scope="col" align="left"><b> Grade:</b> </th>
    				
   				<th scope="col" align="left"><b> Attribution:</b> </th>
    				
   				<th scope="col" align="left"><b> Hospitalization:</b> </th>
    				
   				<th scope="col" align="left"><b> Expected:</b> </th>
    			
   			</tr>
    				
            
   			<c:forEach items="${aeRoutineReport.adverseEvents}" var="ae" varStatus="status">
            	
   			<tr>
            		
   				<td><c:out value="${ae.adverseEventTerm.universalTerm}" /></td>
            		
   				<td><c:out value="${ae.grade}" /></td>
            		
            		
   				<td><c:out value="${ae.attributionSummary}" /></td>
            		
            		
   				<td><c:out value="${ae.hospitalization}"/></td>
   				<td><c:out value="${ae.expected == true ? 'Yes' : ae.expected == false ? 'No' : '' }" /></td>
            	
   			</tr>	 
            
   			</c:forEach>
            
   			</table>
            
   		</c:if>
            
   		</center>
   
   	</chrome:division> 
</chrome:box>
</body>
</html>