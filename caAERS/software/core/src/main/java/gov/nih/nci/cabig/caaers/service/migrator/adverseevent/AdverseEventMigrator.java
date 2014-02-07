/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.DietarySupplementIntervention;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.Term;
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
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BehavioralInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.GeneticInterventionAttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OtherInterventionAttributionType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

/**
 * User: Biju Joseph
 * Date: 1/29/13
 */
public class AdverseEventMigrator implements Migrator<AdverseEventReportingPeriod> {
    private static Log logger = LogFactory.getLog(AdverseEventMigrator.class);
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    public void migrate(AdverseEventReportingPeriod src, AdverseEventReportingPeriod dest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome) {
        Study study = dest.getStudy();
        AeTerminology aeTerminology = study.getAeTerminology();

        for(AdverseEvent aeSrc : src.getAdverseEvents()){
            AdverseEvent aeDest = new AdverseEvent();

            aeDest.setStartDate(aeSrc.getStartDate());
            aeDest.setEndDate(aeSrc.getEndDate());
            aeDest.setGradedDate(aeSrc.getGradedDate());

            aeDest.setAttributionSummary(aeSrc.getAttributionSummary());
            aeDest.setExpected(aeSrc.getExpected());
            aeDest.setGrade(aeSrc.getGrade());
            aeDest.setHospitalization(aeSrc.getHospitalization());

            aeDest.setExternalId(aeSrc.getExternalId());
            aeDest.setComments(aeSrc.getComments());

            aeDest.setSolicited(aeSrc.getSolicited());

            aeDest.setEventApproximateTime(aeSrc.getEventApproximateTime());
            aeDest.setEventLocation(aeSrc.getEventLocation());

            aeDest.setParticipantAtRisk(aeSrc.getParticipantAtRisk());

            aeDest.setOutcomes(aeSrc.getOutcomes());

            aeDest.setDetailsForOther(aeSrc.getDetailsForOther());
            aeDest.setReporterEmail(aeSrc.getReporterEmail());

            if(aeTerminology.getTerm() == Term.CTC){

                AdverseEventCtcTerm aeSrcTerm = aeSrc.getAdverseEventCtcTerm();
                if(aeSrcTerm == null || aeSrcTerm.getCtcTerm() == null || aeSrcTerm.getCtcTerm().getCtepCode() == null){
                    logger.error("The AE terminology on the study is CTC, but there is no CTC term (CtepCode) present in the adverse event");
                    outcome.addError("WS_AEMS_020", "CTC term/Ctep code not found ");
                    return;
                }
                AdverseEventCtcTerm aeCtcTermDest = new AdverseEventCtcTerm();
                CtcTerm ctcTerm = fetchCtcTerm(aeSrcTerm.getCtcTerm().getCtepCode(), aeTerminology.getCtcVersion().getName());
                if(ctcTerm == null){
                    logger.error("The CTC term with ctep code " + aeSrcTerm.getCtcTerm().getCtepCode() + " is not found under version : " + aeTerminology.getCtcVersion().getName());
                    outcome.addError("WS_AEMS_020", "CTC term/Ctep code not found", new String[]{aeSrcTerm.getCtcTerm().getCtepCode()});
                    return;
                }
                aeCtcTermDest.setTerm(ctcTerm);
                aeDest.setAdverseEventCtcTerm(aeCtcTermDest);
                aeCtcTermDest.setAdverseEvent(aeDest);
                if(ctcTerm.isOtherRequired()){
                    if(dest.getStudy().getOtherMeddra() != null){
                        //must have other Meddra
                        if(aeSrc.getLowLevelTerm() == null){
                            logger.error("The Ctc term " + ctcTerm.getCtepCode() + ", needs Other specify (MedDRA) term, but not found");
                            outcome.addError("WS_AEMS_022", "Other MedDRA is missing");
                            return;
                        }
                        LowLevelTerm lowLevelTerm = fetchLowLevelTerm(aeSrc.getLowLevelTerm().getMeddraCode(), dest.getStudy().getOtherMeddra().getId());
                        if(lowLevelTerm == null){
                            logger.error("MedDRA Term is not found (meddra code : " + aeSrc.getLowLevelTerm().getMeddraCode() + ")");
                            outcome.addError("WS_AEMS_021", "Other MedDRA term is not found", new String[]{aeSrc.getLowLevelTerm().getMeddraCode()});
                            return;
                        }
                        aeDest.setLowLevelTerm(lowLevelTerm);

                    }else {
                        //must have other specify
                        if(aeSrc.getOtherSpecify() == null){
                            logger.error("The Ctc term " + ctcTerm.getCtepCode() + ", needs Other Specify (text), but not found");
                            outcome.addError("WS_AEMS_078", "Other specify text is missing", new String[]{ctcTerm.getCtepCode()});
                            return;
                        }
                        aeDest.setOtherSpecify(aeSrc.getOtherSpecify());
                    }
                }
            }else if(aeTerminology.getTerm() == Term.MEDDRA){
                AdverseEventMeddraLowLevelTerm aeSrcTerm = aeSrc.getAdverseEventMeddraLowLevelTerm();
                if(aeSrcTerm == null || aeSrcTerm.getLowLevelTerm() == null || aeSrcTerm.getLowLevelTerm().getMeddraCode() == null){
                    logger.error("The AE terminology on the study is MedDRA, but there is no MedDRA term (meddra code) present in the adverse event");
                    outcome.addError("WS_AEMS_021", "MedDRA term/MedDRA code not found");
                    return;
                }
                AdverseEventMeddraLowLevelTerm meddraTerm = new AdverseEventMeddraLowLevelTerm();
                LowLevelTerm lowLevelTerm = fetchLowLevelTerm(aeSrcTerm.getLowLevelTerm().getMeddraCode(), aeTerminology.getMeddraVersion().getId());
                if(lowLevelTerm == null){
                    logger.error("The AE terminology on the study is MedDRA, but there is no MedDRA term (meddra code) present in the adverse event");
                    outcome.addError("WS_AEMS_021", "MedDRA term/MedDRA code not found", aeSrcTerm.getLowLevelTerm().getMeddraCode());
                    return;
                }
                meddraTerm.setTerm(lowLevelTerm);
                meddraTerm.setAdverseEvent(aeDest);
                aeDest.setAdverseEventMeddraLowLevelTerm(meddraTerm);
            }
            
            // migrate adverse event attributions
            
           //  migrate course attributions
            if(!aeSrc.getCourseAgentAttributions().isEmpty()){
            	for(CourseAgentAttribution srcCAAttribution : aeSrc.getCourseAgentAttributions()){
            		 CourseAgentAttribution attribution = new CourseAgentAttribution();
                     attribution.setAttribution(srcCAAttribution.getAttribution());
                     CourseAgent caDest = new CourseAgent();
                     migrateCourseAgent(srcCAAttribution.getCause(), caDest, outcome, src.getStudy());
                     attribution.setCause(caDest);
                     attribution.setAdverseEvent(aeDest);
            		 aeDest.getCourseAgentAttributions().add(attribution);
            	}
            	
            }
            
            // migrate concomitant medications attributions
            
            if(!aeSrc.getConcomitantMedicationAttributions().isEmpty()){
	            for(ConcomitantMedicationAttribution srcConMedAttribution : aeSrc.getConcomitantMedicationAttributions() ){
	                ConcomitantMedicationAttribution attribution = new ConcomitantMedicationAttribution();
	                attribution.setAttribution(srcConMedAttribution.getAttribution());
	                ConcomitantMedication conMed = new ConcomitantMedication();
	                conMed.setAgentName(srcConMedAttribution.getCause().getAgentName());
	                conMed.setEndDate(srcConMedAttribution.getCause().getEndDate());
	                conMed.setStartDate(srcConMedAttribution.getCause().getStartDate());
	                conMed.setStillTakingMedications(srcConMedAttribution.getCause().getStillTakingMedications());
	                conMed.setReport(src.getAeReports().get(0));
	                attribution.setCause(conMed);
	                attribution.setAdverseEvent(aeDest);
	                aeDest.getConcomitantMedicationAttributions().add(attribution);
	            }
            }
            
            // migrate other cause attributions
            
            if(!aeSrc.getOtherCauseAttributions().isEmpty()){
            
	            for(OtherCauseAttribution srcOtherCauseAttribution : aeSrc.getOtherCauseAttributions() ){
	                OtherCauseAttribution attribution = new OtherCauseAttribution();
	                attribution.setAttribution(srcOtherCauseAttribution.getAttribution());
	                
	                OtherCause otherCause = new OtherCause();
	        		otherCause.setText(srcOtherCauseAttribution.getCause().getText());
	                attribution.setCause(otherCause);
	                
	                attribution.setAdverseEvent(aeDest);
	                aeDest.getOtherCauseAttributions().add(attribution);
	            }
            }
            
            // migrate surgery attributions
            
            for(SurgeryAttribution srcSurgeryAttribution : aeSrc.getSurgeryAttributions() ){
                SurgeryAttribution attribution = new SurgeryAttribution();
                attribution.setAttribution(srcSurgeryAttribution.getAttribution());
                
                
                SurgeryIntervention intervention = new SurgeryIntervention();
        		if(srcSurgeryAttribution.getCause().getInterventionDate() != null){
        			intervention.setInterventionDate(srcSurgeryAttribution.getCause().getInterventionDate());
        		}
        		
        		if(srcSurgeryAttribution.getCause().getInterventionSite() != null){
        			InterventionSite interventionSite = new InterventionSite();
        			interventionSite.setName(srcSurgeryAttribution.getCause().getInterventionSite().getName());
        			intervention.setInterventionSite(interventionSite);
        		}
        		
        		if(srcSurgeryAttribution.getCause().getStudySurgery() != null){
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcSurgeryAttribution.getCause().getStudySurgery().getName());
        			otherIntervention.setDescription(srcSurgeryAttribution.getCause().getStudySurgery().getDescription());
        			otherIntervention.setStudy(study);
        			if(srcSurgeryAttribution.getCause().getStudySurgery().getStudyTherapyType() != null){
        				otherIntervention.setStudyTherapyType(srcSurgeryAttribution.getCause().getStudySurgery().getStudyTherapyType() );
        			}
        			intervention.setStudySurgery(otherIntervention);
        		}
                
                
                attribution.setCause(intervention);
                attribution.setAdverseEvent(aeDest);
                aeDest.getSurgeryAttributions().add(attribution);
            }
            
            // migrate radiation attributions
            
            for(RadiationAttribution srcRadiationAttribution : aeSrc.getRadiationAttributions() ){
                RadiationAttribution attribution = new RadiationAttribution();
                attribution.setAttribution(srcRadiationAttribution.getAttribution());
                
                RadiationIntervention intervention = new RadiationIntervention();
        		intervention.setAdjustment(srcRadiationAttribution.getCause().getAdjustment());
        		intervention.setDaysElapsed(String.valueOf(srcRadiationAttribution.getCause().getDaysElapsed()));
        		intervention.setDosage(String.valueOf(srcRadiationAttribution.getCause().getDosage()));
        		intervention.setDosageUnit(String.valueOf(srcRadiationAttribution.getCause().getDosageUnit()));
        		if(srcRadiationAttribution.getCause().getLastTreatmentDate() != null){
        			intervention.setLastTreatmentDate(srcRadiationAttribution.getCause().getLastTreatmentDate());
        		}
        		intervention.setFractionNumber(String.valueOf(srcRadiationAttribution.getCause().getFractionNumber()));
        		intervention.setAdjustment(srcRadiationAttribution.getCause().getAdjustment());
        		if(srcRadiationAttribution.getCause().getStudyRadiation() != null){
        			OtherIntervention otherIntervention =new OtherIntervention();
        			
        			otherIntervention.setName(srcRadiationAttribution.getCause().getStudyRadiation().getName());
        			otherIntervention.setDescription(srcRadiationAttribution.getCause().getStudyRadiation().getDescription());
        			otherIntervention.setStudy(study);
        			if(srcRadiationAttribution.getCause().getStudyRadiation().getStudyTherapyType() != null){
        				otherIntervention.setStudyTherapyType(srcRadiationAttribution.getCause().getStudyRadiation().getStudyTherapyType() );
        			}
        			
        			intervention.setStudyRadiation(otherIntervention);
        		}
        		intervention.setAdministration(srcRadiationAttribution.getCause().getAdministration());
                attribution.setCause(intervention);
                attribution.setAdverseEvent(aeDest);
                aeDest.getRadiationAttributions().add(attribution);
            }
            
            // migrate device attributions
            
            for(DeviceAttribution srcdeviceAttribution : aeSrc.getDeviceAttributions() ){
                DeviceAttribution attribution = new DeviceAttribution();
                attribution.setAttribution(srcdeviceAttribution.getAttribution());
               
                
                MedicalDevice medicalDevice = new MedicalDevice();
        		medicalDevice.setBrandName(srcdeviceAttribution.getCause().getBrandName());
        		medicalDevice.setCatalogNumber(srcdeviceAttribution.getCause().getCatalogNumber());
        		medicalDevice.setCommonName(srcdeviceAttribution.getCause().getCommonName());
        		if(srcdeviceAttribution.getCause().getDeviceOperator() != null){
        			medicalDevice.setDeviceOperator(srcdeviceAttribution.getCause().getDeviceOperator());
        		}
        		
        		medicalDevice.setDeviceType(srcdeviceAttribution.getCause().getDeviceType());
        		medicalDevice.setManufacturerName(srcdeviceAttribution.getCause().getManufacturerName());
        		medicalDevice.setManufacturerCity(srcdeviceAttribution.getCause().getManufacturerCity());
        		medicalDevice.setManufacturerState(srcdeviceAttribution.getCause().getManufacturerState());
        		medicalDevice.setModelNumber(srcdeviceAttribution.getCause().getModelNumber());
        		medicalDevice.setSerialNumber(srcdeviceAttribution.getCause().getSerialNumber());
        		medicalDevice.setOtherNumber(srcdeviceAttribution.getCause().getOtherNumber());
        		if(srcdeviceAttribution.getCause().getExplantedDate() != null){
        			medicalDevice.setExplantedDate(srcdeviceAttribution.getCause().getExplantedDate());
        		}
        		
        		if(srcdeviceAttribution.getCause().getDeviceReprocessed() != null){
        			medicalDevice.setDeviceReprocessed(ReprocessedDevice.valueOf(srcdeviceAttribution.getCause().getDeviceReprocessed().name()));
        		}
        		if(srcdeviceAttribution.getCause().getEvaluationAvailability() != null){
        			medicalDevice.setEvaluationAvailability(Availability.valueOf(srcdeviceAttribution.getCause().getEvaluationAvailability().name()));
        		}
        		
        		if(srcdeviceAttribution.getCause().getStudyDevice() != null){
        			StudyDevice studyDevice = new StudyDevice();
        			if(srcdeviceAttribution.getCause().getStudyDevice().getDevice() != null){
        				Device device = new Device();
        				device.setType(srcdeviceAttribution.getCause().getStudyDevice().getDevice().getType());
        				device.setBrandName(srcdeviceAttribution.getCause().getStudyDevice().getDevice().getBrandName());
        				device.setCommonName(srcdeviceAttribution.getCause().getStudyDevice().getDevice().getCommonName());

                        studyDevice.setDevice(device);

        				studyDevice.setCatalogNumber(srcdeviceAttribution.getCause().getStudyDevice().getCatalogNumber());
        				studyDevice.setModelNumber(srcdeviceAttribution.getCause().getStudyDevice().getModelNumber());
        				studyDevice.setManufacturerName(srcdeviceAttribution.getCause().getStudyDevice().getManufacturerName());
        				studyDevice.setManufacturerCity(srcdeviceAttribution.getCause().getStudyDevice().getManufacturerCity());
        				studyDevice.setManufacturerState(srcdeviceAttribution.getCause().getStudyDevice().getManufacturerState());
        			} else {
        				studyDevice.setOtherBrandName(srcdeviceAttribution.getCause().getStudyDevice().getOtherBrandName());
        				studyDevice.setOtherCommonName(srcdeviceAttribution.getCause().getStudyDevice().getOtherCommonName());
        				studyDevice.setOtherDeviceType(srcdeviceAttribution.getCause().getStudyDevice().getOtherDeviceType());
        			}

        			medicalDevice.setStudyDevice(studyDevice);
        		}
        		
        		
        		 attribution.setCause(medicalDevice);
                
                attribution.setAdverseEvent(aeDest);
                aeDest.getDeviceAttributions().add(attribution);
            }
            
            
            // migrate biological intervention attributions
            
            for(BiologicalInterventionAttribution srcBiologicalInterventionAttribution : aeSrc.getBiologicalInterventionAttributions() ){
                BiologicalInterventionAttribution attribution = new BiologicalInterventionAttribution();
                attribution.setAttribution(srcBiologicalInterventionAttribution.getAttribution());
                
                
                BiologicalIntervention intervention = new BiologicalIntervention();
        		intervention.setDescription(srcBiologicalInterventionAttribution.getCause().getDescription());
        		
        		if(srcBiologicalInterventionAttribution.getCause().getStudyIntervention() != null){
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcBiologicalInterventionAttribution.getCause().getStudyIntervention().getName());
        			otherIntervention.setDescription(srcBiologicalInterventionAttribution.getCause().getStudyIntervention().getDescription());
        			intervention.setStudyIntervention(otherIntervention);
        		}
                
                attribution.setCause(intervention);
                attribution.setAdverseEvent(aeDest);
                aeDest.getBiologicalInterventionAttributions().add(attribution);
            }
            
            
            // migrate DietarySupplemental Intervention Attributions

