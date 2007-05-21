<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
    <title>Add investigator</title>
	 <tags:stylesheetLink name="participant"/>
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
	function clearField(field){
		field.value="";
	}

</script>

</head>
<body>
<p id="instructions">
        You are creating a new Investigator
    </p>
    
    <chrome:box title="Investigator" id="investigator" autopad="true">
    <form:form method="post" cssClass="standard" name="studySiteForm">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
    <tags:errors path="*"/>
    
     <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>First Name:</div>
	            <div class="value"><form:input path="firstName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Last Name:</div>
	            <div class="value"><form:input path="lastName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Maiden Name:</div>
	            <div class="value"><form:input path="maidenName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value"><form:input path="middleName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">NCI Identifier:</div>
	            <div class="value"><form:input path="nciIdentifier" /></div>
	        </div>
	        
        </div>
        
        <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>Date of Birth:</div>
	            <div class="value"><tags:dateInput path="dateOfBirth"/></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Ethnicity:</div>
	            <div class="value">
	            		<form:select path="ethnicity">
							<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Race:</div>
	            <div class="value">
	            		<form:select path="race">
						<form:options items="${race}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Gender:</div>
	            <div class="value">
	            		<form:select path="gender">
						<form:options items="${genders}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
        </div>
        
        <div class="endpanes">&nbsp;</div>
    
    
    
		
		<br><br>


		<table border="0" id="table1" cellspacing="10" width="600">
	
						<tr>
							<td align="center"> <b> <span class="red">*</span><em></em>Site:</b> </td>
							<td align="center"> <b> <em></em>Email Address:</b> </td>							
							<td align="center"> <b> <span class="red">*</span><em></em>Status:</b> </td>							
							<td align="center">										
								<b><a href="javascript:fireAction('addSite','0');"><img
									src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
							</td>
						</tr>
				
						<c:forEach varStatus="status" items="${command.siteInvestigators}">					
							<tr>
								<td align="center" width="15%"> 
									<form:select path="siteInvestigators[${status.index}].site">
        						  	 	<form:options items="${sitesRefData}" itemLabel="name" itemValue="id" />
									</form:select> 
								</td>
								
								<td align="center" width="15%">							
									<form:input path="siteInvestigators[${status.index}].emailAddress" />								  									
								</td>
								
								<td align="center" width="15%">
								    <form:select path="siteInvestigators[${status.index}].statusCode">										
									 	<form:options items="${studySiteStatusRefData}" itemLabel="desc"
												itemValue="code" />
									</form:select>
								</td>										
								
    	    				    <td align="center" width="10%">
									<a href="javascript:fireAction('removeSite',${status.index});"><img
											src="/caaers/images/checkno.gif" border="0" alt="delete"></a>										
								</td>
							</tr>
						</c:forEach> 
																						
					</table> 
				
               <input type="submit" value="Save"/>
        </form:form>
    </chrome:box>    
</body>
</html>
