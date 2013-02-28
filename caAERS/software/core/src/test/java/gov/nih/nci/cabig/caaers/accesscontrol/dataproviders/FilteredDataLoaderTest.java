/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.CaaersOrganizationIdFetcherImpl;
import gov.nih.nci.cabig.caaers.dao.index.OrganizationIndexDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.context.SecurityContextHolder;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class FilteredDataLoaderTest extends AbstractTestCase {
    FilteredDataLoader loader;
    List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
    LinkedHashMap idIndexDaoMap = new LinkedHashMap();

    CaaersOrganizationIdFetcherImpl orgIdFetcher;
    OrganizationIndexDao orgIndexDao;

    public void setUp() throws Exception {
        loader = new FilteredDataLoader();
        loader.setIdFetchers(idFetchers);
        loader.setIdFetcherIndexDaoMap(idIndexDaoMap);

        orgIndexDao = registerMockFor(OrganizationIndexDao.class);
        orgIdFetcher = registerMockFor(CaaersOrganizationIdFetcherImpl.class);
        idIndexDaoMap.put(orgIdFetcher, orgIndexDao);
        idFetchers.add(orgIdFetcher);
        SecurityContextHolder.getContext().setAuthentication(null);
        switchToSuperUser();
    }

    public void testUpdateIndexByUserName() throws Exception {

        IndexEntry i0 = new IndexEntry(Integer.MIN_VALUE);
        i0.addRole(UserGroupType.business_administrator);
        i0.addRole(UserGroupType.ae_reporter);
        

        IndexEntry i1 = new IndexEntry(2);
        i1.addRole(UserGroupType.ae_study_data_reviewer);
        List<IndexEntry> iList = new ArrayList<IndexEntry>();
        iList.add(i0);
        iList.add(i1);
        
        EasyMock.expect(orgIdFetcher.fetch("SYSTEM_ADMIN")).andReturn(iList);
        orgIndexDao.updateIndex("SYSTEM_ADMIN", iList);
        EasyMock.expectLastCall().anyTimes();
        
        replayMocks();
        
        loader.updateIndexByUserName(SecurityUtils.getAuthentication());
        verifyMocks();
        
    }
    public void testUpdateIndexByUserNameEmptyFetchers() throws Exception {
        replayMocks();
        idFetchers.clear();
        loader.updateIndexByUserName(SecurityUtils.getAuthentication());
        verifyMocks();

    }


}
