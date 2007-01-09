package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersTestCase.assertNotContains;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CtcDaoTest extends DaoTestCase<CtcDao> {
    public void testGetV3() throws Exception {
        Ctc v3 = getDao().getCtcaeV3();
        assertNotNull("V3 not found", v3);
        Collection<Integer> ids = collectIds(v3.getCategories());
        assertEquals("Wrong number of categories", 4, ids.size());
        assertContains(ids, 301);
        assertContains(ids, 302);
        assertContains(ids, 303);
        assertContains(ids, 304);
    }

    public void testGetV2() throws Exception {
        Ctc v2 = getDao().getCtcV2();
        assertNotNull("V2 not found", v2);
        Collection<Integer> ids = collectIds(v2.getCategories());
        assertEquals("Wrong number of categories", 4, ids.size());
        assertContains(ids, 201);
        assertContains(ids, 202);
        assertContains(ids, 203);
        assertContains(ids, 204);
        assertNotContains(ids, 301);
    }

    public void testCategoryLoaded() throws Exception {
        Ctc v3 = getDao().getById(3);
        CtcCategory firstCat = v3.getCategories().get(0);
        assertEquals("Wrong id", 301, (int) firstCat.getId());
        assertEquals("Wrong name", "allergy/immunology", firstCat.getName());
    }

    public void testTermsAccessible() throws Exception {
        Ctc v3 = getDao().getById(3);
        CtcCategory firstCat = v3.getCategories().get(0);
        assertNotNull("First cat not found", firstCat);
        List<CtcTerm> actualTerms = firstCat.getTerms();
        assertEquals("Wrong number of terms", 3, actualTerms.size());
        assertEquals("Wrong term", 1, (int) actualTerms.get(0).getId());
        assertEquals("Wrong term", 2, (int) actualTerms.get(1).getId());
        assertEquals("Wrong term", 3, (int) actualTerms.get(2).getId());
    }

    // TODO: Copied from PSC's DomainObjectTools.  Put somewhere to share.
    public static Collection<Integer> collectIds(Collection<? extends DomainObject> objs) {
        Set<Integer> ids = new LinkedHashSet<Integer>();
        for (DomainObject obj : objs) ids.add(obj.getId());
        return ids;
    }
}
