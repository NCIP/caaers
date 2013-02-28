/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

import java.util.Date;

/**
 * User: Biju Joseph
 * Date: 1/9/13
 */
public class TreatmentInformationMigratorTest extends TestCase {
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
}
