/**
 *
 */
package gov.nih.nci.cabig.caaers.service;

import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author Krikor Krumlian
 *
 */
public class StudyServiceTest extends CaaersDbTestCase {

    private SiteDao siteDao = (SiteDao) getApplicationContext().getBean("siteDao");
    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");
    StudyService studyService = (StudyService) getApplicationContext().getBean("studyService");
    public String getTestDataFileName() {
        String fileName = "testdata/StudyServiceTest.xml";
        return fileName;
    }
    
    public void testSearchStudyByExample()throws Exception
    {
    	Study study = new Study();
    	study.setShortTitle("New Study");
    	List<Study> studies = studyService.search(study);
    	assertNotNull("Studes is null", studies);
    	
    }

}
