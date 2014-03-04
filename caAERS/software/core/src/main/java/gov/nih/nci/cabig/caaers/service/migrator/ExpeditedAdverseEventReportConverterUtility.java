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
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentAdjustment;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
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
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdditionalInformationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReportingPeriodType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventResponseDescriptionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DateValueType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DoseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GeneticInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.LabType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MetastaticDiseaseSiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherAEInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PriorTherapyAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportDeliveryDefinitionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportVersionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteRefType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SubmitterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.TreatmentAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.TreatmentInformationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

/**
 * @author vinodh.rc
 */

public class ExpeditedAdverseEventReportConverterUtility {
	
	  /** The Constant EMAIL. key for the e-mail address */
    protected static final String EMAIL = "e-mail";

    /** The Constant FAX. key for the fax number */
    protected static final String FAX = "fax";

    /** The Constant PHONE. key for the phone number */
    protected static final String PHONE = "phone";
    
    private MessageSource messageSource;
	
    private StudyDao studyDao;
    
	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}


	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	
	protected List<Lab> mergeLabs(List<Lab> allLabs){
		List<Lab> mergedLabs = new ArrayList<Lab>();
		Map<String,Lab> map = new HashMap<String,Lab>();
		for(Lab lab :allLabs){
			if(map.get(lab.getLabTerm().getTerm()) == null){
				map.put(lab.getLabTerm().getTerm(),lab);
			} else {
				if(lab.getBaseline().getValue() != null && map.get(lab.getLabTerm().getTerm()).getBaseline().getValue() == null){
					addBaseline(lab, map.get(lab.getLabTerm().getTerm()));
				}
				if(lab.getNadir().getValue() != null && map.get(lab.getLabTerm().getTerm()).getNadir().getValue() == null){
					addNadir(lab, map.get(lab.getLabTerm().getTerm()));
				}
				if(lab.getRecovery().getValue() != null && map.get(lab.getLabTerm().getTerm()).getRecovery().getValue() == null){
					addRecovery(lab, map.get(lab.getLabTerm().getTerm()));
				}
			}
		}
		
		mergedLabs.addAll(map.values());
		
		
		return mergedLabs;
	}
	
	protected void addBaseline(Lab labSrc,Lab labTarget){
		labTarget.getBaseline().setValue(labSrc.getBaseline().getValue());
		if(labSrc.getBaseline().getDate() != null){
			labTarget.getBaseline().setDate(labSrc.getBaseline().getDate());
		}
	}
	
	protected void addNadir(Lab labSrc,Lab labTarget){
		labTarget.getNadir().setValue(labSrc.getNadir().getValue());
		if(labSrc.getNadir().getDate() != null){
			labTarget.getNadir().setDate(labSrc.getNadir().getDate());
		}
	}
	
	protected void addRecovery(Lab labSrc,Lab labTarget){
		labTarget.getRecovery().setValue(labSrc.getRecovery().getValue());
		if(labSrc.getRecovery().getDate() != null){
			labTarget.getRecovery().setDate(labSrc.getRecovery().getDate());
		}
	}
	
	protected OtherAEIntervention convertOtherAEIntervention(OtherAEInterventionType xmlOtherAEInterventionType, ExpeditedAdverseEventReport report){
		OtherAEIntervention otherAEIntervention = new OtherAEIntervention();
		otherAEIntervention.setDescription(xmlOtherAEInterventionType.getDescription());
		if(xmlOtherAEInterventionType.getStudyIntervention() != null){
			otherAEIntervention.setStudyIntervention(convertOtherIntervention(xmlOtherAEInterventionType.getStudyIntervention()));
		}
		otherAEIntervention.setReport(report);
		
		return otherAEIntervention;
	}
	
	protected BehavioralIntervention convertBehavioralIntervention(BehavioralInterventionType xmlBehavioralInterventionType, ExpeditedAdverseEventReport report){
		BehavioralIntervention behavioralIntervention= new BehavioralIntervention();
		behavioralIntervention.setDescription(xmlBehavioralInterventionType.getDescription());
		if(xmlBehavioralInterventionType.getStudyIntervention() != null){
			behavioralIntervention.setStudyIntervention(convertOtherIntervention(xmlBehavioralInterventionType.getStudyIntervention()));
		}
		behavioralIntervention.setReport(report);
		
		return behavioralIntervention;
	}
	
	protected GeneticIntervention convertGeneticIntervention(GeneticInterventionType xmlGeneticInterventionType, ExpeditedAdverseEventReport report){
		GeneticIntervention geneticIntervention= new GeneticIntervention();
		geneticIntervention.setDescription(xmlGeneticInterventionType.getDescription());
		if(xmlGeneticInterventionType.getStudyIntervention() != null){
			geneticIntervention.setStudyIntervention(convertOtherIntervention(xmlGeneticInterventionType.getStudyIntervention()));
		}
		geneticIntervention.setReport(report);
		
		return geneticIntervention;
	}
	
	
	protected Report convertReport(ReportType xmlReportType, SubmitterType xmlSubmitterType){
		Report report = new Report();
	//	report.setRequired(xmlReportType.isRequired());
		if(xmlReportType.getCaseNumber() != null){
			report.setCaseNumber(xmlReportType.getCaseNumber());
		}
		if ( xmlReportType.isManuallySelected() != null ) {
			report.setManuallySelected(xmlReportType.isManuallySelected());
		}
		
		if(xmlReportType.getReviewStatus() != null){
			report.setReviewStatus(ReviewStatus.valueOf(xmlReportType.getReviewStatus().name()));
		}
		
		for(ReportVersionType xmlReportVersionType: xmlReportType.getAeReportVersion()){
			ReportVersion reportVersion = new ReportVersion();
			if(xmlReportVersionType.getAmendedOn() != null){
				reportVersion.setAmendedOn(xmlReportVersionType.getAmendedOn().toGregorianCalendar().getTime());
			}
			if(xmlReportVersionType.getDueOn() != null){
				reportVersion.setDueOn(xmlReportVersionType.getDueOn().toGregorianCalendar().getTime());
			}
			if(xmlReportVersionType.getWithdrawnOn() != null){
				reportVersion.setWithdrawnOn(xmlReportVersionType.getWithdrawnOn().toGregorianCalendar().getTime());
			}
			if(xmlReportVersionType.getSubmittedOn() != null){
				reportVersion.setSubmittedOn(xmlReportVersionType.getSubmittedOn().toGregorianCalendar().getTime());
			}
			if(xmlReportVersionType.getCreatedOn() != null){
				reportVersion.setCreatedOn(xmlReportVersionType.getCreatedOn().toGregorianCalendar().getTime());
			}
			
			reportVersion.setPhysicianSignoff(xmlReportVersionType.isPhysicianSignoff());
			if(xmlReportVersionType.getReportVersionId() == null){
				reportVersion.setReportVersionId("0");
			} else {
				reportVersion.setReportVersionId((xmlReportVersionType.getReportVersionId()));
			}
			reportVersion.setSubmissionMessage(xmlReportVersionType.getSubmissionMessage());
			reportVersion.setSubmissionUrl(xmlReportVersionType.getSubmissionUrl());
			reportVersion.setCcEmails(xmlReportVersionType.getCcEmails());
			if(xmlReportVersionType.getReportStatus() != null){
				reportVersion.setReportStatus(ReportStatus.valueOf(xmlReportVersionType.getReportStatus().name()));
			}
			
			if(xmlSubmitterType != null){
				Submitter submitter = convertSubmitter(xmlSubmitterType);
				reportVersion.setSubmitter(submitter);
			}
			
			report.addReportVersion(reportVersion);
		}
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName(xmlReportType.getAeReportDefinition().getName());
		reportDefinition.setLabel(xmlReportType.getAeReportDefinition().getLabel());
		reportDefinition.setDuration(xmlReportType.getAeReportDefinition().getDuration());
		if(xmlReportType.getAeReportDefinition().getGroup() != null){
			ConfigProperty group = new ConfigProperty();
			group.setCode(xmlReportType.getAeReportDefinition().getGroup().getCode());
			group.setName(xmlReportType.getAeReportDefinition().getGroup().getName());
			reportDefinition.setGroup(group);
		}
		
		if(xmlReportType.getAeReportDefinition().getTimeScaleUnitType() != null && xmlReportType.getAeReportDefinition().getTimeScaleUnitType().name() != null){
			reportDefinition.setTimeScaleUnitType(TimeScaleUnit.valueOf(xmlReportType.getAeReportDefinition().getTimeScaleUnitType().name()));
		}
		report.setReportDefinition(reportDefinition);
		for(ReportDeliveryDefinitionType xmlReportDeliveryDefinitionType : xmlReportType.getAeReportDefinition().getReportDeliveryDefinition()){
			ReportDeliveryDefinition reportDeliveryDefinition = new ReportDeliveryDefinition();
			reportDeliveryDefinition.setEndPoint(xmlReportDeliveryDefinitionType.getEndPoint());
			reportDeliveryDefinition.setEndPointType(xmlReportDeliveryDefinitionType.getEndPointType());
			reportDeliveryDefinition.setEntityDescription(xmlReportDeliveryDefinitionType.getEntityDescription());
			reportDeliveryDefinition.setEntityName(xmlReportDeliveryDefinitionType.getEntityName());
			reportDeliveryDefinition.setEntityType(xmlReportDeliveryDefinitionType.getEntityType());
			reportDeliveryDefinition.setPassword(xmlReportDeliveryDefinitionType.getPassword());
			reportDeliveryDefinition.setUserName(xmlReportDeliveryDefinitionType.getUserName());
			reportDeliveryDefinition.setFormat(ReportFormat.valueOf(xmlReportDeliveryDefinitionType.getFormat().name()));
			
			reportDefinition.addReportDeliveryDefinition(reportDeliveryDefinition);
			
		}

		return report;
	}
	
	protected AdditionalInformation convertAdditionalInformation(AdditionalInformationType additionalInfoType){
		AdditionalInformation additionalInfo = new AdditionalInformation();
		additionalInfo.setAutopsyReport(additionalInfoType.isAutopsyReport());
		additionalInfo.setConsults(additionalInfoType.isConsults());
		additionalInfo.setDischargeSummary(additionalInfoType.isDischargeSummary());
		additionalInfo.setFlowCharts(additionalInfoType.isFlowCharts());
		additionalInfo.setIrbReport(additionalInfoType.isIrbReport());
		additionalInfo.setLabReports(additionalInfoType.isLabReports());
		additionalInfo.setObaForm(additionalInfoType.isObaForm());
		additionalInfo.setOther(additionalInfoType.isOther());
		additionalInfo.setPathologyReport(additionalInfoType.isPathologyReport());
		additionalInfo.setProgressNotes(additionalInfoType.isProgressNotes());
		additionalInfo.setRadiologyReports(additionalInfoType.isRadiologyReports());
		additionalInfo.setReferralLetters(additionalInfoType.isReferralLetters());
		additionalInfo.setOtherInformation(additionalInfoType.getOtherInformation());
		
		for(AdditionalInformationDocumentType xmlAdditionalInformationDocumentType: additionalInfoType.getAdditionalInformationDocuments()){
			AdditionalInformationDocument document = new AdditionalInformationDocument();
			document.setFileId(xmlAdditionalInformationDocumentType.getFileId());
			document.setFileName(xmlAdditionalInformationDocumentType.getFileName());
			document.setFilePath(xmlAdditionalInformationDocumentType.getFilePath());
			document.setFileSize(xmlAdditionalInformationDocumentType.getFileSize());
			document.setOriginalFileName(xmlAdditionalInformationDocumentType.getOriginalFileName());
			document.setRelativePath(xmlAdditionalInformationDocumentType.getRelativePath());
			if(xmlAdditionalInformationDocumentType.getAdditionalInformationDocumentType() != null){
				document.setAdditionalInformationDocumentType(gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType.valueOf
						(xmlAdditionalInformationDocumentType.getAdditionalInformationDocumentType().name()));
			}
		}
		
		return additionalInfo;
	}
	
	protected OtherCause convertOtherCause(OtherCauseType xmlOtherCauseType){
		OtherCause otherCause = new OtherCause();
		otherCause.setText(xmlOtherCauseType.getText());
		
		return otherCause;
	}
	
	protected TreatmentInformation convertTreatmentInformation(TreatmentInformationType treatmentInfoType){
		TreatmentInformation treatmentInformation = new TreatmentInformation();
		treatmentInformation.setInvestigationalAgentAdministered(treatmentInfoType.isInvestigationalAgentAdministered());
        treatmentInformation.setFirstCourseDate(XMLUtil.toDate(treatmentInfoType.getFirstCourseDate()));
		treatmentInformation.setTotalCourses(treatmentInfoType.getTotalCourses());
        treatmentInformation.setTreatmentAssignment(convertTreatmentAssignment(treatmentInfoType.getTreatmentAssignment()));

        //Intervention - Agents
		for (CourseAgentType courseAgentType : treatmentInfoType.getCourseAgent()){
			treatmentInformation.addCourseAgent(convertCourseAgent(courseAgentType));
		}
		return treatmentInformation;
	}

    protected TreatmentAssignment convertTreatmentAssignment(TreatmentAssignmentType tacType){
        TreatmentAssignment tac = new TreatmentAssignment();
        if(tacType != null){
            tac.setCode(tacType.getCode());
            tac.setDescription(tacType.getDescription());
            tac.setDoseLevelOrder(tacType.getDoseLevelOrder());
            tac.setComments(tacType.getComments());
        }
        return tac;
    }
    protected CourseAgent convertCourseAgentRef(CourseAgentRefType courseAgentRefType){
		CourseAgent courseAgent = new CourseAgent();
		StudyAgent studyAgent = new StudyAgent();
		Agent agent = new Agent();
		agent.setNscNumber(courseAgentRefType.getStudyAgentRef().getAgent().getNscNumber());
		studyAgent.setAgent(agent);
		courseAgent.setStudyAgent(studyAgent);
		
		
		return courseAgent;
	}
	protected CourseAgent convertCourseAgent(CourseAgentType courseAgentType){
		CourseAgent courseAgent = new CourseAgent();
		courseAgent.setAdministrationDelay(courseAgentType.getAdministrationDelayAmount());
		if(courseAgent.getAdministrationDelayUnits() != null){
				courseAgent.setAdministrationDelayUnits(DelayUnits.valueOf(courseAgentType.getAdministrationDelayUnits().name()));
		}
		if(courseAgentType.getDose() != null){
			courseAgent.setDose(convertDose(courseAgentType.getDose()));
		}
		
		if(courseAgentType.getModifiedDose() != null){
			courseAgent.setModifiedDose(convertDose(courseAgentType.getModifiedDose()));
		}
		
		if(courseAgentType.getAgentAdjustment() != null){
			courseAgent.setAgentAdjustment(AgentAdjustment.valueOf(courseAgentType.getAgentAdjustment().value()));
		}
		
		courseAgent.setDurationAndSchedule(courseAgentType.getDurationAndSchedule());
		courseAgent.setTotalDoseAdministeredThisCourse(courseAgentType.getTotalDoseAdministeredThisCourse());
		if(courseAgentType.getLastAdministeredDate() != null){
			courseAgent.setLastAdministeredDate(courseAgentType.getLastAdministeredDate().toGregorianCalendar().getTime());
		}
		
		if(courseAgentType.getStudyAgent() != null){
			StudyAgent studyAgent = new StudyAgent();
			if(courseAgentType.getStudyAgent().getAgent() != null){
				Agent agent = new Agent();
				agent.setName(courseAgentType.getStudyAgent().getAgent().getName());
				agent.setDescription(courseAgentType.getStudyAgent().getAgent().getDescription());
				agent.setNscNumber(courseAgentType.getStudyAgent().getAgent().getNscNumber());
				studyAgent.setAgent(agent);
			} else {
				studyAgent.setOtherAgent(courseAgentType.getStudyAgent().getOtherAgent());
			}
			courseAgent.setStudyAgent(studyAgent);
		}
		
		if(courseAgentType.getLotNumber() != null){
			courseAgent.setLotNumber(courseAgentType.getLotNumber());
		}
		
		return courseAgent;
	}
	
	protected Dose convertDose(DoseType xmlDoseType){
		Dose dose = new Dose();
		dose.setAmount(xmlDoseType.getAmount());
		dose.setUnits(xmlDoseType.getUnits());
		dose.setRoute(xmlDoseType.getRoute());
		
		return dose;
	}
	
	protected SAEReportPriorTherapy convertPriorTherapy(SAEReportPriorTherapyType priorTherapyType){
		SAEReportPriorTherapy saeReportPriorTherapy = new SAEReportPriorTherapy();
		if(priorTherapyType.getStartDate() != null){
			saeReportPriorTherapy.setStartDate((convertDateValue(priorTherapyType.getStartDate())));
		}
		
		if(priorTherapyType.getEndDate() != null) {
			saeReportPriorTherapy.setEndDate((convertDateValue(priorTherapyType.getEndDate())));
		}
		saeReportPriorTherapy.setOther(priorTherapyType.getOther());
		if(priorTherapyType.getPriorTherapy() != null){
			PriorTherapy priorTherapy = new PriorTherapy();
			priorTherapy.setText(priorTherapyType.getPriorTherapy().getText());
			priorTherapy.setMeddraCode((priorTherapyType.getPriorTherapy().getMeddraCode()));
			saeReportPriorTherapy.setPriorTherapy(priorTherapy);
		}
		
		if(priorTherapyType.getPriorTherapyAgent() != null &&  ! priorTherapyType.getPriorTherapyAgent().isEmpty()){
			for(PriorTherapyAgentType xmlPriorTherapyAgent : priorTherapyType.getPriorTherapyAgent()){
				PriorTherapyAgent priorTherapyAgent = new PriorTherapyAgent();
				Agent agent = new Agent();
				agent.setNscNumber(xmlPriorTherapyAgent.getAgent().getNscNumber());
				priorTherapyAgent.setAgent(agent);
				saeReportPriorTherapy.getPriorTherapyAgents().add(priorTherapyAgent);
			}
		}
		
		return saeReportPriorTherapy;
	}

	protected SAEReportPreExistingCondition convertPreExistingCondition(SAEReportPreExistingConditionType preCondType){
		SAEReportPreExistingCondition saeReportPreExistingCondition = new SAEReportPreExistingCondition();
		saeReportPreExistingCondition.setOther(preCondType.getOther());
		saeReportPreExistingCondition.setLinkedToOtherCause(preCondType.isLinkedToOtherCause() == null ? Boolean.FALSE : preCondType.isLinkedToOtherCause());
		if(preCondType.getPreExistingCondition() != null){
			PreExistingCondition preExistingCondition = new PreExistingCondition();
			preExistingCondition.setText(preCondType.getPreExistingCondition().getText());
			preExistingCondition.setMeddraHlgt(preCondType.getPreExistingCondition().getMeddraHlgt());
			preExistingCondition.setMeddraLlt(preCondType.getPreExistingCondition().getMeddraLlt());
			preExistingCondition.setMeddraLltCode(preCondType.getPreExistingCondition().getMeddraLltCode());
            saeReportPreExistingCondition.setPreExistingCondition(preExistingCondition);
		}
		
		return saeReportPreExistingCondition;
	}

	protected Lab convertLab(LabType labType){
		Lab lab = new Lab();
		lab.setUnits(labType.getUnits());
		lab.setNormalRange(labType.getNormalRange());
		
		if(labType.getBaseline() != null){
			LabValue baseline = new LabValue();
			baseline.setValue(labType.getBaseline().getValue());
			if(labType.getBaseline().getDate() != null){
				baseline.setDate(labType.getBaseline().getDate().toGregorianCalendar().getTime());
			}
			lab.setBaseline(baseline);
		}
		if(labType.getNadir() != null){
			LabValue nadir = new LabValue();
			nadir.setValue(labType.getNadir().getValue());
			if(labType.getNadir().getDate() != null){
				nadir.setDate(labType.getNadir().getDate().toGregorianCalendar().getTime());
			}
			lab.setNadir(nadir);
		}
		if(labType.getRecovery() != null){
			LabValue recovery = new LabValue();
			recovery.setValue(labType.getRecovery().getValue());
			if(labType.getRecovery().getDate() != null){
				recovery.setDate(labType.getRecovery().getDate().toGregorianCalendar().getTime());
			}
			lab.setRecovery(recovery);
		}
		
		if(labType.getLabTerm() != null){
			
			LabTerm labTerm = new LabTerm();
			labTerm.setTerm(labType.getLabTerm().getTerm());
			if(labType.getLabTerm().getCategory() != null){
				LabCategory labCategory = new LabCategory();
				labCategory.setName(labType.getLabTerm().getCategory().getName());
				labTerm.setCategory(labCategory);
			}
			lab.setLabTerm(labTerm);
		}
		
		
		return lab;
	}

	
	protected ConcomitantMedication convertConcomitantMedication(ConcomitantMedicationType xmlConcomitantMedicationType){
		ConcomitantMedication concomitantMedication = new ConcomitantMedication();
		concomitantMedication.setAgentName(xmlConcomitantMedicationType.getName().toString());
		if(xmlConcomitantMedicationType.getStartDate() != null){
			concomitantMedication.setStartDate(convertDateValue(xmlConcomitantMedicationType.getStartDate()));
		}
		
		if(xmlConcomitantMedicationType.getEndDate()!= null){
			concomitantMedication.setEndDate(convertDateValue(xmlConcomitantMedicationType.getEndDate()));
		}
		concomitantMedication.setStillTakingMedications(xmlConcomitantMedicationType.isStillTakingMedications() != null ? xmlConcomitantMedicationType.isStillTakingMedications() : Boolean.FALSE );
		
		return concomitantMedication;		
	}
	
	protected ConcomitantMedication convertConcomitantMedicationRef(ConcomitantMedicationRefType xmlConcomitantMedicationRefType){
		ConcomitantMedication concomitantMedication = new ConcomitantMedication();
		concomitantMedication.setAgentName(xmlConcomitantMedicationRefType.getName().toString());
		if(xmlConcomitantMedicationRefType.getStartDate() != null){
			concomitantMedication.setStartDate(convertDateValue(xmlConcomitantMedicationRefType.getStartDate()));
		}
		
		if(xmlConcomitantMedicationRefType.getEndDate()!= null){
			concomitantMedication.setEndDate(convertDateValue(xmlConcomitantMedicationRefType.getEndDate()));
		}
		
		return concomitantMedication;		
	}
	
	protected MedicalDevice convertMedicalDevice(MedicalDeviceType xmlMedicalDeviceType){
		MedicalDevice medicalDevice = new MedicalDevice();
		medicalDevice.setBrandName(xmlMedicalDeviceType.getBrandName());
		medicalDevice.setCatalogNumber(xmlMedicalDeviceType.getCatalogNumber());
		medicalDevice.setCommonName(xmlMedicalDeviceType.getCommonName());
		if(xmlMedicalDeviceType.getDeviceOperator() != null){
			medicalDevice.setDeviceOperator(DeviceOperator.valueOf(xmlMedicalDeviceType.getDeviceOperator().name()));
		}
		
		medicalDevice.setDeviceType(xmlMedicalDeviceType.getDeviceType());
		medicalDevice.setManufacturerName(xmlMedicalDeviceType.getManufacturerName());
		medicalDevice.setManufacturerCity(xmlMedicalDeviceType.getManufacturerCity());
		medicalDevice.setManufacturerState(xmlMedicalDeviceType.getManufacturerState());
		medicalDevice.setModelNumber(xmlMedicalDeviceType.getModelNumber());
		medicalDevice.setSerialNumber(xmlMedicalDeviceType.getSerialNumber());
		medicalDevice.setOtherNumber(xmlMedicalDeviceType.getOtherNumber());
		if(xmlMedicalDeviceType.getExplantedDate() != null){
			medicalDevice.setExplantedDate(xmlMedicalDeviceType.getExplantedDate().toGregorianCalendar().getTime());
		}
		if(xmlMedicalDeviceType.getImplantedDate() != null){
			medicalDevice.setImplantedDate(xmlMedicalDeviceType.getImplantedDate().toGregorianCalendar().getTime());
		}
		
		if(xmlMedicalDeviceType.getDeviceReprocessed() != null){
			medicalDevice.setDeviceReprocessed(ReprocessedDevice.valueOf(xmlMedicalDeviceType.getDeviceReprocessed().name()));
			if(xmlMedicalDeviceType.getDeviceReprocessed().name().equals("YES")){	
				medicalDevice.setReprocessorName(xmlMedicalDeviceType.getReprocessedName());
				medicalDevice.setReprocessorAddress(xmlMedicalDeviceType.getReprocessedAddress());
			}
		}
		if(xmlMedicalDeviceType.getEvaluationAvailability() != null){
			medicalDevice.setEvaluationAvailability(Availability.valueOf(xmlMedicalDeviceType.getEvaluationAvailability().name()));
		}
		
		if(xmlMedicalDeviceType.getStudyDevice() != null){
			StudyDevice studyDevice = new StudyDevice();
			if(xmlMedicalDeviceType.getStudyDevice().getDevice() != null){
				Device device = new Device();
				device.setType(xmlMedicalDeviceType.getStudyDevice().getDevice().getType());
				device.setBrandName(xmlMedicalDeviceType.getStudyDevice().getDevice().getBrandName());
				device.setCommonName(xmlMedicalDeviceType.getStudyDevice().getDevice().getCommonName());

                studyDevice.setDevice(device);

				studyDevice.setCatalogNumber(xmlMedicalDeviceType.getStudyDevice().getCatalogNumber());
				studyDevice.setModelNumber(xmlMedicalDeviceType.getStudyDevice().getModelNumber());
				studyDevice.setManufacturerName(xmlMedicalDeviceType.getStudyDevice().getManufacturerName());
				studyDevice.setManufacturerCity(xmlMedicalDeviceType.getStudyDevice().getManufacturerCity());
				studyDevice.setManufacturerState(xmlMedicalDeviceType.getStudyDevice().getManufacturerState());
			} else {
				studyDevice.setOtherBrandName(xmlMedicalDeviceType.getStudyDevice().getOtherBrandName());
				studyDevice.setOtherCommonName(xmlMedicalDeviceType.getStudyDevice().getOtherCommonName());
				studyDevice.setOtherDeviceType(xmlMedicalDeviceType.getStudyDevice().getOtherDeviceType());
			}

			medicalDevice.setStudyDevice(studyDevice);
		}
		
		if(xmlMedicalDeviceType.getLotNumber() != null){
			medicalDevice.setLotNumber(xmlMedicalDeviceType.getLotNumber());
		}
		
		if(xmlMedicalDeviceType.getReturnedDate() != null){
			medicalDevice.setReturnedDate(xmlMedicalDeviceType.getReturnedDate().toGregorianCalendar().getTime());
		}
		
		if(xmlMedicalDeviceType.getExpirationDate() != null){
			medicalDevice.setExpirationDate(xmlMedicalDeviceType.getExpirationDate().toGregorianCalendar().getTime());
		}
		
		return medicalDevice;
	}
	
	protected MedicalDevice convertMedicalDeviceRef(MedicalDeviceRefType xmlMedicalDeviceRefType){
		MedicalDevice medicalDevice = new MedicalDevice();
		
		StudyDevice studyDevice = new StudyDevice();
		Device device = new Device();
		if(xmlMedicalDeviceRefType.getStudyDeviceRef().getDevice().getType() != null){
			device.setType(xmlMedicalDeviceRefType.getStudyDeviceRef().getDevice().getType());
		}
		device.setBrandName(xmlMedicalDeviceRefType.getStudyDeviceRef().getDevice().getBrandName());
		device.setCommonName(xmlMedicalDeviceRefType.getStudyDeviceRef().getDevice().getCommonName());

        studyDevice.setDevice(device);

		medicalDevice.setStudyDevice(studyDevice);
	
		return medicalDevice;
	}
	
	protected ParticipantHistory convertParticipantHistory(ParticipantHistoryType xmlParticipantHistoryType){
		ParticipantHistory history = new ParticipantHistory();
		history.setBaselinePerformanceStatus(xmlParticipantHistoryType.getBaselinePerformanceStatus());
		if(xmlParticipantHistoryType.getHeight() != null){
			Measure height = new Measure();
			if(xmlParticipantHistoryType.getHeight().getCode() != null){
				height.setCode(xmlParticipantHistoryType.getHeight().getCode());
			}
			height.setQuantity(xmlParticipantHistoryType.getHeight().getQuantity());
			height.setUnit(xmlParticipantHistoryType.getHeight().getUnit());
			history.setHeight(height);
		}
		
		if(xmlParticipantHistoryType.getWeight() != null){
			Measure weight = new Measure();
			if(xmlParticipantHistoryType.getWeight().getCode() != null){
				weight.setCode(xmlParticipantHistoryType.getWeight().getCode());
			}			
			weight.setQuantity(xmlParticipantHistoryType.getWeight().getQuantity());
			weight.setUnit(xmlParticipantHistoryType.getWeight().getUnit());
			history.setWeight(weight);
		}
		
		return history;
	}
	
	// convert interventions
	
	protected RadiationIntervention convertRadiationIntervention(RadiationInterventionType xmlRadiationInterventionType){
		RadiationIntervention intervention = new RadiationIntervention();
		intervention.setAdjustment(xmlRadiationInterventionType.getAdjustment());
		intervention.setDaysElapsed(String.valueOf(xmlRadiationInterventionType.getDaysElapsed()));
		intervention.setDosage(String.valueOf(xmlRadiationInterventionType.getDosage()));
		intervention.setDosageUnit(String.valueOf(xmlRadiationInterventionType.getDosageUnit()));
		if(xmlRadiationInterventionType.getLastTreatmentDate() != null){
			intervention.setLastTreatmentDate(xmlRadiationInterventionType.getLastTreatmentDate().toGregorianCalendar().getTime());
		}
		intervention.setFractionNumber(String.valueOf(xmlRadiationInterventionType.getFractionNumber()));
		intervention.setAdjustment(xmlRadiationInterventionType.getAdjustment());
		if(xmlRadiationInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention =convertOtherIntervention(xmlRadiationInterventionType.getOtherIntervention());
			intervention.setStudyRadiation(otherIntervention);
		}
		if(xmlRadiationInterventionType.getAdministration() != null) {
			intervention.setAdministration(RadiationAdministration.findByDisplayName(xmlRadiationInterventionType.getAdministration().value()));
		}
		
		return intervention;
	}
	
	protected RadiationIntervention convertRadiationInterventionRef(RadiationInterventionRefType xmlRadiationInterventionRefType){
		RadiationIntervention intervention = new RadiationIntervention();
		intervention.setLastTreatmentDate(xmlRadiationInterventionRefType.getLastTreatmentDate().toGregorianCalendar().getTime());
		intervention.setAdministration(RadiationAdministration.findByDisplayName(xmlRadiationInterventionRefType.getAdministration().value()));
		
		return intervention;
	}
	
	protected OtherIntervention convertOtherIntervention(OtherInterventionType xmlOtherInterventionType){
		OtherIntervention otherIntervention = new OtherIntervention();
		otherIntervention.setName(xmlOtherInterventionType.getName());
		otherIntervention.setDescription(xmlOtherInterventionType.getDescription());
		
		return otherIntervention;
	}
	
	protected SurgeryIntervention convertSurgeryIntervention(SurgeryInterventionType xmlSurgeryInterventionType){
		SurgeryIntervention intervention = new SurgeryIntervention();
		if(xmlSurgeryInterventionType.getInterventionDate() != null){
			intervention.setInterventionDate(xmlSurgeryInterventionType.getInterventionDate().toGregorianCalendar().getTime());
		}
		
		if(xmlSurgeryInterventionType.getInterventionSite() != null){
			InterventionSite interventionSite = new InterventionSite();
			interventionSite.setName(xmlSurgeryInterventionType.getInterventionSite().getName());
			intervention.setInterventionSite(interventionSite);
		}
		
		return intervention;
	}
	
	protected BiologicalIntervention convertBiologicalIntervention(BiologicalInterventionType xmlBiologicalInterventionType){
		BiologicalIntervention intervention = new BiologicalIntervention();
		intervention.setDescription(xmlBiologicalInterventionType.getDescription());
		
		if(xmlBiologicalInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention = convertOtherIntervention(xmlBiologicalInterventionType.getOtherIntervention());
			intervention.setStudyIntervention(otherIntervention);
		}
		
		return intervention;
	}
	
	protected DietarySupplementIntervention convertDietarySupplementIntervention(DietarySupplementInterventionType xmlDietarySupplementInterventionType){
		DietarySupplementIntervention intervention = new DietarySupplementIntervention();
		intervention.setDescription(xmlDietarySupplementInterventionType.getDescription());
		
		if(xmlDietarySupplementInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention = convertOtherIntervention(xmlDietarySupplementInterventionType.getOtherIntervention());
			intervention.setStudyIntervention(otherIntervention);
		}
		
		return intervention;
	}

    protected DiseaseHistory convertDiseaseHistory(DiseaseHistoryType xmlDiseaseHistoryType){
        DiseaseHistory diseaseHistory = new DiseaseHistory();

        DiseaseTerm dt = new DiseaseTerm();
        dt.setTerm(xmlDiseaseHistoryType.getPrimaryDisease());

        // Setting the Abstract study Disease as Ctep Study as the Study details are not provided in the input.
        diseaseHistory.setAbstractStudyDisease(new CtepStudyDisease());

        diseaseHistory.getAbstractStudyDisease().setTerm(dt) ;
        diseaseHistory.setOtherPrimaryDisease(xmlDiseaseHistoryType.getOtherPrimaryDisease());

        if (xmlDiseaseHistoryType.getMetastaticDiseaseSite() != null){
            for(MetastaticDiseaseSiteType metastaticDiseaseSite : xmlDiseaseHistoryType.getMetastaticDiseaseSite()){
                diseaseHistory.addMetastaticDiseaseSite(convertMestastaticDiseaseSite(metastaticDiseaseSite));
            }
        }

        AnatomicSite anatomicSite = new AnatomicSite();
        anatomicSite.setName(xmlDiseaseHistoryType.getCodedPrimaryDiseaseSite());
        diseaseHistory.setCodedPrimaryDiseaseSite(anatomicSite);
        diseaseHistory.setOtherPrimaryDiseaseSite(xmlDiseaseHistoryType.getOtherPrimaryDiseaseSite());

        if(xmlDiseaseHistoryType.getDiagnosisDate() != null){
            diseaseHistory.setDiagnosisDate(convertDateValue(xmlDiseaseHistoryType.getDiagnosisDate()));
        }

        return diseaseHistory;
    }
    
    protected DiseaseHistory convertDiseaseHistoryRef(DiseaseHistoryRefType xmlDiseaseHistoryRefType){
        DiseaseHistory diseaseHistory = new DiseaseHistory();
       
        if(xmlDiseaseHistoryRefType.getPrimaryDisease() != null){
        	 DiseaseTerm dt = new DiseaseTerm();
        	 dt.setTerm(xmlDiseaseHistoryRefType.getPrimaryDisease());
        	 diseaseHistory.setAbstractStudyDisease(new CtepStudyDisease());

             diseaseHistory.getAbstractStudyDisease().setTerm(dt) ;
        } else if (xmlDiseaseHistoryRefType.getOtherPrimaryDisease() != null){
        	 diseaseHistory.setOtherPrimaryDisease(xmlDiseaseHistoryRefType.getOtherPrimaryDisease());
        	
        }
       
        return diseaseHistory;
    }


	protected DateValue convertDateValue(DateValueType xmlDateValueType){
		DateValue dateValue = new DateValue();
		dateValue.setDay(xmlDateValueType.getDay());
		dateValue.setMonth(xmlDateValueType.getMonth());
		dateValue.setYear(xmlDateValueType.getYear());
		if(xmlDateValueType.getZone() != null){
			dateValue.setZone(xmlDateValueType.getZone());
		}
		
		return dateValue;
	}
	
	protected MetastaticDiseaseSite convertMestastaticDiseaseSite(MetastaticDiseaseSiteType xmlMetastaticDiseaseSiteType){
		MetastaticDiseaseSite site = new MetastaticDiseaseSite();
		if (xmlMetastaticDiseaseSiteType.getCodedSite() != null){
			AnatomicSite anatomicSite = new AnatomicSite();
			anatomicSite.setName(xmlMetastaticDiseaseSiteType.getCodedSite().getName());
			if(xmlMetastaticDiseaseSiteType.getCodedSite().getCategory() != null){
				anatomicSite.setCategory(xmlMetastaticDiseaseSiteType.getCodedSite().getCategory());
			}
			site.setCodedSite(anatomicSite);
		}
		
		if(xmlMetastaticDiseaseSiteType.getOtherSite() != null){
			site.setOtherSite(xmlMetastaticDiseaseSiteType.getOtherSite());
		}
		
		return site;
	}
	
	protected AdverseEventResponseDescription convertAdverseEventResponseDescription(AdverseEventResponseDescriptionType xmlAdverseEventResponseDescriptionType){
		AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
		
		if(xmlAdverseEventResponseDescriptionType.isRetreated() != null){
			adverseEventResponseDescription.setRetreated(xmlAdverseEventResponseDescriptionType.isRetreated());
		}
		
		if(xmlAdverseEventResponseDescriptionType.isAutopsyPerformed() != null){
			adverseEventResponseDescription.setAutopsyPerformed(xmlAdverseEventResponseDescriptionType.isAutopsyPerformed());
		}
		adverseEventResponseDescription.setEventDescription(xmlAdverseEventResponseDescriptionType.getEventDescription());
		if(xmlAdverseEventResponseDescriptionType.getRecoveryDate() != null){
			adverseEventResponseDescription.setRecoveryDate(xmlAdverseEventResponseDescriptionType.getRecoveryDate().toGregorianCalendar().getTime());
		}
		
		if(xmlAdverseEventResponseDescriptionType.getDateRemovedFromProtocol() != null){
			adverseEventResponseDescription.setDateRemovedFromProtocol(xmlAdverseEventResponseDescriptionType.getDateRemovedFromProtocol().toGregorianCalendar().getTime());
		}
		
		adverseEventResponseDescription.setRetreated(xmlAdverseEventResponseDescriptionType.isRetreated());
		
		if(xmlAdverseEventResponseDescriptionType.getPresentStatus() != null){
			adverseEventResponseDescription.setPresentStatus(PostAdverseEventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getPresentStatus().name()));
		}
		if(xmlAdverseEventResponseDescriptionType.getEventAbate() != null){
			adverseEventResponseDescription.setEventAbate(EventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getEventAbate()));
		}
		
		if(xmlAdverseEventResponseDescriptionType.getEventReappear() != null){
			adverseEventResponseDescription.setEventReappear(EventStatus.valueOf(xmlAdverseEventResponseDescriptionType.getEventReappear()));
		}
		
		if(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime() != null){
			TimeValue primaryTreatmentApproximateTime = new TimeValue();
			primaryTreatmentApproximateTime.setHour(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getHour());
			primaryTreatmentApproximateTime.setMinute(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getMinute());
			primaryTreatmentApproximateTime.setType(xmlAdverseEventResponseDescriptionType.getPrimaryTreatmentApproximateTime().getType());
		}
		
		return adverseEventResponseDescription;
	}
	
	protected Reporter convertReporter(ReporterType xmlReporterType){
		Reporter reporter = new Reporter();
		reporter.setFirstName(xmlReporterType.getFirstName());
		reporter.setLastName(xmlReporterType.getLastName());
		if(xmlReporterType.getNciIdentifier() != null){
			ResearchStaff staff = new LocalResearchStaff();
			staff.setNciIdentifier(xmlReporterType.getNciIdentifier());
		}
		
		Address address = new Address();
		address.setStreet(xmlReporterType.getStreet());
		address.setCity(xmlReporterType.getCity());
		address.setCountry(xmlReporterType.getCountry());
		address.setState(xmlReporterType.getState());
		address.setZip(xmlReporterType.getZip());
		
		reporter.setAddress(address);
		if(xmlReporterType.getContactMechanism() != null){
			reporter.setEmailAddress(getEmail(xmlReporterType.getContactMechanism()));
			reporter.setPhoneNumber(getPhone(xmlReporterType.getContactMechanism()));
			reporter.setFax(getFax(xmlReporterType.getContactMechanism()));
            reporter.setFaxNumber(getFax(xmlReporterType.getContactMechanism()));
		}
		
		return reporter;
		
	}
	
	protected Submitter convertSubmitter(SubmitterType xmlSubmitterType){
		Submitter submitter = new Submitter();
		submitter.setFirstName(xmlSubmitterType.getFirstName());
		submitter.setLastName(xmlSubmitterType.getLastName());
		submitter.setMiddleName(xmlSubmitterType.getMiddleName());
		if(xmlSubmitterType.getNciIdentifier() != null){
			ResearchStaff staff = new LocalResearchStaff();
			staff.setNciIdentifier(xmlSubmitterType.getNciIdentifier());
		}
		
		Address address = new Address();
		address.setStreet(xmlSubmitterType.getStreet());
		address.setCity(xmlSubmitterType.getCity());
		address.setCountry(xmlSubmitterType.getCountry());
		address.setState(xmlSubmitterType.getState());
		address.setZip(xmlSubmitterType.getZip());
		
		submitter.setAddress(address);
		if(xmlSubmitterType.getContactMechanism() != null){
			submitter.setEmailAddress(getEmail(xmlSubmitterType.getContactMechanism()));
			submitter.setPhoneNumber(getPhone(xmlSubmitterType.getContactMechanism()));
			submitter.setFax(getFax(xmlSubmitterType.getContactMechanism()));
			submitter.setFaxNumber(getFax(xmlSubmitterType.getContactMechanism()));
		}
		
		return submitter;
		
	}

	protected Physician convertPhysician(PhysicianType xmlPhysicianType){
		Physician physician = new Physician();
		physician.setFirstName(xmlPhysicianType.getFirstName());
		physician.setLastName(xmlPhysicianType.getLastName());
		if(xmlPhysicianType.getNciIdentifier() != null){
			Investigator investigator = new LocalInvestigator();
			investigator.setNciIdentifier(xmlPhysicianType.getNciIdentifier());
		}
		
		Address address = new Address();
		address.setStreet(xmlPhysicianType.getStreet());
		address.setCity(xmlPhysicianType.getCity());
		address.setCountry(xmlPhysicianType.getCountry());
		address.setState(xmlPhysicianType.getState());
		address.setZip(xmlPhysicianType.getZip());
		
		physician.setAddress(address);
		if(xmlPhysicianType.getContactMechanism() != null){
			physician.setEmailAddress(getEmail(xmlPhysicianType.getContactMechanism()));
			physician.setPhoneNumber(getPhone(xmlPhysicianType.getContactMechanism()));
			physician.setFax(getFax(xmlPhysicianType.getContactMechanism()));
		}
		
		
		return physician;
		
	}
	
	protected AdverseEventReportingPeriod convertAdverseEventReportingPeriod(AdverseEventReportingPeriodType reportingPeriodType){
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		
		if(reportingPeriodType.getStudyParticipantAssignmentRef() != null){
			reportingPeriod.setAssignment(convertStudyParticipantAssignmentRef(reportingPeriodType.getStudyParticipantAssignmentRef()));
		}
		
		if(reportingPeriodType.getTreatmentAssignment() != null){
			TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
			treatmentAssignment.setCode(reportingPeriodType.getTreatmentAssignment().getCode());
			if(reportingPeriodType.getTreatmentAssignment().getComments() != null){
				treatmentAssignment.setComments(reportingPeriodType.getTreatmentAssignment().getComments());
			}
			treatmentAssignment.setDescription(reportingPeriodType.getTreatmentAssignment().getDescription());
			treatmentAssignment.setDoseLevelOrder(reportingPeriodType.getTreatmentAssignment().getDoseLevelOrder());
			
			reportingPeriod.setTreatmentAssignment(treatmentAssignment);
		}
		
		if(reportingPeriodType.getTreatmentAssignmentDescription() != null){
			reportingPeriod.setTreatmentAssignmentDescription(reportingPeriodType.getTreatmentAssignmentDescription());
		}
		
		if(reportingPeriodType.getStartDate() != null){
			reportingPeriod.setStartDate(reportingPeriodType.getStartDate().toGregorianCalendar().getTime());
		}
		
		if(reportingPeriodType.getCycleNumber() != null){
			reportingPeriod.setCycleNumber(reportingPeriodType.getCycleNumber());
		}
		
		
		return reportingPeriod;
		
	}
	
	protected StudyParticipantAssignment convertStudyParticipantAssignment(StudyParticipantAssignmentType assignmentType) {
		StudyParticipantAssignment assignment=  new StudyParticipantAssignment();

        assignment.setStudySubjectIdentifier(assignmentType.getStudySubjectIdentifier());
        assignment.setDateOfEnrollment(XMLUtil.toDate(assignmentType.getDateOfEnrollment()));
        assignment.setStartDateOfFirstCourse(XMLUtil.toDate(assignmentType.getStartDateOfFirstCourse()));

        if(assignmentType.getParticipant() != null) {
		    assignment.setParticipant(convertParticipant(assignmentType.getParticipant()));
		    assignment.getParticipant().getAssignments().add(assignment);
        }
        	

        if(assignmentType.getStudySite() != null)
            assignment.setStudySite(convertStudySite(assignmentType.getStudySite()));

		return assignment;
	}
	
	protected StudyParticipantAssignment convertStudyParticipantAssignmentRef(StudyParticipantAssignmentRefType assignmentType) {
		StudyParticipantAssignment assignment=  new StudyParticipantAssignment();

        assignment.setStudySubjectIdentifier(assignmentType.getStudySubjectIdentifier());

        if(assignmentType.getStudySiteRef() != null)
            assignment.setStudySite(convertStudySiteRef(assignmentType.getStudySiteRef()));

		return assignment;
	}
	
	protected Participant convertParticipant(ParticipantType xmlParticipantType){
		Participant participant = new Participant();
		try{
			participant.setFirstName(xmlParticipantType.getFirstName());
			participant.setLastName(xmlParticipantType.getLastName());
			participant.setMiddleName(xmlParticipantType.getMiddleName());
			participant.setMaidenName(xmlParticipantType.getMaidenName());
			if(xmlParticipantType.getBirthDate() != null){
				DateValue dateOfBirth = new DateValue(xmlParticipantType.getBirthDate().getDay(),xmlParticipantType.getBirthDate().getMonth(),xmlParticipantType.getBirthDate().getYear());
				participant.setDateOfBirth(dateOfBirth);
			}else{
				if(xmlParticipantType.getBirthYear() != null){
					DateValue dateOfBirth = new DateValue(null,xmlParticipantType.getBirthMonth().intValue(),xmlParticipantType.getBirthYear().intValue());
					participant.setDateOfBirth(dateOfBirth);
				}
			}
			if(xmlParticipantType.getGender() != null){
				participant.setGender(xmlParticipantType.getGender().value());
			}
			if(xmlParticipantType.getRace() != null){
				participant.setRace(xmlParticipantType.getRace().value());
			}
			if(xmlParticipantType.getEthnicity() != null){
				participant.setEthnicity(xmlParticipantType.getEthnicity().value());
			}
			
			//Populate Identifiers
			convertIdentifierTypesToDomainIdentifiers(xmlParticipantType.getIdentifiers(),participant.getIdentifiers());
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while converting XML participant to domain participant",e);
		}
		
		return participant;
	}
	
	protected void convertIdentifierTypesToDomainIdentifiers(Identifiers identifierTypes, List<Identifier> identifiers) throws Exception{
		
		if(identifierTypes != null){
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifierTypes.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					identifiers.add(convertOrganizationIdentifierTypeToDomainIdentifier(organizationAssignedIdentifierType));
				}
			}
		}
	}
	
	protected OrganizationAssignedIdentifier convertOrganizationIdentifierTypeToDomainIdentifier(OrganizationAssignedIdentifierType identifierType) throws Exception{
		Organization organization = new LocalOrganization();
		OrganizationAssignedIdentifier orgIdentifier = new OrganizationAssignedIdentifier();
		orgIdentifier.setType(identifierType.getType().value());
		orgIdentifier.setValue(identifierType.getValue());
		orgIdentifier.setPrimaryIndicator(identifierType.isPrimaryIndicator());
		organization.setName(identifierType.getOrganizationRef().getName());
		organization.setNciInstituteCode(identifierType.getOrganizationRef().getNciInstituteCode());
		orgIdentifier.setOrganization(organization);
		return orgIdentifier;
		}
	
	protected StudySite convertStudySite(StudySiteType studySiteType) {
		StudySite studySite =  new StudySite();
		
		studySite.setOrganization(convertOrganization(studySiteType.getOrganization()));
		studySite.setStudy(convertStudy(studySiteType.getStudyRef()));

		return studySite;
	}
	
	protected StudySite convertStudySiteRef(StudySiteRefType studySiteType) {
		StudySite studySite =  new StudySite();
		
		studySite.setStudy(convertStudy(studySiteType.getStudyRef()));
		Organization organization = new LocalOrganization();
		organization.setNciInstituteCode(studySiteType.getNciInstituteCode());
		
		studySite.setOrganization(organization);

		return studySite;
	}
	
	protected Organization convertOrganization(OrganizationType xmlOrganizationType){
		
		LocalOrganization organization = new LocalOrganization();
		organization.setName(xmlOrganizationType.getName());
		organization.setNciInstituteCode(xmlOrganizationType.getNciInstituteCode());
		
		return organization;
	}
	
	protected Study convertStudy(StudyRefType xmlStudyType) {
		Identifier identifier = new Identifier();
		if ( xmlStudyType.getIdentifiers().getIdentifier().getType() != null) {
			identifier.setType(xmlStudyType.getIdentifiers().getIdentifier().getType().value());
		}
		identifier.setValue(xmlStudyType.getIdentifiers().getIdentifier().getValue());
		
		Study study = fetchStudy(identifier);

		return study;
	}
	
	protected String getEmail(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(EMAIL)){
				return cm.getValue();
			}
		}
		
		return null;
	}
	
	protected String getPhone(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(PHONE)){
				return cm.getValue();
			}
		}
		
		return null;
	}
	
	
	protected String getFax(List<ContactMechanismType> contactMechanisms){
		for(ContactMechanismType cm : contactMechanisms){
			if(cm.getType().equals(FAX)){
				return cm.getValue();
			}
		}
		
		return null;
	}
	
	protected Study fetchStudy(Identifier identifier) {
		if (StringUtils.isEmpty(identifier.getValue())) {
			throw new CaaersSystemException("WS_SAE_004", messageSource.getMessage("WS_SAE_004", new String[] {}, "",
					Locale.getDefault()));
		}
		Study study = null;
		try {
			study = studyDao.getByIdentifier(identifier);
		} catch (Exception e) {
			throw new CaaersSystemException("WS_GEN_001", messageSource.getMessage("WS_GEN_001", new String[] {}, "",
					Locale.getDefault()));
		}
		if (study == null) {
			throw new CaaersSystemException("WS_SAE_005", messageSource.getMessage("WS_SAE_005",
					new String[] { identifier.getValue() }, "", Locale.getDefault()));
		}
		return study;
	}
}
