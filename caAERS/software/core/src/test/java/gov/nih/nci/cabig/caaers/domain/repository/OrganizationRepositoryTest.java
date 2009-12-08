package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class OrganizationRepositoryTest extends DaoNoSecurityTestCase<OrganizationDao> {
	
	private OrganizationRepository organizationRepository;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		organizationRepository = (OrganizationRepository)getDeployedApplicationContext().getBean("organizationRepository");
	}
	
	public void testCreate(){
		Organization organization = new LocalOrganization();
		organization.setName("New Organization");
		String nciCode1 = String.format("A%d", System.currentTimeMillis());
		organization.setNciInstituteCode(nciCode1);
		organization.setCity("city");
		organization.setCountry("country");
		organizationRepository.create(organization);
		interruptSession();
		OrganizationQuery query = new OrganizationQuery();
		List<Organization> list = organizationRepository.searchOrganization(query);
		assertNotNull(list);
		assertEquals(6, list.size());
	}
	
	public void testCreateOrUpdate(){
		OrganizationQuery query = new OrganizationQuery();
		List<Organization> list = organizationRepository.searchOrganization(query);
		organizationRepository.createOrUpdate(list.get(0));
		assertNotNull(list);
		assertEquals(5, list.size());
	}
	
	public void testRestrictBySubnames(){
		String[] subnames = {"New Site"};
		List<Organization> list = organizationRepository.restrictBySubnames(subnames);
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	public void testSearchOrganization(){
		OrganizationQuery query = new OrganizationQuery();
		List<Organization> list = organizationRepository.searchOrganization(query);
		assertNotNull(list);
		assertEquals(5, list.size());
	}
	
	public void testSearch(){
		OrganizationRepository organizationRepository = (OrganizationRepository)this.getDeployedApplicationContext().getBean("organizationRepository");
		OrganizationQuery query = new OrganizationQuery();
		query.filterByOrganizationName("National Cancer Institute B");
		//query.filterByNciInstituteCode("NCI");
		//query.getParameterMap();
		List<Organization> organizations = organizationRepository.searchOrganization(query);
		
		for(Object org : organizations){
            try {
            	Organization obj = (Organization)org;
            	System.out.println(obj.getName());
            	System.out.println(obj.getCity());
            	System.out.println(obj.getCountry());
            	System.out.println(obj.getNciInstituteCode());
            	System.out.println(obj.getExternalId());
            	System.out.println(org.getClass().getName());
                //deserialize coppa payload from caXchange response xml and get coppa object
                //objList.add(remoteHealthcareSiteResolver.deSerializeFromCoppa(me.getAsString()));
            } catch (Exception e) {
         
                e.printStackTrace();
            }
        }
		
	}

}
