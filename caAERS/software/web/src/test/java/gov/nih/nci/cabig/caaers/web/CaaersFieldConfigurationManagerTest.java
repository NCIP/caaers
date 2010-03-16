package gov.nih.nci.cabig.caaers.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;

/**
 * This class test CaaersFieldConfigurationManager class
 * @author Sameer Sawant
 */
public class CaaersFieldConfigurationManagerTest extends WebTestCase{
	
	private CaaersFieldConfigurationManager manager = null;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		manager = new CaaersFieldConfigurationManager();
		initializeFieldConfigurationMap();
	}
	
	private void initializeFieldConfigurationMap(){
		Map<String, Map<String, Mandatory>> fieldConfigurationMap = new HashMap<String, Map<String, Mandatory>>();
		Map<String, Mandatory> map = new HashMap<String, Mandatory>();
		map.put("field1", Mandatory.MANDATORY);
		map.put("field2", Mandatory.OPTIONAL);
		map.put("field3", Mandatory.OPTIONAL);
		map.put("field4", Mandatory.NA);
		map.put("field5", Mandatory.NA);
		fieldConfigurationMap.put("testTab", map);
		manager.setFieldConfigurationMap(fieldConfigurationMap);
	}
	
	public void testGetListOfApplicableFields(){
		List<String> applicableFieldsList = manager.getListOfApplicableFields("testTab");
		assertEquals("expected 3 fields", 3, applicableFieldsList.size());
	}
	
	public void testGetListOfNotApplicableFields(){
		List<String> notApplicableFieldsList = manager.getListOfNotApplicableFields("testTab");
		assertEquals("expected 2 fields", 2, notApplicableFieldsList.size());
	}
	
	public void testIsFieldMandatory(){
		assertTrue("Expected true", manager.isFieldMandatory("testTab", "field1"));
		assertFalse("Expected false", manager.isFieldMandatory("testTab", "field3"));
	}
}