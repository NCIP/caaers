package gov.nih.nci.cabig.caaers.validator;

import gov.nih.nci.cabig.caaers.validation.PropertyUtil;
import junit.framework.TestCase;

/**
 * @Biju Joseph
 */
public class PropertyUtilTest extends TestCase {

    public void testValidateForNestedProperty() throws Exception {

        String studySiteMethodName = PropertyUtil
                        .getCollectionMethodName("studySites[0].startDate");
        assertEquals("studySites", studySiteMethodName);

        String testProperty = PropertyUtil.getCollectionMethodName("studySites[0]");
        assertEquals("studySites", testProperty);

        String testStudySiteMethodName = PropertyUtil
                        .getCollectionMethodName("studySites[0].test[4].startDate");
        assertEquals("studySites[0].test", testStudySiteMethodName);

        testProperty = PropertyUtil.getCollectionMethodName(null);
        assertEquals(null, testProperty);

        testProperty = PropertyUtil.getCollectionMethodName("studySites");
        assertEquals(null, testProperty);

    }
}
