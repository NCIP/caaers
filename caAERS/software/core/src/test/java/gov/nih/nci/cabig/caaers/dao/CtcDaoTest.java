package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertNotContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
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

    public void testAll() throws Exception {
        List all = getDao().getAll();
        assertEquals(2, all.size());
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

    public void testGetCtcWithCategories() {
        Ctc v3 = getDao().getCtcWithCategories(2);
        assertEquals(4, v3.getCategories().size());
        interruptSession();
        assertEquals(4, v3.getCategories().size());
    }
    
    public void testSave(){
    	Ctc ctc = new Ctc();
    	ctc.setName("Test");
    	getDao().save(ctc);
    	
    	Ctc saved = getDao().getById(ctc.getId());
    	assertEquals("Test", saved.getName());
    }
    
    public void testSaveWithAssociations(){
    	Ctc ctc = new Ctc();
    	ctc.setName("Test ctc");
    	CtcCategory ctcCategory = new CtcCategory();
    	ctcCategory.setName("Test ctc category");
    	CtcTerm ctcTerm = new CtcTerm();
    	ctcTerm.setCtepCode("Test ctep code");
    	ctcTerm.setCtepTerm("Test ctep term");
    	ctcTerm.setDefinition("Test definition");
    	ctcTerm.setSelect("Test select");
    	ctcTerm.setTerm("Test term");
    	CtcGrade ctcGrade = new CtcGrade();
    	ctcGrade.setGrade(Grade.DEATH);
    	ctcGrade.setText("Test text");
    	ctcTerm.addCtcGrade(ctcGrade);
    	ctcCategory.addCtcTerm(ctcTerm);
    	ctc.addCtcCategory(ctcCategory);
    	
    	getDao().save(ctc);
    	interruptSession();
    	Ctc saved = getDao().getById(ctc.getId());
    	assertEquals("Test ctc", saved.getName());
    	assertEquals("Test ctc category", saved.getCategories().get(0).getName());
    	assertEquals("Test ctep code", saved.getCategories().get(0).getTerms().get(0).getCtepCode());
    	assertEquals("Test ctep term", saved.getCategories().get(0).getTerms().get(0).getCtepTerm());
    	assertEquals("Test definition", saved.getCategories().get(0).getTerms().get(0).getDefinition());
    	assertEquals("Test select", saved.getCategories().get(0).getTerms().get(0).getSelect());
    	assertEquals("Test term", saved.getCategories().get(0).getTerms().get(0).getTerm());
    	assertEquals(Grade.DEATH, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getGrade());
    	assertEquals("Test text", saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getText());
    }

}

