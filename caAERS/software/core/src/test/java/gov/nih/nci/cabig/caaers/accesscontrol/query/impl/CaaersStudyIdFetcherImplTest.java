package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeTestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * CaaersStudyIdFetcherImpl Tester.
 *
 * @author medav
 * 
 */
public class CaaersStudyIdFetcherImplTest extends CaaersDbNoSecurityTestCase {

    CaaersStudyIdFetcherImpl fetcher;

    CaaersSecurityFacade facade;
    
    public void setUp() throws Exception {
        super.setUp();
        fetcher = (CaaersStudyIdFetcherImpl) getDeployedApplicationContext().getBean("studyIdFetcher");
        facade = fetcher.getCaaersSecurityFacade();

        fetcher.setCaaersSecurityFacade(new CaaersSecurityFacadeTestAdapter(){
            @Override
            public List<IndexEntry> getAccessibleStudyIds(String loginId) {
                List<IndexEntry> entries =  super.getAccessibleStudyIds(loginId);
                
                IndexEntry e1 = new IndexEntry(Integer.MIN_VALUE);
                e1.addRole(UserGroupType.ae_reporter);
                e1.addRole(UserGroupType.data_analyst);
                entries.add(e1);
                
                IndexEntry e = new IndexEntry(-2);
                e.addRole(UserGroupType.ae_reporter);
                e.addRole(UserGroupType.data_analyst);
                
                
                entries.add(e);
                return entries;
            }
        });

    }

    public void testFetch(){
        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertEquals(1, entries.size());
    }

    public void tearDown() throws Exception {
        fetcher.setCaaersSecurityFacade(facade);
        super.tearDown();
    }

}