            for(DietarySupplementInterventionAttribution srcDietarySupplementInterventionAttribution : aeSrc.getDietarySupplementInterventionAttributions() ){
                DietarySupplementInterventionAttribution attribution = new DietarySupplementInterventionAttribution();
                
                attribution.setAttribution(srcDietarySupplementInterventionAttribution.getAttribution());
                
            	DietarySupplementIntervention intervention = new DietarySupplementIntervention();
        		intervention.setDescription(srcDietarySupplementInterventionAttribution.getCause().getDescription());
        		
        		if(srcDietarySupplementInterventionAttribution.getCause().getStudyIntervention() != null){
        			
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcDietarySupplementInterventionAttribution.getCause().getStudyIntervention().getName());
        			otherIntervention.setDescription(srcDietarySupplementInterventionAttribution.getCause().getStudyIntervention().getDescription());
        			intervention.setStudyIntervention(otherIntervention);
        		}
                
        		 attribution.setCause(intervention);
                
                attribution.setAdverseEvent(aeDest);
                aeDest.getDietarySupplementInterventionAttributions().add(attribution);
            }
            
            // migrate disease attributions
            
            for(DiseaseAttribution srcDiseaseAttribution : aeSrc.getDiseaseAttributions() ){
                DiseaseAttribution attribution = new DiseaseAttribution();
                attribution.setAttribution(srcDiseaseAttribution.getAttribution());
            	
            	DiseaseHistory diseaseHistory = new DiseaseHistory();
            	
        		if (srcDiseaseAttribution.getCause().getMetastaticDiseaseSites() != null){
        			for(MetastaticDiseaseSite metastaticDiseaseSite : srcDiseaseAttribution.getCause().getMetastaticDiseaseSites()){
        				
        				MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        				if (metastaticDiseaseSite.getCodedSite() != null){
        					AnatomicSite anatomicSite = new AnatomicSite();
        					anatomicSite.setName(metastaticDiseaseSite.getCodedSite().getName());
        					anatomicSite.setCategory(metastaticDiseaseSite.getCodedSite().getCategory());
        					site.setCodedSite(anatomicSite);
        				}
        				
        				if(metastaticDiseaseSite.getOtherSite()!= null){
        					site.setOtherSite(metastaticDiseaseSite.getOtherSite());
        				}
        				
        				diseaseHistory.addMetastaticDiseaseSite(site);
        			}
        		}
        		
        		if(srcDiseaseAttribution.getCause().getDiagnosisDate() != null){
        			diseaseHistory.setDiagnosisDate(srcDiseaseAttribution.getCause().getDiagnosisDate());
        		}
            	
        		attribution.setCause(diseaseHistory);
            	
                attribution.setAdverseEvent(aeDest);
                aeDest.getDiseaseAttributions().add(attribution);
            }
            
            // migrate other intervention attributions
            
            for(OtherInterventionAttribution srcOtherInterventionAttribution : aeSrc.getOtherInterventionAttributions() ){
                OtherInterventionAttribution attribution = new OtherInterventionAttribution();
                attribution.setAttribution(srcOtherInterventionAttribution.getAttribution());
                
                OtherAEIntervention otherAEIntervention = new OtherAEIntervention();
        		otherAEIntervention.setDescription(srcOtherInterventionAttribution.getCause().getDescription());
        		if(srcOtherInterventionAttribution.getCause().getStudyIntervention() != null){
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcOtherInterventionAttribution.getCause().getStudyIntervention().getName());
        			otherIntervention.setDescription(srcOtherInterventionAttribution.getCause().getStudyIntervention().getDescription());
        			
        			otherAEIntervention.setStudyIntervention(otherIntervention);
        		}
        		otherAEIntervention.setReport(src.getAeReports().get(0));
        		
        		
                attribution.setCause(otherAEIntervention);	
                
                attribution.setAdverseEvent(aeDest);                
                aeDest.getOtherInterventionAttributions().add(attribution);
            }
            
            // migrate behavioral intervention attributions
            
            for(BehavioralInterventionAttribution srcBehavioralInterventionAttribution : aeSrc.getBehavioralInterventionAttributions() ){                
                BehavioralInterventionAttribution attribution = new BehavioralInterventionAttribution();
                attribution.setAttribution(srcBehavioralInterventionAttribution.getAttribution());
                
                BehavioralIntervention behavioralIntervention= new BehavioralIntervention();
        		behavioralIntervention.setDescription(srcBehavioralInterventionAttribution.getCause().getDescription());
        		if(srcBehavioralInterventionAttribution.getCause().getStudyIntervention() != null){
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcBehavioralInterventionAttribution.getCause().getStudyIntervention().getName());
        			otherIntervention.setDescription(srcBehavioralInterventionAttribution.getCause().getStudyIntervention().getDescription());
        			behavioralIntervention.setStudyIntervention(otherIntervention);
        		}
        		behavioralIntervention.setReport(src.getAeReports().get(0));
                
                attribution.setCause(behavioralIntervention);	
                
                attribution.setAdverseEvent(aeDest);
                aeDest.getBehavioralInterventionAttributions().add(attribution);
            }
            
            // migrate Genetic Intervention attributions
            
            for(GeneticInterventionAttribution srcGeneticInterventionAttribution : aeSrc.getGeneticInterventionAttributions() ){                
            	GeneticInterventionAttribution attribution = new GeneticInterventionAttribution();
                attribution.setAttribution(srcGeneticInterventionAttribution.getAttribution());
                
                GeneticIntervention geneticIntervention= new GeneticIntervention();
        		geneticIntervention.setDescription(srcGeneticInterventionAttribution.getCause().getDescription());
        		if(srcGeneticInterventionAttribution.getCause().getStudyIntervention() != null){
        			OtherIntervention otherIntervention = new OtherIntervention();
        			otherIntervention.setName(srcGeneticInterventionAttribution.getCause().getStudyIntervention().getName());
        			otherIntervention.setDescription(srcGeneticInterventionAttribution.getCause().getStudyIntervention().getDescription());
        			geneticIntervention.setStudyIntervention(otherIntervention);
        		}
        		geneticIntervention.setReport(src.getAeReports().get(0));
        		 attribution.setCause(geneticIntervention);	
                
                attribution.setAdverseEvent(aeDest);
                aeDest.getGeneticInterventionAttributions().add(attribution);
            }
            
            dest.addAdverseEvent(aeDest);

        }
    }
    
    
    private void migrateCourseAgent(CourseAgent caSrc, CourseAgent caDest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome, Study study){


        StudyAgent studyAgent = caSrc.getStudyAgent();
        if(studyAgent == null || ( (studyAgent.getAgent() == null || StringUtils.isEmpty(studyAgent.getAgent().getNscNumber() ) ) && StringUtils.isEmpty(studyAgent.getOtherAgent() ) ) )  {
            outcome.addWarning("ER-CA-1", "Study Agent is missing in the source");
            return;
        }

        StudyAgent realStudyAgent = studyAgent.getAgent() == null ? study.findStudyAgentByNscOrName(studyAgent.getOtherAgent()) : study.findStudyAgentByNscOrName(studyAgent.getAgent().getNscNumber());
        if(realStudyAgent == null){
            outcome.addWarning("ER-CA-2", "Given Agent is no longer associated to the study");
            return;
        }
        // set the study agent
        caDest.setStudyAgent(realStudyAgent);

        caDest.setDose(caSrc.getDose());
        caDest.setAdministrationDelay(caSrc.getAdministrationDelay());
        caDest.setAdministrationDelayAmount(caSrc.getAdministrationDelayAmount());
        caDest.setAdministrationDelayUnits(caSrc.getAdministrationDelayUnits());
        caDest.setComments(caSrc.getComments());
        caDest.setAgentAdjustment(caSrc.getAgentAdjustment());
        caDest.setModifiedDose(caSrc.getModifiedDose());
        caDest.setLastAdministeredDate(caSrc.getLastAdministeredDate());
        caDest.setDurationAndSchedule(caSrc.getDurationAndSchedule());
        caDest.setFirstAdministeredDate(caSrc.getFirstAdministeredDate());
        caDest.setTotalDoseAdministeredThisCourse(caSrc.getTotalDoseAdministeredThisCourse());
        caDest.setFormulation(caSrc.getFormulation());
        caDest.setLotNumber(caSrc.getLotNumber());

    }

    public CtcTerm fetchCtcTerm(String ctcCode, String ctcVersionName){
       List<CtcTerm> ctcTerms = ctcTermDao.getByCtepCodeandVersion(ctcCode, ctcVersionName);
        if(CollectionUtils.isEmpty(ctcTerms)) return null;
        return ctcTerms.get(0);
    }

    public LowLevelTerm fetchLowLevelTerm(String meddraCode, int meddraVersionId){
        List<LowLevelTerm> llts = lowLevelTermDao.getByMeddraCodeandVersion(meddraCode, meddraVersionId);
        if(CollectionUtils.isEmpty(llts)) return null;
        return llts.get(0);
    }
}
