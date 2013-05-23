/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

                IndexEntry i_1001 = new IndexEntry(-999);
                i_1001.addRole(UserGroupType.ae_reporter);
                i_1001.addRole(UserGroupType.data_reader);
                
                IndexEntry i_1002 = new IndexEntry(-1001);
                i_1002.addRole(UserGroupType.ae_reporter);
                
                entries.add(i_1001);
                entries.add(i_1002);

                return entries;
            }

            @Override
            public List<IndexEntry> getAccessibleStudyIds(String loginId) {
                List<IndexEntry> entries =  super.getAccessibleStudyIds(loginId);
                IndexEntry e = new IndexEntry(-1);
                e.addRole(UserGroupType.ae_reporter);
                
                IndexEntry e1 = new IndexEntry(-4);
                e1.addRole(UserGroupType.ae_reporter);
                e1.addRole(UserGroupType.data_reader);
                
                IndexEntry e3 = new IndexEntry(-5);
                e3.addRole(UserGroupType.data_reader);
                
                entries.add(e);
                entries.add(e1);
                entries.add(e3);
                
                return entries;
            }
        });

    }

    public void testFetch(){
        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertEquals(2, entries.size());
    }

    public void tearDown() throws Exception {
       fetcher.setCaaersSecurityFacade(facade);
        super.tearDown();
    }

}
