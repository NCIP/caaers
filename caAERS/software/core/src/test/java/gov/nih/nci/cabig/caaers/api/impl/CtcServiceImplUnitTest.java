/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.api.CtcService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;

import java.util.List;

import org.easymock.EasyMock;

public class CtcServiceImplUnitTest extends AbstractTestCase {

	private CtcDao ctcDao;
	private CtcService ctcService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctcDao = registerMockFor(CtcDao.class);
		CtcServiceImpl ctcServiceImpl = new CtcServiceImpl();
		ctcServiceImpl.setCtcDao(ctcDao);
		ctcService = ctcServiceImpl;
	}
	
	public void testCtcNameBlank(){
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(new Ctc());
		assertEquals(1, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("NA", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertTrue(processingOutcome.isFailed());
		assertEquals("Ctc name/version cannot be blank", processingOutcome.getMessages().get(0));
	}
	
	public void testCtcCategoryNameBlankCtcAdded(){
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(new CtcCategory());
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(null);
		ctcDao.save(EasyMock.isA(Ctc.class));
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(2, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("NA", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertTrue(processingOutcome.isFailed());
		assertEquals("CtcCategory name cannot be blank", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcCategoryNameBlankCtcUpdated(){
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(new CtcCategory());
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(ctc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(2, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("NA", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertTrue(processingOutcome.isFailed());
		assertEquals("CtcCategory name cannot be blank", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcCategoryAddedNewCtc(){
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(null);
		ctcDao.save(EasyMock.isA(Ctc.class));
		EasyMock.expectLastCall().times(2);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(2, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcCategoryAddedExistingCtc(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("E");
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(2, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcCategoryUpdatedExistingCtc(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(2, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcTermNameBlank(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		ctcCategory.addCtcTerm(new CtcTerm());
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(3, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
		processingOutcome = processingOutcomes.get(2);
		assertEquals("NA", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertTrue(processingOutcome.isFailed());
		assertEquals("CtcTerm ctep_code cannot be blank", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcTermUpdate(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		CtcTerm existingCtcTerm = new CtcTerm();
		existingCtcTerm.setCtepCode("Test ctep code");
    	existingCtcTerm.setCtepTerm("Test ctep term");
    	existingCtcTerm.setDefinition("Test definition");
    	existingCtcTerm.setSelect("Test select");
    	existingCtcTerm.setTerm("Test term");
    	existingCategory.addCtcTerm(existingCtcTerm);
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setCtepCode("Test ctep code");
		ctcTerm.setCtepTerm("Test ctep term 1");
		ctcTerm.setDefinition("Test definition 1");
		ctcTerm.setSelect("Test select 1");
		ctcTerm.setTerm("Test term 1");
		ctcCategory.addCtcTerm(ctcTerm);
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(3, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
		processingOutcome = processingOutcomes.get(2);
		assertEquals("Test ctep code", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcTermAdded(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		CtcTerm existingCtcTerm = new CtcTerm();
		existingCtcTerm.setCtepCode("Test ctep code");
    	existingCtcTerm.setCtepTerm("Test ctep term");
    	existingCtcTerm.setDefinition("Test definition");
    	existingCtcTerm.setSelect("Test select");
    	existingCtcTerm.setTerm("Test term");
    	existingCategory.addCtcTerm(existingCtcTerm);
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setCtepCode("Test ctep code 1");
		ctcTerm.setCtepTerm("Test ctep term 1");
		ctcTerm.setDefinition("Test definition 1");
		ctcTerm.setSelect("Test select 1");
		ctcTerm.setTerm("Test term 1");
		ctcCategory.addCtcTerm(ctcTerm);
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(3, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
		processingOutcome = processingOutcomes.get(2);
		assertEquals("Test ctep code 1", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcGradeInvalid(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		CtcTerm existingCtcTerm = new CtcTerm();
		existingCtcTerm.setCtepCode("Test ctep code");
    	existingCtcTerm.setCtepTerm("Test ctep term");
    	existingCtcTerm.setDefinition("Test definition");
    	existingCtcTerm.setSelect("Test select");
    	existingCtcTerm.setTerm("Test term");
    	existingCategory.addCtcTerm(existingCtcTerm);
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setCtepCode("Test ctep code 1");
		ctcTerm.setCtepTerm("Test ctep term 1");
		ctcTerm.setDefinition("Test definition 1");
		ctcTerm.setSelect("Test select 1");
		ctcTerm.setTerm("Test term 1");
		CtcGrade ctcGrade = new CtcGrade();
//		ctcGrade.setGrade(Grade.DEATH);
//		ctcGrade.setText("Test Grade");
		ctcTerm.addCtcGrade(ctcGrade);
		ctcCategory.addCtcTerm(ctcTerm);
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(4, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
		processingOutcome = processingOutcomes.get(2);
		assertEquals("Test ctep code 1", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(3);
		assertEquals("NA", processingOutcome.getBusinessId());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertTrue(processingOutcome.isFailed());
		assertEquals("Ctc grade or grade description cannot be blank", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testCtcGradeValid(){
		Ctc existingCtc = new Ctc();
		CtcCategory existingCategory = new CtcCategory();
		existingCategory.setName("C");
		CtcTerm existingCtcTerm = new CtcTerm();
		existingCtcTerm.setCtepCode("Test ctep code");
    	existingCtcTerm.setCtepTerm("Test ctep term");
    	existingCtcTerm.setDefinition("Test definition");
    	existingCtcTerm.setSelect("Test select");
    	existingCtcTerm.setTerm("Test term");
    	existingCategory.addCtcTerm(existingCtcTerm);
		existingCtc.addCtcCategory(existingCategory);
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName("C");
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setCtepCode("Test ctep code 1");
		ctcTerm.setCtepTerm("Test ctep term 1");
		ctcTerm.setDefinition("Test definition 1");
		ctcTerm.setSelect("Test select 1");
		ctcTerm.setTerm("Test term 1");
		CtcGrade ctcGrade = new CtcGrade();
		ctcGrade.setGrade(Grade.DEATH);
		ctcGrade.setText("Test Grade");
		ctcTerm.addCtcGrade(ctcGrade);
		ctcCategory.addCtcTerm(ctcTerm);
		Ctc ctc = new Ctc();
		ctc.setName("Test");
		ctc.addCtcCategory(ctcCategory);
		EasyMock.expect(ctcDao.getByName("Test")).andReturn(existingCtc);
		ctcDao.save(existingCtc);
		replayMocks();
		List<ProcessingOutcome> processingOutcomes = ctcService.createOrUpdateCtc(ctc);
		assertEquals(4, processingOutcomes.size());
		ProcessingOutcome processingOutcome = processingOutcomes.get(0);
		assertEquals("Test", processingOutcome.getBusinessId());
		assertEquals(Ctc.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(1);
		assertEquals("C", processingOutcome.getBusinessId());
		assertEquals(CtcCategory.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Updated", processingOutcome.getMessages().get(0));
		verifyMocks();
		processingOutcome = processingOutcomes.get(2);
		assertEquals("Test ctep code 1", processingOutcome.getBusinessId());
		assertEquals(CtcTerm.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		processingOutcome = processingOutcomes.get(3);
		assertEquals(Grade.DEATH.getDisplayName(), processingOutcome.getBusinessId());
		assertEquals(CtcGrade.class.getName(), processingOutcome.getKlassName());
		assertFalse(processingOutcome.isFailed());
		assertEquals("Added", processingOutcome.getMessages().get(0));
		verifyMocks();
	}
	
	public void testIsOtherRequired(){
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("Other, Specify"));
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("Other	,Specify"));
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("Other,Specify"));
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("Other,		  Specify"));
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("other, specify"));
		assertTrue(((CtcServiceImpl)ctcService).isOtherRequired("Test ctep term Other, Specify"));
		assertFalse(((CtcServiceImpl)ctcService).isOtherRequired("Others, Specify"));
	}
}
