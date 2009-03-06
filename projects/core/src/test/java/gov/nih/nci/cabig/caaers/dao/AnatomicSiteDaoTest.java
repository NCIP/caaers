package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class AnatomicSiteDaoTest extends DaoTestCase<AnatomicSiteDao> {

    public void testGetDomainClass() {
        Object obj = getDao().domainClass();
        assertNotNull(obj);
    }

    public void testGetAll() throws Exception {
        List<AnatomicSite> all = getDao().getAll();
        assertNotNull(all);
        assertEquals(5, all.size());
        assertEquals("AS-04-Test", all.get(3).getName());
    }

    public void testGetById() throws Exception {
        AnatomicSite loaded = getDao().getById(-14);
        assertNotNull(loaded);
        assertEquals("Wrong id", -14, loaded.getId().intValue());
        assertEquals("C-02", loaded.getCategory());
    }

    public void testGetBySubnames() throws Exception {
        List<AnatomicSite> all = getDao().getBySubnames(new String[] {"Test"});
        assertNotNull(all);
        assertEquals(2, all.size());
    }

}