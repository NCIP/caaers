package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import java.util.List;

public class InvestigatorResolverTest extends CaaersNoSecurityTestCase{
	
	public void testMethod(){
		
	}

//	private InvestigatorResolver investigatorResolver = 
//		(InvestigatorResolver)getDeployedApplicationContext().getBean("investigatorResolver");
//
//
//	public void testGetRemoteEntityByUniqueId() {
//		Investigator investigator = (RemoteInvestigator) investigatorResolver.getRemoteEntityByUniqueId("HCP_109");
//		assertEquals(investigator.getFirstName(),"micheal");
//		investigator = (RemoteInvestigator) investigatorResolver.getRemoteEntityByUniqueId("HCP_101");
//		assertEquals(investigator.getFirstName(),"javier");
//		investigator = (RemoteInvestigator) investigatorResolver.getRemoteEntityByUniqueId("HCP_103");
//		assertEquals(investigator.getFirstName(),"pen");
//	}
//	
//	public void testFind() {
//		Investigator example = new RemoteInvestigator();
//		Organization org = new RemoteOrganization();
//		org.setNciInstituteCode("RM-TST-ID2");
//		SiteInvestigator si = new SiteInvestigator();
//		si.setOrganization(org);
//		example.getSiteInvestigators().add(si);
//		List<Object> list = investigatorResolver.find(example);
//		assertEquals(list.size(),3);
//
//		for (int i=0 ; i<list.size(); i++) {
//			Investigator obj = (RemoteInvestigator)list.get(i);
//			System.out.println(obj.getFirstName() + "," + obj.getLastName());
//			System.out.println(obj.getEmailAddress());
//			System.out.println(obj.getExternalId());
//		}
//		
//	}
//	
//	public void testGetAll() {
//		Investigator example = new RemoteInvestigator();
//		List<Object> list = investigatorResolver.find(example);
//		assertEquals(list.size(),9);
//
//		for (int i=0 ; i<list.size(); i++) {
//			Investigator obj = (RemoteInvestigator)list.get(i);
//			System.out.println(obj.getFirstName() + "," + obj.getLastName());
//			System.out.println(obj.getEmailAddress());
//			System.out.println(obj.getExternalId());
//		}
//		
//	}
	

}
