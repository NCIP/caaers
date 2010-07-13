package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.index.ParticipantIndex;

public class ParticipantIndexDaoTest extends CaaersDbTestCase {
	
	ParticipantDao participantDao ; 
	@Override
	protected void setUp() throws Exception {
		// change the security interceptor with stub.
		super.setUp();
		participantDao = (ParticipantDao)getApplicationContext().getBean("participantDao");
	}
	
	
	public void testSave() throws Exception {
        Participant participant = participantDao.getById(-100);
        String userName = "srakkala";
        ParticipantIndex participantIndex = new ParticipantIndex();
        participantIndex.setLoginId(userName);
        participantIndex.setParticipant(participant);

        participant = participantDao.getById(-101);
        participantIndex = new ParticipantIndex();
        participantIndex.setLoginId(userName);
        participantIndex.setParticipant(participant);
        
        System.out.print("sss");
    }
}
