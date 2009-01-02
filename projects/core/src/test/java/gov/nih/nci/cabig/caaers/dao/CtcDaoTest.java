package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertNotContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObjectTools;

import java.util.Collection;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class CtcDaoTest extends DaoTestCase<CtcDao> {
    public void testGetV3() throws Exception {
        Ctc v3 = getDao().getCtcaeV3();
        assertNotNull("V3 not found", v3);
        Collection<Integer> ids = DomainObjectTools.collectIds(v3.getCategories());
        assertEquals("Wrong number of categories", 4, ids.size());
        assertContains(ids, 301);
        assertContains(ids, 302);
        assertContains(ids, 303);
        assertContains(ids, 304);
    }

    public void testGetV2() throws Exception {
        Ctc v2 = getDao().getCtcV2();
        assertNotNull("V2 not found", v2);
        Collection<Integer> ids = DomainObjectTools.collectIds(v2.getCategories());
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

}

