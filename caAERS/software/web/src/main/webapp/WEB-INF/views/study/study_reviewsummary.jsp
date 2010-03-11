<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/caaers/css/solicited_ae.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:dwrJavascriptLink objects="createStudy"/>
<title>${tab.longTitle}</title>
<style type="text/css">
       .label { width: 12em; padding: 1px;  margin-right: 0.5em; } 
       div.row div.value  {white-space:normal;}
	   #studyDetails td.label { font-weight: bold; float: left; margin-left: 0.5em; margin-right: 0.5em; width:12em; padding: 1px; }
</style>
<!--[if lte IE 6]>
<style>
	#main {
		top:50px;
	}
</style> 
<![endif]-->

<script type="text/javascript">
	Event.observe(window, "load", function(){
		var openStudyBtn = $('open-study-btn');
		if(openStudyBtn){
			openStudyBtn.observe("click", function(){
				openStudyBtn.writeAttribute('disabled', 'disabled');
				createStudy.openStudy(function(status){
					$('data-entry-status-div').innerHTML = status;
				})
			});
		}
	});
</script>
</head>
<body>

<tags:instructions code="study.study_overview.top" />
<c:if test="${command.study.id > 0}">
    <div class="instructions row">
    	<div class="value">
    		<a href='<c:url value="/pages/study/export?id=${command.study.id}" />'><img src="<c:url value="/images/blue/xml-icon.png"/> " border=""> Export XML</a>
    	</div>
    </div>
