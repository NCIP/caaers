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

            @Override
            public List<IndexEntry> getAccessibleStudyIds(String loginId) {
                List<IndexEntry> entries =  super.getAccessibleStudyIds(loginId);
                IndexEntry e = new IndexEntry(UserGroupType.ae_reporter);
                e.addEntityId(-2);
                entries.add(e);
                return entries;
            }
        });

    }

    public void testFetch(){
        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertEquals(32, entries.size());
    }

    public void tearDown() throws Exception {
        fetcher.setCaaersSecurityFacade(facade);
        super.tearDown();
    }

}
