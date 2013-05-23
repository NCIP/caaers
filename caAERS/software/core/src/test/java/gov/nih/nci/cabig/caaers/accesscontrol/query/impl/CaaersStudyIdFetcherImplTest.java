/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
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
public class CaaersStudyIdFetcherImplTest extends AbstractTestCase {

    CaaersStudyIdFetcherImpl fetcher;

    
    public void setUp() throws Exception {
        super.setUp();
        fetcher =  new CaaersStudyIdFetcherImpl();


        fetcher.setCaaersSecurityFacade(new CaaersSecurityFacadeTestAdapter(){
            @Override
            public List<IndexEntry> getAccessibleStudyIds(String loginId) {
                List<IndexEntry> entries =  super.getAccessibleStudyIds(loginId);
                
                IndexEntry e1 = new IndexEntry(Integer.MIN_VALUE,0);
                e1.addRole(UserGroupType.ae_reporter);
                e1.addRole(UserGroupType.data_analyst);
                entries.add(e1);
                
                IndexEntry e = new IndexEntry(-2,0);
                e.addRole(UserGroupType.ae_expedited_report_reviewer);
                e.addRole(UserGroupType.caaers_central_office_sae_cd);
                
                
                entries.add(e);
                return entries;
            }
        });

    }

    public void testFetch(){
        List<IndexEntry> entries = fetcher.fetch("1000@def.com");
        assertEquals(2, entries.size());
    }


}
