package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;

import java.util.ArrayList;
import java.util.List;

public class AdverseEventReportSerializer {
	
	   private AdverseEventReportDao adverseEventReportDao;
	   private AdverseEventReport adverseEventReportDataObject;
	   
	   
	   //TO-DO set in spring config 
	   private String mappingFile = "xml-mapping/ae-report-xml-mapping.xml";
	   
	   
	   /**
	    * 
	    * @param adverseEventReportDataObject
	    * @return
	    * @throws Exception
	    */
	   public String serialize (AdverseEventReport adverseEventReportDataObject) throws Exception{	
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
				AdverseEventReport aer = this.getAdverseEventReport(adverseEventReportDataObject);
				xml = marshaller.toXML(aer,getMappingFile());
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	   private AdverseEventReport getAdverseEventReport (AdverseEventReport hibernateAdverseEventReport ) throws Exception{	
		   
		    AdverseEventReport aer = new AdverseEventReport();
	    	aer.setDetectionDate(hibernateAdverseEventReport.getDetectionDate());
	    	aer.setCreatedAt(hibernateAdverseEventReport.getCreatedAt());
	    	aer.setStatus(hibernateAdverseEventReport.getStatus());
	    	
	    	
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
	    	
	    	return aer;
	   }
	   
	   /**
	    * 
	    * @param rptr
	    * @return
	    * @throws Exception
	    */
	   private Reporter getReporter(Reporter rptr) throws Exception{	
	    	Reporter reporter = new Reporter();
	    	reporter.setFirstName(rptr.getFirstName());
	    	reporter.setLastName(rptr.getLastName());
	    	reporter.setContactMechanisms(rptr.getContactMechanisms());

	    	
	    	return reporter;
	    }
	    
	   /**
	    * 
	    * @param psn
	    * @return
	    * @throws Exception
	    */
	    private Physician getPhysician(Physician psn) throws Exception{	
	    	Physician physician = new Physician();
	    	physician.setFirstName(psn.getFirstName());
	    	physician.setLastName(psn.getLastName());
	    	physician.setContactMechanisms(psn.getContactMechanisms());

	    	
	    	return physician;
	    }   

	    /**
	     * 
	     * @param ph
	     * @return
	     * @throws Exception
	     */
	    private ParticipantHistory getParticipantHistory(ParticipantHistory ph) throws Exception{	
	    	ParticipantHistory participantHistory = new ParticipantHistory();
	    	participantHistory.setHeight(ph.getHeight());
	    	participantHistory.setHeightUnitOfMeasure(ph.getHeightUnitOfMeasure());
	    	participantHistory.setWeight(ph.getWeight());
	    	participantHistory.setWeightUnitOfMeasure(ph.getWeightUnitOfMeasure());
	    	participantHistory.setBaselinePerformanceStatus(ph.getBaselinePerformanceStatus());
	    	
	    	return participantHistory;
	    }  
	    
	    /**
	     * 
	     * @param aerd
	     * @return
	     * @throws Exception
	     */
	    private AdverseEventResponseDescription getAdverseEventResponseDescription(AdverseEventResponseDescription aerd) throws Exception{	
	    	AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
	    	
	    	adverseEventResponseDescription.setEventDescription(aerd.getEventDescription());
	    	adverseEventResponseDescription.setDateRemovedFromProtocol(aerd.getDateRemovedFromProtocol());
	    	adverseEventResponseDescription.setPresentStatus(aerd.getPresentStatus());
	    	adverseEventResponseDescription.setRecoveryDate(aerd.getRecoveryDate());
	    	adverseEventResponseDescription.setRetreated(aerd.getRetreated());
	    	
	    	
	    	return adverseEventResponseDescription;
	    	
	    }
	    
	    /**
	     * 
	     * @param dh
	     * @return
	     * @throws Exception
	     */
	    private DiseaseHistory getDiseaseHistory(DiseaseHistory dh) throws Exception{	
	    	DiseaseHistory diseaseHistory = new DiseaseHistory();
	    	diseaseHistory.setOtherPrimaryDiseaseCode(dh.getOtherPrimaryDiseaseCode());
	    	diseaseHistory.setOtherPrimaryDiseaseSiteCode(dh.getOtherPrimaryDiseaseSiteCode());
	    	diseaseHistory.setDateOfInitialPathologicDiagnosis(dh.getDateOfInitialPathologicDiagnosis());
	    	diseaseHistory.setAnatomicSite(dh.getAnatomicSite());
	    	List<MetastaticDiseaseSite> mdsList = dh.getMetastaticDiseaseSite();
	    	
	    	for (MetastaticDiseaseSite site: mdsList) {
	    		diseaseHistory.addMetastaticDiseaseSite(site);
	    	}
	    	return diseaseHistory;
	    }
	    
	    /**
	     * 
	     * @param spa
	     * @return
	     * @throws Exception
	     */
	    private StudyParticipantAssignment getStudyParticipantAssignment(StudyParticipantAssignment spa) throws Exception{	
	    	StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();
	    	studyParticipantAssignment.setParticipant(getParticipant(spa.getParticipant()));
	    	studyParticipantAssignment.setDateOfEnrollment(spa.getDateOfEnrollment());
	    	
	    	studyParticipantAssignment.setStudySite(getStudySite(spa.getStudySite()));
	    	
	    	return studyParticipantAssignment;
	    }
	    
	    /**
	     * 
	     * @param p
	     * @return
	     * @throws Exception
	     */
	    private Participant getParticipant(Participant p) throws Exception{	
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
	    
	    /**
	     * 
	     * @param ss
	     * @return
	     * @throws Exception
	     */
	    private StudySite getStudySite(StudySite ss) throws Exception{	
	    	StudySite studySite = new StudySite();
	    	studySite.setIrbApprovalDate(ss.getIrbApprovalDate());
	    	studySite.setRoleCode(ss.getRoleCode());
	    	studySite.setStatusCode(ss.getStatusCode());
	    	studySite.setStartDate(ss.getStartDate());
	    	studySite.setEndDate(ss.getEndDate());
	    	
	    	studySite.setStudy(ss.getStudy());
	    	studySite.setSite(ss.getSite());
	    	
	    	return studySite;    	
	    }
	    
	    /**
	     * 
	     * @param ae
	     * @return
	     * @throws Exception
	     */
	    private AdverseEvent getAdverseEvent(AdverseEvent ae ) throws Exception{	
	    	AdverseEvent adverseEvent = new AdverseEvent();
	    	adverseEvent.setDetailsForOther(ae.getDetailsForOther());
	    	adverseEvent.setExpected(ae.getExpected());
	    	adverseEvent.setComments(ae.getComments());
	    	
	    	adverseEvent.setConcomitantMedicationAttributions(ae.getConcomitantMedicationAttributions());
	    	
	    	List<OtherCauseAttribution> otList = new ArrayList<OtherCauseAttribution>();
	    	
	    	for (OtherCauseAttribution ot : ae.getOtherCauseAttributions()) {
	    		otList.add(getOtherCauseAttribution(ot));
	    	}
	    	
	    	adverseEvent.setOtherCauseAttributions(otList);
	    	adverseEvent.setCourseAgentAttributions(ae.getCourseAgentAttributions());
	    	
	    	
	    	
	    	adverseEvent.setCtcTerm(ae.getCtcTerm());
	    	adverseEvent.setHospitalization(ae.getHospitalization());
	    	adverseEvent.setGrade(ae.getGrade());
	    	
	    	
	    	return adverseEvent;
	    }
	    
	    /**
	     * 
	     * @param oca
	     * @return
	     * @throws Exception
	     */
	    private OtherCauseAttribution getOtherCauseAttribution(OtherCauseAttribution oca) throws Exception{	
	    	OtherCauseAttribution otherCauseAttribution = new OtherCauseAttribution();
	    	otherCauseAttribution.setAttribution(oca.getAttribution());
	    	otherCauseAttribution.setCause(getOtherCause(oca.getCause()));
	    	
	    	return otherCauseAttribution;
	    }
	    
	    /**
	     * 
	     * @param oc
	     * @return
	     * @throws Exception
	     */
	    private OtherCause getOtherCause(OtherCause oc) throws Exception{	
	    	OtherCause otherCause = new OtherCause();
	    	otherCause.setText(oc.getText());
	    	
	    	return otherCause;
	    	
	    }

		public AdverseEventReportDao getAdverseEventReportDao() {
			return adverseEventReportDao;
		}

		public void setAdverseEventReportDao(AdverseEventReportDao adverseEventReportDao) {
			this.adverseEventReportDao = adverseEventReportDao;
		}

		public String getMappingFile() {
			return mappingFile;
		}

//		public void setMappingFile(String mappingFile) {
	//		this.mappingFile = mappingFile;
		//}
	    
}
