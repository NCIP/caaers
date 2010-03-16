package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/**
 * ReportMandatoryField Tester.
 *
 * @author Biju Joseph
 * @since <pre>03/04/2010</pre>
 * 
 */
public class ReportMandatoryFieldTest extends TestCase {

    ReportMandatoryField field;

    public void setUp() throws Exception {
        super.setUp();
        ReportMandatoryFieldDefinition def = Fixtures.createMandatoryField("abcd", RequirednessIndicator.MANDATORY);
        field = new ReportMandatoryField();
        field.setFieldPath(def.getFieldPath());
        field.setMandatory(Mandatory.OPTIONAL);
    }

    public void testGetMandatory() throws Exception {
        assertSame(Mandatory.OPTIONAL, field.getMandatory());
    }

    public void testGetReportMandatoryFieldDefinition() throws Exception {
       assertEquals("abcd", field.getFieldPath());
    }

}
