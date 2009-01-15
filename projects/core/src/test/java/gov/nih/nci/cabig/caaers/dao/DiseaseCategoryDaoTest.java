package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.*;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class DiseaseCategoryDaoTest extends DaoTestCase<DiseaseCategoryDao> {

    public void testDomainClass() throws Exception {
        Class<DiseaseCategory> dcc = getDao().domainClass();
        assertNotNull(dcc);
    }

    public void testGetById() throws Exception {
        DiseaseCategory dc = getDao().getById(-1);
        assertNotNull(dc);
        assertEquals("Category One", dc.getName());
    }

    public void testGetAll() throws Exception {
        List all = getDao().getAll();
        assertNotNull(all);
        assertEquals(5, all.size());
    }

    public void testGetBySubname() throws Exception {
        List all = getDao().getBySubname(new String[] {"Category Three"}, null);
        assertNotNull(all);
        assertEquals(1, all.size());
    }

    public void testGetByParentId() throws Exception {
        List all = getDao().getByParentId(-20);
        assertNotNull(all);
        assertEquals(0, all.size());
    }

}