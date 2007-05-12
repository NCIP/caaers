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
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review">
    <jsp:attribute name="repeatingFields">
		<input type="hidden" name="_finish" value="true"/>
        <chrome:division title="details">
        <table width="50%" border="0" cellspacing="0" cellpadding="0" id="tablecontent">
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

       </table>
       </chrome:division>

		<chrome:division title="Identifiers">
		<table id="tablecontent">
			<tr>
				<th scope="col" align="left">Source</td>
				<th scope="col" align="left">Type</td>
				<th scope="col" align="left">Identifier</td>
			</tr>
			<c:forEach items="${command.identifiers}" var="identifier">
			<tr class="results">
				<td class="alt" align="left">${identifier.source}</td>
				<td class="alt" align="left">${identifier.type}</td>
				<td class="alt" align="left">${identifier.value}</td>
			</tr>
		</c:forEach>
		</table>
		<br>
		</chrome:division>
        <chrome:division title="Sites">
       <table id="tablecontent">
				<tr>
					<th scope="col" align="left">Study Site</td>
					<th scope="col" align="left">Status</td>
					<th scope="col" align="left">Role</td>
					<th scope="col" align="left">Start Date</td>
					<th scope="col" align="left">IRB Approval Date</td>
				</tr>
				<c:forEach items="${command.studySites}" var="studySite">
				<tr class="results">
					<td class="alt" align="left">${studySite.site.name}</td>
					<td class="alt" align="left">${studySite.statusCode}</td>
					<td class="alt" align="left">${studySite.roleCode}</td>
					<td class="alt" align="left">${studySite.startDate}</td>
					<td class="alt" align="left">${studySite.irbApprovalDate}</td>
				</tr>
				</c:forEach>
			</table>	
	  <br>
	</chrome:division>

	<chrome:division title="Investigators">
		<table id="tablecontent">
			<tr>
				<th scope="col" align="left">Investigator</td>
				<th scope="col" align="left">Role</td>
				<th scope="col" align="left">Status</td>
			</tr>
			<c:forEach items="${command.studySites}" var="studySite" varStatus="status">
				<c:forEach items="${studySite.studyInvestigators}" var="studyInvestigator" varStatus="status">
				<tr class="results">
					<td class="alt" align="left">${studyInvestigator.siteInvestigator.investigator.fullName}</td>
					<td class="alt" align="left">${studyInvestigator.roleCode}</td>
					<td class="alt" align="left">${studyInvestigator.statusCode}</td>
				</tr>
				</c:forEach>
			</c:forEach>
		</table>
    </chrome:division>
    
    <chrome:division title="Personnel">
		<table id="mytable">
			<tr>
				<th scope="col" align="left">Name</td>
				<th scope="col" align="left">Role</td>
				<th scope="col" align="left">Status</td>
				</tr>
				<c:forEach items="${command.studySites}" var="studySite" varStatus="status">
					<c:forEach items="${studySite.studyPersonnels}" var="studyPersonnel" varStatus="status">
					<tr class="results">
						<td class="alt">${studyPersonnel.researchStaff.fullName}</td>
						<td class="alt">${studyPersonnel.roleCode}</td>
						<td class="alt">${studyPersonnel.statusCode}</td>
					</tr>
					</c:forEach>
				</c:forEach>								
		</table>
    </chrome:division>
    
    <chrome:division title="Agents">
		<table id="tablecontent">
		<tr >						
			<th scope="col">Agent Name</b></th>						
			<th scope="col">Agent NSC Number</th>
			<th scope="col">IND Identifier</th>
			<th scope="col">IND Indicator</th>	
			<th scope="col">Start Date</th>
			<th scope="col">End Date</th>														
		</tr>																			
	 	    
		<c:forEach items="${command.studyAgents}" var="studyAgent">
			<tr>						
				<td class="alt">${studyAgent.agent.name}</td>
				<td class="alt">${studyAgent.agent.nscNumber}</td>
				<td class="alt">${studyAgent.investigationalNewDrugIdentifier}</td>
				<td class="alt">${studyAgent.investigationalNewDrugIndicator}</td>
				<td class="alt"><tags:formatDate value="${studyAgent.participation.startDate}"/></td>
				<td class="alt"><tags:formatDate value="${studyAgent.participation.endDate}"/></td>											
			</tr>
		</c:forEach>				
		</table>
    </chrome:division>

	<chrome:division title="Diseases">
		<table id ="tablecontent">
		<br>
			<tr>						
				<th scope="col">Disease Term</th>						
				<th scope="col">Primary</th>										
			</tr>																		
		    
			<c:forEach items="${command.ctepStudyDiseases}" var="studyDisease">
				<tr class="results">						
					<td class="alt">${studyDisease.term.ctepTerm}</td>
					<td class="alt">${studyDisease.leadDisease}</td>									
				</tr>
			</c:forEach>				
		</table>
		 <br>
		 </chrome:division>
    </jsp:attribute>
</tags:tabForm>

</body>
</html>
