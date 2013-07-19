/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Biju Joseph
 * Date: 1/9/13
 */
public class TreatmentInformationMigratorTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    TreatmentInformationMigrator migrator;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    AdverseEventReportingPeriod adverseEventReportingPeriod;

    StudyParticipantAssignment assignment;
    StudyAgent sa1;
    StudyAgent sa2;


    public void setUp() throws Exception {
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        migrator = new TreatmentInformationMigrator();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        adverseEventReportingPeriod = Fixtures.createReportingPeriod();
        dest.setReportingPeriod(adverseEventReportingPeriod);
        Study study = dest.getStudy();
        sa1 = Fixtures.createStudyAgent(1, "1");
        sa2 = Fixtures.createStudyAgent(2, "2");
        sa2.getAgent().setNscNumber("2");
        study.addStudyAgent(sa1);
        study.addStudyAgent(sa2);
    }

    public void testMigrate() throws Exception {
        Date d = new Date();

        StudyParticipantAssignment spa = Fixtures.createAssignment();
        spa.setStartDateOfFirstCourse(d);
        List<AdverseEventReportingPeriod> rpList = new ArrayList<AdverseEventReportingPeriod>();
        AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
        rp.setCycleNumber(1);
        rpList.add(rp);
        spa.setReportingPeriods(rpList);
        dest.setAssignment(spa);

        TreatmentInformation ti = src.getTreatmentInformation();
        ti.setTotalCourses(1);
        ti.setFirstCourseDate(d);
        ti.setInvestigationalAgentAdministered(true);
         migrator.migrate(src,dest,outcome);
        assertSame(d, dest.getTreatmentInformation().getFirstCourseDate());
        assertEquals(new Integer(1), dest.getTreatmentInformation().getTotalCourses());
        assertTrue(dest.getTreatmentInformation().getCourseAgentsInternal().isEmpty());

    }


    public void testMigrateWithWrongCourseAgent() throws Exception {
        Date d = new Date();
        TreatmentInformation ti = src.getTreatmentInformation();
        ti.addCourseAgent(Fixtures.createCourseAgent(null));
        ti.addCourseAgent(Fixtures.createCourseAgent("2"));


        migrator.migrate(src,dest,outcome);
        System.out.println(outcome.getMessages());
        assertEquals(1, dest.getTreatmentInformation().getCourseAgents().size());
        assertEquals(new Integer(2), dest.getTreatmentInformation().getCourseAgents().get(0).getStudyAgent().getId());
    }

    public void testTreatmentAssignmentDefaultValue() throws Exception {
        TreatmentAssignment ta = Fixtures.createTreatmentAssignment("abcd");
        adverseEventReportingPeriod.setTreatmentAssignment(ta);
        src.getTreatmentInformation().setTotalCourses(1);
        Date yesterday = DateUtils.yesterday();
        src.getTreatmentInformation().setFirstCourseDate(yesterday);
        migrator.migrate(src,dest,outcome);
        assertFalse(outcome.hasErrors());
        assertSame(dest.getTreatmentInformation().getTreatmentAssignment(), ta);
    }

    public void testTreatmentAssignmentOverridenValue() throws Exception {
        TreatmentAssignment ta = Fixtures.createTreatmentAssignment("abcd");
        TreatmentAssignment taOverriden = Fixtures.createTreatmentAssignment("efg");
        TreatmentAssignment studyTac1 = Fixtures.createTreatmentAssignment("efg");
        adverseEventReportingPeriod.setTreatmentAssignment(ta);
        adverseEventReportingPeriod.getStudy().addTreatmentAssignment(studyTac1);
        src.getTreatmentInformation().setTreatmentAssignment(taOverriden);
        migrator.migrate(src,dest,outcome);
        assertFalse(outcome.hasErrors());
        assertSame(dest.getTreatmentInformation().getTreatmentAssignment(), studyTac1);

    }

    public void testTreatmentAssignmentOverridenValueNotPresentInStudy() throws Exception {
        TreatmentAssignment ta = Fixtures.createTreatmentAssignment("abcd");
        TreatmentAssignment taOverriden = Fixtures.createTreatmentAssignment("unknown");
        TreatmentAssignment studyTac1 = Fixtures.createTreatmentAssignment("efg");
        adverseEventReportingPeriod.setTreatmentAssignment(ta);
        adverseEventReportingPeriod.getStudy().addTreatmentAssignment(studyTac1);
        src.getTreatmentInformation().setTreatmentAssignment(taOverriden);
        migrator.migrate(src,dest,outcome);
        assertTrue(outcome.hasErrors());

    }
}