</c:if>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review" hideBox="true">
    <jsp:attribute name="repeatingFields">
       <c:if test="${(empty command.study.id) or ( command.study.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
	   <div class="summary">
        <chrome:division>

        		<div class="row">
                	<div class="label">Primary identifier</div>
                	<div class="value">${command.study.primaryIdentifier.value} </div>
            	</div>
           		<div class="row">
                	<div class="label">Short title</div>
                	<div class="value">${command.study.shortTitle} </div>
            	</div>
            	<div class="row">
                	<div class="label">Long Title</div>
                	<div class="value">${command.study.longTitle} </div>
            	</div>
            	<div class="row">
                	<div class="label">Precis</div>
                	<div class="value">${command.study.precis} </div>
            	</div>
            	<div class="row">
                	<div class="label">Description</div>
                	<div class="value">${command.study.description} </div>
            	</div>
            	<div class="row">
                	<div class="label">Funding sponsor</div>
                	<c:if test="${command.study.primaryFundingSponsorOrganization.externalId != null}">
                		<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                	</c:if>                	
                	<div class="value">${command.study.primaryFundingSponsorOrganization.name} </div>
            	</div>
				<div class="row">
                	<div class="label">Coordinating center</div>
                	<div class="value">
                	<c:if test="${command.study.studyCoordinatingCenter.organization.externalId != null}">
                		<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                	</c:if>
                	${command.study.studyCoordinatingCenter.organization.name} 
                	</div>
            	</div>

        		<div class="row">
                	<div class="label">Phase code</div>
                	<div class="value">${command.study.phaseCode} </div>
            	</div>
            	<div class="row">
                	<div class="label">Status</div>
                	<div class="value">${command.study.status} </div>
            	</div>	
            	<div class="row">
                	<div class="label">Terminology</div>
                	<div class="value">${command.study.aeTerminology.term} </div>
            	</div>	
            	<div class="row">
                	<div class="label">Terminology Version</div>
                	<div class="value">${command.study.aeTerminology.term eq 'CTC' ? command.study.aeTerminology.ctcVersion.name : command.study.aeTerminology.meddraVersion.name} </div>
            	</div>
            	<c:if test="${command.study.aeTerminology.term eq 'CTC'}">
            	<div class="row">
	            	<div class="label">Other MedDRA</div>
	            	<div class="value">${command.study.otherMeddra.name}</div>
	            </div>
            	</c:if>
            	<div class="row">
                	<div class="label">Multi institutional</div>
                	<div class="value">${command.study.multiInstitutionIndicator == null ? '' : command.study.multiInstitutionIndicator ? 'Yes' : 'No'} </div>
            	</div>
            	<div class="row">
                	<div class="label">AdEERS reporting</div>
                	<div class="value">${command.study.adeersReporting == null ? '' : command.study.adeersReporting ? 'Yes' : 'No'} </div>
            	</div>
            	<div class="row">
                	<div class="label"><caaers:message code="LBL_study.verbatimFirst" /></div>
                	<div class="value">${command.study.verbatimFirst == null ? '' : command.study.verbatimFirst ? 'Yes' : 'No'}</div>
            	</div>
            	<div class="row">
                	<div class="label">Data Entry Status</div>
                	<div id="data-entry-status-div" class="value">${command.dataEntryStatus} </div>
            	</div>		
            		
            	<div class="row">
                	<div class="label"></div>
                	<div class="value">
                        <c:if test="${editFlow and not command.dataEntryComplete}">
                             <tags:button id="open-study-btn" type="button" value="Study Set-up Complete" color="green" icon="check" size="small"/>
                        </c:if>
                	</div>
            	</div>


       </chrome:division>
    <chrome:division title="Report Formats">
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
        <ul>
            <c:forEach items="${command.study.reportFormats}" var="report">
                <li class="results">${report.reportFormatType.displayName}</li>
            </c:forEach>
        </ul>
		
		<c:if test="${empty command.study.reportFormats}">
		
		No report format is selected for this study

		</c:if>																		

    </chrome:division>       
    <chrome:division title="Therapies">
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
		<ul>
		<c:forEach items="${command.study.studyTherapies}" var="therapy">
		<li class="results">
			${therapy.studyTherapyType.displayName}
		</li>
		</c:forEach>
		</ul>
		<c:if test="${empty command.study.studyTherapies}">

			No therapy is selected for this study

		</c:if>																		

    </chrome:division>
    <chrome:division title="Agents">
    	<br/>
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
		<table class="tablecontent" width="100%" >
		<tr >						
			<th scope="col">Agent name</th>
			<th scope="col">Agent NSC<br />number</th>
			<th scop="col">IND indicator</th>
			<th scope="col">IND #</th>
			<th scope="col">Investigational new <br /> drug?</th>
			<th scope="col">Part of <br />lead IND?</th>	
		</tr>																			
	 	<c:set var="activeAgentCnt" value="0" />    
		<c:forEach items="${command.study.studyAgents}" var="studyAgent">
			<c:if test="${not studyAgent.retired}">
			<c:set var="activeAgentCnt" value="${activeAgentCnt + 1}" />
			<tr>						
				<td>${studyAgent.agentName}</td>
				<td>${studyAgent.agent.nscNumber}</td>
				<td>${studyAgent.indType.displayName }</td>
				<td>
					<c:if test="${fn:length(studyAgent.studyAgentINDAssociations) > 0}">
                         <c:forEach items="${studyAgent.studyAgentINDAssociations }" var="sai">
                              ${sai.investigationalNewDrug.strINDNo},&nbsp;&nbsp;${sai.investigationalNewDrug.holderName}
                         </c:forEach>
					</c:if>
				</td>
				<td>${studyAgent.investigationalNewDrugIndicator ? 'Yes' : 'No'}</td>
				<td>${studyAgent.partOfLeadIND ? 'Yes' : 'No' }</td>
			</tr>
			
			</c:if>
		</c:forEach>				
		<c:if test="${activeAgentCnt lt 1}">
			<tr class="results">
				<td colspan="5">No agents are associated to this study</td>
			</tr>
		</c:if>
		</table>
    </chrome:division>
    
    <c:set var="activeTACnt" value="0" />     
    <chrome:division title="Treatment Assignments">
    	<br/>
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
        	<table class="tablecontent" width="100%" >
            	<tr>
                 <th scope="col">Code</th>
                 <th scope="col">Dose level order</th>
                 <th scope="col">Description</th>
                 <th scope="col">Comments</th>
            	</tr>
            	<c:forEach items="${command.study.treatmentAssignments}" var="treatmentAssignment" >
                    <c:if test="${not treatmentAssignment.retired}">
                    <c:set var="activeTACnt" value="${activeTACnt + 1}" />     
                    <tr class="results">
                        <td>${treatmentAssignment.code}</td>
                        <td>${treatmentAssignment.doseLevelOrder}</td>
                        <td>${treatmentAssignment.description}</td>
                        <td>${treatmentAssignment.comments}</td>
                    </tr>
                    </c:if>
            	</c:forEach>
            	<c:if test="${activeTACnt lt 1}">
            	 <tr class="results">
            	  <td colspan="4">No treatment assignment code(TAC) is available to this study</td>
            	 </tr>
            	</c:if>
        	</table>
	</chrome:division>
		
	<c:set var="activeSiteCnt" value="0" />    	       
    <chrome:division title="Sites">
    	<br/>
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
       		<table class="tablecontent" width="100%" >
				<tr><th scope="col"><caaers:message code="LBL_StudySite" /></th><th scope="col"><caaers:message code="LBL_status" /></th></tr>
				<c:forEach items="${command.study.studySites}" var="studySite">
                    <c:if test="${!studySite.retired}">
                        <c:set var="activeSiteCnt" value="${activeSiteCnt + 1}" />
                        <tr class="results">
                            <td>
                                <c:if test="${studySite.organization.externalId != null}"><img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/></c:if>
                                ${studySite.organization.name}
                            </td>
                            <td>${studySite.retired ? "<font color='black'>Retired</font>" : "<font color='black'>Active</font>"}</td>
                        </tr>
                    </c:if>
				</c:forEach>
				<c:if test="${activeSiteCnt lt 1}">
				<tr><td class="results">No sites are associated to this study</td></tr>
				</c:if>
			</table>	
	</chrome:division>
   	<chrome:division title="Investigators">
   		<br/>
   		<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
    	<c:set var="invCnt" value="0" />
        <table class="tablecontent" width="100%" >
            <tr>
                <th scope="col">Investigator</th>
                <th scope="col">Organization</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>
            <c:forEach items="${command.study.uniqueStudyOrganizations}" var="studySite" >
                <c:forEach items="${studySite.studyInvestigators}" var="studyInvestigator" >
                    <tr class="results">
                        <td>
                            <c:if test="${studyInvestigator.siteInvestigator.investigator.externalId != null}">
                                <img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                            </c:if>
                                ${studyInvestigator.siteInvestigator.investigator.fullName}
                        </td>
                        <td>${studyInvestigator.siteInvestigator.organization}</td>
                        <td>${command.studyInvestigatorRoles[studyInvestigator.roleCode]}</td>
                        <td>
                            <c:if test="${studyInvestigator.active}">Active</c:if>
                            <c:if test="${!studyInvestigator.active}">Inactive</c:if>
                        </td>
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
    	<br/>
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
    	<c:set var="staffCnt" value="0" />
        <table class="tablecontent" width="100%" >
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Organization</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
            </tr>

            <c:set var="orgIndex" />

