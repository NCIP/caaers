/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/14/13
 */
public class PhysicianMigratorTest extends TestCase {

    PhysicianMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    Physician physician;

    public void setUp() throws Exception {
        migrator = new PhysicianMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        period.setAssignment(Fixtures.createAssignment());
        dest.setReportingPeriod(period);
        
        		
        StudySite site = Fixtures.createStudySite(Fixtures.createOrganization("Mayo Clinic"), 26);
        study.addStudySite(site);
        
        StudyInvestigator studyInv = Fixtures.createStudyInvestigator("Study Investigator", org);
        studyInv.setRetiredIndicator(false);
        studyInv.setStartDate(new Date("01/12/2013"));
        studyInv.setEndDate(new Date("12/12/2014"));
        List<StudyInvestigator> si = new ArrayList<StudyInvestigator>();
        si.add(studyInv);
        site.setStudyInvestigators(si);
        
        Investigator inv1 = Fixtures.createInvestigator("Investigator 1");
        SiteInvestigator siteInv1  = Fixtures.createSiteInvestigator(org, inv1);
       
        studyInv.setSiteInvestigator(siteInv1);
        
        
        Address address = new Address();
        address.setZip("20171");
        address.setCity("Herndon");
        address.setState("VA");
        address.setStreet("park center road");


        String firstName = "first name";
        String lastName = "last name";
        String middleName = "middle name";

        ReportVersion reportVersion = new ReportVersion();
        reportVersion.setCcEmails("cc email");

        String title = "title";
       
        siteInv1.setAddress(address);
        inv1.setFirstName(firstName);
        inv1.setLastName(lastName);
        inv1.setMiddleName(middleName);
        inv1.setTitle(title);
        
        
        physician = new Physician();
        physician.setTitle(title);
        physician.setVersion(2);
        physician.setMiddleName(middleName);
        physician.setLastName(lastName);
        physician.setFirstName(firstName);
        physician.setEmailAddress("joe@abc.com");
        physician.setAddress(address);
        
      
       
    }


    public void testMigrateWithValues() throws Exception {
        src.setPhysician(physician);
        dest.setPhysician(new Physician());
        migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
        assertNotNull(dest.getPhysician().getEmailAddress());
        assertEquals("first name", dest.getPhysician().getFirstName());
    }
}
