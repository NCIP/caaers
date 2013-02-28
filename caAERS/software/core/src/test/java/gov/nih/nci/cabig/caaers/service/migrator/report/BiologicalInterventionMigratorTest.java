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
 * Date: 1/28/13
 */
public class BiologicalInterventionMigratorTest extends TestCase {

	BiologicalInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new BiologicalInterventionMigrator();
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
        oi.setName("Other Biological Name");
        oi.setDescription("Other Biological Description");
        oi.setRetiredIndicator(false);

        oi.setStudyTherapyType(StudyTherapyType.BIOLOGICAL_VACCINE);

        study.getOtherInterventions().add(oi);
        String desc = "Testing Biological Migrator";
        
        
        BiologicalIntervention bi = Fixtures.createBiologicalIntervention(desc, oi);
        src.getBiologicalInterventions().add(bi);

    }

    public void testMigrateWithValues() throws Exception {
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals("Testing Biological Migrator", dest.getBiologicalInterventions().get(0).getDescription());
    	assertEquals(1, dest.getBiologicalInterventions().size());
    }
}
