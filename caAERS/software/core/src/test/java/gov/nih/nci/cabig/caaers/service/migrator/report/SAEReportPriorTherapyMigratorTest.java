/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;



import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class SAEReportPriorTherapyMigratorTest extends TestCase {

	SAEReportPriorTherapyMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new SAEReportPriorTherapyMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);
        
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        dest.setReportingPeriod(period);
     
        OtherIntervention oi = new OtherIntervention();
        oi.setStudy(study);
        String desc = "Testing SAE Report Prior Therapy Migrator";
        
        PriorTherapy therapy = Fixtures.createPriorTherapy("Prior Therapy");
        SAEReportPriorTherapy si = Fixtures.createSAEReportPriorTherapy(desc, null, null, therapy);
        src.getSaeReportPriorTherapies().add(si);
               
    }

    public void testMigrateWithValues() throws Exception {
    	
    //	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    //	assertEquals(1, dest.getSaeReportPriorTherapies().size());
    //	assertEquals("Testing SAE Report Prior Therapy Migrator", dest.getSaeReportPriorTherapies().get(0).getOther());
    }
}
