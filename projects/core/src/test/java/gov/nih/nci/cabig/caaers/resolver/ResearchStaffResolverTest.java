package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

public class ResearchStaffResolverTest extends CaaersNoSecurityTestCase{
	
	private ResearchStaffResolver researchStaffResolver = 
			(ResearchStaffResolver)getDeployedApplicationContext().getBean("researchStaffResolver");
	
	
	public void testGetRemoteEntityByUniqueId() {
		ResearchStaff researchStaff = (RemoteResearchStaff) researchStaffResolver.getRemoteEntityByUniqueId("CP_109");
		assertEquals(researchStaff.getFirstName(),"Artie");
		researchStaff = (RemoteResearchStaff) researchStaffResolver.getRemoteEntityByUniqueId("CP_101");
		assertEquals(researchStaff.getFirstName(),"Donald");
		researchStaff = (RemoteResearchStaff) researchStaffResolver.getRemoteEntityByUniqueId("CP_103");
		assertEquals(researchStaff.getFirstName(),"Serge");
	}
	
	public void testFind() {
		ResearchStaff researchStaffExample = new RemoteResearchStaff();
		Organization org = new RemoteOrganization();
		org.setNciInstituteCode("RM-TST-ID3");
		researchStaffExample.setOrganization(org);
		List<Object> researchStaffs = researchStaffResolver.find(researchStaffExample);
		assertEquals(researchStaffs.size(),3);

		for (int i=0 ; i<researchStaffs.size(); i++) {
			ResearchStaff obj = (RemoteResearchStaff)researchStaffs.get(i);
			System.out.println(obj.getFirstName() + "," + obj.getLastName());
			System.out.println(obj.getEmailAddress());
			System.out.println(obj.getExternalId());
		}
		
	}
	
	public void testGetAll() {
		ResearchStaff researchStaffExample = new RemoteResearchStaff();
		List<Object> researchStaffs = researchStaffResolver.find(researchStaffExample);
		assertEquals(researchStaffs.size(),9);

		for (int i=0 ; i<researchStaffs.size(); i++) {
			ResearchStaff obj = (RemoteResearchStaff)researchStaffs.get(i);
			System.out.println(obj.getFirstName() + "," + obj.getLastName());
			System.out.println(obj.getEmailAddress());
			System.out.println(obj.getExternalId());
		}
		
	}

}
