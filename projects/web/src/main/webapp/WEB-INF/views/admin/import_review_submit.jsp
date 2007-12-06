<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
<tags:includeScriptaculous/>
<style type="text/css">
        
		div.row div.label { width: 28em; } 
		div.row div.value, div.row div.extra { margin-left: 30em; }
		
    	.graph { 
        position: relative; /* IE is dumb */
        width: 200px; 
        border: 1px solid #3876C1; 
        padding: 2px; 
    	}
    	.graph .bar { 
        display: block;
        position: relative;
        background: #3876C1; 
        text-align: center; 
        color: #333; 
        height: 1em; 
        line-height: 1em;            
    	}
    	.graph .bar span { position: absolute; left: 1em; }
</style>

<tags:dwrJavascriptLink objects="importRoutineAe"/>
<script language="JavaScript" type="text/JavaScript">

	function startImport(totalNumberOfRecords,barId,statusId,type){
		
		numberOfRecordsProcessed = 0;
		i = 0;
		loops = Math.ceil(parseInt(totalNumberOfRecords)/5)
		while(  i < loops ){

			importRoutineAe.saveObjectBlock(i++, type, function(values) {
				theReturnedValue = values ;
				numberOfRecordsProcessed = numberOfRecordsProcessed +  parseInt(theReturnedValue);
				percentage = parseInt(numberOfRecordsProcessed * 100 / parseInt(totalNumberOfRecords))
				$(barId).style.width = percentage + "%"
				$(barId).innerHTML = percentage + "%"
				//$(barId).innerHTML = numberOfRecordsProcessed + " of " + totalNumberOfRecords;
			})
		}
		$(statusId).innerHTML = "Importing ..."
		$(statusId).innerHTML = "Import complete , please press Save at the bottom of the screen to continue"
	}
	
	function validatePage(){
		return true;
	}
	function fireAction(action, selected){
		if(validatePage()){
							
			document.getElementsByName('_finish')[0].name='xyz';				            
			document.studySiteForm._action.value=action;
			document.studySiteForm._selected.value=selected;		
			document.studySiteForm.submit();
		}
	}
	
	Event.observe(window, "load", function() {
		      
    	})

</script>

