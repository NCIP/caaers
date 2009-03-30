package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Status;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.security.util.StringUtilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

        routineAdverseEventReportImportOutcome.addErrorMessageForRoutineAdverseEventReport(xstreamRoutineAdverseEventReport);


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
							routineAdverseEventReportImportOutcome,
							aeTerminology.getMeddraVersion());
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
							routineAdverseEventReportImportOutcome,
							aeTerminology.getMeddraVersion());
				}
			}
		}

		routineAdverseEventReportImportOutcome.setImportedDomainObject(routineAdverseEventReport);
		//System.out.println("Number of messages : " + routineAdverseEventReportImportOutcome.getMessages().size());
		//checkIfParticipantExistsForGivenIdentifiers(routineAdverseEventReport,routineAdverseEventReportImportOutcome,Severity.ERROR);

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
				
				Organization organization = studyParticipantAssignment.getStudySite().getOrganization();
				String nciInstituteCode = organization.getNciInstituteCode();
				String orgName = organization.getName();
				
				if (!StringUtilities.isBlank(nciInstituteCode)) {
					studySite = studySiteDao.matchByStudyAndOrgNciId(nciInstituteCode, identifier.getValue(),identifier.getType());
				}else{
					studySite = studySiteDao.matchByStudyAndOrg(orgName, identifier.getValue(),identifier.getType());
				}
				
				/*
				studySite = studySiteDao.matchByStudyAndOrg(
						studyParticipantAssignment.getStudySite()
								.getOrganization().getName(), identifier
								.getValue(),identifier.getType());*/

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
		routineAdverseEventReportImportOutcome.ifNullObject(destination.getAssignment(),
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
					routineAdverseEventReportImportOutcome.ifNullObject(treatmentAssignment, Severity.ERROR,
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

			Integer ordinal = 1;
			HashSet<Integer> hSet = new HashSet<Integer>();

			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String ctepCode = (adverseEvent.getAdverseEventCtcTerm() != null && adverseEvent.getAdverseEventCtcTerm().getTerm() != null ) ?
						adverseEvent.getAdverseEventCtcTerm().getTerm().getCtepCode() : null;
				String term =  (adverseEvent.getAdverseEventCtcTerm() != null && adverseEvent.getAdverseEventCtcTerm().getTerm() != null ) ?
					 adverseEvent.getAdverseEventCtcTerm().getTerm().getTerm() : null;
				CtcTerm ctcTerm = null;
				AdverseEvent ae = new AdverseEvent();

				if (ctepCode != null) {
					String[] cc = { ctepCode };
					//ctcTerm = ctcTermDao.getCtcTerm(cc);
					ctcTerm = ctcTermDao.getCtcTerm(cc, ctcVersion.getId(), null);
				}
				if (term != null) {
					String[] cc = { term };
					//ctcTerm = ctcTermDao.getCtcTerm(cc);
					ctcTerm = ctcTermDao.getCtcTerm(cc, ctcVersion.getId(), null);
				}
                routineAdverseEventReportImportOutcome.addErrorMessageForCtcTerm(ctcTerm, ordinal,ctcVersion);
                ctcTermValidity(ctcTerm, ctcVersion, adverseEvent, ae, routineAdverseEventReportImportOutcome, false);

				if ( ctcTerm != null){
					if(!hSet.add(ctcTerm.getId())){
						routineAdverseEventReportImportOutcome.addErrorMessage("The Term you provided in AE " + ordinal + " already exists.", Severity.ERROR);
					}
				}

				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);

				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());

				destination.addAdverseEvent(ae);
				ordinal++;
			}

			routineAdverseEventReportImportOutcome.ifNullOrEmptyList(source.getAdverseEvents(), Severity.ERROR,"A list of AdverseEvents could not be found");

		}

		private void migrateCtcBasedAdverseEvents(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
				Ctc ctcVersion) {

			Integer ordinal = 1;
			HashSet<Integer> hSet = new HashSet<Integer>();

			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String ctepCode = (adverseEvent.getAdverseEventCtcTerm() != null && adverseEvent.getAdverseEventCtcTerm().getTerm() != null ) ?
						adverseEvent.getAdverseEventCtcTerm().getTerm().getCtepCode() : null;
				String term =  (adverseEvent.getAdverseEventCtcTerm() != null && adverseEvent.getAdverseEventCtcTerm().getTerm() != null ) ?
					 adverseEvent.getAdverseEventCtcTerm().getTerm().getTerm() : null;
				CtcTerm ctcTerm = null;
				AdverseEvent ae = new AdverseEvent();

				if (ctepCode != null) {
					String[] cc = { ctepCode };
					//ctcTerm = ctcTermDao.getCtcTerm(cc);
					ctcTerm = ctcTermDao.getCtcTerm(cc, ctcVersion.getId(), null);
				}
				if (term != null) {
					String[] cc = { term };
					ctcTerm = ctcTermDao.getCtcTerm(cc, ctcVersion.getId(), null);
					//ctcTerm = ctcTermDao.getCtcTerm(cc);
				}
                routineAdverseEventReportImportOutcome.addErrorMessageForCtcTerm(ctcTerm, ordinal,ctcVersion);

				ctcTermValidity(ctcTerm, ctcVersion, adverseEvent, ae, routineAdverseEventReportImportOutcome, true);

				if ( ctcTerm != null){
					if(!hSet.add(ctcTerm.getId())){
						routineAdverseEventReportImportOutcome.addErrorMessage("The Term you provided in AE " + ordinal + " already exists.", Severity.ERROR);
					}
				}

				ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);

				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());


				routineAdverseEventReportImportOutcome.addErrorMessageForAE(ae,ordinal);
				destination.addAdverseEvent(ae);
				ordinal++;
			}

			routineAdverseEventReportImportOutcome.ifNullOrEmptyList(source.getAdverseEvents(), Severity.ERROR,"AdverseEvents are either Empty or Not Valid");

		}

		private void migrateMeddraBasedAdverseEvents(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
				MeddraVersion meddraVersion) {

			Integer ordinal = 1;
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String meddraCode = (adverseEvent.getAdverseEventMeddraLowLevelTerm() != null && adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm() != null ) ?
					adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraCode() : null;
				LowLevelTerm lowLevelTerm =null;
				AdverseEvent ae =new AdverseEvent();


				if (meddraCode != null) {
					List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(meddraCode);
					lowLevelTerm = terms.isEmpty() == false ? terms.get(0) : null;
				}
				routineAdverseEventReportImportOutcome.addErrorMessageForLowLevelTerm(lowLevelTerm, ordinal,meddraVersion);

				ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());

                routineAdverseEventReportImportOutcome.addErrorMessageForAE(ae,ordinal);
				destination.addAdverseEvent(ae);
				ordinal++;
			}

			routineAdverseEventReportImportOutcome.ifNullOrEmptyList(source.getAdverseEvents(), Severity.ERROR, "AdverseEvents are either Empty or Not Valid");

		}

		private void migrateMeddraBasedAdverseEventsRelaxed(RoutineAdverseEventReport destination,
				RoutineAdverseEventReport source,
				DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
				MeddraVersion meddraVersion) {

			Integer ordinal =1;
			for (AdverseEvent adverseEvent : source.getAdverseEvents()) {
				String meddraCode = (adverseEvent.getAdverseEventMeddraLowLevelTerm() != null && adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm() != null ) ?
						adverseEvent.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraCode() : null;
				LowLevelTerm lowLevelTerm =null;
				AdverseEvent ae =new AdverseEvent();


				if (meddraCode != null) {
					List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(meddraCode);
					lowLevelTerm = terms.isEmpty() == false ? terms.get(0) : null;
				}
                routineAdverseEventReportImportOutcome.addErrorMessageForLowLevelTerm(lowLevelTerm,ordinal,meddraVersion);
                
                ae.getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(lowLevelTerm);
				ae.setGrade(adverseEvent.getGrade());
				ae.setHospitalization(adverseEvent.getHospitalization());
				ae.setExpected(adverseEvent.getExpected());
				ae.setAttributionSummary(adverseEvent.getAttributionSummary());

				destination.addAdverseEvent(ae);
				ordinal++;
			}

			routineAdverseEventReportImportOutcome.ifNullOrEmptyList(source.getAdverseEvents(), Severity.ERROR,"AdverseEvents are either Empty or Not Valid");

		}


		private void ctcTermValidity(CtcTerm ctcTerm,
									 Ctc ctcVersion,
									 AdverseEvent adverseEvent,
									 AdverseEvent ae,
									 DomainObjectImportOutcome routineAdverseEventReportImportOutcome,
									 boolean isCurrent){
			if (ctcTerm != null ){
				// Is provided CtcTerm same version as Ctc version specified in Study ?
				//log.debug(" The CTC version of the provided Term :" + ctcTerm.getCategory().getCtc().getName());
				if (! ctcTerm.getCategory().getCtc().getName().equals(ctcVersion.getName())){
					routineAdverseEventReportImportOutcome.errorInBusinessLogic(Severity.ERROR,
							"There is a discrepency between the CTC versions, The Ctc Term : " +
							ctcTerm.getFullName() + " is " + ctcTerm.getCategory().getCtc().getName() + " whereas the study expects " +
							ctcVersion.getName());
				}

				if (isCurrent && ctcTerm.getCategory().getCtc().getId() == 3) {
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
					routineAdverseEventReportImportOutcome.errorInBusinessLogic(Severity.ERROR,
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
							routineAdverseEventReportImportOutcome.errorInBusinessLogic(Severity.ERROR,
									"The MedDRA Code " + meddraCode  + " you provided for " + ctcTerm.getFullName() + " is not valid");
						}else{
							ae.setLowLevelTerm(lowLevelTerm);
						}
					}
					ae.setDetailsForOther(adverseEvent.getDetailsForOther());
					if (isCurrent){
						if (ae.getDetailsForOther() == null && ae.getLowLevelTerm() == null){
							routineAdverseEventReportImportOutcome.errorInBusinessLogic(Severity.ERROR,
									"The provided CTC term " + ctcTerm.getFullName() + " requires Other information ");
						}
					}
				}
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
		public TreatmentAssignmentDao getTreatmentAssignmentDao() {
			return treatmentAssignmentDao;
		}
		public void setTreatmentAssignmentDao(
				TreatmentAssignmentDao treatmentAssignmentDao) {
			this.treatmentAssignmentDao = treatmentAssignmentDao;
		}


}
