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
		<h4>The following Study Records have been flagged and will NOT be loaded into caAERS</h4>
		<table  width="80%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Short Title</b></td>
						<td> <b>LongTitle</b></td>
						<td> <b>Possible Problem</b></td>																		
					</tr>
					
					<c:forEach var='item' items='${command.nonImportableStudies}'>
						<tr class="results">
   						<td align="left"><c:out value='${item.importedDomainObject.shortTitle}'/></td>
   						<td align="left"><c:out value='${item.importedDomainObject.longTitle}'/></td>
   						<c:forEach var='message' items='${item.messages}'>
   							<td align="left" color="red"><c:out value='${message.message}'/></td>
   						</c:forEach>
   						</tr>
					</c:forEach>
					
		</table>
		</c:if>
		
		<c:if test='${fn:length(command.importableStudies) > 0 }'>
		<br>
		<h4>The following Study Records will be loaded into caAERS</h4>
		
		<table  width="40%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Short Title</b></td>
						<td> <b>Long Title</b></td>																		
					</tr>																			
				 
				    
					<c:forEach varStatus="status" var="item" items="${command.importableStudies}">
								<tr class="results">						
									<td align="left"><c:out value="${item.importedDomainObject.shortTitle}"/></td>	
									<td align="left"><c:out value="${item.importedDomainObject.longTitle}"/></td>									
								</tr>
					</c:forEach>	
					
		</table>
		</c:if>
		
		
		<c:if test='${fn:length(command.nonImportableParticipants) > 0 }'>
		<h4>The following Participant Records have been flagged and will NOT be loaded into caAERS</h4>
		<table  width="80%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>First Name</b></td>
						<td> <b>Last Name</b></td>
						<td> <b>Possible Problem</b></td>																		
					</tr>
					
					<c:forEach var='item' items='${command.nonImportableParticipants}'>
						<tr class="results">
   						<td align="left"><c:out value='${item.importedDomainObject.firstName}'/></td>
   						<td align="left"><c:out value='${item.importedDomainObject.lastName}'/></td>
   						<c:forEach var='message' items='${item.messages}'>
   							<td align="left" color="red"><c:out value='${message.message}'/></td>
   						</c:forEach>
   						</tr>
					</c:forEach>																				
		</table>
		</c:if>
		
		<c:if test='${fn:length(command.importableParticipants) > 0 }'>
		<br>
		<h4>The following Participant Records will be loaded into caAERS</h4>
		
		<table  width="40%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>First Name</b></td>
						<td> <b>Last Name</b></td>																		
					</tr>																			
				 
				    
					<c:forEach varStatus="status" var="item" items="${command.importableParticipants}">
								<tr class="results">						
									<td align="left"><c:out value="${item.importedDomainObject.firstName}"/></td>	
									<td align="left"><c:out value="${item.importedDomainObject.lastName}"/></td>									
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