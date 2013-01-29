package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.DietarySupplementIntervention;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.EventStatus;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
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
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory.Measure;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReportingPeriodType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventResponseDescriptionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ContactMechanismType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DateValueType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.LabType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MetastaticDiseaseSiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantHistoryType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ParticipantType.Identifiers;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.PhysicianType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReporterType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyParticipantAssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.StudyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */

public class ExpeditedAdverseEventReportConverter {
	AEReportAdverseEventConverter aeReportAdverseEventConverter = new AEReportAdverseEventConverter();
	
	  /** The Constant EMAIL. {@link #getContactMechanisms} key for the e-mail address */
    protected static final String EMAIL = "e-mail";

    /** The Constant FAX. {@link #getContactMechanisms} key for the fax number */
    protected static final String FAX = "fax";

    /** The Constant PHONE. {@link #getContactMechanisms} key for the phone number */
    protected static final String PHONE = "phone";
	Study dbStudy = null;

	public Study getDbStudy() {
		return dbStudy;
	}

	public void setDbStudy(Study dbStudy) {
		this.dbStudy = dbStudy;
	}

	public ExpeditedAdverseEventReport convert(
			AdverseEventReport xmlAdverseEventReport) {
		ExpeditedAdverseEventReport domainAdverseEventReport = new ExpeditedAdverseEventReport();
		domainAdverseEventReport.setCreatedAt(new Timestamp(xmlAdverseEventReport.getCreatedAt().toGregorianCalendar().getTimeInMillis()));
		if(xmlAdverseEventReport.getAdverseEventResponseDescription() != null){
			domainAdverseEventReport.setResponseDescription(convertToDomainAdverseEventResponseDescription(xmlAdverseEventReport.getAdverseEventResponseDescription()));
		}
		
		domainAdverseEventReport.setReportingPeriod(convertToDomainReportingPeriod(xmlAdverseEventReport.getAdverseEventReportingPeriod()));
		
		domainAdverseEventReport.setReporter(convertToDomainReporter(xmlAdverseEventReport.getReporter()));
		domainAdverseEventReport.setPhysician(convertToDomainPhysician(xmlAdverseEventReport.getPhysician()));
		if(xmlAdverseEventReport.getParticipantHistory() != null){
			domainAdverseEventReport.setParticipantHistory(convertToDomainParticipantHistory(xmlAdverseEventReport.getParticipantHistory()));
		}
		
		if(xmlAdverseEventReport.getStudyParticipantAssignment() != null){
			domainAdverseEventReport.getReportingPeriod().setAssignment(convertToDomainStudyParticipantAssignment(xmlAdverseEventReport.getStudyParticipantAssignment()));
		}
		
		for(RadiationInterventionType xmlRadiationInterventionType : xmlAdverseEventReport.getRadiationIntervention()){
			domainAdverseEventReport.getRadiationInterventions().add(convertToDomainRadiationIntervention(xmlRadiationInterventionType));
		}
	
		for(SurgeryInterventionType xmlSurgeryInterventionType : xmlAdverseEventReport.getSurgeryIntervention()){
			domainAdverseEventReport.getSurgeryInterventions().add(convertToDomainSurgeryIntervention(xmlSurgeryInterventionType));
		}
	
		for(BiologicalInterventionType xmlBiologicalInterventionType : xmlAdverseEventReport.getBiologicalIntervention()){
			domainAdverseEventReport.getBiologicalInterventions().add(convertToDomainBiologicalIntervention(xmlBiologicalInterventionType));
		}
	
		for(DietarySupplementInterventionType xmlDietarySupplementInterventionType : xmlAdverseEventReport.getDietarySupplementIntervention()){
			domainAdverseEventReport.getDietaryInterventions().add(convertToDomainDietarySupplementIntervention(xmlDietarySupplementInterventionType));
		}
		
		for(AdverseEventType xmlAdverseEventType : xmlAdverseEventReport.getAdverseEvent()){
			domainAdverseEventReport.addAdverseEvent(aeReportAdverseEventConverter.convertAdverseEventDtoToAdverseEventDomain(
					xmlAdverseEventType, dbStudy.getAeTerminology(), domainAdverseEventReport.getReportingPeriod().getAssignment().getStartDateOfFirstCourse()));
		}
		
		for(MedicalDeviceType xmlMedicalDeviceType : xmlAdverseEventReport.getMedicalDevice()){
			domainAdverseEventReport.addMedicalDevice(convertToDomainMedicalDevice(xmlMedicalDeviceType));
		}
		
		for(ConcomitantMedicationType xmlConcomitantMedicationType : xmlAdverseEventReport.getConcomitantMedication()){
			domainAdverseEventReport.addConcomitantMedication(convertToDomainConcomitantMedication(xmlConcomitantMedicationType));
		}
		
		for(LabType xmlLabType : xmlAdverseEventReport.getLab()){
			domainAdverseEventReport.addLab(convertToDomainLab(xmlLabType));
		}
		
		for(SAEReportPreExistingConditionType saeReportPreExistingConditionType : xmlAdverseEventReport.getSAEReportPreExistingCondition()){
			domainAdverseEventReport.addSaeReportPreExistingCondition(convertToDomainSAEReportPreExistingCondition(saeReportPreExistingConditionType));
		}
		
		for(SAEReportPriorTherapyType saeReportPriorTherapyType : xmlAdverseEventReport.getSAEReportPriorTherapy()){
			domainAdverseEventReport.addSaeReportPriorTherapies(convertToDomainSAEReportPriorTherapy(saeReportPriorTherapyType));
		}

		return domainAdverseEventReport;
	}
	
