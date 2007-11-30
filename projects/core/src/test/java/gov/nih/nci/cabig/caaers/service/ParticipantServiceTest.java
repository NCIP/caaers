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
    public void testFindBodySurfaceArea() throws Exception { 
    	double wt = 3234;
    	double ht = 4424;
    	double bsa = Math.sqrt((ht * wt) /3600);
    	double calBsa = participantService.bodySuraceArea(ht, "Centimeter", wt, "Kilogram");
    	double nwt = (wt /2.20462262185);
    	double nht = (ht * 2.54);
    	
    	double bsa2 = Math.sqrt(( nht * nwt ) / 3600);
    	double calBsa2 = participantService.bodySuraceArea(ht, "Inch", wt, "Pound");
    	assertEquals("BodySurfaceArea is wrong",bsa2, calBsa2);
    }
}
