package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.CaaersOrganizationIdFetcherImpl;
import gov.nih.nci.cabig.caaers.dao.index.OrganizationIndexDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
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
        switchToSuperUser();
    }

    public void testUpdateIndexByUserName() throws Exception {

        IndexEntry i0 = new IndexEntry(UserGroupType.ae_reporter);
        i0.addEntityId(1);
        i0.addEntityId(0);
        List<IndexEntry> iListExisting = new ArrayList<IndexEntry>();
        iListExisting.add(i0);

        IndexEntry i1 = new IndexEntry(UserGroupType.ae_reporter);
        i1.addEntityId(1);
        i1.addEntityId(2);
        List<IndexEntry> iList = new ArrayList<IndexEntry>();
        iList.add(i1);
        EasyMock.expect(orgIdFetcher.fetch("SYSTEM")).andReturn(iList);
        EasyMock.expect(orgIndexDao.queryAllIndexEntries("SYSTEM")).andReturn(iListExisting);
        orgIndexDao.updateIndex(EasyMock.eq("SYSTEM"), EasyMock.eq(UserGroupType.ae_reporter.getCode()), EasyMock.eq(i1), EasyMock.eq(i0));
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
