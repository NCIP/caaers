package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersTestCase.assertNotContains;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Rhett Sutphin
 */
public class CtcDaoTest extends DaoTestCase<CtcDao> {
    public void testGetV3() throws Exception {
        Ctc v3 = getDao().getCtcaeV3();
        assertNotNull("V3 not found", v3);
        Collection<Integer> ids = collectIds(v3.getCategories());
        assertEquals("Wrong number of categories", 5, ids.size());
        assertContains(ids, 1);
        assertContains(ids, 2);
        assertContains(ids, 3);
        assertContains(ids, 4);
        assertContains(ids, 12);
    }

    public void testGetV2() throws Exception {
        Ctc v2 = getDao().getCtcV2();
        assertNotNull("V2 not found", v2);
        Collection<Integer> ids = collectIds(v2.getCategories());
        assertEquals("Wrong number of categories", 4, ids.size());
        assertContains(ids, 1);
        assertContains(ids, 2);
        assertContains(ids, 3);
        assertContains(ids, 4);
        assertNotContains(ids, 12);
    }

    public void testCategoryLoaded() throws Exception {
        Ctc v3 = getDao().getById(3);
        CtcCategory firstCat = v3.getCategories().get(0);
        assertEquals("Wrong id", 1, (int) firstCat.getId());
        assertEquals("Wrong name", "allergy/immunology", firstCat.getName());
    }

    // TODO: Copied from PSC's DomainObjectTools.  Put somewhere to share.
    public static Collection<Integer> collectIds(Collection<? extends DomainObject> objs) {
        Set<Integer> ids = new LinkedHashSet<Integer>();
        for (DomainObject obj : objs) ids.add(obj.getId());
        return ids;
    }
}
