package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;

/**
 * This class tests the CaaersFieldDefinitionDao class.  
 * @author Sameer Work
 */

public class CaaersFieldDefinitionDaoTest extends DaoTestCase<CaaersFieldDefinitionDao> {

	public void testGetByTabName() throws Exception{
		List<CaaersFieldDefinition> fieldDefinitionsList = getDao().getByTabName("CaptureAdverseEventsTab");
		System.out.println("fieldDefinitionsList = " + fieldDefinitionsList);
		assertEquals("Incorrect number of field definitions fetched.", 2, fieldDefinitionsList.size());
	}
}
