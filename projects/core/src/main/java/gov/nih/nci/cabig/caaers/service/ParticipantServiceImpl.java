package gov.nih.nci.cabig.caaers.service;

import java.util.List;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;

public class ParticipantServiceImpl implements ParticipantService{
	
	ParticipantDao participantDao;

	/**
	 * Search using a sample. Populate a Participant object
	 * @param  Participant object
	 * @return List of Participant objects based on the sample participant object
	 * @throws Runtime exception 
	 */
	public List<Participant> search(Participant participant) throws Exception {		
		return participantDao.searchByExample(participant);
	}
	
	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
	


	
}
