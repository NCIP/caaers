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
		
		<c:if test='${fn:length(command.studyErrors) > 0 }'>
		<h4>The following Study Records have been flagged and will NOT be loaded into caAERS</h4>
		<table  width="80%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Short Title</b></td>
						<td> <b>LongTitle</b></td>
						<td> <b>Possible Problem</b></td>																		
					</tr>
					
					<c:forEach var='item' items='${command.studyErrors}'>
						<tr class="results">
   						<td align="left"><c:out value='${item.key.shortTitle}'/></td>
   						<td align="left"><c:out value='${item.key.longTitle}'/></td>
   						<c:forEach var='value' items='${item.value}'>
   							<td align="left" color="red"><c:out value='${value}'/></td>
   						</c:forEach>
   						</tr>
					</c:forEach>																				
		</table>
		</c:if>
		
		<c:if test='${fn:length(command.studies) > 0 }'>
		<br>
		<h4>The following Study Records will be loaded into caAERS</h4>
		
		<table  width="40%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Short Title</b></td>
						<td> <b>Long Title</b></td>																		
					</tr>																			
				 
				    
					<c:forEach varStatus="status" var="study" items="${command.studies}">
								<tr class="results">						
									<td align="left"><c:out value="${study.shortTitle}"/></td>	
									<td align="left"><c:out value="${study.longTitle}"/></td>									
								</tr>
					</c:forEach>				
		</table>
		</c:if>
		
		
		<c:if test='${fn:length(command.participantErrors) > 0 }'>
		<h4>The following Participant Records have been flagged and will NOT be loaded into caAERS</h4>
		<table  width="80%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>First Name</b></td>
						<td> <b>Last Name</b></td>
						<td> <b>Possible Problem</b></td>																		
					</tr>
					
					<c:forEach var='item' items='${command.participantErrors}'>
						<tr class="results">
   						<td align="left"><c:out value='${item.key.firstName}'/></td>
   						<td align="left"><c:out value='${item.key.lastName}'/></td>
   						<c:forEach var='value' items='${item.value}'>
   							<td align="left" color="red"><c:out value='${value}'/></td>
   						</c:forEach>
   						</tr>
					</c:forEach>																				
		</table>
		</c:if>
		
		<c:if test='${fn:length(command.participants) > 0 }'>
		<br>
		<h4>The following Participant Records will be loaded into caAERS</h4>
		
		<table  width="40%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>First Name</b></td>
						<td> <b>Last Name</b></td>																		
					</tr>																			
				 
				    
					<c:forEach varStatus="status" var="participant" items="${command.participants}">
								<tr class="results">						
									<td align="left"><c:out value="${participant.firstName}"/></td>	
									<td align="left"><c:out value="${participant.lastName}"/></td>									
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