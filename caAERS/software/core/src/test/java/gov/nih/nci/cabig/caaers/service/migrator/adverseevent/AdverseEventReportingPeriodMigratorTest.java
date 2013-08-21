/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: Biju Joseph
 * Date: 2/5/13
 */
public class AdverseEventReportingPeriodMigratorTest extends AbstractTestCase {
    AdverseEventReportingPeriodMigrator migrator;
    private StudyDao studyDao;
    private ParticipantDao participantDao;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        migrator = new AdverseEventReportingPeriodMigrator();
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
    }

    public void testPreMigrate() throws Exception {
       replayMocks();
       AdverseEventReportingPeriod src = new AdverseEventReportingPeriod();
       AdverseEventReportingPeriod dest = new AdverseEventReportingPeriod();
       TreatmentAssignment assignment = Fixtures.createTreatmentAssignment();
       src.setTreatmentAssignment(assignment);
       
       src.getTreatmentAssignment().setCode("A");
       src.setTreatmentAssignmentDescription("O");
       //
       
       DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome = new DomainObjectImportOutcome<AdverseEventReportingPeriod>();
       migrator.preMigrate(src, dest, outcome);
       assertTrue(outcome.hasErrors());
       // Removed this validation in the Source as the Adverse Events can be empty.
       // assertEquals("WS_AEMS_025", outcome.getValidationErrors().getErrors().get(0).getCode());
       
       assertEquals("WS_AEMS_083", outcome.getValidationErrors().getErrors().get(0).getCode());

       verifyMocks();
    }
}
