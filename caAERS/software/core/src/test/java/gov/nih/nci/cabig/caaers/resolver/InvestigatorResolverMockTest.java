package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.MetadataMatcher;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;

public class InvestigatorResolverMockTest extends AbstractTestCase {
	
	InvestigatorResolver investigatorResolver ;
	MessageBroadcastService messageBroadcastService;
	//ResolverUtils resolverUtils;
	 
	protected void setUp() throws Exception {
		super.setUp();
		
		investigatorResolver = new InvestigatorResolver();
		messageBroadcastService = registerMockFor(MessageBroadcastService.class);
		//investigatorResolver.setResolverUtils(resolverUtils);
		investigatorResolver.setMessageBroadcastService(messageBroadcastService);
	}
	
	public void testSearchByName() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("inv_byname_correlation_coppa_response.xml"));
		Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_ORGANIZATION");
		xml =  IOUtils.toString(getClass().getResourceAsStream("byname_identifiedorg_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_PERSON");
		xml =  IOUtils.toString(getClass().getResourceAsStream("byname_identifiedperson_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		replayMocks();
		Investigator example = new RemoteInvestigator();
		example.setFirstName("abbas");
		List<Object> list = investigatorResolver.find(example);
		assertEquals(4,list.size());
		
	}
	
	
	public void testSearchByNciId() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("bynciid_identifiedperson_coppa_response.xml"));
		Metadata mData = new Metadata("search",  "externalId", "IDENTIFIED_PERSON");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		xml =  IOUtils.toString(getClass().getResourceAsStream("rs_bynciid_correlation_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_ORGANIZATION");
		xml =  IOUtils.toString(getClass().getResourceAsStream("bynciid_identifiedorg_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);

		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_PERSON");
		xml =  IOUtils.toString(getClass().getResourceAsStream("bynciid_identifiedperson_byplids_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		replayMocks();
		Investigator example = new RemoteInvestigator();
		example.setNciIdentifier("60442");
		List<Object> list = investigatorResolver.find(example);
		assertEquals(list.size(),1);	
	}
	
	public void testSearchByOrganization() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("byorg_identifiedorg_coppa_response.xml"));
		Metadata mData = new Metadata("search",  "externalId", "IDENTIFIED_ORGANIZATION");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		xml =  IOUtils.toString(getClass().getResourceAsStream("inv_byorg_correlation_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_PERSON");
		xml =  IOUtils.toString(getClass().getResourceAsStream("byorg_identifiedperson_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		replayMocks();
		Investigator example = new RemoteInvestigator();
		SiteInvestigator si = new SiteInvestigator();
		Organization org = new RemoteOrganization();
		org.setNciInstituteCode("KY082");
		si.setOrganization(org);
		example.getSiteInvestigators().add(si);
		List<Object> list = investigatorResolver.find(example);
		assertEquals(list.size(),3);
		
	}

	public void testSearchByExternalId() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("inv_byextid_correlation_coppa_response.xml"));
		Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_ORGANIZATION");
		xml =  IOUtils.toString(getClass().getResourceAsStream("byextid_identifiedorg_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		mData = new Metadata("getByPlayerIds",  "externalId", "IDENTIFIED_PERSON");
		xml =  IOUtils.toString(getClass().getResourceAsStream("byextid_identifiedperson_coppa_response.xml"));
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
		
		replayMocks();
		Object obj1 = investigatorResolver.getRemoteEntityByUniqueId("1167533");
		Investigator obj = (RemoteInvestigator)obj1;
		assertEquals(obj.getExternalId(),"1167533");	
		
	}
	

}
