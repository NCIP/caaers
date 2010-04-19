package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.utils.FetcherUtils;

import java.util.List;

import org.easymock.EasyMock;

public class ParticipantdFetcherTest extends CaaersDaoTestCase {

	private CaaersParticipantIdFetcherImpl participantIdFetcher = null;
	private FetcherUtils fetcherUtils = null;
	private ParticipantDao participantDao = null;
	
	private void enableAuthorization(){
		SecurityTestUtils.enableAuthorization(true, applicationContext);
	}

	protected void setUp() throws Exception {
		super.setUp();
		participantIdFetcher = 
			(CaaersParticipantIdFetcherImpl) getApplicationContext().getBean("participantIdFetcher");
		participantDao = 
			(ParticipantDao) getApplicationContext().getBean("participantDao");
		
		participantDao = 
			(ParticipantDao) getApplicationContext().getBean("participantDao");
		
		fetcherUtils = registerMockFor(FetcherUtils.class);
		
		
		//ParticipantIndexBuilder
		
		participantIdFetcher.setFetcherUtils(fetcherUtils);
	}
	
	public void testFetchAsStudyCoordinator() {
		enableAuthorization();		
		SecurityTestUtils.switchToSuperuser();
		
		boolean studyFilteringRequired = true;
		EasyMock.expect(fetcherUtils.studyFilteringRequired((List<UserGroupType>)EasyMock.anyObject(),(List<String>)EasyMock.anyObject())).andReturn(studyFilteringRequired);
		replayMocks();
		ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
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
