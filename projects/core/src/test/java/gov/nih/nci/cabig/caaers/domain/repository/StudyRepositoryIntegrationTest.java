package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyRepositoryIntegrationTest extends CaaersDbNoSecurityTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
            "organizationDao");

    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
            "participantDao");

    StudyRepository studyRepository = (StudyRepository) getApplicationContext().getBean("studyRepository");

    public String getTestDataFileName() {
        String fileName = "testdata/StudyRepositoryTest.xml";
        return fileName;
    }

    public void testSearchStudyByExample() throws Exception {
        Study study = new Study();
        study.setShortTitle("New Study");
        List<Study> studies = studyRepository.search(study);
        assertNotNull("Studes is null", studies);
    }
}