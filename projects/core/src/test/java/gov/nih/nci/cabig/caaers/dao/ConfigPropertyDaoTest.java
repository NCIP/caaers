package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ConfigPropertyDaoTest extends DaoTestCase<ConfigPropertyDao> {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetByType() {
		List<ConfigProperty> props = getDao().getByType(ConfigPropertyType.REPORT_TYPE);
		assertEquals(2, props.size());
		assertEquals("EXP1", props.get(0).getCode());
		assertEquals(ConfigPropertyType.REPORT_TYPE, props.get(0).getConfigType());
	}
	
	public void testGetAll(){
		List<ConfigProperty> props = getDao().getAll();
		assertEquals(3, props.size());
	}
	
	public void testGetByTypeAndCode(){
		ConfigProperty cp = getDao().getByTypeAndCode(ConfigPropertyType.REPORT_TYPE, "EXP1");
		assertNotNull(cp);
		assertEquals("Expedited Report", cp.getName());
		cp = getDao().getByTypeAndCode(ConfigPropertyType.REPORT_TYPE, "RT_xx");
		assertNull(cp);
	}

}
