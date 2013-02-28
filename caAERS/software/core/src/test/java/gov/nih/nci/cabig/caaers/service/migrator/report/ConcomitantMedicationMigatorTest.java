/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;


import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class ConcomitantMedicationMigatorTest extends TestCase {

	ConcomitantMedicationMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new ConcomitantMedicationMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        dest.setReportingPeriod(period);
     
        ConcomitantMedication cm = Fixtures.createConcomitantMedication(Boolean.FALSE);
        List<StudyParticipantConcomitantMedication> cms = new ArrayList<StudyParticipantConcomitantMedication>();
        
        cms.add(StudyParticipantConcomitantMedication.createAssignmentConcomitantMedication(cm));
        assignment.setConcomitantMedications(cms);
        
        src.getConcomitantMedications().add(cm);
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);       
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(2, dest.getConcomitantMedications().size());
    	assertEquals(Boolean.FALSE, dest.getConcomitantMedications().get(0).getStillTakingMedications());
    }
}
