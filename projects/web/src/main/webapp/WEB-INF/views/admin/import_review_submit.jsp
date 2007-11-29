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
        div.label {
            width: 35%;
        }
        div.submit {
            text-align: right;
        }
        form {
            width: 80%;
        }
        td.display {
	        background-color: white;
        }
        
    </style>

	<script language="JavaScript" type="text/JavaScript">

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
<body> <br>
<%--
<chrome:body title="Review & Submit">

    <form:form method="post" cssClass="standard" name="studySiteForm"> --%>
    <tags:tabForm tab="${tab}" flow="${flow}" title="Review & Submit">
    <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
		
		<c:if test='${fn:length(command.nonImportableStudies) > 0 }'>
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
   		</table>	
   		</chrome:division>
		</c:if>
		
		<c:if test='${fn:length(command.importableStudies) > 0 }'>
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
		
		
		<c:if test='${fn:length(command.nonImportableParticipants) > 0 }'>
		<chrome:division title="Participant records will NOT be loaded" id="particpant_will_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableParticipants}'>
			<tr class="results">
   				<td align="left">
   					<c:out value="${item.importedDomainObject.firstName}" />
   					<c:out value="${item.importedDomainObject.lastName}" />   				
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
   		</table>	
   		</chrome:division>
		</c:if>
		
		<c:if test='${fn:length(command.importableParticipants) > 0 }'>
		
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
		
		<c:if test='${fn:length(command.nonImportableRoutineAdverseEventReports) > 0 }'>
		<h4>The following Routine AE Records have been flagged and will NOT be loaded into caAERS</h4>
					
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
   		</table>	
		</c:if>
		
		<c:if test='${fn:length(command.importableRoutineAdverseEventReports) > 0 }'>
		<br>
		<h4>The following Routine AE Records will be loaded into caAERS</h4>
		
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
		</c:if>
		<%--
        </form:form>
    
</chrome:body> --%>
</jsp:attribute>
</tags:tabForm>
</body>
</html>