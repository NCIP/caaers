package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * This class test AdvancedSearchCriteriaParameter
 *
 * @author Sameer Sawant
 */
public class AdvancedSearchCriteriaParameterTest extends WebTestCase {
	AdvancedSearchCriteriaParameter criteriaParameter;
	
	protected void setUp() throws Exception {
        super.setUp();
        criteriaParameter = new AdvancedSearchCriteriaParameter();
        criteriaParameter.setAttributeName("testAttribute");
        criteriaParameter.setObjectName("testObject");
        criteriaParameter.setValue("testValue");
        criteriaParameter.setPredicate("==");
    }
	
	public void testCompareToEqualValues(){
		AdvancedSearchCriteriaParameter p = new AdvancedSearchCriteriaParameter();
		p.setAttributeName("testAttribute");
		p.setObjectName("testObject");
		p.setValue("testValue");
		p.setPredicate("==");
		assertEquals("Incorrect comparision", 0, criteriaParameter.compareTo(p));
	}
	
	public void testCompareToSmallerValue(){
		AdvancedSearchCriteriaParameter p = new AdvancedSearchCriteriaParameter();
		p.setAttributeName("testAttribute1");
		p.setObjectName("testObject");
		p.setValue("testValue");
		p.setPredicate("==");
		assertEquals("Incorrect comparision", -1, criteriaParameter.compareTo(p));
	}
	
	public void testCompareToGreaterValue(){
		AdvancedSearchCriteriaParameter p = new AdvancedSearchCriteriaParameter();
		p.setAttributeName("testAttribute");
		p.setObjectName("testObject");
		p.setValue("testValue");
		p.setPredicate("==");
		criteriaParameter.setObjectName("testObject1");
		assertEquals("Incorrect comparision", 1, criteriaParameter.compareTo(p));
	}
}