<%--
            <c:forEach items="${command.study.uniqueStudyOrganizations}" var="studySite" >
                ${studySite}
            </c:forEach>
--%>

            <c:forEach items="${command.study.uniqueStudyOrganizations}" var="studySite" >
                <c:set var="orgIndex" value="0" />
                <c:forEach items="${studySite.studyPersonnels}" var="studyPersonnel" varStatus="i">
                    <c:if test="${not i.first}"><c:set var="orgIndex" value="1" /></c:if>
                    <tr class="results">
                        <td>
                            <c:if test="${studyPersonnel.siteResearchStaff.researchStaff.externalId != null}">
                                <img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                            </c:if>
                                ${studyPersonnel.siteResearchStaff.researchStaff.fullName}
                        </td>
                        <%-- orgIndex 0 means a new Organiation in the list and should be rendered, all are rendered now --%>
                        <td><c:if test="${orgIndex >= 0}">${studyPersonnel.siteResearchStaff.organization}</c:if></td>
                        <td>${command.studyPersonnelRoles[studyPersonnel.roleCode]}</td>
                        <td>
                            <c:if test="${studyPersonnel.active}">Active</c:if>
                            <c:if test="${!studyPersonnel.active}">Inactive</c:if>
                        </td>
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

    <c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'MEDDRA'}">
            <c:if test="${not empty command.study.activeMeddraStudyDiseases}">
            <chrome:division title="Diseases">
            	<br/>
            	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
                <table class="tablecontent" width="100%" >
                    
                    <tr>
                        <th scope="col">Disease Term</th>
                        <th scope="col">MedDRA Code</th>
                    </tr>

                    <c:forEach items="${command.study.activeMeddraStudyDiseases}" var="studyDisease">
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

    <c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'CTEP'}">
            <c:if test="${not empty command.study.activeCtepStudyDiseases}">
            <chrome:division title="Diseases">
            	
            	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
                <table class="tablecontent" width="100%" >
                    <br>
                    <tr>
                        <th scope="col">Primary</th>
                        <th scope="col">Disease Term</th>
                    </tr>

                    <c:forEach items="${command.study.activeCtepStudyDiseases}" var="studyDisease">
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

    <c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'OTHER'}">
            <c:if test="${not empty command.study.activeStudyConditions}">
            <chrome:division title="Diseases">
            	<br/>
            	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
                <table class="tablecontent" width="100%" >
                    <br>
                    <tr>
                        <th scope="col">Disease Term</th>
                    </tr>

                    <c:forEach items="${command.study.activeStudyConditions}" var="studyDisease">
                        <tr class="results">
                            <td>${studyDisease.term.conditionName}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </chrome:division>
            </c:if>
    </c:if>

    <%-- START EXPECTED AEs --%>
    <chrome:division title="Expected AEs">
    	
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
       <table class="tablecontent" width="100%" >
           <br>

           <c:if test="${command.study.aeTerminology.term eq 'MEDDRA'}">
               <c:set var="terms" value="${command.study.expectedAEMeddraLowLevelTerms}" />
           </c:if>

           <c:if test="${command.study.aeTerminology.term eq 'CTC'}">
               <c:set var="terms" value="${command.study.expectedAECtcTerms}" />
           </c:if>

           <tr><th scope="col">Expected AEs</th></tr>
           <c:forEach items="${terms}" var="term">
               <tr class="results">
                   <td>${term.fullName} <c:if test="${command.study.aeTerminology.term eq 'CTC' && term.otherMeddraTerm != null}">(${term.otherMeddraTerm.fullName})</c:if></td>
               </tr>
           </c:forEach>
       </table>
       <br>
    </chrome:division>
    <%-- STOP EXPECTED AEs --%>

    <chrome:division title="Identifiers">
    	<br/>
    	<!--[if lte IE 6]>
		<br>
		<br>
		<![endif]-->
			<table class="tablecontent" width="100%">
			<tr>
				<th scope="col">Assigning Authority</th>
				<th scope="col">Identifier Type</th>
				<th scope="col">Identifier</th>
			</tr>
			<c:forEach items="${command.study.identifiersLazy}" var="identifier">
			<tr class="results">
				<c:if test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					<td>
				<c:if test="${identifier.organization.externalId != null}">
                		<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                	</c:if> 
					
					${identifier.organization}</td>
				</c:if>
				<c:if test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
					<td>${identifier.systemName}</td>
				</c:if>
				<td>${identifier.type}</td>
				<td>${identifier.value}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty command.study.identifiersLazy}">
			    <tr><td colspan="3">No identifier is assigned to this study</td></tr>
			</c:if>
			</table>
			<br>
    </chrome:division>
			
	<chrome:division title="Evaluation Period Types & Solicited Adverse Events">
  		<study:solicitedAETable displayOnly="true" />
    </chrome:division>
	</div>
    </jsp:attribute>
    <jsp:attribute name="localButtons"></jsp:attribute>
</tags:tabForm>

</body>
</html>
