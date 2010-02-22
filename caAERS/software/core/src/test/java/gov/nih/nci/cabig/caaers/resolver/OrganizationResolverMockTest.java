package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.MetadataMatcher;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;

public class OrganizationResolverMockTest extends AbstractTestCase {
	
	OrganizationResolver organizationResolver ;
	MessageBroadcastService messageBroadcastService;
	//ResolverUtils resolverUtils;
	 
	protected void setUp() throws Exception {
		super.setUp();
		
		organizationResolver = new OrganizationResolver();
		messageBroadcastService = registerMockFor(MessageBroadcastService.class);
		//investigatorResolver.setResolverUtils(resolverUtils);
		organizationResolver.setMessageBroadcastService(messageBroadcastService);
	}
	
	public void testSearchByNciId() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("organization_correlation_coppa_response.xml"));
		Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
			
		replayMocks();
		Organization example = new RemoteOrganization();
		example.setNciInstituteCode("HIVNCI");
		List<Object> list = organizationResolver.find(example);
		assertEquals(1,list.size());
		Organization orgResult = (Organization)list.get(0);
		assertEquals("HIVNCI",orgResult.getNciInstituteCode());
		assertEquals("National Cancer Institute HIV/AIDS Malignancy Branch",orgResult.getName());
		
	}

	public void testSearchByName() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("organization_correlation_coppa_response.xml"));
		Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
			
		replayMocks();
		Organization example = new RemoteOrganization();
		example.setName("National Cancer Institute HIV/AIDS Malignancy Branch");
		List<Object> list = organizationResolver.find(example);
		assertEquals(1,list.size());
		Organization orgResult = (Organization)list.get(0);
		assertEquals("HIVNCI",orgResult.getNciInstituteCode());
		assertEquals("National Cancer Institute HIV/AIDS Malignancy Branch",orgResult.getName());
		
	}
	
	public void testSearchByExternalId() throws Exception {
		String xml =  IOUtils.toString(getClass().getResourceAsStream("organization_correlation_coppa_response.xml"));
		Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
		EasyMock.expect(messageBroadcastService.broadcastCOPPA((List<String>)EasyMock.anyObject(), MetadataMatcher.eqMetadata(mData))).andReturn(xml);
			
		replayMocks();
		Organization orgResult = (Organization)organizationResolver.getRemoteEntityByUniqueId("13169");
		assertEquals("HIVNCI",orgResult.getNciInstituteCode());
		assertEquals("National Cancer Institute HIV/AIDS Malignancy Branch",orgResult.getName());
		
	}
	
}
