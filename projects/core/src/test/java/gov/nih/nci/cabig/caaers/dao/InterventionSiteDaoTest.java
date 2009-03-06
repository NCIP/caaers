package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class InterventionSiteDaoTest extends DaoTestCase<InterventionSiteDao> {

    public void testGetDomainClass() {
        Object obj = getDao().domainClass();
        assertNotNull(obj);
    }

    public void testGetAll() throws Exception {
        List<InterventionSite> all = getDao().getAll();
        assertNotNull(all);
        assertEquals(4, all.size());
    }

    public void testGetBySubnames() throws Exception {
        List<InterventionSite> all = getDao().getBySubname(new String[] {"Test"});
        assertNotNull(all);
        assertEquals(2, all.size());
    }


    public void testGetById() throws Exception {
        InterventionSite loaded = getDao().getById(-1001);
        assertNotNull(loaded);
        assertEquals("Wrong id", -1001, loaded.getId().intValue());
        assertEquals("IS-One", loaded.getName());
    }

}