package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.ArrayList;
import java.util.List;

public class PriorTherapyServiceTest extends CaaersDbNoSecurityTestCase{

	private PriorTherapyDao priorTherapyDao;
	private PriorTherapyManagementService priorTherapyLOVService;
	
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();

	        priorTherapyDao = (PriorTherapyDao) getDeployedApplicationContext().getBean("priorTherapyDao");
	        priorTherapyLOVService = (PriorTherapyManagementService) getDeployedApplicationContext().getBean("priorTherapyLOVService");

	    }
	
	public void testImportExistingTherapies(){
		List<PriorTherapy> existingTherapies = priorTherapyDao.getAll();
		assertEquals(1,existingTherapies.size());
		assertEquals("therapy",existingTherapies.get(0).getText());
		
		List<PriorTherapy> importedPriorTherapies = new ArrayList<PriorTherapy>();
		PriorTherapy priorTherapy = new PriorTherapy();
		priorTherapy.setText("therapy");
		
		importedPriorTherapies.add(priorTherapy);
		
		List<ProcessingOutcome> entityErrorMessages = priorTherapyLOVService.importPriorTherapies(importedPriorTherapies);
		assertEquals(1,entityErrorMessages.size());
		assertEquals("therapy", entityErrorMessages.get(0).getBusinessId());
		
		// reload from db
		existingTherapies = priorTherapyDao.getAll();
		// new therapy should not be saved as therapy with same name already exists in db
		assertEquals(1,existingTherapies.size());
	}
	
	public void testImportNewTherapies(){
		List<PriorTherapy> existingTherapies = priorTherapyDao.getAll();
		assertEquals(1,existingTherapies.size());
		assertEquals("therapy",existingTherapies.get(0).getText());
		
		List<PriorTherapy> importedPriorTherapies = new ArrayList<PriorTherapy>();
		
		// 1st prior therapy
		PriorTherapy priorTherapy1 = new PriorTherapy();
		priorTherapy1.setText("therapy1");
		importedPriorTherapies.add(priorTherapy1);
		
		// 2nd prior therapy
		PriorTherapy priorTherapy2 = new PriorTherapy();
		priorTherapy2.setText("therapy2");
		importedPriorTherapies.add(priorTherapy2);
		
		// 3rd priority same text different case. should be imported
		PriorTherapy priorTherapy3 = new PriorTherapy();
		priorTherapy3.setText("Therapy");
		importedPriorTherapies.add(priorTherapy3);
		
		List<ProcessingOutcome> entityErrorMessages = priorTherapyLOVService.importPriorTherapies(importedPriorTherapies);
		assertEquals(3,entityErrorMessages.size());
		assertEquals("therapy1", entityErrorMessages.get(0).getBusinessId());
		
		// reload from db
		existingTherapies = priorTherapyDao.getAll();
		// 3 newly imported therapies should be saved as text is different
		assertEquals(4,existingTherapies.size());
	}
}