	protected SAEReportPriorTherapy convertToDomainSAEReportPriorTherapy(SAEReportPriorTherapyType xmlSaeReportPriorTherapyType){
		SAEReportPriorTherapy saeReportPriorTherapy = new SAEReportPriorTherapy();
		if(xmlSaeReportPriorTherapyType.getStartDate() != null){
			saeReportPriorTherapy.setStartDate((convertToDomainDateValue(xmlSaeReportPriorTherapyType.getStartDate())));
		}
		
		if(xmlSaeReportPriorTherapyType.getEndDate() != null) {
			saeReportPriorTherapy.setEndDate((convertToDomainDateValue(xmlSaeReportPriorTherapyType.getEndDate())));
		}
		saeReportPriorTherapy.setOther(xmlSaeReportPriorTherapyType.getOther());
		if(xmlSaeReportPriorTherapyType.getPriorTherapy() != null){
			PriorTherapy priorTherapy = new PriorTherapy();
			priorTherapy.setText(xmlSaeReportPriorTherapyType.getPriorTherapy().getText());
			priorTherapy.setMeddraCode((xmlSaeReportPriorTherapyType.getPriorTherapy().getMeddraCode()));
			saeReportPriorTherapy.setPriorTherapy(priorTherapy);
		}
		
		return saeReportPriorTherapy;
	}
	
	
	protected SAEReportPreExistingCondition convertToDomainSAEReportPreExistingCondition(SAEReportPreExistingConditionType xmlSaeReportPreExistingConditionType){
		SAEReportPreExistingCondition saeReportPreExistingCondition = new SAEReportPreExistingCondition();
		saeReportPreExistingCondition.setOther(xmlSaeReportPreExistingConditionType.getOther());
		saeReportPreExistingCondition.setLinkedToOtherCause(xmlSaeReportPreExistingConditionType.isLinkedToOtherCause());
		if(xmlSaeReportPreExistingConditionType.getPreExistingCondition() != null){
			PreExistingCondition preExistingCondition = new PreExistingCondition();
			preExistingCondition.setText(xmlSaeReportPreExistingConditionType.getPreExistingCondition().getText());
			preExistingCondition.setMeddraHlgt(xmlSaeReportPreExistingConditionType.getPreExistingCondition().getMeddraHlgt());
			preExistingCondition.setMeddraLlt(xmlSaeReportPreExistingConditionType.getPreExistingCondition().getMeddraLlt());
			preExistingCondition.setMeddraLltCode(xmlSaeReportPreExistingConditionType.getPreExistingCondition().getMeddraLltCode());
		}
		
		return saeReportPreExistingCondition;
	}
	
	
	protected Lab convertToDomainLab(LabType xmlLabType){
		Lab lab = new Lab();
		lab.setUnits(xmlLabType.getUnits());
		lab.setNormalRange(xmlLabType.getNormalRange());
		
		if(xmlLabType.getBaseline() != null){
			LabValue baseline = new LabValue();
			baseline.setValue(xmlLabType.getBaseline().getValue());
			if(xmlLabType.getBaseline().getDate() != null){
				baseline.setDate(xmlLabType.getBaseline().getDate().toGregorianCalendar().getTime());
			}
			lab.setBaseline(baseline);
		}
		if(xmlLabType.getNadir() != null){
			LabValue nadir = new LabValue();
			nadir.setValue(xmlLabType.getNadir().getValue());
			if(xmlLabType.getNadir().getDate() != null){
				nadir.setDate(xmlLabType.getNadir().getDate().toGregorianCalendar().getTime());
			}
			lab.setNadir(nadir);
		}
		if(xmlLabType.getRecovery() != null){
			LabValue recovery = new LabValue();
			recovery.setValue(xmlLabType.getRecovery().getValue());
			if(xmlLabType.getRecovery().getDate() != null){
				recovery.setDate(xmlLabType.getRecovery().getDate().toGregorianCalendar().getTime());
			}
			lab.setRecovery(recovery);
		}
		
		if(xmlLabType.getLabTerm() != null){
			
			LabTerm labTerm = new LabTerm();
			labTerm.setTerm(xmlLabType.getLabTerm().getTerm());
			if(xmlLabType.getLabTerm().getCategory() != null){
				LabCategory labCategory = new LabCategory();
				labCategory.setName(xmlLabType.getLabTerm().getCategory().getName());
				labTerm.setCategory(labCategory);
			}
			lab.setLabTerm(labTerm);
		}
		
		
		return lab;
	}
	
	
	
