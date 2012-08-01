package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.event.*;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class AbstractEventListenerTest extends AbstractTestCase {

    AuthenticationSuccessListener listener;

    @Override
    public void setUp() throws Exception {
        super.setUp();  
        listener = new AuthenticationSuccessListener();
        listener.setEventMonitor(new EventMonitor());
        SecurityTestUtils.switchToSuperuser();
    }

    public void testIsSupported() throws Exception {
        AuthenticationSuccessEvent event = new AuthenticationSuccessEvent(SecurityUtils.getAuthentication());
        assertTrue(listener.isSupported(event));
        assertFalse(listener.isSupported(new StudyModificationEvent(SecurityUtils.getAuthentication(), new LocalStudy())));
    }

    public void testPreProcessAndPostProcess() throws Exception {
        ApplicationEvent e1 = new StudyModificationEvent(SecurityUtils.getAuthentication(), new LocalStudy());
        listener.preProcess(e1);
        assertNotNull(listener.correlationId);
        listener.postProcess(e1);
        AuthenticationSuccessEvent e2 = new AuthenticationSuccessEvent(SecurityUtils.getAuthentication());
        listener.preProcess(e2);
        assertNotNull(listener.correlationId);
        listener.postProcess(e2);
    }

    public void testPreProcessAndPostProcessWithoutEventMonitor() throws Exception {
        
        assertNotNull(listener.getEventMonitor());
        listener.setEventMonitor(null);
        assertNull(listener.getEventMonitor());

        ApplicationEvent e1 = new StudyModificationEvent(SecurityUtils.getAuthentication(), new LocalStudy());
        listener.preProcess(e1);
        assertNull(listener.correlationId);
        listener.postProcess(e1);
        AuthenticationSuccessEvent e2 = new AuthenticationSuccessEvent(SecurityUtils.getAuthentication());
        listener.preProcess(e2);
        assertNull(listener.correlationId);
        listener.postProcess(e2);
    }

    public void testIsSupportedForAllListeners(){


        Authentication a = SecurityUtils.getAuthentication();
        assertTrue(new StudyModificationEventListener().isSupported(new StudyModificationEvent(a, new LocalStudy())));
        assertTrue(new CourseModificationEventListener().isSupported(new CourseModificationEvent(a, new AdverseEvent())));
        assertTrue(new AdverseEventModificationEventListener().isSupported(new AdverseEventModificationEvent(a, new LocalStudy())));
        assertTrue(new ExpeditedReportModificationEventListener().isSupported(new ExpeditedReportModificationEvent(a, new ExpeditedAdverseEventReport())));
        assertTrue(new InvestigatorModificationEventListener().isSupported(new InvestigatorModificationEvent(a, new LocalInvestigator())));
        assertTrue(new OrganizationModificationEventListener().isSupported(new OrganizationModificationEvent(a, new LocalOrganization())));
        assertTrue(new ReportModificationEventListener().isSupported(new ReportModificationEvent(a, new LocalOrganization())));
        assertTrue(new ResearchStaffModificationEventListener().isSupported(new ResearchStaffModificationEvent(a, new LocalOrganization())));
        assertTrue(new SubjectModificationEventListener().isSupported(new SubjectModificationEvent(a, new LocalOrganization())));

        assertFalse(new StudyModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new CourseModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new AdverseEventModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ExpeditedReportModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new InvestigatorModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new OrganizationModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ReportModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ResearchStaffModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new SubjectModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));

    }


}
