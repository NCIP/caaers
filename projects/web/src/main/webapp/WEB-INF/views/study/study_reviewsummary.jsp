<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
       <c:if test="${(empty command.id) or ( command.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
        <chrome:division>
            <div class="row">
                <div class="label">Primary Identifier</div>
                <div class="value">${command.primaryIdentifier.value}</div>
            </div>
            <div class="row">
                <div class="label">Short Title</div>
                <div class="value">${command.shortTitle}</div>
            </div>
            <div class="row">
                <div class="label">Long Title</div>
                <div class="value">${command.longTitle}</div>
            </div>
            <div class="row">
                <div class="label">Precis</div>
                <div class="value">${command.precis}</div>
            </div>
            <div class="row">
                <div class="label">Description</div>
                <div class="value">${command.description}</div>
            </div>
            <div class="row">
                <div class="label">Primary Sponsor</div>
                <div class="value">${command.primaryFundingSponsorOrganization.name}</div>
            </div>

            <div class="row">
                <div class="label">Phase code</div>
                <div class="value">${command.phaseCode}</div>
            </div>
       </chrome:division>
		<c:if test="${not empty command.identifiers}">
			<chrome:division title="Identifiers">
			<table class="tablecontent">
			<tr>
				<th scope="col">Assigning Authority</th>
				<th scope="col">Identifier Type</th>
				<th scope="col">Identifier</th>
			</tr>
			<c:forEach items="${command.identifiers}" var="identifier">
			<tr class="results">
				<td>${identifier.source}</td>
				<td>${identifier.type}</td>
				<td>${identifier.value}</td>
			</tr>
			</c:forEach>
			</table>
			<br>
			</chrome:division>
		</c:if>
		<c:if test="${not empty command.studySites}">       
       		<chrome:division title="Sites">
       		<table class="tablecontent">
				<tr>
					<th scope="col">Study Site</th>
					<th scope="col">Role</th>
					<th scope="col">IRB Approval Date</th>
				</tr>
				<c:forEach items="${command.studySites}" var="studySite">
				<tr class="results">
					<td>${studySite.organization.name}</td>
					<td>${studySite.roleCode}</td>
					<td><tags:formatDate value="${studySite.irbApprovalDate}" /></td>
				</tr>
				</c:forEach>
			</table>	
	  		<br>
			</chrome:division>
	</c:if>
	<c:if test="${not empty command.studySites}">    
    	<chrome:division title="Investigators">
        <table class="tablecontent">
            <tr>
                <th scope="col">Investigator</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>
            <c:forEach items="${command.studySites}" var="studySite" >
                <c:forEach items="${studySite.studyInvestigators}" var="studyInvestigator" >
                    <tr class="results">
                        <td>${studyInvestigator.siteInvestigator.investigator.fullName}</td>
                        <td>${studyInvestigator.roleCode}</td>
                        <td>${studyInvestigator.statusCode}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    	</chrome:division>
    </c:if>
    <c:if test="${not empty command.studySites}">
    <chrome:division title="Personnel">
        <table class="tablecontent">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>
            <c:forEach items="${command.studySites}" var="studySite" >
                <c:forEach items="${studySite.studyPersonnels}" var="studyPersonnel">
                    <tr class="results">
                        <td>${studyPersonnel.researchStaff.fullName}</td>
                        <td>${studyPersonnel.roleCode}</td>
                        <td>${studyPersonnel.statusCode}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </chrome:division>
    </c:if>
    <c:if test="${not empty command.studyAgents}">
    <chrome:division title="Agents">
		<table class="tablecontent">
		<tr >						
			<th scope="col">Agent Name</th>
			<th scope="col">Agent NSC<br />Number</th>
			<th scope="col">IND #</th>
			<th scope="col">Investigational New <br /> Drug?</th>	
		</tr>																			
	 	    
		<c:forEach items="${command.studyAgents}" var="studyAgent">
			<tr>						
				<td>${studyAgent.agent.name}</td>
				<td>${studyAgent.agent.nscNumber}</td>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					 <tr>
						 <c:forEach items="${studyAgent.studyAgentINDAssociations }" var="sai">
						 <td>${sai.investigationalNewDrug.indNumber}</td><td>${sai.investigationalNewDrug.holderName}</td>
				 		 </c:forEach>
					 </tr>
					</table>
				 
				</td>
				<td>${studyAgent.investigationalNewDrugIndicator ? 'Yes' : 'No'}</td>
			</tr>
		</c:forEach>				
		</table>
    </chrome:division>
	</c:if>
	<c:if test="${not empty command.ctepStudyDiseases}">
    <chrome:division title="Diseases">
        <table class="tablecontent">
            <br>
            <tr>
                <th scope="col">Primary</th>
                <th scope="col">Disease Term</th>
            </tr>

            <c:forEach items="${command.ctepStudyDiseases}" var="studyDisease">
                <tr class="results">
                    <td>${studyDisease.leadDisease ? '&times;' : ''}</td>
                    <td>${studyDisease.term.ctepTerm}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </chrome:division>
    </c:if>
    </jsp:attribute>
</tags:tabForm>

</body>
</html>
