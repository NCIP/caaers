package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.api.CtcService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcCategoryType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcGradeType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcVersionType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.Ctcs;

import java.util.List;

public class CtcServiceImplTest extends DaoTestCase {
	private CtcDao ctcDao;
	private CtcService ctcService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctcDao = (CtcDao)getDeployedApplicationContext().getBean("ctcDao");
		CtcServiceImpl ctcServiceImpl = new CtcServiceImpl();
		ctcServiceImpl.setCtcDao(ctcDao);
		ctcService = ctcServiceImpl;
	}
	
	public void testUpdateCtcVersionType(){
//		interruptSession();
		List<EntityProcessingOutcomeType> processingOutcomes = ctcService.createOrUpdateCtc(buildUpdateCtcVersionType()).getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome();
		interruptSession();
		assertEquals(10, processingOutcomes.size());
		EntityProcessingOutcomeType processingOutcome = processingOutcomes.get(0);
		assertEquals("ctc1", processingOutcome.getBusinessIdentifier());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("ctccategory1", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(2);
		assertEquals("ctepcode1", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(3);
		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessIdentifier());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(4);
		assertEquals("ctepcode2", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(5);
		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessIdentifier());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(6);
		assertEquals(Grade.MODERATE.getDisplayName(), processingOutcome.getBusinessIdentifier());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(7);
		assertEquals("ctccategory2", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(8);
		assertEquals("ctepcode3", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(9);
		assertEquals(Grade.SEVERE.getDisplayName(), processingOutcome.getBusinessIdentifier());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		
		Ctc saved = ctcDao.getByName("ctc1");
		assertEquals("ctc1", saved.getName());
		assertEquals(2, saved.getCategories().size());
    	assertEquals("ctccategory1", saved.getCategories().get(0).getName());
    	assertEquals(2, saved.getCategories().get(0).getTerms().size());
    	assertEquals("ctepcode1", saved.getCategories().get(0).getTerms().get(0).getCtepCode());
    	assertEquals(1, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().size());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getSelect());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getDefinition());
    	assertEquals("Test ctep term Other, Specify", saved.getCategories().get(0).getTerms().get(0).getCtepTerm());
    	assertEquals("Test ctep term Other, Specify", saved.getCategories().get(0).getTerms().get(0).getTerm());
    	assertTrue(saved.getCategories().get(0).getTerms().get(0).isOtherRequired());
    	assertEquals(Grade.DEATH, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getGrade());
    	assertEquals("grade1", saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getText());
	}
	
	private Ctcs buildNewCtcVersionType(){
		Ctcs ctcs = new Ctcs();
		CtcVersionType ctc1 = new CtcVersionType();
		ctcs.getCtc().add(ctc1);
    	ctc1.setName("ctc");
    	
    	CtcCategoryType ctcCategory1 = new CtcCategoryType();
    	ctcCategory1.setName("ctccategory");
    	ctc1.getCtcCategory().add(ctcCategory1);
    	CtcTermType ctcTerm1 = new CtcTermType();
    	ctcCategory1.getCtcTerm().add(ctcTerm1);
    	ctcTerm1.setCtepCode("ctepcode");
    	ctcTerm1.setCtepTerm("Test ctep term 0");
    	ctcTerm1.setTerm("Test ctep term 0");
    	CtcGradeType ctcGrade1 = new CtcGradeType();
    	ctcGrade1.setGrade(5);
    	ctcGrade1.setText("grade");
    	ctcTerm1.getCtcGrade().add(ctcGrade1);
    	
    	return ctcs;
	}
	
	public void testNewCtc(){
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(buildNewCtc());
		interruptSession();
		assertEquals(4, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("ctc", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("ctccategory", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(2);
		assertEquals("ctepcode", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(3);
		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessId());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		Ctc saved = ctcDao.getByName("ctc");
		assertEquals("ctc", saved.getName());
    	assertEquals("ctccategory", saved.getCategories().get(0).getName());
    	assertEquals("ctepcode", saved.getCategories().get(0).getTerms().get(0).getCtepCode());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getSelect());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getDefinition());
    	assertEquals("Test ctep term 0", saved.getCategories().get(0).getTerms().get(0).getCtepTerm());
    	assertEquals("Test ctep term 0", saved.getCategories().get(0).getTerms().get(0).getTerm());
    	assertFalse(saved.getCategories().get(0).getTerms().get(0).isOtherRequired());
    	assertEquals(Grade.DEATH, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getGrade());
    	assertEquals("grade", saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getText());
	}
	
//	public void testUpdateCtc(){
//		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(buildUpdateCtc());
//		interruptSession();
//		assertEquals(10, processingOutcomes.size());
//		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
//		assertEquals("ctc1", processingOutcome.getBusinessId());
//		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Updated", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(1);
//		assertEquals("ctccategory1", processingOutcome.getBusinessId());
//		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Updated", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(2);
//		assertEquals("ctepcode1", processingOutcome.getBusinessId());
//		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Updated", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(3);
//		assertEquals(Grade.MILD.getDisplayName(), processingOutcome.getBusinessId());
//		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(4);
//		assertEquals("ctepcode2", processingOutcome.getBusinessId());
//		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(5);
//		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessId());
//		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(6);
//		assertEquals(Grade.MODERATE.getDisplayName(), processingOutcome.getBusinessId());
//		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(7);
//		assertEquals("ctccategory2", processingOutcome.getBusinessId());
//		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(8);
//		assertEquals("ctepcode3", processingOutcome.getBusinessId());
//		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		processingOutcome = processingOutcomes.get(9);
//		assertEquals(Grade.SEVERE.getDisplayName(), processingOutcome.getBusinessId());
//		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
//		assertFalse(processingOutcome.isFailed());
//		assertEquals("Added", processingOutcome.getMessages().get(0));
//		
//		Ctc saved = ctcDao.getByName("ctc1");
//		assertEquals("ctc1", saved.getName());
//		assertEquals(2, saved.getCategories().size());
//    	assertEquals("ctccategory1", saved.getCategories().get(0).getName());
//    	assertEquals(2, saved.getCategories().get(0).getTerms().size());
//    	assertEquals("ctepcode1", saved.getCategories().get(0).getTerms().get(0).getCtepCode());
//    	assertEquals(1, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().size());
//    	assertNull(saved.getCategories().get(0).getTerms().get(0).getSelect());
//    	assertNull(saved.getCategories().get(0).getTerms().get(0).getDefinition());
//    	assertEquals("Test ctep term Other, Specify", saved.getCategories().get(0).getTerms().get(0).getCtepTerm());
//    	assertEquals("Test ctep term Other, Specify", saved.getCategories().get(0).getTerms().get(0).getTerm());
//    	assertTrue(saved.getCategories().get(0).getTerms().get(0).isOtherRequired());
//    	assertEquals(Grade.MILD, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getGrade());
//    	assertEquals("grade1", saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getText());
//	}
	
	private Ctc buildNewCtc(){
		Ctc ctc1 = new Ctc();
    	ctc1.setName("ctc");
    	
    	CtcCategory ctcCategory1 = new CtcCategory();
    	ctcCategory1.setName("ctccategory");
    	ctc1.addCtcCategory(ctcCategory1);
    	CtcTerm ctcTerm1 = new CtcTerm();
    	ctcCategory1.addCtcTerm(ctcTerm1);
    	ctcTerm1.setCtepCode("ctepcode");
    	ctcTerm1.setCtepTerm("Test ctep term 0");
    	ctcTerm1.setTerm("Test ctep term 0");
    	CtcGrade ctcGrade1 = new CtcGrade();
    	ctcGrade1.setGrade(Grade.DEATH);
    	ctcGrade1.setText("grade");
    	ctcTerm1.addCtcGrade(ctcGrade1);
    	
    	return ctc1;
	}
	
	private Ctc buildUpdateCtc(){
		Ctc ctc1 = new Ctc();
    	ctc1.setName("ctc1");
    	
    	CtcCategory ctcCategory1 = new CtcCategory();
    	ctcCategory1.setName("ctccategory1");
    	ctc1.addCtcCategory(ctcCategory1);
    	CtcTerm ctcTerm1 = new CtcTerm();
    	ctcCategory1.addCtcTerm(ctcTerm1);
    	ctcTerm1.setCtepCode("ctepcode1");
    	ctcTerm1.setCtepTerm("Test ctep term Other, Specify");
    	ctcTerm1.setTerm("Test ctep term Other, Specify");
    	CtcGrade ctcGrade1 = new CtcGrade();
    	ctcGrade1.setGrade(Grade.MILD);
    	ctcGrade1.setText("grade1");
    	ctcTerm1.addCtcGrade(ctcGrade1);
    	
    	CtcTerm ctcTerm2 = new CtcTerm();
    	ctcCategory1.addCtcTerm(ctcTerm2);
    	ctcTerm2.setCtepCode("ctepcode2");
    	ctcTerm2.setCtepTerm("Test ctep term 2");
    	ctcTerm2.setTerm("Test term 2");
    	CtcGrade ctcGrade2 = new CtcGrade();
    	ctcGrade2.setGrade(Grade.DEATH);
    	ctcGrade2.setText("grade2");
    	ctcTerm2.addCtcGrade(ctcGrade2);
    	CtcGrade ctcGrade3 = new CtcGrade();
    	ctcGrade3.setGrade(Grade.MODERATE);
    	ctcGrade3.setText("grade3");
    	ctcTerm2.addCtcGrade(ctcGrade3);
    	
    	
    	CtcCategory ctcCategory2 = new CtcCategory();
    	ctcCategory2.setName("ctccategory2");
    	ctc1.addCtcCategory(ctcCategory2);
    	CtcTerm ctcTerm3 = new CtcTerm();
    	ctcCategory2.addCtcTerm(ctcTerm3);
    	ctcTerm3.setCtepCode("ctepcode3");
    	ctcTerm3.setCtepTerm("Test ctep term 3");
    	ctcTerm3.setTerm("Test term 3");
    	CtcGrade ctcGrade4 = new CtcGrade();
    	ctcGrade4.setGrade(Grade.SEVERE);
    	ctcGrade4.setText("grade4");
    	ctcTerm3.addCtcGrade(ctcGrade4);
    	
    	return ctc1;
	}
	
	public void testNewCtcVersionType(){
		List<EntityProcessingOutcomeType> processingOutcomes = ctcService.createOrUpdateCtc(buildNewCtcVersionType()).getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome();
		interruptSession();
		assertEquals(4, processingOutcomes.size());
		EntityProcessingOutcomeType processingOutcome = processingOutcomes.get(0);
		assertEquals("ctc", processingOutcome.getBusinessIdentifier());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("ctccategory", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(2);
		assertEquals("ctepcode", processingOutcome.getBusinessIdentifier());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		processingOutcome = processingOutcomes.get(3);
		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessIdentifier());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessage().get(0));
		Ctc saved = ctcDao.getByName("ctc");
		assertEquals("ctc", saved.getName());
    	assertEquals("ctccategory", saved.getCategories().get(0).getName());
    	assertEquals("ctepcode", saved.getCategories().get(0).getTerms().get(0).getCtepCode());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getSelect());
    	assertNull(saved.getCategories().get(0).getTerms().get(0).getDefinition());
    	assertEquals("Test ctep term 0", saved.getCategories().get(0).getTerms().get(0).getCtepTerm());
    	assertEquals("Test ctep term 0", saved.getCategories().get(0).getTerms().get(0).getTerm());
    	assertFalse(saved.getCategories().get(0).getTerms().get(0).isOtherRequired());
    	assertEquals(Grade.DEATH, saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getGrade());
    	assertEquals("grade", saved.getCategories().get(0).getTerms().get(0).getContextualGrades().get(0).getText());
	}
	
	
	
	private Ctcs buildUpdateCtcVersionType(){
		Ctcs ctcs = new Ctcs();
		CtcVersionType ctc1 = new CtcVersionType();
		ctcs.getCtc().add(ctc1);
    	ctc1.setName("ctc1");
    	
    	CtcCategoryType ctcCategory1 = new CtcCategoryType();
    	ctcCategory1.setName("ctccategory1");
    	ctc1.getCtcCategory().add(ctcCategory1);
    	CtcTermType ctcTerm1 = new CtcTermType();
    	ctcCategory1.getCtcTerm().add(ctcTerm1);
    	ctcTerm1.setCtepCode("ctepcode1");
    	ctcTerm1.setCtepTerm("Test ctep term Other, Specify");
    	ctcTerm1.setTerm("Test ctep term Other, Specify");
    	CtcGradeType ctcGrade1 = new CtcGradeType();
    	ctcGrade1.setGrade(5);
    	ctcGrade1.setText("grade1");
    	ctcTerm1.getCtcGrade().add(ctcGrade1);
    	
    	CtcTermType ctcTerm2 = new CtcTermType();
    	ctcCategory1.getCtcTerm().add(ctcTerm2);
    	ctcTerm2.setCtepCode("ctepcode2");
    	ctcTerm2.setCtepTerm("Test ctep term 2");
    	ctcTerm2.setTerm("Test term 2");
    	CtcGradeType ctcGrade2 = new CtcGradeType();
    	ctcGrade2.setGrade(5);
    	ctcGrade2.setText("grade2");
    	ctcTerm2.getCtcGrade().add(ctcGrade2);
    	CtcGradeType ctcGrade3 = new CtcGradeType();
    	ctcGrade3.setGrade(2);
    	ctcGrade3.setText("grade3");
    	ctcTerm2.getCtcGrade().add(ctcGrade3);
    	
    	
    	CtcCategoryType ctcCategory2 = new CtcCategoryType();
    	ctcCategory2.setName("ctccategory2");
    	ctc1.getCtcCategory().add(ctcCategory2);
    	CtcTermType ctcTerm3 = new CtcTermType();
    	ctcCategory2.getCtcTerm().add(ctcTerm3);
    	ctcTerm3.setCtepCode("ctepcode3");
    	ctcTerm3.setCtepTerm("Test ctep term 3");
    	ctcTerm3.setTerm("Test term 3");
    	CtcGradeType ctcGrade4 = new CtcGradeType();
    	ctcGrade4.setGrade(3);
    	ctcGrade4.setText("grade4");
    	ctcTerm3.getCtcGrade().add(ctcGrade4);
    	
    	return ctcs;
	}
	
}
