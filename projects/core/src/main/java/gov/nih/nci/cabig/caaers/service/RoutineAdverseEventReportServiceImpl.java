package gov.nih.nci.cabig.caaers.service;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.Status;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

public class RoutineAdverseEventReportServiceImpl extends AbstractImportServiceImpl{
	
	ParticipantDao participantDao;
	StudySiteDao studySiteDao;
	StudyParticipantAssignmentDao studyParticipantAssignmentDoa;
	LowLevelTermDao lowLevelTermDao;
	CtcTermDao ctcTermDao;
	
	public DomainObjectImportOutcome createRoutineAdverseEventReportObjects(RoutineAdverseEventReport xstreamRoutineAdverseEventReport) {

		RoutineAdverseEventReport routineAdverseEventReport = new RoutineAdverseEventReport();
		DomainObjectImportOutcome<RoutineAdverseEventReport> routineAdverseEventReportImportOutcome = new DomainObjectImportOutcome<RoutineAdverseEventReport>();
		
		routineAdverseEventReport.setStartDate(xstreamRoutineAdverseEventReport.getStartDate());
		routineAdverseEventReport.setEndDate(xstreamRoutineAdverseEventReport.getEndDate());
		routineAdverseEventReport.setStatus(xstreamRoutineAdverseEventReport.getStatus());

		//migrateIdentifiers(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
		migrateAssignments(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
		 
		Term term = studyCtcOrMeddraBased(routineAdverseEventReport);
		// STATUS = CURRENT 
		if (routineAdverseEventReport.getStatus() != null && routineAdverseEventReport.getStatus() == Status.CURRENT ) {
			
			if (term != null && term == Term.CTC ){
				migrateCtcBasedAdverseEvents(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
			}
			if (term != null && term == Term.MEDDRA ){
				migrateMeddraBasedAdverseEvents(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
			}
		}
		// STATUS = LEGACY
		if (routineAdverseEventReport.getStatus() != null && routineAdverseEventReport.getStatus() == Status.LEGACY ) {
			
			if (term != null && term == Term.CTC ){
				migrateCtcBasedAdverseEventsRelaxed(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
			}
			if (term != null && term == Term.MEDDRA ){
				migrateMeddraBasedAdverseEventsRelaxed(routineAdverseEventReport,xstreamRoutineAdverseEventReport, routineAdverseEventReportImportOutcome);
			}
		}
			
		routineAdverseEventReportImportOutcome.setImportedDomainObject(routineAdverseEventReport);
		//participantUniquenessCheck(routineAdverseEventReport,routineAdverseEventReportImportOutcome,Severity.ERROR);
		
		return routineAdverseEventReportImportOutcome;
		}
	
	
		/*
		 *  Get the Term of the selected Study to find out if MedDRA or CRC based.
		 */
		private Term studyCtcOrMeddraBased(RoutineAdverseEventReport routineAdverseEventReport){
			Term term = null;
			
			if (routineAdverseEventReport.getAssignment() != null) {
				term = routineAdverseEventReport.getAssignment().getStudySite().getStudy().getTerminology().getTerm();
			}
			return term;
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
					System.out.println("StudySite " + studySite.getId());
					break;
				}
			}
			// Participant
			for (Identifier identifier : studyParticipantAssignment
					.getParticipant().getIdentifiers()) {

				participant = participantDao.getByIdentifier(identifier);
				if (participant != null) {
					System.out.println("Participant " + participant.getId());
					break;
				}
			}
			if (studySite != null && participant != null) {
				spa = studyParticipantAssignmentDoa.getAssignment(participant,
						studySite);
				System.out.println("StudyParticipantAssignment " + spa.getId());
			}
			if (spa != null) {
				destination.setAssignment(spa);
			}
		}
		ifNullObject(destination.getAssignment(), routineAdverseEventReportImportOutcome,
				Severity.ERROR);
	}
		
		private void migrateCtcBasedAdverseEvents(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {
			
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
				ifNullObject(ctcTerm, routineAdverseEventReportImportOutcome,Severity.ERROR);
				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
				
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				ifNullObject(ae.getGrade(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getHospitalization(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getExpected(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getAttributionSummary(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				
				destination.addAdverseEvent(ae);
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR);
			
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
				ifNullObject(lowLevelTerm, routineAdverseEventReportImportOutcome,Severity.ERROR);

				ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				ifNullObject(ae.getGrade(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getHospitalization(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getExpected(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				ifNullObject(ae.getAttributionSummary(), routineAdverseEventReportImportOutcome,Severity.ERROR);
				
				destination.addAdverseEvent(ae);
				System.out.println("Hospitalization : " + adverseEvent.getHospitalization());
				System.out.println("Meddra Term Id : " + adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm());
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR);
			
		}
		
		
		private void migrateCtcBasedAdverseEventsRelaxed(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome) {
			
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
				ifNullObject(ctcTerm, routineAdverseEventReportImportOutcome,Severity.ERROR);
				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
				
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				destination.addAdverseEvent(ae);
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR);
			
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
				ifNullObject(lowLevelTerm, routineAdverseEventReportImportOutcome,Severity.ERROR);

				ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());
				
				destination.addAdverseEvent(ae);

				System.out.println("ter" + adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm());
			}
			
			ifNullOrEmptyList(source.getAdverseEvents(), routineAdverseEventReportImportOutcome, Severity.ERROR);
			
		}
		
		
		
		private void participantUniquenessCheck(Participant participant, DomainObjectImportOutcome participantImportOutcome, Severity severity){
			
			String[] s = { participant.getFirstName(),participant.getLastName() };
			List<Participant> pars = participantDao.getByUniqueIdentifiers(s);
			if (pars != null && pars.size() >= 1){
				participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName() + " already exists. ",severity);
			}
		}
		
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
		
}
