package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class DiseaseTermDaoTest extends DaoTestCase<DiseaseTermDao> {

    public void testGetById() throws Exception {
        DiseaseTerm dt = getDao().getById(-1);
        assertNotNull(dt);
        assertEquals("Term One", dt.getFullName());
    }

    public void testGetAll() throws Exception {
        List all = getDao().getAll();
        assertNotNull(all);
        assertEquals(3, all.size());
    }

    public void testGetByCategoryId() throws Exception {
        List all = getDao().getByCategoryId(-5);
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    public void testGetByTermName() throws Exception {
        DiseaseTerm dt = getDao().getByTermName("Term Two");
        assertNotNull(dt);
        assertEquals(-2, dt.getId().intValue());
    }

    public void testGetByCTEPTermName() throws Exception {
        DiseaseTerm dt = getDao().getByCTEPTermName("CTEP Term name 01");
        assertNotNull(dt);
        assertEquals(-1, dt.getId().intValue());
    }

    public void testGetByMeddra() throws Exception {
        DiseaseTerm dt = getDao().getByMeddra("medra code three");
        assertNotNull(dt);
        assertEquals(-3, dt.getId().intValue());
    }

}