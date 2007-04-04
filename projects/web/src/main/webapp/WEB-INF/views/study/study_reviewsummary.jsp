<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
       .label { width: 12em; padding: 1px;  margin-right: 0.5em; } 
	   #studyDetails td.label { font-weight: bold; float: left; margin-left: 0.5em; margin-right: 0.5em; width:12em; padding: 1px; }
</style>

<script language="JavaScript" type="text/JavaScript">

function validatePage(){
	return true;
}
function fireAction(tar){
	if(validatePage()){
		document.getElementsByName('_finish')[0].name=tar;			
		document.review.submit();
	}
}
function clearField(field){
field.value="";
}

</script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">
	
	<form:form method="post" name="review">
	<chrome:division id="study-details">
	<%--	<tags:tabFields tab="${tab}"/> --%>
		<input type="hidden" name="_finish" value="true"/>
		
		<h3> <strong> Study Details </strong> </h3>		
		<table border="0" cellspacing="0" cellpadding="1" id="studyDetails">

		<tr>
			<td class="label" align="right">
				<form:label path="shortTitle">Short Title:</form:label>
			</td>
			<td align="left">
				<form:label path="shortTitle">${command.shortTitle}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="longTitle">Long Title:</form:label>
			</td>
			<td>
				<form:label path="longTitle">${command.longTitle}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="precis">Precis Text:</form:label>
			</td>
			<td>
				<form:label path="precis">${command.precis}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="description">Description Text:</form:label>
			</td>
			<td>
				<form:label path="description" >${command.description}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="status" >Status:</form:label>
			</td>
			<td>
				<form:label path="status">${command.status}</form:label>
			</td>
		</tr>
		
		<tr>
			<td class="label" align="right">
				<form:label path="diseaseCode" >Disease Code:</form:label>
			</td>
			<td>
				<form:label path="diseaseCode">${command.diseaseCode}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="monitorCode" >Monitor Code:</form:label>
			</td>
			<td>
				<form:label path="monitorCode">${command.monitorCode}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label path="phaseCode" >Phase Code:</form:label>
			</td>
			<td>
				<form:label path="phaseCode">${command.phaseCode}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label	path="multiInstitutionIndicator" >Multi Institution:</form:label>
			</td>
			<td>
				<form:checkbox path="multiInstitutionIndicator" />
			</td>
		</tr>

       </table>
	   <br>
 
       <a href="javascript:fireAction('_target0');"><img
					src="images/b-edit.gif" border="0" alt="edit this page"></a>
			
		<h3><strong>Study Identifiers</strong></h3>

		<table  width="60%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Source<span class="red">*</span> </b></td>						
						<td> <b>Identifier Type<span class="red">*</span> </b> </td>						
						<td> <b> Identifier<span class="red">*</span> </b> </td>											
					</tr>																			
				 
				    
					<c:forEach items="${command.identifiers}" var="identifier">
								<tr class="results">						
									<td align="left">${identifier.source}</td>
									<td align="left">${identifier.type}</td>										
									<td align="left">${identifier.value}</td>											
								</tr>
					</c:forEach>				
		</table>

		 <br>
		      <a href="javascript:fireAction('_target1');"><img
					src="images/b-edit.gif" border="0" alt="edit this page"></a>
			
		<h3><strong>Study Site</strong></h3>

		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="label" align="right">
				<form:label
					path="studySites[0].statusCode">Status:</form:label>
			</td>
			<td>
				<form:label
					path="studySites[0].statusCode">${command.studySites[0].statusCode}</form:label>
			</td>
			</tr>
			
			<tr>
			<td class="label" align="right">
				<form:label
					path="studySites[0].roleCode">Role:</form:label>
			</td>
			<td>
				<form:label
					path="studySites[0].roleCode">${command.studySites[0].roleCode}</form:label>
			</td>
		</tr>	
			
			<tr>
			<td class="label" align="right">
				<form:label
					path="studySites[0].startDate">Start Date:</form:label>
			</td>
			<td>
				<form:label
					path="studySites[0].startDate">${command.studySites[0].startDate}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label
					path="studySites[0].endDate">End Date:</form:label>
			</td>
			<td>
				<form:label
					path="studySites[0].endDate">${command.studySites[0].endDate}</form:label>
			</td>
		</tr>

		<tr>
			<td class="label" align="right">
				<form:label
					path="studySites[0].irbApprovalDate">IRB Approval Date:</form:label>
			</td>
			<td>
				<form:label
					path="studySites[0].irbApprovalDate">${command.studySites[0].irbApprovalDate}</form:label>
			</td>
			<td>
				<form:label
					path="studyAgents[0].agent.name">${command.studyAgents[0].agent.name}</form:label>
			</td>
		</tr>

		</table>	
	  <br>
		
	  <a href="javascript:fireAction('_target2');"><img
			src="images/b-edit.gif" border="0" alt="edit this page"></a>
			
			

	<h3><strong>Study Agents</strong></h3>

		<table  width="60%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Agent Name</b></td>						
						<td> <b>Agent NSC Number</b> </td>
						<td> <b>IND Identifier</b> </td>
						<td> <b>IND Indicator</b> </td>		
						<td> <b>Start Date</b> </td>
						<td> <b>End Date</b> </td>														
					</tr>																			
				 
				    
					<c:forEach items="${command.studyAgents}" var="studyAgent">
								<tr class="results">						
									<td align="left">${studyAgent.agent.name}</td>
									<td align="left">${studyAgent.agent.nscNumber}</td>
									<td align="left">${studyAgent.investigationalNewDrugIdentifier}</td>
									<td align="left">${studyAgent.investigationalNewDrugIndicator}</td>
									<td align="left"><tags:formatDate value="${studyAgent.participation.startDate}"/></td>
									<td align="left"><tags:formatDate value="${studyAgent.participation.endDate}"/></td>											
								</tr>
					</c:forEach>				
		</table>

		 <br>
		      <a href="javascript:fireAction('_target3');"><img
					src="images/b-edit.gif" border="0" alt="edit this page"></a>
					
					
	<h3><strong>Study Diseases</strong></h3>

		<table  width="60%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Disease Term</b></td>						
						<td> <b>Primary</b> </td>										
					</tr>																			
				 
				    
					<c:forEach items="${command.studyDiseases}" var="studyDisease">
								<tr class="results">						
									<td align="left">${studyDisease.diseaseTerm.ctepTerm}</td>
									<td align="left">${studyDisease.leadDisease}</td>									
								</tr>
					</c:forEach>				
		</table>

		 <br>
		      <a href="javascript:fireAction('_target4');"><img
					src="images/b-edit.gif" border="0" alt="edit this page"></a>								
				

</chrome:division>
	</form:form>

</chrome:body>
</body>
</html>
