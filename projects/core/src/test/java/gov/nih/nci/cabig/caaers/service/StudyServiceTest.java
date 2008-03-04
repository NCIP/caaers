/**
 *
 */
package gov.nih.nci.cabig.caaers.service;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * @author Krikor Krumlian
 * 
 */
public class StudyServiceTest extends CaaersDbTestCase {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
                    "organizationDao");

    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
                    "participantDao");

    StudyService studyService = (StudyService) getApplicationContext().getBean("studyService");

    public String getTestDataFileName() {
        String fileName = "testdata/StudyServiceTest.xml";
        return fileName;
    }

    public void testSearchStudyByExample() throws Exception {
        Study study = new Study();
        study.setShortTitle("New Study");
        List<Study> studies = studyService.search(study);
        assertNotNull("Studes is null", studies);
    }

}