	protected ConcomitantMedication convertToDomainConcomitantMedication(ConcomitantMedicationType xmlConcomitantMedicationType){
		ConcomitantMedication concomitantMedication = new ConcomitantMedication();
		concomitantMedication.setAgentName(xmlConcomitantMedicationType.getName().toString());
		if(xmlConcomitantMedicationType.getStartDate() != null){
			concomitantMedication.setStartDate(convertToDomainDateValue(xmlConcomitantMedicationType.getStartDate()));
		}
		
		if(xmlConcomitantMedicationType.getEndDate()!= null){
			concomitantMedication.setEndDate(convertToDomainDateValue(xmlConcomitantMedicationType.getEndDate()));
		}
		concomitantMedication.setStillTakingMedications(xmlConcomitantMedicationType.isStillTakingMedications());
		
		return concomitantMedication;		
	}
	
	protected MedicalDevice convertToDomainMedicalDevice(MedicalDeviceType xmlMedicalDeviceType){
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
	
	protected ParticipantHistory convertToDomainParticipantHistory(ParticipantHistoryType xmlParticipantHistoryType){
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
	
	protected RadiationIntervention convertToDomainRadiationIntervention(RadiationInterventionType xmlRadiationInterventionType){
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
			OtherIntervention otherIntervention = new OtherIntervention();
			otherIntervention.setName(xmlRadiationInterventionType.getOtherIntervention().getName());
			otherIntervention.setDescription(xmlRadiationInterventionType.getOtherIntervention().getDescription());
			intervention.setStudyRadiation(otherIntervention);
		}
		
		
		return intervention;
	}
	
	
	protected SurgeryIntervention convertToDomainSurgeryIntervention(SurgeryInterventionType xmlSurgeryInterventionType){
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
			OtherIntervention otherIntervention = new OtherIntervention();
			otherIntervention.setName(xmlSurgeryInterventionType.getOtherIntervention().getName());
			otherIntervention.setDescription(xmlSurgeryInterventionType.getOtherIntervention().getDescription());
			intervention.setStudySurgery(otherIntervention);
		}
		
		return intervention;
	}
	