</head>
<body>

    <tags:tabForm tab="${tab}" flow="${flow}" title="Review & Submit">
    <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
		
		
		<c:if test='${fn:length(command.nonImportableStudies) > 0 || fn:length(command.importableStudies) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input class='ibutton' type='button' value='Import'  title='Import Routine AEs'
				   	onclick="startImport(${fn:length(command.importableStudies)},'bar3','importStatus3','study')" />	
           </div>
		   <div class="value">
		   		<div class="graph">
    				<strong id="bar3" class="bar" style="width: 0%;">0%</strong>
    			</div>
    			
		   </div>
       </div>
       <div class="row">
       		<div class="label"></div>
       		<div class="value" id="importStatus3" >Start import by pressing the above button</div>
       <br/><br/>

		<chrome:division title="Study records will NOT be loaded" id="study_will_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Study Identifier</b> </th>
    			<th scope="col" align="left"><b>Study Short Title</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableStudies}'>
			<tr class="results">
				<td align="left">
					<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
									item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
				</td>
   				<td align="left"><c:out value="${item.importedDomainObject.shortTitle}" /></td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableStudies) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		
		<chrome:division title="Study records will be loaded" id="study_will_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Study Identifier</b> </th>
    			<th scope="col" align="left"><b>Study Short Title</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableStudies}'>
			<tr class="results">
				<td align="left">
					<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
									item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
				</td>
   				<td align="left"><c:out value="${item.importedDomainObject.shortTitle}" /></td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
		</c:if>
		
		
		<c:if test='${fn:length(command.nonImportableParticipants) > 0 || fn:length(command.importableParticipants) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input class='ibutton' type='button' value='Import'  title='Import Routine AEs'
				   	onclick="startImport(${fn:length(command.importableParticipants)},'bar2','importStatus2','participant')" />	
           </div>
		   <div class="value">
		   		<div class="graph">
    				<strong id="bar2" class="bar" style="width: 0%;">0%</strong>
    			</div>
    			
		   </div>
       </div>
       <div class="row">
       		<div class="label"></div>
       		<div class="value" id="importStatus2" >Start import by pressing the above button</div>
       <br/><br/>

		
		<chrome:division title="Participant records will NOT be loaded" id="particpant_will_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Name</b> </th>
    			<th scope="col" align="left"><b>Assigned to Study</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableParticipants}'>
			<tr class="results">
   				<td align="left">
   					<c:out value="${item.importedDomainObject.firstName}" />
   					<c:out value="${item.importedDomainObject.lastName}" />
   					<c:out value=" ( " />
   						<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
						<c:out value =" ) " />	
   				</td>
   				<td align="left">
   					<c:forEach var='assignment' items='${item.importedDomainObject.assignments}'>
   						<c:out value="${assignment.studySite.study.shortTitle}" />
   						<c:out value=" ( " />
   						<c:out value="${assignment.studySite.study.primaryIdentifierValue != null ? 
							 		assignment.studySite.study.primaryIdentifierValue :  'NA'}" />
						<c:out value =" ) " />	 		
   					</c:forEach>
   				</td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableParticipants) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		
		
		
		<chrome:division title="Participant records will be loaded" id="particpant_will_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Name</b> </th>
    			<th scope="col" align="left"><b>Assigned To Study</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableParticipants}'>
			<tr class="results">
   				<td align="left">
   					<c:out value="${item.importedDomainObject.firstName}" />
   					<c:out value="${item.importedDomainObject.lastName}" />
   					<c:out value=" ( " />
   					<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
					<c:out value =" ) " />	
   				</td>
   				<td align="left">
   					<c:forEach var='assignment' items='${item.importedDomainObject.assignments}'>
   						<c:out value="${assignment.studySite.study.shortTitle}" />
   						( <c:out value="${assignment.studySite.study.primaryIdentifierValue != null ? 
							 		assignment.studySite.study.primaryIdentifierValue :  'NA'}" /> )
   					</c:forEach>
   				</td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
		
		</c:if>
		
		<c:if test='${fn:length(command.nonImportableRoutineAdverseEventReports) > 0 || fn:length(command.importableRoutineAdverseEventReports) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input class='ibutton' type='button' value='Import'  title='Import Routine AEs'
				   	onclick="startImport(${fn:length(command.importableRoutineAdverseEventReports)},'bar','importStatus','routineAe')" />	
           </div>
		   <div class="value">
		   		<div class="graph">
    				<strong id="bar" class="bar" style="width: 0%;">0%</strong>
    			</div>
    			
		   </div>
       </div>
       <div class="row">
       		<div class="label"></div>
       		<div class="value" id="importStatus" >Start import by pressing the above button</div>
       <br/><br/>

		
		<chrome:division title="NonImportable Routine AEs ( will not be loaded into caAERS due to errors )" >
		<br/>
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Periods of Observation</b> </th>
    			<th scope="col" align="left"><b>Study assigned to</b> </th>
    			<th scope="col" align="left"><b>Participant assigned to</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableRoutineAdverseEventReports}'>
			<tr class="results">
   				<td align="left">
   					<tags:formatDate value="${item.importedDomainObject.startDate}" />
   					<c:out value="-" />
   					<tags:formatDate value="${item.importedDomainObject.endDate}" />
   				</td>
   					<td align="left">
   					<c:out value="${item.importedDomainObject.assignment.studySite.study.shortTitle}" />
   					<c:out value=" ( " />
   					<c:out value="${item.importedDomainObject.assignment.studySite.study.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.assignment.studySite.study.primaryIdentifierValue :  'NA'}" />
					<c:out value =" ) " />	 
   				</td>
   				<td align="left">
   					<c:out value="${item.importedDomainObject.assignment.participant.firstName}" />
   					<c:out value="${item.importedDomainObject.assignment.participant.lastName}" />
   					<c:out value=" ( " />
   					<c:out value="${item.importedDomainObject.assignment.participant.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.assignment.participant.primaryIdentifierValue :  'NA'}" />
					<c:out value =" ) " />	 
   				</td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableRoutineAdverseEventReports) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>
   		<br />
   		</chrome:division>
		
		<chrome:division title="Importable Routine AEs ( Will be loaded into caAERS)" >
		<br/>
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Periods of Observation</b> </th>
    			<th scope="col" align="left"><b>Study assigned to</b> </th>
    			<th scope="col" align="left"><b>Participant assigned to</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableRoutineAdverseEventReports}'>
			<tr class="results">
   				<td align="left">
   					<tags:formatDate value="${item.importedDomainObject.startDate}" />
   					<c:out value="-" />
   					<tags:formatDate value="${item.importedDomainObject.endDate}" />
   				</td>
   				<td align="left">
   					<c:out value="${item.importedDomainObject.assignment.studySite.study.shortTitle}" />
   					<c:out value=" ( " />
   					<c:out value="${item.importedDomainObject.assignment.studySite.study.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.assignment.studySite.study.primaryIdentifierValue :  'NA'}" />
					<c:out value =" ) " />	 
   				</td>
   				<td align="left">
   					<c:out value="${item.importedDomainObject.assignment.participant.firstName}" />
   					<c:out value="${item.importedDomainObject.assignment.participant.lastName}" />
   					<c:out value=" ( " />
   					<c:out value="${item.importedDomainObject.assignment.participant.primaryIdentifierValue != null ? 
							 		item.importedDomainObject.assignment.participant.primaryIdentifierValue :  'NA'}" />
					<c:out value =" ) " />	 
   				</td>
   				<td align="left" color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						- <c:out value='${message.message}'/><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   		</table>
   		</chrome:division>
		</c:if>
		
		<c:if test='${command.schemaValidationResult != null  }'>
			The provided xml is invaid, Fix the errors and try again.
			<p>
				<c:out value="${command.schemaValidationResult}" />
			</p>
   		</c:if>
		
</jsp:attribute>
</tags:tabForm>
</body>
</html>