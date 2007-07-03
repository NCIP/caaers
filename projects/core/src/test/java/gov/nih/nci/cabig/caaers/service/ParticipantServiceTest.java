/**
 *
 */
package gov.nih.nci.cabig.caaers.service;

import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author Krikor Krumlian
 *
 */
public class ParticipantServiceTest extends CaaersDbTestCase {

    //private SiteDao siteDao = (SiteDao) getApplicationContext().getBean("siteDao");
    //private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");
    //StudyService studyService = (StudyService) getApplicationContext().getBean("studyService");
    ParticipantService participantService = (ParticipantService) getApplicationContext().getBean("participantService");
    
    public String getTestDataFileName() {
        String fileName = "testdata/ParticipantServiceTest.xml";
        return fileName;
    }
    
    public void testSearchParticipantByExample()throws Exception
    {
    	Participant participant = new Participant();
    	participant.setFirstName("Dilbert");
    	
    	List<Participant> participants = participantService.search(participant);
    	assertNotNull("Participant is null", participants);
    	
    }

}