	protected BiologicalIntervention convertToDomainBiologicalIntervention(BiologicalInterventionType xmlBiologicalInterventionType){
		BiologicalIntervention intervention = new BiologicalIntervention();
		intervention.setDescription(xmlBiologicalInterventionType.getDescription());
		
		if(xmlBiologicalInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention = new OtherIntervention();
			otherIntervention.setName(xmlBiologicalInterventionType.getOtherIntervention().getName());
			otherIntervention.setDescription(xmlBiologicalInterventionType.getOtherIntervention().getDescription());
			intervention.setStudyIntervention(otherIntervention);
		}
		
		return intervention;
	}
	
	protected DietarySupplementIntervention convertToDomainDietarySupplementIntervention(DietarySupplementInterventionType xmlDietarySupplementInterventionType){
		DietarySupplementIntervention intervention = new DietarySupplementIntervention();
		intervention.setDescription(xmlDietarySupplementInterventionType.getDescription());
		
		if(xmlDietarySupplementInterventionType.getOtherIntervention() != null){
			OtherIntervention otherIntervention = new OtherIntervention();
			otherIntervention.setName(xmlDietarySupplementInterventionType.getOtherIntervention().getName());
			otherIntervention.setDescription(xmlDietarySupplementInterventionType.getOtherIntervention().getDescription());
			intervention.setStudyIntervention(otherIntervention);
		}
		
		return intervention;
	}
	
	protected DiseaseHistory convertToDomainDiseaseHistory(DiseaseHistoryType xmlDiseaseHistoryType){
		DiseaseHistory diseaseHistory = new DiseaseHistory();
		if (xmlDiseaseHistoryType.getMetastaticDiseaseSite() != null){
			for(MetastaticDiseaseSiteType metastaticDiseaseSite : xmlDiseaseHistoryType.getMetastaticDiseaseSite()){
				diseaseHistory.addMetastaticDiseaseSite(convertToDomainMestastaticDiseaseSite(metastaticDiseaseSite));
			}
		}
		
		if(xmlDiseaseHistoryType.getDiagnosisDate() != null){
			diseaseHistory.setDiagnosisDate(convertToDomainDateValue(xmlDiseaseHistoryType.getDiagnosisDate()));
		}
		
		return diseaseHistory;
	}
	
	protected DateValue convertToDomainDateValue(DateValueType xmlDateValueType){
		DateValue dateValue = new DateValue();
		dateValue.setDay(xmlDateValueType.getDay());
		dateValue.setMonth(xmlDateValueType.getMonth());
		dateValue.setYear(xmlDateValueType.getYear());
		dateValue.setZone(xmlDateValueType.getZone());
		
		return dateValue;
	}
	
