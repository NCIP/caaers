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
	
	function startImport(totalNumberOfRecords,barId,statusId,type,button){
		$(button).disabled=true;
		$(barId).style.display = 'block';
		$(barId).style.visibility = 'visible';
		$(statusId).update("Import In Progess .....")
		importRoutineAe.saveObjectBlock(1, type, function(values){
			returnValue = values;
			$(barId).style.display = 'none';
			$(barId).style.visibility = 'hidden';
			//alert(returnValue);
			if(returnValue == 'ERR'){
				$(statusId).update("Import Incomplete, Please contact caAERS support");
			}
			$(statusId).update("Import complete , please press Save at the bottom of the screen to continue")
		});
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

		<c:if test='${fn:length(command.importableResearchStaff) > 0 || fn:length(command.nonImportableResearchStaff) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input id='button5' class='ibutton' type='button' value='Import'  title='Import ResearchStaff'
				   	onclick="startImport(${fn:length(command.importableStudies)},'bar5','importStatus5','researchstaff','button5')" />	
           </div>
           
           <div id=bar5 style="display: none;">
           		<img src="<c:url value="/images/indicator.white.gif"/>">
           </div>
       </div>
	   <div class="row">
       		<div class="label"></div>
       		<div class="value" id="importStatus5" >Start import by pressing the above button</div>
       <br/><br/> 	
		
		<chrome:division title="ResearchStaff records did NOT get loaded" id="research_staff_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableResearchStaff}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>
   				<td align="left">
   					<c:forEach var='message' items='${item.messages}'>
   						- <font color="red"><c:out value='${message.message}'/></font><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableResearchStaff) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		
		<chrome:division title="ResearchStaff records got loaded" id="research_staff_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableResearchStaff}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>

   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.importableResearchStaff) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		

		
		</c:if>
		
		<c:if test='${fn:length(command.importableInvestigators) > 0 || fn:length(command.nonImportableInvestigators) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input id='button4' class='ibutton' type='button' value='Import'  title='Import Investigators'
				   	onclick="startImport(${fn:length(command.importableStudies)},'bar4','importStatus4','investigator','button4')" />	
           </div>
           
           <div id=bar4 style="display: none;">
           		<img src="<c:url value="/images/indicator.white.gif"/>">
           </div>
       </div>
       <div class="row">
       		<div class="label"></div>
       		<div class="value" id="importStatus4" >Start import by pressing the above button</div>
       <br/><br/>
		
		
		<chrome:division title="Investigator records did NOT get loaded" id="investigator_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableInvestigators}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>
   				<td align="left"> color="red">
   					<c:forEach var='message' items='${item.messages}'>
   						<font color="red">- <c:out value='${message.message}'/></font><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableInvestigators) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		
		<chrome:division title="Investigator records got loaded" id="investigator_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableInvestigators}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>

   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.importableInvestigators) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		</c:if>
				
		<c:if test='${fn:length(command.nonImportableStudies) > 0 || fn:length(command.importableStudies) > 0 }'>
		
		<br/><br/>
		<div class="row">
			<div class="label">
				<input id='button3' class='ibutton' type='button' value='Import'  title='Import Studies'
				   	onclick="startImport(${fn:length(command.importableStudies)},'bar3','importStatus3','study','button3')" />	
           </div>
           
           <div id=bar3 style="display: none;">
           		<img src="<c:url value="/images/indicator.white.gif"/>">
           </div>
           
<!--		   <div class="value">-->
<!--		   		<div class="graph">-->
<!--    				<strong id="bar3" class="bar" style="width: 0%;">0%</strong>-->
<!--    			</div>-->
<!--		   </div>-->
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
				<input id='button2' class='ibutton' type='button' value='Import'  title='Import Participants'
				   	onclick="startImport(${fn:length(command.importableParticipants)},'bar2','importStatus2','participant','button2')" />	
           </div>
           <div id=bar2 style="display: none;">
           		<img src="<c:url value="/images/indicator.white.gif"/>">
           </div>
           
<!--		   <div class="value">-->
<!--		   		<div class="graph">-->
<!--    				<strong id="bar2" class="bar" style="width: 0%;">0%</strong>-->
<!--    			</div>-->
<!--		   </div>-->
       </div>
       <div class="row">
       		<div class="label"></div>
       		<div style="text-align:center;" id="importStatus2" >Start import by pressing the above button</div>
       <br/><br/>

		
		<chrome:division title="Subject records will NOT be loaded" id="particpant_will_not_load">
		
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
		
		
		
		
		<chrome:division title="Subject records will be loaded" id="particpant_will_load">
		
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
				<input id='button' class='ibutton' type='button' value='Import'  title='Import Routine AEs'
				   	onclick="startImport(${fn:length(command.importableRoutineAdverseEventReports)},'bar','importStatus','routineAe','button')" />	
           </div>
           
           <div id=bar style="display: none;">
           		<img src="<c:url value="/images/indicator.white.gif"/>">
           </div>
<!--		   <div class="value">-->
<!--		   		<div class="graph">-->
<!--    				<strong id="bar" class="bar" style="width: 0%;">0%</strong>-->
<!--    			</div>-->
<!--    			-->
<!--		   </div>-->
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
    			<th scope="col" align="left"><b>Subject assigned to</b> </th>
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
    			<th scope="col" align="left"><b>Subject assigned to</b> </th>
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