package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.LabTerm;

import java.util.List;

public class LabTermDaoTest extends DaoTestCase<LabTermDao> {

	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}
	
	public void testGetById(){
		LabTerm labTerm = getDao().getById(-10001);
		assertNotNull(labTerm);
		assertEquals("Bone Marrow Cellularity",labTerm.getTerm());
	}
	
	public void testGetBySubName(){
		
		List<LabTerm> labTerms = getDao().getBySubname(new String[] { "nothing" }, -1001);
		assertEquals(0, labTerms.size());
		
		labTerms = getDao().getBySubname(new String[] {"Albumin - blood"}, -1002);
		assertEquals(1, labTerms.size());
	}
	
	public void testGetCtcTerm(){
		LabTerm labTerm = getDao().getCtcTerm(new String[] {"Amylase - blood"});
		assertNotNull(labTerm);
		assertEquals("Amylase - blood", labTerm.getTerm());
	}
	
	public void testGetAll(){
		List<LabTerm> labTerms = getDao().getAll();
		assertNotNull(labTerms);
		assertEquals(4, labTerms.size());
	}
}
