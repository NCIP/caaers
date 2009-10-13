package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.RemoteStudy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.coppa.services.pa.StudyProtocol;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;
import org.iso._21090.II;
/**
 * 
 * @author Biju Joseph
 *
 */
public class RemoteStudyResolverTest extends AbstractTestCase {
	RemoteStudyResolver studyResolver;
	MessageBroadcastService messageBroadcastService;
	StudyProtocol studyProtocol;
	II identifier;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		//initilize the study protocol
		studyProtocol = new StudyProtocol();
		identifier = new II();
		identifier.setExtension("tester");
		studyProtocol.setIdentifier(identifier);
		
		 
		studyResolver = new RemoteStudyResolver();
		messageBroadcastService = registerMockFor(MessageBroadcastService.class);
		studyResolver.setMessageBroadcastService(messageBroadcastService);
	}

	//Will populate "Drug"
	public void testPopulateStudyTherapies() throws Exception{
		
		
		//get the xml from file.
		String xml =  IOUtils.toString(getClass().getResourceAsStream("planned_activity_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), (Metadata)EasyMock.anyObject())).andReturn(xml);
		replayMocks();
		RemoteStudy remoteStudy = new RemoteStudy();
		assertTrue(remoteStudy.getStudyTherapies().isEmpty());
		studyResolver.populateStudyTherapies(studyProtocol, remoteStudy);
		assertEquals(1, remoteStudy.getStudyTherapies().size());
		assertTrue(remoteStudy.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION));
		verifyMocks();
	}
	

	//Will populate "Drug" and remove "Device"
	public void testPopulateStudyTherapies_AddDrugAndRemoveDevice() throws Exception{
		
		//get the xml from file.
		String xml =  IOUtils.toString(getClass().getResourceAsStream("planned_activity_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), (Metadata)EasyMock.anyObject())).andReturn(xml);
		replayMocks();
		RemoteStudy remoteStudy = new RemoteStudy();
		remoteStudy.addStudyTherapy(StudyTherapyType.DEVICE);
		assertEquals(1, remoteStudy.getStudyTherapies().size());
		studyResolver.populateStudyTherapies(studyProtocol, remoteStudy);
		assertEquals(1, remoteStudy.getStudyTherapies().size());
		assertTrue(remoteStudy.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION));
		assertFalse(remoteStudy.hasTherapyOfType(StudyTherapyType.DEVICE));
		verifyMocks();
	}
	
	

	//Will keep the same "Drug" and remove "Device"
	public void testPopulateStudyTherapies_KeepDrugAndRemoveDevice() throws Exception{
		
		//get the xml from file.
		String xml =  IOUtils.toString(getClass().getResourceAsStream("planned_activity_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), (Metadata)EasyMock.anyObject())).andReturn(xml);
		replayMocks();
		RemoteStudy remoteStudy = new RemoteStudy();
		remoteStudy.addStudyTherapy(StudyTherapyType.DEVICE);
		remoteStudy.addStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION);
		
		StudyTherapy drugIntervention = remoteStudy.getStudyTherapies().get(1);
		assertEquals(2, remoteStudy.getStudyTherapies().size());
		
		studyResolver.populateStudyTherapies(studyProtocol, remoteStudy);
		
		assertEquals(1, remoteStudy.getStudyTherapies().size());
		assertTrue(remoteStudy.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION));
		assertFalse(remoteStudy.hasTherapyOfType(StudyTherapyType.DEVICE));
		assertSame(drugIntervention, remoteStudy.getStudyTherapies().get(0));
		
		verifyMocks();
	}
	

	//Will remove everything, as Coppa response has no intervention
	public void testPopulateStudyTherapies_NoInterventionInCoppaResponse() throws Exception{
		
		String xml =  IOUtils.toString(getClass().getResourceAsStream("planned_activity_coppa_no_intervention_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), (Metadata)EasyMock.anyObject())).andReturn(xml);
		replayMocks();
		RemoteStudy remoteStudy = new RemoteStudy();
		remoteStudy.addStudyTherapy(StudyTherapyType.DEVICE);
		remoteStudy.addStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION);
		
		assertEquals(2, remoteStudy.getStudyTherapies().size());
		
		studyResolver.populateStudyTherapies(studyProtocol, remoteStudy);
		
		assertTrue(remoteStudy.getStudyTherapies().isEmpty());
		
		verifyMocks();
	}
	
	
}
