package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventPreExistingCond;
import gov.nih.nci.cabig.caaers.domain.AdverseEventPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;

import java.util.ArrayList;
import java.util.List;

public class AdverseEventReportSerializer {

	   private ExpeditedAdverseEventReportDao adverseEventReportDao;
	   private ExpeditedAdverseEventReport adverseEventReportDataObject;

	   //TODO:
	   // Added TreatmentAssignment in TreatmentInformation
	   // Removed TreatmentInformation.treatmentAssignmentCode

	   // Added StartDate and endDate in AdverseEvent
	   // Removed detection date from expedited adverse event report
	   //Removed getTreatmentAssignmentCode, from TreatmentInformation

	   //TO-DO set in spring config
	   private String mappingFile = "xml-mapping/ae-report-xml-mapping.xml";

	   /**
	    *
	    * @param adverseEventReportDataObject
	    * @return
	    * @throws Exception
	    */
	   public String serialize (ExpeditedAdverseEventReport adverseEventReportDataObject) throws Exception{
		   this.adverseEventReportDataObject = adverseEventReportDataObject;
		   return serialize();
	   }

	   /**
	    *
	    * @param adverseEventReportId
	    * @return
	    * @throws Exception
	    */
	   public String serialize (int adverseEventReportId) throws Exception{
		   adverseEventReportDataObject = getAdverseEventReportDao().getById(adverseEventReportId);
		   return serialize();
	   }

	   /**
	    *
	    * @return
	    * @throws Exception
	    */
	   private String serialize() throws Exception{


		   String xml = "";

			XmlMarshaller marshaller = new XmlMarshaller();

			try {
				ExpeditedAdverseEventReport aer = this.getAdverseEventReport(adverseEventReportDataObject);
				xml = marshaller.toXML(aer,getMappingFile());
			} catch (Exception e) {
				// TODO Auto-generated catch block
                // TODO: this is a pointless rethrow.  Why not just throw e?  Or don't catch it.
                throw new Exception (e.getMessage(),e);
			}

			return xml;
	   }

