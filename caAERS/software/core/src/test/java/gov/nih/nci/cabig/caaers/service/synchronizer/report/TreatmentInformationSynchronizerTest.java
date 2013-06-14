package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.math.BigDecimal;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class TreatmentInformationSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    TreatmentInformationSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new TreatmentInformationSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrateTreatmentAssignment() throws Exception {
        src.getTreatmentInformation().setTreatmentAssignment(Fixtures.createTreatmentAssignment("tac1"));
        src.getTreatmentInformation().setFirstCourseDate(DateUtils.yesterday());
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertSame(src.getTreatmentInformation().getTreatmentAssignment(), dest.getTreatmentInformation().getTreatmentAssignment());
        assertSame(src.getTreatmentInformation().getFirstCourseDate(), dest.getTreatmentInformation().getFirstCourseDate());
    }


    public void testMigrateAddNewCourseAgent() throws Exception {
        src.getTreatmentInformation().setTreatmentAssignment(Fixtures.createTreatmentAssignment("tac1"));
        CourseAgent ca1 = Fixtures.createCourseAgent("a");
        ca1.getStudyAgent().setId(1);

        src.getTreatmentInformation().addCourseAgent(ca1);
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertSame(src.getTreatmentInformation().getTreatmentAssignment(), dest.getTreatmentInformation().getTreatmentAssignment());
        assertEquals(1, dest.getTreatmentInformation().getCourseAgentsInternal().size());
    }


    public void testMigrateDeleteCourseAgent() throws Exception {
        src.getTreatmentInformation().setTreatmentAssignment(Fixtures.createTreatmentAssignment("tac1"));
        CourseAgent ca1 = Fixtures.createCourseAgent("a");
        ca1.getStudyAgent().setId(1);

        dest.getTreatmentInformation().addCourseAgent(ca1);
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertSame(src.getTreatmentInformation().getTreatmentAssignment(), dest.getTreatmentInformation().getTreatmentAssignment());
        assertEquals(0, dest.getTreatmentInformation().getCourseAgentsInternal().size());
    }


    public void testSynchronizeCourseAgent() throws Exception {
        src.getTreatmentInformation().setTreatmentAssignment(Fixtures.createTreatmentAssignment("tac1"));
        CourseAgent ca1 = Fixtures.createCourseAgent("a");
        ca1.setLotNumber("1");
        ca1.setFormulation("f");
        ca1.setComments("c");
        ca1.setTotalDoseAdministeredThisCourse(new BigDecimal(1.0));
        ca1.getStudyAgent().setId(1);
        StudyAgent sa = ca1.getStudyAgent();

        src.getTreatmentInformation().addCourseAgent(ca1);

        CourseAgent ca2 = Fixtures.createCourseAgent("a");
        ca2.setStudyAgent(sa);

        dest.getTreatmentInformation().addCourseAgent(ca2);
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertSame(src.getTreatmentInformation().getTreatmentAssignment(), dest.getTreatmentInformation().getTreatmentAssignment());
        assertEquals(1, dest.getTreatmentInformation().getCourseAgentsInternal().size());
        assertEquals("f", ca2.getFormulation()) ;
        assertEquals("1", ca2.getLotNumber()) ;
        assertEquals("c", ca2.getComments()) ;
        assertEquals(new BigDecimal(1.0), ca2.getTotalDoseAdministeredThisCourse()) ;
    }
}
