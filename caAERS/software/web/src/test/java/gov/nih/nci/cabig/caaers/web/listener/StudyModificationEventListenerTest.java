package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import junit.framework.TestCase;
import org.acegisecurity.Authentication;

/**
 * @author: Biju Joseph
 */
public class StudyModificationEventListenerTest extends AbstractTestCase {
    StudyModificationEventListener listener;
    StudyRepository studyRepository;
    CaaersSecurityFacade caaersSecurityFacade;
    Study study;
    
    public void setUp() throws Exception {
        listener = new StudyModificationEventListener();
        studyRepository = registerMockFor(StudyRepository.class);
        caaersSecurityFacade = registerMockFor(CaaersSecurityFacade.class);
       
        study = Fixtures.createStudy("x");
        
        listener.setCaaersSecurityFacade(caaersSecurityFacade);
        listener.setStudyRepository(studyRepository);

        SecurityTestUtils.switchToSuperuser();
    }

    public void testPreProcess() throws Exception {
        Authentication a = SecurityUtils.getAuthentication();
        StudyModificationEvent event = new StudyModificationEvent(a, study);
        studyRepository.provisionStudyTeam(study);
        caaersSecurityFacade.clearUserCache("SYSTEM");
        replayMocks();
        listener.preProcess(event);
        verifyMocks();
    }
}