	protected MetastaticDiseaseSite convertToDomainMestastaticDiseaseSite(MetastaticDiseaseSiteType xmlMetastaticDiseaseSiteType){
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
	
	
	
	protected AdverseEventResponseDescription convertToDomainAdverseEventResponseDescription(AdverseEventResponseDescriptionType xmlAdverseEventResponseDescriptionType){
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
	
	protected Reporter convertToDomainReporter(ReporterType  xmlReporterType){
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
	
	
	protected Physician convertToDomainPhysician(PhysicianType  xmlPhysicianType){
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
	
	protected AdverseEventReportingPeriod convertToDomainReportingPeriod(AdverseEventReportingPeriodType xmlReportingPeriodType){
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		if(xmlReportingPeriodType.getStudyParticipantAssignment() != null){
			reportingPeriod.setAssignment(convertToDomainStudyParticipantAssignment(xmlReportingPeriodType.getStudyParticipantAssignment()));
		} 
		
		if(xmlReportingPeriodType.getTreatmentAssignment() != null){
			TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
			treatmentAssignment.setCode(xmlReportingPeriodType.getTreatmentAssignment().getCode());
			if(xmlReportingPeriodType.getTreatmentAssignment().getComments() != null){
				treatmentAssignment.setComments(xmlReportingPeriodType.getTreatmentAssignment().getComments());
			}
			treatmentAssignment.setDescription(xmlReportingPeriodType.getTreatmentAssignment().getDescription());
			treatmentAssignment.setDoseLevelOrder(xmlReportingPeriodType.getTreatmentAssignment().getDoseLevelOrder());
			
			reportingPeriod.setTreatmentAssignment(treatmentAssignment);
		}
		
		if(xmlReportingPeriodType.getStartDate() != null){
			reportingPeriod.setStartDate(xmlReportingPeriodType.getStartDate().toGregorianCalendar().getTime());
		}
		
		if(xmlReportingPeriodType.getCycleNumber() != null){
			reportingPeriod.setCycleNumber(xmlReportingPeriodType.getCycleNumber());
		}
		
		
		return reportingPeriod;
		
	}
	
	protected StudyParticipantAssignment convertToDomainStudyParticipantAssignment(StudyParticipantAssignmentType xmlAssignmentType) {
		StudyParticipantAssignment assignment=  new StudyParticipantAssignment();
		assignment.setStudySubjectIdentifier(xmlAssignmentType.getStudySubjectIdentifier());
		
		assignment.setStudySite(convertToDomainStudySite(xmlAssignmentType.getStudySite()));
		assignment.setDateOfEnrollment(xmlAssignmentType.getDateOfEnrollment().toGregorianCalendar().getTime());
		if(xmlAssignmentType.getStartDateOfFirstCourse() != null){
			assignment.setStartDateOfFirstCourse(xmlAssignmentType.getStartDateOfFirstCourse().toGregorianCalendar().getTime());
		}

		return assignment;
	}
	
	protected Participant convertToDomainParticipant(ParticipantType xmlParticipantType){
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
	
	protected OrganizationAssignedIdentifier convertOrganizationIdentifierTypeToDomainIdentifier(OrganizationAssignedIdentifierType organizationAssignedIdentifierType) throws Exception{
		Organization organization = new LocalOrganization();
		OrganizationAssignedIdentifier orgIdentifier = new OrganizationAssignedIdentifier();
		orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
		orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
		orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
		organization.setName(organizationAssignedIdentifierType.getOrganizationRef().getName());
		organization.setNciInstituteCode(organizationAssignedIdentifierType.getOrganizationRef().getNciInstituteCode());
		orgIdentifier.setOrganization(organization);
		return orgIdentifier;
		}
	
	protected StudySite convertToDomainStudySite(StudySiteType xmlStudySiteType) {
		StudySite studySite =  new StudySite();
		
		studySite.setOrganization(convertToDomainOrganization(xmlStudySiteType.getOrganization()));
		studySite.setStudy(convertToDomainStudy(xmlStudySiteType.getStudy()));

		return studySite;
	}
	
	protected Organization convertToDomainOrganization(OrganizationType xmlOrganizationType){
		
		LocalOrganization organization = new LocalOrganization();
		organization.setName(xmlOrganizationType.getName());
		organization.setNciInstituteCode(xmlOrganizationType.getNciInstituteCode());
		
		return organization;
	}
	
	protected Study convertToDomainStudy(StudyType xmlStudyType) {
		Study study =  new LocalStudy();
		Identifier identifier = new Identifier();
		
		identifier.setType(xmlStudyType.getIdentifiers().getIdentifier().getType().value());
		identifier.setValue(xmlStudyType.getIdentifiers().getIdentifier().getValue());
		study.addIdentifier(identifier);

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


}
