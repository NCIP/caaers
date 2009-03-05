package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

/**
 * @author Sameer Sawant
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class PriorTherapyDaoTest extends DaoTestCase<PriorTherapyDao> {
	
	public void testGetAll() throws Exception{
		List<PriorTherapy> priorTherapyList = getDao().getAll();
		assertEquals("Incorrect number of prior therapies returned by Dao's getAll() method", 3, priorTherapyList.size());
	}
	
	public void testGetBySubnames() throws Exception{
		String[] subnames = {"pTherapy"};
		List<PriorTherapy> priorTherapyList = getDao().getBySubnames(subnames);
		assertEquals("Incorrect number of prior therapies returned by Dao's getBySubnames() method", 3, priorTherapyList.size());
	}
}