	   /**
	    *
	    * @param hibernateAdverseEventReport
	    * @return
	    * @throws Exception
	    */
	   private ExpeditedAdverseEventReport getAdverseEventReport (ExpeditedAdverseEventReport hibernateAdverseEventReport ) throws Exception{

		    ExpeditedAdverseEventReport aer = new ExpeditedAdverseEventReport();
	    	aer.setCreatedAt(hibernateAdverseEventReport.getCreatedAt());
	    	//aer.setStatus(hibernateAdverseEventReport.getStatus());
	    	

	    	//build Reporter
	    	aer.setReporter(getReporter(hibernateAdverseEventReport.getReporter()));

	    	//build Physician
	    	aer.setPhysician(getPhysician(hibernateAdverseEventReport.getPhysician()));

	    	//build AdverseEventResponseDescription
	    	aer.setResponseDescription(getAdverseEventResponseDescription(hibernateAdverseEventReport.getResponseDescription()));

	    	//build DiseaseHistory
	    	aer.setDiseaseHistory(getDiseaseHistory(hibernateAdverseEventReport.getDiseaseHistory()));

	    	//build Participant history
	    	aer.setParticipantHistory(getParticipantHistory(hibernateAdverseEventReport.getParticipantHistory()));

	    	//build StudyParticipantAssignment
	    	aer.setAssignment(getStudyParticipantAssignment(hibernateAdverseEventReport.getAssignment()));

	    	//build treatment info
	    	aer.setTreatmentInformation(getTreatmentInformation(hibernateAdverseEventReport.getTreatmentInformation()));

	    	
	    	//aer.getRadiationIntervention().setAdministration(hibernateAdverseEventReport.getRadiationIntervention().getAdministration());
	    
	    	//build MedicalDevices
	    	List<MedicalDevice> medicalDeviceList = hibernateAdverseEventReport.getMedicalDevices();

	    	for (MedicalDevice medicalDevice: medicalDeviceList) {
	    		aer.addMedicalDevice(medicalDevice);
	    	}
	    	
	    	//	build RadiationInterventions
	    	List<RadiationIntervention> radiationInterventionList = hibernateAdverseEventReport.getRadiationInterventions();

	    	for (RadiationIntervention radiationIntervention: radiationInterventionList) {
	    		aer.addRadiationIntervention(radiationIntervention);
	    	}
	    	
	    	//	build SurgeryInterventions
	    	List<SurgeryIntervention> surgeryInterventionList = hibernateAdverseEventReport.getSurgeryInterventions();

	    	for (SurgeryIntervention surgeryIntervention: surgeryInterventionList) {
	    		aer.addSurgeryIntervention(surgeryIntervention);
	    	}

	    	aer.setAdditionalInformation(hibernateAdverseEventReport.getAdditionalInformation());

	    	//build medications
	    	List<ConcomitantMedication> conMedList = hibernateAdverseEventReport.getConcomitantMedications();

	    	for (ConcomitantMedication medication: conMedList) {
	    		aer.addConcomitantMedication(medication);
	    	}


	    	//build Labs
	    	List<Lab> labList = hibernateAdverseEventReport.getLabs();

	    	for (Lab lab: labList) {
	    		aer.addLab(lab);
	    	}

	    	// build AEs
	    	List<AdverseEvent> aeList = hibernateAdverseEventReport.getAdverseEvents();

	    	for (AdverseEvent ae: aeList) {
	    		aer.addAdverseEvent(getAdverseEvent(ae));
	    	}

	    	//build therapies
	    	List<AdverseEventPriorTherapy> thList = hibernateAdverseEventReport.getAdverseEventPriorTherapies();

	    	for (AdverseEventPriorTherapy therapy: thList) {
	    		aer.addAdverseEventPriorTherapies(therapy);
	    	}

	    	//Build pre existing conditions
	    	List<AdverseEventPreExistingCond> peList = hibernateAdverseEventReport.getAdverseEventPreExistingConds();

	    	for (AdverseEventPreExistingCond pe: peList) {
	    		aer.addAdverseEventPreExistingCond(pe);
	    	}

	    	//Build other causes
	    	List<OtherCause> ocList = hibernateAdverseEventReport.getOtherCauses();

	    	for (OtherCause oc: ocList) {
	    		aer.addOtherCause(oc);
	    	}



	    	return aer;
	   }
	   private Reporter getReporter(Reporter rptr) {
	    	Reporter reporter = new Reporter();
	    	reporter.setFirstName(rptr.getFirstName());
	    	reporter.setLastName(rptr.getLastName());
	    	reporter.setContactMechanisms(rptr.getContactMechanisms());


	    	return reporter;
	    }

	    private Physician getPhysician(Physician psn) {
	    	Physician physician = new Physician();
	    	physician.setFirstName(psn.getFirstName());
	    	physician.setLastName(psn.getLastName());
	    	physician.setContactMechanisms(psn.getContactMechanisms());


	    	return physician;
	    }

	    private ParticipantHistory getParticipantHistory(ParticipantHistory ph) {
	    	ParticipantHistory participantHistory = new ParticipantHistory();
	    	participantHistory.getHeight().setQuantity(ph.getHeight().getQuantity());
	    	participantHistory.getHeight().setUnit(ph.getHeight().getUnit());
	    	participantHistory.getWeight().setQuantity(ph.getWeight().getQuantity());
	    	participantHistory.getWeight().setUnit(ph.getWeight().getUnit());
	    	participantHistory.setBaselinePerformanceStatus(ph.getBaselinePerformanceStatus());

	    	return participantHistory;
	    }

	    private AdverseEventResponseDescription getAdverseEventResponseDescription(AdverseEventResponseDescription aerd) {
	    	AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();

	    	adverseEventResponseDescription.setEventDescription(aerd.getEventDescription());
	    	adverseEventResponseDescription.setDateRemovedFromProtocol(aerd.getDateRemovedFromProtocol());
	    	adverseEventResponseDescription.setPresentStatus(aerd.getPresentStatus());
	    	adverseEventResponseDescription.setRecoveryDate(aerd.getRecoveryDate());
	    	adverseEventResponseDescription.setRetreated(aerd.getRetreated());


	    	return adverseEventResponseDescription;

	    }

