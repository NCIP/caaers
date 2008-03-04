package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT, CREATE_REPORT_FORMAT })
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

    // hibernate uses dynamic proxies for items in collections
    public void testEqualsIfOneIsSubclass() throws Exception {
        ReportDefinition def1 = new ReportDefinition();
        ReportDefinition def2 = new ReportDefinition() {
        }; // anonymous subclass

        assertEquals(def1, def2);
    }
}
