/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
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
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BiologicalInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ConcomitantMedicationType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.CourseAgentAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DeviceAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DietarySupplementInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.DiseaseAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GeneticInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.LabType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.MedicalDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherCauseType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.RadiationInterventionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.ReportType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SAEReportPriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.SurgeryInterventionType;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

/**
 * @author Ramakrishna Gundala
 */

public class ExpeditedAdverseEventReportConverter {
	AdverseEventConverter aeConverter = new AdverseEventConverter();
	
	ExpeditedAdverseEventReportConverterUtility utility = new ExpeditedAdverseEventReportConverterUtility();
	
    private MessageSource messageSource;
    
	public void setStudyDao(StudyDao studyDao) {
		utility.setStudyDao(studyDao);
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
        aeReport.setExternalId(aeReportDto.getExternalId());
        //reporting period
        if(aeReportDto.getAdverseEventReportingPeriod() != null) {
            AdverseEventReportingPeriod reportingPeriod = utility.convertAdverseEventReportingPeriod(aeReportDto.getAdverseEventReportingPeriod());
            reportingPeriod.addAeReport(aeReport);
        }
        
        //Adverse Events
        for(AdverseEventType aeType : aeReportDto.getAdverseEvent()){
            if ( aeReport.getReportingPeriod() != null) {

                AdverseEvent ae = aeConverter.convert(aeType);
                aeReport.getReportingPeriod().addAdverseEvent(ae);
                aeReport.addAdverseEvent(ae);

                for(CourseAgentAttributionType courseAgentAttributionType : aeType.getCourseAgentAttribution() ){
                    CourseAgentAttribution attribution = new CourseAgentAttribution();
                    attribution.setAttribution(Attribution.getByCode(courseAgentAttributionType.getAttribution()));
                    attribution.setCause(utility.convertCourseAgentRef(courseAgentAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getCourseAgentAttributions().add(attribution);
                }

                for(ConcomitantMedicationAttributionType conMedAttributionType : aeType.getConcomitantMedicationAttribution() ){
                    ConcomitantMedicationAttribution attribution = new ConcomitantMedicationAttribution();
                    attribution.setAttribution(Attribution.getByCode(conMedAttributionType.getAttribution()));
                    attribution.setCause(utility.convertConcomitantMedicationRef(conMedAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getConcomitantMedicationAttributions().add(attribution);
                }

                for(OtherCauseAttributionType otherCauseAttributionType : aeType.getOtherCauseAttribution() ){
                    OtherCauseAttribution attribution = new OtherCauseAttribution();
                    attribution.setAttribution(Attribution.getByCode(otherCauseAttributionType.getAttribution()));
                    attribution.setCause(utility.convertOtherCause(otherCauseAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getOtherCauseAttributions().add(attribution);
                }

                for(SurgeryAttributionType surgeryAttributionType : aeType.getSurgeryAttribution() ){
                    SurgeryAttribution attribution = new SurgeryAttribution();
                    attribution.setAttribution(Attribution.getByCode(surgeryAttributionType.getAttribution()));
                    attribution.setCause(utility.convertSurgeryIntervention(surgeryAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getSurgeryAttributions().add(attribution);
                }

                for(RadiationAttributionType radiationAttributionType : aeType.getRadiationAttribution() ){
                    RadiationAttribution attribution = new RadiationAttribution();
                    attribution.setAttribution(Attribution.getByCode(radiationAttributionType.getAttribution()));
                    attribution.setCause(utility.convertRadiationInterventionRef(radiationAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getRadiationAttributions().add(attribution);
                }

                for(DeviceAttributionType deviceAttributionType : aeType.getDeviceAttribution() ){
                    DeviceAttribution attribution = new DeviceAttribution();
                    attribution.setAttribution(Attribution.getByCode(deviceAttributionType.getAttribution()));
                    attribution.setCause(utility.convertMedicalDeviceRef(deviceAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDeviceAttributions().add(attribution);
                }
                
                for(BiologicalInterventionAttributionType biologicalInterventionAttributionType : aeType.getBiologicalInterventionAttribution() ){
                    BiologicalInterventionAttribution attribution = new BiologicalInterventionAttribution();
                    attribution.setAttribution(Attribution.getByCode(biologicalInterventionAttributionType.getAttribution()));
                    attribution.setCause(utility.convertBiologicalIntervention(biologicalInterventionAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getBiologicalInterventionAttributions().add(attribution);
                }

                for(DietarySupplementInterventionAttributionType dietarySupplementInterventionAttributionType : aeType.getDietarySupplementInterventionAttribution() ){
                    DietarySupplementInterventionAttribution attribution = new DietarySupplementInterventionAttribution();
                    attribution.setAttribution(Attribution.getByCode(dietarySupplementInterventionAttributionType.getAttribution()));
                    attribution.setCause(utility.convertDietarySupplementIntervention(dietarySupplementInterventionAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDietarySupplementInterventionAttributions().add(attribution);
                }
                
                
                for(DiseaseAttributionType diseaseAttributionType : aeType.getDiseaseAttribution() ){
                    DiseaseAttribution attribution = new DiseaseAttribution();
                    attribution.setAttribution(Attribution.getByCode(diseaseAttributionType.getAttribution()));
                	attribution.setCause(utility.convertDiseaseHistoryRef(diseaseAttributionType.getCause()));
                    attribution.setAdverseEvent(ae);
                    ae.getDiseaseAttributions().add(attribution);
                }
               
                for(OtherInterventionAttributionType otherInterventionAttributionType : aeType.getOtherInterventionAttribution() ){
                    OtherInterventionAttribution attribution = new OtherInterventionAttribution();
                    attribution.setAttribution(Attribution.getByCode(otherInterventionAttributionType.getAttribution()));
                    attribution.setCause( utility.convertOtherAEIntervention(otherInterventionAttributionType.getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getOtherInterventionAttributions().add(attribution);
                }
                
                for(BehavioralInterventionAttributionType behavioralInterventionAttributionType : aeType.getBehavioralInterventionAttribution() ){                
                    BehavioralInterventionAttribution attribution = new BehavioralInterventionAttribution();
                    attribution.setAttribution(Attribution.getByCode(behavioralInterventionAttributionType.getAttribution()));
                    attribution.setCause(utility.convertBehavioralIntervention(behavioralInterventionAttributionType.getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getBehavioralInterventionAttributions().add(attribution);
                }
                
                for(GeneticInterventionAttributionType geneticInterventionAttributionType : aeType.getGeneticInterventionAttribution() ){                
                	GeneticInterventionAttribution attribution = new GeneticInterventionAttribution();
                    attribution.setAttribution(Attribution.getByCode(geneticInterventionAttributionType.getAttribution()));
                    attribution.setCause(utility.convertGeneticIntervention(geneticInterventionAttributionType.getCause(), aeReport));	
                    attribution.setAdverseEvent(ae);
                    ae.getGeneticInterventionAttributions().add(attribution);
                }
            }
        }
        

        //response description
        if(aeReportDto.getAdverseEventResponseDescription() != null){
			aeReport.setResponseDescription(utility.convertAdverseEventResponseDescription(aeReportDto.getAdverseEventResponseDescription()));
		}


        //reporter
        if(aeReportDto.getReporter() != null){
            aeReport.setReporter(utility.convertReporter(aeReportDto.getReporter()));
        }


		//physician
        if(aeReportDto.getPhysician() != null){
            aeReport.setPhysician(utility.convertPhysician(aeReportDto.getPhysician()));
        }

        //participant Medical History
		if(aeReportDto.getParticipantHistory() != null){
			aeReport.setParticipantHistory(utility.convertParticipantHistory(aeReportDto.getParticipantHistory()));
		}

        //Medical History - Disease History
        if(aeReportDto.getDiseaseHistory() != null){
            aeReport.setDiseaseHistory(utility.convertDiseaseHistory(aeReportDto.getDiseaseHistory()));
        }

        //Medical History - Con Med
        for(ConcomitantMedicationType xmlConcomitantMedicationType : aeReportDto.getConcomitantMedication()){
            aeReport.addConcomitantMedication(utility.convertConcomitantMedication(xmlConcomitantMedicationType));
        }


        //Medical History - Pre-cond
        for(SAEReportPreExistingConditionType saeReportPreExistingConditionType : aeReportDto.getSAEReportPreExistingCondition()){
            aeReport.addSaeReportPreExistingCondition(utility.convertPreExistingCondition(saeReportPreExistingConditionType));
        }

        //Medical History - prior therapy
        for(SAEReportPriorTherapyType saeReportPriorTherapyType : aeReportDto.getSAEReportPriorTherapy()){
            aeReport.addSaeReportPriorTherapies(utility.convertPriorTherapy(saeReportPriorTherapyType));
        }

        //Intervention - Course Agent - Treatment information
        if(aeReportDto.getTreatmentInformation() != null){
            aeReport.setTreatmentInformation(utility.convertTreatmentInformation(aeReportDto.getTreatmentInformation()));
            aeReport.getAssignment().setStartDateOfFirstCourse(aeReport.getTreatmentInformation().getFirstCourseDate());
        }

        //Intervention - Device
        aeReport.setInvestigationalDeviceAdministered(aeReportDto.isInvestigationalDeviceAdministered());
        for(MedicalDeviceType xmlMedicalDeviceType : aeReportDto.getMedicalDevice()){
            aeReport.addMedicalDevice(utility.convertMedicalDevice(xmlMedicalDeviceType));
        }


        //Intervention - Radiation
		for(RadiationInterventionType radiationType : aeReportDto.getRadiationIntervention()){
			aeReport.getRadiationInterventions().add(utility.convertRadiationIntervention(radiationType));
		}

        //Intervention - Surgery
		for(SurgeryInterventionType surgeryType : aeReportDto.getSurgeryIntervention()){
			aeReport.getSurgeryInterventions().add(utility.convertSurgeryIntervention(surgeryType));
		}

        //Intervention - Biological
		for(BiologicalInterventionType biologicalType : aeReportDto.getBiologicalIntervention()){
			aeReport.getBiologicalInterventions().add(utility.convertBiologicalIntervention(biologicalType));
		}

        //Intervention - Dietary
		for(DietarySupplementInterventionType dietarySupplementType : aeReportDto.getDietarySupplementIntervention()){
			aeReport.getDietaryInterventions().add(utility.convertDietarySupplementIntervention(dietarySupplementType));
		}

        //Lab
		List<Lab> allLabs = new ArrayList<Lab>();
		for(LabType xmlLabType : aeReportDto.getLab()){
			allLabs.add(utility.convertLab(xmlLabType));
		}
		
		if(!allLabs.isEmpty()){
			List<Lab> reportLabs = utility.mergeLabs(allLabs);
			for(Lab lab : reportLabs){
				aeReport.addLab(lab);
			}
		}

        //Other cause
		for(OtherCauseType xmlOtherCauseType : aeReportDto.getOtherCause()){
			aeReport.addOtherCause(utility.convertOtherCause(xmlOtherCauseType));
		}

        //Additional Info
		if(aeReportDto.getAdditionalInformation() != null){
			aeReport.setAdditionalInformation(utility.convertAdditionalInformation(aeReportDto.getAdditionalInformation()));
		}

        //Reports
		for(ReportType xmlReportType : aeReportDto.getReport()){
            Report report = utility.convertReport(xmlReportType, aeReportDto.getSubmitter() );
			aeReport.addReport(report);

            //special case if external data collection do not have external Id, add case number
            if(StringUtils.isEmpty(aeReport.getExternalId())) aeReport.setExternalId(report.getCaseNumber());
		}
		
		// Set the study Information to the Source Report.
		return aeReport;
	}
	
}