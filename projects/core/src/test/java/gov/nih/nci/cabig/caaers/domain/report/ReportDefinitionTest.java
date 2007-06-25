package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

/**
 * @author Rhett Sutphin
 */
public class ReportDefinitionTest extends CaaersTestCase {
    private ReportDefinition def;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        def = new ReportDefinition();
    }
    
    public void testCreateReport() throws Exception {
        Report created = def.createReport();
        assertNotNull(created);
        assertSame(def, created.getReportDefinition());
        assertEquals(ReportStatus.PENDING, created.getStatus());
    }
}
