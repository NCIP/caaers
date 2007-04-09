<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
<style type="text/css">
        div.label {
            width: 35%;
        }
        div.submit {
            text-align: right;
        }
        form {
            width: 20em;
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
	function clearField(field){
		field.value="";
	}

</script>

</head>
<body>
<chrome:body title="Create a new Investigator">
<p id="instructions">
        You are creating a new Investigator
    </p>
    
    <chrome:division title="Investigator" id="investigator">
    <form:form method="post" cssClass="standard" name="studySiteForm">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
    <tags:errors path="*"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1">
        <tr><td>
        	
            <table width="50%" border="0" cellspacing="0" cellpadding="0" class="contentAreaL">
        	<tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>First Name:</td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Last Name:</td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr valign="top">
                <td class="label">Maiden Name:</td>
                <td><form:input path="maidenName" /></td>
            </tr>
            <tr>
                <td class="label">Middle Name:</td>
                <td><form:input path="middleName" /></td>
            </tr>
            <tr>
                <td class="label">NCI Identifier:</td>
                <td><form:input path="nciIdentifier" /></td>
            </tr>
            
        </table>
        </td>
        	<td><img src="<chrome:imageUrl name="spacer.gif"/>" width="30" height="1"
                    class="heightControl"></td>
        <td>
        <table width="50%" border="0" cellspacing="0" cellpadding="0" class="contentAreaL">
            <tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
        	<tr valign="top">
            
                <td class="label"><span class="red">*</span>Date of Birth:</td>
                <td><tags:dateInput path="dateOfBirth"/></td>
            </tr>
            <tr valign="top">
                <td class="label">Ethnicity:</td>
                <td><form:select path="ethnicity">
					<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label">Race:</td>
                <td><form:select path="race">
					<form:options items="${race}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label">Gender:</td>
                <td><form:select path="gender">
					<form:options items="${genders}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
        </table>
        </td>
        </tr>
        </table>
        
		<br><br>
		
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

        </form:form>
    </chrome:division>    
</chrome:body>
</body>
</html>
