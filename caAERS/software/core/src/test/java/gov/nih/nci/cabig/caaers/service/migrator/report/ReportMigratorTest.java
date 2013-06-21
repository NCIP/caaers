/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;
import org.easymock.EasyMock;

/**
 * User: medav
 * Date: 1/14/13
 */
public class ReportMigratorTest extends AbstractTestCase {

    ReportMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    ReportDefinitionDao reportDefinitionDao;

    public void setUp() throws Exception {
        migrator = new ReportMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();

        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);

        Organization org = Fixtures.createOrganization("Mayo Clinic", 26);
        ReportDefinition rptDef = Fixtures.createReportDefinition("CTEP 24 hour Report Definition", org, null);
        Report rpt = rptDef.createReport();
        src.addReport(rpt);


        migrator.setReportDefinitionDao(reportDefinitionDao);

        EasyMock.expect( reportDefinitionDao.getByName("CTEP 24 hour Report Definition")).andReturn(rptDef).anyTimes();
        replayMocks();
    }

    @Override
    protected void tearDown() throws Exception {
        verifyMocks();
        super.tearDown();

    }

    public void testMigrateWithValues() throws Exception {
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        migrator.migrate(src, dest, outCome);
        assertFalse(outCome.hasErrors());
    }

    public void testMigrateWithSubmitter(){
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        src.getReports().get(0).getLastVersion().addSubmitter();
        src.getReports().get(0).getSubmitter().setFirstName("x");
        src.getReports().get(0).getSubmitter().setLastName("y");
        migrator.migrate(src, dest, outCome);
        assertFalse(outCome.hasErrors());
        assertEquals("x", dest.getReports().get(0).getSubmitter().getFirstName());
    }


}
