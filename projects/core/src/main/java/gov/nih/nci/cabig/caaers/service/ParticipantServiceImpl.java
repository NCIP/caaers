package gov.nih.nci.cabig.caaers.service;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

public class ParticipantServiceImpl extends AbstractImportServiceImpl implements ParticipantService{
	
	ParticipantDao participantDao;
	StudySiteDao studySiteDao;

	/**
	 * Search using a sample. Populate a Participant object
	 * @param  Participant object
	 * @return List of Participant objects based on the sample participant object
	 * @throws Runtime exception 
	 */
	public List<Participant> search(Participant participant) throws Exception {		
		return participantDao.searchByExample(participant);
	}
	
	/**
	 * Will calculate the body surface area using Mosteller formula
	 */
	public double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM) {
		double newHeight = height;
		double newWeight = weight;
		
		if(heightUOM.equalsIgnoreCase("Inch")) newHeight = height * 2.54;
		if(weightUOM.equalsIgnoreCase("Pound")) newWeight = weight / 2.20462262185;
		return Math.sqrt((newHeight * newWeight) / 3600);
	}
	
	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
	
	
	
	public DomainObjectImportOutcome<Participant> createParticipantObjects(Participant xstreamParticipant) {

		Participant participant = new Participant();
		DomainObjectImportOutcome<Participant> participantImportOutcome = new DomainObjectImportOutcome<Participant>();
		
		participant.setFirstName(xstreamParticipant.getFirstName());
		participant.setLastName(xstreamParticipant.getLastName());
		participant.setMiddleName(xstreamParticipant.getMiddleName());
		participant.setMaidenName(xstreamParticipant.getMaidenName());
		participant.setDateOfBirth(xstreamParticipant.getDateOfBirth());
		participant.setGender(xstreamParticipant.getGender());
		participant.setRace(xstreamParticipant.getRace());
		participant.setEthnicity(xstreamParticipant.getRace());

		//migrateIdentifiers(participant,xstreamParticipant);
		migrateIdentifiers(participant,xstreamParticipant, participantImportOutcome);
		migrateAssignments(participant,xstreamParticipant, participantImportOutcome);
		
		
		participantImportOutcome.setImportedDomainObject(participant);
		participantUniquenessCheck(participant,participantImportOutcome,Severity.ERROR);
		
		return participantImportOutcome;
		}
		
		private void migrateAssignments(Participant destination,
			Participant source,
			DomainObjectImportOutcome participantImportOutcome) {

		if (source.getAssignments() != null) {
			for (int i = 0; i < source.getAssignments().size(); i++) {
				StudyParticipantAssignment studyParticipantAssignment = source
						.getAssignments().get(i);
				//StudySite studySite = null;

				for (Identifier identifier : studyParticipantAssignment
						.getStudySite().getStudy().getIdentifiers()) {
					
					StudySite studySite = studySiteDao.matchByStudyAndOrg(
							studyParticipantAssignment.getStudySite().getOrganization().getName(), 
							identifier.getValue());
					
	
					if (studySite != null) {
						log.info("StudySite was found id :  " + studySite.getId());
						//studySite = study.getStudySites().get(0);
						destination.getAssignments().add(
								new StudyParticipantAssignment(destination,
										studySite));
						break;
					}
					
					ifNullObject(studySite, participantImportOutcome,Severity.ERROR, "Study is either Empty or Not Valid");
					
				}
			}
		}
		ifNullOrEmptyList(source.getAssignments(), participantImportOutcome,
				Severity.ERROR, "Assignments are either Empty or Not Valid");
		}
		
		
		
		private void participantUniquenessCheck(Participant participant, DomainObjectImportOutcome participantImportOutcome, Severity severity){
			
			/*
			String[] s = { participant.getFirstName(),participant.getLastName() };
			List<Participant> pars = participantDao.getByUniqueIdentifiers(s);
			if (pars != null && pars.size() >= 1){
				participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName() + " already exists. ",severity);
			}
			*/
			
			for (Identifier identifier : participant.getIdentifiers()) 
			{	
				Participant tempParticipant = getParticipantDao().getByIdentifier(identifier);
				if (tempParticipant != null) {
					participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName() + " identifier already exists. ",severity);
					break;
				} 		
			}
		}
		
		

		public StudySiteDao getStudySiteDao() {
			return studySiteDao;
		}

		public void setStudySiteDao(StudySiteDao studySiteDao) {
			this.studySiteDao = studySiteDao;
		}
		
		
		
		
		
	
	
	
	


	
}
