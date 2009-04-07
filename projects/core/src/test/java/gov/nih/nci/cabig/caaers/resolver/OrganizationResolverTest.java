package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;

import java.util.List;

public class OrganizationResolverTest extends CaaersNoSecurityTestCase{
	
	private OrganizationResolver organizationResolver = 
			(OrganizationResolver)getDeployedApplicationContext().getBean("organizationResolver");
	/*
	 * 
	 */
	public void testGetRemoteEntityByUniqueId() {
		Organization organization = (RemoteOrganization) organizationResolver.getRemoteEntityByUniqueId("CP-RM-TST-ID");
		assertEquals(organization.getName(),"Nairobi Hospital");
		organization = (Organization) organizationResolver.getRemoteEntityByUniqueId("CP-RM-TST-ID2");
		assertEquals(organization.getName().trim(),"Montreal Childrens Hospital Remote 2".trim());
		organization = (Organization) organizationResolver.getRemoteEntityByUniqueId("CP-RM-TST-ID3");
		assertEquals(organization.getName().trim(),"Sydney Cancer Centre Remote 3".trim());		
	}
	
	public void testFind() {
		Organization remoteOrgExample = new RemoteOrganization();
		List<Object> organizations = organizationResolver.find(remoteOrgExample);
		assertEquals(organizations.size(),3);
		//check if return object is of type RemoteOrganization
		Organization obj = (RemoteOrganization)organizations.get(0);		
		
	}
}
