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
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/caaers/css/solicited_ae.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
       .label { width: 12em; padding: 1px;  margin-right: 0.5em; } 
       div.row div.value  {white-space:normal;}
	   #studyDetails td.label { font-weight: bold; float: left; margin-left: 0.5em; margin-right: 0.5em; width:12em; padding: 1px; }
</style>
 
</head>
<body>
<p><tags:instructions code="study.study_overview.top" /></p>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review">
    <jsp:attribute name="repeatingFields">
       <c:if test="${(empty command.id) or ( command.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
        <chrome:division>
        	<div class="leftpanel">
        		<div class="row">
                	<div class="label">Primary identifier</div>
                	<div class="value">${command.primaryIdentifier.value} </div>
            	</div>
           		<div class="row">
                	<div class="label">Short title</div>
                	<div class="value">${command.shortTitle} </div>
            	</div>
            	<div class="row">
                	<div class="label">Long Title</div>
                	<div class="value">${command.longTitle} </div>
            	</div>
            	<div class="row">
                	<div class="label">Precis</div>
                	<div class="value">${command.precis} </div>
            	</div>
            	<div class="row">
                	<div class="label">Description</div>
                	<div class="value">${command.description} </div>
            	</div>
            	<div class="row">
                	<div class="label">Primary sponsor</div>
                	<div class="value">${command.primaryFundingSponsorOrganization.name} </div>
            	</div>
				<div class="row">
                	<div class="label">Coordinating center</div>
                	<div class="value">${command.studyCoordinatingCenter.organization.name} </div>
            	</div>
            </div>
        	<div class="rightpanel">
        		<div class="row">
                	<div class="label">Phase code</div>
                	<div class="value">${command.phaseCode} </div>
            	</div>
            	<div class="row">
                	<div class="label">Status</div>
                	<div class="value">${command.status} </div>
            	</div>	
            	<div class="row">
                	<div class="label">Terminology</div>
                	<div class="value">${command.aeTerminology.term} </div>
            	</div>	
            	<div class="row">
                	<div class="label">Terminology Version</div>
                	<div class="value">${command.aeTerminology.term eq 'CTC' ? command.aeTerminology.ctcVersion.name : command.aeTerminology.meddraVersion.name} </div>
            	</div>
            	<c:if test="${command.aeTerminology.term eq 'CTC'}">
            	<div class="row">
	            	<div class="label">Other MedDRA</div>
	            	<div class="value">${command.otherMeddra.name}</div>
	            </div>
            	</c:if>
            	<div class="row">
                	<div class="label">Multi institutional</div>
                	<div class="value">${command.multiInstitutionIndicator ? 'Yes' : 'No'} </div>
            	</div>
            	<div class="row">
                	<div class="label">AdEERS reporting</div>
                	<div class="value">${command.adeersReporting ? 'Yes' : 'No'} </div>
            	</div>	
            		
        	</div>
       </chrome:division>
    <chrome:division title="Report Formats">
    	<table class="tablecontent" width="96%" >
		<tr >						
			<th scope="col">Report Format</th>
		</tr>	
		<c:forEach items="${command.reportFormats}" var="report">
		<tr class="results">
			<td>${report.reportFormatType.displayName}</td>
		</tr>
		</c:forEach>
		<c:if test="${empty command.reportFormats}">
		<tr>
		 <td>No report format is selected for this study</td>
		</tr>
		</c:if>																		
		</table>
    </chrome:division>       
    <chrome:division title="Therapies">
    	<table class="tablecontent" width="96%" >
		<tr >						
			<th scope="col">Therapy name</th>
		</tr>	
		<c:forEach items="${command.studyTherapies}" var="therapy">
		<tr class="results">
			<td>${therapy.studyTherapyType.displayName}</td>
		</tr>
		</c:forEach>
		<c:if test="${empty command.studyTherapies}">
		<tr>
		 <td>No therapy is selected for this study</td>
		</tr>
		</c:if>																		
		</table>
    </chrome:division>
    <chrome:division title="Agents">
		<table class="tablecontent" width="96%" >
		<tr >						
			<th scope="col">Agent name</th>
			<th scope="col">Agent NSC<br />number</th>
			<th scop="col">IND indicator</th>
			<th scope="col">IND #</th>
			<th scope="col">Investigational new <br /> drug?</th>
			<th scope="col">Part of <br />lead IND?</th>	
		</tr>																			
	 	    
		<c:forEach items="${command.studyAgents}" var="studyAgent">
			<tr>						
				<td>${studyAgent.agentName}</td>
				<td>${studyAgent.agent.nscNumber}</td>
				<td>${studyAgent.indType.displayName }</td>
				<td>
					<c:if test="${fn:length(studyAgent.studyAgentINDAssociations) > 0}">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					 <c:forEach items="${studyAgent.studyAgentINDAssociations }" var="sai">
						  <tr><td>${sai.investigationalNewDrug.indNumber eq -111 ? 'CTEP IND' : sai.investigationalNewDrug.indNumber eq -222 ? 'DCP IND' : sai.investigationalNewDrug.indNumber }</td><td>${sai.investigationalNewDrug.holderName}</td></tr>
				 	 </c:forEach>
					</table>					
					</c:if>
				</td>
				<td>${studyAgent.investigationalNewDrugIndicator ? 'Yes' : 'No'}</td>
				<td>${studyAgent.partOfLeadIND ? 'Yes' : 'No' }</td>
			</tr>
		</c:forEach>				
		<c:if test="${empty command.studyAgents}">
			<tr class="results">
				<td colspan="5">No agents are associated to this study</td>
			</tr>
		</c:if>
		</table>
    </chrome:division>
         
    <chrome:division title="Treatment Assignments">
        	<table class="tablecontent" width="96%" >
            	<tr>
                 <th scope="col">Code</th>
                 <th scope="col">Dose level order</th>
                 <th scope="col">Description</th>
                 <th scope="col">Comments</th>
            	</tr>
            	<c:forEach items="${command.treatmentAssignments}" var="treatmentAssignment" >
                    <tr class="results">
                        <td>${treatmentAssignment.code}</td>
                        <td>${treatmentAssignment.doseLevelOrder}</td>
                        <td>${treatmentAssignment.description}</td>
                        <td>${treatmentAssignment.comments}</td>
                    </tr>
            	</c:forEach>
            	<c:if test="${empty command.treatmentAssignments}">
            	 <tr class="results">
            	  <td colspan="4">No treatment assignment code(TAC) is available to this study</td>
            	 </tr>
            	</c:if>
        	</table>
	</chrome:division>
		
		       
    <chrome:division title="Sites">
       		<table class="tablecontent" width="96%" >
				<tr>
					<th scope="col">Study Site</th>
				</tr>
				<c:forEach items="${command.studySites}" var="studySite">
				<tr class="results">
					<td>${studySite.organization.name}</td>
				</tr>
				</c:forEach>
				<c:if test="${empty command.studySites}">
				<tr><td class="results">No sites are associated to this study</td></tr>
				</c:if>
			</table>	
	</chrome:division>
   	<chrome:division title="Investigators">
    	<c:set var="invCnt" value="0" />
        <table class="tablecontent" width="96%" >
            <tr>
                <th scope="col">Investigator</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>
            <c:forEach items="${command.studyOrganizations}" var="studySite" >
                <c:forEach items="${studySite.studyInvestigators}" var="studyInvestigator" >
                    <tr class="results">
                        <td>${studyInvestigator.siteInvestigator.investigator.fullName}</td>
                        <td>${studyInvestigator.roleCode}</td>
                        <td>${studyInvestigator.statusCode}</td>
                    </tr>
                    <c:set var="invCnt" value="${invCnt + 1}" />
                </c:forEach>
            </c:forEach>
            <c:if test="${invCnt eq 0}">
				<tr class="results">
				 <td colspan="3">No investigators are assigned to this study</td>
				</tr>
            </c:if>
        </table>
    	</chrome:division>
   
    <chrome:division title="Personnel">
    	<c:set var="staffCnt" value="0" />
        <table class="tablecontent" width="96%" >
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>
            <c:forEach items="${command.studyOrganizations}" var="studySite" >
                <c:forEach items="${studySite.studyPersonnels}" var="studyPersonnel">
                    <tr class="results">
                        <td>${studyPersonnel.researchStaff.fullName}</td>
                        <td>${studyPersonnel.roleCode}</td>
                        <td>${studyPersonnel.statusCode}</td>
                    </tr>
                    <c:set var="staffCnt" value="${invCnt + 1}" />
                </c:forEach>
            </c:forEach>
            <c:if test="${staffCnt eq 0}">
				<tr class="results">
				 <td colspan="3">No research staff assigned to this study yet.</td>
				</tr>
            </c:if>
        </table>
    </chrome:division>

    <c:if test="${command.diseaseTerminology.diseaseCodeTerm == 'MEDDRA'}">
            <c:if test="${not empty command.meddraStudyDiseases}">
            <chrome:division title="Diseases">
                <table class="tablecontent" width="96%" >
                    <br>
                    <tr>
                        <th scope="col">Disease Term</th>
                        <th scope="col">MedDRA Code</th>
                    </tr>

                    <c:forEach items="${command.meddraStudyDiseases}" var="studyDisease">
                        <tr class="results">
                            <td>${studyDisease.term.meddraTerm}</td>
                            <td>${studyDisease.term.meddraCode}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </chrome:division>
            </c:if>
    </c:if>

    <c:if test="${command.diseaseTerminology.diseaseCodeTerm == 'CTEP'}">
            <c:if test="${not empty command.ctepStudyDiseases}">
            <chrome:division title="Diseases">
                <table class="tablecontent" width="96%" >
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
    </c:if>

    <c:if test="${command.diseaseTerminology.diseaseCodeTerm == 'OTHER'}">
            <c:if test="${not empty command.studyConditions}">
            <chrome:division title="Diseases">
                <table class="tablecontent" width="96%" >
                    <br>
                    <tr>
                        <th scope="col">Disease Term</th>
                    </tr>

                    <c:forEach items="${command.studyConditions}" var="studyDisease">
                        <tr class="results">
                            <td>${studyDisease.term.conditionName}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </chrome:division>
            </c:if>
    </c:if>

	<chrome:division title="Identifiers">
			<table class="tablecontent" width="96%">
			<tr>
				<th scope="col">Assigning Authority</th>
				<th scope="col">Identifier Type</th>
				<th scope="col">Identifier</th>
			</tr>
			<c:forEach items="${command.identifiersLazy}" var="identifier">
			<tr class="results">
				<c:if	test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					<td>${identifier.organization}</td>
				</c:if>
				<c:if 	test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
					<td>${identifier.systemName}</td>
				</c:if>
				<td>${identifier.type}</td>
				<td>${identifier.value}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty command.identifiersLazy}">
			<tr>
			  <td colspan="3">No identifier is assigned to this study</td>
			</tr>
			</c:if>
			</table>
			<br>
			</chrome:division>
			
	<chrome:division title="Evaluation Period Types & Solicited Adverse Events">
  		<study:solicitedAETable displayOnly="true" />
    </chrome:division>

    </jsp:attribute>
</tags:tabForm>

</body>
</html>
