package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;

/**
 * This class tests the CaaersFieldDefinitionDao class.  
 * @author Sameer Work
 * @author Ion C. Olaru
 */

public class CaaersFieldDefinitionDaoTest extends DaoTestCase<CaaersFieldDefinitionDao> {

	public void testGetByTabName() throws Exception{
		List<CaaersFieldDefinition> fieldDefinitionsList = getDao().getByTabName("CaptureAdverseEventsTab");
		assertEquals("Incorrect number of field definitions fetched.", 2, fieldDefinitionsList.size());
	}

	public void testGetAll() throws Exception{
		List<CaaersFieldDefinition> fieldDefinitionsList = getDao().getAll();
		assertEquals("Incorrect number of field definitions fetched.", 4, fieldDefinitionsList.size());
	}
}
