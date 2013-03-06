/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.LabVersion;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

public class LabCategoryDaoTest extends DaoTestCase<LabCategoryDao> {
	
	private LabCategoryDao labCategoryDao = (LabCategoryDao) getApplicationContext().getBean("labCategoryDao");
	private LabVersionDao labVersionDao = (LabVersionDao) getApplicationContext().getBean("labVersionDao");
	

	
	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}
	
	public void testGetAll(){
		List<LabCategory> labCats = getDao().getAll();
		assertNotNull(labCats);
		assertEquals(3,labCats.size());
	}
	
	public void testSaveLabCateogry() throws Exception{
		List<LabCategory> labCats = getDao().getAll();
		assertNotNull(labCats);
		assertEquals(3,labCats.size());
		
		LabCategory labCategory = new LabCategory();
		LabVersion labVersion = labVersionDao.getByName("Version 1");
		labCategory.setLabVersion(labVersion);
		labCategory.setName("Cellular");
		
		labCategoryDao.save(labCategory);
		
		interruptSession();
		
		labCats = getDao().getAll();
		assertEquals(4,labCats.size());
		
		LabCategory saved = labCategoryDao.getByName(labCategory.getName());
		assertNotNull(saved);
		assertEquals("Cellular",saved.getName());
		assertEquals(labVersion.getId(),saved.getLabVersion().getId());
	}

	public void testSaveCategoryWithTerms() throws Exception{
		List<LabCategory> labCats = getDao().getAll();
		assertNotNull(labCats);
		assertEquals(3,labCats.size());
		
		LabCategory labCategory = new LabCategory();
		LabVersion labVersion = labVersionDao.getByName("Version 1");
		labCategory.setLabVersion(labVersion);
		labCategory.setName("Biology");
		
		LabTerm term1 = new LabTerm();
		term1.setTerm("term1");
		
		LabTerm term2 = new LabTerm();
		term2.setTerm("term2");
		
		labCategory.addTerm(term1);
		labCategory.addTerm(term2);
		
		labCategoryDao.save(labCategory);
		
		interruptSession();
		
		LabCategory savedCategory = labCategoryDao.getByName("Biology");
		
		assertNotNull(savedCategory);
		assertEquals("Biology",savedCategory.getName());
		assertEquals(2,savedCategory.getTerms().size());
	}
	
	public void testSaveTermsFromCategory() throws Exception{
		LabTerm term1 = new LabTerm();
		term1.setTerm("term1");
		
		LabTerm term2 = new LabTerm();
		term2.setTerm("term2");
		
		LabCategory category = labCategoryDao.getById(-1001);
		
		assertNotNull(category);
		assertEquals("Bone Marrow Biopsy",category.getName());
		assertEquals(2,category.getTerms().size());
		
		category.addTerm(term1);
		category.addTerm(term2);
		
		labCategoryDao.save(category);
		
		interruptSession();
		
		LabCategory savedCategory = labCategoryDao.getById(-1001);
		assertEquals(4,savedCategory.getTerms().size());
		
	}
}