	    private DiseaseHistory getDiseaseHistory(DiseaseHistory dh) {
	    	DiseaseHistory diseaseHistory = new DiseaseHistory();
	    	diseaseHistory.setOtherPrimaryDisease(dh.getOtherPrimaryDisease());
	    	diseaseHistory.setOtherPrimaryDiseaseSite(dh.getOtherPrimaryDiseaseSite());
	    	diseaseHistory.setDiagnosisDate(dh.getDiagnosisDate());
	    	diseaseHistory.setCodedPrimaryDiseaseSite(dh.getCodedPrimaryDiseaseSite());
	    	diseaseHistory.setAbstractStudyDisease(dh.getCtepStudyDisease() == null ?
	    			dh.getMeddraStudyDisease() : dh.getCtepStudyDisease());
	    	List<MetastaticDiseaseSite> mdsList = dh.getMetastaticDiseaseSites();

	    	for (MetastaticDiseaseSite site: mdsList) {
	    		diseaseHistory.addMetastaticDiseaseSite(site);
	    	}
	    	return diseaseHistory;
	    }

	    private StudyParticipantAssignment getStudyParticipantAssignment(StudyParticipantAssignment spa) {
	    	StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();
	    	studyParticipantAssignment.setParticipant(getParticipant(spa.getParticipant()));
	    	studyParticipantAssignment.setDateOfEnrollment(spa.getDateOfEnrollment());

	    	studyParticipantAssignment.setStudySite(getStudySite(spa.getStudySite()));

	    	return studyParticipantAssignment;
	    }

	    private Participant getParticipant(Participant p) {
	    	Participant participant = new Participant();
	    	participant.setInstitutionalPatientNumber(p.getInstitutionalPatientNumber());
	    	participant.setInstitution(p.getInstitution());
	    	participant.setFirstName(p.getFirstName());
	    	participant.setMaidenName(p.getMaidenName());
	    	participant.setMiddleName(p.getMiddleName());
	    	participant.setLastName(p.getLastName());
	    	participant.setDateOfBirth(p.getDateOfBirth());
	    	participant.setGender(p.getGender());
	    	participant.setRace(p.getRace());
	    	participant.setEthnicity(p.getEthnicity());

	    	return participant;
	    }

	    private StudySite getStudySite(StudySite ss) {
	    	StudySite studySite = new StudySite();
	    	//studySite.setIrbApprovalDate(ss.getIrbApprovalDate());
	    	//studySite.setRoleCode(ss.getRoleCode());
	    	studySite.setStatusCode(ss.getStatusCode());
	    	studySite.setStartDate(ss.getStartDate());
	    	studySite.setEndDate(ss.getEndDate());

	    	studySite.setStudy(ss.getStudy());
	    	studySite.setOrganization(ss.getOrganization());
	    	studySite.setStudyInvestigators(ss.getStudyInvestigators());

	    	//System.out.println("STUDY INVES INTERNAL >>>>>>>>>>>>>>> " + ss.getStudyInvestigatorsInternal().size());
	    	return studySite;
	    }

