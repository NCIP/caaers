package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory.Measure;
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
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DateValueType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DoseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.LabType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MetastaticDiseaseSiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherAEInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportDeliveryDefinitionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportVersionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.TreatmentInformationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

/**
 * @author Ramakrishna Gundala
 */

public class ExpeditedAdverseEventReportConverter {
	AdverseEventConverter aeConverter = new AdverseEventConverter();
	
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

	public ExpeditedAdverseEventReport convert( AdverseEventReport aeReportDto) {
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();

        aeReport.setCreatedAt(XMLUtil.toTimestamp(aeReportDto.getCreatedAt()));

        //reporting period
        if(aeReportDto.getAdverseEventReportingPeriod() != null) {
            AdverseEventReportingPeriod reportingPeriod = convertAdverseEventReportingPeriod(aeReportDto.getAdverseEventReportingPeriod());
            reportingPeriod.addAeReport(aeReport);
        }

        //Study Participant Assignment
        if(aeReportDto.getStudyParticipantAssignment() != null && aeReport.getAssignment() == null){
            aeReport.getReportingPeriod().setAssignment(convertStudyParticipantAssignment(aeReportDto.getStudyParticipantAssignment()));
        }

        //Adverse Events
        for(AdverseEventType aeType : aeReportDto.getAdverseEvent()){
            if ( aeReport.getReportingPeriod() != null) {

                AdverseEvent ae = aeConverter.convert(aeType);
                aeReport.getReportingPeriod().addAdverseEvent(ae);

                for(CourseAgentAttributionType courseAgentAttributionType : aeType.getCourseAgentAttribution() ){
                    CourseAgentAttribution attribution = new CourseAgentAttribution();
                    attribution.setAttribution(Attribution.valueOf(courseAgentAttributionType.getAttribution().value() ));
                    attribution.setCause(convertCourseAgent(courseAgentAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getCourseAgentAttributions().add(attribution);
                }

                for(ConcomitantMedicationAttributionType conMedAttributionType : aeType.getConcomitantMedicationAttribution() ){
                    ConcomitantMedicationAttribution attribution = new ConcomitantMedicationAttribution();
                    attribution.setAttribution(Attribution.valueOf(conMedAttributionType.getAttribution().value() ));
                    attribution.setCause(convertConcomitantMedication(conMedAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getConcomitantMedicationAttributions().add(attribution);
                }

                for(OtherCauseAttributionType otherCauseAttributionType : aeType.getOtherCauseAttribution() ){
                    OtherCauseAttribution attribution = new OtherCauseAttribution();
                    attribution.setAttribution(Attribution.valueOf(otherCauseAttributionType.getAttribution().value() ));
                    attribution.setCause(convertOtherCause(otherCauseAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getOtherCauseAttributions().add(attribution);
                }

                if(aeType.getSurgeryAttribution() != null){
                    SurgeryAttribution attribution = new SurgeryAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getSurgeryAttribution().getAttribution().value()));
                    attribution.setCause(convertSurgeryIntervention(aeType.getSurgeryAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getSurgeryAttributions().add(attribution);
                }


                if(aeType.getRadiationAttribution() != null){
                    RadiationAttribution attribution = new RadiationAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getRadiationAttribution().getAttribution().value()));
                    attribution.setCause(convertRadiationIntervention(aeType.getRadiationAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getRadiationAttributions().add(attribution);
                }


                if(aeType.getDeviceAttribution() != null){
                    DeviceAttribution attribution = new DeviceAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getDeviceAttribution().getAttribution().value()));
                    attribution.setCause(convertMedicalDevice(aeType.getDeviceAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDeviceAttributions().add(attribution);
                }

                if(aeType.getBiologicalInterventionAttribution() != null){
                    BiologicalInterventionAttribution attribution = new BiologicalInterventionAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getBiologicalInterventionAttribution().getAttribution().value()));
                    attribution.setCause(convertBiologicalIntervention(aeType.getBiologicalInterventionAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getBiologicalInterventionAttributions().add(attribution);
                }

                if(aeType.getDietarySupplementInterventionAttribution() != null){
                    DietarySupplementInterventionAttribution attribution = new DietarySupplementInterventionAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getDietarySupplementInterventionAttribution().getAttribution().value()));
                    attribution.setCause(convertDietarySupplementIntervention(aeType.getDietarySupplementInterventionAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDietarySupplementInterventionAttributions().add(attribution);
                }
                
                if(aeType.getDiseaseAttribution() != null){
                    DiseaseAttribution attribution = new DiseaseAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getDiseaseAttribution().getAttribution().value()));
                	attribution.setCause(convertDiseaseHistory(aeType.getDiseaseAttribution().getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDiseaseAttributions().add(attribution);
                }
               
                if(aeType.getOtherInterventionAttribution() != null){
                    OtherInterventionAttribution attribution = new OtherInterventionAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getOtherInterventionAttribution().getAttribution().value()));
                    attribution.setCause( convertOtherAEIntervention(aeType.getOtherInterventionAttribution().getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getOtherInterventionAttributions().add(attribution);
                }
                
                if(aeType.getBehavioralInterventionAttribution() != null){
                    BehavioralInterventionAttribution attribution = new BehavioralInterventionAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getBehavioralInterventionAttribution().getAttribution().value()));
                    attribution.setCause(convertBehavioralIntervention(aeType.getBehavioralInterventionAttribution().getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getBehavioralInterventionAttributions().add(attribution);
                }
                
                if(aeType.getGeneticInterventionAttribution() != null){
                	GeneticInterventionAttribution attribution = new GeneticInterventionAttribution();
                    attribution.setAttribution(Attribution.valueOf(aeType.getGeneticInterventionAttribution().getAttribution().value()));
                    attribution.setCause(convertGeneticIntervention(aeType.getBehavioralInterventionAttribution().getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getGeneticInterventionAttributions().add(attribution);
                }
            }
        }
        

        //response description
        if(aeReportDto.getAdverseEventResponseDescription() != null){
			aeReport.setResponseDescription(convertAdverseEventResponseDescription(aeReportDto.getAdverseEventResponseDescription()));
		}


        //reporter
        if(aeReportDto.getReporter() != null){
            aeReport.setReporter(converterReporter(aeReportDto.getReporter()));
        }


		//physician
        if(aeReportDto.getPhysician() != null){
            aeReport.setPhysician(convertPhysician(aeReportDto.getPhysician()));
        }

        //participant Medical History
		if(aeReportDto.getParticipantHistory() != null){
			aeReport.setParticipantHistory(convertParticipantHistory(aeReportDto.getParticipantHistory()));
		}

        //Medical History - Disease History
        if(aeReportDto.getDiseaseHistory() != null){
            aeReport.setDiseaseHistory(convertDiseaseHistory(aeReportDto.getDiseaseHistory()));
        }

        //Medical History - Con Med
        for(ConcomitantMedicationType xmlConcomitantMedicationType : aeReportDto.getConcomitantMedication()){
            aeReport.addConcomitantMedication(convertConcomitantMedication(xmlConcomitantMedicationType));
        }


        //Medical History - Pre-cond
        for(SAEReportPreExistingConditionType saeReportPreExistingConditionType : aeReportDto.getSAEReportPreExistingCondition()){
            aeReport.addSaeReportPreExistingCondition(convertPreExistingCondition(saeReportPreExistingConditionType));
        }

        //Medical History - prior therapy
        for(SAEReportPriorTherapyType saeReportPriorTherapyType : aeReportDto.getSAEReportPriorTherapy()){
            aeReport.addSaeReportPriorTherapies(convertPriorTherapy(saeReportPriorTherapyType));
        }

        //Intervention - Course Agent - Treatment information
        if(aeReportDto.getTreatmentInformation() != null){
            aeReport.setTreatmentInformation(convertTreatmentInformation(aeReportDto.getTreatmentInformation()));
        }

        //Intervention - Device
        for(MedicalDeviceType xmlMedicalDeviceType : aeReportDto.getMedicalDevice()){
            aeReport.addMedicalDevice(convertMedicalDevice(xmlMedicalDeviceType));
        }


        //Intervention - Radiation
		for(RadiationInterventionType radiationType : aeReportDto.getRadiationIntervention()){
			aeReport.getRadiationInterventions().add(convertRadiationIntervention(radiationType));
		}

        //Intervention - Surgery
		for(SurgeryInterventionType surgeryType : aeReportDto.getSurgeryIntervention()){
			aeReport.getSurgeryInterventions().add(convertSurgeryIntervention(surgeryType));
		}

        //Intervention - Biological
		for(BiologicalInterventionType biologicalType : aeReportDto.getBiologicalIntervention()){
			aeReport.getBiologicalInterventions().add(convertBiologicalIntervention(biologicalType));
		}

        //Intervention - Dietary
		for(DietarySupplementInterventionType dietarySupplementType : aeReportDto.getDietarySupplementIntervention()){
			aeReport.getDietaryInterventions().add(convertDietarySupplementIntervention(dietarySupplementType));
		}

        //Lab
		for(LabType xmlLabType : aeReportDto.getLab()){
			aeReport.addLab(convertLab(xmlLabType));
		}



        //Other cause
		for(OtherCauseType xmlOtherCauseType : aeReportDto.getOtherCause()){
			aeReport.addOtherCause(convertOtherCause(xmlOtherCauseType));
		}

        //Additional Info
		if(aeReportDto.getAdditionalInformation() != null){
			aeReport.setAdditionalInformation(convertAdditionalInformation(aeReportDto.getAdditionalInformation()));
		}

        //Reports
		for(ReportType xmlReportType : aeReportDto.getReport()){
			aeReport.addReport(convertReport(xmlReportType));
		}
		
		// Set the study Information to the Source Report.
		return aeReport;
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
	
	protected GeneticIntervention convertGeneticIntervention(BehavioralInterventionType xmlGeneticInterventionType, ExpeditedAdverseEventReport report){
		GeneticIntervention geneticIntervention= new GeneticIntervention();
		geneticIntervention.setDescription(xmlGeneticInterventionType.getDescription());
		if(xmlGeneticInterventionType.getStudyIntervention() != null){
			geneticIntervention.setStudyIntervention(convertOtherIntervention(xmlGeneticInterventionType.getStudyIntervention()));
		}
		geneticIntervention.setReport(report);
		
		return geneticIntervention;
	}
	
	
	protected Report convertReport(ReportType xmlReportType){
		Report report = new Report();
		report.setRequired(xmlReportType.isRequired());
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
			reportVersion.setReportVersionId((xmlReportVersionType.getReportVersionId()));
			reportVersion.setSubmissionMessage(xmlReportVersionType.getSubmissionMessage());
			reportVersion.setSubmissionUrl(xmlReportVersionType.getSubmissionUrl());
			reportVersion.setCcEmails(xmlReportVersionType.getEmail());
			if(xmlReportVersionType.getReportStatus() != null){
				reportVersion.setReportStatus(ReportStatus.valueOf(xmlReportVersionType.getReportStatus().name()));
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
		
		
		reportDefinition.setTimeScaleUnitType(TimeScaleUnit.valueOf(xmlReportType.getAeReportDefinition().getTimeScaleUnitType().name()));
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
        treatmentInformation.setFirstCourseDate(XMLUtil.toDate(treatmentInfoType.getFirstCourseDate()));
		treatmentInformation.setTotalCourses(treatmentInfoType.getTotalCourses());

        //Intervention - Agents
		for (CourseAgentType courseAgentType : treatmentInfoType.getCourseAgent()){
			treatmentInformation.addCourseAgent(convertCourseAgent(courseAgentType));
		}
		return treatmentInformation;
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
		
		return saeReportPriorTherapy;
	}

	protected SAEReportPreExistingCondition convertPreExistingCondition(SAEReportPreExistingConditionType preCondType){
		SAEReportPreExistingCondition saeReportPreExistingCondition = new SAEReportPreExistingCondition();
		saeReportPreExistingCondition.setOther(preCondType.getOther());
		saeReportPreExistingCondition.setLinkedToOtherCause(preCondType.isLinkedToOtherCause());
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
		concomitantMedication.setStillTakingMedications(xmlConcomitantMedicationType.isStillTakingMedications());
		
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
		
		if(xmlMedicalDeviceType.getDeviceReprocessed() != null){
			medicalDevice.setDeviceReprocessed(ReprocessedDevice.valueOf(xmlMedicalDeviceType.getDeviceReprocessed().name()));
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
		
		return medicalDevice;
	}
	
	protected ParticipantHistory convertParticipantHistory(ParticipantHistoryType xmlParticipantHistoryType){
		ParticipantHistory history = new ParticipantHistory();
		history.setBaselinePerformanceStatus(xmlParticipantHistoryType.getBaselinePerformanceStatus());
		if(xmlParticipantHistoryType.getHeight() != null){
			Measure height = new Measure();
			height.setCode(xmlParticipantHistoryType.getHeight().getCode());
			height.setQuantity(xmlParticipantHistoryType.getHeight().getQuantity());
			height.setUnit(xmlParticipantHistoryType.getHeight().getUnit());
			history.setHeight(height);
		}
		
		if(xmlParticipantHistoryType.getWeight() != null){
			Measure weight = new Measure();
			weight.setCode(xmlParticipantHistoryType.getWeight().getCode());
			weight.setQuantity(xmlParticipantHistoryType.getWeight().getQuantity());
			weight.setUnit(xmlParticipantHistoryType.getWeight().getUnit());
			history.setWeight(weight);
		}
		
		return history;
	}
	
	// convert interventions
	
	protected RadiationIntervention
    convertRadiationIntervention(RadiationInterventionType xmlRadiationInterventionType){
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
		intervention.setAdministration(RadiationAdministration.findByDisplayName(xmlRadiationInterventionType.getAdministration().value()));
		
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
		
		if(xmlSurgeryInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention = convertOtherIntervention(xmlSurgeryInterventionType.getOtherIntervention());
			intervention.setStudySurgery(otherIntervention);
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
		if (xmlDiseaseHistoryType.getMetastaticDiseaseSite() != null){
			for(MetastaticDiseaseSiteType metastaticDiseaseSite : xmlDiseaseHistoryType.getMetastaticDiseaseSite()){
				diseaseHistory.addMetastaticDiseaseSite(convertMestastaticDiseaseSite(metastaticDiseaseSite));
			}
		}
		
		if(xmlDiseaseHistoryType.getDiagnosisDate() != null){
			diseaseHistory.setDiagnosisDate(convertDateValue(xmlDiseaseHistoryType.getDiagnosisDate()));
		}
		
		return diseaseHistory;
	}
	
	protected DateValue convertDateValue(DateValueType xmlDateValueType){
		DateValue dateValue = new DateValue();
		dateValue.setDay(xmlDateValueType.getDay());
		dateValue.setMonth(xmlDateValueType.getMonth());
		dateValue.setYear(xmlDateValueType.getYear());
		dateValue.setZone(xmlDateValueType.getZone());
		
		return dateValue;
	}
	
	protected MetastaticDiseaseSite convertMestastaticDiseaseSite(MetastaticDiseaseSiteType xmlMetastaticDiseaseSiteType){
		MetastaticDiseaseSite site = new MetastaticDiseaseSite();
		if (xmlMetastaticDiseaseSiteType.getAnatomicSite() != null){
			AnatomicSite anatomicSite = new AnatomicSite();
			anatomicSite.setName(xmlMetastaticDiseaseSiteType.getAnatomicSite().getName());
			anatomicSite.setCategory(xmlMetastaticDiseaseSiteType.getAnatomicSite().getCategory());
			site.setCodedSite(anatomicSite);
		}
		
		if(xmlMetastaticDiseaseSiteType.getOtherMetastaticDiseaseSite() != null){
			site.setOtherSite(xmlMetastaticDiseaseSiteType.getOtherMetastaticDiseaseSite());
		}
		
		return site;
	}
	
	protected AdverseEventResponseDescription convertAdverseEventResponseDescription(AdverseEventResponseDescriptionType xmlAdverseEventResponseDescriptionType){
		AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
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
	
	protected Reporter converterReporter(ReporterType xmlReporterType){
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
		}
		
		return reporter;
		
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

		if(reportingPeriodType.getStudyParticipantAssignment() != null){
			reportingPeriod.setAssignment(convertStudyParticipantAssignment(reportingPeriodType.getStudyParticipantAssignment()));
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
		studySite.setStudy(convertStudy(studySiteType.getStudy()));

		return studySite;
	}
	
	protected Organization convertOrganization(OrganizationType xmlOrganizationType){
		
		LocalOrganization organization = new LocalOrganization();
		organization.setName(xmlOrganizationType.getName());
		organization.setNciInstituteCode(xmlOrganizationType.getNciInstituteCode());
		
		return organization;
	}
	
	protected Study convertStudy(StudyType xmlStudyType) {
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
	
	private Study fetchStudy(Identifier identifier) {
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
