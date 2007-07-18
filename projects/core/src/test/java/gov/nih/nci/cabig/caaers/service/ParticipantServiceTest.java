/**
 *
 */
package gov.nih.nci.cabig.caaers.service;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;


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
