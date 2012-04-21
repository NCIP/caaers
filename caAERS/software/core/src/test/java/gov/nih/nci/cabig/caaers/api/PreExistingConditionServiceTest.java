package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.ArrayList;
import java.util.List;

public class PreExistingConditionServiceTest extends CaaersDbNoSecurityTestCase{

	private PreExistingConditionDao preExistingConditionDao;
	private PreExistingConditionManagementService preExistingConditionLOVService;
	
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        preExistingConditionDao = (PreExistingConditionDao) getDeployedApplicationContext().getBean("preExistingConditionDao");
	        preExistingConditionLOVService = (PreExistingConditionManagementService) getDeployedApplicationContext().getBean("preExistingConditionLOVService");
	    }
	
	public void testImportExistingConditions(){
		List<PreExistingCondition> existingConditions = preExistingConditionDao.getAll();
		assertEquals(1,existingConditions.size());
		assertEquals("condition",existingConditions.get(0).getText());
		
		List<PreExistingCondition> importedPreExistingConditions = new ArrayList<PreExistingCondition>();
		PreExistingCondition preExistingCondition = new PreExistingCondition();
		preExistingCondition.setText("condition");
		
		importedPreExistingConditions.add(preExistingCondition);
		
		List<ProcessingOutcome> entityErrorMessages = preExistingConditionLOVService.importPreExistingConditions(importedPreExistingConditions);
		assertEquals(1,entityErrorMessages.size());
		assertEquals("condition", entityErrorMessages.get(0).getBusinessId());
		
		// reload from db
		existingConditions = preExistingConditionDao.getAll();
		// new condition should not be saved as condition with same text already exists in db
		assertEquals(1,existingConditions.size());
	}
	
	public void testImportNewConditions(){
		List<PreExistingCondition> existingConditions = preExistingConditionDao.getAll();
		assertEquals(1,existingConditions.size());
		assertEquals("condition",existingConditions.get(0).getText());
		
		List<PreExistingCondition> importedPreExistingConditions = new ArrayList<PreExistingCondition>();
		
		// 1st pre-existing condition
		PreExistingCondition preExistingCondition1 = new PreExistingCondition();
		preExistingCondition1.setText("condition1");
		importedPreExistingConditions.add(preExistingCondition1);
		
		// 2nd pre-existing condition
		PreExistingCondition preExistingCondition2 = new PreExistingCondition();
		preExistingCondition2.setText("condition2");
		importedPreExistingConditions.add(preExistingCondition2);
		
		// 3rd pre-existing same text different case. should be imported
		PreExistingCondition preExistingCondition3 = new PreExistingCondition();
		preExistingCondition3.setText("Condition");
		importedPreExistingConditions.add(preExistingCondition3);
		
		List<ProcessingOutcome> entityErrorMessages = preExistingConditionLOVService.importPreExistingConditions(importedPreExistingConditions);
		assertEquals(3,entityErrorMessages.size());
		assertEquals("condition1", entityErrorMessages.get(0).getBusinessId());
		
		// reload from db
		existingConditions = preExistingConditionDao.getAll();
		// 3 newly imported therapies should be saved as text is different
		assertEquals(4,existingConditions.size());
	}
}
