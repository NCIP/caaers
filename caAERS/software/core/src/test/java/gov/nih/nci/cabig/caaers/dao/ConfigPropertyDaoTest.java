package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ConfigPropertyDaoTest extends DaoTestCase<ConfigPropertyDao> {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetById(){
		ConfigProperty cp = getDao().getById(-1);
		assertNotNull(cp);
		assertNotNull(cp.getConfigType());
	}
	
	public void testGetByType() {
		List<ConfigProperty> props = getDao().getByType(ConfigPropertyType.REPORT_GROUP);
		assertEquals(2, props.size());
		assertEquals("EXP1", props.get(0).getCode());
		assertEquals(ConfigPropertyType.REPORT_GROUP, props.get(0).getConfigType());
	}
	
	public void testGetAll(){
		List<ConfigProperty> props = getDao().getAll();
		assertEquals(3, props.size());
	}
	
	public void testGetByTypeAndCode(){
		ConfigProperty cp = getDao().getByTypeAndCode(ConfigPropertyType.REPORT_GROUP, "EXP1");
		assertNotNull(cp);
		assertEquals("Expedited Report", cp.getName());
		cp = getDao().getByTypeAndCode(ConfigPropertyType.REPORT_GROUP, "RT_xx");
		assertNull(cp);
	}
	
	public void testSave(){
		{
			ConfigProperty cp = Fixtures.createConfigProperty("test");
			cp.setConfigType(ConfigPropertyType.UNKNOWN);
			getDao().save(cp);
		}
		interruptSession();
		{
			ConfigProperty cp = getDao().getByTypeAndCode(ConfigPropertyType.UNKNOWN, "test");
			assertNotNull(cp);
			assertNotNull(cp.getConfigType());
			assertEquals("test", cp.getCode());
			
		}
	}

}
