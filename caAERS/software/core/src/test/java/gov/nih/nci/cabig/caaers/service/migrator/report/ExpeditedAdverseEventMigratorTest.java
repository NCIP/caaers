package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventMigratorTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    ExpeditedAdverseEventMigrator migrator;

    public void setUp() throws Exception {
        migrator = new ExpeditedAdverseEventMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrate() throws Exception {
        migrator.migrate(src, dest, outcome);
        assertNotNull(src.getAdverseEvents());
        assertNotNull(dest.getAdverseEvents());
        assertEquals(0, dest.getAdverseEvents().size());
    }

    public void testMigrateWithValues() throws Exception {
        Date ae1StartDate = DateUtils.yesterday();
        Date ae2StartDate = DateUtils.yesterday();
        Date ae1EndDate = DateUtils.today();
        Date ae2EndDate = DateUtils.tomorrow();

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae1.setStartDate(ae1StartDate);
        ae1.setEndDate(ae1EndDate);
        ae1.setExternalId("1");

        AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.DEATH);
        ae2.setStartDate(ae2StartDate);
        ae2.setEndDate(ae2EndDate);
        ae2.setExternalId("2");

        AdverseEventReportingPeriod rp1 = Fixtures.createReportingPeriod();
        rp1.addAdverseEvent(ae1);
        rp1.addAdverseEvent(ae2);
        src.setReportingPeriod(rp1);
        src.addAdverseEvent(ae1);
        src.addAdverseEvent(ae2);

        dest.setReportingPeriod(rp1);
        migrator.migrate(src, dest, outcome);
        System.out.println(outcome.getValidationErrors());
        assertFalse(outcome.hasErrors());
        assertEquals(2, dest.getAdverseEvents().size()) ;
        assertSame(ae1, dest.getAdverseEvent(1));
        assertSame(ae2, dest.getAdverseEvent(2));


    }


    public void testMigrateWithIncorrectValues() throws Exception {
        Date ae1StartDate = DateUtils.yesterday();
        Date ae2StartDate = DateUtils.yesterday();
        Date ae1EndDate = DateUtils.today();
        Date ae2EndDate = DateUtils.tomorrow();

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae1.setStartDate(ae1StartDate);
        ae1.setEndDate(ae1EndDate);
        ae1.setExternalId("1");

        AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.DEATH);
        ae2.setStartDate(ae2StartDate);
        ae2.setEndDate(ae2EndDate);
        ae2.setExternalId("2");

        AdverseEventReportingPeriod rp1 = Fixtures.createReportingPeriod();
        rp1.addAdverseEvent(ae1);
        src.setReportingPeriod(rp1);
        src.addAdverseEvent(ae1);
        src.addAdverseEvent(ae2);

        dest.setReportingPeriod(rp1);
        migrator.migrate(src, dest, outcome);
        System.out.println(outcome.getValidationErrors());
        assertTrue(outcome.hasErrors());
        assertTrue(outcome.getValidationErrors().containsErrorWithCode("WS_AEMS_079"));


    }


    public void testMigrateWithValuesVerifyDatesModifiedCorrectly() throws Exception {
        Date ae1StartDate = DateUtils.yesterday();
        Date ae2StartDate = DateUtils.today();
        Date ae1EndDate = DateUtils.today();
        Date ae2EndDate = DateUtils.tomorrow();

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae1.setStartDate(ae1StartDate);
        ae1.setEndDate(ae1EndDate);
        ae1.setExternalId("1");

        AdverseEvent ae2 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae2.setStartDate(ae2StartDate);
        ae2.setEndDate(ae2EndDate);
        ae2.setExternalId("1");

        AdverseEventReportingPeriod rp1 = Fixtures.createReportingPeriod();
        rp1.addAdverseEvent(ae1);
        src.setReportingPeriod(rp1);
        src.addAdverseEvent(ae2);

        dest.setReportingPeriod(rp1);
        migrator.migrate(src, dest, outcome);
        System.out.println(outcome.getValidationErrors());
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getAdverseEvents().size()) ;
        assertEquals(dest.getAdverseEvent(1).getStartDate(), ae2StartDate);
        assertEquals(dest.getAdverseEvent(1).getEndDate(), ae2EndDate);


    }

}
