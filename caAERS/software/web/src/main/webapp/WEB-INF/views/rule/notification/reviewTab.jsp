<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not implemented</title>
  	<style type="text/css">
  		.label {
  			float:left;
			font-weight:bold;
			margin-left:0.5em;
			text-align:right;
  		}
  		.value {
  			padding-left:15px;
  		}
  		div.row div.label { width: 11em; }
        div.mandatoryField div.label { width: 21em; }
        div.mandatoryField div.value { margin-left: 22em; }
  	</style>
    <script language="javascript">
    	
    	 
    </script>
    <link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
</head>
<body>

    <tags:tabForm tab="${tab}" flow="${flow}" >
    <jsp:attribute name="header">
    	<div class="summary">
    		<caaers:message code="header.reportdefinition.review" arguments="${command.reportDefinition.name},${command.reportDefinition.organization.name}" />
		</div>
    </jsp:attribute>
    <jsp:attribute name="instructions">
    	<tags:instructions code="createrulereview" />
    </jsp:attribute>
	<jsp:attribute name="repeatingFields">
		  <tags:errors path="*"/>
		  <%--
		<!-- Basic Details -->
		<chrome:division title="Basic Details">
		 
		  <c:forEach items="${FIELDS['Basic Details']}" var="pair">
		    <rd:renderPair pair="${pair}" />
		  </c:forEach>
		</chrome:division>
		--%>
		<!-- Delivery Details -->
		<chrome:division title="Report Delivery Definitions">
		  <table width="100%" class="tablecontent">
			<tr>
			<th width="5%" class="tableHeader">Recipient type</th>
 			 <th width="30%" class="tableHeader">Name</th>
			 <th width="55%" class="tableHeader">Address/Role</th>
			 <th width="10%" class="tableHeader">Report format</th>
			</tr>
		    <c:forEach items="${FIELDS['rdd']}" var="pair" varStatus="status">
		      <tr class="${status.index % 2 ne 0 ? 'even' : 'odd'}">
		    	<td>${pair.key}</td><td>${pair.value}</td><td>${pair.attribute1}</td><td>${pair.attribute2}</td>
			  </tr>
		    </c:forEach>
		  </table>
		  <br>
		</chrome:division>
		<!--  Mandatory Fields  -->
		<chrome:division title="Report Mandatory Fields">
		   <table width="100%" class="tablecontent">
			<tr>
				<th width="35%">Section</th>
				<th width="65%">Field</th>
			</tr>
            <tr>
                <td><caaers:message code="LBL_captureAdverseEvents.heading.adverseEvents" /></td>
                <td><rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION~Adverse events" readOnly="true" /></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.treatmentInformation.heading" /></td>
                <td><rd:renderMandatoryFields key="TREATMENT_INFO_SECTION" readOnly="true"/></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.reporter.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details" readOnly="true"/>
					<rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details~Address" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.physician.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details" readOnly="true"/>
					<rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details~Address" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.responseDescription.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="0" endIndex="5" readOnly="true" />
                    <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="6" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><caaers:message code="LBL_aeReport.participantHistory.subjectDetails.heading" /></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.participantHistory.general.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION" readOnly="true"/>
                    <caaers:message var="x" code="LBL_aeReport.diseaseHistory.diagnosisDate" />
			  	    <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~${x}" heading="${x}" tabular="true" readOnly="true"/>
                    <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Weight" heading="Weight" tabular="true"  readOnly="true"/>
  	    	        <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Height" heading="Height" tabular="true" readOnly="true" />
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.saeReportPreExistingConditions.heading" /></td>
                <td><rd:renderMandatoryFields key="PRE_EXISTING_CONDITION_SECTION~Pre-existing conditions" readOnly="true" /></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.diseaseHistory.metastaticDiseaseSites.heading" /></td>
                <td><rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Metastatic disease sites" readOnly="true"/></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.saeReportPriorTherapies.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys" readOnly="true"/>
                    <caaers:message var="x" code="LBL_aeReport.saeReportPriorTherapies.startDate" />
                    <rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~${x}" heading="${x}" tabular="true" readOnly="true"/>
                    <caaers:message var="x" code="LBL_aeReport.saeReportPriorTherapies.endDate" />
                    <rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~${x}" heading="${x}" tabular="true" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.concomitantMedications.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications" readOnly="true" />
                    <caaers:message var="x" code="LBL_aeReport.concomitantMedications.startDate" />
                    <rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~${x}" heading="${x}" tabular="true" readOnly="true" />
                    <caaers:message var="x" code="LBL_aeReport.concomitantMedications.endDate" />
                    <rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~${x}" heading="${x}" tabular="true" readOnly="true"/>
                </td>
            </tr>

            <tr>
                <td colspan="2"><caaers:message code="LBL_aeReport.studyInterventions.heading" /></td>
            </tr>
           <tr>
               <td><caaers:message code="LBL_aeReport.studyInterventions.agents.heading" /></td>
               <td>
                   <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION~Study Agents" readOnly="true"/>
                   <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION" readOnly="true"/>
               </td>
           </tr>

            <tr>
                <td><caaers:message code="LBL_aeReport.radiationInterventions.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="RADIATION_INTERVENTION_SECTION~RadiationInterventions" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td> <caaers:message code="LBL_aeReport.surgeryInterventions.heading" /></td>
                <td><rd:renderMandatoryFields key="SURGERY_INTERVENTION_SECTION~SurgeryInterventions" readOnly="true" /></td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.medicalDevices.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="0" endIndex="10" readOnly="true" />
                    <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="11" readOnly="true"/>
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.labs.heading" /></td>
                <td><rd:renderMandatoryFields key="LABS_SECTION~Labs" readOnly="true"/>
                    <rd:renderMandatoryFields key="LABS_SECTION~Labs~Baseline" heading="Baseline" tabular="true" readOnly="true"/>
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Worst" heading="Worst" tabular="true" readOnly="true"/>
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Recovery" heading="Recovery" tabular="true" readOnly="true" />
                </td>
            </tr>
            <tr>
                <td><caaers:message code="LBL_aeReport.otherCauses.heading" /></td>
                <td><rd:renderMandatoryFields key="OTHER_CAUSE_SECTION~OtherCausess" readOnly="true"/></td>
            </tr>
			<tr>
                <td><caaers:message code="LBL_aeReport.additionalInformation.heading" /></td>
                <td>
                    <rd:renderMandatoryFields key="ADDITIONAL_INFO_SECTION" readOnly="true"/>
                </td>
            </tr>
			
		   </table>
		</chrome:division> 
		<!-- Notification Details -->	
		<chrome:division title="Notifications">
		 <c:forEach items="${FIELDS.PENF}" var="entry">
		 	<chrome:division title="${entry.key}" >
		 		<c:forEach items="${entry.value}" var="pair">
		 			<rd:renderPair pair="${pair}" preformatedValue="${pair.key eq 'Message'}" />
		 		</c:forEach>
		 	</chrome:division>
		 </c:forEach>
		
		</chrome:division>
        <input id="markFinish" type="hidden" name="_finish"/>
	</jsp:attribute>
</tags:tabForm> 

</body>
</html>