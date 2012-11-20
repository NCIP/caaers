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
                IndexEntry allSites = new IndexEntry(Integer.MIN_VALUE);
                allSites.addRole(UserGroupType.person_and_organization_information_manager);

                entries.add(allSites) ;
                IndexEntry i_1001 = new IndexEntry(-1001);
                i_1001.addRole(UserGroupType.ae_reporter);

                entries.add(i_1001);
                IndexEntry i_1002 = new IndexEntry(-1002);
                i_1002.addRole(UserGroupType.ae_reporter);
                entries.add(i_1002);

                IndexEntry i_1003 = new IndexEntry(-1003);
                i_1003.addRole(UserGroupType.business_administrator);
                entries.add(i_1003);
                IndexEntry i_1004 = new IndexEntry(-1004);
                i_1004.addRole(UserGroupType.business_administrator);
                entries.add(i_1004);


                return entries;
            }

            @Override
            public List<IndexEntry> getAccessibleStudyIds(String loginId) {
                List<IndexEntry> entries =  super.getAccessibleStudyIds(loginId);
                IndexEntry e = new IndexEntry(-2);
                e.addRole(UserGroupType.ae_reporter);
                entries.add(e);
                return entries;
            }
        });

    }

    public void testFetch(){
        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertEquals(6, entries.size());
    }

    public void tearDown() throws Exception {
        fetcher.setCaaersSecurityFacade(facade);
        super.tearDown();
    }

}
