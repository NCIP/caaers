package gov.nih.nci.cabig.caaers.service;

import java.util.List;
import java.util.HashMap;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Status;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

public class RoutineAdverseEventReportServiceImpl extends AbstractImportServiceImpl{
	
	ParticipantDao participantDao;
	StudySiteDao studySiteDao;
	StudyParticipantAssignmentDao studyParticipantAssignmentDoa;
	LowLevelTermDao lowLevelTermDao;
	CtcTermDao ctcTermDao;
	TreatmentAssignmentDao treatmentAssignmentDao;
	
	public DomainObjectImportOutcome<RoutineAdverseEventReport> createRoutineAdverseEventReportObjects(RoutineAdverseEventReport xstreamRoutineAdverseEventReport) {

		RoutineAdverseEventReport routineAdverseEventReport = new RoutineAdverseEventReport();
		DomainObjectImportOutcome<RoutineAdverseEventReport> routineAdverseEventReportImportOutcome = new DomainObjectImportOutcome<RoutineAdverseEventReport>();
		
		routineAdverseEventReport.setStartDate(xstreamRoutineAdverseEventReport.getStartDate());
		routineAdverseEventReport.setEndDate(xstreamRoutineAdverseEventReport.getEndDate());
		routineAdverseEventReport.setStatus(xstreamRoutineAdverseEventReport.getStatus());
		
		ifNullObject(xstreamRoutineAdverseEventReport.getStatus(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Status is either Empty or Not Valid");
		ifNullObject(xstreamRoutineAdverseEventReport.getStartDate(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Start Date is either Empty or Not Valid");
		ifNullObject(xstreamRoutineAdverseEventReport.getEndDate(), routineAdverseEventReportImportOutcome,Severity.ERROR, "End Date is either Empty or Not Valid");


		//migrateIdentifiers(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
		migrateAssignments(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
		
		if (routineAdverseEventReportImportOutcome.isSavable()) {
			
			migrateTreatmentAssignment(routineAdverseEventReport,
									   xstreamRoutineAdverseEventReport,
									   routineAdverseEventReportImportOutcome);

			AeTerminology aeTerminology = studyCtcOrMeddraBased(routineAdverseEventReport);
			// STATUS = CURRENT
			if (routineAdverseEventReport.getStatus() != null
					&& routineAdverseEventReport.getStatus() == Status.CURRENT) {

				if (aeTerminology.getTerm() != null
						&& aeTerminology.getTerm() == Term.CTC) {
					migrateCtcBasedAdverseEvents(routineAdverseEventReport,
							xstreamRoutineAdverseEventReport,
							routineAdverseEventReportImportOutcome, aeTerminology
									.getCtcVersion());
				}
				if (aeTerminology.getTerm() != null
						&& aeTerminology.getTerm() == Term.MEDDRA) {
					migrateMeddraBasedAdverseEvents(routineAdverseEventReport,
							xstreamRoutineAdverseEventReport,
							routineAdverseEventReportImportOutcome);
				}
			}
			// STATUS = LEGACY
			if (routineAdverseEventReport.getStatus() != null
					&& routineAdverseEventReport.getStatus() == Status.LEGACY) {

				if (aeTerminology.getTerm() != null
						&& aeTerminology.getTerm() == Term.CTC) {
					migrateCtcBasedAdverseEventsRelaxed(
							routineAdverseEventReport,
							xstreamRoutineAdverseEventReport,
							routineAdverseEventReportImportOutcome, aeTerminology
									.getCtcVersion());
				}
				if (aeTerminology.getTerm() != null
						&& aeTerminology.getTerm() == Term.MEDDRA) {
					migrateMeddraBasedAdverseEventsRelaxed(
							routineAdverseEventReport,
							xstreamRoutineAdverseEventReport,
							routineAdverseEventReportImportOutcome);
				}
			}
		}
			
		routineAdverseEventReportImportOutcome.setImportedDomainObject(routineAdverseEventReport);
		//System.out.println("Number of messages : " + routineAdverseEventReportImportOutcome.getMessages().size());
		//participantUniquenessCheck(routineAdverseEventReport,routineAdverseEventReportImportOutcome,Severity.ERROR);
		
		return routineAdverseEventReportImportOutcome;
		}
	
	
		/*
		 *  Get the Term of the selected Study to find out if MedDRA or CRC based.
		 */
		private AeTerminology studyCtcOrMeddraBased(RoutineAdverseEventReport routineAdverseEventReport){
			AeTerminology aeTerminology = null;
			
			if (routineAdverseEventReport.getAssignment() != null) {
				aeTerminology = routineAdverseEventReport.getAssignment().getStudySite().getStudy().getAeTerminology();
			}
			return aeTerminology;
		}
		
		
		private void migrateAssignments(RoutineAdverseEventReport destination,
			RoutineAdverseEventReport source,
			DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {

		if (source.getAssignment() != null) {
			StudyParticipantAssignment spa = null;
			StudySite studySite = null;
			Participant participant = null;

			StudyParticipantAssignment studyParticipantAssignment = source
					.getAssignment();

			// StudySite
			for (Identifier identifier : studyParticipantAssignment
					.getStudySite().getStudy().getIdentifiers()) {

				studySite = studySiteDao.matchByStudyAndOrg(
						studyParticipantAssignment.getStudySite()
								.getOrganization().getName(), identifier
								.getValue());
				
				if (studySite != null) {
					log.debug("StudySite id : " + studySite.getId());
					log.debug("Orgs in study " + studySite.getStudy().getStudyOrganizations().size());
					// This is here so the studyOrgaizations are initialized if not hibernate throws a lazy initialization exceptions
					studySite.getStudy().getStudyOrganizations().size();
					break;
				}
			}
			// Participant
			for (Identifier identifier : studyParticipantAssignment
					.getParticipant().getIdentifiers()) {

				participant = participantDao.getByIdentifier(identifier);
				if (participant != null) {
					//System.out.println("Participant " + participant.getId());
					break;
				}
			}
			if (studySite != null && participant != null) {
				spa = studyParticipantAssignmentDoa.getAssignment(participant,
						studySite);
				//System.out.println("StudyParticipantAssignment " + spa.getId());
			}
			if (spa != null) {
				destination.setAssignment(spa);
			}
		}
		ifNullObject(destination.getAssignment(), routineAdverseEventReportImportOutcome,
				Severity.ERROR," Study/Participant could not be found ");
		}
		
		private void migrateTreatmentAssignment(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {
			
			TreatmentAssignment treatmentAssignment = null ;
			
			if (source.getTreatmentAssignment() != null) {
				String code = source.getTreatmentAssignment().getCode();
				if (code != null) {
					treatmentAssignment = treatmentAssignmentDao.getAssignmentsByStudyIdExactMatch(code, destination.getStudy().getId());
					ifNullObject(treatmentAssignment, routineAdverseEventReportImportOutcome,Severity.ERROR,
							"The treatment Assignment code  " + code + "is not valid ");
					destination.setTreatmentAssignment(treatmentAssignment);
				}
			}
		}
		
		
		
		
		/*
		 * This method is used to import legacy routine AEs - It has relaxed Rules
		 */
		private void migrateCtcBasedAdverseEventsRelaxed(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
				Ctc ctcVersion) {
			
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String ctepCode = adverseEvent.getAdverseEventCtcTerm().getTerm().getCtepCode();
				String term = adverseEvent.getAdverseEventCtcTerm().getTerm().getTerm();
				CtcTerm ctcTerm = null;
				AdverseEvent ae = new AdverseEvent();
				
				if (ctepCode != null) {
					String[] cc = { ctepCode };
					ctcTerm = ctcTermDao.getCtcTerm(cc);
				}
				if (term != null) {
					String[] cc = { term };
					ctcTerm = ctcTermDao.getCtcTerm(cc);
				}
				ifNullObject(ctcTerm, routineAdverseEventReportImportOutcome,Severity.ERROR,"The CtcTerm you provided is not valid");
				ctcTermValidity(ctcTerm, ctcVersion, adverseEvent, ae, routineAdverseEventReportImportOutcome, false);
				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
				
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				destination.addAdverseEvent(ae);
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR,"A list of AdverseEvents could not be found");
			
		}
		
		private void migrateCtcBasedAdverseEvents(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
				Ctc ctcVersion) {
			
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String ctepCode = adverseEvent.getAdverseEventCtcTerm().getTerm().getCtepCode();
				String term = adverseEvent.getAdverseEventCtcTerm().getTerm().getTerm();
				String termConcatCtepCode = term == null ? ctepCode == null ? "N/A" : ctepCode :term;
				CtcTerm ctcTerm = null;
				AdverseEvent ae = new AdverseEvent();
				
				if (ctepCode != null) {
					String[] cc = { ctepCode };
					ctcTerm = ctcTermDao.getCtcTerm(cc);
				}
				if (term != null) {
					String[] cc = { term };
					ctcTerm = ctcTermDao.getCtcTerm(cc);
				}
				ifNullObject(ctcTerm, routineAdverseEventReportImportOutcome,Severity.ERROR,"The Term you provided " + termConcatCtepCode + " is not valid ");
				
				ctcTermValidity(ctcTerm, ctcVersion, adverseEvent, ae, routineAdverseEventReportImportOutcome, true);
				
				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
				
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				ifNullObject(ae.getGrade(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Grade is either Empty or Not Valid");
				ifNullObject(ae.getHospitalization(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Hospitalization is either Empty or Not Valid");
				ifNullObject(ae.getExpected(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Expectedness is either Empty or Not Valid");
				ifNullObject(ae.getAttributionSummary(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Attribution is either Empty or Not Valid");
				
				destination.addAdverseEvent(ae);
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR,"AdverseEvents are either Empty or Not Valid");
			
		}
		
		private void migrateMeddraBasedAdverseEvents(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {
			
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String meddraCode = adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraCode();
				LowLevelTerm lowLevelTerm =null;
				AdverseEvent ae =new AdverseEvent();
				
				
				if (meddraCode != null) {
					List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(meddraCode);
					lowLevelTerm = terms.isEmpty() == false ? terms.get(0) : null;
				}
				ifNullObject(lowLevelTerm, routineAdverseEventReportImportOutcome,Severity.ERROR,"LowLevelTerm is either Empty or Not Valid");

				ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				ifNullObject(ae.getGrade(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Grade is either Empty or Not Valid");
				ifNullObject(ae.getHospitalization(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Hospitalization is either Empty or Not Valid");
				ifNullObject(ae.getExpected(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Expectedness is either Empty or Not Valid");
				ifNullObject(ae.getAttributionSummary(), routineAdverseEventReportImportOutcome,Severity.ERROR, "Attribution is either Empty or Not Valid");
				
				destination.addAdverseEvent(ae);
				//System.out.println("Hospitalization : " + adverseEvent.getHospitalization());
				//System.out.println("Meddra Term Id : " + adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm());
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR, "AdverseEvents are either Empty or Not Valid");
			
		}
		
		private void migrateMeddraBasedAdverseEventsRelaxed(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {
			
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String meddraCode = adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraCode();
				LowLevelTerm lowLevelTerm =null;
				AdverseEvent ae =new AdverseEvent();
				
				
				if (meddraCode != null) {
					List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(meddraCode);
					lowLevelTerm = terms.isEmpty() == false ? terms.get(0) : null;
				}
				ifNullObject(lowLevelTerm, routineAdverseEventReportImportOutcome,Severity.ERROR,"LowLevelTerm is either Empty or Not Valid");

				ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				destination.addAdverseEvent(ae);

				//System.out.println("ter" + adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm());
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR,"AdverseEvents are either Empty or Not Valid");
			
		}
		
		
		private void ctcTermValidity(CtcTerm ctcTerm, 
									 Ctc ctcVersion,
									 AdverseEvent adverseEvent, 
									 AdverseEvent ae, 
									 DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
									 boolean isCurrent){
			if (ctcTerm != null ){
				// Is provided CtcTerm same version as Ctc version specified in Study ?
				//System.out.println(" The CTC version of the provided Term :" + ctcTerm.getCategory().getCtc().getName());
				if (! ctcTerm.getCategory().getCtc().getName().equals(ctcVersion.getName())){
					errorInBusinessLogic(routineAdverseEventReportImportOutcome,Severity.ERROR,
							"There is a discrepency between the CTC versions, The Ctc Term : " + 
							ctcTerm.getFullName() + " is " + ctcTerm.getCategory().getCtc().getName() + " whereas the study expects " + 
							ctcVersion.getName());
				}
				
				if (isCurrent) {
				// Get Contextual grades for this specific Term
				HashMap<Grade,CtcGrade> contextualGrades = new HashMap<Grade,CtcGrade>();
				for (CtcGrade ctcContextualGrade : ctcTerm.getContextualGrades()) {
					contextualGrades.put(ctcContextualGrade.getGrade(), ctcContextualGrade);	
				}
				
				// grade provided does not match any in the contextual list ? 
				if (! contextualGrades.containsKey(adverseEvent.getGrade())){
					String grades = "" ; 
					for (Grade key : contextualGrades.keySet()) {
						grades = grades + key.getName() + " ";							
					}    	
					errorInBusinessLogic(routineAdverseEventReportImportOutcome,Severity.ERROR,
							"The grade you provided for " + ctcTerm.getFullName() + " is not allowed use one of those : " + grades);
				}
				}
				
				// does the CtcTerm require other(verbatim) or other(MedDRA) ? 
				if (ctcTerm.isOtherRequired()) {
					String meddraCode = adverseEvent.getLowLevelTerm() != null ? adverseEvent.getLowLevelTerm().getMeddraCode() : null;
					LowLevelTerm lowLevelTerm =null;

					if (meddraCode != null) {
						List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(meddraCode);
						lowLevelTerm = terms.isEmpty() == false ? terms.get(0) : null;
						if (lowLevelTerm == null) {
							errorInBusinessLogic(routineAdverseEventReportImportOutcome,Severity.ERROR,
									"The MedDRA Code " + meddraCode  + " you provided for " + ctcTerm.getFullName() + " is not valid");
						}else{
							ae.setLowLevelTerm(lowLevelTerm);
						}
					}
					ae.setDetailsForOther(adverseEvent.getDetailsForOther());
					if (isCurrent){
						if (ae.getDetailsForOther() == null && ae.getLowLevelTerm() == null){
							errorInBusinessLogic(routineAdverseEventReportImportOutcome,Severity.ERROR,
									"The provided CTC term " + ctcTerm.getFullName() + " requires Other information ");
						}
					}
				}	
			}
		}
		
		/*
		private void participantUniquenessCheck(Participant participant, DomainObjectImportOutcome participantImportOutcome, Severity severity){
			
			String[] s = { participant.getFirstName(),participant.getLastName() };
			List<Participant> pars = participantDao.getByUniqueIdentifiers(s);
			if (pars != null && pars.size() >= 1){
				participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName() + " already exists. ",severity);
			}
		}
		*/
		
		public ParticipantDao getParticipantDao() {
			return participantDao;
		}
		public void setParticipantDao(ParticipantDao participantDao) {
			this.participantDao = participantDao;
		}
		public StudySiteDao getStudySiteDao() {
			return studySiteDao;
		}
		public void setStudySiteDao(StudySiteDao studySiteDao) {
			this.studySiteDao = studySiteDao;
		}
		public StudyParticipantAssignmentDao getStudyParticipantAssignmentDoa() {
			return studyParticipantAssignmentDoa;
		}
		public void setStudyParticipantAssignmentDoa(
				StudyParticipantAssignmentDao studyParticipantAssignmentDoa) {
			this.studyParticipantAssignmentDoa = studyParticipantAssignmentDoa;
		}
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
		public TreatmentAssignmentDao getTreatmentAssignmentDao() {
			return treatmentAssignmentDao;
		}
		public void setTreatmentAssignmentDao(
				TreatmentAssignmentDao treatmentAssignmentDao) {
			this.treatmentAssignmentDao = treatmentAssignmentDao;
		}
		
		
}
