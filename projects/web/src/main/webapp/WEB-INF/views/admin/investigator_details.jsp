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

	function fireAction(action, selected){				
		document.getElementById("_action").value=action;	
		document.getElementById("_selected").value=selected;
		document.form.submit();	
	}
	</script>

</head>
<body>
    <form:form method="post" name="form" id="form">
    <div>		
		<input type="hidden" name="_action" id="_action" value="">
		<input type="hidden" name="_selected" id="_selected" value="-1">
	</div>
    <chrome:box title="Investigator Details" id="investigator" autopad="true">
	<p id="instructions">
        Add a new Investigator
    </p>
    <tags:errors path="*"/>
    
     <div class="content">
        <div class="row">
            <div class="label"><span class="red">*</span>First Name:</div>
            <div class="value"><form:input path="firstName" /></div>
        </div>
        <div class="row">
            <div class="label"><span class="red">*</span>Last Name:</div>
            <div class="value"><form:input path="lastName" /></div>
        </div>
       <div class="row">
            <div class="label">NCI Identifier:</div>
            <div class="value"><form:input path="nciIdentifier" /></div>
        </div>   
     </div>
    </chrome:box>
    <chrome:box title="Associate Site" id="investigator" autopad="true">
      <table class="tablecontent">
		<tr>
			<th scope="col"> <b> <span class="red">*</span><em></em>Site:</b> </td>
			<th scope="col"> <b> <em></em>Email Address:</b> </td>							
			<th scope="col"><b> <span class="red">*</span><em></em>Status:</b> </td>							
			<th scope="col">										
				<b><a href="javascript:fireAction('addSite','0');"><img
					src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
			</td>
		</tr>

		<c:forEach varStatus="status" items="${command.siteInvestigators}">					
		<tr>
			<td class="alt">  
				<form:select path="siteInvestigators[${status.index}].site">
   					<form:options items="${sitesRefData}" itemLabel="name" itemValue="id" />
				</form:select> </td>	
			<td class="alt"> 					
				<form:input path="siteInvestigators[${status.index}].emailAddress"/> </td>
			<td class="alt"> 
			    <form:select path="siteInvestigators[${status.index}].statusCode">										
				 	<form:options items="${studySiteStatusRefData}" itemLabel="desc" itemValue="code" />
				</form:select></td>										
			<td class="alt"> 
				<a href="javascript:fireAction('removeSite',${status.index});"><img
						src="/caaers/images/checkno.gif" border="0" alt="delete"></a></td>
		</tr>
		</c:forEach>														
		</table>
		<br>
		<br>
        <div align = "center">
        <input type="submit" value="Save"/>
        </chrome:box>      
        </form:form>
 </body>
</html>
