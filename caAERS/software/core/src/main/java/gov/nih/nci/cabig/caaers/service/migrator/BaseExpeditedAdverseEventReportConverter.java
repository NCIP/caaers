/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.DietarySupplementIntervention;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Dose;
import gov.nih.nci.cabig.caaers.domain.EventStatus;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory.Measure;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent;
import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Submitter;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.BehavioralInterventionAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.BiologicalInterventionAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DietarySupplementInterventionAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.GeneticInterventionAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherInterventionAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdditionalInformationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReportingPeriodType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventResponseDescriptionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BaseAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DateValueType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DeviceAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DoseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GeneticInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GeneticInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.LabType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MetastaticDiseaseSiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherAEInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PriorTherapyAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportDeliveryDefinitionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportVersionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportedAE;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SubmitterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.TreatmentAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.TreatmentInformationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

/**
 * @author chandrasekaravr
 */

public class BaseExpeditedAdverseEventReportConverter {
	
	ExpeditedAdverseEventReportConverterUtility utility = new ExpeditedAdverseEventReportConverterUtility();
    
    private MessageSource messageSource;
	
    private StudyDao studyDao;
    
	public void setStudyDao(StudyDao studyDao) {
		utility.setStudyDao(studyDao);
	}


	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public ExpeditedAdverseEventReport convert( BaseAdverseEventReport aeReportDto) {
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();

		aeReport.setCreatedAt(XMLUtil.toTimestamp(aeReportDto.getCreatedAt()));
        aeReport.setExternalId(aeReportDto.getExternalId());
        //reporting period
        if(aeReportDto.getAdverseEventReportingPeriod() != null) {
            AdverseEventReportingPeriod reportingPeriod = utility.convertAdverseEventReportingPeriod(aeReportDto.getAdverseEventReportingPeriod());
            reportingPeriod.addAeReport(aeReport);
        }
        
        //Adverse Events
        for(ReportedAE aeType : aeReportDto.getReportedAE()){
            if ( aeReport.getReportingPeriod() != null) {

                AdverseEvent ae = new AdverseEvent();
                ae.setExternalId(aeType.getExternalId());
                aeReport.getReportingPeriod().addAdverseEvent(ae);
                aeReport.addAdverseEvent(ae);
                
            }
        }
       
        //reporter
        if(aeReportDto.getReporter() != null){
            aeReport.setReporter(utility.convertReporter(aeReportDto.getReporter()));
        }


		//physician
        if(aeReportDto.getPhysician() != null){
            aeReport.setPhysician(utility.convertPhysician(aeReportDto.getPhysician()));
        }

        
        //Reports
		for(ReportType xmlReportType : aeReportDto.getReport()){
            Report report = utility.convertReport(xmlReportType, null ); //no submitter info
			aeReport.addReport(report);

            //special case if external data collection do not have external Id, add case number
            if(StringUtils.isEmpty(aeReport.getExternalId())) aeReport.setExternalId(report.getCaseNumber());
		}
		
		// Set the study Information to the Source Report.
		return aeReport;
	}
}