package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.EventStatus;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdverseEventReportSerializer {

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
		   return this.serialize(adverseEventReportDataObject, null);
	   }

	   public synchronized String serialize (ExpeditedAdverseEventReport adverseEventReportDataObject,Report report) throws Exception{
		   int reportId = report == null ? 0 : report.getId();
		   List<String> notApplicableFieldPaths = new ArrayList<String>();
		   
		   if (report != null ) {
			   ReportDefinition reportDefinition = report.getReportDefinition();
			   List<ReportMandatoryFieldDefinition> mandatoryFields = reportDefinition.getMandatoryFields();
			   //
			   
			   for (ReportMandatoryFieldDefinition mandatoryField : mandatoryFields) {
		                if (mandatoryField.getMandatory().equals(Mandatory.NA)) {
		                	notApplicableFieldPaths.add(mandatoryField.getFieldPath());
		                }
		        }
		   }
		   	   
		   String xml = "";
			XmlMarshaller marshaller = new XmlMarshaller();
			ExpeditedAdverseEventReport aer = this.getAdverseEventReport(adverseEventReportDataObject,reportId,notApplicableFieldPaths);
			xml = marshaller.toXML(aer,getMappingFile());
		
			return xml;
	   }
	   
	  

	   /**
	    *
	    * @param hibernateAdverseEventReport
	    * @return
	    * @throws Exception
	    */
	   private ExpeditedAdverseEventReport getAdverseEventReport (ExpeditedAdverseEventReport hibernateAdverseEventReport , int reportId,List<String> notApplicableFieldPaths) throws Exception{

		    ExpeditedAdverseEventReport aer = new ExpeditedAdverseEventReport();
		    AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		    aer.setReportingPeriod(reportingPeriod);
		    
	    	aer.setCreatedAt(hibernateAdverseEventReport.getCreatedAt());
	    	aer.setId(hibernateAdverseEventReport.getId());
	    	
	    	//aer.setStatus(hibernateAdverseEventReport.getStatus());
	    	

	    	//build Reporter
	    	aer.setReporter(getReporter(hibernateAdverseEventReport.getReporter()));

	    	//build Physician
	    	aer.setPhysician(getPhysician(hibernateAdverseEventReport.getPhysician()));

	    	//build AdverseEventResponseDescription
	    	aer.setResponseDescription(getAdverseEventResponseDescription(hibernateAdverseEventReport.getResponseDescription(),notApplicableFieldPaths));

	    	//build DiseaseHistory
	    	aer.setDiseaseHistory(getDiseaseHistory(hibernateAdverseEventReport.getDiseaseHistory(),notApplicableFieldPaths));

	    	//build Participant history
	    	aer.setParticipantHistory(getParticipantHistory(hibernateAdverseEventReport.getParticipantHistory()));

	    	//build StudyParticipantAssignment
	    	aer.setAssignment(getStudyParticipantAssignment(hibernateAdverseEventReport.getAssignment()));

	    	//build treatment info
	    	aer.setTreatmentInformation(getTreatmentInformation(hibernateAdverseEventReport.getTreatmentInformation(),notApplicableFieldPaths));

	    	
	    
	    	//build MedicalDevices
	    	List<MedicalDevice> medicalDeviceList = hibernateAdverseEventReport.getMedicalDevices();

	    	for (MedicalDevice medicalDevice: medicalDeviceList) {
	    		aer.addMedicalDevice(getMedicalDevice(medicalDevice));
	    	}
	    	
	    	//	build RadiationInterventions
	    	List<RadiationIntervention> radiationInterventionList = hibernateAdverseEventReport.getRadiationInterventions();

	    	for (RadiationIntervention radiationIntervention: radiationInterventionList) {
	    		
	    		aer.addRadiationIntervention(getRadiationIntervention(radiationIntervention));
	    	}
	   	
	    	//	build SurgeryInterventions
	    	List<SurgeryIntervention> surgeryInterventionList = hibernateAdverseEventReport.getSurgeryInterventions();

	    	for (SurgeryIntervention surgeryIntervention: surgeryInterventionList) {
	    		aer.addSurgeryIntervention(getSurgeryIntervention(surgeryIntervention));
	    	}

	    	aer.setAdditionalInformation(getAdditionalInformation(hibernateAdverseEventReport.getAdditionalInformation()));

	    	//build medications
	    	List<ConcomitantMedication> conMedList = hibernateAdverseEventReport.getConcomitantMedications();

	    	for (ConcomitantMedication medication: conMedList) {
	    		aer.addConcomitantMedication(getConcomitantMedication(medication));
	    	}


	    	//build Labs
	    	List<Lab> labList = hibernateAdverseEventReport.getLabs();

	    	for (Lab lab: labList) {
	    		aer.addLab(getLab(lab));
	    	}

	    	// build AEs
	    	List<AdverseEvent> aeList = hibernateAdverseEventReport.getAdverseEvents();

	    	for (int i=0; i<aeList.size(); i++) {
	    		AdverseEvent ae = (AdverseEvent)aeList.get(i);
	    		aer.addAdverseEvent(getAdverseEvent(ae,i));
	    	}

	    	//build therapies
	    	List<SAEReportPriorTherapy> thList = hibernateAdverseEventReport.getSaeReportPriorTherapies();

	    	for (SAEReportPriorTherapy therapy: thList) {
	    		aer.addSaeReportPriorTherapies(getSAEReportPriorTherapy(therapy));
	    	}

	    	//Build pre existing conditions
	    	List<SAEReportPreExistingCondition> peList = hibernateAdverseEventReport.getSaeReportPreExistingConditions();

	    	for (SAEReportPreExistingCondition pe: peList) {
	    		aer.addSaeReportPreExistingCondition(getSAEReportPreExistingCondition(pe));
	    	}

	    	//Build other causes
	    	List<OtherCause> ocList = hibernateAdverseEventReport.getOtherCauses();

	    	for (OtherCause oc: ocList) {
	    		aer.addOtherCause(getOtherCause(oc));
	    	}
	    	
	    	//Build reports
	    	List<Report> reports = hibernateAdverseEventReport.getReports();
	    	for (Report report: reports) {
	    		if (reportId > 0 ) {
	    			// generate report data only for selected report (when submitting to AdEERS)
	    			if (reportId == report.getId()) {
	    				Report latestReport = getReport(report);
	    				ReportVersion reportVersion = report.getLastVersion();
	    				ReportVersion latestVersion = new ReportVersion();
	    				latestVersion.setReportVersionId(reportVersion.getReportVersionId());
	    				latestReport.addReportVersion(latestVersion);
	    				aer.addReport(latestReport);
	    			}
	    		} 
	    		//else {
	    			//aer.addReport(getReport(report));
	    		//}
	    	}	    	
	    	
	    	/*
			BJ:FIXME
	    	List<Outcome> outcomes = hibernateAdverseEventReport.getOutcomes();
	    	
	    	for (Outcome oc: outcomes) {
	    		aer.addOutcomes(getOutcome(oc));
	    	}*/


	    	return aer;
	   }
	   
	   private Report getReport(Report report) throws Exception {
		   
		   Report r = new Report();
		   r.setId(report.getId());
		   r.setAssignedIdentifer(report.getAssignedIdentifer());
		   r.setSubmissionMessage(report.getSubmissionMessage());
		   ReportVersion rv = new ReportVersion();
		   
		   //rv.setAssignedIdentifer(report.getLastVersion().getAssignedIdentifer());
		   rv.setReportVersionId(report.getLastVersion().getReportVersionId());
		   
		   r.addReportVersion(rv);
		   r.setReportDefinition(getReportDefinition(report.getReportDefinition()));
		 //  r.setAmmendmentNumber(report.getAmmendmentNumber());
		   
		   // if report is successfully submitted before , get the ticket id.
//		   if (report.getAssignedIdentifer() != null) {
	//		   return r;
		//   }
/*		   
		   // check if any of the previous version is successfully submitted . if  so , ammend it for same ticket number.
		   List<ReportVersion> rvs = report.getReportVersions();
		   for (ReportVersion rv: rvs) {
			   if (rv.getAssignedIdentifer() !=null ) {
				   Report r1 = new Report();
				   r1.setId(report.getId());
				   r1.setAssignedIdentifer(rv.getAssignedIdentifer());
				   r1.setSubmissionMessage(rv.getSubmissionMessage());
				   return r1;
			   }
	    	}		   
		   
*/	   		   
		   return r;
	   }
	   private ReportDefinition getReportDefinition(ReportDefinition rd) throws Exception {
		   
		   ReportDefinition reportDefinition = new ReportDefinition();
		   reportDefinition.setId(rd.getId());
		   reportDefinition.setDuration(rd.getDuration());
		   reportDefinition.setTimeScaleUnitType(rd.getTimeScaleUnitType());   
		   return reportDefinition;
	   }	   
	   private AdditionalInformation getAdditionalInformation (AdditionalInformation additionalInformation) throws Exception {
		   
		   AdditionalInformation a = new AdditionalInformation();
		   a.setId(additionalInformation.getId());
		   a.setOtherInformation(additionalInformation.getOtherInformation());
		   a.setAutopsyReport(additionalInformation.getAutopsyReport());
		   a.setConsults(additionalInformation.getConsults());
		   a.setDischargeSummary(additionalInformation.getDischargeSummary());
		   a.setFlowCharts(additionalInformation.getFlowCharts());
		   a.setLabReports(additionalInformation.getLabReports());
		   a.setObaForm(additionalInformation.getObaForm());
		   a.setOther(additionalInformation.getOther());
		   a.setPathologyReport(additionalInformation.getPathologyReport());
		   a.setProgressNotes(additionalInformation.getProgressNotes());
		   a.setRadiologyReports(additionalInformation.getRadiologyReports());
		   a.setReferralLetters(additionalInformation.getReferralLetters());
		   a.setIrbReport(additionalInformation.getIrbReport());
		   
		   
		   return a;
	   }
	   
	   private Outcome getOutcome(Outcome outcome) throws Exception {
		   Outcome o = new Outcome();
		   
		   o.setId(outcome.getId());
		   o.setOther(outcome.getOther());
		   o.setOutcomeType(outcome.getOutcomeType());
		   o.setDate(outcome.getDate());
		   
		   return o;
	   }
	   
	   private SAEReportPreExistingCondition getSAEReportPreExistingCondition(SAEReportPreExistingCondition saeReportPreExistingCondition) throws Exception {
		   
		   SAEReportPreExistingCondition s = new SAEReportPreExistingCondition();
		   s.setId(saeReportPreExistingCondition.getId());
		   s.setOther(saeReportPreExistingCondition.getOther());
		   s.setPreExistingCondition(saeReportPreExistingCondition.getPreExistingCondition());
		   
		   return s;
	   }
	   
	   private SAEReportPriorTherapy getSAEReportPriorTherapy(SAEReportPriorTherapy saeReportPriorTherapy) throws Exception {
		   
		   SAEReportPriorTherapy s = new SAEReportPriorTherapy();
		   s.setId(saeReportPriorTherapy.getId());
		   s.setPriorTherapy(saeReportPriorTherapy.getPriorTherapy());
		   s.setStartDate(saeReportPriorTherapy.getStartDate());
		   s.setEndDate(saeReportPriorTherapy.getEndDate());
		   s.setOther(saeReportPriorTherapy.getOther());
		   
		   List<PriorTherapyAgent> agents = saeReportPriorTherapy.getPriorTherapyAgents();
		   
		   for (PriorTherapyAgent agent : agents) {
			   PriorTherapyAgent pta = new PriorTherapyAgent();
			   pta.setId(agent.getId());
			   pta.setChemoAgent(agent.getChemoAgent());
			   s.addPriorTherapyAgent(pta);
		   }
		   
		   return s;
		   
	   }
	   private Lab getLab(Lab lab) throws Exception {
		   Lab l = new Lab();
		   l.setId(lab.getId());
		   l.setLabTerm(lab.getLabTerm());
		   l.setOther(lab.getOther());
		   l.setUnits(lab.getUnits());
		   l.setBaseline(lab.getBaseline());
		   l.setNadir(lab.getNadir());
		   l.setRecovery(lab.getRecovery());
		   l.setLabDate(lab.getLabDate());
		   l.setSite(lab.getSite());
		   l.setInfectiousAgent(lab.getInfectiousAgent());
		   
		   return l;
	   }
	   private ConcomitantMedication getConcomitantMedication (ConcomitantMedication concomitantMedication) throws Exception {
		   ConcomitantMedication c = new ConcomitantMedication();
		   c.setAgentName(concomitantMedication.getAgentName());
		   c.setStartDate(concomitantMedication.getStartDate());
		   c.setEndDate(concomitantMedication.getEndDate());
		   c.setStillTakingMedications(concomitantMedication.getStillTakingMedications());
		   return c;
		   
	   }
	   
	   private SurgeryIntervention getSurgeryIntervention(SurgeryIntervention surgeryIntervention) throws Exception{
		   SurgeryIntervention s = new SurgeryIntervention();
		   
		   s.setId(surgeryIntervention.getId());
		   s.setTreatmentArm(surgeryIntervention.getTreatmentArm());
		   s.setDescription(surgeryIntervention.getDescription());
		   s.setInterventionDate(surgeryIntervention.getInterventionDate());
		   s.setInterventionSite(surgeryIntervention.getInterventionSite());
		   
		   return s;
		   
	   }
	   
	   private MedicalDevice getMedicalDevice(MedicalDevice medicalDevice) throws Exception {
		   MedicalDevice m = new MedicalDevice();
		   m.setId(medicalDevice.getId());
		   m.setBrandName(medicalDevice.getBrandName());
		   m.setCommonName(medicalDevice.getCommonName());
		   m.setDeviceType(medicalDevice.getDeviceType());
		   m.setManufacturerName(medicalDevice.getManufacturerName());
		   m.setManufacturerCity(medicalDevice.getManufacturerCity());
		   m.setManufacturerState(medicalDevice.getManufacturerState());
		   m.setModelNumber(medicalDevice.getModelNumber());
		   m.setLotNumber(medicalDevice.getLotNumber());
		   m.setCatalogNumber(medicalDevice.getCatalogNumber());
		   m.setExpirationDate(medicalDevice.getExpirationDate());
		   m.setSerialNumber(medicalDevice.getSerialNumber());
		   m.setOtherNumber(medicalDevice.getOtherNumber());
		   m.setOtherDeviceOperator(medicalDevice.getOtherDeviceOperator());
		   m.setImplantedDate(medicalDevice.getImplantedDate());
		   m.setExplantedDate(medicalDevice.getExplantedDate());
		   m.setReprocessorName(medicalDevice.getReprocessorName());
		   m.setReprocessorAddress(medicalDevice.getReprocessorAddress());
		   m.setReturnedDate(medicalDevice.getReturnedDate());
		   m.setDeviceOperator(medicalDevice.getDeviceOperator());
		   m.setDeviceReprocessed(medicalDevice.getDeviceReprocessed());
		   m.setEvaluationAvailability(medicalDevice.getEvaluationAvailability());
		   
		   return m;
	   }
	   
	   private RadiationIntervention getRadiationIntervention(RadiationIntervention ri) throws Exception {
		   RadiationIntervention radiationIntervention = new RadiationIntervention();
		   try {
			   radiationIntervention.setId(ri.getId());
			   radiationIntervention.setDosage(ri.getDosage());
			   radiationIntervention.setDosageUnit(ri.getDosageUnit());
			   radiationIntervention.setLastTreatmentDate(ri.getLastTreatmentDate());
			   radiationIntervention.setFractionNumber(ri.getFractionNumber());
			   radiationIntervention.setDaysElapsed(ri.getDaysElapsed());
			   radiationIntervention.setAdjustment(ri.getAdjustment());
			   radiationIntervention.setAdministration(ri.getAdministration());
			   
			   
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getRadiationIntervention() "+e.getMessage() , e);
	    	}
		   
		   return radiationIntervention;
	   }
	   private Reporter getReporter(Reporter rptr) throws Exception {
	    	Reporter reporter = new Reporter();
	    	try {
		    	reporter.setFirstName(rptr.getFirstName());
		    	reporter.setLastName(rptr.getLastName());
		    	reporter.setTitle(rptr.getTitle());
		    	reporter.setAddress(rptr.getAddress());
		    	reporter.setContactMechanisms(rptr.getContactMechanisms());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getReporter() "+e.getMessage() , e);
	    	}


	    	return reporter;
	    }

	    private Physician getPhysician(Physician psn) throws Exception {
	    	Physician physician = new Physician();
	    	try {
	    		physician.setFirstName(psn.getFirstName());
	    		physician.setLastName(psn.getLastName());
	    		physician.setTitle(psn.getTitle());
	    		physician.setAddress(psn.getAddress());
	    		physician.setContactMechanisms(psn.getContactMechanisms());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getPhysician() "+e.getMessage() , e);
	    	}

	    	return physician;
	    }

	    private ParticipantHistory getParticipantHistory(ParticipantHistory ph) throws Exception {
	    	ParticipantHistory participantHistory = new ParticipantHistory();
	    	try {
		    	participantHistory.getHeight().setQuantity(ph.getHeight().getQuantity());
		    	participantHistory.getHeight().setUnit(ph.getHeight().getUnit());
		    	participantHistory.getWeight().setQuantity(ph.getWeight().getQuantity());
		    	participantHistory.getWeight().setUnit(ph.getWeight().getUnit());
		    	participantHistory.setBaselinePerformanceStatus(ph.getBaselinePerformanceStatus());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getParticipantHistory() "+e.getMessage() , e);
	    	}
	    	return participantHistory;
	    }

	    private AdverseEventResponseDescription getAdverseEventResponseDescription(AdverseEventResponseDescription aerd,List<String> notApplicableFieldPaths) throws Exception {
	    	AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
	    	try {
		    	adverseEventResponseDescription.setEventDescription(aerd.getEventDescription());
		    	if (!notApplicableFieldPaths.contains("responseDescription.dateRemovedFromProtocol")) {
		    		adverseEventResponseDescription.setDateRemovedFromProtocol(aerd.getDateRemovedFromProtocol());
		    	} else {
		    		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
					try {
						Date someNotPossibleDate = dfm.parse("1678-01-01 00:00:00");
						adverseEventResponseDescription.setDateRemovedFromProtocol(someNotPossibleDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    	}
		    	adverseEventResponseDescription.setPresentStatus(aerd.getPresentStatus());
		    	adverseEventResponseDescription.setRecoveryDate(aerd.getRecoveryDate());
		    	adverseEventResponseDescription.setRetreated(aerd.getRetreated());
		    	
		    	adverseEventResponseDescription.setBlindBroken(aerd.getBlindBroken());
		    	adverseEventResponseDescription.setStudyDrugInterrupted(aerd.getStudyDrugInterrupted());
		    	adverseEventResponseDescription.setReducedDose(aerd.getReducedDose());
		    	adverseEventResponseDescription.setReducedDate(aerd.getReducedDate());
		    	adverseEventResponseDescription.setDaysNotGiven(aerd.getDaysNotGiven());
		    	if (!notApplicableFieldPaths.contains("responseDescription.eventAbate")) {
		    		adverseEventResponseDescription.setEventAbate(aerd.getEventAbate());
		    	} else {
		    		adverseEventResponseDescription.setEventAbate(EventStatus.NA);
		    	}
		    	if (!notApplicableFieldPaths.contains("responseDescription.eventReappear")) {
		    		adverseEventResponseDescription.setEventReappear(aerd.getEventReappear());
		    	} else {
		    		adverseEventResponseDescription.setEventReappear(EventStatus.NA);
		    	}
		    	
		    	adverseEventResponseDescription.setAutopsyPerformed(aerd.getAutopsyPerformed());
		    	adverseEventResponseDescription.setCauseOfDeath(aerd.getCauseOfDeath());
		    	
		    	adverseEventResponseDescription.setPrimaryTreatment(aerd.getPrimaryTreatment());
		    	adverseEventResponseDescription.setPrimaryTreatmentApproximateTime(aerd.getPrimaryTreatmentApproximateTime());
		    	
		    	
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getAdverseEventResponseDescription() "+e.getMessage() , e);
	    	}

	    	return adverseEventResponseDescription;

	    }

	    private DiseaseHistory getDiseaseHistory(DiseaseHistory dh,List<String> notApplicableFieldPaths) throws Exception {
	    	DiseaseHistory diseaseHistory = new DiseaseHistory();
	    	try {
		    	diseaseHistory.setOtherPrimaryDisease(dh.getOtherPrimaryDisease());
		    	diseaseHistory.setOtherPrimaryDiseaseSite(dh.getOtherPrimaryDiseaseSite());
		    	if (!notApplicableFieldPaths.contains("diseaseHistory.diagnosisDate")) {
		    		diseaseHistory.setDiagnosisDate(dh.getDiagnosisDate());
		    	}
		    	if (dh.getCodedPrimaryDiseaseSite() != null && !notApplicableFieldPaths.contains("diseaseHistory.codedPrimaryDiseaseSite")) {
		    		diseaseHistory.setCodedPrimaryDiseaseSite(getAnatomicSite(dh.getCodedPrimaryDiseaseSite()));
		    	}
		    	diseaseHistory.setAbstractStudyDisease(dh.getAbstractStudyDisease());
		    	List<MetastaticDiseaseSite> mdsList = dh.getMetastaticDiseaseSites();
	
		    	for (MetastaticDiseaseSite site: mdsList) {
		    		diseaseHistory.addMetastaticDiseaseSite(site);
		    	}
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getDiseaseHistory() "+e.getMessage() , e);
	    	}
	    	return diseaseHistory;
	    }
	    private AnatomicSite getAnatomicSite(AnatomicSite ash) {
	    	AnatomicSite site = new AnatomicSite();
	    	site.setId(ash.getId());
	    	site.setName(ash.getName());
	    	site.setCategory(ash.getCategory());
	    	return site;	    	
	    }
	    
	    private StudyParticipantAssignment getStudyParticipantAssignment(StudyParticipantAssignment spa) throws Exception {
	    	StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();
	    	try {
		    	studyParticipantAssignment.setParticipant(getParticipant(spa.getParticipant()));
		    	studyParticipantAssignment.setDateOfEnrollment(spa.getDateOfEnrollment());
	
		    	studyParticipantAssignment.setStudySite(getStudySite(spa.getStudySite()));
		    	studyParticipantAssignment.setStudySubjectIdentifier(spa.getStudySubjectIdentifier());

	    	} catch (Exception e) {
	    		throw new Exception ("Error building getStudyParticipantAssignment() "+e.getMessage() , e);
	    	}
	    	return studyParticipantAssignment;
	    }

	    private Participant getParticipant(Participant p) throws Exception {
	    	Participant participant = new Participant();
	    	try {
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
		    	//participant.setIdentifiers(p.getIdentifiers())
		    	for (Identifier id:p.getIdentifiers()) {
		    		participant.addIdentifier(getIdentifier(id));
		    	}
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getParticipant() "+e.getMessage() , e);
	    	}
	    	return participant;
	    }
	    private Identifier getIdentifier(Identifier id) {
	    	Identifier identifier = new Identifier();
	    	identifier.setPrimaryIndicator(id.getPrimaryIndicator());
	    	identifier.setType(id.getType());
	    	identifier.setValue(id.getValue());
	    	return identifier;
	    }
	    private StudySite getStudySite(StudySite ss) throws Exception {
	    	StudySite studySite = new StudySite();
	    	//studySite.setIrbApprovalDate(ss.getIrbApprovalDate());
	    	//studySite.setRoleCode(ss.getRoleCode());
	    	try { 
		    	studySite.setStartDate(ss.getStartDate());
		    	studySite.setEndDate(ss.getEndDate());
		    	//buld identifiers , to resolve sesion error 
		    	Study hibernateStudy = ss.getStudy();
		    	
		    	Study s = new Study();
		    	s.setId(hibernateStudy.getId());
		    	s.setBlindedIndicator(hibernateStudy.getBlindedIndicator());
		    	s.setMultiInstitutionIndicator(hibernateStudy.getMultiInstitutionIndicator());
		    	s.setRandomizedIndicator(hibernateStudy.getRandomizedIndicator());
		    	s.setShortTitle(hibernateStudy.getShortTitle());
		    	s.setLongTitle(hibernateStudy.getLongTitle());
		    	s.setDescription(hibernateStudy.getDescription());
		    	s.setPrecis(hibernateStudy.getPrecis());
		    	s.setDiseaseCode(hibernateStudy.getDiseaseCode());
		    	s.setMonitorCode(hibernateStudy.getMonitorCode());
		    	s.setPhaseCode(hibernateStudy.getPhaseCode());
		    	s.setPrimaryFundingSponsorOrganization(hibernateStudy.getPrimaryFundingSponsorOrganization());
		    	s.setStatus(hibernateStudy.getStatus());
		    	s.setTargetAccrualNumber(hibernateStudy.getTargetAccrualNumber());
		    	s.setCtcVersion(hibernateStudy.getCtcVersion());
		    	s.setDesign(hibernateStudy.getDesign());

		    	List<CtepStudyDisease> ctepds = hibernateStudy.getCtepStudyDiseases();
		    	for (CtepStudyDisease dis:ctepds) {
		    		s.addCtepStudyDisease(dis);
		    	}
		    	
		    	List<StudyAgent> sas = hibernateStudy.getStudyAgents();
		    	for (StudyAgent sa:sas) {
		    		s.addStudyAgent(getStudyAgent(sa));
		    	}
		    	
		    	List<Identifier> ids = hibernateStudy.getIdentifiers();
		    	//s.setIdentifiers(new ArrayList<Identifier>());
		    	for (Identifier id:ids) {
		    		s.addIdentifier(getIdentifier(id));
		    	}
		    	studySite.setStudy(s);
		    	//studySite.setOrganization(ss.getOrganization());
		    	studySite.setOrganization(getOrganization(ss.getOrganization()));
		    	
		    	studySite.setStudyInvestigators(ss.getStudyInvestigators());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getStudySite() "+e.getMessage() , e);
	    	}
	    	//System.out.println("STUDY INVES INTERNAL >>>>>>>>>>>>>>> " + ss.getStudyInvestigatorsInternal().size());
	    	return studySite;
	    }
	    
	    private StudyAgent getStudyAgent(StudyAgent sa) {
	    	StudyAgent studyAgent = new StudyAgent();
	    	studyAgent.setIndType(sa.getIndType());
	    	studyAgent.setAgent(sa.getAgent());
	    	studyAgent.setAgentAsString(sa.getAgentAsString());
	    	studyAgent.setOtherAgent(sa.getOtherAgent());
	    	return studyAgent;
	    }
	    private Organization getOrganization(Organization org) {
	    	Organization organization = new LocalOrganization();
	    	organization.setId(org.getId());
	    	organization.setName(org.getName());
	    	organization.setNciInstituteCode(org.getNciInstituteCode());
	    	organization.setDescriptionText(org.getDescriptionText());
	    	
	    	List<SiteInvestigator> siList = new ArrayList<SiteInvestigator>();
	    	
	    	for (SiteInvestigator si:org.getSiteInvestigators()) {
	    		siList.add(si);
	    	}
	    	organization.setSiteInvestigators(siList);
	    	
	    	return organization;
	    }
	    protected AdverseEvent getAdverseEvent(AdverseEvent ae , int seq) throws Exception {
	    	AdverseEvent adverseEvent = new AdverseEvent();
	    	try {
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
	
		    	
		    	adverseEvent.setDiseaseAttributions(ae.getDiseaseAttributions());
		    	adverseEvent.setSurgeryAttributions(ae.getSurgeryAttributions());
		    	adverseEvent.setRadiationAttributions(ae.getRadiationAttributions());
		    	adverseEvent.setDeviceAttributions(ae.getDeviceAttributions());
	
	
				if (ae.getAdverseEventTerm().getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
					adverseEvent.setAdverseEventMeddraLowLevelTerm(ae.getAdverseEventMeddraLowLevelTerm());
				} else {
					adverseEvent.getAdverseEventCtcTerm().setCtcTerm(getCtcTerm(ae.getAdverseEventCtcTerm().getCtcTerm()));
				}
				adverseEvent.setLowLevelTerm(ae.getLowLevelTerm());
	
				
		    	adverseEvent.setHospitalization(ae.getHospitalization());
		    	adverseEvent.setGrade(ae.getGrade());
		    	adverseEvent.setAttributionSummary(ae.getAttributionSummary());
		    	adverseEvent.setExpected(ae.getExpected());
		    	
		    	if (seq == 0 ) {
		    		adverseEvent.setGridId("PRY"+ae.getGridId());
		    	} else {
		    		adverseEvent.setGridId("PRN"+ae.getGridId());
		    	}
		    	adverseEvent.setEventApproximateTime(ae.getEventApproximateTime());
		    	adverseEvent.setEventLocation(ae.getEventLocation());

		    	List<Outcome> outcomes = ae.getOutcomes();
		    	
		    	for (Outcome oc: outcomes) {
		    		adverseEvent.addOutcome(getOutcome(oc));
		    	}
		    	
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getAdverseEvent() "+e.getMessage() , e);
	    	}

	    	return adverseEvent;
	    }
	    /**
	     * This method will return a copy of the CTCTerm from the given term
	     * @param term
	     * @return
	     */
	    private CtcTerm getCtcTerm(CtcTerm ctcTerm){
	    	CtcTerm term = new CtcTerm();
	    	term.setId(ctcTerm.getId());
	    	term.setTerm(ctcTerm.getTerm());
	    	term.setCtepTerm(ctcTerm.getCtepTerm());
	    	term.setCtepCode(ctcTerm.getCtepCode());
	    	term.setSelect(ctcTerm.getSelect());
	    	term.setOtherRequired(ctcTerm.isOtherRequired());
	    	CtcCategory category = new CtcCategory();
	    	category.setId(ctcTerm.getCategory().getId());
	    	category.setName(ctcTerm.getCategory().getName());
	    	term.setCategory(category);
	    	
	    	return term;
	    }

	    private OtherCauseAttribution getOtherCauseAttribution(OtherCauseAttribution oca) throws Exception {
	    	OtherCauseAttribution otherCauseAttribution = new OtherCauseAttribution();
	    	try {
	    		otherCauseAttribution.setAttribution(oca.getAttribution());
	    		otherCauseAttribution.setCause(getOtherCause(oca.getCause()));
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getOtherCauseAttribution() "+e.getMessage() , e);
	    	}
	    	return otherCauseAttribution;
	    }


	    private OtherCause getOtherCause(OtherCause oc) throws Exception {
	    	OtherCause otherCause = new OtherCause();
		    try {
		    	otherCause.setText(oc.getText());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getOtherCause() "+e.getMessage() , e);
	    	}
	    	return otherCause;

	    }

	    private TreatmentInformation getTreatmentInformation(TreatmentInformation trtInf,List<String> notApplicableFieldPaths) throws Exception {
	    	TreatmentInformation treatmentInformation = new TreatmentInformation();
	    	String field = "treatmentInformation";
	    	
	    	try {

	    		treatmentInformation.setFirstCourseDate(trtInf.getFirstCourseDate());
		    	treatmentInformation.setAdverseEventCourse(trtInf.getAdverseEventCourse());
		    	treatmentInformation.setTotalCourses(trtInf.getTotalCourses());
	    		if (!notApplicableFieldPaths.contains("treatmentInformation.investigationalAgentAdministered")) {	
	    			treatmentInformation.setInvestigationalAgentAdministered(trtInf.getInvestigationalAgentAdministered());
	    		}
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
		    		if (!notApplicableFieldPaths.contains("treatmentInformation.courseAgents[].administrationDelayAmount")) {
		    			ca1.setAdministrationDelayAmount(ca.getAdministrationDelayAmount());
		    		} else {
		    			ca1.setAdministrationDelayAmount(new BigDecimal(-1));
		    		}
		    		ca1.setAdministrationDelayUnits(ca.getAdministrationDelayUnits());
		    		ca1.setDose(ca.getDose());
		    		//ca1.setModifiedDose(ca.getModifiedDose());
		    		ca1.setAgentAdjustment(ca.getAgentAdjustment());
		    		if (ca.getStudyAgent() != null) {
		    			ca1.setStudyAgent(getStudyAgent(ca.getStudyAgent()));
		    		}
		    		ca1.setTotalDoseAdministeredThisCourse(ca.getTotalDoseAdministeredThisCourse());
		    		ca1.setFormulation(ca.getFormulation());
		    		ca1.setLotNumber(ca.getLotNumber());
		    		treatmentInformation.addCourseAgent(ca1);
		    	}
		    	
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getTreatmentInformation() "+e.getMessage() , e);
	    	}

	    	return treatmentInformation;
	    }



		public String getMappingFile() {
			return mappingFile;
		}

//		public void setMappingFile(String mappingFile) {
	//		this.mappingFile = mappingFile;
		//}
		
		public static void main (String[] args) {
			//
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			try {
				Date a = dfm.parse("1678-06-06 00:00:00");

				AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
				adverseEventResponseDescription.setDateRemovedFromProtocol(a);
				System.out.println(adverseEventResponseDescription.getDateRemovedFromProtocol());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
		}

}
