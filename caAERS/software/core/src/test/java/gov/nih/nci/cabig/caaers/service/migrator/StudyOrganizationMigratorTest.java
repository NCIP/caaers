package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.dbunit.operation.DatabaseOperation;

import java.util.ArrayList;

public class StudyOrganizationMigratorTest extends DaoTestCase {

    private StudyOrganizationMigrator som;
    private StudyDao studyDao;
    private OrganizationDao organizationDao;
    private Study srcStudy;
    private Study destStudy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        som = (StudyOrganizationMigrator)applicationContext.getBean("studyOrganizationMigrator");
        organizationDao = (OrganizationDao)applicationContext.getBean("organizationDao");
        studyDao = (StudyDao)applicationContext.getBean("studyDao");

        srcStudy = new LocalStudy();
        srcStudy.setStudyOrganizations(new ArrayList<StudyOrganization>());

        destStudy = new LocalStudy();
        destStudy.setStudyOrganizations(new ArrayList<StudyOrganization>());

        StudyOrganization so = new StudySite();
        so.setOrganization(new LocalOrganization());
        so.getOrganization().setNciInstituteCode("NCI");
        so.getOrganization().setName("National Cancer Institute - UPDATED");
        srcStudy.getStudyOrganizations().add(so);
    }

    public void testChangeExistingOrganizationName() {
        Organization o = organizationDao.getByNCIcode("NCI");
        assertEquals(4, organizationDao.getAll().size());
        assertEquals("National Cancer Institute", o.getName());
        DomainObjectImportOutcome<Study> outcome = new DomainObjectImportOutcome<Study>();
        som.migrate(srcStudy, destStudy, outcome);
        assertEquals("National Cancer Institute - UPDATED", destStudy.getStudyOrganizations().get(0).getOrganization().getName());
        assertEquals(-1001, destStudy.getStudyOrganizations().get(0).getOrganization().getId().intValue());
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }


}
