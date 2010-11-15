package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeTestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * CaaersOrganizationIdFetcherImpl Tester.
 *
 * @author Biju Joseph
 * @since <pre>11/11/2010</pre>
 * 
 */
public class CaaersOrganizationIdFetcherImplTest extends CaaersDbNoSecurityTestCase {

    CaaersOrganizationIdFetcherImpl fetcher;

    CaaersSecurityFacade facade;
    
    public void setUp() throws Exception {
        super.setUp();
        fetcher = (CaaersOrganizationIdFetcherImpl) getDeployedApplicationContext().getBean("organizationIdFetcher");
        facade = fetcher.getCaaersSecurityFacade();

        fetcher.setCaaersSecurityFacade(new CaaersSecurityFacadeTestAdapter(){
            @Override
            public List<IndexEntry> getAccessibleOrganizationIds(String loginId) {
                List<IndexEntry> entries = new ArrayList<IndexEntry>();
                for(UserGroupType ug : UserGroupType.values()){
                   IndexEntry e = new IndexEntry(ug);
                   entries.add(e);

                   if(ug == UserGroupType.person_and_organization_information_manager){
                       e.getEntityIds().add(Integer.MIN_VALUE);
                   }

                   if(ug == UserGroupType.ae_reporter){
                      e.getEntityIds().add(-1001);
                      e.getEntityIds().add(-1002); 
                   }

                   if(ug == UserGroupType.business_administrator){
                      e.getEntityIds().add(-1003);
                      e.getEntityIds().add(-1004); 
                   }

                }
                return entries;
            }
        });

    }

    public void testFetch(){

        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertTrue(entries.get(8).getEntityIds().isEmpty());
        assertTrue(entries.get(8).getRole() == UserGroupType.system_administrator);

        assertTrue(entries.get(9).getEntityIds().get(0).equals(-1003));
        assertTrue(entries.get(9).getEntityIds().get(1).equals(-1004));
        assertTrue(entries.get(9).getRole() == UserGroupType.business_administrator);

        assertTrue(entries.get(10).getEntityIds().get(0).equals(-999));
        assertTrue(entries.get(10).getEntityIds().get(1).equals(-1001));
        assertTrue(entries.get(10).getRole() == UserGroupType.person_and_organization_information_manager);

        assertTrue(entries.get(24).getEntityIds().contains(-1002));
        assertTrue(entries.get(24).getEntityIds().contains(-1001));
        assertTrue(entries.get(24).getEntityIds().contains(-1003));
        assertTrue(entries.get(24).getRole() == UserGroupType.ae_reporter);

    }

    public void tearDown() throws Exception {
        fetcher.setCaaersSecurityFacade(facade);
        super.tearDown();
    }

}
