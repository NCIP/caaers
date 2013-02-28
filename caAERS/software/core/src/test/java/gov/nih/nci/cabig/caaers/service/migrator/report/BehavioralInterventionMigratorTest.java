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

/**
 * User: medav
 * Date: 1/14/13
 */
public class BehavioralInterventionMigratorTest extends TestCase {

	BehavioralInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new BehavioralInterventionMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();

        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");

        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);

        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        assignment.setStudySite(site);
        assignment.getStudySite().setStudy(study);

        //Set the destination.

        dest.setReportingPeriod(period);
        //Set the destination.

        dest.setReportingPeriod(period);
        OtherIntervention oi = new OtherIntervention();
        oi.setId(-100);
        oi.setName("Other Behavioral Name");
        oi.setDescription("Other Behavioral Description");
        oi.setRetiredIndicator(false);

        oi.setStudyTherapyType(StudyTherapyType.BEHAVIORAL);

        study.getOtherInterventions().add(oi);

        String desc = "Testing Behavioral Intervention Migrator";

        BehavioralIntervention bi = Fixtures.createBehavioralIntervention(desc, oi);
        src.getBehavioralInterventions().add(bi);
        
    }

    public void testMigrateWithValues() throws Exception {
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
        System.out.println( dest.getBehavioralInterventions().get(0).getDescription());
    	assertEquals("Testing Behavioral Intervention Migrator", dest.getBehavioralInterventions().get(0).getDescription());
    	assertEquals(1, dest.getBehavioralInterventions().size());
    }
}
