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
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/14/13
 */
public class ReporterMigratorTest extends TestCase {

    ReporterMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    Reporter reporter;

    public void setUp() throws Exception {
        migrator = new ReporterMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        period.setAssignment(Fixtures.createAssignment());
        dest.setReportingPeriod(period);
        
        		
        StudySite site = Fixtures.createStudySite(Fixtures.createOrganization("Mayo Clinic"), 26);
        study.addStudySite(site);
        
        List<UserGroupType> userGroupTypeList = new ArrayList<UserGroupType>();
        userGroupTypeList.add(UserGroupType.ae_reporter);
        userGroupTypeList.add(UserGroupType.data_reader);
        
        ResearchStaff staff = Fixtures.createResearchStaff(org, userGroupTypeList , "rstaff" );
        SiteResearchStaff siteResearchStaff = Fixtures.createSiteResearchStaff(org, staff);
        
        StudyPersonnel studyPer = Fixtures.createStudyPersonnel(staff);
        studyPer.setSiteResearchStaff(siteResearchStaff);
       
        
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
       
        siteResearchStaff.setAddress(address);
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setMiddleName(middleName);
        staff.setTitle(title);
        
        
        reporter = new Reporter();
        reporter.setTitle(title);
        reporter.setVersion(2);
        reporter.setMiddleName(middleName);
        reporter.setLastName(lastName);
        reporter.setFirstName(firstName);
        reporter.setEmailAddress("joe@abc.com");
        reporter.setAddress(address);
        
      
       
    }


    public void testMigrateWithValues() throws Exception {
        src.setReporter(reporter);
      //  dest.setReporter(new Reporter());
        migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
        assertNotNull(dest.getReporter().getEmailAddress());
        assertEquals("first name", dest.getReporter().getFirstName());
    }
}
