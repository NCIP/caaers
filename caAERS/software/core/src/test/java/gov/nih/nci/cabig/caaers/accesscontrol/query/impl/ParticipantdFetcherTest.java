package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.List;

import org.easymock.EasyMock;

public class ParticipantdFetcherTest extends CaaersDaoTestCase {

	private CaaersParticipantIdFetcherImpl participantIdFetcher = null;

	
	private void enableAuthorization(){
		SecurityTestUtils.enableAuthorization(true, applicationContext);
	}

	protected void setUp() throws Exception {
		super.setUp();
		participantIdFetcher = 
			(CaaersParticipantIdFetcherImpl) getApplicationContext().getBean("participantIdFetcher");

	}
	
	public void testFetchAsStudyCoordinator() {
		enableAuthorization();		
		SecurityTestUtils.switchToSuperuser();
		
		boolean studyFilteringRequired = true;
		replayMocks();

		List<String> filteredList = participantIdFetcher.fetch("1000@def.com");
		assertEquals(1,filteredList.size());	
		/*
		Participant participant = participantDao.getById(Integer.parseInt(filteredList.get(0)));
		ParticipantIndex pi = new ParticipantIndex();
		pi.setParticipant(participant);
		pi.setLoginId("1000@def.com");
		participantIndexDao.save(pi);
		
		
		assertEquals(1,participantIndexDao.getByLoginId("1000@def.com").size());	*/
	}

}
