package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.LabServiceImpl;
import gov.nih.nci.cabig.caaers.dao.LabCategoryDao;
import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabTerm;

import java.util.ArrayList;
import java.util.List;

public class LabServiceTest extends CaaersDbNoSecurityTestCase{
	
	    private LabCategoryDao labCategoryDao;
		private LabServiceImpl labService;
		private LabTermDao labTermDao;
	
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();

	        labService = (LabServiceImpl) getDeployedApplicationContext().getBean("labService");
	        labCategoryDao = (LabCategoryDao) getDeployedApplicationContext().getBean("labCategoryDao");
	        labTermDao = (LabTermDao) getDeployedApplicationContext().getBean("labTermDao");
	    }
	 
	 public void testAddCategories(){
		List<LabCategory> dbCategories = labCategoryDao.getAll();
		 assertEquals(3,dbCategories.size());
		 
		 List<LabCategory> labCategories = new ArrayList<LabCategory>();
		 
		 LabCategory labCategory1 = new LabCategory();
		 labCategory1.setName("Molecular");
		 labCategories.add(labCategory1);
		 
		 LabCategory labCategory2 = new LabCategory();
		 labCategory2.setName("Biological");
		 labCategories.add(labCategory2);
		 
		 labService.createOrUpdateLabs(labCategories);
		 
		 interruptSession();
		 
		 dbCategories = labCategoryDao.getAll();
		 assertEquals(5,dbCategories.size());
		 
		 LabCategory savedLabCateogry1 = labCategoryDao.getByName("Molecular");
		 assertNotNull(savedLabCateogry1);
		 LabCategory savedLabCateogry2 = labCategoryDao.getByName("Biological");
		 assertNotNull(savedLabCateogry2);
	 }
	 
	 public void testAddNewAndDuplicateCategories(){
			List<LabCategory> dbCategories = labCategoryDao.getAll();
			 assertEquals(3,dbCategories.size());
			 
			 LabCategory savedLabCateogry = labCategoryDao.getByName("Respiratory");
			 assertNotNull(savedLabCateogry);
			 assertFalse(savedLabCateogry.getRetiredIndicator());
			 
			 List<LabCategory> labCategories = new ArrayList<LabCategory>();
			 
			 LabCategory labCategory1 = new LabCategory();
			 labCategory1.setName("Molecular");
			 labCategories.add(labCategory1);
			 
			 LabCategory labCategory2 = new LabCategory();
			 labCategory2.setName("Respiratory");
			 labCategory2.setRetiredIndicator(true);
			 labCategories.add(labCategory2);
			 
			 labService.createOrUpdateLabs(labCategories);
			 
			 interruptSession();
			 
			 dbCategories = labCategoryDao.getAll();
			 assertEquals(4,dbCategories.size());
			 
			 LabCategory savedLabCateogry1 = labCategoryDao.getByName("Molecular");
			 assertNotNull(savedLabCateogry1);
			 
			 LabCategory savedLabCateogry2 = labCategoryDao.getByName("Respiratory");
			 assertNotNull(savedLabCateogry2);
			 assertTrue(savedLabCateogry2.getRetiredIndicator());
		 }
	 
	 public void testAddNewCategoriesWithTerms(){
			List<LabCategory> dbCategories = labCategoryDao.getAll();
			 assertEquals(3,dbCategories.size());
			 
			 List<LabCategory> labCategories = new ArrayList<LabCategory>();
			 
			 LabCategory labCategory1 = new LabCategory();
			 labCategory1.setName("Molecular");
			 labCategories.add(labCategory1);
			 
			 LabTerm labTerm1 = new LabTerm();
			 labTerm1.setTerm("Term 1");
			 
			 LabTerm labTerm2 = new LabTerm();
			 labTerm2.setTerm("Term 2");
			 
			 labCategory1.addTerm(labTerm1);
			 labCategory1.addTerm(labTerm2);
			 
			 LabCategory labCategory2 = new LabCategory();
			 labCategory2.setName("Biological");
			 labCategories.add(labCategory2);
			 
			 labService.createOrUpdateLabs(labCategories);
			 
			 interruptSession();
			 
			 dbCategories = labCategoryDao.getAll();
			 assertEquals(5,dbCategories.size());
			 
			 LabCategory savedLabCateogry1 = labCategoryDao.getByName("Molecular");
			 assertNotNull(savedLabCateogry1);
			 assertEquals(2,savedLabCateogry1.getTerms().size());
			 assertNotNull(labTermDao.getBySubname(new String[]{"Term 1"}, savedLabCateogry1.getId()));
			 assertNotNull(labTermDao.getBySubname(new String[]{"Term 2"}, savedLabCateogry1.getId()));
			 
			 LabCategory savedLabCateogry2 = labCategoryDao.getByName("Biological");
			 assertNotNull(savedLabCateogry2);
			 assertEquals(0,savedLabCateogry2.getTerms().size());
		 }
	 
	 public void testAddNewAndOldTermsToExistingCategories(){
			List<LabCategory> dbCategories = labCategoryDao.getAll();
			 assertEquals(3,dbCategories.size());
			 
			 LabCategory savedLabCateogry = labCategoryDao.getByName("Chemistry");
			 assertNotNull(savedLabCateogry);
			 assertEquals(2,savedLabCateogry.getTerms().size());
			 assertNotNull(labTermDao.getBySubname(new String[]{"Albumin - blood"}, savedLabCateogry.getId()));
			 assertFalse(labTermDao.getBySubname(new String[]{"Albumin - blood"}, savedLabCateogry.getId()).get(0).getRetiredIndicator());
			 assertNotNull(labTermDao.getBySubname(new String[]{"Amylase - blood"}, savedLabCateogry.getId()));
			 
			 List<LabCategory> labCategories = new ArrayList<LabCategory>();
			 
			 LabCategory labCategory1 = new LabCategory();
			 labCategory1.setName("Chemistry");
			 
			 LabTerm labTerm1 = new LabTerm();
			 labTerm1.setTerm("Term 1");
			 
			 LabTerm labTerm2 = new LabTerm();
			 labTerm2.setTerm("Albumin - blood");
			 labTerm2.setRetiredIndicator(true);
			 
			 labCategory1.addTerm(labTerm1);
			 labCategory1.addTerm(labTerm2);
			 
			 labCategories.add(labCategory1);
			 
			 
			 LabCategory savedLabCateogry1 = labCategoryDao.getByName("Respiratory");
			 assertNotNull(savedLabCateogry1);
			 assertEquals(0,savedLabCateogry1.getTerms().size());
			 
			 LabCategory labCategory2 = new LabCategory();
			 labCategory2.setName("Respiratory");
			
			 
			 LabTerm labTerm3 = new LabTerm();
			 labTerm3.setTerm("Term 3");
			 
			 LabTerm labTerm4 = new LabTerm();
			 labTerm4.setTerm("Term 4");
			 labTerm4.setRetiredIndicator(true);
			 
			 labCategory2.addTerm(labTerm3);
			 labCategory2.addTerm(labTerm4);
			 labCategories.add(labCategory2);
			 
			 labService.createOrUpdateLabs(labCategories);
			 
			 interruptSession();
			 
			 dbCategories = labCategoryDao.getAll();
			 assertEquals(3,dbCategories.size());
			 
			 LabCategory reSavedLabCateogry1 = labCategoryDao.getByName("Chemistry");
			 assertNotNull(reSavedLabCateogry1);
			 assertEquals(3,reSavedLabCateogry1.getTerms().size());
			 assertEquals(1,labTermDao.getBySubname(new String[]{"Term 1"}, reSavedLabCateogry1.getId()).size());
			 assertEquals(1,labTermDao.getBySubname(new String[]{"Albumin - blood"}, reSavedLabCateogry1.getId()).size());
			 assertTrue(labTermDao.getBySubname(new String[]{"Albumin - blood"}, reSavedLabCateogry1.getId()).get(0).getRetiredIndicator());
			 assertEquals(1,labTermDao.getBySubname(new String[]{"Amylase - blood"}, reSavedLabCateogry1.getId()).size());
			 
			 LabCategory reSavedLabCateogry2 = labCategoryDao.getByName("Respiratory");
			 assertNotNull(reSavedLabCateogry2);
			 assertEquals(2,reSavedLabCateogry2.getTerms().size());
			 assertEquals(1,labTermDao.getBySubname(new String[]{"Term 3"}, reSavedLabCateogry2.getId()).size());
			 assertEquals(1,labTermDao.getBySubname(new String[]{"Term 4"}, reSavedLabCateogry2.getId()).size());
			 assertTrue(labTermDao.getBySubname(new String[]{"Term 4"}, reSavedLabCateogry2.getId()).get(0).getRetiredIndicator());
		 }

}
