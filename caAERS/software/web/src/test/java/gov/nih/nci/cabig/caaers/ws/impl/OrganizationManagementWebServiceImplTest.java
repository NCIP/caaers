/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.organization.Organizations;

public class OrganizationManagementWebServiceImplTest extends CaaersTestCase{
	
	private OrganizationManagementWebServiceImpl organizationManagementWebServiceImpl;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		organizationManagementWebServiceImpl = (OrganizationManagementWebServiceImpl) applicationContext.getBean("organizationManagementWebServiceBean");
	}
	public void testMergeOrganization() throws Exception {
		
		Organizations xmlOrganizations = new Organizations();
		OrganizationType organization1 = new OrganizationType();
		organization1.setName("Duke University");
		organization1.setNciInstituteCode("NC010");
		
		// merged organization
		OrganizationType mergedOrg = new OrganizationType();
		mergedOrg.setName("Duke University Center");
		mergedOrg.setNciInstituteCode("NC011");
		mergedOrg.setStatus("Active");
		mergedOrg.setCity("Raleigh");
		xmlOrganizations.getOrganization().add(organization1);
		
		organization1.setMergedOrganization(mergedOrg);
		CaaersServiceResponse caaersServiceResponse = organizationManagementWebServiceImpl.mergeOrganization(xmlOrganizations);
		
		assertTrue(caaersServiceResponse.getServiceResponse().getWsError().isEmpty());
	}
}