	    private AdverseEvent getAdverseEvent(AdverseEvent ae ) {
	    	AdverseEvent adverseEvent = new AdverseEvent();
	    	adverseEvent.setDetailsForOther(ae.getDetailsForOther());
	    	adverseEvent.setExpected(ae.getExpected());
	    	adverseEvent.setComments(ae.getComments());
	    	adverseEvent.setStartDate(ae.getStartDate());
	    	adverseEvent.setEndDate(ae.getEndDate());
	    	adverseEvent.setConcomitantMedicationAttributions(ae.getConcomitantMedicationAttributions());

	    	List<OtherCauseAttribution> otList = new ArrayList<OtherCauseAttribution>();

	    	for (OtherCauseAttribution ot : ae.getOtherCauseAttributions()) {
	    		otList.add(getOtherCauseAttribution(ot));
	    	}

	    	adverseEvent.setOtherCauseAttributions(otList);
	    	adverseEvent.setCourseAgentAttributions(ae.getCourseAgentAttributions());



			if (ae.getAdverseEventTerm().getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
				adverseEvent.setAdverseEventMeddraLowLevelTerm(ae.getAdverseEventMeddraLowLevelTerm());
			} else {
				adverseEvent.getAdverseEventCtcTerm().setCtcTerm(ae.getAdverseEventCtcTerm().getCtcTerm());
			}



	    	adverseEvent.setHospitalization(ae.getHospitalization());
	    	adverseEvent.setGrade(ae.getGrade());
	    	adverseEvent.setAttributionSummary(ae.getAttributionSummary());
	    	adverseEvent.setExpected(ae.getExpected());


	    	return adverseEvent;
	    }


	    private OtherCauseAttribution getOtherCauseAttribution(OtherCauseAttribution oca) {
	    	OtherCauseAttribution otherCauseAttribution = new OtherCauseAttribution();
	    	otherCauseAttribution.setAttribution(oca.getAttribution());
	    	otherCauseAttribution.setCause(getOtherCause(oca.getCause()));

	    	return otherCauseAttribution;
	    }


	    private OtherCause getOtherCause(OtherCause oc) {
	    	OtherCause otherCause = new OtherCause();
	    	otherCause.setText(oc.getText());

	    	return otherCause;

	    }

	    private TreatmentInformation getTreatmentInformation(TreatmentInformation trtInf) {
	    	TreatmentInformation treatmentInformation = new TreatmentInformation();
	    	treatmentInformation.setFirstCourseDate(trtInf.getFirstCourseDate());
	    	treatmentInformation.setAdverseEventCourse(trtInf.getAdverseEventCourse());
	    	treatmentInformation.setTotalCourses(trtInf.getTotalCourses());

	    	TreatmentAssignment ta = trtInf.getTreatmentAssignment();
	    	
	    	if (ta != null ) {
		    	TreatmentAssignment taNew = new TreatmentAssignment();
		    	taNew.setCode(ta.getCode());
		    	taNew.setDoseLevelOrder(ta.getDoseLevelOrder());
		    	taNew.setDescription(ta.getDescription());
		    	taNew.setComments(ta.getComments());
		    	taNew.setStudy(ta.getStudy());
		    	
		    	treatmentInformation.setTreatmentAssignment(taNew);
	    	}

	    	List<CourseAgent> caList = trtInf.getCourseAgents();

	    	for (CourseAgent ca: caList) {
	    		CourseAgent ca1 = new CourseAgent();
	    		ca1.setId(ca.getId());
	    		ca1.setLastAdministeredDate(ca.getLastAdministeredDate());
	    		ca1.setAdministrationDelayAmount(ca.getAdministrationDelayAmount());
	    		ca1.setAdministrationDelayUnits(ca.getAdministrationDelayUnits());
	    		ca1.setDose(ca.getDose());
	    		ca1.setModifiedDose(ca.getModifiedDose());
	    		ca1.setStudyAgent(ca.getStudyAgent());
	    		ca1.setTotalDoseAdministeredThisCourse(ca.getTotalDoseAdministeredThisCourse());
	
	    		treatmentInformation.addCourseAgent(ca1);
	    	}


	    	return treatmentInformation;
	    }

		public ExpeditedAdverseEventReportDao getAdverseEventReportDao() {
			return adverseEventReportDao;
		}

		public void setAdverseEventReportDao(ExpeditedAdverseEventReportDao adverseEventReportDao) {
			this.adverseEventReportDao = adverseEventReportDao;
		}

		public String getMappingFile() {
			return mappingFile;
		}

//		public void setMappingFile(String mappingFile) {
	//		this.mappingFile = mappingFile;
		//}

}
